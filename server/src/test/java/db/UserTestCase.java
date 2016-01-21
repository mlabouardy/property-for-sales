package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

public class UserTestCase extends DatabaseTestCase{
	private FlatXmlDataSet loadedDataSet;
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(DBHelper.getConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
				.getResourceAsStream("dbunitData.xml"));
		return loadedDataSet;
	}
	
	@Test
	public void testInsertUser(){
		String sql="SELECT * FROM User WHERE firstName=?";
		try(PreparedStatement ps=DBHelper.getConnection().prepareStatement(sql)){
			ps.setString(1, "mohamed");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String firstName=rs.getString("firstName");
				String lastName=rs.getString("lastName");
				String email=rs.getString("email");
				String password=rs.getString("password");
				assertEquals("Mohamed", firstName);
				assertEquals("Labouardy", lastName);
				assertEquals("mohamed@labouardy.com", email);
				assertEquals("password", password);
			}else{
				fail("User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}