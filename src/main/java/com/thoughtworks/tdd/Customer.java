package com.thoughtworks.tdd;

public class Customer {
    private  String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String queryErrorMessage() {
        return getErrorMessage();
    }
}
