package com.adios.ediostoiadmin.data.network.responses;

import com.adios.ediostoiadmin.data.modal.DeliveryPartners;

public class DeliveryPartnerResponse {
    private String Result_Status;
    private String Result_Message;
    private String Updated_Date;
    private DeliveryPartners Result_Output;
    private String Result_Code;

    public String getResult_Status() {
        return Result_Status;
    }

    public String getResult_Message() {
        return Result_Message;
    }

    public String getUpdated_Date() {
        return Updated_Date;
    }

    public DeliveryPartners getResult_OutputObject() {
        return Result_Output;
    }

    public String getResult_Code() {
        return Result_Code;
    }

    @Override
    public String toString() {
        return "DeliveryPartnerResponse{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Result_Message='" + Result_Message + '\'' +
                ", Updated_Date='" + Updated_Date + '\'' +
                ", Result_OutputObject=" + Result_Output +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }
}
