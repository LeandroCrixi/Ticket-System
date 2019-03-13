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
import javax.swing.JCheckBox;
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
public class SystemAdmin extends JFrame implements ActionListener {
    
    String id = null;
    String username = null;
    String password = null;
    String type = null;
    
    public SystemAdmin(LoginGUI ref){
   
    }
    
    public SystemAdmin(){
        
        setSize(400,300);
        setTitle("System Admin");
        setVisible(true);
        
            this.setLayout(new BorderLayout());
        
            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(2, 1));
            
            JPanel topButtons = new JPanel();
            buttons.add(topButtons);
            
            JPanel bottonButtons = new JPanel();
            buttons.add(bottonButtons);
            
            JButton add = new JButton ("New User"); //Creating the button
            topButtons.add(add); // Adding the button to the frame
            add.addActionListener(this); // turn the listening on
            add.setActionCommand("new"); // give it an ID
            
            JButton change = new JButton ("Change Account"); //Creating the button
            topButtons.add(change); // Adding the button to the frame
            change.addActionListener(this); // turn the listening on
            change.setActionCommand("change"); // give it an ID
            
            JButton del = new JButton ("Delete"); //Creating the button
            topButtons.add(del); // Adding the button to the frame
            del.addActionListener(this); // turn the listening on
            del.setActionCommand("del"); // give it an ID
            
            JButton update = new JButton("Update"); //Creating the button
            bottonButtons.add(update); // Adding thr button to the frame
            update.addActionListener(this); // turn the listener on
            update.setActionCommand("update"); // give it an ID
            
            JButton logout = new JButton("LogOut"); //Creating the button
            bottonButtons.add(logout); // Adding thr button to the frame
            logout.addActionListener(this); // turn the listener on
            logout.setActionCommand("logout"); // give it an ID
            
            this.add(buttons, BorderLayout.PAGE_END);
            
            ViewAdmin();
        
        repaint();
        validate();
    }
    
    public void ViewAdmin(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?" +
                            "user=root&password=");
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from company;");
            
            String[][] data = new String [10][4];
            int counter = 0;
            
            while(rs.next()){
    	    	String id = rs.getString("id");
    	       	data[counter][0] = id;
    	      
    	        String username = rs.getString("username");
    	    	data[counter][1] = username;
                
                String password = rs.getString("password");
    	    	data[counter][2] = password;
                
                String type = rs.getString("type");
    	    	data[counter][3] = type;
    	        
    	        counter = counter + 1;
    	    }
            
            String[] colNames = {"ID","Username", "Password", "Type"};
            
            JTable table = new JTable(data, colNames);            
            
            JScrollPane sr = new JScrollPane(table);
            
            this.add(sr);
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("logout")){
        int n = JOptionPane.showConfirmDialog(this,  
        "Are you sure you want to exit?","", JOptionPane.YES_NO_OPTION);
                        
        if(n == JOptionPane.YES_OPTION) System.exit(0);
    }
        else if (e.getActionCommand().equals("new")){
        NewUser u = new NewUser(this);
    }
        else if (e.getActionCommand().equals("del")){
            DeleteUser del = new DeleteUser(this);
        }
        else if (e.getActionCommand().equals("change")){
            ChangeUserAccount cua = new ChangeUserAccount(this);
        }
        else if (e.getActionCommand().equals("update")){
            setVisible(false);
            new SystemAdmin();
        }
   }
    
}
