package com.tracker.coronavirus.model;

import lombok.Data;

@Data
public class TestedData {

    private String positivecasesfromsamplesreported;
    private String samplereportedtoday;
    private String source;
    private String totalindividualstested;
    private String totalpositivecases;
    private String totalsamplestested;
    private String updatetimestamp;
}
