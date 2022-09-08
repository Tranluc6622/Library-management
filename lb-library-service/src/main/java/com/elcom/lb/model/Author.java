    package com.elcom.lb.model;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.Table;
    import java.io.Serializable;
    @Entity
    @Table(name = "author")
    public class Author implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "authorid")
        private String authorID;

        @Column(name = "author_name")
        private String authorName;

        public Author(){}

        public Author(String authorID, String authorName) {
            this.authorID = authorID;
            this.authorName = authorName;
        }
        public String getAuthorID() {
            return authorID;
        }

        public void setAuthorID(String authorID) {
            this.authorID = authorID;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }
    }
