package lu.sven.epcmodeler.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;

import org.apache.log4j.Logger;

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
	
	public static HashSet<InetAddress> getIPAddresses() throws SocketException {
		HashSet<InetAddress> result = new HashSet<InetAddress>();
		Logger.getRootLogger().info("read all NetworkInterfaces");
		for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
			Logger.getRootLogger().info("Found NIC: " + ni.toString());
			for (Enumeration<InetAddress> e = ni.getInetAddresses() ; e.hasMoreElements() ;) {
				InetAddress ia = e.nextElement();
				Logger.getRootLogger().info("Found IP: "+ia.toString());
				result.add(ia);
			}
		}
		
    return result;
	}
}
