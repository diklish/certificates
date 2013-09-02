package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

class OpcMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7965530706871346935L;
	private MDIDesktopPane desktop;
	UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
	ButtonGroup lafGroup = new ButtonGroup();

	public OpcMenu(final MDIDesktopPane desktop) {
		this.desktop = desktop;
		setText("Вид");
		for (int i = 0; i < lafs.length; i++) {
			JRadioButtonMenuItem rb = new JRadioButtonMenuItem(
					lafs[i].getName());
			add(rb);
			rb.setSelected(UIManager.getLookAndFeel().getName()
					.equals(lafs[i].getName()));
			rb.putClientProperty("UIKey", lafs[i]);
			rb.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ae) {
					JRadioButtonMenuItem rb2 = (JRadioButtonMenuItem) ae
							.getSource();
					if (rb2.isSelected()) {
						UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rb2
								.getClientProperty("UIKey");
						try {
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(desktop.getParent().getParent().getParent().getParent());
							
						} catch (Exception e) {
							System.err.println("unable to set UI "
									+ e.getMessage());
						}
					}
				}
			});
			lafGroup.add(rb);
		}
	}
}
