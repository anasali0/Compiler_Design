package compiler_design;

/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author Jameel Emad Matar 20171660
 * 
**/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

class Compiler_Design extends JFrame implements ActionListener{
    
    Container container; 
    JButton btnSelect , btnClear;
    JTable showTable;
    JPanel panel;
    String[] keywords = {"+" , "*" , "<>" , ":=" , ";" , "," , ".." , "ARRAY" , "OF" , "." , "(" , ")" , "]" , "[" , "-" , "/" , "PROGRAM" , "INTEGER" , "BOOLEAN" , "BEGIN" , "END" , "PROCEDURE" , "IF" , "THEN" , "ELSE" , "FOR" , "TRUE" , "FALSE" , "VAR" , ":" , "DO" , "READ" , "WRITE" , "MOD" , "DIV" , "AND" , "OR" , "NOT" , ">" , "<" , ">=" , "<=" , "="};
    int n = 0;
    String project;
    String[] line;   
    Object rows[][];
    
    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    public Compiler_Design(){
        super("Compiler Design Project");
        container = getContentPane();
        container.setLayout(null);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(100 , 80 , 420 , 315);
        rows = new Object[100][4];
        btnSelect = new JButton("Select Code");
        btnSelect.setBounds(160 , 450 , 120 , 40);
        btnClear = new JButton("Clear Table");
        btnClear.setBounds(300 , 450 , 120 , 40);
        String title[]={"Token No." , "Name" , "Token Type" , "Line Number"};
        showTable = new JTable(rows , title);
        JTableHeader th = showTable.getTableHeader();
        th.setBackground(Color.gray);
        JScrollPane sp = new JScrollPane(showTable);
        panel.add(sp);
        btnSelect.addActionListener(this);
        container.add(btnSelect);
        btnClear.addActionListener(this);
        container.add(btnClear);
        container.add(panel);
        panel.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600 , 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnSelect){
            Open open = new Open();
            project = open.getContents(open.openFile());
            line = project.split("\\n");
            for(int i = 0 ; i < line.length ; i++){
                getToken(line[i] , i + 1);
            }
        }else if(e.getSource() == btnClear){
            for(int i = 0 ; i <= 99 ; i++)
                for(int j = 0 ; j <= 3 ; j++)
                    rows[i][j] = "";
            panel.revalidate();
            panel.repaint();
            n = 0;
        }
    }
    
    public void getToken(String s , int i){
        String s1 = "";
        s = s.toLowerCase();
        for(int i1 = 0 ; i1 < s.length(); i1++){
            if(Character.isLetterOrDigit(s.charAt(i1))){
                s1 += s.charAt(i1);
            }
            else{
                if(Arrays.asList(keywords).contains(s1)){
                    rows[n][0] = n;
                    rows[n][1] = s1;
                    rows[n][2] = "Keyword";
                    rows[n][3] = i;
                    n++;
                    panel.repaint();
                }
                else if(!s1.equals("")){
                    try{
                        Integer.parseInt(s1);
                        rows[n][0] = n;
                        rows[n][1] = s1;
                        rows[n][2] = "Number";
                        rows[n][3] = i;
                        n++;
                        panel.repaint();
                    }catch(NumberFormatException ex){
                        rows[n][0] = n;
                        rows[n][1] = s1;
                        rows[n][2] = "Identifier";
                        rows[n][3] = i;
                        n++;
                        panel.repaint();
                    }
                }
                if(Arrays.asList(keywords).contains("" + s.charAt(i1))){
                    rows[n][0] = n;
                    rows[n][1] = s.charAt(i1);
                    rows[n][2] = "KeyWord";
                    rows[n][3] = i;
                    n++;
                    panel.repaint();
                }
                s1 = "";
            }
        }
    }
    /**
     *
     * @author Anas Ali banat 20172121
     * @author Mohamed zeyad abu hassanien 20174100
     * @author 
     * 
    **/   
    public static void main(String[] args) {
        Compiler_Design compiler_Design = new Compiler_Design();
    }    
}
/**
 *
 * @author Anas Ali banat 20172121
 * @author Mohamed zeyad abu hassanien 20174100
 * @author 
 * 
**/
