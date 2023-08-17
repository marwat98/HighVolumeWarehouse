package org.Engineering;

import java.io.*;


import java.nio.file.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;
import java.util.stream.Collectors;



 public class ConnectionWithDataBase implements conection {
	 
	  String url = "jdbc:mysql://localhost:3306/PalletePlace";
	  String username = "root";
	  String password = "password1";
	  String set;
	  Connection connection = null;
	  Statement statement;
	  ResultSet resultSet;
	  ResultSetMetaData metaData;
	  String placeA = "SELECT Cod, A1,A2,A3,DateANDTime FROM PlaceA";
	  String placeB = "SELECT Cod, B1,B2,B3,DateANDTime  FROM PlaceB";
	  
	 
	 
	@Override
	public void conWithTablePlaces(String set) {
		
		this.set = set;
		
		
		try {
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(set);
			metaData = resultSet.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
			for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
			
			
			
			while(resultSet.next()) {
				System.out.println("");
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
			
                }
                
             
				
			}
			resultSet.close();
			statement.close();
				
		}catch(Exception e) {
			System.out.println(e);
		}
	
	}
	
	        
	
	
	@Override
	public void addPalleteinPlaceA(int idPallete, int weightPallete, int code, String selectA, String place1a, String place2a, String place3a, String insertA) {

	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        String checkCodeQuery = "SELECT 1 FROM PlaceA WHERE Cod = ?";
	        PreparedStatement checkCodeStmt = connection.prepareStatement(checkCodeQuery);
	        checkCodeStmt.setInt(1, code);
	        ResultSet checkCodeResult = checkCodeStmt.executeQuery();
	        
	        if (checkCodeResult.next()) {
	        	System.out.println();
                System.out.println("                 * Miejsce jest już zajęte! *              ");
                System.out.println();
                System.out.println("-----------------------------------------------------------");
	        }
	       
	        PreparedStatement checkStmtA = connection.prepareStatement(selectA);
	        ResultSet checkResultA = checkStmtA.executeQuery();
	        checkResultA.next();
	        int distinctCountB = checkResultA.getInt(1);
	        String DateANDTime = "DateANDTime";

	        if (distinctCountB < 3) {

	            String columnToUpdate = null;

	            if (code == 111) {
	                columnToUpdate = place1a;
	            } else if (code == 222) {
	                columnToUpdate = place2a;
	            } else if (code == 333) {
	                columnToUpdate = place3a;
	            }

	            String columnToUpdate2 = null;
	            switch (idPallete % 3) {
	                case 0:
	                    columnToUpdate2 = place1a;
	                    break;
	                case 1:
	                    columnToUpdate2 = place2a;
	                    break;
	                case 2:
	                    columnToUpdate2 = place3a;
	                    break;
	            }

	           
	            String checkQuery = "SELECT " + columnToUpdate + " FROM PlaceA WHERE " + columnToUpdate + " IS NOT NULL";
	            PreparedStatement checkPlaceStmt = connection.prepareStatement(checkQuery);
	            ResultSet checkPlaceResult = checkPlaceStmt.executeQuery();
	            if (checkPlaceResult.next()) {
	            	System.out.println();
	                System.out.println("                 * Miejsce jest już zajęte! *              ");
	                System.out.println();
	                System.out.println("-----------------------------------------------------------");
	            } else {
	                String insertQuery = String.format(insertA, columnToUpdate, DateANDTime, code);
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

	 

	
	@Override
	public void addPalleteinPlaceB(int idPallete, int weightPallete, int code, String selectB,String place1b,String place2b,String place3b,String insertB) {
		try {
	        connection = DriverManager.getConnection(url, username, password);
	        
	        String checkCodeQuery = "SELECT 1 FROM PlaceA WHERE Cod = ?";
	        PreparedStatement checkCodeStmt = connection.prepareStatement(checkCodeQuery);
	        checkCodeStmt.setInt(1, code);
	        ResultSet checkCodeResult = checkCodeStmt.executeQuery();
	        
	        if (checkCodeResult.next()) {
	        	System.out.println();
                System.out.println("               * Podany kod jest już zajęty! *              ");
                System.out.println();
                System.out.println("-----------------------------------------------------------");
	        }
	     
	        PreparedStatement checkStmtA = connection.prepareStatement(selectB);
	        ResultSet checkResultA = checkStmtA.executeQuery();
	        checkResultA.next();
	        int distinctCountB = checkResultA.getInt(1);
	        String DateANDTime = "DateANDTime";

	        if (distinctCountB < 3) {

	            String columnToUpdate = null;

	            if (code == 101) {
	                columnToUpdate = place1b;
	            } else if (code == 202) {
	                columnToUpdate = place2b;
	            } else if (code == 303) {
	                columnToUpdate = place3b;
	            }

	            String columnToUpdate2 = null;
	            switch (idPallete % 3) {
	                case 0:
	                    columnToUpdate2 = place1b;
	                    break;
	                case 1:
	                    columnToUpdate2 = place2b;
	                    break;
	                case 2:
	                    columnToUpdate2 = place3b;
	                    break;
	            }

	           
	            String checkQuery = "SELECT " + columnToUpdate + " FROM PlaceB WHERE " + columnToUpdate + " IS NOT NULL";
	            PreparedStatement checkPlaceStmt = connection.prepareStatement(checkQuery);
	            ResultSet checkPlaceResult = checkPlaceStmt.executeQuery();
	            
	            if (checkPlaceResult.next()) {
	            	System.out.println();
	                System.out.println("               * Miejsce jest już zajęte! *              ");
	                System.out.println();
	                System.out.println("-----------------------------------------------------------");
	            } else {
	                String insertQuery = String.format(insertB, columnToUpdate, DateANDTime, code);
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

	
	@Override
	public void removePalleteAndRemoveFromFile(String rackCode, String palleteCode, int buforCode, String sql1, String place1, String place2, String place3, String sql3)  {
	    try {
	        connection = DriverManager.getConnection(url, username, password);
	        PreparedStatement statement = connection.prepareStatement(sql1);
	        statement.setString(1, rackCode);
	        statement.setString(2, palleteCode);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String weight;
	            if (resultSet.getString(place1) != null && !resultSet.getString(place1).isEmpty()) {
	                weight = resultSet.getString(place1);
	            } else if (resultSet.getString(place2) != null && !resultSet.getString(place2).isEmpty()) {
	                weight = resultSet.getString(place2);
	            } else {
	                weight = resultSet.getString(place3);
	            }
	            Timestamp dataAndTime = resultSet.getTimestamp("DateAndTime");
	            palleteCode= resultSet.getString("Cod");

	            String sql2 = "INSERT INTO Bufor (Cod, Weight, DateAndTime, BuforCode) VALUES (?, ?, ?, ?)";
	            PreparedStatement pst = connection.prepareStatement(sql2);
	            pst.setString(1, palleteCode);
	            pst.setString(2, weight);
	            pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
	            pst.setInt(4, buforCode);
	            int rowsInserted = pst.executeUpdate();

	            PreparedStatement pst2 = connection.prepareStatement(sql3);
	            pst2.setString(1, palleteCode);
	            int rowsDeleted = pst2.executeUpdate();

	        } 
	        
	        removeWithTextFile(palleteCode ,"SELECT Cod FROM PlaceA"); 
	        removeWithTextFile(palleteCode ,"SELECT Cod FROM PlaceB"); 
	        

	    } catch (SQLException ex) {
	        System.out.println("Wystąpił błąd: " + ex.getMessage());
	    }finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Błąd podczas zamykania zasobów: " + e.getMessage());
	        }
	        
	    }
	}


	
	void removeWithTextFile(String cod, String sql) {
	    Path path = Paths.get("date.txt");

	    if (!Files.exists(path)) {
	        System.out.println("Plik nie istnieje: " + path.toAbsolutePath().toString());
	        return;
	    } 

	    try (Connection connection = DriverManager.getConnection(url, username, password)) {
	        try (PreparedStatement pst = connection.prepareStatement(sql)) {
	            ResultSet resultSet = pst.executeQuery();

	            Set<String> codesInTable = new HashSet<>();
	            while (resultSet.next()) {
	                String codDB = resultSet.getString("Cod");
	                codesInTable.add(codDB.trim().toUpperCase());
	            }

	            List<String> lines = Files.readAllLines(path);
	            List<String> filteredLines = lines.stream().filter(line -> {
	                String[] parts = line.split("\t");
	                String codFromFile = parts[0].trim().toUpperCase();

	                boolean shouldKeep = !codFromFile.equals(cod.trim().toUpperCase());

	                if (!shouldKeep) {
	                	System.out.println("-----------------------------------------------------------");
            	        System.out.println();
            	        System.out.println("                * Pomyślnie zdjęto paletę *                ");
            	        System.out.println();
            	        System.out.println("-----------------------------------------------------------");
	                } 
	                return shouldKeep;
	            }).collect(Collectors.toList());

	            Files.write(path, filteredLines);
	        } catch (SQLException e) {
	            System.out.println("Błąd podczas odczytu z bazy danych: " + e.getMessage());
	        }
	    } catch (IOException e) {
	        System.out.println("Błąd podczas operacji na pliku: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Błąd podczas łączenia z bazą danych: " + e.getMessage());
	    }
	}


	
		void conWithTablePlaceA() {
			
			conWithTablePlaces(placeA);
			
			}
	
	
		void conWithTablePlaceB() {
			
		conWithTablePlaces(placeB);
		
			}
		
		 static void addCompletly() {
			
			 System.out.println();
			 System.out.println("-----------------------------------------------------------");
			 System.out.println();
			 System.out.println("                    * Pomyślnie dodano *                   ");
			 System.out.println();
    		 System.out.println("-----------------------------------------------------------");
    		
		}
		
		static void noFreePlace() {
			
			System.out.println();
			System.out.println("-----------------------------------------------------------");
			System.out.println();
    		System.out.println("                * Nie ma dostępnego miejsca! *             ");
    		System.out.println();
    		System.out.println("-----------------------------------------------------------");
			
		}
		
		static void remove() {
			System.out.println();
			System.out.println("-----------------------------------------------------------");
			System.out.println();
    		System.out.println("             * Pomyślnie zdjęto z regału *                  ");
    		System.out.println();
    		System.out.println("-----------------------------------------------------------");
		}
		

 }