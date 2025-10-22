package com.janken.api.services;

import com.janken.api.models.Game;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class GameService {

    private final DataSource dataSource;

    public GameService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Game score() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT id, wins, draws, losses FROM games WHERE id = ?";

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return new Game(
                        resultSet.getInt("id"),
                        resultSet.getInt("wins"),
                        resultSet.getInt("draws"),
                        resultSet.getInt("losses"));
            } else {
                this.newGame();
                Game initialGame = this.score();

                return new Game(
                        initialGame.getId(),
                        initialGame.getWins(),
                        initialGame.getDraws(),
                        initialGame.getLosses());
            }
        } catch (SQLException se) {
            System.err.println(LocalDateTime.now() + " " + se.getMessage());
            throw se;
        } finally {
            if (resultSet != null) { resultSet.close(); }
            if (preparedStatement != null) { preparedStatement.close(); }
            if (connection != null) { connection.close(); }
        }
    }

    public void save(Game game) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE games SET wins=?, draws=?, losses=? WHERE id=?";

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, game.getWins());
            preparedStatement.setInt(2, game.getDraws());
            preparedStatement.setInt(3, game.getLosses());
            preparedStatement.setInt(4, game.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.err.println(LocalDateTime.now() + " " + se.getMessage());
            throw se;
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (connection != null) { connection.close(); }
        }
    }

    private void newGame() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO games (id, wins, draws, losses) VALUES (?, ?, ?, ?)";

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.err.println(LocalDateTime.now() + " " + se.getMessage());
            throw se;
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (connection != null) { connection.close(); }
        }
    }
}