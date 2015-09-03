package com.marklogic.spark.examples;

import java.io.Serializable;
/**
 * Created by hpuranik on 7/20/2015.
 */
public class USASpendingVendor implements Serializable {

    //vendorID
    private String vendorID;

    //vendorname
    private String vendorName;

    //streetaddress
    private String streetAddress;

    //streetaddress2
    private String streetAddress2;

    //city
    private String city;

    //state
    private String state;

    //zipcode
    private String zipCode;

    //country
    private String country;

    //congressionaldistrict
    private String congressionalDistrict;

    //dunsnumber
    private String dunsNumber;

    //phoneno
    private String phoneNumber;

    //faxno
    private String faxNumber;

    //mod_parent
    private String parentCompany;

    //organizationaltype
    private String organizationType;

    //numberofemployees
    private int numberOfEmployees;

    //annualrevenue
    private double annualRevenue;

    //contractingofficerbusinesssizedetermination
    private String businessSizeClassification;

    //manufacturingorganizationtype
    private String manufacturingOrgType;

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getBusinessSizeClassification() {
        return businessSizeClassification;
    }

    public void setBusinessSizeClassification(String businessSizeClassification) {
        this.businessSizeClassification = businessSizeClassification;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getManufacturingOrgType() {
        return manufacturingOrgType;
    }

    public void setManufacturingOrgType(String manufacturingOrgType) {
        this.manufacturingOrgType = manufacturingOrgType;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(String parentCompany) {
        this.parentCompany = parentCompany;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setProperty(String key, String value){
        if(key.equalsIgnoreCase("vendorID")){
            setVendorID(value);
        }else if(key.equalsIgnoreCase("vendorname")){
            setVendorName(value);
        }else if(key.equalsIgnoreCase("streetaddress")){
            setStreetAddress(value);
        }else if(key.equalsIgnoreCase("streetaddress2")){
            setStreetAddress2(value);
        }else if(key.equalsIgnoreCase("city")){
            setCity(value);
        }else if(key.equalsIgnoreCase("state")){
            setState(value);
        }else if(key.equalsIgnoreCase("zipcode")){
            setZipCode(value);
        }else if(key.equalsIgnoreCase("country")){
            setCountry(value);
        }else if(key.equalsIgnoreCase("congressionaldistrict")){
            setCongressionalDistrict(value);
        }else if(key.equalsIgnoreCase("dunsnumber")){
            setDunsNumber(value);
        }else if(key.equalsIgnoreCase("phoneno")){
            setPhoneNumber(value);
        }else if(key.equalsIgnoreCase("faxno")){
            setFaxNumber(value);
        }else if(key.equalsIgnoreCase("mod_parent")){
            setParentCompany(value);
        }else if(key.equalsIgnoreCase("organizationaltype")){
            setOrganizationType(value);
        }else if(key.equalsIgnoreCase("numberofemployees")){
            setNumberOfEmployees(Integer.valueOf(value));
        }else if(key.equalsIgnoreCase("annualrevenue")){
            setAnnualRevenue(Double.valueOf(value));
        }else if(key.equalsIgnoreCase("contractingofficerbusinesssizedetermination")){
            setBusinessSizeClassification(value);
        }else if(key.equalsIgnoreCase("manufacturingorganizationtype")){
            setManufacturingOrgType(value);
        }
    }

}
