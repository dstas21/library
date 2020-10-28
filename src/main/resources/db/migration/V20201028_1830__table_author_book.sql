CREATE TABLE IF NOT EXISTS library.author_book
(
    author_id BIGINT NOT NULL,
    book_id   BIGINT NOT NULL,
    primary key (author_id, book_id)
);

alter table library.author_book add constraint author_book__author_id_fk
    foreign key (author_id) references library.authors(id);

alter table library.author_book add constraint author_book__book_id_fk
    foreign key (book_id) references library.books(id);

COMMENT ON TABLE library.author_book IS 'Таблица связывает авторов с книгами';
COMMENT ON COLUMN library.author_book.author_id IS 'Идентификатор автора';
COMMENT ON COLUMN library.author_book.book_id IS 'Идентификатор книги';