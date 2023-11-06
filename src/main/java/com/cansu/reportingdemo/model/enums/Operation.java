package com.cansu.reportingdemo.model.enums;

public enum Operation {

    direct("DIRECT"),
    refund("REFUND"),
    threeD("3D"),
    threeDAuth("3DAUTH"),
    stored("STORED");

    Operation(String value) {
    }

}
