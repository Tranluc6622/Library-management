package com.elcom.lb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "book")
@ToString(callSuper = true)
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name ="bookid")
    private UUID bookID;
    @Column(name = "book_name")
    private String bookName;
    @Column(name ="first_Letter")
    private String firstLetter;
    @ManyToOne
  @JsonIgnore
    @JoinColumn(name = "authorid")
    public Author authorID;
    @ManyToOne
   @JsonIgnore
    @JoinColumn(name = "categoryid")
    public Category categoryID;

//    @Transient
//    private String category;
//    @Transient
//    private String author;
//
//    public String getAuthor() {
//        return this.authorID.getAuthorID();
//    }
//    public String getCategory() {
//        return this.categoryID.getCategoryID();
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public Book() {
    }
    public Book(UUID bookID, String bookName, String firstLetter, Author authorID, Category categoryID)
    {
        this.bookID = bookID;
        this.bookName = bookName;
        this.firstLetter = firstLetter;
        this.authorID = authorID;
        this.categoryID = categoryID;
    }

    public UUID getBookID() {
        return bookID;
    }

    public void setBookID(UUID bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Author getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Author authorID) {
        this.authorID = authorID;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }
}
