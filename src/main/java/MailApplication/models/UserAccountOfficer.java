package MailApplication.models;

public class UserAccountOfficer extends UserAccount{

    private String lastTimeLogin;
    private String status;
    private int timesLoginBfSusp;

    public UserAccountOfficer(String userType, String name, String username, String password, String lastTimeLogin, String status, int timesLoginBfSusp) {
        super(userType,name, username, password);
        this.lastTimeLogin = lastTimeLogin;
        this.status = status;
        this.timesLoginBfSusp =timesLoginBfSusp;
    }


    public String getLastTimeLogin() { return lastTimeLogin; }

    public String getStatus() { return status; }

    public int getTimesLoginBfSusp() { return timesLoginBfSusp; }

    public void setLastTimeLogin(String lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastTimeLoginBfSuspToZero() {
        this.timesLoginBfSusp = 0;
    }


    public void setAddTimesLoginBfSusp() {
        this.timesLoginBfSusp = this.timesLoginBfSusp + 1;
    }

    @Override
    public int compareTo(Object otherObject) {
        UserAccountOfficer other = (UserAccountOfficer) otherObject;
        if (lastTimeLogin.compareTo(other.getLastTimeLogin()) < 0) {return 1;}
        if (lastTimeLogin.compareTo(other.getLastTimeLogin()) > 0) {return -1;}
        return 0;
    }

}
