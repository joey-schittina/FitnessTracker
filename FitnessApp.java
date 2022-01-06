    import javax.swing.*;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.awt.*;
    import java.sql.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.Console;
    import java.awt.Color;
    import java.awt.Font;
    import java.sql.Date;



    public class FitnessApp{
        
        
           

        static String day;
        static String month;
        static String year;
        static Color myBlue = new Color(0,100,200);

        public static void main(String[] args){
            try{    
                Connection con=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from meals");  
                while(rs.next())  
                System.out.println(rs.getDate(1)+rs.getString(2)+rs.getString(3));  
                con.close();  
                }catch(Exception e){ System.out.println(e);}

            JFrame logFrame = new JFrame();
            logFrame.setTitle("PirateJoe13's Fitness Tracker");
            logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            logFrame.setResizable(false);
            logFrame.setLayout(null);
            logFrame.setSize(500,600);
            
            JPanel logPanel = new JPanel();
            logPanel.setBackground(myBlue);
            logPanel.setBounds(0,0,500,600);
            logPanel.setLayout(null);

            logFrame.add(logPanel);

            JLabel helloText = new JLabel("Welcome to PirateJoe13's Calorie Tracker!");
            helloText.setBounds(50,50,450,50);
            helloText.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
            helloText.setForeground(Color.white);
            logPanel.add(helloText);

            JLabel DateLabel = new JLabel("Select date to get started");
            DateLabel.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,15));
            DateLabel.setForeground(Color.white);
            DateLabel.setBounds(160,150,400,50);
            logPanel.add(DateLabel);


            String[] monthList = {"January", "February","March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            JComboBox monthBox = new JComboBox<>(monthList);
            monthBox.setBounds(80,250,100,30);
            logPanel.add(monthBox);

            String[] days = new String[31];
            for(int n=1;n<32;n++){
                days[n-1]=Integer.toString(n);
            }
            JComboBox dayBox = new JComboBox<>(days);
            dayBox.setBounds(190,250,100,30);
            logPanel.add(dayBox);

            String[] yearList = {"2021","2022","2023","2024"};
            JComboBox yearBox = new JComboBox<>(yearList);
            yearBox.setBounds(300,250,100,30);
            logPanel.add(yearBox);

            JButton start = new JButton("Start");
            start.setBounds(200,350,100,30);
            start.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    
                    day=dayBox.getSelectedItem().toString();
                    month=monthBox.getSelectedItem().toString();
                    year=yearBox.getSelectedItem().toString();

                    JFrame mainFrame = new JFrame();
                    mainFrame.setTitle("PirateJoe13's Fitness Tracker");
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainFrame.setResizable(false);
                    mainFrame.setLayout(null);
                    mainFrame.setSize(500,600);
                    
                    JPanel mainPanel = new JPanel();
                    mainPanel.setBackground(myBlue);
                    mainPanel.setBounds(0,0,500,600);
                    mainPanel.setLayout(null);

                    JLabel myDate = new JLabel(month+" "+day+", "+year);
                    myDate.setBounds(100,50,300,50);
                    myDate.setHorizontalAlignment(JLabel.CENTER);
                    myDate.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
                    myDate.setForeground(Color.white);

                    JButton addMeal = new JButton("Add Meal");
                    JButton newInfo = new JButton("Input New Nutritional Info");
                    JButton todaysMeals = new JButton("View Today's Nutritional Info");

                    todaysMeals.setBounds(150,150,200,30);
                    addMeal.setBounds(150,200,200,30);
                    newInfo.setBounds(150,250,200,30);  

                    mainPanel.add(newInfo);
                    mainPanel.add(addMeal);
                    mainPanel.add(myDate);
                    mainPanel.add(todaysMeals);

                    mainFrame.add(mainPanel);
                    mainFrame.setVisible(true);

                    
                    


                }
            });

            logPanel.add(start);
            logFrame.setVisible(true);




            



            



        }
    }

