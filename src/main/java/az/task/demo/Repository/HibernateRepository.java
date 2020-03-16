package az.task.demo.Repository;

import az.task.demo.Domains.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class HibernateRepository {

    private final EntityManagerFactory managerFactory;

    private SessionFactory sessionFactory;

    public HibernateRepository(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
        sessionFactory = managerFactory.unwrap(SessionFactory.class);
    }

    @PostConstruct
    public void createSessionFactory() {
        sessionFactory = managerFactory.unwrap(SessionFactory.class);
    }

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Transactional
    public boolean update(String query) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.clear();
        Query runnableQuery=session.createQuery(query);
        int update=runnableQuery.executeUpdate();
        transaction.commit();
        session.close();
        return (update!=0);
    }


}
