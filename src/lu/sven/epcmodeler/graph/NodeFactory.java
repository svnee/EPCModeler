package lu.sven.epcmodeler.graph;

import java.awt.Dimension;
import java.awt.Point;
import java.net.SocketException;

import lu.sven.epcmodeler.util.NetworkUtils;

import org.apache.commons.collections15.Factory;

public class NodeFactory implements Factory<Node> {
	private static int nodeCount = 0;
	private static NodeType defaultNodeType = NodeType.EVENT;
	private static String defaultAccessType = "public";
	private static NodeVisibility defaultState = NodeVisibility.VISIBLE;
	private static Point defaultPosition = new Point(0,0);
	private static Dimension defaultDimension = new Dimension(90,30);
    private static NodeFactory instance = new NodeFactory();
	
    private NodeFactory() {            
    }
    
    public static NodeFactory getInstance() {
        return instance;
    }
    
    public Node create() {
        String label = "Node" + nodeCount;
        String id;
		try {
			String uuid = NetworkUtils.getMacAddress();
			if(uuid.equals("")) {
				try {
					java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
					uuid = localMachine.getHostName();
					}
					catch (java.net.UnknownHostException uhe) { 
						// handle exception
					}
			}
			id = "NODE::" + uuid + "::" + nodeCount;
		} catch (SocketException e) {
			// TODO: log the error on WARN
			id = "NODE::nil::" + nodeCount;
		}
        String ts = new Long(System.currentTimeMillis() / 1000).toString();
        
        Node n = new Node(label, id, defaultNodeType, defaultPosition, defaultDimension, defaultAccessType, ts, defaultState);
        nodeCount++;
        return n;
    }
}
