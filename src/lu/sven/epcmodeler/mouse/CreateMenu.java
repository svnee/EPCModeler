package lu.sven.epcmodeler.mouse;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public class CreateMenu extends JPopupMenu {        
	private static final long serialVersionUID = 4892999878525670177L;
 
    public CreateMenu(final JFrame frame) {
        super("Create");
        this.add("To add a node, please choose Mode > EDITING");         
    }   
}
