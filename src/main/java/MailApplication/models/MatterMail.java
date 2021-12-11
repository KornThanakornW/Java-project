package MailApplication.models;

public class MatterMail extends Thing {

    private String priority;

    public MatterMail(String typeThing, String statusMail, String senderInfo, String senderTime, String receiverOfficerName, String room,
                      String receiverName, String receiverTime, String packingSize, String priority) {
        super(typeThing, statusMail, senderInfo, senderTime, receiverOfficerName, room, receiverName, receiverTime, packingSize);
        this.priority = priority;
    }

    public String getPriority(){ return priority; }

    @Override
    public String toString() {
        return "MatterMail{ " +
                "priority=" + priority +
                " }";
    }
}
