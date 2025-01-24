package at.conmunity.API.Model;

import at.conmunity.API.Enums.EGender;
import at.conmunity.API.Enums.EMemberType;
import at.conmunity.API.Service.CryptographyService;
import at.conmunity.API.Service.ObjectJSONService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class Member {
    @JsonProperty("memberID")
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long MemberID;
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
    @JsonProperty("street")
    private String Street;
    @JsonProperty("postalCode")
    private String PostalCode;
    @JsonProperty("city")
    private String City;
    @JsonProperty("telephone")
    private String Telephone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("student")
    private boolean Student;
    @JsonProperty("wantsNewsletter")
    private boolean WantsNewsletter;
    @JsonProperty("memberType")
    private EMemberType MemberType;
    @JsonProperty("password")
    private String Password;

    protected Member(){}

    public Member(String lastname, String preferredFirstname, String actualFirstname, Date birthday, EGender gender, String lastnameGuardian, String firstnameGuardian, String street, String postalCode, String city, String telephone, String email, boolean isStudent, boolean wantsNewsletter, EMemberType memberType, String password){
        this(lastname, preferredFirstname, actualFirstname, birthday, gender, street, postalCode, city, telephone, email, isStudent, wantsNewsletter, memberType, password);
        this.LastnameGuardian = lastnameGuardian;
        this.FirstnameGuardian = firstnameGuardian;
    }

    public Member(String lastname, String preferredFirstname, String actualFirstname, Date birthday, EGender gender, String street, String postalCode, String city, String telephone, String email, boolean isStudent, boolean wantsNewsletter, EMemberType memberType, String password){
        this.Lastname = lastname;
        this.PreferredFirstname = preferredFirstname;
        this.ActualFirstname = actualFirstname;
        this.Birthday = birthday;
        this.Gender = gender;
        this.Street = street;
        this.PostalCode = postalCode;
        this.City = city;
        this.Telephone = telephone;
        this.email = email;
        this.Student = isStudent;
        this.WantsNewsletter = wantsNewsletter;
        this.MemberType = memberType;
        this.Password = CryptographyService.CreateHash(password);
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

    public String getStreet() {
        return Street;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public String getCity() {
        return City;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isStudent() {
        return Student;
    }

    public boolean isWantsNewsletter() {
        return WantsNewsletter;
    }

    public EMemberType getMemberType() {
        return MemberType;
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

    public void setStreet(String street) {
        Street = street;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudent(boolean student) {
        Student = student;
    }

    public void setWantsNewsletter(boolean wantsNewsletter) {
        WantsNewsletter = wantsNewsletter;
    }

    public void setMemberType(EMemberType memberType) {
        MemberType = memberType;
    }

    public void setPassword(String password) {
        Password = CryptographyService.CreateHash(password);
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
        return "User: [UserUUID=" + this.MemberID +", Lastname=" + this.Lastname + ", Actual Firstname=" + this.ActualFirstname + ", preferred Firstname=" + this.PreferredFirstname + ", ContactUUID=" + this.Street + "]";
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
        this.setStreet(other.getStreet());
        this.setPostalCode(other.getPostalCode());
        this.setCity(other.getCity());
        this.setTelephone(other.getTelephone());
        this.setEmail(other.getEmail());
        this.setMemberType(other.getMemberType());
        this.setStudent(other.isStudent());
        this.setWantsNewsletter(other.isWantsNewsletter());
        this.setPassword(other.getPassword());

        return this;
    }
}
