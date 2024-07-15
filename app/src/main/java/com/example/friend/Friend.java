package com.example.friend;

public class Friend {
    private String name;
    private int profileImage;

    public Friend(String name, int profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public int getProfileImage() {
        return profileImage;
    }
}
