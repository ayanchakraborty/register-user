package com.registration.dto;

import com.registration.util.UserChecks;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;

@GroupSequence({UserBO.class, UserChecks.class})
public class UserBO {
    @NotBlank(message = "firstName can't be left blank!")
    @Size(min=2, max=45, message = "firstName can't be more than 45 characters", groups = UserChecks.class)
    private String firstName;
    @NotBlank(message = "lastName can't be left blank!")
    @Size(min=2, max=45, message = "lastName can't be more than 45 characters", groups = UserChecks.class)
    private String lastName;
    @NotBlank(message = "email can't be left blank!")
    @Size(min=2, max=45, message = "email can't be more than 45 characters", groups = UserChecks.class)
    @Email(message = "Email should be valid", groups = UserChecks.class)
    private String email;
    @Digits(integer =10, fraction =0, message = "telephone can't be more than 10 digits", groups = UserChecks.class)
    @Positive(message = "telephone should be positive numbers only")
    private Long telephone;
    @NotBlank(message = "gender can't be left blank!")
    @Size(min=1, max=1, message = "gender can be either M / F", groups = UserChecks.class)
    private String gender;
    @NotNull(message = "address can't be null!")
    @Valid
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
