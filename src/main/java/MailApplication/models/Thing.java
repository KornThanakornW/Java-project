package MailApplication.models;

public class Thing implements Comparable{
    private String typeThing;
    private String statusMail;
    private String senderInfo;
    private String senderTime;
    private String receiverOfficerName;
    private String room;
    private String receiverName;
    private String receiveTime;
    private String packingSize;


    public Thing(String typeThing, String statusMail, String senderInfo, String senderTime, String receiverOfficerName,
                 String room,String receiverName, String receiverTime, String packingSize) {
        this.typeThing = typeThing;
        this.statusMail = statusMail;
        this.senderInfo = senderInfo;
        this.senderTime = senderTime;
        this.receiverOfficerName = receiverOfficerName;
        this.room = room;
        this.receiverName = receiverName;
        this.receiveTime = receiverTime;
        this.packingSize = packingSize;
    }



    public String getPackingSize() { return packingSize; }

    public String getSenderInfo() { return senderInfo; }

    public String getSenderTime() { return senderTime; }

    public String getReceiverOfficerName() { return receiverOfficerName; }

    public String getRoom() { return room; }

    public String getReceiverName() { return receiverName; }

    public String getReceiveTime() { return receiveTime; }

    public String getStatusMail(){ return statusMail; }

    public String getTypeThing() { return typeThing; }

    public void receiveMail(String name, String time){
        receiverName = name;
        statusMail = "received";
        receiveTime = time;
    }

    @Override
    public int compareTo(Object otherObject) {
        Thing other = (Thing) otherObject;
        if (senderTime.compareTo(other.getSenderTime()) < 0) {return 1;}
        if (senderTime.compareTo(other.getSenderTime()) > 0) {return -1;}
        return 0;
    }
}
