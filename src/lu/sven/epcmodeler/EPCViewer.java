package lu.sven.epcmodeler;

import java.io.File;

import org.apache.commons.collections15.Factory;

import lu.sven.epcmodeler.graph.*;
import lu.sven.epcmodeler.util.GraphChangeListener;
import lu.sven.epcmodeler.util.LoadGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;

public class EPCViewer {
	public static Graph<Node, Edge> EPCGraph;
	int nodeCount, edgeCount;
	ObservableGraph<Node, Edge> OBSGraph;
	
	Factory<Node> vertexFactory;
    Factory<Edge> edgeFactory;
    
    /** Creates a new instance of SimpleGraphView */
    public EPCViewer() {
    	// Check if a cached version exists  
    	File fp = new File("cache.xml");
    	if (fp.exists()) {
    		EPCGraph = LoadGraph.load();
    	} else {
    		EPCGraph = new DirectedSparseMultigraph<Node, Edge>();
    	}
    	
    	EPCGraph = new OurObservableGraph(EPCGraph);
    	GraphChangeListener gel = new GraphChangeListener();
    	((OurObservableGraph) EPCGraph).addGraphEventListener(gel);
    	
    	
    	nodeCount = EPCGraph.getVertexCount();
    	edgeCount = EPCGraph.getEdgeCount();;
    	
    	vertexFactory = NodeFactory.getInstance();
    	edgeFactory = EdgeFactory.getInstance();
    }
}
