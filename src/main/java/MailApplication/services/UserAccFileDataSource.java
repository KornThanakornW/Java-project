package MailApplication.services;

import MailApplication.models.UserAccountList;
import MailApplication.models.UserAccount;
import MailApplication.models.UserAccountOfficer;
import MailApplication.models.UserAccountResident;

import java.io.*;

public class UserAccFileDataSource {
    private String fileDirectoryName;
    private String fileName;
    private UserAccountList users;

    public UserAccFileDataSource(String fileDirectoryName, String fileName){
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
            if (data[0].equals("systemAdministrator")) {
                UserAccount systemAdministrator = new UserAccount(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim());
                users.add(systemAdministrator);
            }
            else  if (data[0].equals("officer")){
                UserAccount officerUser = new UserAccountOfficer(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), Integer.parseInt(data[6]));
                users.add(officerUser);
            }
            else if(data[0].equals("resident")){
                UserAccount residentUser = new UserAccountResident(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim());
                users.add(residentUser);
            }
        }
        reader.close();
    }

    public UserAccountList getUsersData(){
        try{
            users = new UserAccountList();
            readData();
        } catch (FileNotFoundException e){
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return users;

    }

    public void setUsersData(UserAccountList users) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (UserAccount userAccount : users.toList()) {
                if (userAccount.getUserType().equals("systemAdministrator")) {
                    String line = userAccount.getUserType() + ","
                            + userAccount.getName() + ","
                            + userAccount.getUsername() + ","
                            + userAccount.getPassword();
                    writer.append(line);
                    writer.newLine();
                } else if (userAccount.getUserType().equals("officer")) {
                    String line = userAccount.getUserType() + ","
                            + userAccount.getName() + ","
                            + userAccount.getUsername() + ","
                            + userAccount.getPassword() + ","
                            + ((UserAccountOfficer)userAccount).getLastTimeLogin()+ ","
                            + ((UserAccountOfficer)userAccount).getStatus() + ","
                            + ((UserAccountOfficer)userAccount).getTimesLoginBfSusp();
                    writer.append(line);
                    writer.newLine();
                } else if (userAccount.getUserType().equals("resident")) {
                    String line = userAccount.getUserType() + ","
                            + userAccount.getName() + ","
                            + userAccount.getUsername() + ","
                            + userAccount.getPassword() + ","
                            + ((UserAccountResident)userAccount).getRoom();
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
