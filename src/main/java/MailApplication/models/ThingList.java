package MailApplication.models;

import java.util.ArrayList;
import java.util.Collections;

public class ThingList {
    private ArrayList<Thing> things;
    private Thing currentThing;
    private Thing searchThing;



    public ThingList() { things = new ArrayList<>(); }

    public void add(Thing thing){ things.add(thing); }

    public ArrayList<Thing> toList() { return things; }

    public ArrayList<Thing> getReceivedThing() {
        ArrayList<Thing> thingsReceived = new ArrayList<Thing>();
        for(Thing t : things) {
            if (t.getStatusMail().equals("received")) {
                thingsReceived.add(t);
            }
        }
        return thingsReceived;
    }

    public ArrayList<Thing> getNotReceivedThing() {
        ArrayList<Thing> thingsNotReceived = new ArrayList<Thing>();
        for(Thing t : things) {
            if (t.getStatusMail().equals("notReceivedYet")) {
                thingsNotReceived.add(t);
            }
        }
        return thingsNotReceived;
    }

    public ArrayList<Thing> getMailFromRoom(String r) {
        ArrayList<Thing> thingsSearch = new ArrayList<Thing>();
        for(Thing t : things) {
            if (t.getRoom().equals(r)) {
                thingsSearch.add(t);
            }
        }
        return thingsSearch;
    }

    public void sortArrayList(){
        Collections.sort(things);
    }



}
