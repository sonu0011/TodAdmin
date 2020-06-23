package com.adios.ediostoiadmin.data.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CustomerOrder implements Parcelable {
    private String orderDateTime;
    private String orderType;
    private String orderNumber;
    private ArrayList<Item> customerOrderItems;
    private String orderPickupDateTime;
    private float totalPrice;
    private float totalDiscountAmount;
    private float customerOrderID;
    private String orderInstructions;
    private String orderStatus;
    private boolean updateOrder;
    private String paymentType;
    private float deliveryChargesAmount;
    private float totalOrderAmount;
    private String orderStatusDateTime;
    private float promoCodeID;
    private float promoDiscountAmount;
    private float customerUserID;
    private String paymentDateTime;
    private float totalTaxAmount;
    private String paymentTransactionID;
    private String paymentStatus;
    private float orderReadyTimeInmin;
    private Customer customer;

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    protected CustomerOrder(Parcel in) {
        orderDateTime = in.readString();
        orderType = in.readString();
        orderNumber = in.readString();
        customerOrderItems = in.createTypedArrayList(Item.CREATOR);
        orderPickupDateTime = in.readString();
        totalPrice = in.readFloat();
        totalDiscountAmount = in.readFloat();
        customerOrderID = in.readFloat();
        orderInstructions = in.readString();
        orderStatus = in.readString();
        updateOrder = in.readByte() != 0;
        paymentType = in.readString();
        deliveryChargesAmount = in.readFloat();
        totalOrderAmount = in.readFloat();
        orderStatusDateTime = in.readString();
        promoCodeID = in.readFloat();
        promoDiscountAmount = in.readFloat();
        customerUserID = in.readFloat();
        paymentDateTime = in.readString();
        totalTaxAmount = in.readFloat();
        paymentTransactionID = in.readString();
        paymentStatus = in.readString();
        orderReadyTimeInmin = in.readFloat();
        customer = in.readParcelable(Customer.class.getClassLoader());
    }

    public static final Creator<CustomerOrder> CREATOR = new Creator<CustomerOrder>() {
        @Override
        public CustomerOrder createFromParcel(Parcel in) {
            return new CustomerOrder(in);
        }

        @Override
        public CustomerOrder[] newArray(int size) {
            return new CustomerOrder[size];
        }
    };

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public ArrayList<Item> getCustomerOrderItems() {
        return customerOrderItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }


    public String getOrderPickupDateTime() {
        return orderPickupDateTime;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public float getCustomerOrderID() {
        return customerOrderID;
    }

    public String getOrderInstructions() {
        return orderInstructions;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public boolean isUpdateOrder() {
        return updateOrder;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public float getDeliveryChargesAmount() {
        return deliveryChargesAmount;
    }

    public float getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public String getOrderStatusDateTime() {
        return orderStatusDateTime;
    }

    public float getPromoCodeID() {
        return promoCodeID;
    }

    public float getPromoDiscountAmount() {
        return promoDiscountAmount;
    }

    public float getCustomerUserID() {
        return customerUserID;
    }

    public String getPaymentDateTime() {
        return paymentDateTime;
    }

    public float getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public String getPaymentTransactionID() {
        return paymentTransactionID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public float getOrderReadyTimeInmin() {
        return orderReadyTimeInmin;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(orderDateTime);
        dest.writeString(orderType);
        dest.writeString(orderNumber);
        dest.writeTypedList(customerOrderItems);
        dest.writeString(orderPickupDateTime);
        dest.writeFloat(totalPrice);
        dest.writeFloat(totalDiscountAmount);
        dest.writeFloat(customerOrderID);
        dest.writeString(orderInstructions);
        dest.writeString(orderStatus);
        dest.writeByte((byte) (updateOrder ? 1 : 0));
        dest.writeString(paymentType);
        dest.writeFloat(deliveryChargesAmount);
        dest.writeFloat(totalOrderAmount);
        dest.writeString(orderStatusDateTime);
        dest.writeFloat(promoCodeID);
        dest.writeFloat(promoDiscountAmount);
        dest.writeFloat(customerUserID);
        dest.writeString(paymentDateTime);
        dest.writeFloat(totalTaxAmount);
        dest.writeString(paymentTransactionID);
        dest.writeString(paymentStatus);
        dest.writeFloat(orderReadyTimeInmin);
        dest.writeParcelable(customer, flags);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderDateTime='" + orderDateTime + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", customerOrderItems=" + customerOrderItems +
                ", orderPickupDateTime='" + orderPickupDateTime + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalDiscountAmount=" + totalDiscountAmount +
                ", customerOrderID=" + customerOrderID +
                ", orderInstructions='" + orderInstructions + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", updateOrder=" + updateOrder +
                ", paymentType='" + paymentType + '\'' +
                ", deliveryChargesAmount=" + deliveryChargesAmount +
                ", totalOrderAmount=" + totalOrderAmount +
                ", orderStatusDateTime='" + orderStatusDateTime + '\'' +
                ", promoCodeID=" + promoCodeID +
                ", promoDiscountAmount=" + promoDiscountAmount +
                ", customerUserID=" + customerUserID +
                ", paymentDateTime='" + paymentDateTime + '\'' +
                ", totalTaxAmount=" + totalTaxAmount +
                ", paymentTransactionID='" + paymentTransactionID + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orderReadyTimeInmin=" + orderReadyTimeInmin +
                ", customer=" + customer +
                '}';
    }


}
