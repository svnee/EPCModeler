package lu.sven.epcmodeler.util;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JList;

import org.apache.log4j.Logger;

import edu.uci.ics.jung.algorithms.cluster.WeakComponentClusterer;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

import lu.sven.epcmodeler.EPCViewer;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

public class getSubGraphs implements Action{
	
	JList list;
	
	public getSubGraphs(JList l) {
		this.list = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Logger log = Logger.getRootLogger();
		log.debug("Get subGraphs started!");
		log.debug("Generate undirected graph");
		UndirectedSparseMultigraph<Node, Edge> g = new UndirectedSparseMultigraph<Node, Edge>();
		
		for (Node n : EPCViewer.EPCGraph.getVertices()) {
			g.addVertex(n);
		}
		
		for (Edge e : EPCViewer.EPCGraph.getEdges()) {
			g.addEdge(e, EPCViewer.EPCGraph.getDest(e), EPCViewer.EPCGraph.getSource(e));
		}
		
		log.debug("undirected graph generated");
		
		WeakComponentClusterer<Node, Edge> wcc = new WeakComponentClusterer<Node, Edge>();
		Set<Set<Node>> components = wcc.transform(g);
		
		log.debug("We found "+components.size()+" components!");
		
		for(Set<Node> component : components) {
			log.debug("The component has " + component.size() + " elements");
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void putValue(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

}
