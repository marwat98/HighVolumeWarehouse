package org.Engineering;

import java.io.FileWriter;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;



public class ProgramMenager {
	
		// Class Path which represent file path
	    Path palletPath;
	    // variable which connection with database
	    String url = "jdbc:mysql://localhost:3306/PalletePlace";
		String username = "root";
		String password = "password1";
		
		// Classes connection and supporting SQL queries
		Connection connection = null;
		Statement statement;
		ResultSet resultSet;

		// contractor which check file
	    public ProgramMenager() {
	        try {
	            URL url = ProgramMenager.class.getClassLoader().getResource("date.txt");
	            if (url != null) {
	                palletPath = Paths.get(url.toURI());
	            } else {
	                palletPath = Paths.get("date.txt");
	                if (!Files.exists(palletPath)) {
	                    Files.createFile(palletPath);
	                }
	            }
	        } catch (URISyntaxException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // method which read line in file
	    public List<String> getPalletePlaces() throws IOException {
	        return Files.readAllLines(palletPath, StandardCharsets.UTF_8);
	    }
	    //method which check file
	    public void checkPallete() throws URISyntaxException, IOException {
	        HashSet<String> places = new HashSet<>(getPalletePlaces());
	    }
	    // method which add records in file
	    public void addPalete(String addDate) throws IOException {
	        HashSet<String> Date = new HashSet(getPalletePlaces());
	        Files.write(palletPath, (System.lineSeparator() + addDate).getBytes(), StandardOpenOption.APPEND);
	    }
	    
	    void addRecords(String addResult, String place1, String place2, String place3) {
	        try (
	            Connection connection = DriverManager.getConnection(url, username, password);
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(addResult);
	            BufferedWriter writer = new BufferedWriter(new FileWriter("date.txt", true))
	        ) {
	            while (resultSet.next()) {
	                String cod = resultSet.getString("Cod");
	                String rackCode = resultSet.getString("RackCode"); 
	                
	                String weight;
	                if (resultSet.getString(place1) != null && !resultSet.getString(place1).isEmpty()) {
	                    weight = resultSet.getString(place1);
	                } else if (resultSet.getString(place2) != null && !resultSet.getString(place2).isEmpty()) {
	                    weight = resultSet.getString(place2);
	                } else {
	                    weight = resultSet.getString(place3);
	                }
	                
	                Timestamp timestamp = resultSet.getTimestamp("DateANDTime");
	                LocalDateTime dataAndTime = timestamp.toLocalDateTime();

	                // Uwzględnienie wartości z kolumny "RackCode" w linii do zapisu
	                String line = cod + "\t" + rackCode + "\t" + weight + "\t" + dataAndTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	                
	                writer.write(line);
	                writer.newLine();
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	 }
	    
