package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            String sql = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                //Process is kind of rinse and repeat, Passing arguements into statements
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                int affectedRows = ps.executeUpdate();
                result = affectedRows > 0;   // Verifies insertion was sucessful
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE 
                //Again, rinse and repeat through this file
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                int affectedRows = ps.executeUpdate();
                result = affectedRows > 0;
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
            
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                //Rinse and Repeat
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                int affectedRows = ps.executeUpdate();
                result = affectedRows > 0;
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            String sql = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                rs = ps.executeQuery();
                result = DAOUtility.getResultSetAsJson(rs);
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}