/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAssignment;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Leandro
 */
public class LoginGUI extends JFrame implements ActionListener {

 //Global variable
 JTextField username = null;
 JPasswordField password = null;
 JComboBox < String > type = null;
 
 public static void main(String[] args) {
  new LoginGUI();
 }

 public LoginGUI() {

  //set the frame
  setSize(350, 200);
  setTitle("LOGIN");
  setVisible(true);

  //set the labels and TextField
  JPanel loginPage = new JPanel();
  loginPage.setLayout(new GridLayout(4, 1));
  this.add(loginPage);

  JLabel un = new JLabel("       Username");
  loginPage.add(un);
  username = new JTextField(20);
  loginPage.add(username);

  
     
  //
  
  JLabel pw = new JLabel("       Password");
  loginPage.add(pw);
  password = new JPasswordField(10);
  password.addActionListener(this);
  password.setActionCommand("OK");
  loginPage.add(password);

  JLabel tp = new JLabel("       Type");
  loginPage.add(tp);
  type = new JComboBox < > ();
  type.addItem("System Admin");
  type.addItem("Manager");
  type.addItem("Tech Support");
  loginPage.add(type);

  JPanel bottonLogin1 = new JPanel();
  loginPage.add(bottonLogin1);

  JButton jv = new JButton("Forgot your password?");
  jv.addActionListener(this);
  jv.setActionCommand("forgot");
  bottonLogin1.add(jv);

  JPanel bottonLogin2 = new JPanel();
  loginPage.add(bottonLogin2);

  JButton login = new JButton("Login!");
  login.addActionListener(this);
  login.setActionCommand("login");
  bottonLogin2.add(login);

  repaint();
  validate();
 }

 //Class to loguin with the database
 public void loginWithDatabase() {

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

   String un = username.getText();
   String pw = password.getText();
   String tp = (String) type.getSelectedItem();

   if (stmt.execute("select * from company where username = '" + un + "' and " + "password = '" + pw + "' and " + "type = '" + tp + "'")) {
    rs = stmt.getResultSet();
   }

   // loop over results

   while (rs.next()) {    
    tp = rs.getString("type");

    if (tp.equals("System Admin")) {
     SystemAdmin adm = new SystemAdmin();

    } else if (tp.equals("Manager")) {
     Manager mngr = new Manager();

    } else {
     TechSupport tech = new TechSupport();

    }
   }

  } catch (SQLException ex) {
   // handle any errors
   System.out.println("SQLException: " + ex.getMessage());
   System.out.println("SQLState: " + ex.getSQLState());
   System.out.println("VendorError: " + ex.getErrorCode());
  }
 }


 @Override
 public void actionPerformed(ActionEvent e) {
  //Listener to Loging
  if (e.getActionCommand().equals("login")) {
   loginWithDatabase();
   setVisible(false);
  }
  //Listener to go to the page Forgot Password
  else if (e.getActionCommand().equals("forgot")) {
   ResetPassword rp = new ResetPassword(this);
  }
 }
}