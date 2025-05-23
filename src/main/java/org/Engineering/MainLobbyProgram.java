package org.Engineering;

import java.io.IOException;



import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fazecast.jSerialComm.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 * 
 * @author marcinwatras
 * 
 *  
 *
 */

public class MainLobbyProgram {
    public static void main(String[] args) throws URISyntaxException, IOException {
    	
        Scanner scanner = new Scanner(System.in);
        boolean ifTrue = true;
        int choice = 0;
        int buforCode = 0;
        String sugestion = null;
        int idPallete = 0;
        int weightPallete = 0;
        int code = 0;
        boolean isValid;
        String palleteCode = null;
        String rackCode = null;
        String addDates = null;
      
       
        
        //Class which add records in file
        ProgramMenager programMenager = new ProgramMenager();
        
        //Class that connects to the database
        ConnectionWithDataBase con = new ConnectionWithDataBase();
        
        //Class that connects to GPT chat which was implemented with maven
        ChatGPTConnector connectionWithGPT = new ChatGPTConnector();
        
        //Class that connects to Arduino which was implemented with maven
        Arduino arduino = new Arduino();
        
        //Class which implements date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  
        // Travers code 1A
        RackCoder rack1A = new RackCoder("111");
        // Travers code 2A
        RackCoder rack2A = new RackCoder("222");
        // Travers code 3A
        RackCoder rack3A = new RackCoder("333");
        // Travers code 1B
        RackCoder rack1B = new RackCoder("101");
        // Travers code 2B
        RackCoder rack2B = new RackCoder("202");
        // Travers code 3B
        RackCoder rack3B = new RackCoder("303");
        
        
        System.out.println();
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        System.out.println("               Program Oświetlenia Regału                  ");
        System.out.println();
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        
        
        
        while(ifTrue){
        	System.out.println("-----------------------------------------------------------");
        	System.out.println("                                                            ");
            System.out.println("         1. Pokaż miejsce na wysokim składzie             \n");
            System.out.println("         2. Dodaj palete na wysoki skład                  \n");
            System.out.println("         3. Zdejmij palete z wysokiego składu             \n");
            System.out.println("         4. Sprawdź daty palet wrzuconych na wysoki skład \n");
            System.out.println("         5. Wyjście                                       \n");
            System.out.println("                                                            ");
            System.out.println("-----------------------------------------------------------");
            System.out.println();
            System.out.print("                     Wybierz opcje: ");
            
            
            //an exception that prevents the value from not being selected
            try {
              //option selection variable
              choice = Integer.parseInt(scanner.nextLine());
              
            }catch (NumberFormatException e) {
                continue; 
            }
            
            // conditional statement which gives possibility choose option
            switch (choice){
                case 1  -> {
                	
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.out.println("                  Miejsce na poziomie A                    ");
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	con.conWithTablePlaceA();
                	System.out.println();
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
            		System.out.println();
            		
            		System.out.println("-----------------------------------------------------------");
                	System.out.println("                  Miejsce na poziomie B                    ");
                	System.out.println("-----------------------------------------------------------");
            		System.out.println();
            		
            		con.conWithTablePlaceB();
            		System.out.println();
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
            		System.out.println();

                }
                case 2 ->{
                	// method which on green light in LED panel
                	arduino.arduinoGreenOn();
                	
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.println("           * Wproawdź dane w odpowiednie pola *             ");
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.print("                  Wprowadź kod palety: ");
                	
                
                	do {
                	    isValid = true;  
                	    try {
                	        // variable which add number to column named Cod
                	        idPallete = Integer.parseInt(scanner.nextLine());
                	        if (checkCode(idPallete)) {
                	        	System.out.println("-----------------------------------------------------------");
                	            System.out.println();
                	            System.out.print("              * Podany kod jest prawidłowy *                   ");
                	            System.out.println();
                	            System.out.println();
                	            System.out.println("-----------------------------------------------------------");
                	            
                	        }else {
                	        	System.out.println("-----------------------------------------------------------");
                	            System.out.println();
                	            System.out.print("           * Podany kod  nie jest prawidłowy *         ");
                	            System.out.println();
                	            System.out.println();
                	            System.out.println("-----------------------------------------------------------");
                	            isValid = false;
                	        }

                	    } catch (NumberFormatException e) {
                	    	System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	        System.out.print("            * Podana wartość nie jest liczbą *         ");
                	        System.out.println();
            	            System.out.println();
            	            System.out.println("-----------------------------------------------------------");
                	        isValid = false;  
                	    }
                	} while (!isValid);  

                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.print("                 Wproawdź wagę palety: ");
  
                	
                	do {
                		isValid = true;
                	
                	try {
                	
                	//viariable which add weight to column named A1 , A2, A3
                    weightPallete = Integer.parseInt(scanner.nextLine());
                    
                	}catch (NumberFormatException e) {

                		System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("             * Podana wartość nie jest wagą *                 ");
                    	System.out.println();
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	isValid = false;
                    	
                		} 
                	} while(!isValid);
                	
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                    
                    if(weightPallete > 200) {
                    	
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.println("      * Ze względu na wagę umieść paletę na poziomie A *   ");
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("                   Wproawdź kod regału: ");

                    	
                    	
                    	do {
                    		isValid = true; 
                    		
                    	try {
                    		
                    	code = Integer.parseInt(scanner.nextLine());
                    		
                    
                    	} catch (NumberFormatException e) {
                        	isValid = false;  
                    		System.out.println("-----------------------------------------------------------");
                        	System.out.println();
                        	System.out.print("            * Podany kod nie należy do tego regału *         ");
                        	System.out.println();
                        	System.out.println();
                        	System.out.println("-----------------------------------------------------------");

                    		}
                    	} while (!isValid);  
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	
                    	//method which supports varaible and SQL questions
        	            con.addPalleteinPlaceA(idPallete, weightPallete,code,"SELECT COUNT(DISTINCT Cod) FROM PlaceA","A1","A2","A3","INSERT INTO PlaceA (Cod, %s, %s,RackCode) VALUES (?, ?, ?, ?)");
        	            
        	            //method which add SQL questions to the table in MySQLWorkbench
        	            programMenager.addRecords("SELECT Cod, A1, A2, A3,RackCode, DateANDTime FROM PlaceA","A1","A2","A3");
        	            
        	            // method which off green light in LED panel
        	            arduino.arduinoGreenOff();
        	            
        	            // method which on red light in LED panel
        	            arduino.arduinoRedOn();
        	            
        	            
                  
                    } else if(weightPallete < 200) {
                    	
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.println("     * Ze względu na wagę umieść paletę na poziomie B *     ");
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("                  Wproawdź kod regału: ");
                    	
                    	do {
                    		isValid = true;
                    	try {
                    	// variable which add special number to column named RackCode 
                        code = Integer.parseInt(scanner.nextLine());
                        
                        
                        if(code != 101 && code != 202 && code != 303) {
                        	
                        	System.out.println("-----------------------------------------------------------");
                        	System.out.println();
                        	System.out.print("            * Podany kod nie należy do tego regału *         ");
                        	System.out.println();
                        	System.out.println();
                        	System.out.println("-----------------------------------------------------------");
                        	isValid = false;
                        }
                        
                    	} catch (NumberFormatException e) {
                    		System.out.println("-----------------------------------------------------------");
                        	System.out.println();
                        	System.out.print("           * Podana wartość nie jest liczbą! *             ");
                        	System.out.println();
                        	System.out.println();
                        	System.out.println("-----------------------------------------------------------");
                        	isValid = false;   
                    		}
                    	} while (!isValid); 
                    	
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	
                    	//method which supports varaible and SQL questions
                    	con.addPalleteinPlaceB(idPallete, weightPallete,code,"SELECT COUNT(DISTINCT Cod) FROM PlaceB","B1","B2","B3","INSERT INTO PlaceB (Cod, %s, %s,RackCode) VALUES (?, ?, ?, ?)");
                    	
                    	//method which add SQL questions to the table in MySQLWorkbench
                    	programMenager.addRecords("SELECT Cod, B1, B2, B3,RackCode,DateANDTime FROM PlaceB","B1","B2","B3");
                    	
                    	// method which off green light in LED panel
                    	arduino.arduinoGreenOff();
                    	
                    	// method which on red light in LED panel
        	            arduino.arduinoRedOn();
                    }
                }
                case 3 ->{
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.println("          * Wproawdź dane do ściągniecia palety *          ");
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.print("                  Wproawdź kod regału: ");

                	do {
                	    rackCode = scanner.nextLine();

                	    if (rackCode.equals("111") || rackCode.equals("222") || rackCode.equals("333") 
                	        || rackCode.equals("101") || rackCode.equals("202") || rackCode.equals("303")) {
                	    	
                	        isValid = true;
                	        
                	    } else {
                	        System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	        System.out.println("            * Podany kod regału nie istnieje *             ");
                	        System.out.println();
                	        System.out.println("-----------------------------------------------------------");
                	        isValid = false;
                	    }
                	} while(!isValid);

                	
                	do {
                	    System.out.println("-----------------------------------------------------------");
                	    System.out.println();
                	    System.out.print("                  Wproawdź kod palety: ");
                	    palleteCode = scanner.nextLine();

                	    isValid = checkRackCodeInDatabase(palleteCode);
                	    
                	    if(!isValid) {
                	        System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	        System.out.println("             * Podany kod palety nie istnieje *             ");
                	        System.out.println();
                	        System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	    }
                	} while(!isValid);

                	do {
                		isValid = true;

                	    try {
                	        System.out.println();
                	        System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	        System.out.print("                  Wproawdź kod buforu: ");
                	        buforCode = Integer.parseInt(scanner.nextLine());
                	        
                	        if (buforCode != 909) {
                	        	System.out.println("-----------------------------------------------------------");
                     	        System.out.println();
                     	        System.out.println("           * Podana wartość nie należy do buforu *         ");
                     	        System.out.println();
                     	        System.out.println("-----------------------------------------------------------");
                	            isValid = false;
                	        }
  
                	    } catch (NumberFormatException e) {
                	        System.out.println("-----------------------------------------------------------");
                	        System.out.println();
                	        System.out.println("             * Podana wartość nie jest liczbą *             ");
                	        System.out.println();
                	        System.out.println("-----------------------------------------------------------");
                	        isValid = false;
                	        
                	    }  
                	} while(!isValid);

                	System.out.println("-----------------------------------------------------------");

                	con.removePalleteAndRemoveFromFile(rackCode,palleteCode, buforCode, "SELECT * FROM PlaceA WHERE RackCode = ? AND Cod = ?", "A1", "A2", "A3", "DELETE FROM PlaceA WHERE Cod = ?");
                	con.removePalleteAndRemoveFromFile(rackCode,palleteCode, buforCode, "SELECT * FROM PlaceB WHERE RackCode = ? AND Cod = ?", "B1", "B2", "B3", "DELETE FROM PlaceB WHERE Cod = ?");

                	// method which off red light in LED panel
                	arduino.arduinoRedOff();
                	
                	// method which on green light in LED panel
                	arduino.arduinoGreenOn();
                		
                
                }
                	case 4->{
                		
                	dateFormat.setLenient(false);
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.print("Podaj datę:  ");
                	//variable which add question in GPT chat
                	addDates = scanner.nextLine();
                	
                	do {
                		isValid = true;
                		
                	if(addDates instanceof String) {
                		continue;
                	} else {
                		System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("             * Podana wartość nie jest datą *                ");
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("Podaj datę:  ");
                    	isValid = false;
                    	
                		} 
                	}	while(!isValid);
                	
                	
                	do {
                		isValid = true;
                	try {
                		
                        dateFormat.parse(addDates);
                        System.out.println();
                    	System.out.print("Sprawdzam daty proszę o chwilę cierpliwości....");
                    	System.out.println();
                    	System.out.println();
                        
                        //viarbale which has function avaliable in ChatGPT class
                        sugestion = connectionWithGPT.addSugestionForPallete(programMenager.getPalletePlaces(),addDates);
                        
                    } catch (ParseException e) {
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("          * Ciąg znaków nie jest poprawną datą *             ");
                    	System.out.println();
                    	System.out.println("-----------------------------------------------------------");
                    	System.out.println();
                    	System.out.print("Podaj datę: ");
                    	addDates = scanner.nextLine();
                    	isValid = false;
                    	
                    	}
                	}	while(!isValid);
                	
            
                	// show GPT answer
                	System.out.println(sugestion);
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	
                }
                case 5->{
                	// method which off green and red light in LED panel
                	arduino.arduinoGreenOff();
                	arduino.arduinoRedOff();
                	
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.out.println();
                	System.out.println("                * Dziekuje do zobaczenia *                ");
                	System.out.println();
                	System.out.println("-----------------------------------------------------------");
                	System.exit(0);
                	
                	
                }
                
            }
            
        }
        
    }
    			// static method with check if variable have 4 numbers
    			public static boolean checkCode(int idPallete ) {
    				
    			return idPallete >= 1001 && idPallete <= 1100;
		
    			}
    			public static boolean isNumber(String palleteCode) {
    		        try {
    		            Double.parseDouble(palleteCode); 
    		            return true;
    		        } catch (NumberFormatException e) {
    		            return false;
    		        }
    		    }
    			private static boolean checkRackCodeInDatabase(String rackCode) {
    				 String url = "jdbc:mysql://localhost:3306/PalletePlace";
    			  	 String username = "root";
    			  	 String password = "password1";
    			  	 
    			    try (Connection connection = DriverManager.getConnection(url, username, password)) {
    			        String query = "SELECT Cod FROM PlaceA WHERE Cod = ? " +
    			                       "UNION " +
    			                       "SELECT Cod FROM PlaceB WHERE Cod = ? " +
    			                       "UNION " +
    			                       "SELECT Cod FROM Bufor WHERE Cod = ? ";

    			        PreparedStatement statement = connection.prepareStatement(query);
    			        statement.setString(1, rackCode);
    			        statement.setString(2, rackCode);
    			        statement.setString(3, rackCode);
    			        
    			        ResultSet resultSet = statement.executeQuery();
    			        return resultSet.next(); 
    			    } catch (Exception e) {
    			        e.printStackTrace();
    			    }
    			    return false;
    			}
    	
}
 
    
