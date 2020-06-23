package com.adios.ediostoiadmin.data.network.responses;

import com.adios.ediostoiadmin.data.modal.ResultOutput;
import com.adios.ediostoiadmin.data.modal.SiteDetailsEntity;
import com.google.gson.annotations.SerializedName;

public class UpdateOrderResponse {
private String Result_Status;
    private String Result_Message;
    private String Updated_Date;
    private Object Result_Output;
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

    public Object getResult_Output() {
        return Result_Output;
    }

    public String getResult_Code() {
        return Result_Code;
    }

    @Override
    public String toString() {
        return "UpdateOrderResponse{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Result_Message='" + Result_Message + '\'' +
                ", Updated_Date='" + Updated_Date + '\'' +
                ", Result_Output=" + Result_Output +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }
}
