package com.alexcomeau.response.geocoding;

public class Results {
    private AddressComponent[] address_components;

    public AddressComponent[] getAddress_components() {
        return address_components;
    }

    public void setAddress_components(AddressComponent[] address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public boolean isPartial_match() {
        return partial_match;
    }

    public void setPartial_match(boolean partial_match) {
        this.partial_match = partial_match;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public PlusCode getPlus_code() {
        return plus_code;
    }

    public void setPlus_code(PlusCode plus_code) {
        this.plus_code = plus_code;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    private String formatted_address;
    private Geometry geometry;
    private boolean partial_match;
    private String place_id;
    private PlusCode plus_code;
    private String[] types;
}
