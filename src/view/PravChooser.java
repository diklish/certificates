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
import javax.swing.table.TableRowSorter;
import org.orm.PersistentException;
import TableModel.AdminPravTableModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * A Prav chooser that is shown inside a dialog
 */
class PravChooser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1001553496811328337L;
	//private AdminPravTableModel adminPravModel;
	private JTable jTableListPrav;

	public PravChooser(AdminPravTableModel adminPravModel) throws PersistentException {

		SwingUtilities.updateComponentTreeUI(this);
		jTableListPrav = new JTable(adminPravModel);

		jTableListPrav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				adminPravModel);
		jTableListPrav.setRowSorter(sorter);

		// Клик по сторочке
		jTableListPrav.addMouseListener(new MouseListener() {

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
		JLabel label = new JLabel("Фильтр");
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
					} catch (PatternSyntaxException pse) {
						System.err.println("Bad regex pattern");
					}
				}

			}
		});
		// Клик по кнопке фильтер
		JButton button = new JButton("Фильтр");
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
					} catch (PatternSyntaxException pse) {
						System.err.println("Bad regex pattern");
					}
				}
			}
		});

		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int rowIndex = jTableListPrav.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(dialog, "Объект не выбран.",
							"Ошибка", JOptionPane.ERROR_MESSAGE);
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

		JScrollPane panelTable = new JScrollPane(jTableListPrav);
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


		
	
public int getPrav(int selectTab) throws PersistentException {
		
		int i = Integer.parseInt(jTableListPrav.getValueAt(
				jTableListPrav.getSelectedRow(), 0).toString());
		
		
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
