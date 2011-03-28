package lu.sven.epcmodeler.mouse.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;
import lu.sven.epcmodeler.mouse.MenuPointListener;
import lu.sven.epcmodeler.mouse.NodeMenuListener;
import lu.sven.epcmodeler.mouse.dialogs.NodeLabelDialog;

public class NodeChangeLabel extends JMenuItem implements NodeMenuListener<Node>, MenuPointListener {
	private static final long serialVersionUID = -5484627609659220059L;
	Node node;
	VisualizationViewer<Node, Edge> vv;
	Point2D point;
	
	public NodeChangeLabel(final JFrame frame) {            
        super("Set label");
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	NodeLabelDialog dialog = new NodeLabelDialog(frame, node);
                dialog.setLocation((int)point.getX()+ frame.getX(), (int)point.getY()+ frame.getY());
                dialog.setVisible(true);
            }   
        });
    }
	
	@Override
	public void setPoint(Point2D _point) {
		this.point = _point;
	}

	@Override
	public void setVertexAndView(Node n, VisualizationViewer<Node, Edge> _vv) {
		this.node = n;
		this.vv = _vv;
	}

}
