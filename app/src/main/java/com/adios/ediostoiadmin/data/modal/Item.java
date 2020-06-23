package com.adios.ediostoiadmin.data.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private float itemID;
    private String itemName;
    private float itemDiscountAmount;
    private String itemInstructions;
    private String itemType;
    private float itemQuantity;
    private float itemTotalAmount;
    private float orderItemID;
    private float customerOrderID;
    private float itemPrice;
    private float itemTaxAmount;
    private String itemSpicyLevel;

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", itemDiscountAmount=" + itemDiscountAmount +
                ", itemInstructions='" + itemInstructions + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemTotalAmount=" + itemTotalAmount +
                ", orderItemID=" + orderItemID +
                ", customerOrderID=" + customerOrderID +
                ", itemPrice=" + itemPrice +
                ", itemTaxAmount=" + itemTaxAmount +
                ", itemSpicyLevel='" + itemSpicyLevel + '\'' +
                '}';
    }

    protected Item(Parcel in) {
        itemID = in.readFloat();
        itemName = in.readString();
        itemDiscountAmount = in.readFloat();
        itemInstructions = in.readString();
        itemType = in.readString();
        itemQuantity = in.readFloat();
        itemTotalAmount = in.readFloat();
        orderItemID = in.readFloat();
        customerOrderID = in.readFloat();
        itemPrice = in.readFloat();
        itemTaxAmount = in.readFloat();
        itemSpicyLevel = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public float getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public float getItemDiscountAmount() {
        return itemDiscountAmount;
    }

    public String getItemInstructions() {
        return itemInstructions;
    }

    public String getItemType() {
        return itemType;
    }

    public float getItemQuantity() {
        return itemQuantity;
    }

    public float getItemTotalAmount() {
        return itemTotalAmount;
    }

    public float getOrderItemID() {
        return orderItemID;
    }

    public float getCustomerOrderID() {
        return customerOrderID;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public float getItemTaxAmount() {
        return itemTaxAmount;
    }

    public String getItemSpicyLevel() {
        return itemSpicyLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(itemID);
        dest.writeString(itemName);
        dest.writeFloat(itemDiscountAmount);
        dest.writeString(itemInstructions);
        dest.writeString(itemType);
        dest.writeFloat(itemQuantity);
        dest.writeFloat(itemTotalAmount);
        dest.writeFloat(orderItemID);
        dest.writeFloat(customerOrderID);
        dest.writeFloat(itemPrice);
        dest.writeFloat(itemTaxAmount);
        dest.writeString(itemSpicyLevel);
    }
}
