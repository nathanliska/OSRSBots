package hoWoodcutter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	
	private JCheckBox worldHop;
	private JCheckBox powerChop;
	
	private ArrayList<String> displayTreeAreas = new ArrayList<>();
	private ArrayList<String> displayBankAreas = new ArrayList<>(); 

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

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public hoWoodcutterGUI(hoWoodcutter main) {

		hoWoodcutterGUI.context = main;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 325, 250);
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

		worldHop = new JCheckBox("World Hop if attacked?");
		worldHop.setSelected(true);
		
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
														.addComponent(treeType, 0, GroupLayout.DEFAULT_SIZE, 
																Short.MAX_VALUE)
														.addComponent(treeArea, 0, 0,
																Short.MAX_VALUE)
														.addComponent(bankArea, 0, 0, Short.MAX_VALUE)
														.addComponent(worldHop, GroupLayout.DEFAULT_SIZE,
																88, Short.MAX_VALUE)
														.addComponent(powerChop, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblSelectTypeOf)
														.addComponent(lblSelectLocation)
														.addComponent(lblSelectBank)))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(34)
												.addComponent(
												btnStartButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
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
								.addComponent(worldHop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(powerChop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
						.addComponent(btnStartButton)));
		contentPane.setLayout(gl_contentPane);
	}

	public boolean getWorldHop() {
		return worldHop.isSelected();
	}
	
	public boolean getPowerChop() {
		return powerChop.isSelected();
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
		return TreeAreas.values()[treeArea.getSelectedIndex()];
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
	
	private String[] getAllTreeNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			treeNames[i] = Trees.values()[i].getTreeName();
		}
		return treeNames;
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
		String[] temp = getTreeArea().getBankAreasStrings();
		ArrayList<String> temp2 = new ArrayList<>();
		for(int i = 0; i < temp.length; i++) {
			temp2.add(temp[i]);
		}
		displayBankAreas = temp2;
	}
}
