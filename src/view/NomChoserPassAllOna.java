package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import org.orm.PersistentException;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * A Prav chooser that is shown inside a dialog
 */

class NomChoserPassAllOne extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5130725549606018500L;
	// private AdminPravTableModel adminPravModel;
	// private JTable jTableListPrav;
	final JTable jTableList;

	public NomChoserPassAllOne(JTable jTableListS, final RowSorter<TableModel> sorter) {
		jTableList =jTableListS;
		SwingUtilities.updateComponentTreeUI(this);
		
		jTableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jTableList.setRowSorter(sorter);

		// ���� �� ��������
		jTableList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ((arg0.getClickCount() == 2)
						&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
					ok = true;
					dialog.setVisible(false);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("������");
		panel.add(label, BorderLayout.WEST);

		final JTextField filterText = new JTextField("");
		panel.add(filterText, BorderLayout.CENTER);

		filterText.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {

				String text = filterText.getText();
				if (text.length() == 0) {
					((DefaultRowSorter<TableModel, Integer>) sorter)
							.setRowFilter(null);
				} else {
					try {
						((DefaultRowSorter<TableModel, Integer>) sorter)
								.setRowFilter(RowFilter.regexFilter(text));
						jTableList.setRowSorter(sorter);
						jTableList.repaint();
					} catch (PatternSyntaxException pse) {
						System.err.println("Bad regex pattern");
					}
				}

			}
		});
		// ���� �� ������ �������
		JButton button = new JButton("������");
		button.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String text = filterText.getText();
				if (text.length() == 0) {
					((DefaultRowSorter<TableModel, Integer>) sorter)
							.setRowFilter(null);
				} else {
					try {
						((DefaultRowSorter<TableModel, Integer>) sorter)
								.setRowFilter(RowFilter.regexFilter(text));
						jTableList.setRowSorter(sorter);
						jTableList.repaint();
					} catch (PatternSyntaxException pse) {
						System.err.println("Bad regex pattern");
					}
				}
			}
		});

		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int rowIndex = jTableList.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(dialog, "������ �� ������.",
							"������", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ok = true;
				dialog.setVisible(false);

			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialog.setVisible(false);
			}
		});

		JScrollPane panelTable = new JScrollPane(jTableList);
		panel.add(button, BorderLayout.EAST);

		FormLayout layout = new FormLayout(
				"fill:default:grow, fill:default:grow, fill:default:grow",
				"fill:p:grow, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// , new FormDebugPanel()
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(panel, cc.xy(2, 2));
		builder.add(panelTable, cc.xy(2, 4));

		builder.add(
				ButtonBarFactory.buildAddRemoveRightBar(okButton, cancelButton),
				cc.xy(2, 6));

		add(builder.getPanel());

	}

	public int getDate(int selectTab) throws PersistentException {

		int i = Integer.parseInt(jTableList.getValueAt(
				jTableList.getSelectedRow(), 0).toString());

		return i;
	}

	/**
	 * Show the chooser panel in a dialog
	 * 
	 * @param parent
	 *            a component in the owner frame or null
	 * @param title
	 *            the dialog window title
	 */
	public boolean showDialog(Component parent, String title) {
		ok = false;

		// locate the owner frame

		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class,
					parent);

		// if first time, or if owner has changed, make new dialog

		if (dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}

		// set title and show dialog

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}

	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
}
