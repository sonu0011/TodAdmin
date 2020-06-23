package com.adios.ediostoiadmin.data.modal;

public class AdminSettings {
    private String userName;
    private float accountID =1;
    private float siteID =1;
    private String signatureKey ="1234";
    private String readyTimeInMin;
    private String euAppShutdown;
    private String deliveryChargesType;
    private String orderType;
    private String deliveryChargesAmount;
    private String deliveryRadius;
    private String deliveryTime;


    public AdminSettings(String userName, String readyTimeInMin, String euAppShutdown, String deliveryChargesType, String orderType, String deliveryChargesAmount, String deliveryRadius, String deliveryTime) {
        this.userName = userName;
        this.readyTimeInMin = readyTimeInMin;
        this.euAppShutdown = euAppShutdown;
        this.deliveryChargesType = deliveryChargesType;
        this.orderType = orderType;
        this.deliveryChargesAmount = deliveryChargesAmount;
        this.deliveryRadius = deliveryRadius;
        this.deliveryTime = deliveryTime;
    }

    public String getUserName() {
        return userName;
    }

    public float getAccountID() {
        return accountID;
    }

    public float getSiteID() {
        return siteID;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    public String getReadyTimeInMin() {
        return readyTimeInMin;
    }

    public String getEuAppShutdown() {
        return euAppShutdown;
    }

    public String getDeliveryChargesType() {
        return deliveryChargesType;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getDeliveryChargesAmount() {
        return deliveryChargesAmount;
    }

    public String getDeliveryRadius() {
        return deliveryRadius;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    @Override
    public String toString() {
        return "AdminSettings{" +
                "userName='" + userName + '\'' +
                ", accountID=" + accountID +
                ", siteID=" + siteID +
                ", signatureKey='" + signatureKey + '\'' +
                ", readyTimeInMin='" + readyTimeInMin + '\'' +
                ", euAppShutdown='" + euAppShutdown + '\'' +
                ", deliveryChargesType='" + deliveryChargesType + '\'' +
                ", orderType='" + orderType + '\'' +
                ", deliveryChargesAmount='" + deliveryChargesAmount + '\'' +
                ", deliveryRadius='" + deliveryRadius + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                '}';
    }
}

