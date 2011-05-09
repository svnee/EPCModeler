package lu.sven.epcmodeler.http;

import java.io.IOException;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

import org.apache.log4j.Logger;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;

public class HTTPServer {
	RequestListenerThread t = null;
	Logger logger = Logger.getRootLogger();
	public Graph<Node,Edge> graph;
	public Layout<Node, Edge> layout;
	
	public HTTPServer(Graph<Node, Edge> g, Layout<Node, Edge> l) {
		try {
			this.graph = g;
			this.layout = l;
			t = new RequestListenerThread(EPCModeler.port, this.graph, this.layout);
			t.setDaemon(false);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		t.interrupt();
	}
}
