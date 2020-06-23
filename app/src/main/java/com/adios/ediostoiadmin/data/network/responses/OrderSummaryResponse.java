package com.adios.ediostoiadmin.data.network.responses;

import com.adios.ediostoiadmin.data.modal.OrderSummary;

import java.util.ArrayList;

public class OrderSummaryResponse {
    private String Result_Status;
    private String Result_Message;
    private String Updated_Date;
    ArrayList <OrderSummary> Result_Output;
    private String Result_Code;
//     "fromDate": "2020-6-1 10:00:00",
//    "toDate": "2020-06-4 10:00:00"

    public String getResult_Status() {
        return Result_Status;
    }

    public String getResult_Message() {
        return Result_Message;
    }

    public String getUpdated_Date() {
        return Updated_Date;
    }

    public ArrayList<OrderSummary> getResult_Output() {
        return Result_Output;
    }

    public String getResult_Code() {
        return Result_Code;
    }

    @Override
    public String toString() {
        return "OrderSummaryResponse{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Result_Message='" + Result_Message + '\'' +
                ", Updated_Date='" + Updated_Date + '\'' +
                ", Result_Output=" + Result_Output +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }
}

