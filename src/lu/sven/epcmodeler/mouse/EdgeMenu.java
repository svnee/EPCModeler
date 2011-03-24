package lu.sven.epcmodeler.mouse;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public class EdgeMenu extends JPopupMenu {        
	private static final long serialVersionUID = 4892999878525670177L;
 
    public EdgeMenu(final JFrame frame) {
        super("Edge Menu");
        this.add(new EdgeMenuItems.IDDisplay());         
    }   
}
