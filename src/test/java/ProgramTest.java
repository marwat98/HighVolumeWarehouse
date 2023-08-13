



import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.Engineering.ProgramClassTest;
import org.junit.jupiter.api.*;
import java.sql.*;

public class ProgramTest {
	   private Connection connection;
	   int idPallete = 0002;
	   int weightPallete = 213;
	   int cod = 222;
    
	   @Test
	    public void test_addPalleteinPlaceATest() throws SQLException {
		   
	        //Mock connection with DataBase
	        Connection mockConnection = mock(Connection.class);
	        PreparedStatement mockStatement = mock(PreparedStatement.class);
	        ResultSet mockResultSet = mock(ResultSet.class);

	        //Defining mock behavior
	        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
	        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
	        when(mockResultSet.next()).thenReturn(true).thenReturn(false); 
	        when(mockResultSet.getInt(1)).thenReturn(2); 

	        ProgramClassTest add = new ProgramClassTest();
	        
	        //Setting up a mimicked connection instance in your class
	        add.connection = mockConnection; 
	        
	        if(idPallete >= 0000 && idPallete <= 0004) {
	        	
	        // Method call
	        add.addPalleteinPlaceATest(idPallete,weightPallete,cod);
	        } 
	        else {
	        	System.out.print("Podany numer palety jest nieprawidłowy");
	        }

	        // Veryfication
	        verify(mockStatement, times(2)).executeQuery();
	    }

   
	   
}



	



