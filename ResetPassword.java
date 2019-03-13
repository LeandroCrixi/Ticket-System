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
public class ResetPassword extends JFrame implements ActionListener {
    
    JTextField user = null;
    JTextField password = null;
    
    public ResetPassword(LoginGUI ref){
        
        JOptionPane.showMessageDialog(this, "Choose your new password!");
        
        setSize(300,200);
        setTitle("Reset Password!");
        setVisible(true);
        
            JPanel creatingUser = new JPanel();
            creatingUser.setLayout(new GridLayout(3,1));
            this.add(creatingUser);
                        
            JLabel username = new JLabel("Username");
            creatingUser.add(username);
            user = new JTextField(20);
            creatingUser.add(user);
            
            JLabel pass = new JLabel("Password");
            creatingUser.add(pass);
            password = new JTextField(20);
            creatingUser.add(password);
            
            JPanel bottonReset = new JPanel();
            creatingUser.add(bottonReset);
            
            JButton save = new JButton ("SAVE"); //Creating the button
            bottonReset.add(save); // Adding the button to the frame
            save.addActionListener(this); // turn the listening on
            save.setActionCommand("save"); // give it an ID
            
        
        repaint();
        validate();
    }
    
    public void savePassWord(){
		try {
			
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			}catch(Exception e ){}
			
			
                Connection conn = null;
	    	Statement stmt = null;
	    	ResultSet rs = null;
	    	try {
	    	    conn =
	    	       DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?user=root&password=");

	    	    // Do something with the Connection
	    	    stmt = conn.createStatement();

	    	    String us = user.getText();
                    String ps = password.getText();
	    	    
	    	    if (stmt.execute("UPDATE `logingui`.`company` "
                            + "SET `password`= '"+ps+"' WHERE  `username`= '"+us+"';")){
	    	        
	    	    }
                    JOptionPane.showMessageDialog(this, "New password saved successfully!");

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
        
        savePassWord();
        setVisible(false);
    
    }
    
}
