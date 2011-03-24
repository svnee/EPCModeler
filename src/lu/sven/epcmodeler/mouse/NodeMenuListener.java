package lu.sven.epcmodeler.mouse;

import lu.sven.epcmodeler.graph.*;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public interface NodeMenuListener<V> {
    void setVertexAndView(V n, VisualizationViewer<V, Edge> vv);    
}
