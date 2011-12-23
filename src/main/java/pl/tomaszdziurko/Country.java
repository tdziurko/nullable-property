package pl.tomaszdziurko;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "population", nullable = false)
    private Long population;

    public Country() {
    }

    public Country(String name, Long population) {
        this.population = population;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
