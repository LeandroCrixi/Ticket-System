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
import javax.swing.DefaultCellEditor;
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
public class TechSupport extends JFrame implements ActionListener{
    
    public TechSupport(){
        
        setSize(900,500);
        setTitle("Tech Support");
        setVisible(true);
        
            this.setLayout(new BorderLayout());
        
            JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(2, 1));
            
            JPanel topButtons = new JPanel();
            buttons.add(topButtons);
            
            JPanel bottonButtons = new JPanel();
            buttons.add(bottonButtons);
            
            JButton add = new JButton ("New Ticket"); //Creating the button
            topButtons.add(add); // Adding the button to the frame
            add.addActionListener(this); // turn the listening on
            add.setActionCommand("new"); // give it an ID
            
            JButton close = new JButton ("Close Ticket"); //Creating the button
            topButtons.add(close); // Adding the button to the frame
            close.addActionListener(this); // turn the listening on
            close.setActionCommand("close"); // give it an ID
                                   
            JButton del = new JButton ("Delete"); //Creating the button
            topButtons.add(del); // Adding the button to the frame
            del.addActionListener(this); // turn the listening on
            del.setActionCommand("del"); // give it an ID
            
            JButton update = new JButton ("Update"); //Creating the button
            bottonButtons.add(update); // Adding the button to the frame
            update.addActionListener(this); // turn the listening on
            update.setActionCommand("update"); // give it an ID
            
            JButton logout = new JButton("LogOut"); //Creating the button
            bottonButtons.add(logout); // Adding thr button to the frame
            logout.addActionListener(this); // turn the listener on
            logout.setActionCommand("logout"); // give it an ID
            
            this.add(buttons, BorderLayout.PAGE_END);
        
            ViewTech();
        
        repaint();
        validate();
    }
    
    public void ViewTech(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = 
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1/logingui?" +
                            "user=root&password=");
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tickets;");
            
            String[][] data = new String [50][8];
            int counter = 0;
            
            while(rs.next()){
    	    	String ticket = rs.getString("ticket");
    	       	data[counter][0] = ticket;
    	      
    	        String name = rs.getString("open_date");
    	    	data[counter][1] = name;
                
                String openDate = rs.getString("tech_support_id");
    	    	data[counter][2] = openDate;
                
                String closeDate = rs.getString("customer_name");
    	    	data[counter][3] = closeDate;
                
                String techId = rs.getString("comment");
    	    	data[counter][4] = techId;
                
                String comment = rs.getString("classification");
    	    	data[counter][5] = comment;
                
                String clst = rs.getString("close_date");
    	    	data[counter][6] = clst;
                
                String status = rs.getString("status");
                data[counter][7] = status;
    	        
    	        counter = counter + 1;
    	    }
            
            String[] colNames = {"Tickets", "Open Date", "Tech Support ID",
                "Customer Name", "Comment", "Class", "Close Date", "Status"};
            
            JTable table = new JTable(data, colNames);            
            
            JScrollPane sr = new JScrollPane(table);
            
            this.add(sr);
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    TechSupport(Manager aThis) {
        new TechSupport();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("logout")){
        int n = JOptionPane.showConfirmDialog(this,  
        "Are you sure you want to exit?","", JOptionPane.YES_NO_OPTION);
                        
        if(n == JOptionPane.YES_OPTION) System.exit(0);
    }
        else if (e.getActionCommand().equals("new")){
        NewTicket t = new NewTicket(this);
    }
        else if (e.getActionCommand().equals("del")){
            DeleteTicket del = new DeleteTicket(this);
        }
        else if (e.getActionCommand().equals("close")){
            CloseTicket cls = new CloseTicket(this);
        }
        else if (e.getActionCommand().equals("update")){
            setVisible(false);
            new TechSupport();
        
    }
        }
   }
