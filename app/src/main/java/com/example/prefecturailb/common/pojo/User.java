package com.example.prefecturailb.common.pojo;

import com.google.firebase.database.Exclude;


public class User {
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String TYPE="type";
    public static final String PROFESOR="profesor";
    public static final String PREFECTO="prefecto";



    private String name;
    private String email;
    private String type;
    @Exclude
    private String password;
    @Exclude
    private String uid;

    public User() {
    }


    @Exclude
    public String getUid() {
        return uid;
    }
    @Exclude
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    @Exclude
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return uid != null ? uid.equals(user.uid) : user.uid == null;
    }

    @Override
    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }


}
