package lu.sven.epcmodeler.mouse.dialogs;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import lu.sven.epcmodeler.EPCModeler;
import lu.sven.epcmodeler.graph.Node;

public class NodeAccessDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = -7166671767827909370L;
	Node node;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    
	public NodeAccessDialog(java.awt.Frame parent, Node _node) {
        super(parent, true);
        this.node = _node;
        setTitle("Access for " + node.toString());
        setSize(400, 120);
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonHandler(evt);
            }
        });

        jTextField1.setText("");
        jLabel1.setText("This node is shared with the following peers:");
        jButton1.setText("OK");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);    
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1).addComponent(jTextField1).addComponent(jButton1));
        hGroup.addGroup(layout.createParallelGroup());
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jTextField1));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jButton1));
        layout.setVerticalGroup(vGroup);
    }
	private void okButtonHandler(java.awt.event.ActionEvent evt) {
		node.setAccess(this.jTextField1.getText());
		EPCModeler.pushToPeers(node.toGML(), "/updateNode");
		dispose();
	}
}
