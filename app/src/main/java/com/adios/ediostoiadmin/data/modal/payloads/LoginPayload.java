package com.adios.ediostoiadmin.data.modal.payloads;

public class LoginPayload {
    private String signatureKey = "1234";
    private int siteID = 1;
    private int accountID = 1;
    private String userName;
    private String userPassword;

    public LoginPayload(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
