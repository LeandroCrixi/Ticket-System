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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class DeleteTicket extends JFrame implements ActionListener {

 //Global variable
 JTextField ticketNum = null;


 public DeleteTicket(TechSupport ref) {

  //set the frame
  setSize(280, 130);
  setTitle("Delete a Ticket");
  setVisible(true);

  //set the labels and TextField
  JPanel deletingTicket = new JPanel();
  deletingTicket.setLayout(new GridLayout(2, 1));
  this.add(deletingTicket);

  JLabel ticket = new JLabel("Ticket Number");
  deletingTicket.add(ticket);
  ticketNum = new JTextField(20);
  deletingTicket.add(ticketNum);

  JPanel bottonDel = new JPanel();
  deletingTicket.add(bottonDel);

  JButton delete = new JButton("Delete Ticket"); //Creating the button
  bottonDel.add(delete); // Adding the button to the frame
  delete.addActionListener(this); // turn the listening on
  delete.setActionCommand("delete"); // give it an ID


  repaint();
  validate();
 }

 //Class to delete a ticket by ticket number
 public void Delete() {
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

   String tk = ticketNum.getText();

   if (stmt.execute("DELETE FROM `logingui`.`tickets` WHERE  `ticket`='" + tk + "';")) {

   }
   JOptionPane.showMessageDialog(this, "Ticket deleted from the system!");

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

  Delete();
  //set the frame not visible when the program finish
  setVisible(false);

 }

}