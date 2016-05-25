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

public class hoWoodcutterGUI extends JFrame {

	private static hoWoodcutter context;
	private JPanel contentPane;
	
	private String[] treeNames = new String[Trees.values().length];
	private String[] treeAreaNames = new String[TreeAreas.values().length];
	private String[] bankAreaNames = new String[BankAreas.values().length];

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
		for (int i = 0; i < Trees.values().length; i++) {
			treeAreaNames[i] = Trees.values()[i].getTreeName();
		}
		return treeAreaNames;
	}
	
	private String[] getAllBankAreaNames() {
		for (int i = 0; i < Trees.values().length; i++) {
			bankAreaNames[i] = Trees.values()[i].getTreeName();
		}
		return bankAreaNames;
	}

	/**
	 * Create the frame.
	 */
	private hoWoodcutterGUI(hoWoodcutter main) {
		
		hoWoodcutterGUI.context = main;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(e -> startButtonActionPerformed(e));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(getAllTreeNames()));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(getAllTreeAreaNames()));

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(getAllBankAreaNames()));
		
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
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_2, 0, 0, Short.MAX_VALUE)
								.addComponent(comboBox_1, 0, 88, Short.MAX_VALUE))
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
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectTypeOf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void startButtonActionPerformed(ActionEvent e) {
		context.setShouldStart(true);
	}
}