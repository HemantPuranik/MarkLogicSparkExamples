package com.marklogic.spark.examples;

import java.io.Serializable;
/**
 * Created by hpuranik on 7/20/2015.
 */
public class USASpendingPlaceOfPerformance implements Serializable{

    //PoPID
    private String placeOfPerformanceID;
    //statecode
    private String stateCode;
    //city
    private String city;
    //country
    private String country;
    //zipcode
    private String zipCode;
    //congressionaldistrict
    private String congressionalDistrict;

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

    public String getPlaceOfPerformanceID() {
        return placeOfPerformanceID;
    }

    public void setPlaceOfPerformanceID(String placeOfPerformanceID) {
        this.placeOfPerformanceID = placeOfPerformanceID;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setProperty(String key, String value){
        if(key.equalsIgnoreCase("PoPID")){
            setPlaceOfPerformanceID(value);
        } else if(key.equalsIgnoreCase("statecode")){
            setStateCode(value);
        }else if(key.equalsIgnoreCase("city")){
            setCity(value);
        }else if(key.equalsIgnoreCase("country")){
            setCountry(value);
        }else if(key.equalsIgnoreCase("zipcode")){
            setZipCode(value);
        }else if(key.equalsIgnoreCase("congressionaldistrict")){
            setCongressionalDistrict(value);
        }
    }

}
