package org.Engineering;

import java.sql.ResultSet;

public interface conection {
	
	void conWithTablePlaces(String set);
	void addPalleteinPlaceA(int idPallete, int weightPallete, int code ,String selectA,String place1a,String place2a,String place3a,String insertA);
	void addPalleteinPlaceB(int idPallete, int weightPallete, int code , String selectB,String place1b,String place2b,String place3b,String insertB);
	void removePalleteAndRemoveFromFile(String rackCode, String palleteCode, int buforCode,String sql1,String place1,String place2,String place3,String sql3);
	

}
