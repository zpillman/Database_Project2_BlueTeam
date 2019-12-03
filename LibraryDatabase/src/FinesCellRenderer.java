import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class FinesCellRenderer extends JLabel implements ListCellRenderer {
  private static final Color HIGHLIGHT_COLOR = new Color(0, 1, 128);

  public FinesCellRenderer() {
    setOpaque(true);
    setIconTextGap(12);
  }

  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    Fine fine = (Fine) value;
    Borrower borrower = fine.getBorrower();
    BookLoan bookLoan = fine.getBookLoan();

    String displayString = "Name: " + borrower.getBname() + " " + borrower.getBnameLast()
        + "   " + "Fine Amt: " + fine.getFineAmt()
        + "   " + "Is Paid? " + fine.isPaid();

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
