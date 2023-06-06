package jpa.example.SpringJPA.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
  private Connection connection;
  public DBConnector(String url,String user,String password){
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection= DriverManager.getConnection(url, user,password);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public Connection getConnection(){
    return this.connection;
  }
}
