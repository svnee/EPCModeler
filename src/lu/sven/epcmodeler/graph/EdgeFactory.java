package lu.sven.epcmodeler.graph;

import java.net.SocketException;

import lu.sven.epcmodeler.util.NetworkUtils;

import org.apache.commons.collections15.Factory;

public class EdgeFactory implements Factory<Edge> {
	private static int edgeCount = 0;
	private static EdgeFactory instance = new EdgeFactory();
	
	private EdgeFactory() {            
    }
    
    public static EdgeFactory getInstance() {
        return instance;
    }
    
    public Edge create() {
        String id;
		try {
			id = "EDGE::"+NetworkUtils.getMacAddress() + "::" + edgeCount;
		} catch (SocketException e) {
			// TODO: log the error on WARN
			id = "EDGE::nil::" + edgeCount;
		}
        String ts = new Long(System.currentTimeMillis() / 1000).toString();
        
        Edge e = new Edge(id, ts);
        edgeCount++;
        return e;
    }
}
