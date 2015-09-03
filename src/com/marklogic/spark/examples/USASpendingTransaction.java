package com.marklogic.spark.examples;


import java.io.Serializable;

/**
 * Created by hpuranik on 7/20/2015.
 */
public class USASpendingTransaction implements Serializable {

    //unique_transaction_id
    private String uniqueTransactionId;

    //obligatedamount
    private double obligatedAmount;

    //Puchaser_ID
    private String purchaserID;

    //Contract_ID
    private String contractID;

    //Vendor_ID
    private String vendorID;

    //Product_ID
    private String productID;

    //PlaceOfPerformance_ID
    private String placeOfPerformanceID;

    public String getUniqueTransactionId(){
        return this.uniqueTransactionId;
    }

    public void setUniqueTransactionId(String uniqueTransactionId) {
        this.uniqueTransactionId = uniqueTransactionId;
    }

    public double getObligatedAmount() {
        return obligatedAmount;
    }

    public void setObligatedAmount(double obligatedAmount) {
        this.obligatedAmount = obligatedAmount;
    }

    public String getPurchaserID() {
        return purchaserID;
    }

    public void setPurchaserID(String purchaserID) {
        this.purchaserID = purchaserID;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getPlaceOfPerformanceID() {
        return placeOfPerformanceID;
    }

    public void setPlaceOfPerformanceID(String placeOfPerformanceID) {
        this.placeOfPerformanceID = placeOfPerformanceID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public void setProperty(String key, String value){
        if(key.equalsIgnoreCase("unique_transaction_id")){
            setUniqueTransactionId(value);
        }else if(key.equalsIgnoreCase("obligatedamount")){
            setObligatedAmount(Double.valueOf(value));
        }else if(key.equalsIgnoreCase("Puchaser_ID")){
            setPurchaserID(value);
        }else if(key.equalsIgnoreCase("Contract_ID")){
            setContractID(value);
        }else if(key.equalsIgnoreCase("Vendor_ID")){
            setVendorID(value);
        }else if(key.equalsIgnoreCase("Product_ID")){
            setProductID(value);
        }else if(key.equalsIgnoreCase("PlaceOfPerformance_ID")){
            setPlaceOfPerformanceID(value);
        }
    }


}
