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
public class ChangeUserAccount extends JFrame implements ActionListener {

 //Global variable
 JTextField id = null;
 JTextField username = null;
 JTextField password = null;
 JComboBox < String > type = null;

 public ChangeUserAccount(SystemAdmin ref) {

  //set the frame
  setSize(300, 200);
  setTitle("Changing the User Account");
  setVisible(true);

  //set the labels and TextField
  JPanel changingUser = new JPanel();
  changingUser.setLayout(new GridLayout(5, 1));
  this.add(changingUser);

  JLabel ident = new JLabel("ID");
  changingUser.add(ident);
  id = new JTextField(20);
  changingUser.add(id);

  JLabel usName = new JLabel("Name");
  changingUser.add(usName);
  username = new JTextField(20);
  changingUser.add(username);

  JLabel pass = new JLabel("Password");
  changingUser.add(pass);
  password = new JTextField(20);
  changingUser.add(password);

  JLabel tp = new JLabel("Type");
  changingUser.add(tp);
  type = new JComboBox < > ();
  type.addItem("System Admin");
  type.addItem("Manager");
  type.addItem("Tech Support");
  changingUser.add(type);

  JPanel bottonChange = new JPanel();
  changingUser.add(bottonChange);

  JButton save = new JButton("SAVE"); //Creating the button
  bottonChange.add(save); // Adding the button to the frame
  save.addActionListener(this); // turn the listening on
  save.setActionCommand("save"); // give it an ID

  repaint();
  validate();
 }

 /*class to reset the User Account (username, password and type) from
 the Admin account*/
 public void resetUserAccount() {
  try {

   Class.forName("com.mysql.jdbc.Driver").newInstance();

  } catch (Exception e) {}

  //Global variables for database connection	
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  //Connecting to the database
  try {
   conn =
    DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?user=root&password=");

   // Do something with the Connection
   stmt = conn.createStatement();

   //getting the information from the TextField
   String id1 = id.getText();
   String us = username.getText();
   String ps = password.getText();
   String tp = (String) type.getSelectedItem();

   //transfering the information to database
   if (stmt.execute("UPDATE `logingui`.`company` " + "SET `username`= '" + us + "' , `password`= '" + ps + "'" + " , `type`= '" + tp + "' WHERE  `id`= '" + id1 + "';")) {

   }
   JOptionPane.showMessageDialog(this, "User Account Changed!");

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

  resetUserAccount();
  //set the frame not visible when the program finish
  setVisible(false);

 }

}