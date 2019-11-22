import java.util.Date;

public class BookLoan {
  private Date dateOut;
  private Date dueDate;
  private Date dateIn;
  private int cardId;
  private int loanId;
  private String isbn;

  public BookLoan() {
  }

  public BookLoan(Date dateOut, Date dueDate, Date dateIn, int cardId, int loanId,
      String isbn) {
    this.dateOut = dateOut;
    this.dueDate = dueDate;
    this.dateIn = dateIn;
    this.cardId = cardId;
    this.loanId = loanId;
    this.isbn = isbn;
  }

  public Date getDateOut() {
    return dateOut;
  }

  public void setDateOut(Date dateOut) {
    this.dateOut = dateOut;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getDateIn() {
    return dateIn;
  }

  public void setDateIn(Date dateIn) {
    this.dateIn = dateIn;
  }

  public int getCardId() {
    return cardId;
  }

  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  public int getLoanId() {
    return loanId;
  }

  public void setLoanId(int loanId) {
    this.loanId = loanId;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return "BookLoan{" +
        "dateOut=" + dateOut +
        ", dueDate=" + dueDate +
        ", dateIn=" + dateIn +
        ", cardId=" + cardId +
        ", loanId=" + loanId +
        ", isbn='" + isbn + '\'' +
        '}';
  }
}
