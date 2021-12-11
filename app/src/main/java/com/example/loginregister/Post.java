package com.example.loginregister;

import java.io.Serializable;

public class Post implements Serializable {
    private String postId;
    private String postTitle;
    private String postMenu;
    private String postEatLocation;
    private String postPeopleNum;
    private String postEndTime;
    private String postContents;
    private String postWrittenDate;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostMenu() {
        return postMenu;
    }

    public void setPostMenu(String postMenu) {
        this.postMenu = postMenu;
    }

    public String getPostEatLocation() {
        return postEatLocation;
    }

    public void setPostEatLocation(String postEatLocation) {
        this.postEatLocation = postEatLocation;
    }

    public String getPostPeopleNum() {
        return postPeopleNum;
    }

    public void setPostPeopleNum(String postPeopleNum) {
        this.postPeopleNum = postPeopleNum;
    }

    public String getPostEndTime() {
        return postEndTime;
    }

    public void setPostEndTime(String postEndTime) {
        this.postEndTime = postEndTime;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

    public String getPostWrittenDate(){
        return postWrittenDate;
    }

    public void setPostWrittenDate(String postWrittenDate){
        this.postWrittenDate = postWrittenDate;
    }

}