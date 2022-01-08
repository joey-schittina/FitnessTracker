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
        static int monthInt;
        static String year;
        static String fulldate;
        static Color myBlue = new Color(0,100,200);

        public static void main(String[] args){


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
                    monthInt = monthBox.getSelectedIndex()+1;
                    fulldate = year+"-"+monthInt+"-"+day;
                    System.out.println(fulldate);

                    try{    
                        Connection con=DriverManager.getConnection(  
                        "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
                        Statement stmt=con.createStatement(); 
                        ResultSet rs = stmt.executeQuery("select date from dates where date=\""+fulldate+"\"");
                        rs.next();
                        if (rs.getRow()==0){
                            int result = stmt.executeUpdate("insert into dates values(\""+fulldate+"\",null,null,null)");
                        }
                        con.close();  
                        }catch(Exception e2){ System.out.println(e);}



                    JFrame mainFrame = new JFrame();
                    mainFrame.setTitle("PirateJoe13's Fitness Tracker");
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

                    JButton addMeal = new JButton("Log a meal");
                    JButton newInfo = new JButton("Input New Food Info");
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

                    newInfo.addActionListener(new ActionListener() {
                
                        public void actionPerformed(ActionEvent e) {
                            JFrame nutFrame = new JFrame();
                            nutFrame.setTitle("PirateJoe13's Fitness Tracker");
                            nutFrame.setResizable(false);
                            nutFrame.setLayout(null);
                            nutFrame.setSize(500,600);
                                
                            JPanel nutPanel = new JPanel();
                            nutPanel.setBackground(myBlue);
                            nutPanel.setBounds(0,0,500,600);
                            nutPanel.setLayout(null);

                            JLabel guide = new JLabel("Enter New Food Name and Calories");
                            guide.setBounds(50,100,400,50);
                            guide.setHorizontalAlignment(JLabel.CENTER);
                            guide.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
                            guide.setForeground(Color.white);

                            JTextField name = new JTextField("Food Name");
                            JTextField cals = new JTextField("Calories");
                            JButton enter = new JButton("Submit");

                            enter.setBounds(200,300,100,30);
                            name.setBounds(100,250,200,30);
                            cals.setBounds(325,250,100,30);
                            
                            nutPanel.add(enter);
                            nutPanel.add(name);
                            nutPanel.add(cals);
                            nutPanel.add(guide);

                            enter.addActionListener(new ActionListener() {
                
                                public void actionPerformed(ActionEvent e) {
                                    String nameString = name.getText();
                                    String calsInt = cals.getText();

                                    try{    
                                        Connection con=DriverManager.getConnection(  
                                        "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
                                        Statement stmt=con.createStatement(); 
                                        int result = stmt.executeUpdate("insert into foods values(\""+nameString+"\", "+calsInt+")");
                                        con.close();  
                                        }catch(Exception e2){ System.out.println(e);}
                                    }
                                });



                            nutFrame.add(nutPanel);
                            nutFrame.setVisible(true);

                        }
                    });

                    addMeal.addActionListener(new ActionListener() {
                
                        public void actionPerformed(ActionEvent e) {

                            JFrame inpFrame = new JFrame();
                            inpFrame.setTitle("PirateJoe13's Fitness Tracker");
                            inpFrame.setResizable(false);
                            inpFrame.setLayout(null);
                            inpFrame.setSize(500,600);
                                
                            JPanel inpPanel = new JPanel();
                            inpPanel.setBackground(myBlue);
                            inpPanel.setBounds(0,0,500,270);
                            inpPanel.setLayout(null);

                            JLabel guide2 = new JLabel("List of Foods in Database:");
                            guide2.setBounds(50,230,400,50);
                            guide2.setHorizontalAlignment(JLabel.CENTER);
                            guide2.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
                            guide2.setForeground(Color.white);
                            inpPanel.add(guide2);

                            JLabel guide = new JLabel("Input Food and Time Eaten:");
                            guide.setBounds(50,50,400,50);
                            guide.setHorizontalAlignment(JLabel.CENTER);
                            guide.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
                            guide.setForeground(Color.white);

                            JTextField name = new JTextField("Food Eaten:");
                            JTextField mealTime = new JTextField("Time Eaten:");
                            JButton enter = new JButton("Submit");

                            enter.setBounds(200,200,100,30);
                            name.setBounds(100,150,150,30);
                            mealTime.setBounds(275,150,150,30);

                            JPanel itemsPanel = new JPanel();
                            itemsPanel.setBackground(myBlue);
                            itemsPanel.setBounds(0,270,500,330);
                            itemsPanel.setLayout(new GridLayout(20,5));
                            

                            try{

                                
                                Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
            
                                Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
                                ResultSet rs = stmt.executeQuery("select * from foods");
                                rs.last();
                                int rows=rs.getRow();
                                rs.first();
                                int newrows=(int)Math.ceil(rows/2.0);
                                System.out.println(newrows);
                                itemsPanel.setLayout(new GridLayout(newrows,2));
                                JLabel items[] = new JLabel[rows];
                                for(int i=0; i<rows; i++){
                                    items[i]=new JLabel(rs.getString(1)+" is/are "+Integer.toString(rs.getInt(2))+" calories.");
                                    items[i].setForeground(Color.white);
                                    itemsPanel.add(items[i]);
                                    rs.next();
                                }
                                



                            }catch(Exception e2){ System.out.println(e);}
                            
                            inpPanel.add(enter);
                            inpPanel.add(name);
                            inpPanel.add(mealTime);
                            inpPanel.add(guide);

                            enter.addActionListener(new ActionListener() {
                
                                public void actionPerformed(ActionEvent e) {

                                    String foodsString = name.getText();
                                    String timeString = mealTime.getText();
                                    System.out.println(foodsString);
                                    System.out.println(timeString);
                                    System.out.println(fulldate);
                                    try{

                                
                                        Connection con=DriverManager.getConnection(  
                                        "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
                    
                                        Statement stmt=con.createStatement(); 
                                        int res = stmt.executeUpdate("insert into meals values(\""+fulldate+"\",\""+foodsString+"\",\""+timeString+"\")");


                                    }catch(Exception e2){ System.out.println(e);}
                                }
                            });
                            inpFrame.add(inpPanel);
                            inpFrame.setVisible(true);
                            inpFrame.add(itemsPanel);
                            itemsPanel.setVisible(true);

                  

                        }
                    });



                    todaysMeals.addActionListener(new ActionListener() {
                
                        public void actionPerformed(ActionEvent e) {

                            try{    
                                Connection con=DriverManager.getConnection(  
                                "jdbc:mysql://localhost:3306/fitness_app","root","Slapshot1515");  
                                Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
                                ResultSet rs = stmt.executeQuery("select * from meals where date=\""+fulldate+"\"");

                                rs.last();
                                int rows = rs.getRow();
                                rs.first();



                                JFrame todaysFrame = new JFrame();
                                todaysFrame.setTitle("PirateJoe13's Fitness Tracker");
                                todaysFrame.setResizable(false);
                                todaysFrame.setLayout(null);
                                todaysFrame.setSize(500,600);
                                todaysFrame.setBackground(myBlue);
                                    
                                JPanel titlePanel = new JPanel();
                                titlePanel.setBackground(myBlue);
                                titlePanel.setBounds(0,0,500,100);
                                titlePanel.setLayout(null);

                                JPanel listPanel = new JPanel();
                                listPanel.setBackground(myBlue);
                                listPanel.setBounds(0,100,500,20*rows);
                                listPanel.setLayout(new GridLayout(rows+1,2));

                                JPanel excessPanel = new JPanel();
                                excessPanel.setBackground(myBlue);
                                excessPanel.setBounds(0,100+20*rows,500,500-20*rows);
                                excessPanel.setLayout(new GridLayout(rows+1,2,10,40));

                                JLabel titleLabel = new JLabel("Meals Eaten Today");
                                titleLabel.setBounds(50,20,400,50);
                                titleLabel.setHorizontalAlignment(JLabel.CENTER);
                                titleLabel.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE,20));
                                titleLabel.setForeground(Color.white);

                                JLabel[] mealArr = new JLabel[rows+1];
                                JLabel[] timeArr = new JLabel[rows+1];

                                mealArr[0]=new JLabel("List of Items Eaten Today:");
                                timeArr[0]=new JLabel("Time Meal Was Eaten:");

                                mealArr[0].setForeground(Color.white);
                                timeArr[0].setForeground(Color.white);

                                listPanel.add(mealArr[0]);
                                listPanel.add(timeArr[0]);

                                for(int i=0;i<rows;i++){
                                    mealArr[i+1]=new JLabel(rs.getString(2));
                                    timeArr[i+1]=new JLabel(rs.getString(3));
                                    //mealArr[i+1].setSize(50,60);
                                    //timeArr[i+1].setSize(50,60);
                                    listPanel.add(mealArr[i+1]);
                                    listPanel.add(timeArr[i+1]);
                                    rs.next();
                                }
                                
                                titlePanel.add(titleLabel);
                    

                                todaysFrame.add(excessPanel);
                                todaysFrame.add(listPanel);
                                todaysFrame.add(titlePanel);
                                todaysFrame.setVisible(true);
                                con.close();
                            }catch(Exception e2){ System.out.println(e);}

                        }
                    });


                    
                    


                }
            });

            logPanel.add(start);
            logFrame.setVisible(true);




            



            



        }
    }

