package com.adios.ediostoiadmin.data.network.responses;

import com.adios.ediostoiadmin.data.modal.SiteDetailsModel;

public class AuthResponse {
    private String Result_Status;
    private SiteDetailsModel Site_Details;
    private String Result_Message;
    private String  Updated_Date;
    private String Result_Output;
    private String Result_Code;


    public AuthResponse(String result_Status, SiteDetailsModel site_Details, String result_Message, String updated_Date, String result_Output, String result_Code) {
        Result_Status = result_Status;
        Site_Details = site_Details;
        Result_Message = result_Message;
        Updated_Date = updated_Date;
        Result_Output = result_Output;
        Result_Code = result_Code;
    }

    @Override
    public String toString() {
        return "LoginModal{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Site_Details=" + Site_Details +
                ", Result_Message='" + Result_Message + '\'' +
                ", Updated_Date='" + Updated_Date + '\'' +
                ", Result_Output='" + Result_Output + '\'' +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }

    public String getResult_Message() {
        return Result_Message;
    }

    public String getResult_Status() {
        return Result_Status;
    }
}
