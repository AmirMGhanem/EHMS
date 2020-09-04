package DBH;

import Util.JPQLHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class adressDAO implements JPQLHandler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager entityManager = emf.createEntityManager();


    @Override
    public void SelectQuery() {


    }

    @Override
    public void InsertQuery() {

    }

    @Override
    public void RemoveQuery() {

    }

    @Override
    public int CountQuery() {
        return 0;
    }

    @Override
    public List SelectAllQuery() {
        Query query = entityManager.
                createQuery("Select (a.city) from address");
        List<String> list = query.getResultList();
        return list;
    }
}
