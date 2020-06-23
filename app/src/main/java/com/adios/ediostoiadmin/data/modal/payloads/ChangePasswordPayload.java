package com.adios.ediostoiadmin.data.modal.payloads;

public class ChangePasswordPayload {
    private String userName;
    private String password;
    private String newPassword;
    private float accountID = 1;
    private float siteID = 1;
    private String signatureKey = "1234";

    public ChangePasswordPayload(String userName, String password, String newPassword) {
        this.userName = userName;
        this.password = password;
        this.newPassword = newPassword;
    }
}
