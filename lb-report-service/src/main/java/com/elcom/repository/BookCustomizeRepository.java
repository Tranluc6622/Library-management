package com.elcom.repository;

import com.elcom.model_dto.CountBookByFirstLetter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookCustomizeRepository {
    public List<CountBookByFirstLetter> countBookByFirstLetter()
    {

        StringBuilder queryString = new StringBuilder("select first_letter,count(bookid) as \"numBook\" from book ");
        queryString.append(" group by first_Letter" );
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(queryString.toString());

        List<Object[]> result = query.getResultList();

        List<CountFirstLetter> vos = result.stream().map(item -> {
            CountFirstLetter countFirstLetter = new CountFirstLetter();
            countFirstLetter.setFirstLetter(item[0].toString());
            countFirstLetter.setNumBook(Integer.parseInt(item[1].toString()));


            return countFirstLetter;
        }).collect(Collectors.toList());
        return vos;
    }

    public List<CountAuthor> countAuthor()
    {

        StringBuilder queryString = new StringBuilder("select categoryid,count(bookid) as \"numBook\" from book ");
        queryString.append(" group by authorid" );
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(queryString.toString());

        List<Object[]> result = query.getResultList();

        List<CountAuthor> vo = result.stream().map(item -> {
            CountAuthor countAuthor = new CountAuthor();
            countAuthor.setAuthor(item[0].toString());
            countAuthor.setNumBook(Integer.parseInt(item[1].toString()));


            return countAuthor;
        }).collect(Collectors.toList());
        return vo;
    }
    public List<CountCategory> countCategory()
    {

        StringBuilder queryString = new StringBuilder("select categoryid,count(bookid) as \"numBook\" from book  ");
        queryString.append(" group by categoryid\n" );
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery(queryString.toString());

        List<Object[]> result = query.getResultList();

        List<CountCategory> vos = result.stream().map(item -> {
            CountCategory countCategory = new CountCategory();
            countCategory.setCategory(item[0].toString());
            countCategory.setNumBook(Integer.parseInt(item[1].toString()));


            return countCategory;
        }).collect(Collectors.toList());
        return vos;
    }
}
