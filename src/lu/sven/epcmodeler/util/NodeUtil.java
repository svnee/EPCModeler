package lu.sven.epcmodeler.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.Graph;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

public class NodeUtil {

	public static Node getNodeById(String Id, Graph<Node,Edge> g){
		
		Node returnNode=null;
		
		Collection<Node> nodeList = g.getVertices();
		
		for(Node n:nodeList){
			if((n.getID()).equals(Id)){
				returnNode=n;
				break;
			}
		}
		return returnNode;
	}
	
	public static List<InetAddress> getAllowedPeers(String input){
		
		List<InetAddress> inetAddr = new LinkedList<InetAddress>();
		
		String[] addresses = input.split(",");

		for(String s: addresses){
			s= s.trim();
			try {
				inetAddr.add(InetAddress.getByName(s));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		return inetAddr;
		
	}
}
