package br.ufc.location.facade;

import br.ufc.location.geoengine.DevicePath;
import br.ufc.location.geoengine.IGeoPosition;

/**
 * Interface que define as informações de um dispositivo movel ( jogado, mina, munição etc.)
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
	 * Seta a interface da posição corrente
	 * @param position
	 */
	void               setGeoPosition(IGeoPosition position);
	/**
	 * Retorna a interface da posição corrente
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
	 * Retorna o grupo que o usário pertence
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
	 * Seta o listener que é atuado quando o servidor de georeferenciamento
	 * detectar situação de proximidade
	 * @param listener
	 */
	void  setProximityListener(IProximityListener listener);
	/**
	 * Retorna o listener que é atuado quando o servidor de georeferenciamento
	 * detectar situação de proximidade
	 * @return
	 */
	IProximityListener               getProximityListener();
	/**
	 * Converte as informaçoes do dispositivo em parametros XML
	 * @return
	 */
	String toXML();
}
