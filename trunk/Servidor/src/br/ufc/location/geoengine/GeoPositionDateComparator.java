package geoengine;

import java.util.Comparator;
import java.util.Date;

import facade.IGeoPosition;

public class GeoPositionDateComparator  implements Comparator{
   
    public int compare(Object dev1, Object dev2){
    	Date date1 = ((IGeoPosition)dev1).getDate();        
        Date date2 = ((IGeoPosition)dev2).getDate();
       
        return date1.compareTo(date2);
    }

}
