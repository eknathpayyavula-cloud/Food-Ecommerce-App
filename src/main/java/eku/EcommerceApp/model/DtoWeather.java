package eku.EcommerceApp.model;

public class DtoWeather {

    private String name;
    private String region;
    private String country;
    private DtoNew current;

    public DtoWeather( ) {
    }

    public DtoWeather(String name, String region, String country, DtoNew current) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.current=current;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DtoNew getCurrent() {
        return current;
    }

    public void setCurrent(DtoNew current) {
        this.current = current;
    }

}
