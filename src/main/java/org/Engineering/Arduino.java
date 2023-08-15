package org.Engineering;
import java.util.stream.*;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino {
	
	
	public void arduinoGreenOn() {
		// Connection with arduino port
	    SerialPort arduinoPort = SerialPort.getCommPort("/dev/cu.usbmodem11301");
	    // Port parameters
	    arduinoPort.setComPortParameters(9600, 8, 1, 0);
	    //a method that sets timeouts for the serial port
	    arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

	    if (arduinoPort.openPort()) {
	    	System.out.println();
	    	System.out.println("-----------------------------------------------------------");
        	System.out.println();
	        System.out.println("            * Zielona dioda została włączona *             ");
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
	        //method which sets the baud rate
	        arduinoPort.setBaudRate(9600);

	        try {
	        	//Waiting 2 seconds
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // simulating sending information to arduino
	        String dataToSend = "GreenOn\n";
	        arduinoPort.writeBytes(dataToSend.getBytes(), dataToSend.length());

	        try {
	        	// Waiting 3 seconds
	            Thread.sleep(3000); 
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	        // Close port 
	        arduinoPort.closePort();
	    } else {
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
	        System.out.println();
	        System.out.println("              * Nie można otworzyć portu! *                ");
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
	    }
	}
	
		public void arduinoGreenOff() {
	    
		SerialPort arduinoPort = SerialPort.getCommPort("/dev/cu.usbmodem11301");
	    arduinoPort.setComPortParameters(9600, 8, 1, 0);
	    arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
	   
	    if (arduinoPort.openPort()) {
	    	System.out.println();
	    	System.out.println("-----------------------------------------------------------");
        	System.out.println();
        	System.out.println("             * Zielona dioda została wyłączona *           ");
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
	        arduinoPort.setBaudRate(9600);
	        
	        
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        
	        String dataToSend = "GreenOff\n"; 
	        arduinoPort.writeBytes(dataToSend.getBytes(), dataToSend.length());
	        
	        try {
	            Thread.sleep(3000);  
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	       
	        arduinoPort.closePort();
	        
	        } else {
	        	System.out.println();
	        	System.out.println("-----------------------------------------------------------");
	 	        System.out.println();
	 	        System.out.println("              * Nie można otworzyć portu! *                ");
	 	        System.out.println();
	 	        System.out.println("-----------------------------------------------------------");
	        
	    } 
	}
	
		public void arduinoRedOn() {
	    
	    
		SerialPort arduinoPort = SerialPort.getCommPort("/dev/cu.usbmodem11301");
	    arduinoPort.setComPortParameters(9600, 8, 1, 0);
	    arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
	   
	    if (arduinoPort.openPort()) {
	    	System.out.println();
	    	System.out.println("-----------------------------------------------------------");
        	System.out.println();
	        System.out.println("             * Czerwona dioda została włączona *           ");
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
	        arduinoPort.setBaudRate(9600);
	        
	        
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	       
	        String dataToSend = "RedOn\n"; 
	        arduinoPort.writeBytes(dataToSend.getBytes(), dataToSend.length());
	        
	        try {
	            Thread.sleep(3000);  // Waiting 3 second
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	        
	        arduinoPort.closePort();
	        
	        } else {
	        	System.out.println();
	        	System.out.println("-----------------------------------------------------------");
	 	        System.out.println();
	 	        System.out.println("              *	Nie można otworzyć portu! *                ");
	 	        System.out.println();
	 	        System.out.println("-----------------------------------------------------------");
	        
	    } 
}
	
		public void arduinoRedOff() {
    
    
		SerialPort arduinoPort = SerialPort.getCommPort("/dev/cu.usbmodem11301");
		arduinoPort.setComPortParameters(9600, 8, 1, 0);
		arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
   
		if (arduinoPort.openPort()) {
			System.out.println();
			System.out.println("-----------------------------------------------------------");
        	System.out.println();
        	System.out.println("            * Czerwona dioda została wyłączona *             ");
	        System.out.println();
	        System.out.println("-----------------------------------------------------------");
        arduinoPort.setBaudRate(9600);
        
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
       
        String dataToSend = "RedOff\n"; 
        arduinoPort.writeBytes(dataToSend.getBytes(), dataToSend.length());
        
        try {
            Thread.sleep(3000);  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       
       
        arduinoPort.closePort();
        
        } else {
        	System.out.println();
        	System.out.println("-----------------------------------------------------------");
 	        System.out.println();
 	        System.out.println("              *	Nie można otworzyć portu! *                ");
 	        System.out.println();
 	        System.out.println("-----------------------------------------------------------");
        } 
	}
}

