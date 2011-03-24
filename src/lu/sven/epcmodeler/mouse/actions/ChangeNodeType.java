package lu.sven.epcmodeler.mouse.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;
import lu.sven.epcmodeler.graph.NodeType;

public class ChangeNodeType extends AbstractAction {
	private static final long serialVersionUID = 764855208327279821L;
	private Node n;
	private NodeType t;
	private VisualizationViewer<Node, Edge> vv;
	
	public ChangeNodeType(Node _n, NodeType _t, VisualizationViewer<Node, Edge> _vv) {
		this.n = _n;
		this.t = _t;
		this.vv = _vv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.n.setNodeType(this.t);
		EPCModeler.pushToPeers(n.toGML(), "/updateNode");
		this.vv.repaint();
	}
}

