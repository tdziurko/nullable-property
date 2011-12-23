package pl.tomaszdziurko;

import org.hibernate.ejb.Ejb3Configuration;
import org.jboss.arquillian.api.Deployment;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;
import pl.softwaremill.common.dbtest.AbstractDBTest;
import pl.softwaremill.common.dbtest.ArchiveConfigurator;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class UserServiceTest extends AbstractDBTest {

    @Inject
    private UserService userService;

    @Deployment
    public static JavaArchive createTestArchive() {
        return new ArchiveConfigurator() {
            @Override
            protected JavaArchive configureBeans(JavaArchive ar) {
                return ar.addPackage(UserService.class.getPackage());
            }
        }.createTestArchive();
    }

    @Override
    protected void configureEntities(Ejb3Configuration cfg) {
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(City.class);
        cfg.addAnnotatedClass(Country.class);
    }

    @Override
    protected void loadTestData(EntityManager entityManager) {

        Country poland = new Country("Poland", 38L);
        entityManager.persist(poland);

        Country belgium = new Country("Belgium", 11L);
        entityManager.persist(belgium);

        Country spain = new Country("Spain", 46L);
        entityManager.persist(spain);

        Country southAfrica = new Country("South Africa", 51L);
        entityManager.persist(southAfrica);

        Country togo = new Country("Togo", 6L);
        entityManager.persist(togo);

        City warsaw = new City("Warsaw");
        entityManager.persist(warsaw);

        City cracow = new City("Cracow");
        entityManager.persist(cracow);

        City antwerp = new City("Antwerp");
        entityManager.persist(antwerp);

        City madrid = new City("Madrid");
        entityManager.persist(madrid);

        City pretoria = new City("Pretoria");
        entityManager.persist(pretoria);


        User user1 = new User("user_01", "name_01", "surname 01", poland, warsaw);
        entityManager.persist(user1);
        User user2 = new User("user_02", "name_02", "surname 02", poland, null);
        entityManager.persist(user2);
        User user3 = new User("user_03", "name_03", "surname 03", belgium, antwerp);
        entityManager.persist(user3);
        User user4 = new User("user_04", "name_04", "surname 04", southAfrica, pretoria);
        entityManager.persist(user4);
        User user5 = new User("user_05", "name_05", "surname 05", poland, warsaw);
        entityManager.persist(user5);
        User user6 = new User("user_06", "name_06", "surname 06", poland, cracow);
        entityManager.persist(user6);
        User user7 = new User("user_07", "name_07", "surname 07", spain, null);
        entityManager.persist(user7);
        User user8 = new User("user_08", "name_08", "surname 08", null, madrid);
        entityManager.persist(user8);
        User user9 = new User("user_09", "name_09", "surname 09", null, null);
        entityManager.persist(user9);
        User user10 = new User("user_10", "name_10", "surname 10", togo, null);
        entityManager.persist(user10);
    }

    @Test
    public void shouldLoadAllUserDescriptors() throws Exception {
        List<UserDescriptor> allUsers = userService.loadAllUserDescriptors(UserSortField.ID, true);
        assertThat(allUsers.size()).isEqualTo(10);
    }

    @Test
    public void shouldOrderByCountryName() throws Exception {
        List<UserDescriptor> allUsers = userService.loadAllUserDescriptors(UserSortField.COUNTRY_NAME, true);
        assertThat(allUsers).onProperty("countryName").containsExactly(
                "Belgium", "Poland", "Poland", "Poland", "Poland", "South Africa",
                "Spain", "Togo", null, null);
    }

    @Test
    public void shouldOrderByCityName() throws Exception {
        List<UserDescriptor> allUsers = userService.loadAllUserDescriptors(UserSortField.CITY_NAME, true);
        assertThat(allUsers).onProperty("cityName").containsExactly(
                "Antwerp", "Cracow", "Madrid", "Pretoria", "Warsaw", "Warsaw",
                null, null, null, null);
    }

    @Test
    public void shouldOrderByCountryPopulation() throws Exception {
        List<UserDescriptor> allUsers = userService.loadAllUserDescriptors(UserSortField.COUNTRY_POPULATION, false);
        assertThat(allUsers).onProperty("countryPopulation").containsExactly(
                null, null, 51L, 46L, 38L, 38L, 38L, 38L, 11L, 6L);
    }


    @Test
    public void shouldOrderByUserName() throws Exception {
        List<UserDescriptor> allUsers = userService.loadAllUserDescriptors(UserSortField.NAME, true);
        assertThat(allUsers.size()).isEqualTo(10);
        assertThat(allUsers).onProperty("name").containsExactly(
                "name_01", "name_02", "name_03", "name_04", "name_05", "name_06",
                "name_07", "name_08", "name_09", "name_10");
    }


}
