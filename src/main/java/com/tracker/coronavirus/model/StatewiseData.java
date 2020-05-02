package com.tracker.coronavirus.model;

import lombok.Data;

@Data
public class StatewiseData {
    private String active;
    private String confirmed;
    private String deaths;
    private String deltaconfirmed;
    private String deltadeaths;
    private String deltarecovered;
    private String lastupdatedtime;
    private String recovered;
    private String state;
    private String statecode;
}
