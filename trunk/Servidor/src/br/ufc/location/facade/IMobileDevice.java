package br.ufc.location.facade;

import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.geoengine.IGeoPosition;

/**
 * Interface que define as informa��es de um dispositivo movel ( jogado, mina, muni��o etc.)
 * @author Danilo Reis
 *
 */
public interface IMobileDevice {
	/**
	 * Retorna o identificador do dispositivo
	 * @return
	 */
	Integer                                          getId();
	/**
	 * Seta a interface da posi��o corrente
	 * @param position
	 */
	void               setGeoPosition(IGeoPosition position);
	/**
	 * Retorna a interface da posi��o corrente
	 * @return
	 */
	IGeoPosition                            getGeoPosition();
	/**
	 * Calcula a distancia entre o dispositivo e o dispositivo passado como parametro
	 * @param device dispositivo a ser  calculada a distancia
	 * @return distancia em metros do dispositivo
	 */
	double                   getDistanceFrom(IMobileDevice device);
	/**
	 * Retorna o valor da ultima distancia calculada
	 * @return
	 */
	double                                 getLastDistance();
	/**
	 * Retorna o tipo do dispositivo
	 * @return
	 */
	Integer                                        getType();
	/**
	 * Seta o tipo do dispositivo
	 * @param type tipo do dispositivo
	 */
	void                               setType(Integer type);
	/**
	 * Seta distancia na qual limite para um dispositivo ser considerado sobre
	 * @param distancia em metros
	 */
	void                           setDistanceOn(float dist);
	/**
	 * Seta o grupo que o dispositivo pertence
	 * @param group
	 */
	void                             setGroup(Integer group);
	/**
	 * Retorna o grupo que o us�rio pertence
	 * @return
	 */
	Integer                                       getGroup();
	/**
	 * Retorna a distancia limite para um dispositivo ser considerado sobre
	 * @return
	 */
	float                                   getDistanceOn();
	/**
	 * Seta o objeto de controle da rota realizada  do dispositivo
	 * @param path
	 */
	void                     setDevicePath(DevicePath path);
	/**
	 * Retorna o objeto de controle da rota realizada do dispositivo
	 * @return
	 */
	DevicePath                              getDevicePath();
	/** 
	 * Seta o listener que � atuado quando o servidor de georeferenciamento
	 * detectar situa��o de proximidade
	 * @param listener
	 */
	void  setProximityListener(IProximityListener listener);
	/**
	 * Retorna o listener que � atuado quando o servidor de georeferenciamento
	 * detectar situa��o de proximidade
	 * @return
	 */
	IProximityListener               getProximityListener();
	/**
	 * Converte as informa�oes do dispositivo em parametros XML
	 * @return
	 */
	String toXML();
}
