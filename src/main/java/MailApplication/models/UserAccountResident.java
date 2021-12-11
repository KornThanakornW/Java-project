package MailApplication.models;

public class UserAccountResident extends UserAccount{
    private String room;

    public UserAccountResident(String userType, String name, String username, String password, String room) {
        super(userType, name, username, password);
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public int compareTo(Object otherObject) {
        UserAccountResident other = (UserAccountResident) otherObject;
        if (room.compareTo(other.getRoom()) < 0) {return -1;}
        if (room.compareTo(other.getRoom()) > 0) {return 1;}
        return 0;
    }
}
