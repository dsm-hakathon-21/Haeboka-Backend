package com.hackathon.dsmhackathon21.domain.user.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;

    private String nickname;

    private String email;

    private String password;

    protected UserEntity() {}

    private UserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public static UserEntity createEntity(String nickname, String email, String password) {
        return new UserEntity(nickname, email, password);
    }
}
