package com.adios.ediostoiadmin.data.modal.payloads;

import com.adios.ediostoiadmin.data.modal.CustomerOrder;

public class UpdateOrderPayload {
    private String userName;
    private float accountID =1;
    private float siteID =1;
    private String appEnd ="Admin";
    private String signatureKey ="1234";
    private float deliveryPartnerID= 1;
    private CustomerOrder OrderToBeUpdatedObject;

    public UpdateOrderPayload(String userName, CustomerOrder orderToBeUpdatedObject) {
        this.userName = userName;
        OrderToBeUpdatedObject = orderToBeUpdatedObject;
    }
}
