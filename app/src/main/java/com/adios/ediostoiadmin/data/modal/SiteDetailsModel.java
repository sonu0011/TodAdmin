package com.adios.ediostoiadmin.data.modal;

public class SiteDetailsModel {
    private String orderCancellationInMin;
    private float euAppVersion;
    private String itemWiseSpiceLevels;
    private int orderReadyTime;
    private String timeZone;
    private String  adminAppForceUpgrade;
    private String closingDates;
    private String euAppForceUpgrade;
    private String openingDaysTimes;
    private String orderPrefix;
    private String siteCurrency;
    private float adminAppVersion;

    public SiteDetailsModel(String orderCancellationInMin, float euAppVersion, String itemWiseSpiceLevels, int orderReadyTime, String timeZone, String adminAppForceUpgrade, String closingDates, String euAppForceUpgrade, String openingDaysTimes, String orderPrefix, String siteCurrency, float adminAppVersion) {
        this.orderCancellationInMin = orderCancellationInMin;
        this.euAppVersion = euAppVersion;
        this.itemWiseSpiceLevels = itemWiseSpiceLevels;
        this.orderReadyTime = orderReadyTime;
        this.timeZone = timeZone;
        this.adminAppForceUpgrade = adminAppForceUpgrade;
        this.closingDates = closingDates;
        this.euAppForceUpgrade = euAppForceUpgrade;
        this.openingDaysTimes = openingDaysTimes;
        this.orderPrefix = orderPrefix;
        this.siteCurrency = siteCurrency;
        this.adminAppVersion = adminAppVersion;
    }

}
