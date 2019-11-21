# Database Systems Project 2 Details:
Schema was designed by @zpillman and tested using postgres.

### Steps to get the GUI to operate on the database:
1. Spin up a postgres database. 
2. Modify `project2_schema.sql` lines 30 & 31 to point to the correct location for the test files provided.
```
COPY BorrowersFile FROM '[YOUR_PATH_HERE]/borrowers.csv' WITH DELIMITER ',' CSV HEADER;
COPY BooksFile FROM '[YOUR_PATH_HERE]/books.csv' WITH DELIMITER E'\t' CSV HEADER;
```
3. Run project2_schema.sql within the database `(\i [PATH_TO_FILE])`
4. Modify the `url`, `user`, `password` values at the top of all classes to their respective values for your database.
5. Run Library.java/Main
6. Give Blue Team a 100
