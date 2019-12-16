package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Patients implements Serializable{
    //personal information
    private LocalDate Birthday;
    private String Name,Firstname, Middlename,Doctor, OtherDoctor;
    private int Room,Bed,patientid;
    //---------------------------------------------------------------------------------------------------
    //Street information
    private String Country;
    private String Street;
    private String IllnessInformation;
    private int Zip,Housenumber;
    //---------------------------------------------------------------------------------------------------
    //Room information
    private int Floornumber,Departmentnumber,Buildingnumber, roomid;
    private String Roominformation, Buildingname;
    //---------------------------------------------------------------------------------------------------
    //Constructor
    public Patients(){}

    public Patients(int id, String firstname, String lastname,LocalDate birthday,String illnessInformation){
        this.patientid = id;
        this.Firstname = firstname;
        this.Name = lastname;
        this.Birthday = birthday;
        this.IllnessInformation =illnessInformation;

        //----------------------------------------------------------
        //set null

        this.Middlename = "not entered yet";

        this.Room = -1;
        this.Bed = -1;
        this.Doctor = null;
        this.OtherDoctor = null;

        this.Country = null;
        this.Street = null;
        this.Zip = -1;
        this.Housenumber = -1;

        this.Floornumber = -1;
        this.Departmentnumber = -1;
        this.Buildingnumber = -1;
        this.Roominformation = null;

    }

    public Patients(String Name, String firstname,String middlename, String birthday,int Room, int Bed, String Doctor, String OtherDoctor){
        this.Name =Name;
        this.Firstname = firstname;
        this.Middlename = middlename;
        this.Birthday = LocalDate.parse(birthday);
        this.Room = Room;
        this.Bed = Bed;
        this.Doctor = Doctor;
        this.OtherDoctor = OtherDoctor;
        //patientid
        //----------------------------------------------------------
        //set null

        this.IllnessInformation = null;

        this.Country = null;
        this.Street = null;
        this.Zip = -1;
        this.Housenumber = -1;

        this.Floornumber = -1;
        this.Departmentnumber = -1;
        this.Buildingnumber = -1;
        this.Roominformation = null;


    }

    public void setStreetInformation(String country, String street, int zip, int number){
        this.Country = country;
        this.Street = street;
        this.Zip = zip;
        this.Housenumber = number;
    }

    public void setAllRoominformation(int roomnumber, int floornumber, int departmentnumber, int buildingnumber, String roominformation){
        this.Room = roomnumber;
        this.Floornumber = floornumber;
        this.Departmentnumber = departmentnumber;
        this.Buildingnumber = buildingnumber;
        this.Roominformation = roominformation;
    }

    public String getIllnessInformation() {
        return IllnessInformation;
    }

    public void setIllnessInformation(String illnessInformation) {
        IllnessInformation = illnessInformation;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public void setMiddlename(String middlename) {
        Middlename = middlename;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.Birthday = birthday;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        Zip = zip;
    }

    public int getHousenumber() {
        return Housenumber;
    }

    public void setHousenumber(int housenumber) {
        Housenumber = housenumber;
    }

    public int getFloornumber() {
        return Floornumber;
    }

    public void setFloornumber(int floornumber) {
        Floornumber = floornumber;
    }

    public int getDepartmentnumber() {
        return Departmentnumber;
    }

    public void setDepartmentnumber(int departmentnumber) {
        Departmentnumber = departmentnumber;
    }

    public int getBuildingnumber() {
        return Buildingnumber;
    }

    public void setBuildingnumber(int buildingnumber) {
        Buildingnumber = buildingnumber;
    }

    public String getRoominformation() {
        return Roominformation;
    }

    public void setRoominformation(String roominformation) {
        Roominformation = roominformation;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public void setOtherDoctor(String otherDoctor) {
        OtherDoctor = otherDoctor;
    }

    public void setRoom(int room) {
        Room = room;
    }

    public void setBed(int bed) {
        Bed = bed;
    }

    public String getOtherDoctor() {
        return OtherDoctor;
    }

    public int getRoom() {
        return Room;
    }

    public int getBed() {
        return Bed;
    }

    public String getName() {
        return Name;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getBuildingname() {
        return this.Buildingname;
    }

    public void setBuildingname(String dfd) {
        this.Buildingname = dfd;
    }
}
