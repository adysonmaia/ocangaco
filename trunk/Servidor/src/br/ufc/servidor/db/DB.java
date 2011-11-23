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
          stat.executeUpdate("create table player (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50) UNIQUE, tipo int, lat double, lon double);");

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
	        	p.setLongitude(rs.getDouble("lon"));
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
	        PreparedStatement prep = conn.prepareStatement("select * from player where player.tipo = ?;");
	        prep.setInt(1,team);
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	        	p = new Player(rs.getString("nome"),rs.getInt("tipo"));
	        	p.setLatitude(rs.getDouble("lat"));
	        	p.setLongitude(rs.getDouble("lon"));
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
     * @throws ClassNotFoundException 
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
	        ResultSet rs = null;
	        
	        rs = prep.executeQuery();
	        if(rs.next()){
	    		returnValue = rs.getInt("id");
	    	} else
	    		returnValue = -1;
	    	
	        prep.close();
	        rs.close();
            conn.close();
            

    	
    	} catch(ClassNotFoundException ce){
    		ce.printStackTrace();
    	} catch (SQLException e) {
    		if(e.getMessage().equals("column nome is not unique"))
    			returnValue = -1;
		}
    	return returnValue;
    }
    	
    /**
     * Remove um jogador do jogo
     * 
     * @param player Nome do Jogador a ser removido
     * @return 1 em caso de sucesso, -1 em caso de falha
     */
    public static int delPlayer(String player){
    	Connection conn = null;
    	int returnValue = -1;
    	try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db");
            conn.setAutoCommit(false);
    	
            PreparedStatement prep = conn.prepareStatement("DELETE FROM player WHERE p.nome = ?");
            prep.setString(1, player);
            
	        ResultSet rs = prep.executeQuery();
    		returnValue = 1;
	    	
	    	
	        prep.close();
	        rs.close();
            conn.close();
            

    	
    	} catch(ClassNotFoundException ce){
    		ce.printStackTrace();
    	} catch (SQLException e) {
    		if(e.getMessage().equals("column nome is not unique"))
    			returnValue = -1;
		}
    	return returnValue;
    }
    
    public static int updatePlayer(Player p){
    	Connection conn = null;
    	int returnValue = -1;
    	try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:gameserver.db");
            conn.setAutoCommit(false);

            PreparedStatement prep = conn.prepareStatement("UPDATE player set player.tipo = ?, player.lat = ?, player.lon = ? where WHERE player.nome = ?");
            prep.setInt(1, p.getTipo());
            prep.setDouble(2, p.getLatitude());
            prep.setDouble(3, p.getLongitude());
            prep.setString(4, p.getNome());
            
	        ResultSet rs = prep.executeQuery();
    		returnValue = 1;
	    	
	    	
	        prep.close();
	        rs.close();
            conn.close();
            

    	
    	} catch(ClassNotFoundException ce){
    		ce.printStackTrace();
    	} catch (SQLException e) {
    		if(e.getMessage().equals("column nome is not unique"))
    			returnValue = -1;
		}
    	return returnValue;
    }


}
