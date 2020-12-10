CREATE TABLE IF NOT EXISTS library.author_book
(
    author_id BIGINT NOT NULL,
    book_id   BIGINT NOT NULL,
    primary key (author_id, book_id)
);

COMMENT ON TABLE library.author_book IS 'Таблица связывает авторов с книгами';
COMMENT ON COLUMN library.author_book.author_id IS 'Идентификатор автора';
COMMENT ON COLUMN library.author_book.book_id IS 'Идентификатор книги';