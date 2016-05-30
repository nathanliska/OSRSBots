package hoWoodcutter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import hoWoodcutter.util.BankAreas;
import hoWoodcutter.util.TreeAreas;
import hoWoodcutter.util.Trees;

@SuppressWarnings("serial")
public class hoWoodcutterGUI extends JFrame {

	private hoWoodcutter context;
	private JPanel contentPane;

	private String[] treeNames = new String[Trees.values().length];

	@SuppressWarnings("rawtypes")
	private JComboBox treeType;
	@SuppressWarnings("rawtypes")
	private JComboBox treeArea;
	@SuppressWarnings("rawtypes")
	private JComboBox bankArea;
	
	private JCheckBox worldHopIfAttacked;
	private JCheckBox worldHopIfNoTrees;
	private JCheckBox powerChop;
	
	private static boolean inClient = true;
	
	private ArrayList<String> displayTreeAreas = new ArrayList<>();
	private ArrayList<String> displayBankAreas = new ArrayList<>(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				inClient = false;
				try {
					hoWoodcutterGUI frame = new hoWoodcutterGUI(null);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public hoWoodcutterGUI(hoWoodcutter main) {
		
		context = main;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if(inClient) {
			int x = (int)context.getDreambotFrame().getLocation().getX();
			int y = (int)context.getDreambotFrame().getLocation().getY();
			setBounds(x + 182, y + 225, 400, 250);
		} else {
			setBounds(100, 100, 400, 250);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnStartButton = new JButton("Start");
		btnStartButton.addActionListener(e -> startButtonActionPerformed(e));
		
		treeType = new JComboBox();
		treeArea = new JComboBox();
		bankArea = new JComboBox();
		
		treeType.addActionListener(e -> treeTypeActionPerformed(e));
		treeArea.addActionListener(e -> treeAreaActionPerformed(e));
		
		treeType.setModel(new DefaultComboBoxModel(getAllTreeNames()));
		
		updateDisplayTreeAreas();
		
		treeArea.setModel(new DefaultComboBoxModel(displayTreeAreas.toArray()));
		
		updateDisplayBankAreas();
		
		bankArea.setModel(new DefaultComboBoxModel(displayBankAreas.toArray()));
		
		JLabel lblSelectTypeOf = new JLabel("Select type of logs");

		JLabel lblSelectLocation = new JLabel("Select location");

		JLabel lblSelectBank = new JLabel("Select bank");

		worldHopIfAttacked = new JCheckBox("World Hop if attacked?");
		worldHopIfAttacked.setSelected(true);
		
		worldHopIfNoTrees = new JCheckBox("World Hop if all trees are chopped?");
		worldHopIfNoTrees.setSelected(true);
		
		powerChop = new JCheckBox("Power Chop?");
		powerChop.setSelected(false);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane
								.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(
										Alignment.LEADING).addGroup(gl_contentPane
												.createSequentialGroup().addContainerGap()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(treeType, 0, 0, Short.MAX_VALUE)
														.addComponent(treeArea, 0, 0, Short.MAX_VALUE)
														.addComponent(bankArea, 0, 0, Short.MAX_VALUE)
														.addComponent(worldHopIfAttacked, 0, 88, Short.MAX_VALUE)
														.addComponent(worldHopIfNoTrees, GroupLayout.DEFAULT_SIZE,
																88, Short.MAX_VALUE)
														.addComponent(powerChop, 0,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblSelectTypeOf)
														.addComponent(lblSelectLocation)
														.addComponent(lblSelectBank)))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(120)
												.addComponent(
												btnStartButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
								.addGap(60)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(treeType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectTypeOf))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(treeArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectLocation))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(bankArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectBank))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(worldHopIfAttacked, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(worldHopIfNoTrees, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(powerChop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
						.addComponent(btnStartButton)));
		contentPane.setLayout(gl_contentPane);
	}

	private String[] getAllTreeNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			treeNames[i] = Trees.values()[i].getTreeName();
		}
		return treeNames;
	}

	public Trees getTreeType() {
		for(int i = 0; i < Trees.values().length; i++) {
			if(Trees.values()[i].getTreeName().equals(treeType.getSelectedItem())) {
				return Trees.values()[i];
			}
		}
		hoWoodcutter.log("Something wen't wrong in type");
		return Trees.values()[treeType.getSelectedIndex()];
	}

	public TreeAreas getTreeArea() {
		for(int i = 0; i < TreeAreas.values().length; i++) {
			if(TreeAreas.values()[i].getAreaName().equals(treeArea.getSelectedItem())) {
				return TreeAreas.values()[i];
			}
		}
		hoWoodcutter.log("Something wen't wrong in tree area");
		return null;
	}

	public BankAreas getBankArea() {
		for(int i = 0; i < BankAreas.values().length; i++) {
			if(BankAreas.values()[i].getAreaName().equals(bankArea.getSelectedItem())) {
				return BankAreas.values()[i];
			}
		}
		hoWoodcutter.log("Something wen't wrong in bank area");
		return BankAreas.values()[bankArea.getSelectedIndex()];
	}

	public boolean getWorldHopIfAttacked() {
		return worldHopIfAttacked.isSelected();
	}
	
	public boolean getWorldHopIfNoTrees() {
		return worldHopIfNoTrees.isSelected();
	}
	
	public boolean getPowerChop() {
		return powerChop.isSelected();
	}

	private void startButtonActionPerformed(ActionEvent e) {
		context.setShouldStart(true);
		setVisible(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void treeTypeActionPerformed(ActionEvent e) {
		updateDisplayTreeAreas();
		
		treeArea.setModel(
				new DefaultComboBoxModel(displayTreeAreas.toArray()));
		
		updateDisplayBankAreas();
		
		bankArea.setModel(
				new DefaultComboBoxModel(displayBankAreas.toArray()));
	}
	
	private void updateDisplayTreeAreas() {
		ArrayList<String> temp = new ArrayList<>();
		for(int i = 0; i < TreeAreas.values().length; i++) {
			if(TreeAreas.values()[i].getAreaContains(getTreeType())) {
				temp.add(TreeAreas.values()[i].getAreaName());
			}
		}
		displayTreeAreas = temp;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void treeAreaActionPerformed(ActionEvent e) {
		updateDisplayBankAreas();
		bankArea.setModel(
				new DefaultComboBoxModel(displayBankAreas.toArray()));
	}
	
	private void updateDisplayBankAreas() {
		if(getTreeArea() == null) {
			displayBankAreas = new ArrayList<>();
			return;
		}
		String[] temp = getTreeArea().getBankAreasStrings();
		ArrayList<String> temp2 = new ArrayList<>();
		for(int i = 0; i < temp.length; i++) {
			temp2.add(temp[i]);
		}
		displayBankAreas = temp2;
	}
}
