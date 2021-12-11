package MailApplication.models;

public class ParcelPost extends Thing {

    private String serviceCompany;
    private String trackingNumber;

    public ParcelPost(String typeThing, String statusMail, String senderInfo, String senderTime, String receiverOfficerName, String room,
                      String receiverName, String receiverTime, String packingSize, String serviceCompany, String trackingNumber) {
        super(typeThing, statusMail, senderInfo, senderTime, receiverOfficerName, room, receiverName, receiverTime, packingSize);
        this.serviceCompany = serviceCompany;
        this.trackingNumber = trackingNumber;
    }

    public String getServiceCompany() { return serviceCompany; }

    public String getTrackingNumber() { return trackingNumber; }
}
