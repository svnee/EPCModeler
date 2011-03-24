package lu.sven.epcmodeler.mouse;

import javax.swing.JMenuItem;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import lu.sven.epcmodeler.graph.*;

public class EdgeMenuItems {
	public static class IDDisplay extends JMenuItem implements EdgeMenuListener<Edge> {
		private static final long serialVersionUID = 792389276684856523L;

		public void setEdgeAndView(Edge e, VisualizationViewer<Node, Edge> vv) {
            this.setText("ID: "+e.getID());
        }
    }
}
