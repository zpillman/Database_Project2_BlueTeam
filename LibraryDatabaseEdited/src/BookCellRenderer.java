import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class BookCellRenderer extends JLabel implements ListCellRenderer {
  private static final Color HIGHLIGHT_COLOR = new Color(0, 1, 128);

  public BookCellRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }

  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    Book book = (Book) value;

    Author author = book.getAuthor();

    String displayString = "Isbn10: " + book.getIsbn10() + "   Title: " + book.getTitle() +
        "    Authors: " + author.getFullName() + "    Is Available: " + !book.isCheckedOut();

    setText(displayString);
    if (isSelected) {
      setBackground(HIGHLIGHT_COLOR);
      setForeground(Color.white);
    } else {
      setBackground(Color.white);
      setForeground(Color.black);
    }
    return this;
  }
}