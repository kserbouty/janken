<h1 align="center">Jan-Ken-Pon</h1>

<p align="center">
    <a href="https://openjdk.org/projects/jdk-updates/">
        <img src="https://img.shields.io/badge/java-17-B07219?style=flat&logo=openjdk"
        alt="java-17" /></a>
    <a href="https://docs.spring.io/spring-boot/index.html">
        <img src="https://img.shields.io/badge/spring--boot-3-6DB33F?style=flat&logo=spring&logoColor=white"
        alt="springboot-3" /></a>
    <a href="https://maven.apache.org/">
        <img src="https://img.shields.io/badge/maven-3-C71A36?style=flat&logo=apachemaven"
        alt="maven-3" /></a>
    <a href="https://www.postgresql.org/">
        <img src="https://img.shields.io/badge/postgresql-18-4169E1?style=flat&logo=postgresql&logoColor=white"
        alt="postgresql-18" /></a>
    <a href="./LICENSE.md">
        <img src="https://img.shields.io/badge/license-mit-darkblue?style=flat&logo=github"
        alt="license-mit" /></a>
    <a href="https://github.com/serbouty/janken">
        <img src="https://img.shields.io/badge/status-complete-darkblue?style=flat&logo=github"
        alt="status-complete" /></a>
</p>

## Features

- MVC pattern with Spring Web.
- Data persistence through JDBC and PostgreSQL.
- Responsive layout with plain CSS.
- Support for containers from Docker.

## Project Dependencies

### Spring Boot

- Spring Web
- JDBC API
- PostgreSQL Driver
- Thymeleaf
- Docker Compose Support
- Spring Boot DevTools

## Screenshots

<br>
<div align="center">
    <img src="docs/game-start.png" alt="game-start">
    <img src="docs/game-result.png" alt="game-result">
</div>

## Installation

### Requirements

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) running in the background.
- [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download) set with Java 17.

### Launch

1. Open this project with **IntelliJ**.
2. Run **JankenApplication**.
3. Visit [localhost:8080](http://localhost:8080/) to start a game.

## License

This project is released under the [MIT License](./LICENSE.md).