CREATE SCHEMA IF NOT EXISTS library;

COMMENT ON SCHEMA library IS 'Схема для сервиса библиотеки';


CREATE TABLE IF NOT EXISTS library.authors
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(80)           NOT NULL,
    last_name  VARCHAR(80)           NOT NULL
);

create unique index if not exists author_first_and_last_name_unique_ix on library.authors (first_name,last_name);

COMMENT ON TABLE library.authors IS 'Таблица с авторами';
COMMENT ON COLUMN library.authors.id IS 'Идентификатор автора';
COMMENT ON COLUMN library.authors.first_name IS 'Имя автора';
COMMENT ON COLUMN library.authors.last_name IS 'Фамилия автора';
