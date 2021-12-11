#MAIL ROOM
[ ผู้จัดทำ: 6210407960 นาย ธนกร วงษ์สนิท ] 

> MAIL ROOM เป็น Desktop Application สำหรับการบันทึกการรับ/ส่งจดหมาย เอกสาร หรือพัสดุ 
สำหรับผู้พักอาศัยและเจ้าหน้าที่ส่วนกลางที่ดูแลห้องพักอาศัยในคอนโด ด้วย JavaFX (JavaSE 8u251)

###การพัฒนาโปรแกรมในแต่ละสัปดาห์
* สัปดาห์ที่หนึ่ง "add maven to project" เพิ่ม Maven Framework ลงในโปรเจค และ copy file .gitignore ใส่ไว้ในโปรเจคด้วย
    
* สัปดาห์ที่สอง "Lab assignment final2" ทำหน้าแรกของโปรแกรม เพิ่มรูปภาพที่อาจมีการใช้ลงใน folder "pictures" 
[/Users/thanakornwongsanit/Documents/Java/LabJava/javaProject/src/main/resources/pictures]
    
* สัปดาห์ที่สาม "Lab assignment final 3 (Polymorphism)"
    สร้างคลาส จดหมาย(letter) เอกสาร(ParcelPost) และ พัสดุ(MatterMail) ให้มีPolymorphism
    
* สัปดาห์ที่สี่ "Lab Assignment Final 4 (Read-Write File)"
    สร้าง Package "services" โดยมีคลาสภายในซึ่งช่วยในการอ่าน-เขียนไฟล์ของโปรแกรม
* สัปดาห์ที่ห้า "Lab Assignment Final 5"
    แก้ไข ไฟล์.fxml เปลี่ยนรูปแบบการจัดวาง เพิ่มหน้าของโปรแกรม เปลี่ยนรูปภาพ จัดการไฟล์ส่วนที่ไม่ได้ใช้งาน และแก้ไขไฟล์เพิ่มเติมในส่วนที่ยังไม่สมบูรณ์
* สัปดาห์ท่ีหก "Project" เพิ่มไฟล์ต่างๆ และคลาสต่างๆ เช่น เพิ่มไฟล์.csv เพิ่มคลาสการทำงานภายในโปรแกรม เพิ่มข้อมูลในไฟล์.csvให้มีข้อมูลมากขึ้น มีการแก้ไขusername password ของแต่ละบัญชีผูใช้ให้ทดลองโปรแกรมได้สะดวกมากขึ้น
* สัปดาห์ที่เจ็ด "ส่งงานแก้ไขโปรเจครอบที่ 2 วิชา 01418211" แก้ไขหน้าแรกของโปรแกรมให้สามารถสลับหน้าได้โดยการเลื่อนจากซ้ายไปขวา หรือขวาไปซ้าย เช่น หากกดปุ่ม sign up หน้าต่างจะเลื่อนไปทางซ้ายและมีการเปลี่ยนรูปแบบเป็นหน้าสร้างบัญชีผู้ใช้สำหรับผู้เข้าพักอาศัย มีการแก้ไขไฟล์ส่วนที่ไม่ได้ใช้งาน ลบรูปภาพที่ไม่ได้ใช้ แก้ไขjar-file แก้ไขไฟล์readmeทั้งหมด และแก้ไขไฟล์pdfทั้งหมด สร้างfolderเก็บไฟล์รหัสนิสิต.jar จัดfolderเพิ่มเติมให้อยู่ในรูปแบบที่อ่านง่ายขึ้น
ย้ายไฟล์แต่ละประเภทลงใน packageต่างๆ ได้แก่ controllers,models,services
* สัปดาห์ที่เจ็ด(แก้ไขเพิ่มเติม) "ส่งงานแก้ไขโปรเจครอบที่ 3 วิชา 01418211" แก้ไขการเพิ่มบัญชีผู้ใช้เจ้าหน้าที่ส่วนกลาง ให้อัปเดตตารางทันทีหลังมีการเพิ่มบัญชีผู้ใช้

###วิธีการติดตั้งโปรแกรม
กดสองครั้งที่ไฟล์.jar "6210407960.jar" ใน folder ชื่อ"6210407960" หรือ เปิด Terminal(Mac) และเข้าไปที่ Directory ที่มีไฟล์.jarอยู่
จากนั้นพิมพ์  " java -jar 6210407960.jar "


###การวางโครงสร้างไฟล์
* MailApplication ประกอบด้วย
  * controller -- เก็บคลาสควบคุมการทำงานของ fxml ที่เชื่อมกับ GUI
  * model -- เก็บคลาสบัญชีผู้ใช้ คลาสจดหมาย-พัสดุ-สิ่งของ คลาสการจัดการต่างๆภายในโปรแกรม
  * service -- เก็บคลาสการจัดการข้อมูลของโปรแกรม
* resources เก็บไฟล์.fxml และfolderต่างๆ ประกอบด้วย
  * libraries -- เก็บไฟล์.psdที่ใช้ตกแต่งภาพภายในโปรแกรม , ไฟล์pdfแนะนำการใช้งาน Maven ในโปรแกรม IntelliJ IDEA ,ไฟล์คำสั่งโปรเจครายวิชา 01418211 Software Construction
  * pictures -- เก็บไฟล์รูปภาพที่ถูกใช้ภายในโปรแกรม
* data เก็บไฟล์ CSV ซึ่งได้แก่
   1. accountOfficer.csv -> เก็บข้อมูลเจ้าหน้าที่ส่วนกลาง(Officer)
      * ตัวอย่างข้อมูล: officer,Adam,Adam_officer,ad5v,2019/01/22 04:00,NORMAL,0
      > รูปแบบข้อมูลในแต่ละบรรทัด _{ประเภทของผู้ใช้,ชื่อ,Username,Password,วันเวลาเข้าใช้ครั้งล่าสุด,สถานะ,จำนวนครั้งการพยายามเข้าใช้หลังถูกระงับสิทธิ์}_
   2. accountResident.csv -> เก็บข้อมูลผู้เข้าพัก(Resident)
      * ตัวอย่างข้อมูล: resident,Thanakorn,Korn__resident,kkorn,A102 
      > รูปแบบข้อมูลในแต่ละบรรทัด _{ประเภทของผู้ใช้,ชื่อ,Username,Password,หมายเลขห้อง}_
   3. accountSystemAdministrator.csv -> เก็บข้อมูลผู้ดูแลระบบ
      * ตัวอย่างข้อมูล: systemAdministrator,admin Name,Admin username,abcd1234
      > รูปแบบข้อมูลในแต่ละบรรทัด _{ประเภทของผู้ใช้,ชื่อ,Username,Password}_    
   4. things.csv -> เก็บข้อมูลเกี่ยวกับ จดหมาย/เอกสาร/พัสดุ ทั้งที่ผู้เข้าพักรับไปแล้วและยังไม่รับ
      * ตัวอย่างข้อมูล:  
           Letter,received,Adis,2019/04/01 16:30,Beel_officer,A101,Hash,2019/04/03 16:00,11*11.25 cm.
         
           ParcelPost,notReceivedYet,Cven,2019/06/27 11:30,Pess_officer,B401,-,-,10*15 *5 cm.,Kerry,003-gh35
         
           MatterMail,received,Jnis,2020/11/01 11:11,Stin_officer,A810,Peny,2020/11/10 12:31,22.9*30.5 cm.,urgent
      > รูปแบบข้อมูลในแต่ละบรรทัด _{ประเภทของ,สถานะ,ชื่อผู้ส่ง,วันเวลาส่ง,ชื่อเจ้าหน้าที่ที่รับของ,หมายเลขห้อง,ชื่อผู้เข้าพักที่มารับของ,วันเวลาที่ผู้เข้าพักมารับของ,ขนาด}_
        >>หากเป็นเอกสารจะมีข้อมุลด้านหลังเพิ่ม คือ _{...,ระดับความสำคัญของเอกสาร}_                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        และหากเป็นพัสดุจะมีข้อมุลด้านหลังเพิ่ม คือ _{...,ชื่อบริการส่งพัสดุ,Tracking number}_
                                                                                                      
###ตัวอย่างบัญชีผู้ใช้
| ประเภทข้อมูล                | Username           | Password   |
| ------------------------- |:------------------:| ---------: |
| - ผู้ดูแลระบบ 
(System Administrator)      | SA                 |   0000 |
| - เจ้าหน้าที่ส่วนกลาง 
(Officer)                   | OF                 |   0000 |
| - เจ้าหน้าที่ส่วนกลางที่ถูกระงับสิทธิ์ 
(Officer suspended)         | OFsp               |   0000 |
| - ผู้เข้าพัก 
(Resident)                  | RS                 |   0000 |

###การใช้งานเบื่องต้น 
เมื่อเข้าสู่โปรแกรม สามารถเข้าถึงหน้าอื่นโดยการกดปุ่มต่างๆ ได้แก่
1. Sign Up -> เข้าสู่หน้าลงทะเบียนบัญชีผู้ใช้ที่เป็นผู้เข้าพัก
2. About -> เข้าสู่หน้าคำอธิบายโปรแกรม และคำแนะนำต่างๆ
3. Developer -> เข้าสู่หน้าข้อมูลเกี่ยวกับผู้จัดทำโปรแกรม

หรือสามารถ Sign In เพื่อเข้าสู่หน้าอื่นๆของโปรแกรมได้ โดยแบ่งการเข้าใช้งานเป็น 3 รูปแบบ ได้แก่ 
1. System Administrator คือ ผู้ดูแลระบบ สามารถเพิ่มบัญชีผู้ใช้ของเจ้าหน้าที่ส่วนกลางได้ สามารถเปลี่ยนสถานะของเจ้าหน้าที่ส่วนกลางได้ เช่น ระงับการเข้าใช้งานระบบ หรือคืนสิทธิ์การเข้าระบบ 
และผู้ดูแลระบบสามารถดูการเข้าระบบของเจ้าหน้าที่ส่วนกลางได้
2. Officer คือ เจ้าหน้าที่ส่วนกลาง สามารถจัดการกับข้อมูลห้อง ผู้เข้าพัก และจัดการกับจดหมาย/เอกสาร/พัสดุต่างๆ
3. Resident คือ ผู้เข้าพัก สามารถดูจดหมายที่รับมาแล้วหรืออยู่กับเจ้าหน้าที่ส่วนกลางได้ และสามารถเปลี่ยนรหัสการเข้าโปรแกรมได้