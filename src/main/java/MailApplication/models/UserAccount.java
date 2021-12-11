package MailApplication.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class UserAccount extends RecursiveTreeObject<UserAccount> implements Comparable{

    private String userType;
    private String name;
    private String username;
    private String password;

    public UserAccount(String userType,String name, String username, String password){

        this.userType = userType;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getUserType() { return userType; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


    @Override
    public int compareTo(Object otherObject) {
        UserAccount other = (UserAccount) otherObject;
        if (name.compareTo(other.getName()) < 0) {return -1;}
        if (name.compareTo(other.getName()) > 0) {return 1;}
        return 0;
    }
}
