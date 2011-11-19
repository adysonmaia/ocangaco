package br.ufc.servidor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.ufc.servidor.player.Player;

public class DB {
	
	/**
	 * Inicia o bando de dados - fazendo drop das existentes e criando novamente
     * @throws Exception
     */
    public void startDB() throws Exception {
          Class.forName("org.sqlite.JDBC");
          Connection conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db");
          Statement stat = conn.createStatement();
          conn.setAutoCommit(true);
          stat.executeUpdate("drop table if exists player;");
          stat.executeUpdate("create table player (id INTEGER CONSTRAINT PK PRIMARY KEY, playerName VARCHAR(50));");
          
          /* exemplo de chave estrangeira
          stat.executeUpdate("drop table if exists friendOf;");
          stat.executeUpdate("create table friendOf (uId INTEGER, friendId INTEGER, "+
                                             "CONSTRAINT PK PRIMARY KEY (uId,friendId), "+ 
                                             "CONSTRAINT FK1 FOREIGN KEY (uId,friendId) REFERENCES users (id,id) ON DELETE CASCADE);");
         */
          
          PreparedStatement prep = conn.prepareStatement(
          "insert into users values (NULL, ?, ?);");

          // test users
          prep.setString(1,"Ze");
          prep.setString(2,"abc");
          prep.addBatch();
          prep.setString(1, "Sa");
          prep.setString(2, "123");
          prep.addBatch();
          prep.setString(1, "Silva");
          prep.setString(2, "abc123");
          prep.addBatch();

          conn.setAutoCommit(false);
          prep.executeBatch();
          conn.setAutoCommit(true);

          ResultSet rs = stat.executeQuery("select * from users;");
          while (rs.next()) {
            System.out.println("User = " + rs.getString("user")+", Pass = " + rs.getString("password"));
          }
          rs.close();
          conn.close();
          
      }
    
    public static int addPlayer(Player player){
    	Connection conn = null;
    	int returnValue = -1;
    	try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:capim.db");
            conn.setAutoCommit(false);
    	
            PreparedStatement prep = conn.prepareStatement("INSERT INTO player (playerName) values('?')");
            prep.setString(1, player.getUserName());
            ResultSet rs = prep.executeQuery();
            
            if(rs.rowInserted()){
            	returnValue=1;
			} else
            	returnValue= -1;
            
            rs.close();
            conn.close();
            

    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return returnValue;
    }
    	
    	

    /**
     * Check if the user and login suplied exists in the DB
     * @param userName user name
     * @param password password form user
     * @return true if the user and password match or false if not 
     */
    	/*
    public static boolean checkUser(String userName, String password){
            Connection conn = null;
            boolean returnVariable = false;
            try {
                    Class.forName("org.sqlite.JDBC");
                    conn = DriverManager.getConnection("jdbc:sqlite:capim.db");
                conn.setAutoCommit(false);
                
                // Debug only
                // System.out.println("Usuario->"+userName+":pass->"+password);
                
                PreparedStatement prep = conn.prepareStatement("SELECT * FROM users u WHERE u.user = ? AND u.password = ?");
                prep.setString(1, userName);
                prep.setString(2, password);
                ResultSet rs = prep.executeQuery();
                if(rs.next()){
                    returnVariable = true;
                } else returnVariable = false;
                rs.close();
                
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            } catch (SQLException e1) {
                    e1.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e2) {
                    e2.printStackTrace();
            } finally {
                    if(conn != null)
                            try {
                                    conn.close();
                            } catch (SQLException e) {
                                    e.printStackTrace();
                            }
            }
            
            return returnVariable;
    }
    */


}
