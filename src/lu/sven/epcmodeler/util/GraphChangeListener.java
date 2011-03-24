package lu.sven.epcmodeler.util;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.EPCViewer;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.event.GraphEventListener;

public class GraphChangeListener implements GraphEventListener<Node, Edge> {

	@Override
	public void handleGraphEvent(GraphEvent<Node, Edge> evt) {
		switch(evt.getType()) {
		case VERTEX_ADDED:
			GraphEvent.Vertex<Node, Edge> event = (GraphEvent.Vertex<Node, Edge>) evt;
			Node n = event.getVertex();
			if(!EPCModeler.receivedNodes.contains(n)) {
				EPCModeler.pushToPeers(n.toGML(), "/addNode");
			}
			break;
		case EDGE_ADDED:
			GraphEvent.Edge<Node, Edge> edge_event = (GraphEvent.Edge<Node, Edge>) evt;
			Edge e = edge_event.getEdge();
			Node dest = EPCViewer.EPCGraph.getDest(e);
			Node start = EPCViewer.EPCGraph.getSource(e);
			String gml = "<edge source=\""+start.getID()+"\" target=\""+dest.getID()+"\" />";
			EPCModeler.pushToPeers(gml, "/addEdge");
			break;
		}
	}

}
