--Project 2 Schema
--author: zjp160030

--Create Temp Table for CSV file data
CREATE TABLE BorrowersFile
(
    borrower_id INT,
    ssn VARCHAR(11),
    Bname VARCHAR(255),
    Bname_last VARCHAR(255),
    email VARCHAR(255),
    addr VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(2),
    phone VARCHAR(18)
);

CREATE TABLE BooksFile
(
    isbn VARCHAR(10),
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
 name text[] NOT NULL
);

CREATE UNIQUE INDEX PK_Authors ON Authors
(
 author_id
);

--BookAuthors Table
CREATE TABLE Book_Authors
(
 author_id serial NOT NULL,
 isbn    varchar(10) NOT NULL
);

CREATE UNIQUE INDEX PK_Book_Authors ON Book_Authors
(
 author_id,
 isbn
);

CREATE INDEX fkIdx_117 ON Book_Authors
(
 isbn
);

CREATE INDEX fkIdx_63 ON Book_Authors
(
 author_id
);

--Books Table
CREATE TABLE Book
(
 isbn    varchar(10) NOT NULL,
 title     varchar(255) NOT NULL,
 cover     varchar(255) NULL,
 publisher varchar(255) NULL,
 pages     integer NULL,
 is_checked_out boolean DEFAULT false
);

CREATE UNIQUE INDEX PK_book ON Book
(
 isbn
);

--BookLoans
CREATE TABLE Book_Loans
(
 date_out date NOT NULL DEFAULT CURRENT_DATE,
 due_date date NOT NULL DEFAULT CURRENT_DATE + 14,
 date_in  date NULL,
 card_id  serial NOT NULL,
 loan_id  serial NOT NULL,
 isbn   varchar(10) NOT NULL
);

CREATE UNIQUE INDEX PK_Book_Loans ON Book_Loans
(
 loan_id
);

CREATE INDEX fkIdx_111 ON Book_Loans
(
 card_id
);

CREATE INDEX fkIdx_114 ON Book_Loans
(
 loan_id
);

CREATE INDEX fkIdx_123 ON Book_Loans
(
 isbn
);

--Fines Table
CREATE TABLE Fines
(
 loan_id  serial NOT NULL,
 fine_amt decimal NOT NULL,
 is_paid  boolean NOT NULL DEFAULT false
);

CREATE UNIQUE INDEX PK_Fines ON Fines
(
 loan_id
);

--Borrowers Table
CREATE TABLE Borrower
(
 card_id    serial NOT NULL,
 ssn        varchar(11) NOT NULL,
 Bname      varchar(255) NOT NULL,
 Bname_last  varchar(255) NOT NULL,
 email      varchar(255) NULL,
 address    varchar(255) NOT NULL,
 city       varchar(255) NOT NULL,
 state      varchar(2) NOT NULL,
 phone      varchar(16) NOT NULL
);

CREATE UNIQUE INDEX PK_Borrower ON Borrower
(
 card_id
);

--Isbns table
CREATE TABLE Isbns
(
 isbn13 varchar(13) NULL,
 isbn  varchar(10) NOT NULL
);

CREATE INDEX fkIdx_120 ON Isbns
(
 isbn
);

--Execute queries to move data into expected places
INSERT INTO Borrower(ssn, Bname, Bname_last, email, address, city, state, phone)
SELECT ssn, Bname, Bname_last, email, addr, city, state, phone FROM BorrowersFile;

INSERT INTO Book(isbn, title, cover, publisher, pages)
SELECT isbn, title, cover, publisher, pages FROM BooksFile;

INSERT INTO Isbns(isbn, isbn13)
SELECT isbn, isbn13 FROM BooksFile;

--Don't accept books with no author
--split the comma delimited String of authors into an array
INSERT INTO Authors(name)
SELECT DISTINCT regexp_split_to_array(BooksFile.author, '\,') FROM BooksFile
WHERE BooksFile.author IS NOT NULL;

INSERT INTO Book_Authors(author_id, isbn)
SELECT DISTINCT Authors.author_id, BooksFile.isbn FROM Authors
JOIN BooksFile ON regexp_split_to_array(BooksFile.author, '\,') = Authors.name;

--Add constraints to tables
ALTER TABLE Book_Authors ADD CONSTRAINT FK_117 FOREIGN KEY ( isbn ) REFERENCES Book ( isbn );
ALTER TABLE Book_Authors ADD CONSTRAINT FK_63 FOREIGN KEY ( author_id ) REFERENCES Authors ( author_id );
ALTER TABLE Isbns ADD CONSTRAINT FK_120 FOREIGN KEY ( isbn ) REFERENCES Book ( isbn );
ALTER TABLE Book_Loans ADD CONSTRAINT FK_111 FOREIGN KEY ( card_id ) REFERENCES Borrower ( card_id );
ALTER TABLE Fines ADD CONSTRAINT FK_114 FOREIGN KEY ( loan_id ) REFERENCES Book_Loans ( loan_id );
ALTER TABLE Book_Loans ADD CONSTRAINT FK_123 FOREIGN KEY ( isbn ) REFERENCES Book ( isbn );

--Drop Temp Tables
DROP TABLE BorrowersFile;
DROP TABLE BooksFile;