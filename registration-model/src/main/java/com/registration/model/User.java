package com.registration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema="user")
public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long telNum;
    private String gender;
    private Address address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name", length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "tel_num", length = 45)
    public Long getTelNum() {
        return telNum;
    }

    public void setTelNum(Long telNum) {
        this.telNum = telNum;
    }

    @Column(name = "gender", length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString(){
        return new StringBuffer()
                .append("firstName = " + firstName + "\n")
                .append("lastName = " + lastName + "\n")
                .append("email = " + email + "\n")
                .append("telNum = " + telNum + "\n")
                .append("gender = " + gender + "\n")
                .append("address = " + address).toString();
    }
}
