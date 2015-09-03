package com.marklogic.spark.examples;

import java.io.Serializable;

/**
 * Created by hpuranik on 9/2/2015.
 */
public class ConsumerComplaint implements Serializable {

    private String complaintID;
    private String product;
    private String subProduct;
    private String issue;
    private String subIssue;
    private String state;
    private String zip;
    private String submittedVia;
    private String dateReceived;
    private String dateSentToCompany;
    private String company;
    private String companyResponse;
    private String timelyResponse;
    private String consumerDisputed;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyResponse() {
        return companyResponse;
    }

    public void setCompanyResponse(String companyResponse) {
        this.companyResponse = companyResponse;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getConsumerDisputed() {
        return consumerDisputed;
    }


    public void setConsumerDisputed(String consumerDisputed) {
        this.consumerDisputed = consumerDisputed;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDateSentToCompany() {
        return dateSentToCompany;
    }

    public void setDateSentToCompany(String dateSentToCompany) {
        this.dateSentToCompany = dateSentToCompany;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubIssue() {
        return subIssue;
    }

    public void setSubIssue(String subIssue) {
        this.subIssue = subIssue;
    }

    public String getSubmittedVia() {
        return submittedVia;
    }

    public void setSubmittedVia(String submittedVia) {
        this.submittedVia = submittedVia;
    }

    public String getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(String subProduct) {
        this.subProduct = subProduct;
    }

    public String getTimelyResponse() {
        return timelyResponse;
    }

    public void setTimelyResponse(String timelyResponse) {
        this.timelyResponse = timelyResponse;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setProperty(String key, String value){
        if(key.equalsIgnoreCase("company")){
            setCompany(value);
        }else if(key.equalsIgnoreCase("Company_response")){
            setCompanyResponse(value);
        }else if(key.equalsIgnoreCase("Complaint_ID")){
            setComplaintID(value);
        }else if(key.equalsIgnoreCase("Consumer_disputed_")){
            setConsumerDisputed(value);
        }else if(key.equalsIgnoreCase("Date_received")){
            setDateReceived(value);
        }else if(key.equalsIgnoreCase("Date_sent_to_company")){
            setDateSentToCompany(value);
        }else if(key.equalsIgnoreCase("issue")){
            setIssue(value);
        }else if(key.equalsIgnoreCase("product")){
            setProduct(value);
        }else if(key.equalsIgnoreCase("state")){
            setState(value);
        }else if(key.equalsIgnoreCase("sub-issue")){
            setSubIssue(value);
        }else if(key.equalsIgnoreCase("Submitted_via")){
            setSubmittedVia(value);
        }else if(key.equalsIgnoreCase("sub-product")){
            setSubProduct(value);
        }else if(key.equalsIgnoreCase("Timely_response_")){
            setTimelyResponse(value);
        }else if(key.equalsIgnoreCase("ZIP_code")){
            setZip(value);
        }
    }

}
