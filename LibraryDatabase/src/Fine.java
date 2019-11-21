public class Fine {
  private int loanId;
  private double fineAmt;
  private boolean isPaid;

  public Fine() {
  }

  public Fine(int loanId, double fineAmt, boolean isPaid) {
    this.loanId = loanId;
    this.fineAmt = fineAmt;
    this.isPaid = isPaid;
  }

  public int getLoanId() {
    return loanId;
  }

  public void setLoanId(int loanId) {
    this.loanId = loanId;
  }

  public double getFineAmt() {
    return fineAmt;
  }

  public void setFineAmt(double fineAmt) {
    this.fineAmt = fineAmt;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }

  @Override
  public String toString() {
    return "Fine{" +
        "loanId=" + loanId +
        ", fineAmt=" + fineAmt +
        ", isPaid=" + isPaid +
        '}';
  }
}
