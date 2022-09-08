package com.elcom.lb.repository.Book;

import com.elcom.lb.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BookCustomizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCustomizeRepository.class);

    private SessionFactory sessionFactory;

    @Autowired
    public BookCustomizeRepository(EntityManagerFactory factory)
    {
        if(factory.unwrap(SessionFactory.class)== null)
            throw new NullPointerException(" factory is not a hibernate factory ");
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }
    public List<Book> findAll()
    {
        Session session = openSession();
        List<Book> result = new ArrayList<>();
        try {
            Query query = session.createNativeQuery("SELECT * FROM book", Book.class);

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
                Query query = session.createNativeQuery("insert into book(bookid,book_name,first_Letter,authorid,categoryid)"
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
    public Book findById(UUID bookID)
    {
        Session session = openSession();
        try{
            Book book = (Book) session.load(Book.class, bookID);
            return book;
        }
        catch(EntityNotFoundException ex)
        {
            LOGGER.error(ex.toString());
        }
        finally
        {
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
