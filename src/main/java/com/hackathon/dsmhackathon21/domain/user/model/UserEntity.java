package com.hackathon.dsmhackathon21.domain.user.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Getter
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;

    private String nickname;

    private String email;

    private String password;

    private List<String> wordBook;

    private List<String> grammarBook;

    public void updateWordBook(List<String> wordBook) {
        this.wordBook = wordBook;
    }

    public void updateGrammarBook(List<String> grammarBook) {
        this.grammarBook = grammarBook;
    }

    protected UserEntity() {}

    private UserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        wordBook = Collections.emptyList();
        grammarBook = Collections.emptyList();
    }

    public static UserEntity createEntity(String nickname, String email, String password) {
        return new UserEntity(nickname, email, password);
    }
}
