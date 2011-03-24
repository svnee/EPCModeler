package lu.sven.epcmodeler.mouse.actions;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Node;

public class ChangeNodeAccess extends AbstractAction {
	private static final long serialVersionUID = -5895671065531379981L;
	private String access;
	private Node n;

	public ChangeNodeAccess(Node _n, String _access) {
		this.n = _n;
		this.access = _access;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.n.setAccess(this.access);
		this.n.setPoint(new Point2D.Double(EPCModeler.layout.getX(this.n), EPCModeler.layout.getY(this.n)));
		if(this.access.equals("public")) {
			EPCModeler.pushToPeers(n.toGML(), "/addNode");
		} else {
			EPCModeler.pushToPeers(n.toGML(), "/rmNode");
		}
		
		// TODO: check for group!!!
	}

}
