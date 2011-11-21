package br.ufc.servidor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.ufc.servidor.player.Player;

public class DB {
	
	/**
	 * Inicia o bando de dados - fazendo drop das existentes e criando novamente
     * @throws Exception
     */
    public static void startDB() throws Exception {
          Class.forName("org.sqlite.JDBC");
          Connection conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db");
          Statement stat = conn.createStatement();
          conn.setAutoCommit(true);
          stat.executeUpdate("drop table if exists player;");
          stat.executeUpdate("create table player (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), tipo int, lat double, long double);");
          
          /* exemplo de chave estrangeira
          stat.executeUpdate("drop table if exists friendOf;");
          stat.executeUpdate("create table friendOf (uId INTEGER, friendId INTEGER, "+
                                             "CONSTRAINT PK PRIMARY KEY (uId,friendId), "+ 
                                             "CONSTRAINT FK1 FOREIGN KEY (uId,friendId) REFERENCES users (id,id) ON DELETE CASCADE);");
         */

          /*
          PreparedStatement prep = conn.prepareStatement(
          "insert into player values (NULL, ?, ?, NULL, NULL);");

          
          // test users
          prep.setString(1,"Ze");
          prep.setInt(2,1);
          prep.addBatch();
          prep.setString(1, "Sa");
          prep.setInt(2,1);
          prep.addBatch();
          prep.setString(1, "Silva");
          prep.setInt(2,2);
          prep.addBatch();

          conn.setAutoCommit(false);
          prep.executeBatch();
          conn.setAutoCommit(true);

          ResultSet rs = stat.executeQuery("select * from player;");
          while (rs.next()) {
            System.out.println("User = " + rs.getString("nome")+", Time = " + rs.getInt("time"));
          }
          rs.close();
          */
          conn.close();
          
      }
    
    /**
     * Lista todos os jogadores do jogo
     * @return ArrayList<Player> com todos os jogadores do jogo
     * @throws ClassNotFoundException
     */
    public static ArrayList<Player> getPlayers() throws ClassNotFoundException{
    	 Class.forName("org.sqlite.JDBC");
         Connection conn;
         ArrayList<Player> list = new ArrayList<Player>();
         Player p;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db"); 
			Statement stat = conn.createStatement();
	        conn.setAutoCommit(true);
	        ResultSet rs = stat.executeQuery("select * from player;");
	        while (rs.next()) {
	        	p = new Player(rs.getString("nome"),rs.getInt("tipo"));
	        	p.setLatitude(rs.getDouble("lat"));
	        	p.setLongitude(rs.getDouble("long"));
	        	list.add(p);
	        	
	          //System.out.println("Player name = " + rs.getString("nome")+", Time = " + rs.getInt("tipo") + "Latitude: "+rs.getDouble("lat")+ "Longitude: "+rs.getDouble("lon"));
	        }
	        rs.close();
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        return list;
    	
    }
    
    /**
     * Lista todos os jogadores to tipo repassado como parametro
     * @param team Tipo do jogador a ser passado como parâmetro
     * @return ArrayList<Player> com todos os jogadores do tipo team
     * @throws ClassNotFoundException
     */
    public static ArrayList<Player> getPlayersFromTeam(int team) throws ClassNotFoundException{
   	 Class.forName("org.sqlite.JDBC");
        Connection conn;
        ArrayList<Player> list = new ArrayList<Player>();
        Player p;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db"); 
	        conn.setAutoCommit(true);
	        PreparedStatement prep = conn.prepareStatement("select * from player where player.id = ?;");
	        prep.setInt(1,team);
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	        	p = new Player(rs.getString("nome"),rs.getInt("tipo"));
	        	p.setLatitude(rs.getDouble("lat"));
	        	p.setLongitude(rs.getDouble("long"));
	        	list.add(p);
	        	
	          // System.out.println("Player name = " + rs.getString("nome")+", Tipo = " + rs.getInt("tipo") + "Latitude: "+rs.getDouble("lat")+ "Longitude: "+rs.getDouble("lon"));
	        }
	        rs.close();
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
       return list;
   	
   }
    
    
    /**
     * Adiciona um jogador ao jogo
     * @param player Jogador a ser inserido
     * @return -1 em caso de erro e o ID do jogador caso contrário
     */
    public static int addPlayer(Player player){
    	Connection conn = null;
    	int returnValue = -1;
    	try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db");
            conn.setAutoCommit(false);
    	
            PreparedStatement prep = conn.prepareStatement("INSERT INTO player (nome, tipo) values(?,?)");
            prep.setString(1, player.getNome());
            prep.setInt(2, player.getTipo());
            prep.execute();
            conn.setAutoCommit(true);
            prep.close();
            
            conn.setAutoCommit(false);
	        prep = conn.prepareStatement("SELECT * FROM player where player.nome = ?");
	        prep.setString(1, player.getNome());
	        ResultSet rs = prep.executeQuery();
	        // prep.close();
	        if(rs.next()){
	        	returnValue = rs.getInt("id");
	        } else
	        	returnValue = -1;
	        prep.close();
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
