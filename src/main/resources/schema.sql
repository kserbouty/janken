CREATE TABLE IF NOT EXISTS games (
    id          serial PRIMARY KEY,
    wins        integer NOT NULL,
    draws       integer NOT NULL,
    losses      integer NOT NULL
);