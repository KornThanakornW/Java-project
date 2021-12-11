package MailApplication.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class UserAccountList {

    private ArrayList<UserAccount> userAccounts;
    private UserAccount currentAccount;
    private UserAccount searchAccount;

    public UserAccountList() { userAccounts = new ArrayList<>(); }

    public void add(UserAccount userAccount){ userAccounts.add(userAccount); }

    public int signInCheck(String userName, String password){
        for(UserAccount account : userAccounts){
            if(account.getUsername().equals(userName) &&
                    account.getPassword().equals(password)){
                currentAccount = account;
                if(account.getUserType().equals("officer") ){
                    if( ((UserAccountOfficer)account).getStatus().equals("SUSPENDED")){

                        ((UserAccountOfficer)account).setAddTimesLoginBfSusp();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                        Date date = new Date();
                        ((UserAccountOfficer)account).setLastTimeLogin(simpleDateFormat.format(date));
                        //officer can not sign in
                        return 0;
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    Date date = new Date();
                    ((UserAccountOfficer)account).setLastTimeLogin(simpleDateFormat.format(date));
                    //officer can sign in
                    return 1;
                }
                //other can sign in
                return 2;
            }
            else if (account.getUsername().equals(userName)){
                //incorrect password
                return -1;
            }
        }
        currentAccount = null;
        // can not sign in, username not found
        return -2;
    }

    public UserAccount searchAccount(String userName, String password) {
        for(UserAccount account : userAccounts){
            if(account.getUsername().equals(userName) && account.getPassword().equals(password)){
                searchAccount = account;
            }
        }
        return searchAccount;
    }

    public Boolean searchUsername(String userName) {
        for(UserAccount account : userAccounts){
            if(account.getUsername().equals(userName)){
                return true;
            }
        }
        return false;
    }


    public ArrayList<UserAccount> toList() { return userAccounts; }

    public UserAccount getCurrentAccount() { return currentAccount; }

    public void sortArrayList(){
        Collections.sort(userAccounts);
    }
}
