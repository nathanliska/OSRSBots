package hoWoodcutter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import hoWoodcutter.util.Trees;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.BankAreas;

@SuppressWarnings("serial")
public class hoWoodcutterGUI extends JFrame {

	private static hoWoodcutter context;
	private JPanel contentPane;
	
	private String[] treeNames = new String[Trees.values().length];
	private String[] treeAreaNames = new String[TreeAreas.values().length];
	private String[] bankAreaNames = new String[BankAreas.values().length];
	
	@SuppressWarnings("rawtypes")
	private JComboBox treeType;
	@SuppressWarnings("rawtypes")
	private JComboBox treeArea;
	@SuppressWarnings("rawtypes")
	private JComboBox bankArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hoWoodcutterGUI frame = new hoWoodcutterGUI(context);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] getAllTreeNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			treeNames[i] = Trees.values()[i].getTreeName();
		}
		return treeNames;
	}
	
	private String[] getAllTreeAreaNames() {
		for (int i = 0; i < TreeAreas.values().length; i++) {
			treeAreaNames[i] = TreeAreas.values()[i].getStringArea();
		}
		return treeAreaNames;
	}
	
	private String[] getAllBankAreaNames() {
		for (int i = 0; i < BankAreas.values().length; i++) {
			bankAreaNames[i] = BankAreas.values()[i].getStringArea();
		}
		return bankAreaNames;
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public hoWoodcutterGUI(hoWoodcutter main) {
		
		hoWoodcutterGUI.context = main;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(e -> startButtonActionPerformed(e));
		
		treeType = new JComboBox();
		treeType.setModel(new DefaultComboBoxModel(getAllTreeNames()));
		
		treeArea = new JComboBox();
		treeArea.setModel(new DefaultComboBoxModel(getAllTreeAreaNames()));
		
		bankArea = new JComboBox();
		bankArea.setModel(new DefaultComboBoxModel(getAllBankAreaNames()));
		
		JLabel lblSelectTypeOf = new JLabel("Select type of logs");
		
		JLabel lblNewLabel = new JLabel("Select location");
		
		JLabel lblNewLabel_1 = new JLabel("Select bank");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(treeType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(treeArea, 0, 0, Short.MAX_VALUE)
								.addComponent(bankArea, 0, 88, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel)
								.addComponent(lblSelectTypeOf)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
					.addGap(60))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(treeType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectTypeOf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(treeArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bankArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public Trees getTreeType() {		
		return Trees.values()[treeType.getSelectedIndex()];
	}
	
	public TreeAreas getTreeArea() {
		return TreeAreas.values()[treeArea.getSelectedIndex()];
	}
	
	public BankAreas getBankArea() {
		return BankAreas.values()[bankArea.getSelectedIndex()];
	}
	
	private void startButtonActionPerformed(ActionEvent e) {
		context.setShouldStart(true);
		setVisible(false);
	}
}
