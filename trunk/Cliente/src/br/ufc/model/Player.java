package br.ufc.model;



/**
 * @author Andr� Fonteles, Rafael de Lima e Benedito Neto
 *
 * Class that defines a player
 * 
 */
public class Player extends MapObject {
	
	public static final int CANGACEIRO = 1;
	public static final int JAGUNCO = 2;
	
	public static final int MUNICIADOR = 1;
	public static final int MEDICO     = 2;
	public static final int ENGENHEIRO = 3;
	public static final int ESPIAO     = 4;
	
	private String nome;
	private int tipo;
	private int papel;

	public Player(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Player(String nome, int tipo, int papel) {
		super();
		this.nome  = nome;
		this.tipo  = tipo;
		this.papel = papel;
	}

	public Player(String nome) {
		super();
		this.nome = nome;
	}

	public Player(String nome, int tipo, double latitude, double longitude) {
		super(latitude, longitude);
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Player(String nome, int tipo, int papel, double latitude, double longitude) {
		super(latitude, longitude);
		this.nome = nome;
		this.tipo = tipo;
		this.papel = papel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getPapel() {
		return papel;
	}

	public void setPapel(int papel) {
		this.papel = papel;
	}
}