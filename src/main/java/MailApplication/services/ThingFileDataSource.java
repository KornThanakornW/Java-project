package MailApplication.services;

import MailApplication.models.MatterMail;
import MailApplication.models.ParcelPost;
import MailApplication.models.Thing;
import MailApplication.models.ThingList;

import java.io.*;

public class ThingFileDataSource {
    private String fileDirectoryName;
    private String fileName;
    private ThingList things;

    public ThingFileDataSource(String fileDirectoryName, String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted(){
        File file = new File(fileDirectoryName);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Cannot create " + filePath);
            }
        }
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while((line = reader.readLine()) != null){
            String[] data = line.split(",");
            if (data[0].equals("Letter")) {
                Thing letter = new Thing(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                        data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim());
                things.add(letter);
            }
            else  if (data[0].equals("MatterMail")){
                Thing matterMail = new MatterMail(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                        data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim());
                things.add(matterMail);
            }
            else if(data[0].equals("ParcelPost")){
                Thing parcelPost = new ParcelPost(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                        data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim(),data[10].trim());
                things.add(parcelPost);
            }
        }
        reader.close();
    }

    public ThingList getThingsData(){
        try{
            things = new ThingList();
            readData();
        } catch (FileNotFoundException e){
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return things;
    }

    public void setThingsData(ThingList things) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Thing thing : things.toList()) {
                if (thing.getTypeThing().equals("Letter")) {
                    String line = thing.getTypeThing() + ","
                            + thing.getStatusMail() + ","
                            + thing.getSenderInfo() + ","
                            + thing.getSenderTime() + ","
                            + thing.getReceiverOfficerName() + ","
                            + thing.getRoom() + ","
                            + thing.getReceiverName() + ","
                            + thing.getReceiveTime() + ","
                            + thing.getPackingSize();
                    writer.append(line);
                    writer.newLine();
                } else if (thing.getTypeThing().equals("MatterMail")) {
                    String line = thing.getTypeThing() + ","
                            + thing.getStatusMail() + ","
                            + thing.getSenderInfo() + ","
                            + thing.getSenderTime() + ","
                            + thing.getReceiverOfficerName() + ","
                            + thing.getRoom() + ","
                            + thing.getReceiverName() + ","
                            + thing.getReceiveTime() + ","
                            + thing.getPackingSize()+ ","
                            + ((MatterMail)thing).getPriority();
                    writer.append(line);
                    writer.newLine();
                } else if (thing.getTypeThing().equals("ParcelPost")) {
                    String line = thing.getTypeThing() + ","
                            + thing.getStatusMail() + ","
                            + thing.getSenderInfo() + ","
                            + thing.getSenderTime() + ","
                            + thing.getReceiverOfficerName() + ","
                            + thing.getRoom() + ","
                            + thing.getReceiverName() + ","
                            + thing.getReceiveTime() + ","
                            + thing.getPackingSize()+ ","
                            + ((ParcelPost)thing).getServiceCompany()+ ","
                            + ((ParcelPost)thing).getTrackingNumber();
                    writer.append(line);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot write " + filePath);
        }
    }

}
