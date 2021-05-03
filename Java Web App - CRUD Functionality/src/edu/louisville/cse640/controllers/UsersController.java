package edu.louisville.cse640.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UsersController
{
    private Connection dbConnection = null;
    private String      fullName     = "";
    private String 		users 		 = "";
    private String 		batting 	 = "";
    private String 		fielding 	 = "";
    
    private Map<String,String> usersIDMap = null;
    
    public Map<String, String> getUsersIDMap()
    {
        return usersIDMap;
    }
    public void populateUsersIDMap()
    {
        ResultSet rs = null;
        usersIDMap = new HashMap<String,String>();
        String template = "SELECT * FROM Users";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            while (rs.next())
            {
                usersIDMap.put(rs.getString("ID"), (rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("LNAME")));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    
    public String getFullName()
    {
        return fullName;
    }
    
    public void setUsers(String users)
    {
        this.users = users;
    }
    /**
     * 
     */
    public UsersController(Connection dbConnection)
    {
        this.dbConnection = dbConnection;
    }
    public int insertUser(String id, String username, String password, String fname, String lname)
    {
        int rc = 0;
        String template = "INSERT INTO USERS VALUES(?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, fname);
            ps.setString(5, lname);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rc);
    }
    public int updateUser(String id, String username, String password, String fname, String lname, String jersey)
    {
        int rc = 0;
        String template = "UPDATE USERS SET ID = ?, USERNAME = ?, PASSWORD = ?, "
        		+ "FNAME = ?, LNAME = ?"
        		+ " where ID = ?";
        try
        {
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, fname);
            ps.setString(5, lname);
            ps.setString(6, jersey);
            
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rc);
    }
    public int deleteUser(String username, String password)
    {
        int rc = 0;
        String template = "DELETE FROM USERS WHERE USERNAME=? and PASSWORD=?";
        try
        {
            System.out.println("About to delete user " + username + " with password " + password);
            PreparedStatement ps = dbConnection.prepareStatement(template);
            ps.setString(1, username);
            ps.setString(2, password);
            rc = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rc);
    }
    public boolean findUser(String user, String password)
    {
        System.out.println("I will look for user");
        boolean rc = false;
        fullName     = "";
        String template = "SELECT * FROM USERS WHERE USERNAME = '" + user + "' AND PASSWORD = '" + password + "'";
        System.out.println(template);
        try
        {
            Statement s = dbConnection.createStatement();
            ResultSet rs = s.executeQuery(template);
            if (rs.next())
            {
                System.out.println("Found user");
                rc = true;
                setFullName(rs.getString("FNAME") + " " + rs.getString("LNAME"));
            }
            else
            {
                System.out.println("Did not find  user");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rc);
    }
    public ResultSet getUsersList(String keyword)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERS";
        
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rs);
    }
    
    
    public String getUsersList()
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERS";
        users		 = "";
        
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            while (rs.next())
            {
                users += rs.getString("FNAME") + " " + rs.getString("LNAME") + "<br>";
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return users;
    }
    
    public String getBatting()
    {
        ResultSet rs = null;
        String template = "SELECT * FROM BATTING";
        
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            
            
            while (rs.next())
            {
            	batting += rs.getString("LNAME") + ": (Hits) -> " + rs.getString("HITS") + "<br>";
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return batting;
    }
    
    public String getFielding()
    {
        ResultSet rs = null;
        String template = "SELECT * FROM FIELDING";
        
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
            
            
            while (rs.next())
            {
            	fielding += rs.getString("LNAME") + ": (Errors) -> " + rs.getString("ERRORS") + "<br>";
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return fielding;
    }
    
    public ResultSet getUserRecord(String id, String password)
    {
        ResultSet rs = null;
        String template = "SELECT * FROM USERS WHERE ID= '" + id + "' AND PASSWORD = '" + password + "'";
        try
        {
            Statement s = dbConnection.createStatement();
            rs = s.executeQuery(template);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return (rs);
    }
}

