package com.adios.ediostoiadmin.data.modal.payloads;

public class OderSummaryPayload {
    private String userName;
    private float accountID=1;
    private float siteID=1;
    private String fromDate;
    private String toDate;
    private String signatureKey ="1234";

    public OderSummaryPayload(String userName, String fromDate, String toDate) {
        this.userName = userName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
