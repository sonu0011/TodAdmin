package com.adios.ediostoiadmin.data.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer  implements Parcelable {
    private String mobileNo;
    private String userName;

    protected Customer(Parcel in) {
        mobileNo = in.readString();
        userName = in.readString();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mobileNo='" + mobileNo + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public String getMobileNo() {
        return mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobileNo);
        dest.writeString(userName);
    }
}
