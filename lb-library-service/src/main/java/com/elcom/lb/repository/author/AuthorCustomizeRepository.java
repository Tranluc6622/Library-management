package com.elcom.lb.repository.author;

import com.elcom.lb.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorCustomizeRepository.class);

    @PersistenceContext
    private EntityManager entityManager;
    private SessionFactory sessionFactory;
    @Autowired
    public AuthorCustomizeRepository (EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
    public Author findById(String authorID)
    {
        Session session = openSession();
        try
        {
            Author author = session.load(Author.class, authorID);
            return author;
        }
        catch (EntityActionVetoException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally {
            closeSession(session);
        }
        return null;
    }

    public List<Author> findAll()
    {
        Session session = openSession();
        List<Author> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM author",Author.class);

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

    @Transactional
    public void insertTest(Author author)
    {
        entityManager.createNativeQuery("INSERT INTO author (authorid, author_name) values(:authorid, :author_name)",Author.class)
                .setParameter(1, author.getAuthorID())
                .setParameter(2, author.getAuthorName())
                .executeUpdate();
    }
    /*
    @Transactional
    public boolean insertTest(Author author) {
        Session session = sessionFactory.openSession();
        try {
//            for(int i=1;i<=4;i++) {
            Query query = session.createNativeQuery("insert into author(authorid,author_name) "
                    + " values(:authorid, :author_name)");
                query.setParameter("authorid",author.getAuthorID());
                query.setParameter("author_name", author.getAuthorName());
                query.executeUpdate();
//            }
            return true;
        }catch (NoResultException ex)
        {
            LOGGER.error(ex.toString());
            return false;
        }
        finally
        {
            closeSession(session);
        }
    }

    /*
    @Transactional
    public boolean insertTest(Author author) {
        EntityManager entityManager = new EntityManager(author);
        entityManager.createNativeQuery("INSERT INTO author (authorid, author_name ) VALUES (:authorid,:author_name)")
                .setParameter(1, author.getAuthorID())
                .setParameter(2, author.getAuthorName())
                .executeUpdate();
        return false;
    }

     */
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
