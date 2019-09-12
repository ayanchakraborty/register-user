package com.registration.dto;

public class UserBO {
    private String firstName;
    private String lastName;
    private String email;
    private Long telephone;
    private String gender;
    private AddressBO address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telphone) {
        this.telephone = telphone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public AddressBO getAddress() {
        return address;
    }

    public void setAddress(AddressBO address) {
        this.address = address;
    }
}
