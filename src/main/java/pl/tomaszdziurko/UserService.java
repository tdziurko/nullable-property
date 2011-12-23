package pl.tomaszdziurko;


import pl.softwaremill.common.cdi.persistence.Writeable;
import pl.softwaremill.common.cdi.transaction.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Transactional
public class UserService {

    @Writeable
    @Inject
    private EntityManager entityManager;

    public List<User> loadAll(UserSortField sortField, boolean ascending) {

        String queryString = "from User u left join u.country as country left join u.city as city order by " + sortField.getField() + (ascending ? " asc " : " desc ");
        return entityManager.createQuery(queryString).getResultList();
    }

public List<UserDescriptor> loadAllUserDescriptors(UserSortField sortField, boolean ascending) {

    String queryString = "select new pl.tomaszdziurko.UserDescriptor(u.name, u.surname, " +
            " country.name, country.population,  city.name, " +
            " case when " + sortField.getEntity() + " is null then 2 else 1 end as nullOrderer) from User u " +
            " left join u.city as city left join u.country as country " +
            " order by nullOrderer " + (ascending ? " asc " : " desc ") + ", " +
            sortField.getField() + (ascending ? " asc " : " desc ");

    return entityManager.createQuery(queryString).getResultList();
}
}
