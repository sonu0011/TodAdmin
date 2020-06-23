package com.adios.ediostoiadmin.data.modal.payloads;

public class DeliveryPartnerPayload {
    private String userName;
    private float accountID =1;
    private float siteID =1;
    private String lastUpdatedDate ="2019-10-18 10:00:00";
    private String signatureKey ="1234";

    public DeliveryPartnerPayload(String userName) {
        this.userName = userName;
    }
}
