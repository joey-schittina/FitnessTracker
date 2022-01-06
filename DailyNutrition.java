
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.io.Console;


public class DailyNutrition {
    static Color myBlue = new Color(0,100,200);
    public static void main(String[] args) {
        JFrame nutFrame = new JFrame();
            nutFrame.setTitle("PirateJoe13's Fitness Tracker");
            nutFrame.setResizable(false);
            nutFrame.setLayout(new GridLayout());
            nutFrame.setSize(500,600);
            
            JPanel nutPanel = new JPanel();
            nutPanel.setBackground(myBlue);
            nutPanel.setBounds(0,0,500,600);
            nutPanel.setLayout(null);

            nutFrame.add(nutPanel);
            nutFrame.setVisible(true);
    }
    
}
