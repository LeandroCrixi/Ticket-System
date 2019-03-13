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
public class NewTicket extends JFrame implements ActionListener {
    
    //Global variables
    JTextField epoch = null;
    JTextField cusName = null;
    JTextField techId = null;
    JTextArea comment = null;
    JComboBox<String> clas = null;
    
    public NewTicket(TechSupport ref){
        
        //set the frame
        setSize(500,250);
        setTitle("New Ticket");
        setVisible(true);
        
            //set the labels and TextField
            JPanel creatingTicket = new JPanel();
            creatingTicket.setLayout(new GridLayout(6,1));
            this.add(creatingTicket);
            
            JLabel epc = new JLabel("Ticket");
            creatingTicket.add(epc);
            epoch = new JTextField(20);
            creatingTicket.add(epoch);
            epoch.setEditable(false);
            epoch.setText(Long.toString(Instant.now().getEpochSecond()));
                        
            JLabel name = new JLabel("Customer Name");
            creatingTicket.add(name);
            cusName = new JTextField(20);
            creatingTicket.add(cusName);
            
            JLabel techSupport = new JLabel("Tech Support");
            creatingTicket.add(techSupport);
            techId = new JTextField(20);
            creatingTicket.add(techId);
            
            JLabel comments = new JLabel("Comments");
            creatingTicket.add(comments);
            comment = new JTextArea();
            creatingTicket.add(comment);
            
            JLabel cls = new JLabel("Classification");
            creatingTicket.add(cls);
            clas = new JComboBox<>();
            clas.addItem("Urgent");
            clas.addItem("Normal");
            clas.addItem("Long Term");
            creatingTicket.add(clas);
                        
            
            JButton create = new JButton ("Save New Ticket"); //Creating the button
            creatingTicket.add(create); // Adding the button to the frame
            create.addActionListener(this); // turn the listening on
            create.setActionCommand("Create"); // give it an ID
            
        
        repaint();
        validate();
    }
    
    //Class to Register a New ticket
    public void RegisterNewTicket(){
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

	    	    String ep = epoch.getText();
                    String nm = cusName.getText();
                    String tc = techId.getText();
                    String cm = comment.getText();
                    String cs = (String) clas.getSelectedItem();
	    	    
	    	    if (stmt.execute("INSERT INTO `logingui`.`tickets` (`customer_name`,"
                            + " `tech_support_id`, `comment`, `classification` , `open_date`)"
                            + " VALUES ('"+nm+"','"+tc+"', '"+cm+"', '"+cs+"', '"+ep+"');")) {
	    	        
	    	    }
                    JOptionPane.showMessageDialog(this, "New Ticket added to the system!");

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
        
        RegisterNewTicket();
        setVisible(false);
    
    }
    
}
