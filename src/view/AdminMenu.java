package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import orm.Prav;
import org.orm.PersistentException;
import ormsamples.Create1CERTData;
import ormsamples.Delete1CERTData;
import ormsamples.List1CERTData;
import ormsamples.RetrieveAndUpdate1CERTData;
import TableModel.*;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class AdminMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3119786367485787182L;
	private MDIDesktopPane desktop;
	private JMenuItem adminPrav = new JMenuItem("Список прав", new ImageIcon(
			"resurs/rol.png"));
	private JMenuItem adminUsers = new JMenuItem("Список пользователей",
			new ImageIcon("resurs/users.png"));
	private AdminUsersTableModel adminUsersModel;
	private TextFrame tableFrame;
	private JTable jTableAdminPrav;
	private JTextField filterText;
	/*
	 * private RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
	 * adminUsersModel);
	 */
	private PravChooser dialog = null;
	private Prav pravSelect;
	private int strIDValue;
	private String strNameValue, strFullNameValue, strPasswordValue, strPrav;

	public AdminMenu(MDIDesktopPane desktop) throws PersistentException {
		this.desktop = desktop;
		// setDesktop(desktop);

		setText("Администрирование");

		// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
		adminPravActionListener();
		adminUsersActionListener();

		addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				removeAll();
			}

			public void menuSelected(MenuEvent e) {
				add(adminPrav);
				add(adminUsers);

			}

		});

	}

	private void adminPravActionListener() {
		adminPrav.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame("Список прав.",
						new ImageIcon("resurs/rol.png"));
				final AdminPravTableModel adminPravModel = new AdminPravTableModel(
						null, "pravID");
				final JTable jTableAdminPrav = new JTable(adminPravModel);

				jTableAdminPrav
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						adminPravModel);
				jTableAdminPrav.setRowSorter(sorter);

				// пїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
				jTableAdminPrav.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableAdminPrav.getSelectedRow();

							strIDValue = -1;
							strNameValue = "";
							strFullNameValue = "";

							orm.Prav[] oRMPrav;

							try {
								oRMPrav = orm.PravDAO.listPravByQuery(
										"pravID='"
												+ jTableAdminPrav.getValueAt(
														rowIndex, 0) + "'",
										null);
								strIDValue = oRMPrav[0].getPravID();
								strNameValue = oRMPrav[0].getPravName();
								strFullNameValue = oRMPrav[0].getPravFullName();

							} catch (PersistentException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

							/*
							 * final String strIDValue = jTableAdminPrav
							 * .getValueAt(rowIndex, 0).toString(); final String
							 * strNameValue = jTableAdminPrav
							 * .getValueAt(rowIndex, 1).toString(); final String
							 * strFullNameValue = jTableAdminPrav
							 * .getValueAt(rowIndex, 2).toString();
							 */

							final TextFrame textFrame = new TextFrame(
									"Список прав");
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Имя");
							JLabel fullNameLb = new JLabel("Полное имя");

							JTextField codIdTxt = new JTextField(strIDValue);
							codIdTxt.setEnabled(false);
							final JTextField nameTxt = new JTextField(
									strNameValue);
							final JTextField fulLNameTxt = new JTextField(
									strFullNameValue);
							FormLayout layout = new FormLayout(
									"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
									"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

							DefaultFormBuilder builder = new DefaultFormBuilder(
									layout);
							// , new FormDebugPanel()
							builder.setDefaultDialogBorder();
							CellConstraints cc = new CellConstraints();

							builder.add(codLb, cc.xy(2, 2));
							builder.add(codIdTxt, cc.xy(4, 2));
							builder.add(nameLb, cc.xy(2, 4));
							builder.add(nameTxt, cc.xy(4, 4));
							builder.add(fullNameLb, cc.xy(2, 6));
							builder.add(fulLNameTxt, cc.xy(4, 6));
							builder.add(ButtonBarFactory
									.buildAddRemoveRightBar(saveBt, canBt), cc
									.xy(4, 8));

							textFrame.add(builder.getPanel());

							AdminMenu.this.desktop.add(textFrame);
							// Save Prav to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdatePravData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText());

										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();

										}
									} catch (Exception ee) {
										ee.printStackTrace();

									}

									try {
										textFrame.setClosed(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
									}

								}
							});
							canBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										textFrame.setClosed(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
									}
								}
							});
						}

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

				JScrollPane pane = new JScrollPane(jTableAdminPrav);
				tableFrame.add(pane, BorderLayout.CENTER);

				JPanel panel2 = new JPanel(new BorderLayout());

				final JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Фильтр");
				panel.add(label, BorderLayout.WEST);

				final JTextField filterText = new JTextField("");
				panel.add(filterText, BorderLayout.CENTER);
				// Ентер в поле поиска
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
										.setRowFilter(RowFilter
												.regexFilter(text));
							} catch (PatternSyntaxException pse) {
								System.err.println("Bad regex pattern");
							}
						}

					}
				});
				// Фильтр
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
										.setRowFilter(RowFilter
												.regexFilter(text));
							} catch (PatternSyntaxException pse) {
								System.err.println("Bad regex pattern");
							}
						}
					}
				});

				panel.add(button, BorderLayout.EAST);
				JToolBar jtb = new JToolBar("nomBar");
				ImageIcon newNom = new ImageIcon("resurs/newNom.png");
				ImageIcon editNom = new ImageIcon("resurs/editNom.png");
				ImageIcon delNom = new ImageIcon("resurs/delNom.png");
				ImageIcon refNom = new ImageIcon("resurs/refNom.png");
				JButton newBt = new JButton(newNom);
				newBt.setText("Новый");
				JButton editBt = new JButton(editNom);
				editBt.setText("Редактировать");
				JButton delBt = new JButton(delNom);
				delBt.setText("Удалить");
				JButton refBt = new JButton(refNom);
				refBt.setText("Обновить");

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newPravActionLisenten(newBt);
				editPravBt(tableFrame, jTableAdminPrav, editBt);
				delPravBt(tableFrame, jTableAdminPrav, delBt);
				// refNomBt(antiKorModel, refNomBt);

				jtb.add(newBt);
				jtb.add(editBt);
				jtb.add(delBt);
				jtb.add(refBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				tableFrame.add(panel2, BorderLayout.NORTH);
				tableFrame.add(pane, BorderLayout.CENTER);

				AdminMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableAdminPrav
			 * @param delNomBt
			 */
			private void delPravBt(final TextFrame tableFrame,
					final JTable jTableAdminPrav, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableAdminPrav.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableAdminPrav.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableAdminPrav.getValueAt(
								rowIndex, 1).toString();

						if (JOptionPane
								.showConfirmDialog(tableFrame, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;
						Boolean flag = true;
						try {
							Delete1CERTData delete1CERTData = new Delete1CERTData();
							try {
								delete1CERTData.deletePravData(strIDValue);

							} catch (Exception e2) {
								e2.printStackTrace();
								flag = false;

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							flag = false;
						}
						if (flag)
							JOptionPane.showMessageDialog(tableFrame, "Объект "
									+ jTableAdminPrav.getValueAt(rowIndex, 1)
											.toString() + " удален.",
									"Сообщение",
									JOptionPane.INFORMATION_MESSAGE);

						else
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);

					}

				});
			}

			private void editPravBt(final TextFrame tableFrame,
					final JTable jTableAdminPrav, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableAdminPrav.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						strIDValue = -1;
						strNameValue = "";
						strFullNameValue = "";

						orm.Prav[] oRMPrav;

						try {
							oRMPrav = orm.PravDAO.listPravByQuery("pravID='"
									+ jTableAdminPrav.getValueAt(rowIndex, 0)
									+ "'", null);
							strIDValue = oRMPrav[0].getPravID();
							strNameValue = oRMPrav[0].getPravName();
							strFullNameValue = oRMPrav[0].getPravFullName();

						} catch (PersistentException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						/*
						 * final String strIDValue = jTableAdminPrav
						 * .getValueAt(rowIndex, 0).toString(); final String
						 * strNameValue = jTableAdminPrav .getValueAt(rowIndex,
						 * 1).toString(); final String strFullNameValue =
						 * jTableAdminPrav .getValueAt(rowIndex, 2).toString();
						 */
						final TextFrame textFrame = new TextFrame("Список прав");
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Имя");
						JLabel fullNameLb = new JLabel("Полное имя");

						JTextField codIdTxt = new JTextField(strIDValue);
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField(strNameValue);
						final JTextField fulLNameTxt = new JTextField(
								strFullNameValue);
						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xy(4, 2));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xy(4, 4));
						builder.add(fullNameLb, cc.xy(2, 6));
						builder.add(fulLNameTxt, cc.xy(4, 6));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xy(4, 8));

						textFrame.add(builder.getPanel());

						AdminMenu.this.desktop.add(textFrame);
						// Save Prav to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdatePravData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText());

									} finally {
										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();

									}
								} catch (Exception ee) {
									ee.printStackTrace();

								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
						canBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}
							}
						});

					}
				});
			}

			private void newPravActionLisenten(JButton newBt) {
				newBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame("Список прав");
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Имя");
						JLabel fullNameLb = new JLabel("Полное имя");
						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();

						// пїЅпїЅпїЅпїЅпїЅпїЅпїЅ JTextField пїЅпїЅпїЅпїЅ
						nameTxt.addFocusListener(new FocusListener() {

							@Override
							public void focusLost(FocusEvent arg0) {
								fulLNameTxt.setText(nameTxt.getText());
							}

							@Override
							public void focusGained(FocusEvent arg0) {
							}
						});

						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xy(4, 2));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xy(4, 4));
						builder.add(fullNameLb, cc.xy(2, 6));
						builder.add(fulLNameTxt, cc.xy(4, 6));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xy(4, 8));

						textFrame.add(builder.getPanel());

						AdminMenu.this.desktop.add(textFrame);
						// Save Prav to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createPravData(
												nameTxt.getText(),
												fulLNameTxt.getText());

									} finally {
										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();

									}
								} catch (Exception ee) {
									ee.printStackTrace();

								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
						canBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}
							}
						});
					}
				});
			}
		});
	}

	private void adminUsersActionListener() {

		adminUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tableFrame = new TextFrame("Список пользователей.",
						new ImageIcon("resurs/users.png"));
				adminUsersModel = new AdminUsersTableModel(null, "userrID");
				jTableAdminPrav = new JTable(adminUsersModel);

				jTableAdminPrav
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						adminUsersModel);
				jTableAdminPrav.setRowSorter(sorter);

				// Клик по сторочке
				jTableAdminPrav.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							final int rowIndex = jTableAdminPrav
									.getSelectedRow();

							strIDValue = -1;
							strNameValue = "";
							strFullNameValue = "";
							strPasswordValue = "";
							strPrav = "";
							orm.Users[] oRMUsers;
							try {
								oRMUsers = orm.UsersDAO.listUsersByQuery(
										"userrID='"
												+ jTableAdminPrav.getValueAt(
														rowIndex, 0) + "'",
										null);
								strIDValue = oRMUsers[0].getUserrID();
								strNameValue = oRMUsers[0].getUserName();
								strFullNameValue = oRMUsers[0]
										.getUserFullName();
								strPasswordValue = oRMUsers[0].getPassword();
								pravSelect = oRMUsers[0].getPravprav();
								strPrav = pravSelect.getPravName();
							} catch (PersistentException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

							final TextFrame textFrame = new TextFrame(
									"Список прав");
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Имя");
							JLabel fullNameLb = new JLabel("Полное имя");
							JLabel passwordLb = new JLabel("Пароль");
							JButton pravBt = new JButton("...");
							JLabel pravLb = new JLabel("Роль");

							final JTextField codIdTxt = new JTextField(
									strIDValue);
							final JTextField nameTxt = new JTextField(
									strNameValue);
							final JTextField fulLNameTxt = new JTextField(
									strFullNameValue);
							final JTextField pravTxt = new JTextField(strPrav);
							final JTextField passwordTxt = new JTextField(
									strPasswordValue);

							codIdTxt.setEditable(false);

							FormLayout layout = new FormLayout(
									"fill:default:grow, pref, 9dlu, fill:default:grow, 2dlu, p, fill:default:grow",
									"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

							DefaultFormBuilder builder = new DefaultFormBuilder(
									layout);
							// , new FormDebugPanel()
							builder.setDefaultDialogBorder();
							CellConstraints cc = new CellConstraints();

							builder.add(codLb, cc.xy(2, 2));
							builder.add(codIdTxt, cc.xyw(4, 2, 3));
							builder.add(nameLb, cc.xy(2, 4));
							builder.add(nameTxt, cc.xyw(4, 4, 3));
							builder.add(fullNameLb, cc.xy(2, 6));
							builder.add(fulLNameTxt, cc.xyw(4, 6, 3));

							builder.add(passwordLb, cc.xy(2, 8));
							builder.add(passwordTxt, cc.xyw(4, 8, 3));

							builder.add(pravLb, cc.xy(2, 10));
							builder.add(pravTxt, cc.xyw(4, 10, 1));
							builder.add(pravBt, cc.xyw(6, 10, 1));
							builder.add(ButtonBarFactory
									.buildAddRemoveRightBar(saveBt, canBt), cc
									.xyw(2, 12, 5));

							textFrame.add(builder.getPanel());

							AdminMenu.this.desktop.add(textFrame);

							pravBt.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {

									// if first time, construct dialog

									if (dialog == null)
										try {
											AdminPravTableModel adminPravModelSelect = new AdminPravTableModel(
													null, "pravID");
											dialog = new PravChooser(
													adminPravModelSelect);
										} catch (PersistentException e1) {

											e1.printStackTrace();
										}

									if (dialog.showDialog(null, "Список прав")) {
										try {
											int i = dialog.getPrav(1);
											pravSelect = orm.PravDAO
													.listPravByQuery("pravID='"
															+ i + "'", null)[0];
											pravTxt.setText(pravSelect
													.getPravName());

										} catch (PersistentException e1) {

											e1.printStackTrace();
										}

									}
								}
							});

							// Save User to DateBase

							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateUserData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText(),
															passwordTxt
																	.getText(),
															pravSelect);

											// adminUsersModel.fireRefresh();
											jTableAdminPrav.setValueAt(
													strIDValue, rowIndex, 0);
											jTableAdminPrav.setValueAt(
													nameTxt.getText(),
													rowIndex, 1);
											jTableAdminPrav.setValueAt(
													fulLNameTxt.getText(),
													rowIndex, 2);
											jTableAdminPrav.setValueAt(
													pravSelect.getPravName(),
													rowIndex, 3);
											adminUsersModel.fireRefresh();
											jTableAdminPrav
													.update(getGraphics());
										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();

										}
									} catch (Exception ee) {
										ee.printStackTrace();

									}

									try {
										textFrame.setClosed(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
									}

								}
							});
							canBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										textFrame.setClosed(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
									}
								}
							});
						}

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

				JScrollPane pane = new JScrollPane(jTableAdminPrav);
				tableFrame.add(pane, BorderLayout.CENTER);

				JPanel panel2 = new JPanel(new BorderLayout());

				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Фильтр");
				panel.add(label, BorderLayout.WEST);

				filterText = new JTextField("");
				panel.add(filterText, BorderLayout.CENTER);
				// Ентер в поле поиска
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
										.setRowFilter(RowFilter
												.regexFilter(text));
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
										.setRowFilter(RowFilter
												.regexFilter(text));
							} catch (PatternSyntaxException pse) {
								System.err.println("Bad regex pattern");
							}
						}
					}
				});

				panel.add(button, BorderLayout.EAST);
				JToolBar jtb = new JToolBar("nomBar");
				ImageIcon newNom = new ImageIcon("resurs/newNom.png");
				ImageIcon editNom = new ImageIcon("resurs/editNom.png");
				ImageIcon delNom = new ImageIcon("resurs/delNom.png");
				ImageIcon refNom = new ImageIcon("resurs/refNom.png");
				JButton newNomBt = new JButton(newNom);
				newNomBt.setText("NEW");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("EDIT");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("DELETE");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("REF");

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newUserActionLisenten(newNomBt);
				editUserBt(tableFrame, jTableAdminPrav, editNomBt);
				delUserBt(tableFrame, jTableAdminPrav, delNomBt);
				// refNomBt(antiKorModel, refNomBt);

				jtb.add(newNomBt);
				jtb.add(editNomBt);
				jtb.add(delNomBt);
				jtb.add(refNomBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				tableFrame.add(panel2, BorderLayout.NORTH);
				tableFrame.add(pane, BorderLayout.CENTER);

				AdminMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableAdminPrav
			 * @param delNomBt
			 */
			private void delUserBt(final TextFrame tableFrame,
					final JTable jTableAdminPrav, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableAdminPrav.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableAdminPrav.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableAdminPrav.getValueAt(
								rowIndex, 1).toString();

						if (JOptionPane
								.showConfirmDialog(tableFrame, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;
						Boolean flag = false;
						orm.Users userSel = null;
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								userSel = list1CERTData.listUsersData(
										"userrID='" + strIDValue + "'", null)[0];
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();

							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

						try {
							Delete1CERTData delete1CERTData = new Delete1CERTData();
							try {
								delete1CERTData.deleteUsersData(Integer
										.toString(userSel.getUserrID()));
								flag = true;

							} catch (Exception e2) {
								e2.printStackTrace();

								JOptionPane.showMessageDialog(tableFrame,
										"Объект нельзя удалить.", "Ошибка",
										JOptionPane.ERROR_MESSAGE);

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();

							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}
						if (flag)
							JOptionPane
									.showMessageDialog(
											tableFrame,
											"Объект "
													+ jTableAdminPrav
															.getValueAt(
																	rowIndex, 1)
															.toString()
													+ " удален.", "Сообение",
											JOptionPane.INFORMATION_MESSAGE);

						else
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);

					}

				});
			}

			private void editUserBt(final TextFrame tableFrame,
					final JTable jTableAdminPrav, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableAdminPrav.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						strIDValue = -1;
						strNameValue = "";
						strFullNameValue = "";
						strPasswordValue = "";
						strPrav = "";
						orm.Users[] oRMUsers;
						try {
							oRMUsers = orm.UsersDAO.listUsersByQuery(
									"userrID='"
											+ jTableAdminPrav.getValueAt(
													rowIndex, 0) + "'", null);
							strIDValue = oRMUsers[0].getUserrID();
							strNameValue = oRMUsers[0].getUserName();
							strFullNameValue = oRMUsers[0].getUserFullName();
							strPasswordValue = oRMUsers[0].getPassword();
							pravSelect = oRMUsers[0].getPravprav();
							strPrav = pravSelect.getPravName();
						} catch (PersistentException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						final TextFrame textFrame = new TextFrame("Список прав");
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Имя");
						JLabel fullNameLb = new JLabel("Полное имя");
						JLabel passwordLb = new JLabel("Пароль");
						JButton pravBt = new JButton("...");
						JLabel pravLb = new JLabel("Роль");

						final JTextField codIdTxt = new JTextField(strIDValue);
						final JTextField nameTxt = new JTextField(strNameValue);
						final JTextField fulLNameTxt = new JTextField(
								strFullNameValue);
						final JTextField pravTxt = new JTextField(strPrav);
						final JTextField passwordTxt = new JTextField(
								strPasswordValue);

						codIdTxt.setEditable(false);

						FormLayout layout = new FormLayout(
								"fill:default:grow, pref, 9dlu, fill:default:grow, 2dlu, p, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xyw(4, 2, 3));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xyw(4, 4, 3));
						builder.add(fullNameLb, cc.xy(2, 6));
						builder.add(fulLNameTxt, cc.xyw(4, 6, 3));

						builder.add(passwordLb, cc.xy(2, 8));
						builder.add(passwordTxt, cc.xyw(4, 8, 3));

						builder.add(pravLb, cc.xy(2, 10));
						builder.add(pravTxt, cc.xyw(4, 10, 1));
						builder.add(pravBt, cc.xyw(6, 10, 1));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(2, 12, 5));

						textFrame.add(builder.getPanel());

						AdminMenu.this.desktop.add(textFrame);

						pravBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								// if first time, construct dialog

								if (dialog == null)
									try {
										AdminPravTableModel adminPravModelSelect = new AdminPravTableModel(
												null, "pravID");
										dialog = new PravChooser(
												adminPravModelSelect);
									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								if (dialog.showDialog(null,
										"Списо прав")) {
									try {
										int i = dialog.getPrav(1);
										pravSelect = orm.PravDAO
												.listPravByQuery("pravID='" + i
														+ "'", null)[0];
										pravTxt.setText(pravSelect
												.getPravName());

									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								}

							}

						});

						// Save User to DateBase

						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									// String userrID, String nameUser,
									// String fullNameUser, String passwordUser,
									// Prav prav

									try {
										updateDate.retrieveAndUpdateUserData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText(),
												passwordTxt.getText(),
												pravSelect);

									} finally {
										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();

									}
								} catch (Exception ee) {
									ee.printStackTrace();

								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
						canBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}
							}
						});
					}
				});
			}

			private void newUserActionLisenten(JButton newNomBt) {

				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Пользователь: Новый.");
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JButton pravBt = new JButton("...");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");
						JLabel passwordLb = new JLabel("Пароль");
						JLabel pravLb = new JLabel("Роль");
						/*
						 * "пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ: пїЅпїЅпїЅпїЅпїЅ.")
						 * ; JButton canBt = new JButton("пїЅпїЅпїЅпїЅпїЅпїЅ");
						 * JButton saveBt = new JButton(
						 * "пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ"); JButton pravBt = new
						 * JButton("..."); JLabel codLb = new
						 * JLabel("пїЅпїЅпїЅ"); JLabel nameLb = new JLabel(
						 * "пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ"); JLabel
						 * fullNameLb = new JLabel(
						 * "пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ"
						 * ); JLabel passwordLb = new
						 * JLabel("пїЅпїЅпїЅпїЅпїЅпїЅ"); JLabel pravLb = new
						 * JLabel("пїЅпїЅпїЅпїЅ");
						 */
						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();
						final JTextField pravTxt = new JTextField();
						final JTextField passwordTxt = new JTextField();

						// События JTextField поля
						nameTxt.addFocusListener(new FocusListener() {

							@Override
							public void focusLost(FocusEvent arg0) {
								fulLNameTxt.setText(nameTxt.getText());
							}

							@Override
							public void focusGained(FocusEvent arg0) {
							}
						});

						FormLayout layout = new FormLayout(
								"fill:default:grow, pref, 9dlu, fill:default:grow, 2dlu, p, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xyw(4, 2, 3));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xyw(4, 4, 3));
						builder.add(fullNameLb, cc.xy(2, 6));
						builder.add(fulLNameTxt, cc.xyw(4, 6, 3));

						builder.add(passwordLb, cc.xy(2, 8));
						builder.add(passwordTxt, cc.xyw(4, 8, 3));

						builder.add(pravLb, cc.xy(2, 10));
						builder.add(pravTxt, cc.xyw(4, 10, 1));
						builder.add(pravBt, cc.xyw(6, 10, 1));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(2, 12, 5));

						textFrame.add(builder.getPanel());

						AdminMenu.this.desktop.add(textFrame);

						pravBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								// if first time, construct dialog

								if (dialog == null)
									try {
										AdminPravTableModel adminPravModelSelect = new AdminPravTableModel(
												null, "pravID");
										dialog = new PravChooser(
												adminPravModelSelect);
									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								if (dialog.showDialog(null, "Список прав")) {
									try {
										int i = dialog.getPrav(1);
										pravSelect = orm.PravDAO
												.listPravByQuery("pravID='" + i
														+ "'", null)[0];
										pravTxt.setText(pravSelect
												.getPravName());

									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								}

							}

						});

						// Save User to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createUserData(
												nameTxt.getText(),
												fulLNameTxt.getText(),
												passwordTxt.getText(),
												pravSelect);

									} finally {
										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();

									}
								} catch (Exception ee) {
									ee.printStackTrace();

								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
						canBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}
							}
						});
					}
				});
			}
		});
	}

}
