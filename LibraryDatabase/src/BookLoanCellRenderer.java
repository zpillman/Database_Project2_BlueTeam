import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class BookLoanCellRenderer extends JLabel implements ListCellRenderer {
  private static final Color HIGHLIGHT_COLOR = new Color(0, 1, 128);

  public BookLoanCellRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }

  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    BookLoan bookLoan = (BookLoan) value;

    String displayString = "Loan Id: " + bookLoan.getLoanId()
        + "     Card Id: " + bookLoan.getCardId()
        + "     Isbn10: " + bookLoan.getIsbn()
        + "     Date Out: " + bookLoan.getDateOut()
        + "     Due Date: " + bookLoan.getDueDate();

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