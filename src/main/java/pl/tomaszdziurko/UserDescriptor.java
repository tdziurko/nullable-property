package pl.tomaszdziurko;

public class UserDescriptor {

    private String name;
    private String surname;
    private String countryName;
    private Long countryPopulation;
    private String cityName;

    public UserDescriptor(String name, String surname, String countryName, Long countryPopulation, String cityName, Object isNull) {
        this.name = name;
        this.surname = surname;
        this.countryName = countryName;
        this.countryPopulation = countryPopulation;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountryName() {
        return countryName;
    }

    public Long getCountryPopulation() {
        return countryPopulation;
    }

    public String getCityName() {
        return cityName;
    }
}
