import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.util.PSQLException;

public class ConnectionDB {
	final static String url = "jdbc:postgresql://junction.proxy.rlwy.net:16902/railway?user=postgres&password=imioQblEwsVUXsictkeZCjvnuWWqyqNE";
	
	public PGSimpleDataSource createDataSource() throws PSQLException{
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setURL(url);
		return dataSource;
	}

	public Connection connect(){
		try{
			PGSimpleDataSource dataSource = createDataSource();
			return dataSource.getConnection();
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}