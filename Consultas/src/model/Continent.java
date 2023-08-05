package model;

/**
 *
 * @author Alex
 */
public class Continent {
    private int num_continent;
    private String nombre_continent;

    public Continent() {
    }

    public Continent(int num_continent, String nombre_continent) {
        this.num_continent = num_continent;
        this.nombre_continent = nombre_continent;
    }

    public int getNum_continent() {
        return num_continent;
    }

    public void setNum_continent(int num_continent) {
        this.num_continent = num_continent;
    }

    public String getNombre_continent() {
        return nombre_continent;
    }

    public void setNombre_continent(String nombre_continent) {
        this.nombre_continent = nombre_continent;
    }
    
    
}
