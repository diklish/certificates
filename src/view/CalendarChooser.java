package view;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;

/**
 * A Prav chooser that is shown inside a dialog
 */

class CalendarChoser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6567301823694629813L;
	private JCalendar datePartT;

	public CalendarChoser(JCalendar datePart) {
		datePartT = new JCalendar();
	//	SwingUtilities.updateComponentTreeUI(this);
		JButton submit = new JButton("Ok");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ok = true;
			//	dialog.dispose();
				dialog.setVisible(false);
			}
		});

		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//dialog.dispose();
				dialog.setVisible(false);
				

			}
		});
		FormLayout layout = new FormLayout(
				"fill:default:grow, fill:default:grow, fill:default:grow",
				"fill:p:grow, pref, 4dlu, p, fill:p:grow");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// , new FormDebugPanel()
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(datePartT, cc.xy(2, 2));
		builder.add(
				ButtonBarFactory.buildAddRemoveRightBar(submit, cancel),
				cc.xy(2, 4));

		add(builder.getPanel());
		
		
		
		setLocation(300, 300);
		setVisible(true);
	}

	public String getDate() {
		return new SimpleDateFormat("dd.MM.yyyy").format(datePartT.getDate());
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
		SwingUtilities.updateComponentTreeUI(this);
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
