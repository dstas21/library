package com.triangle.library.jms;

import com.triangle.library.model.Author;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorReceiver {

    public static final String CREATE_AUTHOR = "createAuthor";
    public static final String UPDATE_AUTHOR = "updateAuthor";

    @JmsListener(destination = CREATE_AUTHOR)
    public void createAuthorMessage(Author author) {
        System.out.println("Получен автор для создания: " + author);
    }

    @JmsListener(destination = UPDATE_AUTHOR)
    public void updateAuthorMessage(Author author) {
        System.out.println("Получен автор для обновления: " + author);
    }
}
