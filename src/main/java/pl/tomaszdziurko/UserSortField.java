package pl.tomaszdziurko;

public enum UserSortField {

    ID("u", "u.id"),
    NAME("u", "u.name"),
    SURNAME("u", "u.surname"),
    COUNTRY_POPULATION("country", "country.population"),
    COUNTRY_NAME("country", "country.name"),
    CITY_NAME("city", "city.name");

    private String entity;
    private String field;

    private UserSortField(String entity, String field) {
        this.entity = entity;
        this.field = field;
    }

    public String getEntity() {
        return entity;
    }

    public String getField() {
        return field;
    }
}
