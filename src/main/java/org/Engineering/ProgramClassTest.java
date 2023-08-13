package org.Engineering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class ProgramClassTest {
	
	  String url = "jdbc:mysql://localhost:3306/PalletePlace";
	  String username = "root";
	  String password = "password1";
	  String set;
	  public Connection connection = null;
	  public Statement statement;
	  public ResultSet resultSet;
	  public ResultSetMetaData metaData;
	
	
	  public void addPalleteinPlaceATest(int idPallete, int weightPallete, int code) {
		    try {
		       
		        PreparedStatement checkStmtA = connection.prepareStatement("SELECT COUNT(DISTINCT Cod) FROM TestAddPlaces");
		        ResultSet checkResultA = checkStmtA.executeQuery();
		        checkResultA.next();
		        int distinctCountB = checkResultA.getInt(1);
		        String DateANDTime = "DateANDTime";

		        if (distinctCountB < 3) {
		            String columnToUpdate = null;
		            if (code == 111) {
		                columnToUpdate = "1a";
		            } else if (code == 222) {
		                columnToUpdate = "2a";
		            } else if (code == 333) {
		                columnToUpdate = "3a";
		            }

		            String columnToUpdate2 = null;
		            switch (idPallete % 3) {
		                case 0:
		                    columnToUpdate2 = "1a";
		                    break;
		                case 1:
		                    columnToUpdate2 = "2a";
		                    break;
		                case 2:
		                    columnToUpdate2 ="3a";
		                    break;
		            }

		            String checkQuery = "SELECT " + columnToUpdate + " FROM TestAddPlaces WHERE " + columnToUpdate + " IS NOT NULL";
		            PreparedStatement checkPlaceStmt = connection.prepareStatement(checkQuery);
		            ResultSet checkPlaceResult = checkPlaceStmt.executeQuery();
		            if (checkPlaceResult.next()) {
		                System.out.println();
		                System.out.println("Miejsce jest już zajęte!");
		                System.out.println();
		                System.out.println("------------------------------------------------------------------");
		            } else {
		                String insertQuery = String.format("INSERT INTO TestAddPlaces (Cod, %s, %s,RackCode) VALUES (?, ?, ?, ?)", columnToUpdate, DateANDTime, code);
		                PreparedStatement pstmt = connection.prepareStatement(insertQuery);
		                pstmt.setInt(1, idPallete);
		                pstmt.setDouble(2, weightPallete);
		                pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		                pstmt.setInt(4, code);
		                pstmt.executeUpdate();

		                ConnectionWithDataBase.addCompletly();               
		            }
		        } else {
		            ConnectionWithDataBase.noFreePlace();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

}
