package com.tracker.coronavirus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CoronaVirusDTO {

    private List<StatewiseData> statewise;

    private List<TestedData> tested;

    @JsonIgnore
    private StatewiseData total;

    @JsonIgnore
    private TestedData latestTested;
}
