package model;

/**
 * @author Alex
 */
public class Country {

    private String country;
    private int num_residents;
    private int surface_km2;
    private int num_continent;

    public Country() {
    }

    public Country(String country, int num_residents, int surface_km2, int num_continent) {
        this.country = country;
        this.num_residents = num_residents;
        this.surface_km2 = surface_km2;
        this.num_continent = num_continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNum_residents() {
        return num_residents;
    }

    public void setNum_residents(int num_residents) {
        this.num_residents = num_residents;
    }

    public int getSurface_km2() {
        return surface_km2;
    }

    public void setSurface_km2(int surface_km2) {
        this.surface_km2 = surface_km2;
    }

    public int getNum_continent() {
        return num_continent;
    }

    public void setNum_continent(int num_continent) {
        this.num_continent = num_continent;
    }
    
    
    
}
