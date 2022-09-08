package com.elcom.lb.repository.category;

import com.elcom.lb.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CategoryCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCustomizeRepository.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CategoryCustomizeRepository(EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
    public List<Category> findAll()
    {
        Session session = openSession();
        List<Category> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM category",Category.class);

            result = query.getResultList();
        }
        catch(NoResultException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally{
            closeSession(session);
        }
        return result;
    }
    public boolean insertTest()
    {
        Session session = sessionFactory.openSession();
        try {
            for(int i =1; i<=4;i++)
            {
                Query query = session.createNativeQuery("insert into category(categoryid, category_name)"
                + "values(:categoryid, :category_name");
                query.setParameter("categoryid", UUID.randomUUID().toString());
                query.setParameter("category_name", + i);
                query.executeUpdate();
            }
        }catch(NoResultException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally
        {
            closeSession(session);
        }
        return true;
    }
    public Category findById(String categoryID)
    {
        Session session = openSession();
        try{
            Category category = session.load(Category.class,categoryID);
            return category;
        }
        catch(NoResultException ex){
            LOGGER.error(ex.toString());
        }
        finally {
            closeSession(session);
        }
        return null;
    }
    private Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    private void closeSession(Session session) {
        if( session.isOpen() ) {
            session.disconnect();
            session.close();
        }
    }
}
