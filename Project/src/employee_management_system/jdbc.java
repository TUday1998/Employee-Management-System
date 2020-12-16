
package employee_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jdbc {
     public static void main(String[] arge) throws ClassNotFoundException,SQLException{
        Scanner sc = new  Scanner(System.in);
        int choice = 0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
         try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employee?" ,"root",""))  {
             
             do
             {
                 System.out.println("          **************WELCOME TO Employee MANAGEMENT SYSTEM**************         ");
                 System.out.println(" ");
                 System.out.println("                    ********************MENU********************");
                 System.out.println("");
                 System.out.println("                            Press '1' For CREATE The VALUES");
                 System.out.println("");
                 System.out.println("                            Press '2' For READ The VALUES");
                 System.out.println("");
                 System.out.println("                            Press '3' For UPDATE  The VALUES");
                 System.out.println("");
                 System.out.println("                            Press '4' For DELETE The VALUES");
                 System.out.println("");
                 System.out.println("                            Press '5' For EXIT The VALUES");
                 System.out.println("");
                 System.out.println("                            Enter Your Choice : ");
                 choice = sc.nextInt();
                 switch (choice){
                     case 1:
                         try{
                              System.out.println("Enter Employee ID : ");
                              int id = sc.nextInt();
                              System.out.println("Enter Employee  Name : ");
                              String name = sc.next();
                              System.out.println("Enter Employee Age : ");
                              int age = sc.nextInt();
                              System.out.println("Enter Employee Department : ");
                              String department = sc.next();
                              System.out.println("Enter Salary : ");
                              int salary = sc.nextInt();
                              String sql = "Insert Into empInfo values (?,?,?,?,?);";
                              PreparedStatement stmt = con.prepareStatement(sql);
                              stmt.setInt(1, id);
                              stmt.setString(2, name);
                              stmt.setInt(3, age); 
                              stmt.setString(4, department);
                              stmt.setInt(5, salary);
                              stmt.execute();
                              System.out.println("Successfully Inserted Employee Details ");
                              System.out.println("");
                         }
                         catch (SQLException e)
                         {
                             System.out.println(e.getMessage());
                         }
                         break;
                     case 2 :
                         try
                         {
                             System.out.println("Enter Employee ID For Read The Record : ");
                             Scanner input = new Scanner(System.in);
                             int id = input.nextInt();
                             String sql = "SELECT * FROM empInfo WHERE id=?;";
                             PreparedStatement stmt = con.prepareStatement(sql);
                             stmt.setInt(1, id);
                             ResultSet rs = stmt.executeQuery();
                             while (rs.next())
                             {
                                 System.out.println("Employee ID : " + rs.getInt(1));
                                 System.out.println("Employee Name : " + rs.getString(2));
                                 System.out.println("Employee Age : " + rs.getInt(3));
                                 System.out.println("Employee Department : " + rs.getString(4));
                                 System.out.println("Employee Salary : " + rs.getInt(5));  
                             }
                         }
                         catch (SQLException e)
                         {
                             System.out.println(e.getMessage());
                         }
                         break;
                     case 3:
                         try
                         {
                             System.out.println("Enter Employee Name : ");
                             String name =sc.next();
                             System.out.println("Enter Employee New Salary : ");
                             int salary =sc.nextInt();
                             String sql = "Update empinfo set salary =? WHERE name=?;";
                             PreparedStatement statement = con.prepareStatement(sql);
                             statement.setInt(1, salary);
                             statement.setString(2, name);
                             int rowsUpdated = statement.executeUpdate();
                             if(rowsUpdated > 0)
                             {
                                  System.out.println("Successfully Updated Record ");
                             }
                             else
                             {
                                  System.out.println("There is No Such Record ");
                             }
                             
                         }
                         catch(SQLException e)
                         {
                              System.out.println(e.getMessage());
                         }
                         break;
                     case 4:
                     try
                     {
                         
                             System.out.println("Enter Employee Name for Delete the Record : ");
                             String name =sc.next();
                             String sql = "DELETE FROM empinfo WHERE name =?;";
                             PreparedStatement statement = con.prepareStatement(sql);
                             statement.setString(1, name);
                             int rowsUpdated = statement.executeUpdate();
                             if(rowsUpdated > 0)
                             {
                                  System.out.println("Successfully Deleted Record ");
                             }
                             else
                             {
                                  System.out.println("There is No Such Record Found");
                             }
                             
                         }
                         catch(SQLException e)
                         {
                              System.out.println(e.getMessage());
                         }
                         break;
                     case 5:
                         System.out.println(",,,,,,,,,,,EXIT THE DATABASE,,,,,,,,,,");
                         break;
                     default:
                         System.out.println(",,,,,,,,,,,Select The Correct Option,,,,,,,,,,");
                         break;
                         
                     }
                 }
             while(choice !=5);
             System.out.println(",,,,,,,,,,,THANK YOU,,,,,,,,,,");
             }
         }
        catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE,null, ex);
    }
    
    }
}

