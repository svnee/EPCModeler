package lu.sven.epcmodeler.mouse.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;

public class DeleteNode extends AbstractAction {
	private static final long serialVersionUID = 764855208327279821L;
	private Node n;
	private VisualizationViewer<Node, Edge> vv;
	
	public DeleteNode(Node _n, VisualizationViewer<Node, Edge> _vv) {
		this.n = _n;
		this.vv = _vv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.vv.getGraphLayout().getGraph().removeVertex(this.n);
		EPCModeler.pushToPeers(n.toGML(), "/rmNode");
		this.vv.repaint();
	}
}

