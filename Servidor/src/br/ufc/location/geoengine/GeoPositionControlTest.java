package br.ufc.location.geoengine;

import java.util.Date;
import java.util.List;

import br.ufc.business.commands.CmdRegister;

public class GeoPositionControlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String []               params;
		CmdRegister             cmdReg;
		String                    resp;
		DevicesPositionControl control;
		GeoPosition           position;
		double                latitude;
		double               longitude;
		
		params = new String [10];
		
		params[0] ="Danilo";
		params[1] ="1";
		params[2] ="1";
		params[3] ="-3.717381";
		params[4] ="38.539906";
		
        cmdReg = new CmdRegister();
        resp = cmdReg.execute(params);
        
        System.out.println("Register test: " + resp);
        
		params[0] ="Fabio";
		params[1] ="1";
		params[2] ="1";
        params[3] ="-3.717123";
        params[4] ="38.540081";

        resp = cmdReg.execute(params);
        
        System.out.println("Register test: " + resp);
        
        latitude  = Double.parseDouble("-3.717381");
        longitude = Double.parseDouble("38.539986");
        Date now  = new Date();
        position  = new GeoPosition(now, latitude, longitude);
        control   = DevicesPositionControl.getInstance();
        control.updateDevicePosition(new Integer(1), position);
        
        List list = control.getDevicesByGroup(1);
	}

}
