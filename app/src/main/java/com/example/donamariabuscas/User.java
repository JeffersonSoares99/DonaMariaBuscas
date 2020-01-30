package com.example.donamariabuscas;

public class User {

    private final String uuid;
    private final String username;
    private final String userendereco;
    private final String usercnpj;
    private final String usertelefone;
    private final String useremail;
    private final String profileUrl;


    public User(String uuid, String username, String userendereco, String usercnpj, String usertelefone, String useremail, String profileUrl) {
        this.uuid = uuid;
        this.username = username;
        this.userendereco = userendereco;
        this.usercnpj = usercnpj;
        this.usertelefone = usertelefone;
        this.useremail = useremail;
        this.profileUrl = profileUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getUserendereco() {
        return userendereco;
    }

    public String getUsercnpj() {
        return usercnpj;
    }

    public String getUsertelefone() {
        return usertelefone;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
