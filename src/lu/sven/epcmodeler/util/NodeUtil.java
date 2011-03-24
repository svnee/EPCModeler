package lu.sven.epcmodeler.util;

import java.util.Collection;

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
}
