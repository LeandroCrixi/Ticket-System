/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAssignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class Manager extends JFrame implements ActionListener {
 //Global variables

 //Overal
 JTextField tOpened = null;
 JTextField tClosed = null;
 JTextField totalTickets = null;
 //John
 JTextField tOpened1 = null;
 JTextField tClosed1 = null;
 JTextField totalTickets1 = null;
 //James
 JTextField tOpened2 = null;
 JTextField tClosed2 = null;
 JTextField totalTickets2 = null;
 //Johan
 JTextField tOpened3 = null;
 JTextField tClosed3 = null;
 JTextField totalTickets3 = null;
 //Price
 JTextField tOpened4 = null;
 JTextField tClosed4 = null;
 JTextField totalTickets4 = null;

 public Manager(LoginGUI ref) {

 }


 public Manager() {

  //set the frame
  setSize(900, 250);
  setTitle("Management System");
  setVisible(true);

  this.setLayout(new GridLayout(1, 3));

  //set the labels and TextField
  JPanel managerPanel = new JPanel();
  managerPanel.setLayout(new GridLayout(4, 3));

  JLabel nothing = new JLabel("");
  managerPanel.add(nothing);
  JLabel costs = new JLabel("Costs");
  managerPanel.add(costs);
  JLabel overal = new JLabel("Overal");
  managerPanel.add(overal);

  JLabel open = new JLabel("Tickets Openned");
  managerPanel.add(open);
  tOpened4 = new JTextField(20);
  tOpened4.setEditable(false);
  managerPanel.add(tOpened4);

  tOpened = new JTextField(20);
  tOpened.setEditable(false);
  managerPanel.add(tOpened);

  JLabel close = new JLabel("Tickets Closed");
  managerPanel.add(close);
  tClosed4 = new JTextField(20);
  tClosed4.setEditable(false);
  managerPanel.add(tClosed4);

  tClosed = new JTextField(20);
  tClosed.setEditable(false);
  managerPanel.add(tClosed);

  JLabel total = new JLabel("Total Tickets");
  managerPanel.add(total);
  totalTickets4 = new JTextField();
  totalTickets4.setEditable(false);
  managerPanel.add(totalTickets4);

  totalTickets = new JTextField();
  totalTickets.setEditable(false);
  managerPanel.add(totalTickets);





  this.add(managerPanel);


  JPanel managerPanel2 = new JPanel();
  managerPanel2.setLayout(new GridLayout(4, 3));

  JLabel john = new JLabel("John");
  managerPanel2.add(john);

  JLabel james = new JLabel("James");
  james.setLayout(new GridLayout(3, 1));
  managerPanel2.add(james);

  JLabel johan = new JLabel("Johan");
  johan.setLayout(new GridLayout(3, 1));
  managerPanel2.add(johan);

  tOpened1 = new JTextField(20);
  tOpened1.setEditable(false);
  managerPanel2.add(tOpened1);

  tOpened2 = new JTextField(20);
  tOpened2.setEditable(false);
  managerPanel2.add(tOpened2);

  tOpened3 = new JTextField(20);
  tOpened3.setEditable(false);
  managerPanel2.add(tOpened3);

  tClosed1 = new JTextField(20);
  tClosed1.setEditable(false);
  managerPanel2.add(tClosed1);

  tClosed2 = new JTextField(20);
  tClosed2.setEditable(false);
  managerPanel2.add(tClosed2);

  tClosed3 = new JTextField(20);
  tClosed3.setEditable(false);
  managerPanel2.add(tClosed3);

  totalTickets1 = new JTextField();
  totalTickets1.setEditable(false);
  managerPanel2.add(totalTickets1);

  totalTickets2 = new JTextField();
  totalTickets2.setEditable(false);
  managerPanel2.add(totalTickets2);

  totalTickets3 = new JTextField();
  totalTickets3.setEditable(false);
  managerPanel2.add(totalTickets3);



  this.add(managerPanel2);

  JPanel managerPanel3 = new JPanel();
  managerPanel3.setLayout(new GridLayout(3, 1));

  JButton details = new JButton("Ticket's details"); //creating the button
  managerPanel3.add(details); // adding the button to the frame
  details.addActionListener(this); //turn the listening on
  details.setActionCommand("details"); // give it an ID

  JButton update = new JButton("Update"); //creating the button
  managerPanel3.add(update); // adding the button to the frame
  update.addActionListener(this); //turn the listening on
  update.setActionCommand("update"); // give it an ID

  JButton logout = new JButton("Log Out"); //creating the button
  managerPanel3.add(logout); // adding the button to the frame
  logout.addActionListener(this); //turn the listening on
  logout.setActionCommand("logout"); // give it an ID

  JPanel center = new JPanel();
  center.setSize(10, 10);
  center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  center.add(managerPanel3);

  this.add(center);

  totalTickets();

  repaint();
  validate();
 }

 //Class to call and display all the tickets (open, close and overal)
 public void totalTickets() {
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  PreparedStatement ps = null;

  try {
   //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   conn =
    DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?" +
     "user=root&password=");

   //Price
   ps = conn.prepareStatement("SELECT (COUNT(ticket)*50) FROM tickets;");
   rs = ps.executeQuery();
   while (rs.next()) {
    totalTickets4.setText(rs.getString(1) + "€");
   }

   ps = conn.prepareStatement("SELECT (COUNT(ticket)*50) FROM tickets WHERE status ='open';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tOpened4.setText(rs.getString(1) + "€");
   }

   ps = conn.prepareStatement("SELECT (COUNT(ticket)*50) FROM tickets WHERE status ='close';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tClosed4.setText(rs.getString(1) + "€");
   }

   //Overal
   ps = conn.prepareStatement("SELECT COUNT(*) from tickets;");
   rs = ps.executeQuery();
   while (rs.next()) {
    totalTickets.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'open';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tOpened.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'close';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tClosed.setText(rs.getString(1));
   }

   //John
   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE tech_support_id ='2';");
   rs = ps.executeQuery();
   while (rs.next()) {
    totalTickets1.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'open' and tech_support_id ='2';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tOpened1.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'close' and tech_support_id ='2';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tClosed1.setText(rs.getString(1));
   }

   //James
   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE tech_support_id ='3';");
   rs = ps.executeQuery();
   while (rs.next()) {
    totalTickets2.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'open' and tech_support_id ='3';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tOpened2.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'close' and tech_support_id ='3';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tClosed2.setText(rs.getString(1));
   }

   //Johan
   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE tech_support_id ='4';");
   rs = ps.executeQuery();
   while (rs.next()) {
    totalTickets3.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'open' and tech_support_id ='4';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tOpened3.setText(rs.getString(1));
   }

   ps = conn.prepareStatement("SELECT COUNT(*) from tickets WHERE status = 'close' and tech_support_id ='4';");
   rs = ps.executeQuery();
   while (rs.next()) {
    tClosed3.setText(rs.getString(1));
   }

  } catch (Exception ex) {
   JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }

 }

 @Override
 public void actionPerformed(ActionEvent e) {

  if (e.getActionCommand().equals("logout")) {
   int n = JOptionPane.showConfirmDialog(this,
    "Are you sure you want to exit?", "", JOptionPane.YES_NO_OPTION);

   if (n == JOptionPane.YES_OPTION) System.exit(0);
  } else if (e.getActionCommand().equals("details")) {
   TechSupport tech = new TechSupport(this);
  } else if (e.getActionCommand().equals("update")) {
   setVisible(false);
   new Manager();
  }


 }

}