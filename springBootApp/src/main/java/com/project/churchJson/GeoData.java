package com.project.churchJson;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coordinates",
        "type"
})
public class GeoData {

    @JsonProperty("coordinates")
    private String[] coordinates;
    @JsonProperty("type")
    private String type;

    @JsonProperty("coordinates")
    public String[] getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GeoData{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", type='" + type + '\'' + '\n' +
                '}';
    }
}
