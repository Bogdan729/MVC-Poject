package com.project.churchJson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "global_id",
        "Number",
        "Cells",
})

public class RussianChurch {
    @JsonProperty("global_id")
    public int globalId;
    @JsonProperty("Number")
    public int number;
    @JsonProperty("Cells")
    public Cells cells;

    @JsonProperty("global_id")
    public int getGlobalId() {
        return globalId;
    }

    @JsonProperty("global_id")
    public void setGlobal_id(int globalId) {
        this.globalId = globalId;
    }

    @JsonProperty("Number")
    public int getNumber() {
        return number;
    }

    @JsonProperty("Number")
    public void setNumber(int number) {
        this.number = number;
    }

    @JsonProperty("Cells")
    public Cells getCells() {
        return cells;
    }

    @JsonProperty("Cells")
    public void setCells(Cells cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "RussianСhurch{" + '\n' +
                " globalId=" + globalId + "," + '\n' +
                " number=" + number + "," + '\n' +
                " cells=" + cells + '\n' +
                '}';
    }
}