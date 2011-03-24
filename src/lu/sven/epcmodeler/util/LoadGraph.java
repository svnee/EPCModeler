package lu.sven.epcmodeler.util;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.EdgeFactory;
import lu.sven.epcmodeler.graph.Node;
import lu.sven.epcmodeler.graph.NodeFactory;
import lu.sven.epcmodeler.graph.NodeType;
import lu.sven.epcmodeler.graph.NodeVisibility;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.io.GraphIOException;
import edu.uci.ics.jung.io.graphml.EdgeMetadata;
import edu.uci.ics.jung.io.graphml.GraphMLReader2;
import edu.uci.ics.jung.io.graphml.GraphMetadata;
import edu.uci.ics.jung.io.graphml.GraphMetadata.EdgeDefault;
import edu.uci.ics.jung.io.graphml.HyperEdgeMetadata;
import edu.uci.ics.jung.io.graphml.NodeMetadata;

public class LoadGraph {
	
	public LoadGraph() {}
	
	public static Graph<Node, Edge> load() {
		String filename = "cache.xml";
		try {
			BufferedReader fileReader = new BufferedReader(
			        new FileReader(filename));
			
			/* Create the Graph Transformer */
			 Transformer<GraphMetadata, Graph<Node, Edge>>
			 graphTransformer = new Transformer<GraphMetadata,
			                           Graph<Node, Edge>>() {

			   public Graph<Node, Edge>
			       transform(GraphMetadata metadata) {
			         metadata.getEdgeDefault();
					if (metadata.getEdgeDefault().equals(
			         EdgeDefault.DIRECTED)) {
			             return new DirectedSparseMultigraph<Node, Edge>();
			         } else {
			             return new
			             UndirectedSparseMultigraph<Node, Edge>();
			         }
			       }
			 };
			 
			 /* Create the Vertex Transformer */
			 Transformer<NodeMetadata, Node> vertexTransformer
			 = new Transformer<NodeMetadata, Node>() {
			     public Node transform(NodeMetadata metadata) {
			    	 Node n =
			             NodeFactory.getInstance().create();
			         // Set the saved values
			    	 n.setID(metadata.getId());
			         n.setLabel(metadata.getProperty("label"));
			         n.setNodeType(NodeType.valueOf(metadata.getProperty("type")));
			         n.setState(NodeVisibility.valueOf(metadata.getProperty("state")));
			         n.setTimeStamp(metadata.getProperty("timestamp"));
			         // Set position
			         Point2D p = new Point2D.Double(
			        		 Double.parseDouble(metadata.getProperty("x")),
			        		 Double.parseDouble(metadata.getProperty("y"))
			         	);
			         n.setPoint(p);
			         
			         return n;
			     }
			 };
			 
			 /* Create the Edge Transformer */
			 Transformer<EdgeMetadata, Edge> edgeTransformer =
			 new Transformer<EdgeMetadata, Edge>() {
			     public Edge transform(EdgeMetadata metadata) {
			    	 Edge e = EdgeFactory.getInstance().create();
			         return e;
			     }
			 };
			 
			 // We do not have hyperedges, but it is needed by the interface
			 Transformer<HyperEdgeMetadata, Edge> hyperEdgeTransformer =
				 new Transformer<HyperEdgeMetadata, Edge>() {
				     public Edge transform(HyperEdgeMetadata metadata) {
				    	 Edge e = EdgeFactory.getInstance().create();
				         return e;
				     }
				 };
			
			/* Create the graphMLReader2 */
			GraphMLReader2<Graph<Node, Edge>, Node, Edge>
			graphReader = new GraphMLReader2<Graph<Node, Edge>, Node, Edge>
				(fileReader, graphTransformer, vertexTransformer,
				edgeTransformer, hyperEdgeTransformer);
			
			try {
			    /* Get the new graph object from the GraphML file */
			    Graph<Node, Edge> g = (DirectedGraph<Node, Edge>) graphReader.readGraph();
			    return g;
			} catch (GraphIOException ex) {}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
