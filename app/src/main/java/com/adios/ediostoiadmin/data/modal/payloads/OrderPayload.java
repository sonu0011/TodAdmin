package com.adios.ediostoiadmin.data.modal.payloads;

public class OrderPayload {
    private String userName;
    private float accountID =1;
    private float siteID =1;
    private String lastUpdatedDate;
    private String signatureKey ="1234";

    public OrderPayload(String userName ,String lastUpdatedDate) {
        this.userName = userName;
        this.lastUpdatedDate =lastUpdatedDate;
    }
}
