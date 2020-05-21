package com.github.wassilkhetim.android.presentation.model;

import java.util.List;

public class RestRickmortyResponse {

    private InfoObjet info;
    private List<PersonnageInfo> results;

    public InfoObjet getInfo() {
        return info;
    }

    public List<PersonnageInfo> getResults() {
        return results;
    }
}
