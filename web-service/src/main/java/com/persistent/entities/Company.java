package com.persistent.entities;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "company", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companyId;

    @NotNull
    @Size(max = 150)
    private String name;

    @Size(max = 100)
    private String address;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String country;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    @Size(min = 5, max = 15)
    private String phoneNumber;

    @OneToMany(mappedBy="company", cascade = CascadeType.ALL)
    private List<BeneficialOwner> beneficialOwner;


    public Company(){
    }


    public Company(Company company) {
        this(company.getName(), company.getAddress(), company.getCity(),
                company.getCountry(), company.getEmail(), company.getPhoneNumber(),
                company.getBeneficialOwner());
    }


    public Company(String name, String address, String city, String country, String email,
                   String phoneNumber, List<BeneficialOwner> beneficialOwner) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.beneficialOwner = beneficialOwner;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<BeneficialOwner> getBeneficialOwner() {
        return beneficialOwner;
    }

    public void setBeneficialOwner(List<BeneficialOwner> beneficialOwner) {
        this.beneficialOwner = beneficialOwner;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", beneficialOwner=" + beneficialOwner +
                '}';
    }
}
