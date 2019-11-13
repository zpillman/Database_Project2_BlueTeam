--Project 2 Schema
--author: zjp160030

--Create Temp Table for CSV file data
CREATE TABLE BorrowersFile
(
    borrower_id INT,
    ssn VARCHAR(11),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    addr VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(2),
    phone VARCHAR(18)
);

CREATE TABLE BooksFile
(
    isbn10 VARCHAR(10),
    isbn13 VARCHAR(13),
    title VARCHAR(255),
    author VARCHAR(255),
    cover VARCHAR(255),
    publisher VARCHAR(255),
    pages INT
);

--Import CSV Data into temp tables
COPY BorrowersFile FROM '/Users/zpillman/School/CS_4347_Database_Systems/project_2/borrowers.csv' WITH DELIMITER ',' CSV HEADER;
COPY BooksFile FROM '/Users/zpillman/School/CS_4347_Database_Systems/project_2/books.csv' WITH DELIMITER E'\t' CSV HEADER;

--Authors Table
CREATE TABLE Authors
(
 author_id serial NOT NULL,
 full_name varchar(255) NOT NULL
);

CREATE UNIQUE INDEX PK_Authors ON Authors
(
 author_id
);

--BookAuthors Table
CREATE TABLE BookAuthors
(
 author_id serial NOT NULL,
 isbn10    varchar(10) NOT NULL
);

CREATE UNIQUE INDEX PK_BookAuthors ON BookAuthors
(
 author_id,
 isbn10
);

CREATE INDEX fkIdx_117 ON BookAuthors
(
 isbn10
);

CREATE INDEX fkIdx_63 ON BookAuthors
(
 author_id
);

--Books Table
CREATE TABLE Books
(
 isbn10    varchar(10) NOT NULL,
 title     varchar(255) NOT NULL,
 cover     varchar(255) NULL,
 publisher varchar(255) NULL,
 pages     int NULL
);

CREATE UNIQUE INDEX PK_book ON Books
(
 isbn10
);

--BookLoans
CREATE TABLE BookLoans
(
 date_out date NOT NULL,
 due_date date NOT NULL,
 date_in  date NULL,
 card_id  int NOT NULL,
 loan_id  serial NOT NULL,
 isbn10   varchar(10) NOT NULL
);

CREATE UNIQUE INDEX PK_BookLoans ON BookLoans
(
 loan_id
);

CREATE INDEX fkIdx_111 ON BookLoans
(
 card_id
);

CREATE INDEX fkIdx_114 ON BookLoans
(
 loan_id
);

CREATE INDEX fkIdx_123 ON BookLoans
(
 isbn10
);

--Fines Table
CREATE TABLE Fines
(
 loan_id  serial NOT NULL,
 fine_amt decimal NOT NULL,
 is_paid  boolean NOT NULL
);

CREATE UNIQUE INDEX PK_Fines ON Fines
(
 loan_id
);

--Borrowers Table
CREATE TABLE Borrowers
(
 card_id    int NOT NULL,
 ssn        varchar(11) NOT NULL,
 first_name varchar(255) NOT NULL,
 last_name  varchar(255) NOT NULL,
 email      varchar(255) NULL,
 address    varchar(255) NOT NULL,
 city       varchar(255) NOT NULL,
 state      varchar(2) NOT NULL,
 phone      varchar(16) NOT NULL
);

CREATE UNIQUE INDEX PK_Borrowers ON Borrowers
(
 card_id
);

--Isbns table
CREATE TABLE Isbns
(
 isbn13 varchar(13) NULL,
 isbn10  varchar(10) NOT NULL
);

CREATE INDEX fkIdx_120 ON Isbns
(
 isbn10
);

--Execute queries to move data into expected places
INSERT INTO Borrowers(card_id, ssn, first_name, last_name, email, address, city, state, phone)
SELECT borrower_id, ssn, first_name, last_name, email, addr, city, state, phone FROM BorrowersFile;

INSERT INTO Books(isbn10, title, cover, publisher, pages)
SELECT isbn10, title, cover, publisher, pages FROM BooksFile;

INSERT INTO Isbns(isbn10, isbn13)
SELECT isbn10, isbn13 FROM BooksFile;

--Don't accept books with no author
INSERT INTO Authors(full_name)
SELECT author FROM BooksFile
WHERE author IS NOT NULL;

INSERT INTO BookAuthors(author_id, isbn10)
SELECT Authors.author_id, BooksFile.isbn10 FROM Authors
JOIN BooksFile ON BooksFile.author = Authors.full_name;

--Add constraints to tables
ALTER TABLE BookAuthors ADD CONSTRAINT FK_117 FOREIGN KEY ( isbn10 ) REFERENCES Books ( isbn10 );
ALTER TABLE BookAuthors ADD CONSTRAINT FK_63 FOREIGN KEY ( author_id ) REFERENCES Authors ( author_id );
ALTER TABLE Isbns ADD CONSTRAINT FK_120 FOREIGN KEY ( isbn10 ) REFERENCES Books ( isbn10 );
ALTER TABLE BookLoans ADD CONSTRAINT FK_111 FOREIGN KEY ( card_id ) REFERENCES Borrowers ( card_id );
ALTER TABLE BookLoans ADD CONSTRAINT FK_114 FOREIGN KEY ( loan_id ) REFERENCES Fines ( loan_id );
ALTER TABLE BookLoans ADD CONSTRAINT FK_123 FOREIGN KEY ( isbn10 ) REFERENCES Books ( isbn10 );

--Drop Temp Tables
DROP TABLE BorrowersFile;
DROP TABLE BooksFile;