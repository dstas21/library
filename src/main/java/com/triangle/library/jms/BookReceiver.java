package com.triangle.library.jms;

import com.triangle.library.model.Book;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BookReceiver {

    public static final String CREATE_BOOK = "createBook";
    public static final String UPDATE_BOOK = "updateBook";

    @JmsListener(destination = CREATE_BOOK)
    public void createAuthorMessage(Book book) {
        System.out.println("Получена книга для создания: " + book);
    }

    @JmsListener(destination = UPDATE_BOOK)
    public void updateAuthorMessage(Book book) {
        System.out.println("Получен автор для обновления: " + book);
    }
}
