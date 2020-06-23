package com.adios.ediostoiadmin.data.modal.payloads;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchOrderPayload implements Parcelable {
    private String userName;
    private String customerName;
    private String mobileNo;
    private String eMailAddress;
    private String orderNo;
    private float accountID =1;
    private float siteID =1;
    private String signatureKey ="1234";

    public SearchOrderPayload(String userName, String customerName, String mobileNo, String eMailAddress, String orderNo) {
        this.userName = userName;
        this.customerName = customerName;
        this.mobileNo = mobileNo;
        this.eMailAddress = eMailAddress;
        this.orderNo = orderNo;
    }

    protected SearchOrderPayload(Parcel in) {
        userName = in.readString();
        customerName = in.readString();
        mobileNo = in.readString();
        eMailAddress = in.readString();
        orderNo = in.readString();
        accountID = in.readFloat();
        siteID = in.readFloat();
        signatureKey = in.readString();
    }

    public static final Creator<SearchOrderPayload> CREATOR = new Creator<SearchOrderPayload>() {
        @Override
        public SearchOrderPayload createFromParcel(Parcel in) {
            return new SearchOrderPayload(in);
        }

        @Override
        public SearchOrderPayload[] newArray(int size) {
            return new SearchOrderPayload[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(customerName);
        dest.writeString(mobileNo);
        dest.writeString(eMailAddress);
        dest.writeString(orderNo);
        dest.writeFloat(accountID);
        dest.writeFloat(siteID);
        dest.writeString(signatureKey);
    }
}
