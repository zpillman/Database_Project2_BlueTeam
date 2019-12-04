/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Warren
 */
public class CheckIn extends javax.swing.JFrame {

    private final String url = "jdbc:postgresql://localhost:5434/postgres";
    private final String user = "zpillman";
    private final String password = "password";

    /**
     * Creates new form CheckIn
     */
    public CheckIn() {
        initComponents();
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        CheckIn_OK = new javax.swing.JButton();
        CheckIn_TextBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Check In Search");
        setBounds(new java.awt.Rectangle(500, 100, 500, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Please input book title, card number, or your name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(69, 46, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        CheckIn_OK.setText("Ok");
        CheckIn_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckIn_OKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 10, 130, 37);
        getContentPane().add(CheckIn_OK, gridBagConstraints);

        CheckIn_TextBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        CheckIn_TextBox.setPreferredSize(new java.awt.Dimension(250, 20));
        CheckIn_TextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckIn_TextBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 256;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 46, 0, 0);
        getContentPane().add(CheckIn_TextBox, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckIn_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckIn_OKActionPerformed
        String userInput = CheckIn_TextBox.getText();
        String term = "%" + userInput + "%";

        List<BookLoan> bookLoans = searchBookLoanByTerm(term);

        if(bookLoans.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Error, No BookLoans Found Matching " + userInput,
                "No BookLoan Found",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        CheckInResults cir = new CheckInResults(bookLoans);
        cir.setVisible(true);
        dispose();
    }//GEN-LAST:event_CheckIn_OKActionPerformed

    private void CheckIn_TextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckIn_TextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckIn_TextBoxActionPerformed

    public List<BookLoan> searchBookLoanByTerm(String term) {
        term = "%" + term + "%";
        String searchBookLoansByTerm = "SELECT DISTINCT Book_Loans.loan_id, Book_Loans.date_out, "
            + "Book_Loans.due_date, Borrower.card_id, "
            + "Borrower.bname, Borrower.bname_last, Book.isbn FROM Book_Loans "
            + "JOIN Book ON Book.isbn = Book_Loans.isbn "
            + "JOIN Borrower ON Borrower.card_id = Book_Loans.card_id "
            + "WHERE (Borrower.bname ILIKE ? "
            + "OR Borrower.bname_last ILIKE ? "
            + "OR Book.isbn ILIKE ? "
            + "OR (Borrower.bname || ' ' || Borrower.bname_last) ILIKE ?"
            + "OR CAST(Borrower.card_id as VARCHAR(64)) ILIKE ? ) "
            + "AND Book_Loans.date_in IS NULL ";

        List<BookLoan> bookLoans = new ArrayList<>();

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(searchBookLoansByTerm)) {
            pstmt.setString(1, term);
            pstmt.setString(2, term);
            pstmt.setString(3, term);
            pstmt.setString(4, term);
            pstmt.setString(5, term);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                BookLoan bookLoan = new BookLoan();
                bookLoan.setDateOut(rs.getDate("date_out"));
                bookLoan.setDueDate(rs.getDate("due_date"));
                bookLoan.setLoanId(rs.getInt("loan_id"));
                bookLoan.setIsbn(rs.getString("isbn"));
                bookLoan.setCardId(rs.getInt("card_id"));
                bookLoans.add(bookLoan);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return bookLoans;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckIn_OK;
    private javax.swing.JTextField CheckIn_TextBox;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
