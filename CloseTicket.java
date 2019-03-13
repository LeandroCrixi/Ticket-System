/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAssignment;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class CloseTicket extends JFrame implements ActionListener {
 //Global variable
 JTextField ticketClose = null;
 JTextField epochClose = null;
 JTextField statusClose = null;

 public CloseTicket(TechSupport ref) {
  //set the frame
  setSize(320, 200);
  setTitle("Closing the ticket");
  setVisible(true);

  //set the labels and TextField
  JPanel closing = new JPanel();
  closing.setLayout(new GridLayout(3, 1));
  this.add(closing);

  JLabel tk = new JLabel("Ticket");
  closing.add(tk);
  ticketClose = new JTextField(20);
  closing.add(ticketClose);

  JLabel epch = new JLabel("Epoch");
  closing.add(epch);
  epochClose = new JTextField(20);
  epochClose.setEditable(false);
  closing.add(epochClose);
  epochClose.setText(Long.toString(Instant.now().getEpochSecond()));

  JLabel nth = new JLabel();
  closing.add(nth);

  JPanel botton = new JPanel();
  closing.add(botton);
  JButton saveClose = new JButton("SAVE");
  botton.add(saveClose);
  saveClose.addActionListener(this); // turn the listening on
  saveClose.setActionCommand("save"); // give it an ID

  repaint();
  validate();
 }

 //Class to closing the ticket by the ticket number
 public void ClosingTicket() {
  try {

   Class.forName("com.mysql.jdbc.Driver").newInstance();

  } catch (Exception e) {}


  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  try {
   conn =
    DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?user=root&password=");

   // Do something with the Connection
   stmt = conn.createStatement();

   String tc = ticketClose.getText();
   String ec = epochClose.getText();
   //String sc = statusClose.getText();

   if (stmt.execute("UPDATE `logingui`.`tickets` " + "SET `close_date`= '" + ec + "' , `status`= 'Close'" + "WHERE  `ticket`= '" + tc + "';")) {

   }
   JOptionPane.showMessageDialog(this, "Ticket Closed!");

   // loop over results

  } catch (SQLException ex) {
   // handle any errors
   System.out.println("SQLException: " + ex.getMessage());
   System.out.println("SQLState: " + ex.getSQLState());
   System.out.println("VendorError: " + ex.getErrorCode());
  }
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  ClosingTicket();
  //set the frame not visible when the program finish
  setVisible(false);
 }

}