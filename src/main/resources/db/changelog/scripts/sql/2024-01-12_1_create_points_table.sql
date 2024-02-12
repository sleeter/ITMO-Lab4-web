--liquibase formatted sql

--changeset sleeter:1_create_points_table
CREATE TABLE IF NOT EXISTS points
(
    id			SERIAL            PRIMARY KEY,
    x           float8            NOT NULL,
    y          	integer           NOT NULL,
    r           float8            NOT NULL,
    cur_time    VARCHAR(255)      NOT NULL,
    script_time integer           NOT NULL,
    is_hit      boolean           NOT NULL
);
