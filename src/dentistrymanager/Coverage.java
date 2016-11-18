package dentistrymanager;

import java.sql.*;
import java.util.*;

public class Coverage {

	private String typeOfTreatment;
	private int numOfTreatments;
	private double costCovered;
	
	// Constructor
	public Coverage(String typeOfTreatment, int numOfTreatments, double costCovered) {
		this.typeOfTreatment = typeOfTreatment;
		this.numOfTreatments = numOfTreatments;
		this.costCovered = costCovered;
	}
	
	// Accessors
	public String getTypeOfTreatment() {
		return typeOfTreatment;
	}
	
	public int getNumOfTreatments() {
		return numOfTreatments;
	}
	
	public double getCostCovered() {
		return costCovered;
	}
	
	// Static methods 
	public static ArrayList<Coverage> getCoverageByPlan(Connection connection, String plan) {
		ArrayList<Coverage> planCoverage = new ArrayList<>();
		try(Statement stmt = connection.createStatement()) {
			String sql = "SELECT typeOfTreatment, numOfTreatments, costCovered FROM Coverage WHERE plan = " + plan +";";
			ResultSet res = stmt.executeQuery(sql);
			while(res.next())
				planCoverage.add(new Coverage(res.getString("typeOfTreatment"), 
												res.getInt("numOfTreatments"), 
												res.getDouble("costCovered")));	
		} catch (SQLException e) {
			DBConnect.printSQLError(e);
		} 
		return planCoverage;
	}
}