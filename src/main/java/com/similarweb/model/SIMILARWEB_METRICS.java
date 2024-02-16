package com.similarweb.model;

import lombok.Getter;

@Getter
public enum SIMILARWEB_METRICS {
    VISITS("VISITS"), UNIQUE_VISITS_DESKTOP("UNIQUE_VISITS_DESKTOP"),
    UNIQUE_VISITS_MOBILE("UNIQUE_VISITS_MOBILE");;
    private String alias;

    SIMILARWEB_METRICS(String alias) {
        this.alias = alias;
    }
}
