package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {


    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private  String firstname;

    @Expose
    @Column(name = "lastname")
    private  String lastname;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private  String email;
    @Type(type = "text")
    private  String email2;
    @Type(type = "text")
    private  String email3;

    @Transient
    private String group;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name = "address")
    @Type(type = "text")
    private String Address;

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String allDetails;

   /* @Column(name = "photo")
    @Type(type = "text")
    private String photo;*/


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getAllDetails() {
        return allDetails;
    }
    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }

  /*  public File getPhoto() {
        return new File(photo);
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }*/




    public String getAllPhones() {
        return allPhones;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAddress() {
        return Address;
    }
    public ContactData withAddress(String address) {
        Address = address;
        return this;
    }

    public int getId() {
        return id;
    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public String getGroup() {
        return group;
    }
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }


    public String getMobilePhone(){
        return mobilePhone;
    }
    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }
    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }


    public String getHomePhone() {
        return homePhone;
    }
    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (Address != null ? !Address.equals(that.Address) : that.Address != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
        return allDetails != null ? allDetails.equals(that.allDetails) : that.allDetails == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (Address != null ? Address.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        result = 31 * result + (allDetails != null ? allDetails.hashCode() : 0);
        return result;
    }
}
