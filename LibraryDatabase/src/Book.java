public class Book {
  private String isbn;
  private String isbn13;
  private String title;
  private Author author;
  private String cover;
  private String publisher;
  private int pages;
  private boolean isCheckedOut;

  public Book() {
  }

  public Book(String isbn, String isbn13, String title, Author author, String cover,
      String publisher, int pages, boolean isCheckedOut) {
    this.isbn = isbn;
    this.isbn13 = isbn13;
    this.title = title;
    this.author = author;
    this.cover = cover;
    this.publisher = publisher;
    this.pages = pages;
    this.isCheckedOut = isCheckedOut;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public boolean isCheckedOut() {
    return isCheckedOut;
  }

  public void setCheckedOut(boolean checkedOut) {
    isCheckedOut = checkedOut;
  }

  @Override
  public String toString() {
    return "Book{" +
        "isbn='" + isbn + '\'' +
        ", isbn13='" + isbn13 + '\'' +
        ", title='" + title + '\'' +
        ", author=" + author +
        ", cover='" + cover + '\'' +
        ", publisher='" + publisher + '\'' +
        ", pages=" + pages + '\'' +
        ", isCheckedOut='" + isCheckedOut + '\'' +
        '}';
  }
}
