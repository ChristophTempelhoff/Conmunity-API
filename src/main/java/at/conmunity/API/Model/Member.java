package at.conmunity.API.Model;

import at.conmunity.API.Enums.EGender;
import at.conmunity.API.Service.ObjectJSONService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Member {
    @JsonProperty("memberID")
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long MemberID;
    @JsonProperty("lastname")
    private String Lastname;
    @JsonProperty("preferredFirstname")
    private String PreferredFirstname;
    @JsonProperty("actualFirstname")
    private String ActualFirstname;
    @JsonProperty("birthday")
    private Date Birthday;
    @JsonProperty("gender")
    private EGender Gender;
    @JsonProperty("lastnameGuardian")
    private String LastnameGuardian = null;
    @JsonProperty("firstnameGuardian")
    private String FirstnameGuardian = null;
    @JsonProperty("contactID")
    private long ContactID;
    @JsonProperty("password")
    private String Password;

    protected Member(){
        this.MemberID = 0;
        this.Lastname = "Ipsum";
        this.PreferredFirstname = "Lora";
        this.ActualFirstname = "Lorem";
        this.Birthday = new Date();
        this.Gender = EGender.FEMALE;
        this.LastnameGuardian = "Ipsum";
        this.FirstnameGuardian = "Torem";
        this.ContactID = 0;
        this.Password = "Test";
    }

    public Member(String lastname, String preferredFirstname, String actualFirstname, Date birthday, EGender gender, String lastnameGuardian, String firstnameGuardian, long contactID, String password){
        this(lastname, preferredFirstname, actualFirstname, birthday, gender, contactID, password);
        this.LastnameGuardian = lastnameGuardian;
        this.FirstnameGuardian = firstnameGuardian;
    }

    public Member(String lastname, String preferredFirstname, String actualFirstname, Date birthday, EGender gender, long contactID, String password){
        this.Lastname = lastname;
        this.PreferredFirstname = preferredFirstname;
        this.ActualFirstname = actualFirstname;
        this.Birthday = birthday;
        this.Gender = gender;
        this.ContactID = contactID;
        this.Password = password;
    }

    public Long getMemberID() {
        return MemberID;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getPreferredFirstname() {
        return PreferredFirstname;
    }

    public String getActualFirstname() {
        return ActualFirstname;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public EGender getGender() {
        return Gender;
    }

    public String getLastnameGuardian() {
        return LastnameGuardian;
    }

    public String getFirstnameGuardian() {
        return FirstnameGuardian;
    }

    public long getContactID() {
        return ContactID;
    }

    public String getPassword() {
        return Password;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setPreferredFirstname(String preferredFirstname) {
        PreferredFirstname = preferredFirstname;
    }

    public void setActualFirstname(String actualFirstname) {
        ActualFirstname = actualFirstname;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public void setGender(EGender gender) {
        Gender = gender;
    }

    public void setLastnameGuardian(String lastnameGuardian) {
        LastnameGuardian = lastnameGuardian;
    }

    public void setFirstnameGuardian(String firstnameGuardian) {
        FirstnameGuardian = firstnameGuardian;
    }

    public void setContactID(long contactID) {
        ContactID = contactID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.MemberID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        Member other = (Member) obj;

        return this.getMemberID().equals(other.getMemberID());
    }

    @Override
    public String toString() {
        return "User: [UserUUID=" + this.MemberID +", Lastname=" + this.Lastname + ", Actual Firstname=" + this.ActualFirstname + ", preferred Firstname=" + this.PreferredFirstname + ", ContactUUID=" + this.ContactID + "]";
    }

    public String toJSON() {
        ObjectJSONService<Member> objectJSONService = new ObjectJSONService<>();
        return objectJSONService.ObjectToJson(this);
    }

    public Member doMap(Member other) {
        this.setLastname(other.getLastname());
        this.setActualFirstname(other.getActualFirstname());
        this.setPreferredFirstname(other.getPreferredFirstname());
        this.setBirthday(other.getBirthday());
        this.setGender(other.getGender());
        this.setLastnameGuardian(other.getLastnameGuardian());
        this.setFirstnameGuardian(other.getFirstnameGuardian());
        this.setContactID(other.getContactID());
        this.setPassword(other.getPassword());

        return this;
    }
}
