package com.registration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address", schema="user")
public class Address implements Serializable {
    private Long id;
    private String addrLine1;
    private String addrLine2;
    private String city;
    private String province;
    private String country;
    private String zipcode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "addr_line_1", length = 45)
    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    @Column(name = "addr_line_2", length = 45)
    public String getAddrLine2() {
        return addrLine2;
    }

    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    @Column(name = "city", length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "province", length = 45)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "country", nullable = false, length = 45)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "zip", nullable = false, length = 45)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String toString(){
        return new StringBuffer()
                .append("addressLine1 = " + addrLine1 + "\n")
                .append("addressLine2 = " + addrLine2 + "\n")
                .append("city = " + city + "\n")
                .append("province = " + province + "\n")
                .append("country = " + country + "\n")
                .append("zip = " + zipcode + "\n")
                .toString();
    }
}
