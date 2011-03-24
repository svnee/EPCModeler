package lu.sven.epcmodeler.http;

import java.io.IOException;

import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

import org.apache.log4j.Logger;

import edu.uci.ics.jung.graph.Graph;

public class HTTPServer {
	RequestListenerThread t = null;
	Logger logger = Logger.getRootLogger();
	public Graph<Node,Edge> graph;
	
	public HTTPServer(Graph<Node, Edge> g) {
		try {
			this.graph = g;
			t = new RequestListenerThread(1337, this.graph);
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
