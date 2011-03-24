package lu.sven.epcmodeler.graph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.util.EdgeType;

public class OurObservableGraph extends ObservableGraph<Node, Edge> {

	private static final long serialVersionUID = 5085861866496476266L;

	public OurObservableGraph(Graph<Node, Edge> delegate) {
		super(delegate);
	}
	
	/**
	 * @see edu.uci.ics.jung.graph.Graph#addEdge(java.lang.Object, java.lang.Object, java.lang.Object, edu.uci.ics.jung.graph.util.EdgeType)
	 */
	@Override
  public boolean addEdge(Edge e, Node v1, Node v2, EdgeType edgeType) {
		boolean state = super.addEdge(e, v1, v2, EdgeType.DIRECTED);
		if(state) {
			GraphEvent<Node,Edge> evt = new GraphEvent.Edge<Node,Edge>(delegate, GraphEvent.Type.EDGE_ADDED, e);
			fireGraphEvent(evt);
		}
		return state;
	}

	/**
	 * @see edu.uci.ics.jung.graph.Graph#addEdge(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
  public boolean addEdge(Edge e, Node v1, Node v2) {
		boolean state = super.addEdge(e, v1, v2, EdgeType.DIRECTED);
		if(state) {
			GraphEvent<Node,Edge> evt = new GraphEvent.Edge<Node,Edge>(delegate, GraphEvent.Type.EDGE_ADDED, e);
			fireGraphEvent(evt);
		}
		return state;
	}

}
