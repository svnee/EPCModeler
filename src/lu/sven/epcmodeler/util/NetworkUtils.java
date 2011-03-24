package lu.sven.epcmodeler.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

public class NetworkUtils {
	
	public static String getMacAddress() throws SocketException { 
	  String result = ""; 
	 
	  for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) { 
	    byte[] hardwareAddress = ni.getHardwareAddress(); 
	 
	    if (hardwareAddress != null) { 
	      for (int i = 0; i < hardwareAddress.length; i++) 
	        result += String.format((i==0?"":"-")+"%02X", hardwareAddress[i]); 
	 
	      return result; 
	    } 
	  } 
	 
	  return result; 
	}
}
