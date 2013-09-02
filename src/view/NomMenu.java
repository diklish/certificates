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
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.*;
import TableModel.*;
import org.orm.PersistentException;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import orm.*;
import ormsamples.Create1CERTData;
import ormsamples.Delete1CERTData;
import ormsamples.RetrieveAndUpdate1CERTData;

@SuppressWarnings("serial")
class NomMenu extends JMenu {

	private MDIDesktopPane desktop;
	private JMenuItem nomSpr = new JMenuItem("Номенклатура", new ImageIcon(
			"resurs/nom.png"));
	private JMenuItem seriySpr = new JMenuItem("Серии", new ImageIcon(
			"resurs/series.png"));
	private JMenuItem markaSpr = new JMenuItem("Марка бетона", new ImageIcon(
			"resurs/m.png"));
	private JMenuItem stallSpr = new JMenuItem("Марка стали закладных",
			new ImageIcon("resurs/stall.png"));
	private JMenuItem antikorSpr = new JMenuItem(
			"Вид антикоррозийного покрытия", new ImageIcon("resurs/pokr.png"));
	private JMenuItem katPovSpr = new JMenuItem(
			"Категория лицевых бетонных поверхностей", new ImageIcon(
					"resurs/kat.png"));
	private JMenuItem tUSpr = new JMenuItem("Обозначение стандарта (ТУ)",
			new ImageIcon("resurs/norma.png"));
	private NomNewEdit dialog = null;
	private TextFrame textFrame;
	private JTable jTableNom;
	private KatPovTableModel katPovModel;
	private JTable jTableKatPov;
	private TUTableModel tUModel;
	private JTable jTableTU;
	private AntiKorTableModel antiKorModel;
	private JTable jTableAntiKor;
	private MarkaTableModel markaModel;
	private JTable jTableMarka;
	private JTable jTableStall;
	private StallTableModel stallModel;
	private NomTableModelAll nomModel;
	private SeriyTableModel seriyModel;
	private JTable jTableSeriy;
	private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();

	public NomMenu(MDIDesktopPane desktop) throws PersistentException {

		this.desktop = desktop;
		setText("Справочники");

		// Слушатели меню
		nomSprActionListener();
		seriySprActionListener();
		stallSprActionListener();
		markaSprActionListener();
		antiKorSprActionListener();
		katPovSprActionListener();
		tUSprActionListener();

		addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				removeAll();
			}

			public void menuSelected(MenuEvent e) {
				add(nomSpr);
				add(seriySpr);
				add(markaSpr);
				add(stallSpr);
				add(antikorSpr);
				add(katPovSpr);
				add(tUSpr);
			}

		});

	}

	private void nomSprActionListener() {
		nomSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ImageIcon newNom = new ImageIcon("resurs/newNom.png");
				ImageIcon editNom = new ImageIcon("resurs/editNom.png");
				ImageIcon delNom = new ImageIcon("resurs/delNom.png");
				final ImageIcon AllNom = new ImageIcon("resurs/AllNom.png");
				final ImageIcon GrNom = new ImageIcon("resurs/GrNom.png");
				JButton newNomBt = new JButton(newNom);
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удалить");
				final JButton AllGpNomBt = new JButton("Вид", AllNom);

				textFrame = new TextFrame("Номенклатура", new ImageIcon(
						"resurs/nom.png"));
				nomModel = new NomTableModelAll("parentID=1");
				nomModel.setData("parentID=0");
				// nomModel.setData("parentID=1");
				jTableNom = new JTable(nomModel);

				jTableNom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				sorter.setModel(nomModel);
				sorter.setSortable(0, false);
				sorter.setSortable(1, false);

				jTableNom.setRowSorter(sorter);
				// System.out.println("nomModel1.getRowCount()"+nomModel.getRowCount());
				// Клик по сторочкеn
				jTableNom.addMouseListener(new MouseListener() {
					private Nomenclature EditNomE;

					@Override
					public void mouseClicked(MouseEvent arg0) {
						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(textFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						String strIDValue = jTableNom.getValueAt(rowIndex, 1)
								.toString();
						Boolean isFolderValue;

						if (((ImageIcon) jTableNom.getValueAt(rowIndex, 0))
								.getImage().equals(
										(new ImageIcon("resurs/list.png"))
												.getImage()))
							isFolderValue = false;
						else
							isFolderValue = true;

						if ((isFolderValue)
								&& (arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

							if ((((ImageIcon) jTableNom.getValueAt(rowIndex, 0))
									.getImage().equals((new ImageIcon(
									"resurs/folderClose.png")).getImage()))) {
								nomModel.setDataInsert((orm.Nomenclature) jTableNom
										.getValueAt(rowIndex, 1));
								AllGpNomBt.setIcon(AllNom);
							} else {
								nomModel.setDataInsert(((orm.Nomenclature) jTableNom
										.getValueAt(rowIndex, 1)).getParent());
								AllGpNomBt.setIcon(AllNom);
							}

							sorter.setModel(nomModel);
							sorter.setSortable(0, false);
							sorter.setSortable(1, false);

							jTableNom.setRowSorter(sorter);
							jTableNom.repaint();

						} else {

							if ((!isFolderValue)
									&& (arg0.getClickCount() == 2)
									&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
								try {
									EditNomE = orm.NomenclatureDAO
											.loadNomenclatureByQuery("nomid='"
													+ strIDValue + "'", null);

									try {
										dialog = new NomNewEdit(null, EditNomE);
										dialog.setVisible(true);

										nomModel.setData("parent='"
												+ EditNomE.getParent() + "'");
										sorter.setModel(nomModel);
										sorter.setSortable(0, false);
										sorter.setSortable(1, false);

										jTableNom.setRowSorter(sorter);
										jTableNom.repaint();
									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								} catch (PersistentException e2) {

									e2.printStackTrace();
								}
							}
						}
						if ((isFolderValue)
								&& (arg0.getModifiers() == InputEvent.BUTTON2_MASK)) {
							try {
								EditNomE = orm.NomenclatureDAO
										.loadNomenclatureByQuery("nomid='"
												+ strIDValue + "'", null);
								try {
									dialog = new NomNewEdit(null, EditNomE);
									dialog.setVisible(true);
									sorter.setModel(nomModel);
									sorter.setSortable(0, false);
									sorter.setSortable(1, false);

									jTableNom.setRowSorter(sorter);
									jTableNom.repaint();

								} catch (PersistentException e1) {

									e1.printStackTrace();
								}

							} catch (PersistentException e2) {

								e2.printStackTrace();
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}
				});

				JScrollPane pane = new JScrollPane(jTableNom);
				textFrame.add(pane, BorderLayout.CENTER);

				JPanel panel2 = new JPanel(new BorderLayout());

				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Фильтр");
				panel.add(label, BorderLayout.WEST);

				final JTextField filterText = new JTextField("");
				panel.add(filterText, BorderLayout.CENTER);
				// Ентер в поле поиска
				filterText.addActionListener(new ActionListener() {

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
								sorter.setModel(nomModel);
								sorter.setSortable(0, false);
								sorter.setSortable(1, false);

								jTableNom.setRowSorter(sorter);
								jTableNom.repaint();

							} catch (PatternSyntaxException pse) {
								System.err.println("Bad regex pattern");
							}
						}

					}
				});
				// Клик по кнопке фильтер
				JButton button = new JButton("Фильтр");
				button.addActionListener(new ActionListener() {

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
								sorter.setModel(nomModel);
								sorter.setSortable(0, false);
								sorter.setSortable(1, false);

								jTableNom.setRowSorter(sorter);
								jTableNom.repaint();

							} catch (PatternSyntaxException pse) {
								System.err.println("Bad regex pattern");
							}
						}

					}
				});

				panel.add(button, BorderLayout.EAST);
				JToolBar jtb = new JToolBar("nomBar");

				if ((GlobalVars.value != 1) && (GlobalVars.value != 2)) {
					delNomBt.setVisible(false);
					// editNomBt.setVisible(false);
				}

				NewNom newNomForm = new NewNom();
				newNomBt.addActionListener(newNomForm);

				EditNom editNomForm = new EditNom();
				editNomBt.addActionListener(editNomForm);

				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableNom.getValueAt(
								rowIndex, 1).toString();
						final String strNameValue = jTableNom.getValueAt(
								rowIndex, 1).toString();

						if (JOptionPane
								.showConfirmDialog(null, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;

						try {
							Delete1CERTData delete1CERTData = new Delete1CERTData();
							try {
								delete1CERTData
										.deleteNomenclatureData(strIDValue);

								JOptionPane.showMessageDialog(null, "Объект "
										+ jTableNom.getValueAt(rowIndex, 1)
												.toString() + " удален.",
										"Сообение",
										JOptionPane.INFORMATION_MESSAGE);

							} finally {
								nomModel.setData("parent=0");
								sorter.setModel(nomModel);
								sorter.setSortable(0, false);
								sorter.setSortable(1, false);

								jTableNom.setRowSorter(sorter);
								jTableNom.repaint();
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(
									null,
									"Объект нельзя уделить. \n"
											+ e1.getMessage(), "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				AllGpNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (AllGpNomBt.getIcon().equals(AllNom)) {
							nomModel.setData(null, null);
							jTableNom.setModel(nomModel);
							jTableNom
									.setRowSorter(new TableRowSorter<TableModel>(
											nomModel));
							jTableNom.repaint();

							AllGpNomBt.setIcon(GrNom);
						} else {

							nomModel.setData("parentID=1");
							// nomModel.setData(null, null);
							jTableNom.setModel(nomModel);
							jTableNom
									.setRowSorter(new TableRowSorter<TableModel>(
											nomModel));
							jTableNom.repaint();
							AllGpNomBt.setIcon(AllNom);
						}
					}
				});

				jtb.add(newNomBt);
				jtb.add(editNomBt);
				jtb.add(delNomBt);
				jtb.add(AllGpNomBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				textFrame.add(panel2, BorderLayout.NORTH);

				NomMenu.this.desktop.add(textFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			class NewNom implements ActionListener {

				public void actionPerformed(ActionEvent e) {
					int rowIndex = jTableNom.getSelectedRow();
					if (rowIndex == -1) {
						rowIndex = 1;
					}
					String strIDValue = null;
					try {
						strIDValue = jTableNom.getValueAt(rowIndex, 1)
								.toString();
					} catch (Exception e21) {
						strIDValue = "0";
						e21.printStackTrace();
					}

					try {
						Nomenclature EditNomE = orm.NomenclatureDAO
								.loadNomenclatureByQuery(
										"nomid='" + strIDValue + "'", null)
								.getParent();

						try {

							dialog = new NomNewEdit(null, null);
							dialog.setNomenGr(EditNomE);
							dialog.setVisible(true);
							nomModel.setData("parent='" + EditNomE.getParent()
									+ "'");

							sorter.setModel(nomModel);
							sorter.setSortable(0, false);
							sorter.setSortable(1, false);

							jTableNom.setRowSorter(sorter);
							jTableNom.repaint();
						} catch (PersistentException e1) {

							e1.printStackTrace();
						}
					} catch (PersistentException e2) {

						e2.printStackTrace();
					}

				}
			}

			class EditNom implements ActionListener {
				private Nomenclature EditNomE;

				public void actionPerformed(ActionEvent e) {
					int rowIndex = jTableNom.getSelectedRow();
					if (rowIndex == -1) {
						JOptionPane.showMessageDialog(textFrame,
								"Объект не выбран.", "Ошибка",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String strIDValue = jTableNom.getValueAt(rowIndex, 1)
							.toString();
					try {
						EditNomE = orm.NomenclatureDAO.loadNomenclatureByQuery(
								"nomid='" + strIDValue + "'", null);

						try {
							dialog = new NomNewEdit(null, EditNomE);
							dialog.setVisible(true);

							nomModel.setData("parent='" + EditNomE.getParent()
									+ "'");

							sorter.setModel(nomModel);
							sorter.setSortable(0, false);
							sorter.setSortable(1, false);

							jTableNom.setRowSorter(sorter);
							jTableNom.repaint();
						} catch (PersistentException e1) {

							e1.printStackTrace();
						}

					} catch (PersistentException e2) {

						e2.printStackTrace();
					}

				}
			}
		});
	}

	private void seriySprActionListener() {
		seriySpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame("Номер серии",
						new ImageIcon("resurs/series.png"));
				seriyModel = new SeriyTableModel(null, "seriyid");
				jTableSeriy = new JTable(seriyModel);

				jTableSeriy
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						seriyModel);

				jTableSeriy.setRowSorter(sorter);

				// Клик по сторочке
				jTableSeriy.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableSeriy.getSelectedRow();
							final String strIDValue = jTableSeriy.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableSeriy.getValueAt(
									rowIndex, 1).toString();
							final String strFullNameValue = jTableSeriy
									.getValueAt(rowIndex, 2).toString();

							final TextFrame textFrame = new TextFrame(
									"Номер серии: Редактирование.",
									new ImageIcon("resurs/series.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							JLabel fullNameLb = new JLabel(
									"Полное наименование");

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

							NomMenu.this.desktop.add(textFrame);
							// Save Seriy to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateSeriyData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText());
											seriyModel.setData(null, null);
											jTableSeriy.setModel(seriyModel);
											jTableSeriy
													.setRowSorter(new TableRowSorter<TableModel>(
															seriyModel));
											jTableSeriy.repaint();

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

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseReleased(MouseEvent e) {

					}
				});

				JScrollPane pane = new JScrollPane(jTableSeriy);
				tableFrame.add(pane, BorderLayout.CENTER);

				JPanel panel2 = new JPanel(new BorderLayout());

				final JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Фильтр");
				panel.add(label, BorderLayout.WEST);

				final JTextField filterText = new JTextField("");
				panel.add(filterText, BorderLayout.CENTER);
				// Ентер в поле поиска
				filterText.addActionListener(new ActionListener() {

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
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удвлить");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");

				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newSeriyActionLisenten(newNomBt);
				editSeriyBt(tableFrame, jTableSeriy, editNomBt);
				delSeriyBt(tableFrame, jTableSeriy, delNomBt);
				refSeriyBt(refNomBt);

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

				NomMenu.this.desktop.add(tableFrame);

			}

			/**
			 * @param tableFrame
			 * @param jTableSeriy
			 * @param delNomBt
			 */
			private void delSeriyBt(final TextFrame tableFrame,
					final JTable jTableSeriy, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableSeriy.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableSeriy.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableSeriy.getValueAt(
								rowIndex, 1).toString();

						if (JOptionPane
								.showConfirmDialog(tableFrame, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;

						try {
							Delete1CERTData delete1CERTData = new Delete1CERTData();
							try {
								delete1CERTData.deleteSeriyData(strIDValue);

								JOptionPane.showMessageDialog(
										tableFrame,
										"Объект "
												+ jTableSeriy.getValueAt(
														rowIndex, 1).toString()
												+ " удален.", "Сообение",
										JOptionPane.INFORMATION_MESSAGE);
								seriyModel.setData(null, null);
								jTableSeriy.setModel(seriyModel);
								jTableSeriy
										.setRowSorter(new TableRowSorter<TableModel>(
												seriyModel));
								jTableSeriy.repaint();

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя уделить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

					}

				});
			}

			/**
			 * @param model1
			 * @param refNomBt
			 */
			private void refSeriyBt(JButton refNomBt) {
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						seriyModel.setData(null, null);
						jTableSeriy.setModel(seriyModel);
						jTableSeriy
								.setRowSorter(new TableRowSorter<TableModel>(
										seriyModel));
						jTableSeriy.repaint();

					}
				});
			}

			/**
			 * @param tableFrame
			 * @param jTableSeriy
			 * @param editNomBt
			 */
			private void editSeriyBt(final TextFrame tableFrame,
					final JTable jTableSeriy, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableSeriy.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableSeriy.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableSeriy.getValueAt(
								rowIndex, 1).toString();
						final String strFullNameValue = jTableSeriy.getValueAt(
								rowIndex, 2).toString();

						final TextFrame textFrame = new TextFrame(
								"Номер серии: Редактирование.", new ImageIcon(
										"resurs/series.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdateSeriyData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText());
										seriyModel.setData(null, null);
										jTableSeriy.setModel(seriyModel);
										jTableSeriy
												.setRowSorter(new TableRowSorter<TableModel>(
														seriyModel));
										jTableSeriy.repaint();

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

			/**
			 * @param newNomBt
			 */
			private void newSeriyActionLisenten(JButton newNomBt) {
				newNomBt.addActionListener(new ActionListener() {
					TextFrame textFrame;
					JTextField nameTxt;
					JTextField fulLNameTxt;

					public void actionPerformed(ActionEvent e) {
						textFrame = new TextFrame("Номер серии: Новый.",
								new ImageIcon("resurs/series.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						nameTxt = new JTextField();
						fulLNameTxt = new JTextField();

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createSeriyData(
												nameTxt.getText(), "");
										seriyModel.setData(null, null);
										jTableSeriy.setModel(seriyModel);
										jTableSeriy
												.setRowSorter(new TableRowSorter<TableModel>(
														seriyModel));
										jTableSeriy.repaint();

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

	private void stallSprActionListener() {
		stallSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame(
						"Марка стали закладных и выпусков арматуры",
						new ImageIcon("resurs/stall.png"));
				stallModel = new StallTableModel(null, "stalid");
				jTableStall = new JTable(stallModel);

				jTableStall
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						stallModel);
				jTableStall.setRowSorter(sorter);

				// Клик по сторочке
				jTableStall.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableStall.getSelectedRow();
							final String strIDValue = jTableStall.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableStall.getValueAt(
									rowIndex, 1).toString();
							final String strFullNameValue = jTableStall
									.getValueAt(rowIndex, 2).toString();
							final TextFrame textFrame = new TextFrame(
									"Марка стали закладных и выпусков арматуры: Редактирование.",
									new ImageIcon("resurs/stall.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							JLabel fullNameLb = new JLabel(
									"Полное наименование");

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

							NomMenu.this.desktop.add(textFrame);
							// Save Stall to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateStallData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText());
											stallModel.setData(null, null);
											jTableStall.setModel(stallModel);
											jTableStall
													.setRowSorter(new TableRowSorter<TableModel>(
															stallModel));
											jTableStall.repaint();
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

				JScrollPane pane = new JScrollPane(jTableStall);
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
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удвлить");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");

				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newNomActionLisenten(newNomBt);
				editNomBt(tableFrame, jTableStall, editNomBt);
				delNomBt(tableFrame, jTableStall, delNomBt);
				refNomBt(refNomBt);

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

				NomMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableStall
			 * @param delNomBt
			 */
			private void delNomBt(final TextFrame tableFrame,
					final JTable jTableStall, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableStall.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableStall.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableStall.getValueAt(
								rowIndex, 1).toString();

						if (JOptionPane
								.showConfirmDialog(tableFrame, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;

						try {
							Delete1CERTData delete1CERTData = new Delete1CERTData();
							try {
								delete1CERTData.deleteStallData(strIDValue);

								JOptionPane.showMessageDialog(
										tableFrame,
										"Объект "
												+ jTableStall.getValueAt(
														rowIndex, 1).toString()
												+ " удален.", "Сообение",
										JOptionPane.INFORMATION_MESSAGE);
								stallModel.setData(null, null);
								jTableStall.setModel(stallModel);
								jTableStall
										.setRowSorter(new TableRowSorter<TableModel>(
												stallModel));
								jTableStall.repaint();

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя уделить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

					}

				});
			}

			/**
			 * @param model1
			 * @param refNomBt
			 */
			private void refNomBt(JButton refNomBt) {
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						stallModel.setData(null, null);
						jTableStall.setModel(stallModel);
						jTableStall
								.setRowSorter(new TableRowSorter<TableModel>(
										stallModel));
						jTableStall.repaint();
					}
				});
			}

			/**
			 * @param tableFrame
			 * @param jTableStall
			 * @param editNomBt
			 */
			private void editNomBt(final TextFrame tableFrame,
					final JTable jTableStall, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableStall.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableStall.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableStall.getValueAt(
								rowIndex, 1).toString();
						final String strFullNameValue = jTableStall.getValueAt(
								rowIndex, 2).toString();

						final TextFrame textFrame = new TextFrame(
								"Марка стали закладных и выпусков арматуры: Редактирование.",
								new ImageIcon("resurs/stall.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdateStallData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText());
										stallModel.setData(null, null);
										jTableStall.setModel(stallModel);
										jTableStall
												.setRowSorter(new TableRowSorter<TableModel>(
														stallModel));
										jTableStall.repaint();

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

			/**
			 * @param newNomBt
			 */
			private void newNomActionLisenten(JButton newNomBt) {
				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Марка стали закладных и выпусков арматуры: Новый.",
								new ImageIcon("resurs/stall.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createStall(
												nameTxt.getText(),
												fulLNameTxt.getText());
										stallModel.setData(null, null);
										jTableStall.setModel(stallModel);
										jTableStall
												.setRowSorter(new TableRowSorter<TableModel>(
														stallModel));
										jTableStall.repaint();

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

	private void markaSprActionListener() {
		markaSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame("Марка бетона",
						new ImageIcon("resurs/m.png"));
				markaModel = new MarkaTableModel(null, "marcaid");
				jTableMarka = new JTable(markaModel);

				jTableMarka
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						markaModel);
				jTableMarka.setRowSorter(sorter);

				// Клик по сторочке
				jTableMarka.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableMarka.getSelectedRow();
							final String strIDValue = jTableMarka.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableMarka.getValueAt(
									rowIndex, 1).toString();

							final TextFrame textFrame = new TextFrame(
									"Марка бетона: Редактирование.",
									new ImageIcon("resurs/m.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							// JLabel fullNameLb = new JLabel(
							// "Полное наименование");

							JTextField codIdTxt = new JTextField(strIDValue);
							codIdTxt.setEnabled(false);
							final JTextField nameTxt = new JTextField(
									strNameValue);

							FormLayout layout = new FormLayout(
									"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
									"fill:p:grow, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

							DefaultFormBuilder builder = new DefaultFormBuilder(
									layout);
							// , new FormDebugPanel()
							builder.setDefaultDialogBorder();
							CellConstraints cc = new CellConstraints();

							builder.add(codLb, cc.xy(2, 2));
							builder.add(codIdTxt, cc.xy(4, 2));
							builder.add(nameLb, cc.xy(2, 4));
							builder.add(nameTxt, cc.xy(4, 4));
							builder.add(ButtonBarFactory
									.buildAddRemoveRightBar(saveBt, canBt), cc
									.xy(4, 6));

							textFrame.add(builder.getPanel());

							NomMenu.this.desktop.add(textFrame);
							// Save Seriy to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateMarkaData(
															strIDValue,
															nameTxt.getText());
											markaModel.setData(null, null);
											jTableMarka.setModel(markaModel);
											jTableMarka
													.setRowSorter(new TableRowSorter<TableModel>(
															markaModel));
											jTableMarka.repaint();

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

				JScrollPane pane = new JScrollPane(jTableMarka);
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
				ImageIcon newMarka = new ImageIcon("resurs/newNom.png");
				ImageIcon editMarka = new ImageIcon("resurs/editNom.png");
				ImageIcon delMarka = new ImageIcon("resurs/delNom.png");
				ImageIcon refMarka = new ImageIcon("resurs/refNom.png");
				JButton newMarkaBt = new JButton(newMarka);
				newMarkaBt.setText("Новый");
				JButton editMarkaBt = new JButton(editMarka);
				editMarkaBt.setText("Изменить");
				JButton delMarkaBt = new JButton(delMarka);
				delMarkaBt.setText("Удвлить");
				JButton refMarkaBt = new JButton(refMarka);
				refMarkaBt.setText("Обновить");

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newMarkaActionLisenten(newMarkaBt);
				editMarkaBt(tableFrame, jTableMarka, editMarkaBt);
				delMarkaBt(tableFrame, jTableMarka, delMarkaBt);
				refMarkaBt(refMarkaBt);

				jtb.add(newMarkaBt);
				jtb.add(editMarkaBt);
				jtb.add(delMarkaBt);
				jtb.add(refMarkaBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				tableFrame.add(panel2, BorderLayout.NORTH);
				tableFrame.add(pane, BorderLayout.CENTER);

				NomMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableMarka
			 * @param delMarkaBt
			 */
			private void delMarkaBt(final TextFrame tableFrame,
					final JTable jTableMarka, JButton delMarkaBt) {
				delMarkaBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableMarka.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableMarka.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableMarka.getValueAt(
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
								delete1CERTData.deleteMarkaData(strIDValue);
							} catch (Exception e21) {
								flag = false;
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}

						} catch (Exception e1) {
							e1.printStackTrace();
							flag = false;
						}
						if (flag) {
							JOptionPane
									.showMessageDialog(
											tableFrame,
											"Объект "
													+ jTableMarka.getValueAt(
															rowIndex, 1)
															.toString()
													+ " удален.", "Сообение",
											JOptionPane.INFORMATION_MESSAGE);
							markaModel.setData(null, null);
							jTableMarka.setModel(markaModel);
							jTableMarka
									.setRowSorter(new TableRowSorter<TableModel>(
											markaModel));
							jTableMarka.repaint();
						}

						else
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя уделить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);

					}

				});
			}

			/**
			 * @param model1
			 * @param refNomBt
			 */
			private void refMarkaBt(JButton refMarkaBt) {
				refMarkaBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						markaModel.setData(null, null);
						jTableMarka.setModel(markaModel);
						jTableMarka
								.setRowSorter(new TableRowSorter<TableModel>(
										markaModel));
						jTableMarka.repaint();

					}
				});
			}

			/**
			 * @param tableFrame
			 * @param jTableMarka
			 * @param editMarkaBt
			 */
			private void editMarkaBt(final TextFrame tableFrame,
					final JTable jTableMarka, JButton editMarkaBt) {
				editMarkaBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableMarka.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableMarka.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableMarka.getValueAt(
								rowIndex, 1).toString();

						final TextFrame textFrame = new TextFrame(
								"Марка бетона: Редактирование.", new ImageIcon(
										"resurs/m.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						// JLabel fullNameLb = new
						// JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField(strIDValue);
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField(strNameValue);

						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xy(4, 2));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xy(4, 4));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xy(4, 6));

						textFrame.add(builder.getPanel());

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdateMarkaData(
												strIDValue, nameTxt.getText());
										markaModel.setData(null, null);
										jTableMarka.setModel(markaModel);
										jTableMarka
												.setRowSorter(new TableRowSorter<TableModel>(
														markaModel));
										jTableMarka.repaint();

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

			/**
			 * @param newMarkaBt
			 */
			private void newMarkaActionLisenten(JButton newMarkaBt) {
				newMarkaBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Марка бетона: Новый.", new ImageIcon(
										"resurs/m.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();

						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();

						builder.add(codLb, cc.xy(2, 2));
						builder.add(codIdTxt, cc.xy(4, 2));
						builder.add(nameLb, cc.xy(2, 4));
						builder.add(nameTxt, cc.xy(4, 4));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xy(4, 6));

						textFrame.add(builder.getPanel());

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createMarkaData(
												nameTxt.getText(), "");
										markaModel.setData(null, null);
										jTableMarka.setModel(markaModel);
										jTableMarka
												.setRowSorter(new TableRowSorter<TableModel>(
														markaModel));
										jTableMarka.repaint();

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

	private void antiKorSprActionListener() {
		antikorSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame(
						"Вид антикоррозийного покрытия", new ImageIcon(
								"resurs/pokr.png"));
				antiKorModel = new AntiKorTableModel(null, "antiKorid");
				jTableAntiKor = new JTable(antiKorModel);

				jTableAntiKor
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						antiKorModel);
				jTableAntiKor.setRowSorter(sorter);

				// Клик по сторочке
				jTableAntiKor.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableAntiKor.getSelectedRow();
							final String strIDValue = jTableAntiKor.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableAntiKor
									.getValueAt(rowIndex, 1).toString();
							final String strFullNameValue = jTableAntiKor
									.getValueAt(rowIndex, 2).toString();

							final TextFrame textFrame = new TextFrame(
									"Вид антикоррозийного покрытия: Редактирование.",
									new ImageIcon("resurs/pokr.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							JLabel fullNameLb = new JLabel(
									"Полное наименование");

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

							NomMenu.this.desktop.add(textFrame);
							// Save AntiKor to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateAntiKorData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText());
											antiKorModel.setData(null, null);
											jTableAntiKor
													.setModel(antiKorModel);
											jTableAntiKor
													.setRowSorter(new TableRowSorter<TableModel>(
															antiKorModel));
											jTableAntiKor.repaint();

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

				JScrollPane pane = new JScrollPane(jTableAntiKor);
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
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удвлить");
				JButton refAntiKorBt = new JButton(refNom);
				refAntiKorBt.setText("Обновить");

				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newAntiKorActionLisenten(newNomBt);
				editAntiKorBt(tableFrame, jTableAntiKor, editNomBt);
				delAntiKorBt(tableFrame, jTableAntiKor, delNomBt);
				refAntiKorBt(refAntiKorBt);

				jtb.add(newNomBt);
				jtb.add(editNomBt);
				jtb.add(delNomBt);
				jtb.add(refAntiKorBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				tableFrame.add(panel2, BorderLayout.NORTH);
				tableFrame.add(pane, BorderLayout.CENTER);

				NomMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableAntiKor
			 * @param delNomBt
			 */
			private void delAntiKorBt(final TextFrame tableFrame,
					final JTable jTableAntiKor, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableAntiKor.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableAntiKor.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableAntiKor.getValueAt(
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
								delete1CERTData.deleteAntiKorData(strIDValue);

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
						if (flag) {
							JOptionPane
									.showMessageDialog(
											tableFrame,
											"Объект "
													+ jTableAntiKor.getValueAt(
															rowIndex, 1)
															.toString()
													+ " удален.", "Сообение",
											JOptionPane.INFORMATION_MESSAGE);
							antiKorModel.setData(null, null);
							jTableAntiKor.setModel(antiKorModel);
							jTableAntiKor
									.setRowSorter(new TableRowSorter<TableModel>(
											antiKorModel));
							jTableAntiKor.repaint();
						}

						else
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);

					}

				});
			}

			private void refAntiKorBt(JButton refNomBt) {
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						antiKorModel.setData(null, null);
						jTableAntiKor.setModel(antiKorModel);
						jTableAntiKor
								.setRowSorter(new TableRowSorter<TableModel>(
										antiKorModel));
						jTableAntiKor.repaint();

					}
				});
			}

			private void editAntiKorBt(final TextFrame tableFrame,
					final JTable jTableAntiKor, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableAntiKor.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableAntiKor.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableAntiKor.getValueAt(
								rowIndex, 1).toString();
						final String strFullNameValue = jTableAntiKor
								.getValueAt(rowIndex, 2).toString();

						final TextFrame textFrame = new TextFrame(
								"Вид антикоррозийного покрытия: Редактирование.",
								new ImageIcon("resurs/pokr.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate
												.retrieveAndUpdateAntiKorData(
														strIDValue,
														nameTxt.getText(),
														fulLNameTxt.getText());
										antiKorModel.setData(null, null);
										jTableAntiKor.setModel(antiKorModel);
										jTableAntiKor
												.setRowSorter(new TableRowSorter<TableModel>(
														antiKorModel));
										jTableAntiKor.repaint();

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

			private void newAntiKorActionLisenten(JButton newNomBt) {
				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Вид антикоррозийного покрытия: Новый.",
								new ImageIcon("resurs/pokr.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createAntiKorData(
												nameTxt.getText(),
												fulLNameTxt.getText());
										antiKorModel.setData(null, null);
										jTableAntiKor.setModel(antiKorModel);
										jTableAntiKor
												.setRowSorter(new TableRowSorter<TableModel>(
														antiKorModel));
										jTableAntiKor.repaint();

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

	private void katPovSprActionListener() {
		katPovSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame(
						"Категория лицевых бетонных поверхностей",
						new ImageIcon("resurs/kat.png"));
				katPovModel = new KatPovTableModel(null, "katPovID");
				jTableKatPov = new JTable(katPovModel);

				jTableKatPov
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						katPovModel);
				jTableKatPov.setRowSorter(sorter);

				// Клик по сторочке
				jTableKatPov.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableKatPov.getSelectedRow();
							final String strIDValue = jTableKatPov.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableKatPov
									.getValueAt(rowIndex, 1).toString();
							final String strFullNameValue = jTableKatPov
									.getValueAt(rowIndex, 2).toString();

							final TextFrame textFrame = new TextFrame(
									"Категория лицевых бетонных поверхностей: Редактирование.",
									new ImageIcon("resurs/kat.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							JLabel fullNameLb = new JLabel(
									"Полное наименование");

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

							NomMenu.this.desktop.add(textFrame);
							// Save KatPov to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate
													.retrieveAndUpdateKatPovData(
															strIDValue, nameTxt
																	.getText(),
															fulLNameTxt
																	.getText());
											katPovModel.setData(null, null);
											jTableKatPov.setModel(katPovModel);
											jTableKatPov
													.setRowSorter(new TableRowSorter<TableModel>(
															katPovModel));
											jTableKatPov.repaint();

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

				JScrollPane pane = new JScrollPane(jTableKatPov);
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
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удвлить");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");

				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);

				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newKatPovActionLisenten(newNomBt);
				editKatPovBt(tableFrame, jTableKatPov, editNomBt);
				delKatPovBt(tableFrame, jTableKatPov, delNomBt);
				refKatPovBt(refNomBt);

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

				NomMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableKatPov
			 * @param delNomBt
			 */
			private void delKatPovBt(final TextFrame tableFrame,
					final JTable jTableKatPov, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableKatPov.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableKatPov.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableKatPov.getValueAt(
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
								delete1CERTData.deleteKatPovData(strIDValue);

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
							JOptionPane
									.showMessageDialog(
											tableFrame,
											"Объект "
													+ jTableKatPov.getValueAt(
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

			private void refKatPovBt(JButton refNomBt) {
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						katPovModel.setData(null, null);
						jTableKatPov.setModel(katPovModel);
						jTableKatPov
								.setRowSorter(new TableRowSorter<TableModel>(
										katPovModel));
						jTableKatPov.repaint();
					}
				});
			}

			private void editKatPovBt(final TextFrame tableFrame,
					final JTable jTableKatPov, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableKatPov.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableKatPov.getValueAt(
								rowIndex, 0).toString();
						final String strNameValue = jTableKatPov.getValueAt(
								rowIndex, 1).toString();
						final String strFullNameValue = jTableKatPov
								.getValueAt(rowIndex, 2).toString();

						final TextFrame textFrame = new TextFrame(
								"Категория лицевых бетонных поверхностей: Редактирование.",
								new ImageIcon("resurs/kat.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdateKatPovData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText());
										katPovModel.setData(null, null);
										jTableKatPov.setModel(katPovModel);
										jTableKatPov
												.setRowSorter(new TableRowSorter<TableModel>(
														katPovModel));
										jTableKatPov.repaint();

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

			private void newKatPovActionLisenten(JButton newNomBt) {
				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Категория лицевых бетонных поверхностей: Новый.",
								new ImageIcon("resurs/kat.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createKatPovData(
												nameTxt.getText(),
												fulLNameTxt.getText());
										katPovModel.setData(null, null);
										jTableKatPov.setModel(katPovModel);
										jTableKatPov
												.setRowSorter(new TableRowSorter<TableModel>(
														katPovModel));
										jTableKatPov.repaint();

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

	private void tUSprActionListener() {
		tUSpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				final TextFrame tableFrame = new TextFrame(
						"Обозначение стандарта (ТУ)", new ImageIcon(
								"resurs/norma.png"));
				tUModel = new TUTableModel(null, "tuID");
				jTableTU = new JTable(tUModel);

				jTableTU.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				final RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
						tUModel);
				jTableTU.setRowSorter(sorter);

				// Клик по сторочке
				jTableTU.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {
							int rowIndex = jTableTU.getSelectedRow();
							final String strIDValue = jTableTU.getValueAt(
									rowIndex, 0).toString();
							final String strNameValue = jTableTU.getValueAt(
									rowIndex, 1).toString();
							final String strFullNameValue = jTableTU
									.getValueAt(rowIndex, 2).toString();

							final TextFrame textFrame = new TextFrame(
									"Обозначение стандарта (ТУ): Редактирование.",
									new ImageIcon("resurs/norma.png"));
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JLabel codLb = new JLabel("Код");
							JLabel nameLb = new JLabel("Наименование");
							JLabel fullNameLb = new JLabel(
									"Полное наименование");

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

							NomMenu.this.desktop.add(textFrame);
							// Save TU to DateBase
							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {

									try {
										RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

										try {
											updateDate.retrieveAndUpdateTUData(
													strIDValue,
													nameTxt.getText(),
													fulLNameTxt.getText());
											tUModel.setData(null, null);
											jTableTU.setModel(tUModel);
											jTableTU.setRowSorter(new TableRowSorter<TableModel>(
													tUModel));
											jTableTU.repaint();

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

				JScrollPane pane = new JScrollPane(jTableTU);
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
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удвлить");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");

				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);
				// Слушатели "Новый", "Изменить", "Удалить", "Обновить"
				newTUActionLisenten(newNomBt);
				editTUBt(tableFrame, jTableTU, editNomBt);
				delTUBt(tableFrame, jTableTU, delNomBt);
				refTUBt(refNomBt);

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

				NomMenu.this.desktop.add(tableFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}

			/**
			 * @param tableFrame
			 * @param jTableTU
			 * @param delNomBt
			 */
			private void delTUBt(final TextFrame tableFrame,
					final JTable jTableTU, JButton delNomBt) {
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableTU.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableTU.getValueAt(rowIndex,
								0).toString();
						final String strNameValue = jTableTU.getValueAt(
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
								delete1CERTData.deleteTUData(strIDValue);
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
						if (flag) {
							JOptionPane
									.showMessageDialog(tableFrame, "Объект "
											+ jTableTU.getValueAt(rowIndex, 1)
													.toString() + " удален.",
											"Сообение",
											JOptionPane.INFORMATION_MESSAGE);
							tUModel.setData(null, null);
							jTableTU.setModel(tUModel);
							jTableTU.setRowSorter(new TableRowSorter<TableModel>(
									tUModel));
							jTableTU.repaint();
						}

						else
							JOptionPane.showMessageDialog(tableFrame,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);

					}

				});
			}

			private void refTUBt(JButton refNomBt) {
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						tUModel.setData(null, null);
						jTableTU.setModel(tUModel);
						jTableTU.setRowSorter(new TableRowSorter<TableModel>(
								tUModel));
						jTableTU.repaint();

					}
				});
			}

			private void editTUBt(final TextFrame tableFrame,
					final JTable jTableTU, JButton editNomBt) {
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableTU.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(tableFrame,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strIDValue = jTableTU.getValueAt(rowIndex,
								0).toString();
						final String strNameValue = jTableTU.getValueAt(
								rowIndex, 1).toString();
						final String strFullNameValue = jTableTU.getValueAt(
								rowIndex, 2).toString();

						final TextFrame textFrame = new TextFrame(
								"Обозначение стандарта (ТУ): Редактирование.",
								new ImageIcon("resurs/norma.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();

									try {
										updateDate.retrieveAndUpdateTUData(
												strIDValue, nameTxt.getText(),
												fulLNameTxt.getText());
										tUModel.setData(null, null);
										jTableTU.setModel(tUModel);
										jTableTU.setRowSorter(new TableRowSorter<TableModel>(
												tUModel));
										jTableTU.repaint();

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

			private void newTUActionLisenten(JButton newNomBt) {
				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						final TextFrame textFrame = new TextFrame(
								"Обозначение стандарта (ТУ): Новый.",
								new ImageIcon("resurs/norma.png"));
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JLabel codLb = new JLabel("Код");
						JLabel nameLb = new JLabel("Наименование");
						JLabel fullNameLb = new JLabel("Полное наименование");

						JTextField codIdTxt = new JTextField();
						codIdTxt.setEnabled(false);
						final JTextField nameTxt = new JTextField();
						final JTextField fulLNameTxt = new JTextField();

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

						NomMenu.this.desktop.add(textFrame);
						// Save Stall to DateBase
						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								try {
									Create1CERTData create1CERTData = new Create1CERTData();
									try {
										create1CERTData.createTUData(
												nameTxt.getText(),
												fulLNameTxt.getText(), "");
										tUModel.setData(null, null);
										jTableTU.setModel(tUModel);
										jTableTU.setRowSorter(new TableRowSorter<TableModel>(
												tUModel));
										jTableTU.repaint();

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
