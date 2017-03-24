package ru.stqa.pft.addressbook.model;

import java.io.File;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String lastname;
    private  String email;
    private  String email2;
    private  String email3;
    private String group;
    private String homePhone;
    private String workPhone;
    private String mobilePhone;
    private String Address;
    private String allPhones;
    private String allEmails;
    private String allDetails;


    public String getAllDetails() {
        return allDetails;
    }
    public ContactData withAllDetails(String allDetails) {
        this.allDetails = allDetails;
        return this;
    }

    public File getPhoto() {
        return photo;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;


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
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }



}
