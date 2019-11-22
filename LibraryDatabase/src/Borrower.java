public class Borrower {
  private int cardId;
  private String ssn;
  private String bname;
  private String bnameLast;
  private String email;
  private String address;
  private String city;
  private String state;
  private String phone;

  public Borrower() {
  }

  public Borrower(int cardId, String ssn, String bname, String bnameLast, String email,
      String address, String city, String state, String phone) {
    this.cardId = cardId;
    this.ssn = ssn;
    this.bname = bname;
    this.bnameLast = bnameLast;
    this.email = email;
    this.address = address;
    this.city = city;
    this.state = state;
    this.phone = phone;
  }

  public int getCardId() {
    return cardId;
  }

  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getBname() {
    return bname;
  }

  public void setBname(String bname) {
    this.bname = bname;
  }

  public String getBnameLast() {
    return bnameLast;
  }

  public void setBnameLast(String bnameLast) {
    this.bnameLast = bnameLast;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "Borrower{" +
        "cardId=" + cardId +
        ", ssn='" + ssn + '\'' +
        ", bname='" + bname + '\'' +
        ", bnameLast='" + bnameLast + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", phone='" + phone + '\'' +
        '}';
  }
}
