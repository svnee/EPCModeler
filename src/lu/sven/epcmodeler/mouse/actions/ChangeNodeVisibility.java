package lu.sven.epcmodeler.mouse.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Node;
import lu.sven.epcmodeler.graph.NodeVisibility;

public class ChangeNodeVisibility extends AbstractAction {
	private static final long serialVersionUID = 764855208327279821L;
	private Node n;
	private NodeVisibility v;
	
	public ChangeNodeVisibility(Node _n, NodeVisibility _v) {
		this.n = _n;
		this.v = _v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.n.setState(this.v);
		EPCModeler.pushToPeers(n.toGML(), "/updateNode");
	}
}

