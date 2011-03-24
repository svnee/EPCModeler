package lu.sven.epcmodeler.mouse;

import java.awt.geom.Point2D;

import lu.sven.epcmodeler.graph.Edge;
import lu.sven.epcmodeler.graph.Node;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public interface CreateMenuListener {
	void setPointAndView(Point2D point, VisualizationViewer<Node, Edge> vv); 
}
