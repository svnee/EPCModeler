package lu.sven.epcmodeler.util;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.Action;

import lu.sven.epcmodeler.graph.*;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.io.GraphMLWriter;

public class SaveGraph implements Action {
	Graph<Node, Edge> g;
	Layout<Node, Edge> l;
	
	public SaveGraph(Layout<Node, Edge> _l) {
		this.l = _l;
		Transformers._l = (AbstractLayout<Node, Edge>) this.l;
		this.g = this.l.getGraph();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		save();
	}
	
	public void save() {
		String filename = "cache.xml";
    	
    	GraphMLWriter<Node, Edge> graphWriter = new GraphMLWriter<Node, Edge> ();
    	PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
			graphWriter.setVertexIDs(Transformers.setVertexId);
			
			// Add the X Position
	    	graphWriter.addVertexData("x", null, "0", Transformers.setXValue);
	    	
	    	// Add the Y Position
	    	graphWriter.addVertexData("y", null, "0", Transformers.setYValue);
	    	
	    	// Add a label
	    	graphWriter.addVertexData("label", null, "Node", Transformers.setLabel);
	    	
	    	// Add the NodeType
	    	graphWriter.addVertexData("type", null, "FUNCTION", Transformers.setNodeType);
	    	
	    	// Add the timestamp
	    	graphWriter.addVertexData("timestamp", null, "0", Transformers.setTimestamp);
	    	
	    	// Add the AccessType
	    	graphWriter.addVertexData("access", null, "0", Transformers.setAccess);
	    	
	    	// Add the State
	    	graphWriter.addVertexData("state", null, "0", Transformers.setState);
			
			graphWriter.save(this.g, out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {}

	@Override
	public Object getValue(String arg0) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void putValue(String arg0, Object arg1) {}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {}

	@Override
	public void setEnabled(boolean arg0) {}
}
