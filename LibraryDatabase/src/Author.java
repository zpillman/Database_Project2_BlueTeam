public class Author {
  private int authorId;
  private String[] names;

  public Author() {
  }

  public Author(int authorId, String[] names) {
    this.authorId = authorId;
    this.names = names;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public String[] getNames() {
    return names;
  }

  public void setNames(String[] names) {
    this.names = names;
  }

  @Override
  public String toString() {
    return "Author{" +
        "names='" + names + '\''
        + '}';
  }
}
