CREATE TABLE IF NOT EXISTS library.books
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)          NOT NULL
);

COMMENT ON TABLE library.books IS 'Таблица с книгами';
COMMENT ON COLUMN library.books.id IS 'Идентификатор книги';
COMMENT ON COLUMN library.books.name IS 'Название книги';
