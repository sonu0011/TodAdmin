package com.adios.ediostoiadmin.data.network.responses;

import com.adios.ediostoiadmin.data.modal.CustomerOrders;
import com.adios.ediostoiadmin.data.modal.SiteDetail;

public class AllOrdersResponse {
    private String Result_Status;
    private SiteDetail Site_Details;
    private String Result_Message;
    private String Updated_Date;
    private CustomerOrders Result_Output;
    private String Result_Code;

    public AllOrdersResponse(String result_Status, SiteDetail site_Details, String result_Message, String updated_Date, CustomerOrders result_Output, String result_Code) {
        Result_Status = result_Status;
        Site_Details = site_Details;
        Result_Message = result_Message;
        Updated_Date = updated_Date;
        Result_Output = result_Output;
        Result_Code = result_Code;
    }

    public String getResult_Status() {
        return Result_Status;
    }

    public SiteDetail getSite_Details() {
        return Site_Details;
    }

    public String getResult_Message() {
        return Result_Message;
    }

    public String getUpdated_Date() {
        return Updated_Date;
    }

    public CustomerOrders getResult_Output() {
        return Result_Output;
    }

    public String getResult_Code() {
        return Result_Code;
    }

    @Override
    public String toString() {
        return "AllOrdersResponse{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Site_Details=" + Site_Details +
                ", Result_Message='" + Result_Message + '\'' +
                ", Updated_Date='" + Updated_Date + '\'' +
                ", Result_Output=" + Result_Output.toString() +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }
}
