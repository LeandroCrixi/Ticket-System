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
public class NewUser extends JFrame implements ActionListener {
    
    JTextField username = null;
    JTextField password = null;
    JComboBox<String> type = null;
    
    public NewUser(SystemAdmin ref){
        
        setSize(300,200);
        setTitle("New User");
        setVisible(true);
        
            JPanel creatingUser = new JPanel();
            creatingUser.setLayout(new GridLayout(4,1));
            this.add(creatingUser);
                        
            JLabel name = new JLabel("Username");
            creatingUser.add(name);
            username = new JTextField(20);
            creatingUser.add(username);
            
            JLabel pass = new JLabel("Password");
            creatingUser.add(pass);
            password = new JTextField(20);
            creatingUser.add(password);
            
            JLabel tp = new JLabel("Type");
            creatingUser.add(tp);
            type = new JComboBox<>();
            type.addItem("System Admin");
            type.addItem("Tech Support");
            type.addItem("Manager");
            creatingUser.add(type);
                  
            JPanel bottonNew = new JPanel();
            creatingUser.add(bottonNew);
            
            JButton create = new JButton ("Save New User"); //Creating the button
            bottonNew.add(create); // Adding the button to the frame
            create.addActionListener(this); // turn the listening on
            create.setActionCommand("Create"); // give it an ID
            
        
        repaint();
        validate();
    }
    
    public void registerNewUser(){
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

	    	    String us = username.getText();
                    String ps = password.getText();
                    String tp = (String) type.getSelectedItem();
	    	    
	    	    if (stmt.execute("INSERT INTO `logingui`.`company` (`username`,"
                            + " `password`, `type`) VALUES ('"+us+"',"
                            + "'"+ps+"', '"+tp+"');")) {
	    	        
	    	    }
                    JOptionPane.showMessageDialog(this, "New User added to the system!");

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
        
        registerNewUser();
        setVisible(false);
    
    }
    
}
