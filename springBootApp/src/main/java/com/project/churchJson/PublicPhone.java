package com.project.churchJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "PublicPhone",
})
public class PublicPhone{
    @JsonProperty("PublicPhone")
    public String publicPhone;

    @JsonProperty("PublicPhone")
    public String getPublicPhone() {
        return publicPhone;
    }

    @JsonProperty("PublicPhone")
    public void setPublicPhone(String publicPhone) {
        this.publicPhone = publicPhone;
    }

    @Override
    public String toString() {
        return "PublicPhone{" +
                "publicPhone='" + publicPhone + '\'' +
                '}';
    }
}