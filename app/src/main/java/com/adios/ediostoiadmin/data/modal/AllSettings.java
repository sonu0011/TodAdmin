package com.adios.ediostoiadmin.data.modal;

public class AllSettings {
    AdminSettings adminSettings;
    private String refresh_time;
    private boolean autoPrint;
    private String printSize;

    public AdminSettings getAdminSettings() {
        return adminSettings;
    }

    public String getRefresh_time() {
        return refresh_time;
    }

    public boolean isAutoPrint() {
        return autoPrint;
    }

    public String getPrintSize() {
        return printSize;
    }

    public AllSettings(AdminSettings adminSettings, String refresh_time, boolean autoPrint, String printSize) {
        this.adminSettings = adminSettings;
        this.refresh_time = refresh_time;
        this.autoPrint = autoPrint;
        this.printSize = printSize;
    }

    @Override
    public String toString() {
        return "AllSettings{" +
                "adminSettings=" + adminSettings +
                ", refresh_time='" + refresh_time + '\'' +
                ", autoPrint=" + autoPrint +
                ", printSize='" + printSize + '\'' +
                '}';
    }
}
