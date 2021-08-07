package com.alexcomeau.response.tomtomGeocoding;

import com.alexcomeau.response.tomtomGeocoding.Result;

public class TomTomGeocodingStruct {
    private Result[] results;
    private String status;

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}