package com.adios.ediostoiadmin.data.modal;

public class GenericResponse {
    private String Result_Status;
    private String Result_Message;
    private String Result_Output;
    private String updatedDate;
    private String Result_Code;

    @Override
    public String toString() {
        return "GenericResponse{" +
                "Result_Status='" + Result_Status + '\'' +
                ", Result_Message='" + Result_Message + '\'' +
                ", Result_Output='" + Result_Output + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", Result_Code='" + Result_Code + '\'' +
                '}';
    }

    public String getResult_Status() {
        return Result_Status;
    }

    public String getResult_Message() {
        return Result_Message;
    }

    public String getResult_Output() {
        return Result_Output;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getResult_Code() {
        return Result_Code;
    }
}
