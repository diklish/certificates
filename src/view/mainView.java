package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.Locale;
import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import org.orm.PersistentException;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

class mainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7789434263849945026L;

	private MDIDesktopPane desktop = new MDIDesktopPane();

	private JMenuBar menuBar = new JMenuBar();
	private JMenu exitGMenu = new JMenu("Файл");
	private JMenuItem connectMenu = new JMenuItem("Вход", new ImageIcon(
			"resurs/login.gif"));
	private JMenuItem exitMenu = new JMenuItem("Выход", new ImageIcon(
			"resurs/exit.png"));
	private JMenu helpMenu = new JMenu("Справка");
	private JMenuItem helpMenuHelp = new JMenuItem("Справка", new ImageIcon(
			"resurs/help.png"));
	private JMenuItem helpMenuAbout = new JMenuItem("О программе",
			new ImageIcon("resurs/about.png"));

	private JScrollPane scrollPane = new JScrollPane();
	private PasswordChooser dialog = null;
	private NomMenu nomMenu = new NomMenu(desktop);
	private PassportMenu passportMenu = new PassportMenu(desktop);
	private AdminMenu adminMenu = new AdminMenu(desktop);
	private WindowMenu winMenu = new WindowMenu(desktop);
	private OpcMenu opcMenu = new OpcMenu(desktop);

	public mainFrame() throws PersistentException {

		connectMenu.addActionListener(new ConnectAction());

		exitGMenu.add(connectMenu);
		exitGMenu.add(exitMenu);
		menuBar.add(exitGMenu);

		menuBar.add(nomMenu);
		menuBar.add(passportMenu);
		menuBar.add(adminMenu);
		menuBar.add(winMenu);
		menuBar.add(opcMenu);

		helpMenu.add(helpMenuHelp);
		helpMenu.add(helpMenuAbout);
		menuBar.add(helpMenu);

		nomMenu.setVisible(false);
		passportMenu.setVisible(false);
		adminMenu.setVisible(false);
		winMenu.setVisible(false);
		opcMenu.setVisible(false);

		setJMenuBar(menuBar);

		setTitle("Паспорта изделий");
		ImageIcon mainICO = new ImageIcon("resurs/frameICO.png");
		setIconImage(mainICO.getImage());

		scrollPane.getViewport().add(desktop);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		helpMenuHelp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}
		});
		helpMenuAbout.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Программа для ведения паспортов соответствия. \n Версия программы 2.5 \n Автор: Клишин Д.И. ",
								"О программе", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		exitMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	/**
	 * The Connect action pops up the password dialog.
	 */

	private class ConnectAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// if first time, construct dialog

			if (dialog == null)
				try {
					dialog = new PasswordChooser();
				} catch (PersistentException e1) {

					e1.printStackTrace();
				}
			// set default values
			dialog.setUser();

			// pop up dialog

			if (dialog.showDialog(mainFrame.this, "Connect")) {
				// if accepted, retrieve user input

				User u = dialog.getUser();

				orm.Users[] oRMUserses;
				try {
					String str = null;
					Boolean flagError = true;
					oRMUserses = orm.UsersDAO.listUsersByQuery(
							"userName='" + u.getName() + "'", null);

					if (oRMUserses.length == 0) {
						str = "Такого пользователя не существует!";
						flagError = false;

					} else {
						if (!oRMUserses[0].getUserName().equals(u.getName())) {
							str = "Такого пользователя не существует.";
							flagError = false;
						} else if (!oRMUserses[0].getPassword().equals(
								new String(u.getPassword()))) {
							str = "Пароль введен неверно!";
							flagError = false;
						}
					}
					if (flagError) {
						GlobalVars.setUser(oRMUserses[0]);
						// Old
						GlobalVars.value = oRMUserses[0].getPravprav()
								.getPravID();
						nomMenu.setVisible(true);
						passportMenu.setVisible(true);
						opcMenu.setVisible(true);
						winMenu.setVisible(true);

						connectMenu.setVisible(false);

						if (GlobalVars.value != 1)
							adminMenu.setVisible(false);
						else
							adminMenu.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(dialog, str,
								"ошибка аутентификации.",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (PersistentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

public class mainView {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				mainFrame frame;
				Locale.setDefault(new Locale("RU"));
				try {

					frame = new mainFrame();

					frame.setSize(600, 400);
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					Dimension frameSize = frame.getSize();
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}

					frame.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					// frame.setDefaultCloseOperation(
					// JFrame.DO_NOTHING_ON_CLOSE );

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (PersistentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

	}

}

class TextFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4439494014861079108L;

	private JTextArea textArea = new JTextArea();

	private JScrollPane scrollPane = new JScrollPane();

	public TextFrame(String Title) {

		setTitle(Title);
		setMaximizable(true);
		setIconifiable(true);

		setClosable(true);
		setResizable(true);
		scrollPane.getViewport().add(textArea);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	public TextFrame(String Title, Boolean MaxiZ) {
		// setSize(Sizel, SizeR);
		setTitle(Title);
		setMaximizable(MaxiZ);
		setIconifiable(true);

		setClosable(true);
		setResizable(true);
		scrollPane.getViewport().add(textArea);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	public TextFrame(String Title, ImageIcon IconF) {
		// setSize(Sizel, SizeR);
		setTitle(Title);
		setIconifiable(true);
		setMaximizable(true);
		setFrameIcon(IconF);
		setClosable(true);
		setResizable(true);
		scrollPane.getViewport().add(textArea);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}

/**
 * An extension of WDesktopPane that supports often used MDI functionality. This
 * class also handles setting scroll bars for when windows move too far to the
 * left or bottom, providing the MDIDesktopPane is in a ScrollPane.
 */
class MDIDesktopPane extends JDesktopPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3923248954190272845L;

	private static int FRAME_OFFSET = 20;

	private MDIDesktopManager manager;

	public MDIDesktopPane() {
		manager = new MDIDesktopManager(this);
		setDesktopManager(manager);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// setBackground(new Color((float) Math.random(), (float) Math.random(),
		// (float) Math.random()));

		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}

	public void setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		checkDesktopSize();
	}

	public Component add(JInternalFrame frame) {
		JInternalFrame[] array = getAllFrames();
		Point p;
		int w;
		int h;

		Component retval = super.add(frame);
		checkDesktopSize();
		if (array.length > 0) {
			p = array[0].getLocation();
			p.x = p.x + FRAME_OFFSET;
			p.y = p.y + FRAME_OFFSET;
		} else {
			p = new Point(0, 0);
		}
		frame.setLocation(p.x, p.y);
		if (frame.isResizable()) {
			w = getWidth() - (getWidth() / 3);
			h = getHeight() - (getHeight() / 3);
			if (w < frame.getMinimumSize().getWidth())
				w = (int) frame.getMinimumSize().getWidth();
			if (h < frame.getMinimumSize().getHeight())
				h = (int) frame.getMinimumSize().getHeight();
			frame.setSize(w, h);
		}
		moveToFront(frame);
		frame.setVisible(true);
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
			frame.toBack();
		}
		return retval;
	}

	public void remove(Component c) {
		super.remove(c);
		checkDesktopSize();
	}

	/**
	 * Cascade all internal frames
	 */
	public void cascadeFrames() {
		int x = 0;
		int y = 0;
		JInternalFrame allFrames[] = getAllFrames();

		manager.setNormalSize();
		int frameHeight = (getBounds().height - 5) - allFrames.length
				* FRAME_OFFSET;
		int frameWidth = (getBounds().width - 5) - allFrames.length
				* FRAME_OFFSET;
		for (int i = allFrames.length - 1; i >= 0; i--) {
			allFrames[i].setSize(frameWidth, frameHeight);
			allFrames[i].setLocation(x, y);
			x = x + FRAME_OFFSET;
			y = y + FRAME_OFFSET;
		}
	}

	/**
	 * Tile all internal frames
	 */
	public void tileFrames() {
		java.awt.Component allFrames[] = getAllFrames();
		manager.setNormalSize();
		int frameHeight = getBounds().height / allFrames.length;
		int y = 0;
		for (int i = 0; i < allFrames.length; i++) {
			allFrames[i].setSize(getBounds().width, frameHeight);
			allFrames[i].setLocation(0, y);
			y = y + frameHeight;
		}
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given dimension.
	 */
	public void setAllSize(Dimension d) {
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given width and height.
	 */
	public void setAllSize(int width, int height) {
		setAllSize(new Dimension(width, height));
	}

	private void checkDesktopSize() {
		if (getParent() != null && isVisible())
			manager.resizeDesktop();
	}
}

/**
 * Private class used to replace the standard DesktopManager for JDesktopPane.
 * Used to provide scrollbar functionality.
 */
class MDIDesktopManager extends DefaultDesktopManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437794763581941373L;
	private MDIDesktopPane desktop;

	public MDIDesktopManager(MDIDesktopPane desktop) {
		this.desktop = desktop;
	}

	public void endResizingFrame(JComponent f) {
		super.endResizingFrame(f);
		resizeDesktop();
	}

	public void endDraggingFrame(JComponent f) {
		super.endDraggingFrame(f);
		resizeDesktop();
	}

	public void setNormalSize() {
		JScrollPane scrollPane = getScrollPane();
		int x = 0;
		int y = 0;
		Insets scrollInsets = getScrollPaneInsets();

		if (scrollPane != null) {
			Dimension d = scrollPane.getVisibleRect().getSize();
			if (scrollPane.getBorder() != null) {
				d.setSize(
						d.getWidth() - scrollInsets.left - scrollInsets.right,
						d.getHeight() - scrollInsets.top - scrollInsets.bottom);
			}

			d.setSize(d.getWidth() - 20, d.getHeight() - 20);
			desktop.setAllSize(x, y);
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}

	private Insets getScrollPaneInsets() {
		JScrollPane scrollPane = getScrollPane();
		if (scrollPane == null)
			return new Insets(0, 0, 0, 0);
		else
			return getScrollPane().getBorder().getBorderInsets(scrollPane);
	}

	private JScrollPane getScrollPane() {
		if (desktop.getParent() instanceof JViewport) {
			JViewport viewPort = (JViewport) desktop.getParent();
			if (viewPort.getParent() instanceof JScrollPane)
				return (JScrollPane) viewPort.getParent();
		}
		return null;
	}

	protected void resizeDesktop() {
		int x = 0;
		int y = 0;
		JScrollPane scrollPane = getScrollPane();
		Insets scrollInsets = getScrollPaneInsets();

		if (scrollPane != null) {
			JInternalFrame allFrames[] = desktop.getAllFrames();
			for (int i = 0; i < allFrames.length; i++) {
				if (allFrames[i].getX() + allFrames[i].getWidth() > x) {
					x = allFrames[i].getX() + allFrames[i].getWidth();
				}
				if (allFrames[i].getY() + allFrames[i].getHeight() > y) {
					y = allFrames[i].getY() + allFrames[i].getHeight();
				}
			}
			Dimension d = scrollPane.getVisibleRect().getSize();
			if (scrollPane.getBorder() != null) {
				d.setSize(
						d.getWidth() - scrollInsets.left - scrollInsets.right,
						d.getHeight() - scrollInsets.top - scrollInsets.bottom);
			}

			if (x <= d.getWidth())
				x = ((int) d.getWidth()) - 20;
			if (y <= d.getHeight())
				y = ((int) d.getHeight()) - 20;
			desktop.setAllSize(x, y);
			scrollPane.invalidate();
			scrollPane.validate();
		}
	}
}

/**
 * Menu component that handles the functionality expected of a standard
 * "Windows" menu for MDI applications.
 */
class WindowMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8625348732052064909L;

	private MDIDesktopPane desktop;

	private JMenuItem cascade = new JMenuItem("Cascade");

	private JMenuItem tile = new JMenuItem("Tile");

	public WindowMenu(MDIDesktopPane desktop) {
		this.desktop = desktop;
		setText("Окно");
		cascade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				WindowMenu.this.desktop.cascadeFrames();
			}
		});
		tile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				WindowMenu.this.desktop.tileFrames();
			}
		});
		addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				removeAll();
			}

			public void menuSelected(MenuEvent e) {
				buildChildMenus();
			}
		});
	}

	/* Sets up the children menus depending on the current desktop state */
	private void buildChildMenus() {
		int i;
		ChildMenuItem menu;
		JInternalFrame[] array = desktop.getAllFrames();

		add(cascade);
		add(tile);
		if (array.length > 0)
			addSeparator();
		cascade.setEnabled(array.length > 0);
		tile.setEnabled(array.length > 0);

		for (i = 0; i < array.length; i++) {
			menu = new ChildMenuItem(array[i]);
			menu.setState(i == 0);
			menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					JInternalFrame frame = ((ChildMenuItem) ae.getSource())
							.getFrame();
					frame.moveToFront();
					try {
						frame.setSelected(true);
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}
				}
			});
			menu.setIcon(array[i].getFrameIcon());
			add(menu);
		}
	}

	/*
	 * This JCheckBoxMenuItem descendant is used to track the child frame that
	 * corresponds to a give menu.
	 */
	class ChildMenuItem extends JCheckBoxMenuItem {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3470459520302109206L;
		private JInternalFrame frame;

		public ChildMenuItem(JInternalFrame frame) {
			super(frame.getTitle());
			this.frame = frame;
		}

		public JInternalFrame getFrame() {
			return frame;
		}
	}
}

/**
 * A password chooser that is shown inside a dialog
 */
class PasswordChooser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 575538895917947340L;

	public PasswordChooser() throws PersistentException {
		usernamew = new JComboBox<String>();
		usernamew.setEditable(false);
		orm.Users[] oRMUsersList = orm.UsersDAO.listUsersByQuery(null,
				"userName");
		for (orm.Users users : oRMUsersList) {

			usernamew.addItem(users.getUserName());
		}
		password = new JPasswordField("");
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
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
		FormLayout layout = new FormLayout(
				"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
				"fill:p:grow, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// , new FormDebugPanel()
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(new JLabel("Пользователь:"), cc.xy(2, 2));
		builder.add(usernamew, cc.xy(4, 2));
		builder.add(new JLabel("Password:"), cc.xy(2, 4));
		builder.add(password, cc.xy(4, 4));
		builder.add(
				ButtonBarFactory.buildAddRemoveRightBar(okButton, cancelButton),
				cc.xy(4, 6));

		add(builder.getPanel());

	}

	/**
	 * Sets the dialog defaults.
	 * 
	 * @param u
	 *            the default user information
	 */
	public void setUser() {
		// usernamew.setText(u.getName());
		usernamew.setSelectedIndex(0);
	}

	/**
	 * Gets the dialog entries.
	 * 
	 * @return a User object whose state represents the dialog entries
	 */
	public User getUser() {
		return new User(usernamew.getSelectedItem().toString(),
				password.getPassword());

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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = dialog.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		dialog.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}

	private JComboBox<String> usernamew;
	// private JTextField username;
	private JPasswordField password;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
}

/**
 * A user has a name and password. For security reasons, the password is stored
 * as a char[], not a String.
 */
class User {
	public User(String aName, char[] aPassword) {
		name = aName;
		password = aPassword;
	}

	public String getName() {
		return name;
	}

	public char[] getPassword() {
		return password;
	}

	public void setName(String aName) {
		name = aName;
	}

	public void setPassword(char[] aPassword) {
		password = aPassword;
	}

	private String name;
	private char[] password;
}
