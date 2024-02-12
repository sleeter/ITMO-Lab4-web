--liquibase formatted sql

--changeset sleeter:6_add_verified_column
ALTER TABLE users ADD COLUMN verified BOOLEAN NOT NULL DEFAULT FALSE;