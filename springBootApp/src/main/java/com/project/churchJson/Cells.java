package com.project.churchJson;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "global_id",
        "Name",
        "AdmArea",
        "District",
        "Address",
        "MetroStation",
        "MetroLine",
        "WebSite",
        "PublicPhone",
        "geoData"
})
public class Cells {

    @JsonProperty("global_id")
    private Integer globalId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("AdmArea")
    private String admArea;
    @JsonProperty("District")
    private String district;
    @JsonProperty("Address")
    public String address;
    @JsonProperty("MetroLine")
    private String metroLine;
    @JsonProperty("WebSite")
    private String webSite;
    @JsonProperty("MetroStation")
    private String metroStation;
    @JsonProperty("PublicPhone")
    public List<PublicPhone> publicPhone;
    @JsonProperty("geoData")
    private GeoData geoData;

    @JsonProperty("global_id")
    public Integer getGlobalId() {
        return globalId;
    }

    @JsonProperty("global_id")
    public void setGlobalId(Integer globalId) {
        this.globalId = globalId;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String objectName) {
        this.name = objectName;
    }

    @JsonProperty("PublicPhone")
    public void setPublicPhone(List<PublicPhone> publicPhone) {
        this.publicPhone = publicPhone;
    }

    @JsonProperty("PublicPhone")
    public List<PublicPhone> getPublicPhone() {
        return publicPhone;
    }

    @JsonProperty("AdmArea")
    public void setAdmArea(String admArea) {
        this.admArea = admArea;
    }

    @JsonProperty("AdmArea")
    public String getAdmArea() {
        return admArea;
    }

    @JsonProperty("District")
    public String getDistrict() {
        return district;
    }

    @JsonProperty("District")
    public void setDistrict(String district) {
        this.district = district;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("WebSite")
    public String getWebSite() {
        return webSite;
    }

    @JsonProperty("WebSite")
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @JsonProperty("geoData")
    public GeoData getGeoData() {
        return geoData;
    }

    @JsonProperty("geoData")
    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }

    @JsonProperty("MetroLine")
    public String getMetroLine() {
        return metroLine;
    }

    @JsonProperty("MetroLine")
    public void setMetroLine(String metroLine) {
        this.metroLine = metroLine;
    }

    @JsonProperty("MetroStation")
    public String getMetroStation() {
        return metroStation;
    }

    @JsonProperty("MetroStation")
    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    @Override
    public String toString() {
        return "Cells{" +
                " globalId=" + globalId + '\'' + "," + '\n' +
                " name='" + name + '\'' + "," + '\n' +
                " admArea='" + admArea + '\'' + "," + '\n' +
                " district='" + district + '\'' + "," + '\n' +
                " address='" + address + '\'' + "," + '\n' +
                " metroLine='" + metroLine + '\'' + "," + '\n' +
                " webSite='" + webSite + '\'' + "," + '\n' +
                " metroStation='" + metroStation + '\'' + "," + '\n' +
                " publicPhone=" + publicPhone  + "," + '\n' +
                " geoData=" + geoData +
                '}';
    }
}