package com.project.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "churches")
public class Church {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "adm_area")
    private String admArea;
    @Column(name = "district")
    private String district;
    @Column(name = "adress")
    private String adress;
    @Column(name = "metro_station")
    private String metroStation;
    @Column(name = "metro_line")
    private String metroLine;
    @Column(name = "website")
    private String website;
    @Column(columnDefinition = "text[]", name = "phone_numbers")
    @Type(type = "com.project.type.PostgreSqlStringArrayType")
    private String[] phoneNumbers;
    @Column(columnDefinition = "text[]")
    @Type(type = "com.project.type.PostgreSqlStringArrayType")
    private String[] coordinates;
    @Column(name = "coordinate_type")
    private String coordinateType;

    public Church() {}

    public Church(String name, String admArea, String district, String adress,
                  String metroStation, String metroLine, String website,
                  String[] phoneNumbers, String[] coordinates, String coordinateType) {
        this.name = name;
        this.admArea = admArea;
        this.district = district;
        this.adress = adress;
        this.metroStation = metroStation;
        this.metroLine = metroLine;
        this.website = website;
        this.phoneNumbers = phoneNumbers;
        this.coordinates = coordinates;
        this.coordinateType = coordinateType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmArea() {
        return admArea;
    }

    public void setAdmArea(String admArea) {
        this.admArea = admArea;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getMetroLine() {
        return metroLine;
    }

    public void setMetroLine(String metroLine) {
        this.metroLine = metroLine;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    @Override
    public String toString() {
        return "Church{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admArea='" + admArea + '\'' +
                ", district='" + district + '\'' +
                ", adress='" + adress + '\'' +
                ", metroStation='" + metroStation + '\'' +
                ", metroLine='" + metroLine + '\'' +
                ", website='" + website + '\'' +
                ", phoneNumbers=" + Arrays.toString(phoneNumbers) +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", coordinateType='" + coordinateType + '\'' +
                '}';
    }
}
