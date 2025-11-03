package com.janken.api.repository;

import com.janken.api.model.Game;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Logger;

@Repository
public class GameRepository {

    Logger logger = Logger.getLogger(getClass().getName());
    private final DataSource dataSource;

    public GameRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Game findScores() throws SQLException {
        String sql = "SELECT id, wins, draws, losses FROM games WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Game(
                        resultSet.getInt("id"),
                        resultSet.getInt("wins"),
                        resultSet.getInt("draws"),
                        resultSet.getInt("losses"));
            } else {
                this.start();
                Game initialGame = this.findScores();
                return new Game(
                        initialGame.getId(),
                        initialGame.getWins(),
                        initialGame.getDraws(),
                        initialGame.getLosses());
            }
        } catch (SQLException se) {
            logger.info(se.getMessage());
        }

        return null;
    }

    public void save(Game game) {
        String sql = "UPDATE games SET wins=?, draws=?, losses=? WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, game.getWins());
            preparedStatement.setInt(2, game.getDraws());
            preparedStatement.setInt(3, game.getLosses());
            preparedStatement.setInt(4, game.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            logger.info(se.getMessage());
        }
    }

    private void start() throws SQLException {
        String sql = "INSERT INTO games (id, wins, draws, losses) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            logger.info(se.getMessage());
        }
    }
}