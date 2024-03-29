package com.registration.dto;

import com.registration.util.AddressChecks;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@GroupSequence({AddressBO.class, AddressChecks.class})
public class AddressBO {
    @NotBlank(message = "addressLine1 can't be left blank!")
    @Size(min=2, max=45, message = "addressLine1 can't be more than 45 characters", groups = AddressChecks.class)
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    @NotBlank(message = "country can't be left blank!")
    @Size(min=2, max=45, message = "country can't be more than 45 characters", groups = AddressChecks.class)
    private String country;
    @NotBlank(message = "zipcode can't be left blank!")
    @Size(min=2, max=45, message = "zipcode can't be more than 45 characters", groups = AddressChecks.class)
    private String zipcode;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
