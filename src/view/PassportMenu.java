package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import com.toedter.calendar.JCalendar;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import TableModel.*;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import orm.Certif;
import orm.Certif_Nomenclature;
import orm.Nomenclature;
import orm.NomenclatureCount;
import ormsamples.Create1CERTData;
import ormsamples.Delete1CERTData;
import ormsamples.List1CERTData;
import ormsamples.RetrieveAndUpdate1CERTData;

public class PassportMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876543236641853676L;
	private MDIDesktopPane desktop;
	private JMenuItem passDoc = new JMenuItem("Паспорта", new ImageIcon(
			"resurs/cer.png"));
	private JMenuItem partDoc = new JMenuItem("Партии", new ImageIcon(
			"resurs/part.png"));
	private JCalendar dat;
	private CalendarChoser dialog;
	private JTextField dateTxt;
	private JTextField numberTxt;
	private JTextField markaMPZTxt;
	private JTextField prochProTxt;
	private JTextField prochFctTxt;
	private JTextField nomenklTxt;
	private JTextField markaTxt;
	private int intIDValue;
	private int intNumbeValue;
	private int prochProValue;
	private int prochFctValue;
	private String markaValue;
	private String nomenclaturaValue;
	private Timestamp dateValue;
	private JTextField numberPartTxt;
	private JTextField numberOfProdTxt;
	private JTextField datePartTxt;
	private JTextField partProchFactTxt;
	private JTextField partProchProTxt;
	private JTextField markaPartTxt;
	private JTextField markaPartFactTxt;
	private JTextField numenclTxt;
	private JTextField userTxt;
	// private NomChoser dialogPart = null;
	private NomCountChooserTableModel nomModelSelect = null;
	private NomChoser dialogNumencl = null;
	private JScrollPane panePass;
	private JTable jTableNom;
	private JTable jTableNomPart;
	private PartsTableModel partsModel;
	private TableRowSorter<TableModel> sorter, sorterPart;
	private NomChoserAllPass dialogChooserNom;
	private Nomenclature nomE;
	// private NomChooserTableModel nomModelSelect = null;
	private JTable jTablePass;
	private JTable jTableListNom;
	private orm.Part part;
	private orm.Users user;
	private PassTableModel passModel;
	private Certif certifSelect;
	private Certif_Nomenclature[] cerNoSelect;

	// private JTextField text;
	// private PageFormat pageFormat;
	// private PrintRequestAttributeSet attributes;

	// private JTable jTableNom;

	public PassportMenu(MDIDesktopPane desktop) throws PersistentException {
		this.desktop = desktop;
		setText("Документы");

		passDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				TextFrame textFrame = new TextFrame("Паспорта", new ImageIcon(
						"resurs/cer.png"));

				passModel = new PassTableModel(null, null);
				jTableNom = new JTable(passModel);

				jTableNom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				/*
				 * final RowSorter<TableModel> sorter = new
				 * TableRowSorter<TableModel>( passModel);
				 * jTableNom.setRowSorter(sorter);
				 */
				sorterPart = new TableRowSorter<TableModel>(passModel) {
					@Override
					public Comparator<?> getComparator(int column) {
						// для нулевой строки
						switch (column) {
						case 0:
							return new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									if (Integer.parseInt(s1.substring(0, 1)) == Integer
											.parseInt(s2.substring(0, 1)))
										return Integer.parseInt(s1.substring(3,
												s1.length()))
												- Integer.parseInt(s2
														.substring(3,
																s2.length()));
									return Integer.parseInt(s1.substring(0, 1))
											- Integer.parseInt(s2.substring(0,
													1));
								}
							};
						case 3:
							return new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									return Integer.parseInt(s1)
											- Integer.parseInt(s2);
								}
							};
						case 2:
						case 4:
							return new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									DateFormat df1 = DateFormat
											.getDateInstance();
									DateFormat df2 = DateFormat
											.getDateInstance();
									Date date1 = new Date();
									Date date2 = new Date();
									try {
										date1 = df1.parse(s1);
										date2 = df1.parse(s2);

									} catch (ParseException e) {

									}
									if (date1.before(date2))
										return -1;
									else
										return 1;
								}
							};
							// для всех остальных
						default:
							return super.getComparator(column);
						}
					}
				};
				jTableNom.setRowSorter(sorterPart);

				// Клик по сторочке
				jTableNom.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

							int rowIndex = jTableNom.getSelectedRow();
							if (rowIndex == -1) {
								JOptionPane.showMessageDialog(null,
										"Объект не выбран.", "Ошибка",
										JOptionPane.ERROR_MESSAGE);
								return;
							}

							String strNumberOfPart = jTableNom.getValueAt(
									rowIndex, 0).toString();
							try {
								List1CERTData list1CERTData = new List1CERTData();
								try {
									certifSelect = list1CERTData
											.listCertifData("NumberOfCert='"
													+ strNumberOfPart + "'",
													null)[0];
								} finally {
									orm._1CERTPersistentManager.instance()
											.disposePersistentManager();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}

							try {
								List1CERTData list1CERTData = new List1CERTData();
								try {
									cerNoSelect = list1CERTData.listCertif_NomData(
											"CertifcertifID='"
													+ certifSelect
															.getCertifID()
													+ "'", null);

								} finally {
									orm._1CERTPersistentManager.instance()
											.disposePersistentManager();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}

							dat = new JCalendar();
							final TextFrame textFrame = new TextFrame(
									"Редактирование документа: Паспорт.", true);
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");
							JButton calChooserBt = new JButton("...");
							JButton calPartChooserBt = new JButton("...");
							JButton calNumenclChooserBt = new JButton("...");
							numberTxt = new JTextField();
							numberTxt.setText(strNumberOfPart);
							numberTxt.setEditable(false);
							prochProTxt = new JTextField();
							prochFctTxt = new JTextField();
							nomenklTxt = new JTextField();
							markaTxt = new JTextField();
							dateTxt = new JTextField(new SimpleDateFormat(
									"dd.MM.yyyy").format(certifSelect
									.getCertifTimes()));
							dateTxt.setEditable(false);

							datePartTxt = new JTextField();
							markaMPZTxt = new JTextField();
							datePartTxt.setEditable(false);
							numberOfProdTxt = new JTextField();
							numberPartTxt = new JTextField();
							numberPartTxt.setEditable(false);
							partProchFactTxt = new JTextField();
							partProchFactTxt.setEditable(false);
							partProchProTxt = new JTextField();
							partProchProTxt.setEditable(false);
							markaPartTxt = new JTextField();
							markaPartTxt.setEditable(false);
							markaPartFactTxt = new JTextField();
							markaPartFactTxt.setEditable(false);
							numenclTxt = new JTextField();
							numenclTxt.setEditable(false);
							userTxt = new JTextField();
							userTxt.setEditable(false);
							userTxt.setText(GlobalVars.getUser().getUserName());

							if (certifSelect.getMarcaMPZ() != null)
								markaMPZTxt.setText(certifSelect.getMarcaMPZ());
							else
								markaMPZTxt.setText(" ");
							numberPartTxt.setText(Integer.toString(certifSelect
									.getPartpart().getNumberOfPart()));
							datePartTxt.setText(new SimpleDateFormat(
									"dd.MM.yyyy").format(certifSelect
									.getPartpart().getPartDate()));
							partProchProTxt.setText(Integer
									.toString(certifSelect.getPartpart()
											.getPartProchPro()));
							partProchFactTxt.setText(Integer
									.toString(certifSelect.getPartpart()
											.getPartProchFact()));
							markaPartFactTxt.setText(cerNoSelect[0]
									.getNomenclaturenom().getMarkamarca1()
									.getMarcaName());
							markaPartTxt.setText(certifSelect.getPartpart()
									.getMarka());
							numberOfProdTxt.setText(Integer
									.toString(cerNoSelect[0].getNumberOfProd()));

							nomModelSelect = new NomCountChooserTableModel();
							for (Certif_Nomenclature cerNomSelect : cerNoSelect) {
								NomenclatureCount certCount = new NomenclatureCount(
										cerNomSelect.getNomenclaturenom(),
										cerNomSelect.getCountN());
								nomModelSelect.setValueAt(certCount);

							}

							jTablePass = new JTable(nomModelSelect);
							jTablePass
									.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

							// 25.01.2012
							jTablePass.addMouseListener(new MouseListener() {

								@Override
								public void mouseClicked(MouseEvent arg0) {
									if ((arg0.getClickCount() == 2)
											&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

										int rowIndex = jTablePass
												.getSelectedRow();

										if (rowIndex == -1) {
											JOptionPane.showMessageDialog(null,
													"Объект не выбран.",
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
											return;
										}

										int numberId = Integer
												.parseInt(jTablePass
														.getValueAt(rowIndex, 0)
														.toString());
										String s = (String) JOptionPane
												.showInputDialog(
														null,
														"Количество",
														"Ввод количества изделий",
														JOptionPane.QUESTION_MESSAGE,
														null, null, null);
										Pattern p = Pattern.compile("\\d+");
										Matcher m = p.matcher(s);
										if (!m.matches()) {
											JOptionPane
													.showMessageDialog(null,
															"Не корректный ввод числа! \n Повторите ввод.");
										} else if ((s != null)
												&& (s.length() > 0)) {
											nomModelSelect.setValueAt(
													Integer.parseInt(s),
													numberId);
										}
										jTablePass.setModel(nomModelSelect);
										jTablePass.repaint();
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

							panePass = new JScrollPane(jTablePass);

							JToolBar jtb = new JToolBar("nomBar");
							ImageIcon newNom = new ImageIcon(
									"resurs/newNom.png");
							// ImageIcon editNom = new ImageIcon("editNom.png");
							ImageIcon delNom = new ImageIcon(
									"resurs/delNom.png");
							JButton newNomBt = new JButton(newNom);
							newNomBt.setText("Новый");
							// JButton editNomBt = new JButton(editNom);
							JButton delNomBt = new JButton(delNom);
							delNomBt.setText("Удалить");
							if (GlobalVars.value != 1)
								delNomBt.setVisible(false);
							jtb.add(newNomBt);
							// jtb.add(editNomBt);
							jtb.add(delNomBt);

							FormLayout layout = new FormLayout(
									"fill:default:grow, pref, 9dlu, fill:default:grow, 9dlu, fill:default:grow, 14dlu, fill:default:grow, p, fill:default:grow",
									"fill:default:grow, pref, 4dlu,pref,4dlu,pref, 0dlu,fill:default:grow, 4dlu,pref, 4dlu,pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 1dlu, p, fill:default:grow");

							DefaultFormBuilder builder = new DefaultFormBuilder(
									layout);
							// , new FormDebugPanel()
							builder.setDefaultDialogBorder();
							CellConstraints cc = new CellConstraints();
							builder.add(new JLabel("Паспорт №"), cc.xy(2, 2));
							builder.add(numberTxt, cc.xy(4, 2));
							builder.add(new JLabel("Выдан"), cc.xy(6, 2));
							builder.add(dateTxt, cc.xy(8, 2));
							builder.add(calChooserBt, cc.xy(9, 2));

							builder.add(new JLabel("Изделия:"), cc.xyw(2, 4, 8));
							builder.add(jtb, cc.xyw(2, 6, 8));
							builder.add(panePass, cc.xywh(2, 8, 8, 1));

							builder.add(new JLabel("партия"), cc.xy(6, 12));
							builder.add(numberPartTxt, cc.xy(8, 12));
							builder.add(calPartChooserBt, cc.xy(9, 12));

							builder.add(new JLabel("Марка бетона МПЗ"),
									cc.xy(2, 10));
							builder.add(markaMPZTxt, cc.xy(4, 10));

							builder.add(new JLabel("Количество изделий"),
									cc.xy(2, 12));
							builder.add(numberOfProdTxt, cc.xy(4, 12));
							builder.add(new JLabel("Дата изготовления"),
									cc.xy(6, 14));
							builder.add(datePartTxt, cc.xy(8, 14));

							builder.add(new JLabel("Отп. прочность, %"),
									cc.xy(2, 14));
							builder.add(partProchProTxt, cc.xy(4, 14));
							builder.add(new JLabel("Факт. прочность, кгс/см2"),
									cc.xy(6, 16));
							builder.add(partProchFactTxt, cc.xy(8, 16));

							builder.add(new JLabel("Проектная марка бетона"),
									cc.xy(2, 16));
							builder.add(markaPartTxt, cc.xy(4, 16));

							builder.add(new JLabel("Фактическая марка бетона"),
									cc.xy(2, 18));
							builder.add(markaPartFactTxt, cc.xy(4, 18));

							builder.add(new JLabel("Ответственный"),
									cc.xy(6, 18));
							builder.add(userTxt, cc.xy(8, 18));

							builder.add(ButtonBarFactory
									.buildAddRemoveRightBar(saveBt, canBt), cc
									.xyw(4, 20, 5));
							textFrame.add(builder.getPanel());

							PassportMenu.this.desktop.add(textFrame);
							newNomBt.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent event) {

									// if first time, construct dialog

									if (dialogChooserNom == null) {
										NomTableModelAll nomModelSelect = new NomTableModelAll(
												null, "nomID");
										nomModelSelect.setData(
												"isFolder='true'", "nomID");
										jTableListNom = new JTable(
												nomModelSelect);

										dialogChooserNom = new NomChoserAllPass(
												jTableListNom, nomModelSelect);

									}
									if (dialogChooserNom.showDialog(null,
											"Список номенклатуры")) {
										try {
											int i = dialogChooserNom.getDate(1);
											nomE = orm.NomenclatureDAO
													.listNomenclatureByQuery(
															"nomID='" + i + "'",
															null)[0];
											String s = (String) JOptionPane
													.showInputDialog(
															null,
															"Количество",
															"Ввод количества изделий",
															JOptionPane.QUESTION_MESSAGE,
															null, null, null);

											Pattern p = Pattern.compile("\\d+");
											Matcher m = p.matcher(s);
											if (!m.matches()) {
												JOptionPane
														.showMessageDialog(
																null,
																"Не корректный ввод числа! \n Повторите ввод.");

											} else if ((s != null)
													&& (s.length() > 0)) {
												nomModelSelect.setValueAt(nomE,
														Integer.parseInt(s));

											}

											markaPartFactTxt.setText(nomE
													.getMarkamarca1()
													.getMarcaName());

											jTablePass.setModel(nomModelSelect);
											jTablePass
													.setRowSorter(new TableRowSorter<TableModel>(
															nomModelSelect));
											jTablePass.repaint();

										} catch (PersistentException e1) {

											e1.printStackTrace();
										}

									}
								}
							});

							delNomBt.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									int rowIndex = jTablePass.getSelectedRow();
									if (rowIndex == -1) {
										JOptionPane.showMessageDialog(null,
												"Объект не выбран.", "Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
									final String strNameValue = jTablePass
											.getValueAt(rowIndex, 0).toString();

									if (JOptionPane.showConfirmDialog(null,
											"Объект " + strNameValue
													+ " будет удален!",
											"Предупреждение.",
											JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
										return;

									try {
										List1CERTData list1CERTData = new List1CERTData();
										try {

											nomE = list1CERTData.listNomenclatureData(
													"nomID='"
															+ Integer
																	.parseInt(strNameValue)
															+ "'", null)[0];

											nomModelSelect.delValueAt(nomE);
											jTablePass.setModel(nomModelSelect);
											jTablePass
													.setRowSorter(new TableRowSorter<TableModel>(
															nomModelSelect));
											jTablePass.repaint();

										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();
										}

									} catch (Exception e22) {
										e22.printStackTrace();
										JOptionPane.showMessageDialog(null,
												"Объект нельзя уделить. \n"
														+ e22.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
									}

								}
							});

							calChooserBt
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent event) {
											if (dialog == null) {
												dialog = new CalendarChoser(
														null);
											}

											if (dialog.showDialog(null,
													"Выбор даты")) {

												dateTxt.setText(dialog
														.getDate());

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
							calPartChooserBt
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {

											// if first time, construct dialog
											NomChoser dialogPart = null;
											if (dialogPart == null) {
												PartsTableModel partModelSelect = new PartsTableModel(
														null, "NumberOfPart");
												JTable jTableListAnti = new JTable(
														partModelSelect);

												final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
														partModelSelect);

												dialogPart = new NomChoser(
														jTableListAnti, sorter);
											}

											if (dialogPart.showDialog(null,
													"Выбор партии")) {
												try {
													int i = dialogPart
															.getDate(1);

													try {
														List1CERTData list1CERTData = new List1CERTData();

														try {

															part = list1CERTData
																	.listParsData(
																			"NumberOfPart='"
																					+ i
																					+ "'",
																			null)[0];
															numberPartTxt
																	.setText(Integer
																			.toString(part
																					.getNumberOfPart()));
															datePartTxt
																	.setText(new SimpleDateFormat(
																			"dd.MM.yyyy")
																			.format(part
																					.getPartDate()));
															partProchProTxt
																	.setText(Integer
																			.toString(part
																					.getPartProchPro()));
															partProchFactTxt
																	.setText(Integer
																			.toString(part
																					.getPartProchFact()));
															markaPartTxt
																	.setText(part
																			.getMarka());

														} finally {
															orm._1CERTPersistentManager
																	.instance()
																	.disposePersistentManager();
														}

													} catch (Exception ee) {
														ee.printStackTrace();
													}

												} catch (PersistentException e1) {

													e1.printStackTrace();
												}

											}

										}
									});
							calNumenclChooserBt
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {

											// if first time, construct dialog

											if (dialogNumencl == null) {
												NomTableModel nomModelSelect = new NomTableModel(
														null, "nomName");
												JTable jTableListAnti = new JTable(
														nomModelSelect);

												final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
														nomModelSelect);

												dialogNumencl = new NomChoser(
														jTableListAnti, sorter);
											}

											if (dialogNumencl.showDialog(null,
													"Выбор номенклатуры")) {
												try {
													int i = dialogNumencl
															.getDate(1);

													try {
														List1CERTData list1CERTData = new List1CERTData();

														orm.Nomenclature[] num;
														try {

															num = list1CERTData
																	.listNomenclatureData(
																			"nomID='"
																					+ i
																					+ "'",
																			null);
															numenclTxt
																	.setText(num[0]
																			.getNomName());

														} finally {
															orm._1CERTPersistentManager
																	.instance()
																	.disposePersistentManager();
														}

													} catch (Exception ee) {
														ee.printStackTrace();
													}

												} catch (PersistentException e1) {

													e1.printStackTrace();
												}

											}

										}
									});

							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									DateFormat df = DateFormat
											.getDateInstance();
									Date date;
									// int numberOfpart=0;

									if (datePartTxt.getText() == ""
											|| numberTxt.getText() == ""
											|| prochProTxt.getText() == ""
											|| numberOfProdTxt.getText() == ""
											|| nomModelSelect.getRowCount() == 0) {
										JOptionPane.showMessageDialog(null,
												"Не все поля заполнены. \n",
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
									// Update Certif and Certif_Nomenclature

									try {
										date = df.parse(dateTxt.getText());
										Timestamp timestamp = new Timestamp(
												date.getTime());
										try {
											RetrieveAndUpdate1CERTData retrieveAndUpdate1CERTData = new RetrieveAndUpdate1CERTData();
											List1CERTData list1CERTData = new List1CERTData();

											orm.Users user1 = null;
											orm.Part part1 = null;
											try {
												user1 = list1CERTData
														.listUsersData(
																"userrID='"
																		+ GlobalVars
																				.getUser()
																				.getUserrID()
																		+ "'",
																null)[0];
												part1 = list1CERTData
														.listParsData(
																"NumberOfPart='"
																		+ Integer
																				.parseInt(numberPartTxt
																						.getText())
																		+ "'",
																null)[0];

												orm.Certif certif = retrieveAndUpdate1CERTData
														.retrieveAndUpdateCertifData(
																numberTxt
																		.getText()
																		.trim(),
																part1,
																user1,
																timestamp,
																markaMPZTxt
																		.getText());
												// Update Certif_nom

												// cerNoSelect -old,
												// nomModelSelect-new
												int len = nomModelSelect
														.getRowCount();
												Nomenclature[] nomMasNew = new Nomenclature[len];
												try {
													List1CERTData list1CERTData2 = new List1CERTData();
													try {
														for (int i = 0; i < len; i++) {

															nomMasNew[i] = list1CERTData2
																	.listNomenclatureData(
																			"nomID='"
																					+ nomModelSelect
																							.getNomIDAt(i)
																					+ "'",
																			null)[0];
														}
													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}
												} catch (Exception e2) {
													e2.printStackTrace();
												}

												// orm.Certif_Nomenclature
												// oRMCertifNom;

												PersistentTransaction t = orm._1CERTPersistentManager
														.instance()
														.getSession()
														.beginTransaction();
												try {
													for (int i = 0; i < cerNoSelect.length; i++) {
														orm.Certif_NomenclatureDAO
																.delete(cerNoSelect[i]);
													}
													t.commit();

												} catch (Exception e2) {
													t.rollback();
												}

												Certif oRMCerfit;
												for (int i = 0; i < len; i++) {
													try {
														oRMCerfit = orm.CertifDAO
																.listCertifByQuery(
																		"certifID='"
																				+ certif.getCertifID()
																				+ "'",
																		null)[0];
														nomE = orm.NomenclatureDAO
																.listNomenclatureByQuery(
																		"nomID='"
																				+ nomMasNew[i]
																						.getNomID()
																				+ "'",
																		null)[0];
														try {
															Create1CERTData create1CERTData = new Create1CERTData();
															try {
																create1CERTData
																		.createCertifNomData(
																				nomE,
																				oRMCerfit,
																				Integer.parseInt(numberOfProdTxt
																						.getText()),
																				nomModelSelect
																						.getCountAt(i));
															} finally {
																orm._1CERTPersistentManager
																		.instance()
																		.disposePersistentManager();
															}
														} catch (Exception e2) {
															e2.printStackTrace();
														}

													} catch (Exception e12) {

														e12.printStackTrace();
														JOptionPane
																.showMessageDialog(
																		null,
																		e12.getMessage(),
																		"Ошибка",
																		JOptionPane.ERROR_MESSAGE);
														return;
													}
												}

												passModel.setData(null, null);
												// passModel.setValueAt(certif);

												jTableNom.setModel(passModel);
												jTableNom.repaint();

											} finally {
												orm._1CERTPersistentManager
														.instance()
														.disposePersistentManager();
											}

										} catch (Exception e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(
													null,
													"Ошибка записи"
															+ e1.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
											return;
										}
									} catch (Exception e2) {
										e2.printStackTrace();
										JOptionPane.showMessageDialog(
												null,
												"Ошибка записи"
														+ e2.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}

									try {
										textFrame.setClosed(true);
									} catch (PropertyVetoException e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(
												null,
												"Ошибка записи"
														+ e1.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
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
				ImageIcon printNom = new ImageIcon("resurs/print.png");
				ImageIcon refNom = new ImageIcon("resurs/refNom.png");
				JButton newNomBt = new JButton(newNom);
				newNomBt.setText("Новый");
				JButton editNomBt = new JButton(editNom);
				editNomBt.setText("Изменить");
				JButton delNomBt = new JButton(delNom);
				delNomBt.setText("Удалить");
				JButton printBt = new JButton(printNom);
				printBt.setText("Печать");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");
				// if (GlobalVars.value != 1)
				// delNomBt.setVisible(false);
				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						passModel.fireTableDataChanged();
					}
				});
				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// Date Now=new Date();
						dat = new JCalendar();
						final TextFrame textFrame = new TextFrame(
								"Новый документ: Паспорт.", true);
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						// JTextField codIdTxt = new JTextField();
						// codIdTxt.setEnabled(false);
						JButton calChooserBt = new JButton("...");
						JButton calPartChooserBt = new JButton("...");
						JButton calNumenclChooserBt = new JButton("...");
						markaMPZTxt = new JTextField();
						numberTxt = new JTextField();
						///numberTxt.setText(NumberOfPassport());
						numberTxt.setEditable(false);
						prochProTxt = new JTextField();
						prochFctTxt = new JTextField();
						nomenklTxt = new JTextField();
						markaTxt = new JTextField();
						dateTxt = new JTextField(new SimpleDateFormat(
								"dd.MM.yyyy").format(dat.getDate()));
						dateTxt.setEditable(false);

						datePartTxt = new JTextField();
						datePartTxt.setEditable(false);
						numberOfProdTxt = new JTextField();
						numberPartTxt = new JTextField();
						numberPartTxt.setEditable(false);
						partProchFactTxt = new JTextField();
						partProchFactTxt.setEditable(false);
						partProchProTxt = new JTextField();
						partProchProTxt.setEditable(false);
						markaPartTxt = new JTextField();
						markaPartTxt.setEditable(false);
						markaPartFactTxt = new JTextField();
						markaPartFactTxt.setEditable(false);
						numenclTxt = new JTextField();
						numenclTxt.setEditable(false);
						userTxt = new JTextField();
						userTxt.setEditable(false);

						userTxt.setText(GlobalVars.getUser().getUserName());

						nomModelSelect = new NomCountChooserTableModel();
						jTablePass = new JTable(nomModelSelect);
						jTablePass
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						// 25.01.2012
						jTablePass.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								if ((arg0.getClickCount() == 2)
										&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

									int rowIndex = jTablePass.getSelectedRow();

									if (rowIndex == -1) {
										JOptionPane.showMessageDialog(null,
												"Объект не выбран.", "Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}

									int numberId = Integer
											.parseInt(jTablePass.getValueAt(
													rowIndex, 0).toString());
									String s = (String) JOptionPane
											.showInputDialog(
													null,
													"Количество",
													"Ввод количества изделий",
													JOptionPane.QUESTION_MESSAGE,
													null, null, null);
									Pattern p = Pattern.compile("\\d+");
									Matcher m = p.matcher(s);
									if (!m.matches()) {
										JOptionPane
												.showMessageDialog(null,
														"Не корректный ввод числа! \n Повторите ввод.");
									} else if ((s != null) && (s.length() > 0)) {
										nomModelSelect.setValueAt(
												Integer.parseInt(s), numberId);
									}
									jTablePass.setModel(nomModelSelect);
									jTablePass.repaint();
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

						panePass = new JScrollPane(jTablePass);

						JToolBar jtb = new JToolBar("nomBar");
						ImageIcon newNom = new ImageIcon("resurs/newNom.png");
						// ImageIcon editNom = new ImageIcon("editNom.png");
						ImageIcon delNom = new ImageIcon("resurs/delNom.png");
						JButton newNomBt = new JButton(newNom);
						newNomBt.setText("Новый");
						// JButton editNomBt = new JButton(editNom);
						JButton delNomBt = new JButton(delNom);
						delNomBt.setText("Удалить");

						if (GlobalVars.value != 1)
							delNomBt.setVisible(false);
						jtb.add(newNomBt);
						// jtb.add(editNomBt);
						jtb.add(delNomBt);

						FormLayout layout = new FormLayout(
								"fill:default:grow, pref, 9dlu, fill:default:grow, 9dlu, fill:default:grow, 14dlu, fill:default:grow, p, fill:default:grow",
								"fill:default:grow, pref, 4dlu,pref,4dlu,pref, 0dlu,fill:default:grow, 4dlu,pref, 4dlu,pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 1dlu, p, fill:default:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel());
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();
						builder.add(new JLabel("Паспорт №"), cc.xy(2, 2));
						builder.add(numberTxt, cc.xy(4, 2));
						builder.add(new JLabel("Выдан"), cc.xy(6, 2));
						builder.add(dateTxt, cc.xy(8, 2));
						builder.add(calChooserBt, cc.xy(9, 2));

						builder.add(new JLabel("Изделия:"), cc.xyw(2, 4, 8));
						builder.add(jtb, cc.xyw(2, 6, 8));
						builder.add(panePass, cc.xywh(2, 8, 8, 1));

						builder.add(new JLabel("партия"), cc.xy(6, 12));
						builder.add(numberPartTxt, cc.xy(8, 12));
						builder.add(calPartChooserBt, cc.xy(9, 12));

						builder.add(new JLabel("Марка бетона МПЗ"),
								cc.xy(2, 10));
						builder.add(markaMPZTxt, cc.xy(4, 10));

						builder.add(new JLabel("Количество изделий"),
								cc.xy(2, 12));
						builder.add(numberOfProdTxt, cc.xy(4, 12));
						builder.add(new JLabel("Дата изготовления"),
								cc.xy(6, 14));
						builder.add(datePartTxt, cc.xy(8, 14));

						builder.add(new JLabel("Отп. прочность, %"),
								cc.xy(2, 14));
						builder.add(partProchProTxt, cc.xy(4, 14));
						builder.add(new JLabel("Факт. прочность, кгс/см2"),
								cc.xy(6, 16));
						builder.add(partProchFactTxt, cc.xy(8, 16));

						builder.add(new JLabel("Проектная марка бетона"),
								cc.xy(2, 16));
						builder.add(markaPartTxt, cc.xy(4, 16));

						builder.add(new JLabel("Фактическая марка бетона"),
								cc.xy(2, 18));
						builder.add(markaPartFactTxt, cc.xy(4, 18));

						builder.add(new JLabel("Ответственный"), cc.xy(6, 18));
						builder.add(userTxt, cc.xy(8, 18));

						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(4, 20, 5));

						textFrame.add(builder.getPanel());

						PassportMenu.this.desktop.add(textFrame);
						newNomBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {

								// if first time, construct dialog

								if (dialogChooserNom == null) {
									NomTableModelAll nomModelSelect = new NomTableModelAll(
											null, "nomName");
									nomModelSelect.setData("isFolder='true'",
											"nomID");
									jTableListNom = new JTable(nomModelSelect);
									dialogChooserNom = new NomChoserAllPass(
											jTableListNom, nomModelSelect);

								}
								if (dialogChooserNom.showDialog(null,
										"Список номенклатуры")) {
									try {
										int i = dialogChooserNom.getDate(1);

										nomE = orm.NomenclatureDAO
												.listNomenclatureByQuery(
														"nomID='" + i + "'",
														null)[0];

										String s = (String) JOptionPane
												.showInputDialog(
														null,
														"Количество",
														"Ввод количества изделий",
														JOptionPane.QUESTION_MESSAGE,
														null, null, null);

										Pattern p = Pattern.compile("\\d+");
										Matcher m = p.matcher(s);
										if (!m.matches()) {
											JOptionPane
													.showMessageDialog(null,
															"Не корректный ввод числа! \n Повторите ввод.");

										} else if ((s != null)
												&& (s.length() > 0)) {
											nomModelSelect.setValueAt(nomE,
													Integer.parseInt(s));

										}

										markaPartFactTxt.setText(nomE
												.getMarkamarca1()
												.getMarcaName());
										jTablePass.setModel(nomModelSelect);
										jTablePass
												.setRowSorter(new TableRowSorter<TableModel>(
														nomModelSelect));
										jTablePass.repaint();

									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								}
							}
						});

						delNomBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int rowIndex = jTablePass.getSelectedRow();
								if (rowIndex == -1) {
									JOptionPane.showMessageDialog(null,
											"Объект не выбран.", "Ошибка",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								final String strNameValue = jTablePass
										.getValueAt(rowIndex, 0).toString();

								if (JOptionPane.showConfirmDialog(null,
										"Объект " + strNameValue
												+ " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
									return;

								try {
									List1CERTData list1CERTData = new List1CERTData();
									try {

										nomE = list1CERTData.listNomenclatureData(
												"nomID='"
														+ Integer
																.parseInt(strNameValue)
														+ "'", null)[0];

										nomModelSelect.delValueAt(nomE);
										jTablePass.setModel(nomModelSelect);
										jTablePass
												.setRowSorter(new TableRowSorter<TableModel>(
														nomModelSelect));
										jTablePass.repaint();

									} finally {
										orm._1CERTPersistentManager
												.instance()
												.disposePersistentManager();
									}

								} catch (Exception e22) {
									e22.printStackTrace();
									JOptionPane.showMessageDialog(null,
											"Объект нельзя уделить. \n"
													+ e22.getMessage(),
											"Ошибка",
											JOptionPane.ERROR_MESSAGE);
								}

							}
						});

						calChooserBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								if (dialog == null) {
									dialog = new CalendarChoser(null);
								}

								if (dialog.showDialog(null, "Выбор даты")) {

									dateTxt.setText(dialog.getDate());

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
						calPartChooserBt
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										// if first time, construct dialog
										NomChoser dialogPart = null;
										if (dialogPart == null) {
											PartsTableModel partModelSelect = new PartsTableModel(
													null, "NumberOfPart");
											JTable jTableListAnti = new JTable(
													partModelSelect);

											final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
													partModelSelect);

											dialogPart = new NomChoser(
													jTableListAnti, sorter);
										}

										if (dialogPart.showDialog(null,
												"Выбор партии")) {
											try {
												int i = dialogPart.getDate(1);

												try {
													List1CERTData list1CERTData = new List1CERTData();

													try {

														part = list1CERTData
																.listParsData(
																		"NumberOfPart='"
																				+ i
																				+ "'",
																		null)[0];
														numberPartTxt
																.setText(Integer
																		.toString(part
																				.getNumberOfPart()));
														datePartTxt
																.setText(new SimpleDateFormat(
																		"dd.MM.yyyy")
																		.format(part
																				.getPartDate()));
														partProchProTxt
																.setText(Integer
																		.toString(part
																				.getPartProchPro()));
														partProchFactTxt
																.setText(Integer
																		.toString(part
																				.getPartProchFact()));

														markaPartTxt.setText(part
																.getMarka());

													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}

												} catch (Exception ee) {
													ee.printStackTrace();
												}

											} catch (PersistentException e1) {

												e1.printStackTrace();
											}

										}

									}
								});
						calNumenclChooserBt
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										// if first time, construct dialog

										if (dialogNumencl == null) {
											NomTableModel nomModelSelect = new NomTableModel(
													null, "nomName");
											JTable jTableListAnti = new JTable(
													nomModelSelect);

											final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
													nomModelSelect);

											dialogNumencl = new NomChoser(
													jTableListAnti, sorter);
										}

										if (dialogNumencl.showDialog(null,
												"Выбор номенклатуры")) {
											try {
												int i = dialogNumencl
														.getDate(1);

												try {
													List1CERTData list1CERTData = new List1CERTData();

													orm.Nomenclature[] num;
													try {

														num = list1CERTData
																.listNomenclatureData(
																		"nomID='"
																				+ i
																				+ "'",
																		null);
														numenclTxt.setText(num[0]
																.getNomName());

													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}

												} catch (Exception ee) {
													ee.printStackTrace();
												}

											} catch (PersistentException e1) {

												e1.printStackTrace();
											}

										}

									}
								});

						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								DateFormat df = DateFormat.getDateInstance();
								Date date;
								
								// int numberOfpart=0;

								if (datePartTxt.getText() == ""
									//	|| numberTxt.getText() == ""
										|| prochProTxt.getText() == ""
										|| numberOfProdTxt.getText() == ""
										|| nomModelSelect.getRowCount() == 0) {
									JOptionPane
											.showMessageDialog(
													null,
													"Не все поля заполнены. \n",
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}
								// Save Certif and Certif_Nomenclature

								try {
									date = df.parse(dateTxt.getText());
									Timestamp timestamp = new Timestamp(date
											.getTime());
									String numberCurent=NumberOfPassport().trim();
									try {

										Create1CERTData create1CERTData = new Create1CERTData();
										List1CERTData list1CERTData = new List1CERTData();

										orm.Users user1 = null;
										orm.Part part1 = null;
										try {
											user1 = list1CERTData
													.listUsersData(
															"userrID='"
																	+ GlobalVars
																			.getUser()
																			.getUserrID()
																	+ "'", null)[0];
											part1 = list1CERTData.listParsData(
													"NumberOfPart='"
															+ Integer
																	.parseInt(numberPartTxt
																			.getText())
															+ "'", null)[0];
												orm.Certif certif = create1CERTData.createCertifData(numberCurent,
															part1, user1,
															timestamp,
															markaMPZTxt
																	.getText());
											// Save Certif_nom
											int len = nomModelSelect
													.getRowCount();
											for (int i = 0; i < len; i++) {
												try {

													Create1CERTData create1CERTData2 = new Create1CERTData();
													try {
														nomE = list1CERTData
																.listNomenclatureData(
																		"nomID='"
																				+ nomModelSelect
																						.getNomIDAt(i)
																				+ "'",
																		null)[0];
														create1CERTData2
																.createCertifNomData(
																		nomE,
																		certif,
																		Integer.parseInt(numberOfProdTxt
																				.getText()),
																		nomModelSelect
																				.getCountAt(i));
														// /???

													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}
												} catch (Exception e3) {
													e3.printStackTrace();
													JOptionPane
															.showMessageDialog(
																	null,
																	"Ошибка записи"
																			+ e3.getMessage(),
																	"Ошибка",
																	JOptionPane.ERROR_MESSAGE);
													return;
												}

											}
									
											passModel.setData(null, null);
										//	passModel.setValueAt(certif);

											jTableNom.setModel(passModel);
											/*
											 * jTableNom .setRowSorter(new
											 * TableRowSorter<TableModel>(
											 * passModel));
											 */
											jTableNom.repaint();

										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();
										}

									} catch (Exception e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(
												null,
												"Ошибка записи"
														+ e1.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
								} catch (Exception e2) {
									e2.printStackTrace();
									JOptionPane
											.showMessageDialog(
													null,
													"Ошибка записи"
															+ e2.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
									JOptionPane
											.showMessageDialog(
													null,
													"Ошибка записи"
															+ e1.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
								}

							}
						});
					}

					private String NumberOfPassport() {

						orm.Certif[] oRMCert = null;
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								oRMCert = list1CERTData.listCertifData(null,
										"CertifID");
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (PersistentException e) {
							e.printStackTrace();
						}

						Calendar calendar = Calendar.getInstance(
								java.util.TimeZone.getDefault(),
								java.util.Locale.getDefault());
						calendar.setTime(new Date());
						int dtn = calendar.get(Calendar.YEAR);
						String dtnStr = Integer.toString(dtn).substring(2, 4);
						if (oRMCert.length == 0) {
							// Calendar calendar = new
							return dtnStr + "/1";
						} else {
							String NumberStr = oRMCert[oRMCert.length - 1]
									.getNumberOfCert();
							int strLength = NumberStr.length();

							int Number = Integer.parseInt(NumberStr.substring(
									3, strLength));

							return dtnStr + "/" + Integer.toString(Number + 1);
						}
					}
				});
				editNomBt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						String strNumberOfPart = jTableNom.getValueAt(rowIndex,
								0).toString();
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								certifSelect = list1CERTData.listCertifData(
										"NumberOfCert='" + strNumberOfPart
												+ "'", null)[0];
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								cerNoSelect = list1CERTData.listCertif_NomData(
										"CertifcertifID='"
												+ certifSelect.getCertifID()
												+ "'", null);

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						dat = new JCalendar();
						final TextFrame textFrame = new TextFrame(
								"Редактирование документа: Паспорт.", true);
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						JButton calChooserBt = new JButton("...");
						JButton calPartChooserBt = new JButton("...");
						JButton calNumenclChooserBt = new JButton("...");
						numberTxt = new JTextField();
						numberTxt.setText(strNumberOfPart);
						numberTxt.setEditable(false);
						prochProTxt = new JTextField();
						prochFctTxt = new JTextField();
						nomenklTxt = new JTextField();
						markaTxt = new JTextField();
						markaMPZTxt = new JTextField();
						dateTxt = new JTextField(new SimpleDateFormat(
								"dd.MM.yyyy").format(certifSelect
								.getCertifTimes()));
						dateTxt.setEditable(false);

						datePartTxt = new JTextField();
						datePartTxt.setEditable(false);
						numberOfProdTxt = new JTextField();
						numberPartTxt = new JTextField();
						numberPartTxt.setEditable(false);
						partProchFactTxt = new JTextField();
						partProchFactTxt.setEditable(false);
						partProchProTxt = new JTextField();
						partProchProTxt.setEditable(false);
						markaPartTxt = new JTextField();
						markaPartTxt.setEditable(false);
						markaPartFactTxt = new JTextField();
						markaPartFactTxt.setEditable(false);
						numenclTxt = new JTextField();
						numenclTxt.setEditable(false);
						userTxt = new JTextField();
						userTxt.setEditable(false);
						userTxt.setText(GlobalVars.getUser().getUserName());

						if (certifSelect.getMarcaMPZ() != null)
							markaMPZTxt.setText(certifSelect.getMarcaMPZ());
						else
							markaMPZTxt.setText(" ");

						numberPartTxt.setText(Integer.toString(certifSelect
								.getPartpart().getNumberOfPart()));
						datePartTxt.setText(new SimpleDateFormat("dd.MM.yyyy")
								.format(certifSelect.getPartpart()
										.getPartDate()));
						partProchProTxt.setText(Integer.toString(certifSelect
								.getPartpart().getPartProchPro()));
						partProchFactTxt.setText(Integer.toString(certifSelect
								.getPartpart().getPartProchFact()));
						markaPartFactTxt.setText(cerNoSelect[0]
								.getNomenclaturenom().getMarkamarca1()
								.getMarcaName());
						markaPartTxt.setText(certifSelect.getPartpart()
								.getMarka());
						numberOfProdTxt.setText(Integer.toString(cerNoSelect[0]
								.getNumberOfProd()));

						nomModelSelect = new NomCountChooserTableModel();
						for (Certif_Nomenclature cerNomSelect : cerNoSelect) {
							NomenclatureCount certCount = new NomenclatureCount(
									cerNomSelect.getNomenclaturenom(),
									cerNomSelect.getCountN());
							nomModelSelect.setValueAt(certCount);

						}

						jTablePass = new JTable(nomModelSelect);
						jTablePass
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

						// 25.01.2012
						jTablePass.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								if ((arg0.getClickCount() == 2)
										&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

									int rowIndex = jTablePass.getSelectedRow();

									if (rowIndex == -1) {
										JOptionPane.showMessageDialog(null,
												"Объект не выбран.", "Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}

									int numberId = Integer
											.parseInt(jTablePass.getValueAt(
													rowIndex, 0).toString());
									String s = (String) JOptionPane
											.showInputDialog(
													null,
													"Количество",
													"Ввод количества изделий",
													JOptionPane.QUESTION_MESSAGE,
													null, null, null);
									Pattern p = Pattern.compile("\\d+");
									Matcher m = p.matcher(s);
									if (!m.matches()) {
										JOptionPane
												.showMessageDialog(null,
														"Не корректный ввод числа! \n Повторите ввод.");
									} else if ((s != null) && (s.length() > 0)) {
										nomModelSelect.setValueAt(
												Integer.parseInt(s), numberId);
									}
									jTablePass.setModel(nomModelSelect);
									jTablePass.repaint();
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

						panePass = new JScrollPane(jTablePass);

						JToolBar jtb = new JToolBar("nomBar");
						ImageIcon newNom = new ImageIcon("resurs/newNom.png");
						// ImageIcon editNom = new ImageIcon("editNom.png");
						ImageIcon delNom = new ImageIcon("resurs/delNom.png");
						JButton newNomBt = new JButton(newNom);
						newNomBt.setText("Новый");
						// JButton editNomBt = new JButton(editNom);
						JButton delNomBt = new JButton(delNom);
						delNomBt.setText("Удалить");
						if (GlobalVars.value != 1)
							delNomBt.setVisible(false);
						jtb.add(newNomBt);
						// jtb.add(editNomBt);
						jtb.add(delNomBt);

						FormLayout layout = new FormLayout(
								"fill:default:grow, pref, 9dlu, fill:default:grow, 9dlu, fill:default:grow, 14dlu, fill:default:grow, p, fill:default:grow",
								"fill:default:grow, pref, 4dlu,pref,4dlu,pref, 0dlu,fill:default:grow, 4dlu,pref,4dlu,pref, 4dlu,pref, 4dlu, pref, 4dlu, pref, 1dlu, p, fill:default:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();
						builder.add(new JLabel("Паспорт №"), cc.xy(2, 2));
						builder.add(numberTxt, cc.xy(4, 2));
						builder.add(new JLabel("Выдан"), cc.xy(6, 2));
						builder.add(dateTxt, cc.xy(8, 2));
						builder.add(calChooserBt, cc.xy(9, 2));

						builder.add(new JLabel("Изделия:"), cc.xyw(2, 4, 8));
						builder.add(jtb, cc.xyw(2, 6, 8));
						builder.add(panePass, cc.xywh(2, 8, 8, 1));

						builder.add(new JLabel("партия"), cc.xy(6, 12));
						builder.add(numberPartTxt, cc.xy(8, 12));
						builder.add(calPartChooserBt, cc.xy(9, 12));

						builder.add(new JLabel("Марка бетона МПЗ"),
								cc.xy(2, 10));
						builder.add(markaMPZTxt, cc.xy(4, 10));

						builder.add(new JLabel("Количество изделий"),
								cc.xy(2, 12));
						builder.add(numberOfProdTxt, cc.xy(4, 12));
						builder.add(new JLabel("Дата изготовления"),
								cc.xy(6, 14));
						builder.add(datePartTxt, cc.xy(8, 14));

						builder.add(new JLabel("Отп. прочность, %"),
								cc.xy(2, 14));
						builder.add(partProchProTxt, cc.xy(4, 14));
						builder.add(new JLabel("Факт. прочность, кгс/см2"),
								cc.xy(6, 16));
						builder.add(partProchFactTxt, cc.xy(8, 16));

						builder.add(new JLabel("Проектная марка бетона"),
								cc.xy(2, 16));
						builder.add(markaPartTxt, cc.xy(4, 16));

						builder.add(new JLabel("Фактическая марка бетона"),
								cc.xy(2, 18));
						builder.add(markaPartFactTxt, cc.xy(4, 18));

						builder.add(new JLabel("Ответственный"), cc.xy(6, 18));
						builder.add(userTxt, cc.xy(8, 18));

						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(4, 20, 5));
						textFrame.add(builder.getPanel());

						PassportMenu.this.desktop.add(textFrame);
						newNomBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {

								// if first time, construct dialog

								if (dialogChooserNom == null) {
									NomTableModelAll nomModelSelect = new NomTableModelAll(
											null, "nomID");
									nomModelSelect.setData("isFolder='true'",
											"nomID");
									jTableListNom = new JTable(nomModelSelect);

									dialogChooserNom = new NomChoserAllPass(
											jTableListNom, nomModelSelect);

								}
								if (dialogChooserNom.showDialog(null,
										"Список номенклатуры")) {
									try {
										int i = dialogChooserNom.getDate(1);
										nomE = orm.NomenclatureDAO
												.listNomenclatureByQuery(
														"nomID='" + i + "'",
														null)[0];

										String s = (String) JOptionPane
												.showInputDialog(
														null,
														"Количество",
														"Ввод количества изделий",
														JOptionPane.QUESTION_MESSAGE,
														null, null, null);

										Pattern p = Pattern.compile("\\d+");
										Matcher m = p.matcher(s);
										if (!m.matches()) {
											JOptionPane
													.showMessageDialog(null,
															"Не корректный ввод числа! \n Повторите ввод.");

										} else if ((s != null)
												&& (s.length() > 0)) {
											nomModelSelect.setValueAt(nomE,
													Integer.parseInt(s));

										}

										markaPartFactTxt.setText(nomE
												.getMarkamarca1()
												.getMarcaName());

										jTablePass.setModel(nomModelSelect);
										jTablePass
												.setRowSorter(new TableRowSorter<TableModel>(
														nomModelSelect));
										jTablePass.repaint();

									} catch (PersistentException e1) {

										e1.printStackTrace();
									}

								}
							}
						});

						delNomBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int rowIndex = jTablePass.getSelectedRow();
								if (rowIndex == -1) {
									JOptionPane.showMessageDialog(null,
											"Объект не выбран.", "Ошибка",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								final String strNameValue = jTablePass
										.getValueAt(rowIndex, 0).toString();

								if (JOptionPane.showConfirmDialog(null,
										"Объект " + strNameValue
												+ " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
									return;

								try {
									List1CERTData list1CERTData = new List1CERTData();
									try {

										nomE = list1CERTData.listNomenclatureData(
												"nomID='"
														+ Integer
																.parseInt(strNameValue)
														+ "'", null)[0];

										nomModelSelect.delValueAt(nomE);
										jTablePass.setModel(nomModelSelect);
										jTablePass
												.setRowSorter(new TableRowSorter<TableModel>(
														nomModelSelect));
										jTablePass.repaint();

									} finally {
										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();
									}

								} catch (Exception e22) {
									e22.printStackTrace();
									JOptionPane
											.showMessageDialog(null,
													"Объект нельзя уделить. \n"
															+ e22.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
								}

							}
						});

						calChooserBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								if (dialog == null) {
									dialog = new CalendarChoser(null);
								}

								if (dialog.showDialog(null, "Выбор даты")) {

									dateTxt.setText(dialog.getDate());

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
						calPartChooserBt
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										// if first time, construct dialog
										NomChoser dialogPart = null;
										if (dialogPart == null) {
											PartsTableModel partModelSelect = new PartsTableModel(
													null, "NumberOfPart");
											JTable jTableListAnti = new JTable(
													partModelSelect);

											final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
													partModelSelect);

											dialogPart = new NomChoser(
													jTableListAnti, sorter);
										}

										if (dialogPart.showDialog(null,
												"Выбор партии")) {
											try {
												int i = dialogPart.getDate(1);

												try {
													List1CERTData list1CERTData = new List1CERTData();

													try {

														part = list1CERTData
																.listParsData(
																		"NumberOfPart='"
																				+ i
																				+ "'",
																		null)[0];
														numberPartTxt
																.setText(Integer
																		.toString(part
																				.getNumberOfPart()));
														datePartTxt
																.setText(new SimpleDateFormat(
																		"dd.MM.yyyy")
																		.format(part
																				.getPartDate()));
														partProchProTxt
																.setText(Integer
																		.toString(part
																				.getPartProchPro()));
														partProchFactTxt
																.setText(Integer
																		.toString(part
																				.getPartProchFact()));
														markaPartTxt.setText(part
																.getMarka());

													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}

												} catch (Exception ee) {
													ee.printStackTrace();
												}

											} catch (PersistentException e1) {

												e1.printStackTrace();
											}

										}

									}
								});
						calNumenclChooserBt
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										// if first time, construct dialog

										if (dialogNumencl == null) {
											NomTableModel nomModelSelect = new NomTableModel(
													null, "nomName");
											JTable jTableListAnti = new JTable(
													nomModelSelect);

											final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
													nomModelSelect);

											dialogNumencl = new NomChoser(
													jTableListAnti, sorter);
										}

										if (dialogNumencl.showDialog(null,
												"Выбор номенклатуры")) {
											try {
												int i = dialogNumencl
														.getDate(1);

												try {
													List1CERTData list1CERTData = new List1CERTData();

													orm.Nomenclature[] num;
													try {

														num = list1CERTData
																.listNomenclatureData(
																		"nomID='"
																				+ i
																				+ "'",
																		null);
														numenclTxt.setText(num[0]
																.getNomName());

													} finally {
														orm._1CERTPersistentManager
																.instance()
																.disposePersistentManager();
													}

												} catch (Exception ee) {
													ee.printStackTrace();
												}

											} catch (PersistentException e1) {

												e1.printStackTrace();
											}

										}

									}
								});

						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								DateFormat df = DateFormat.getDateInstance();
								Date date;
								// int numberOfpart=0;

								if (datePartTxt.getText() == ""
										//|| numberTxt.getText() == ""
										|| prochProTxt.getText() == ""
										|| numberOfProdTxt.getText() == ""
										|| nomModelSelect.getRowCount() == 0) {
									JOptionPane
											.showMessageDialog(
													null,
													"Не все поля заполнены. \n",
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}
								// Update Certif and Certif_Nomenclature

								try {
									date = df.parse(dateTxt.getText());
									Timestamp timestamp = new Timestamp(date
											.getTime());
									try {
										RetrieveAndUpdate1CERTData retrieveAndUpdate1CERTData = new RetrieveAndUpdate1CERTData();
										List1CERTData list1CERTData = new List1CERTData();

										orm.Users user1 = null;
										orm.Part part1 = null;
										try {
											user1 = list1CERTData
													.listUsersData(
															"userrID='"
																	+ GlobalVars
																			.getUser()
																			.getUserrID()
																	+ "'", null)[0];
											part1 = list1CERTData.listParsData(
													"NumberOfPart='"
															+ Integer
																	.parseInt(numberPartTxt
																			.getText())
															+ "'", null)[0];

											orm.Certif certif = retrieveAndUpdate1CERTData
													.retrieveAndUpdateCertifData(
															numberTxt.getText()
																	.trim(),
															part1, user1,
															timestamp,
															markaMPZTxt
																	.getText());
											// Update Certif_nom

											// cerNoSelect -old,
											// nomModelSelect-new
											int len = nomModelSelect
													.getRowCount();
											Nomenclature[] nomMasNew = new Nomenclature[len];
											try {
												List1CERTData list1CERTData2 = new List1CERTData();
												try {
													for (int i = 0; i < len; i++) {

														nomMasNew[i] = list1CERTData2
																.listNomenclatureData(
																		"nomID='"
																				+ nomModelSelect
																						.getNomIDAt(i)
																				+ "'",
																		null)[0];
													}
												} finally {
													orm._1CERTPersistentManager
															.instance()
															.disposePersistentManager();
												}
											} catch (Exception e2) {
												e2.printStackTrace();
											}

											// orm.Certif_Nomenclature
											// oRMCertifNom;

											PersistentTransaction t = orm._1CERTPersistentManager
													.instance().getSession()
													.beginTransaction();
											try {
												for (int i = 0; i < cerNoSelect.length; i++) {
													orm.Certif_NomenclatureDAO
															.delete(cerNoSelect[i]);
												}
												t.commit();

											} catch (Exception e2) {
												t.rollback();
											}

											Certif oRMCerfit;
											for (int i = 0; i < len; i++) {
												try {
													oRMCerfit = orm.CertifDAO
															.listCertifByQuery(
																	"certifID='"
																			+ certif.getCertifID()
																			+ "'",
																	null)[0];
													nomE = orm.NomenclatureDAO
															.listNomenclatureByQuery(
																	"nomID='"
																			+ nomMasNew[i]
																					.getNomID()
																			+ "'",
																	null)[0];
													try {
														Create1CERTData create1CERTData = new Create1CERTData();
														try {
															create1CERTData
																	.createCertifNomData(
																			nomE,
																			oRMCerfit,
																			Integer.parseInt(numberOfProdTxt
																					.getText()),
																			nomModelSelect
																					.getCountAt(i));
														} finally {
															orm._1CERTPersistentManager
																	.instance()
																	.disposePersistentManager();
														}
													} catch (Exception e2) {
														e2.printStackTrace();
													}

												} catch (Exception e12) {

													e12.printStackTrace();
													JOptionPane.showMessageDialog(
															null,
															e12.getMessage(),
															"Ошибка",
															JOptionPane.ERROR_MESSAGE);
													return;
												}
											}
											passModel.setData(null, null);
											// passModel.setValueAt(certif);
											jTableNom.setModel(passModel);
											/*
											 * jTableNom .setRowSorter(new
											 * TableRowSorter<TableModel>(
											 * passModel));
											 */
											jTableNom.repaint();

										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();
										}

									} catch (Exception e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(
												null,
												"Ошибка записи"
														+ e1.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
								} catch (Exception e2) {
									e2.printStackTrace();
									JOptionPane
											.showMessageDialog(
													null,
													"Ошибка записи"
															+ e2.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}

								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
									JOptionPane
											.showMessageDialog(
													null,
													"Ошибка записи"
															+ e1.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
								}

							}
						});
					}
				});
				delNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						final String strNameValue = jTableNom.getValueAt(
								rowIndex, 0).toString();

						if (JOptionPane
								.showConfirmDialog(null, "Объект "
										+ strNameValue + " будет удален!",
										"Предупреждение.",
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
							return;

						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								certifSelect = list1CERTData.listCertifData(
										"NumberOfCert='" + strNameValue + "'",
										null)[0];

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}

						} catch (Exception e22) {
							e22.printStackTrace();
							JOptionPane.showMessageDialog(
									null,
									"Объект нельзя уделить. \n"
											+ e22.getMessage(), "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {

								Certif_Nomenclature[] certN = list1CERTData
										.listCertif_NomData("CertifcertifID='"
												+ certifSelect + "'", null);
								try {
									Delete1CERTData delete1CERTData = new Delete1CERTData();
									try {
										for (Certif_Nomenclature cerNS : certN) {

											delete1CERTData
													.deleteCertNomData(cerNS
															.getCertID());
										}
										delete1CERTData
												.deleteCertData(certifSelect
														.getCertifID());
									} finally {

										orm._1CERTPersistentManager.instance()
												.disposePersistentManager();
									}
								} catch (Exception e1) {
									e1.printStackTrace();
									JOptionPane
											.showMessageDialog(null,
													"Объект нельзя удалить. \n"
															+ e1.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}
								passModel.setData(null, null);
								// passModel.delValueAt(certifSelect);
								jTableNom.setModel(passModel);
								/*
								 * jTableNom .setRowSorter(new
								 * TableRowSorter<TableModel>( passModel));
								 */
								jTableNom.repaint();

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}

						} catch (Exception e22) {
							e22.printStackTrace();
							JOptionPane.showMessageDialog(
									null,
									"Объект нельзя удалить. \n"
											+ e22.getMessage(), "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				});

				// attributes = new HashPrintRequestAttributeSet();

				printBt.addActionListener(new ActionListener() {

					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent e) {

						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						String strNumberOfPart = jTableNom.getValueAt(rowIndex,
								0).toString();
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								certifSelect = list1CERTData.listCertifData(
										"NumberOfCert='" + strNumberOfPart
												+ "'", null)[0];
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							return;
						}

						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								cerNoSelect = list1CERTData.listCertif_NomData(
										"CertifcertifID='"
												+ certifSelect.getCertifID()
												+ "'", null);

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							return;
						}

						StringBuilder nameOfnom = new StringBuilder();
						StringBuilder count = new StringBuilder();
						Boolean flagPrint = false;
						for (Certif_Nomenclature cerNomSelect : cerNoSelect) {
							if (cerNomSelect.getCountN() != 1)
								flagPrint = true;
						}
						for (Certif_Nomenclature cerNomSelect : cerNoSelect) {
							if (flagPrint)
								count.append(" - " + cerNomSelect.getCountN()
										+ " шт");
							if (nameOfnom.length() == 0) {
								nameOfnom = nameOfnom.append(cerNomSelect
										.getNomenclaturenom().getNomName()
										+ count);
								count.delete(0, count.length());
							} else {
								nameOfnom = nameOfnom.append(", "
										+ cerNomSelect.getNomenclaturenom()
												.getNomName() + count);
								count.delete(0, count.length());
							}
						}

						// Preparing parameters
						@SuppressWarnings("rawtypes")
						Map<String, Object> parameters = new HashMap();
						parameters.put("numberOfPas",
								certifSelect.getNumberOfCert());
						parameters.put("dateDoc", new SimpleDateFormat(
								"dd.MM.yyyy").format(certifSelect
								.getCertifTimes()));
						// System.out.print("certifSelect.getPartpart().getMarka())="+certifSelect.getPartpart().getMarka());
						parameters.put("nameOfnom", nameOfnom.toString());
						parameters.put("partN", Integer.toString(certifSelect
								.getPartpart().getNumberOfPart()));
						parameters.put("dateIzg", new SimpleDateFormat(
								"dd.MM.yyyy").format(certifSelect.getPartpart()
								.getPartDate()));
						parameters.put("numberOfIzd", Integer
								.toString(cerNoSelect[0].getNumberOfProd()));
						// По прозьбе ОТК
						// parameters.put("marka", certifSelect.getPartpart()
						// .getMarka());
						parameters.put("marka", cerNoSelect[0]
								.getNomenclaturenom().getMarkamarca1()
								.getMarcaName());
						parameters.put("prochOtp", Integer
								.toString(certifSelect.getPartpart()
										.getPartProchPro()));
						parameters.put("trebOtpPr", " ");
						parameters.put("trebOtpPrKg", " ");
						parameters.put("factPr", Integer.toString(certifSelect
								.getPartpart().getPartProchFact()));
						parameters.put("peredPr", " ");
						parameters.put(
								"markaMPZ",
								certifSelect.getMarcaMPZ() != null ? certifSelect
										.getMarcaMPZ() : "");
						parameters.put("markaSt", cerNoSelect[0]
								.getNomenclaturenom().getStalstal()
								.getStalName());
						parameters.put("vidAnt", cerNoSelect[0]
								.getNomenclaturenom().getAntiKorantiKor()
								.getAntiKorName());
						parameters.put("katPov", cerNoSelect[0]
								.getNomenclaturenom().getKatPovkatPov()
								.getKatPovName());
						parameters.put("TU", cerNoSelect[0]
								.getNomenclaturenom().getTUtu().getTuName());
						parameters.put("numberOfCh", cerNoSelect[0]
								.getNomenclaturenom().getSeriyseriy()
								.getSeriyName());

						try {
							JasperReport jasperPeport = JasperCompileManager
									.compileReport("reports/PassportReport.jrxml");
							JasperPrint jasperPrint = JasperFillManager
									.fillReport(jasperPeport, parameters,
											new JREmptyDataSource());

							JasperViewer.viewReport(jasperPrint, false);
						} catch (JRException we) {
							JOptionPane.showMessageDialog(null,
									we.getMessage(), "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							// we.printStackTrace()
						}

					}
				});

				jtb.add(newNomBt);
				jtb.add(editNomBt);
				jtb.add(delNomBt);
				jtb.add(printBt);
				jtb.add(refNomBt);

				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				textFrame.add(panel2, BorderLayout.NORTH);

				PassportMenu.this.desktop.add(textFrame);
				/*
				 * } catch (PersistentException e) { catch block
				 * e.printStackTrace(); }
				 */

			}
		});
		partDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				final TextFrame textFrame = new TextFrame("Партии",
						new ImageIcon("resurs/part.png"));

				partsModel = new PartsTableModel(null, null);
				jTableNomPart = new JTable(partsModel);

				jTableNomPart
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				// sorter = new TableRowSorter<TableModel>(partsModel);
				// jTableNomPart.setRowSorter(sorter);

				// TableRowSorter<TableModel> sorter = new
				// TableRowSorter(myDataModel);
				// tableBuddies.setRowSorter(sorter);

				sorter = new TableRowSorter<TableModel>(partsModel) {
					@Override
					public Comparator<?> getComparator(int column) {
						// для нулевой строки
						switch (column) {
						case 0:
						case 2:
						case 3:
							return new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									return Integer.parseInt(s1)
											- Integer.parseInt(s2);
								}
							};
						case 1:
							return new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									DateFormat df1 = DateFormat
											.getDateInstance();
									DateFormat df2 = DateFormat
											.getDateInstance();
									Date date1 = new Date();
									Date date2 = new Date();
									try {
										date1 = df1.parse(s1);
										date2 = df1.parse(s2);

									} catch (ParseException e) {

									}
									if (date1.before(date2))
										return -1;
									else
										return 1;
								}
							};
							// для всех остальных
						default:
							return super.getComparator(column);
						}
					}
				};
				jTableNomPart.setRowSorter(sorter);

				// Клик по сторочке
				jTableNomPart.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						if ((arg0.getClickCount() == 2)
								&& (arg0.getModifiers() == InputEvent.BUTTON1_MASK)) {

							int rowIndex = jTableNomPart.getSelectedRow();
							if (rowIndex == -1) {
								JOptionPane.showMessageDialog(null,
										"Объект не выбран.", "Ошибка",
										JOptionPane.ERROR_MESSAGE);
								return;
							}

							orm.Part[] oRMPart;
							// intIDValue = -1;

							try {
								List1CERTData list1CERTData = new List1CERTData();
								try {
									oRMPart = list1CERTData.listParsData(
											"numberOfPart='"
													+ jTableNomPart.getValueAt(
															rowIndex, 0) + "'",
											null);
									intIDValue = oRMPart[0].getPartID();
									intNumbeValue = oRMPart[0]
											.getNumberOfPart();
									dateValue = new Timestamp(oRMPart[0]
											.getPartDate().getTime());
									prochProValue = oRMPart[0]
											.getPartProchPro();
									prochFctValue = oRMPart[0]
											.getPartProchFact();
									nomenclaturaValue = oRMPart[0]
											.getNomenklature();
									markaValue = oRMPart[0].getMarka();
								} finally {
									orm._1CERTPersistentManager.instance()
											.disposePersistentManager();
								}
							} catch (PersistentException e2) {

								e2.printStackTrace();
								return;
							}

							dat = new JCalendar();
							final TextFrame textFrame = new TextFrame(
									"Редактирование: Парития.", true);
							JButton canBt = new JButton("Отмена");
							JButton saveBt = new JButton("Сохранить");

							JButton calChooserBt = new JButton("...");
							numberTxt = new JTextField(Integer
									.toString(intNumbeValue));
							numberTxt.setEditable(false);
							prochProTxt = new JTextField(Integer
									.toString(prochProValue));
							prochFctTxt = new JTextField(Integer
									.toString(prochFctValue));
							nomenklTxt = new JTextField(nomenclaturaValue);
							markaTxt = new JTextField(markaValue);
							dateTxt = new JTextField(new SimpleDateFormat(
									"dd.MM.yyyy").format(dateValue));
							dateTxt.setEditable(false);
							FormLayout layout = new FormLayout(
									"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, 14dlu, p, fill:default:grow",
									"fill:p:grow, pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu, pref, 1dlu, p, fill:p:grow");

							DefaultFormBuilder builder = new DefaultFormBuilder(
									layout);
							// , new FormDebugPanel()
							builder.setDefaultDialogBorder();
							CellConstraints cc = new CellConstraints();
							builder.add(new JLabel("Номер партии"), cc.xy(2, 2));
							builder.add(numberTxt, cc.xyw(4, 2, 3));
							builder.add(
									new JLabel("Отпускная прочность бетона"),
									cc.xy(2, 4));
							builder.add(prochProTxt, cc.xyw(4, 4, 3));
							builder.add(new JLabel(
									"Фактическая прочность бетона"), cc
									.xy(2, 6));
							builder.add(prochFctTxt, cc.xyw(4, 6, 3));
							builder.add(new JLabel("Номенклатура"), cc.xy(2, 8));
							builder.add(nomenklTxt, cc.xyw(4, 8, 3));
							builder.add(new JLabel("Марка"), cc.xy(2, 10));
							builder.add(markaTxt, cc.xyw(4, 10, 3));
							builder.add(new JLabel("Дата"), cc.xy(2, 12));
							builder.add(dateTxt, cc.xyw(4, 12, 1));
							builder.add(calChooserBt, cc.xyw(6, 12, 1));
							builder.add(ButtonBarFactory
									.buildAddRemoveRightBar(saveBt, canBt), cc
									.xyw(2, 14, 5));

							textFrame.add(builder.getPanel());

							PassportMenu.this.desktop.add(textFrame);

							calChooserBt
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent event) {
											if (dialog == null) {
												dialog = new CalendarChoser(
														null);
											}

											if (dialog.showDialog(null,
													"Выбор даты")) {

												dateTxt.setText(dialog
														.getDate());

											}

											// System.out.println(new
											// SimpleDateFormat("dd.MM.yyyy").format(dat.getDate()));
											// System.out.println(new
											// SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(dat.getDate()));

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

							saveBt.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									DateFormat df = DateFormat
											.getDateInstance();
									Date date;

									int numberOfpart = 0;
									try {
										numberOfpart = Integer
												.parseInt(numberTxt.getText());
									} catch (NumberFormatException ee) {

										JOptionPane.showMessageDialog(null,
												"Не все поля заполнены. \n"
														+ ee.getMessage(),
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
									/*
									 * try { List1CERTData list1CERTData = new
									 * List1CERTData(); orm.Part[] oRMPart; try
									 * {
									 * 
									 * oRMPart = list1CERTData.listParsData(
									 * "partProchFact='" + numberTxt.getText() +
									 * "'", null); //!!!!(Не работает) if
									 * (oRMPart.length > 1) { JOptionPane
									 * .showMessageDialog( null, "Номер партии "
									 * + numberOfpart + " уже существует.",
									 * "Ошибка", JOptionPane.ERROR_MESSAGE);
									 * return; } } finally {
									 * orm._1CERTPersistentManager.instance()
									 * .disposePersistentManager(); } } catch
									 * (PersistentException e3) {
									 * e3.printStackTrace(); }
									 */
									try {
										date = df.parse(dateTxt.getText());
										Timestamp timestamp = new Timestamp(
												date.getTime());
										try {
											RetrieveAndUpdate1CERTData retrieveAndUpdate1CERTData = new RetrieveAndUpdate1CERTData();
											try {
												retrieveAndUpdate1CERTData
														.retrieveAndUpdatePartData(
																intIDValue,
																timestamp,
																Integer.parseInt(prochFctTxt
																		.getText()
																		.trim()),
																Integer.parseInt(prochProTxt
																		.getText()
																		.trim()),
																numberOfpart,
																nomenklTxt
																		.getText()
																		.trim(),
																markaTxt.getText()
																		.trim());
												partsModel.setData(null, null);
												jTableNomPart
														.setModel(partsModel);
												jTableNomPart.repaint();
											} finally {
												orm._1CERTPersistentManager
														.instance()
														.disposePersistentManager();
											}
										} catch (Exception ee) {
											ee.printStackTrace();
										}

									} catch (ParseException e2) {
										e2.printStackTrace();
									}
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

				JScrollPane pane = new JScrollPane(jTableNomPart);
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
				delNomBt.setText("Удалить");
				JButton refNomBt = new JButton(refNom);
				refNomBt.setText("Обновить");
				if (GlobalVars.value != 1)
					delNomBt.setVisible(false);

				newNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// Date Now=new Date();
						dat = new JCalendar();
						final TextFrame textFrame = new TextFrame(
								"Новый документ: Парития.", true);
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");
						// JTextField codIdTxt = new JTextField();
						// codIdTxt.setEnabled(false);
						JButton calChooserBt = new JButton("...");
						numberTxt = new JTextField();
						numberTxt.setText(NumberOfPart());
						numberTxt.setEditable(false);
						prochProTxt = new JTextField();
						prochFctTxt = new JTextField();
						nomenklTxt = new JTextField();
						markaTxt = new JTextField();
						dateTxt = new JTextField(new SimpleDateFormat(
								"dd.MM.yyyy").format(dat.getDate()));
						dateTxt.setEditable(false);
						user = GlobalVars.getUser();
						userTxt = new JTextField();
						userTxt.setText(user.getUserName());
						userTxt.setEditable(false);
						// System.out.println(new
						// SimpleDateFormat("dd.MM.yyyy").format(dat.getDate()));
						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, 14dlu, p, fill:default:grow",
								"fill:p:grow, pref, 4dlu, pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu, pref, 1dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();
						builder.add(new JLabel("Номер партии"), cc.xy(2, 2));
						builder.add(numberTxt, cc.xyw(4, 2, 3));
						builder.add(
								new JLabel("Отпускная прочность бетона, %"),
								cc.xy(2, 4));
						builder.add(prochProTxt, cc.xyw(4, 4, 3));
						builder.add(new JLabel("Фактическая прочность бетона"),
								cc.xy(2, 6));
						builder.add(prochFctTxt, cc.xyw(4, 6, 3));
						builder.add(new JLabel("Номенклатура"), cc.xy(2, 8));
						builder.add(nomenklTxt, cc.xyw(4, 8, 3));
						builder.add(new JLabel("Марка"), cc.xy(2, 10));
						builder.add(markaTxt, cc.xyw(4, 10, 3));
						builder.add(new JLabel("Дата"), cc.xy(2, 12));
						builder.add(dateTxt, cc.xyw(4, 12, 1));
						builder.add(calChooserBt, cc.xyw(6, 12, 1));
						builder.add(new JLabel("Ответственный"), cc.xy(2, 14));
						builder.add(userTxt, cc.xyw(4, 14, 3));

						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(2, 16, 5));

						textFrame.add(builder.getPanel());

						PassportMenu.this.desktop.add(textFrame);

						calChooserBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								if (dialog == null) {

									dialog = new CalendarChoser(null);
								}

								if (dialog.showDialog(null, "Выбор даты")) {

									dateTxt.setText(dialog.getDate());

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

						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								DateFormat df = DateFormat.getDateInstance();
								Date date;

								int numberOfpart = 0;
								try {
									numberOfpart = Integer.parseInt(numberTxt
											.getText());
								} catch (NumberFormatException ee) {
									// ee.printStackTrace();
									JOptionPane
											.showMessageDialog(null,
													"Не все поля заполнены. \n"
															+ ee.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}
								try {
									orm.Part[] oRMPart = orm.PartDAO
											.listPartByQuery(
													// "partProchFact='"
													"NumberOfPart='"
															+ numberTxt
																	.getText()
															+ "'", null);
									if (oRMPart.length != 0) {
										JOptionPane.showMessageDialog(null,
												"Номер партии " + numberOfpart
														+ " уже существует.",
												"Ошибка",
												JOptionPane.ERROR_MESSAGE);
										return;
									}
								} catch (PersistentException e3) {
									e3.printStackTrace();
								}
								try {
									date = df.parse(dateTxt.getText());
									Timestamp timestamp = new Timestamp(date
											.getTime());

									try {
										List1CERTData list1CERTData = new List1CERTData();
										orm.Users user1;
										try {
											user1 = list1CERTData
													.listUsersData(
															"userrID='"
																	+ GlobalVars
																			.getUser()
																			.getUserrID()
																	+ "'", null)[0];

											try {
												Create1CERTData create1CERTData = new Create1CERTData();
												try {
													create1CERTData
															.createPartData(
																	timestamp,
																	Integer.parseInt(prochProTxt
																			.getText()
																			.trim()),
																	Integer.parseInt(prochFctTxt
																			.getText()
																			.trim()),
																	nomenklTxt
																			.getText()
																			.trim(),
																	markaTxt.getText()
																			.trim(),
																	Integer.parseInt(numberTxt
																			.getText()
																			.trim()),
																	user1);
													partsModel.setData(null,
															null);
													jTableNomPart
															.setModel(partsModel);
													// jTableNomPart
													// .setRowSorter(new
													// TableRowSorter<TableModel>(
													// partsModel));
													jTableNomPart.repaint();

												} finally {
													orm._1CERTPersistentManager
															.instance()
															.disposePersistentManager();

												}

											} catch (Exception ee) {
												ee.printStackTrace();
												JOptionPane.showMessageDialog(
														null,
														"Ошибка записи"
																+ ee.getMessage(),
														"Ошибка",
														JOptionPane.ERROR_MESSAGE);
											}
										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();
										}

									} catch (Exception e3) {
										e3.printStackTrace();
									}
								} catch (ParseException e2) {
									e2.printStackTrace();
									JOptionPane
											.showMessageDialog(
													null,
													"Ошибка записи."
															+ e2.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
								}
								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
					}

					private String NumberOfPart() {
						orm.Part[] oRMPart = null;
						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								oRMPart = list1CERTData.listParsData(null,
										"numberOfPart");
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (PersistentException e) {
							e.printStackTrace();
						}
						// if (oRMPart.equals(null)) {
						if (oRMPart.length == 0) {
							return "1";
						} else {
							return Integer.toString(oRMPart[oRMPart.length - 1]
									.getNumberOfPart() + 1);
						}
					}
				});
				editNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int rowIndex = jTableNom.getSelectedRow();
						if (rowIndex == -1) {
							JOptionPane.showMessageDialog(null,
									"Объект не выбран.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						orm.Part[] oRMPart;
						// intIDValue = -1;

						try {
							List1CERTData list1CERTData = new List1CERTData();
							try {
								oRMPart = list1CERTData.listParsData(
										"numberOfPart='"
												+ jTableNom.getValueAt(
														rowIndex, 0) + "'",
										null);
								intIDValue = oRMPart[0].getPartID();
								intNumbeValue = oRMPart[0].getNumberOfPart();
								dateValue = new Timestamp(oRMPart[0]
										.getPartDate().getTime());
								prochProValue = oRMPart[0].getPartProchPro();
								prochFctValue = oRMPart[0].getPartProchFact();
								nomenclaturaValue = oRMPart[0]
										.getNomenklature();
								markaValue = oRMPart[0].getMarka();
							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (PersistentException e2) {

							e2.printStackTrace();
							return;
						}

						dat = new JCalendar();
						final TextFrame textFrame = new TextFrame(
								"Редактирование: Парития.", true);
						JButton canBt = new JButton("Отмена");
						JButton saveBt = new JButton("Сохранить");

						JButton calChooserBt = new JButton("...");
						numberTxt = new JTextField(Integer
								.toString(intNumbeValue));
						numberTxt.setEditable(false);
						prochProTxt = new JTextField(Integer
								.toString(prochProValue));
						prochFctTxt = new JTextField(Integer
								.toString(prochFctValue));
						nomenklTxt = new JTextField(nomenclaturaValue);
						markaTxt = new JTextField(markaValue);
						dateTxt = new JTextField(new SimpleDateFormat(
								"dd.MM.yyyy").format(dateValue));
						dateTxt.setEditable(false);
						FormLayout layout = new FormLayout(
								"fill:default:grow, fill:default:grow, 9dlu, fill:default:grow, 14dlu, p, fill:default:grow",
								"fill:p:grow, pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu, pref, 1dlu, p, fill:p:grow");

						DefaultFormBuilder builder = new DefaultFormBuilder(
								layout);
						// , new FormDebugPanel()
						builder.setDefaultDialogBorder();
						CellConstraints cc = new CellConstraints();
						builder.add(new JLabel("Номер партии"), cc.xy(2, 2));
						builder.add(numberTxt, cc.xyw(4, 2, 3));
						builder.add(new JLabel("Отпускная прочность бетона"),
								cc.xy(2, 4));
						builder.add(prochProTxt, cc.xyw(4, 4, 3));
						builder.add(new JLabel("Фактическая прочность бетона"),
								cc.xy(2, 6));
						builder.add(prochFctTxt, cc.xyw(4, 6, 3));
						builder.add(new JLabel("Номенклатура"), cc.xy(2, 8));
						builder.add(nomenklTxt, cc.xyw(4, 8, 3));
						builder.add(new JLabel("Марка"), cc.xy(2, 10));
						builder.add(markaTxt, cc.xyw(4, 10, 3));
						builder.add(new JLabel("Дата"), cc.xy(2, 12));
						builder.add(dateTxt, cc.xyw(4, 12, 1));
						builder.add(calChooserBt, cc.xyw(6, 12, 1));
						builder.add(ButtonBarFactory.buildAddRemoveRightBar(
								saveBt, canBt), cc.xyw(2, 14, 5));

						textFrame.add(builder.getPanel());

						PassportMenu.this.desktop.add(textFrame);

						calChooserBt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								if (dialog == null) {
									dialog = new CalendarChoser(null);
								}

								if (dialog.showDialog(null, "Выбор даты")) {

									dateTxt.setText(dialog.getDate());

								}

								// System.out.println(new
								// SimpleDateFormat("dd.MM.yyyy").format(dat.getDate()));
								// System.out.println(new
								// SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(dat.getDate()));

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

						saveBt.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								DateFormat df = DateFormat.getDateInstance();
								Date date;
								int numberOfpart = 0;
								try {
									numberOfpart = Integer.parseInt(numberTxt
											.getText());
								} catch (NumberFormatException ee) {

									JOptionPane
											.showMessageDialog(null,
													"Не все поля заполнены. \n"
															+ ee.getMessage(),
													"Ошибка",
													JOptionPane.ERROR_MESSAGE);
									return;
								}
								/*
								 * try { List1CERTData list1CERTData = new
								 * List1CERTData(); orm.Part[] oRMPart; try {
								 * 
								 * oRMPart = list1CERTData.listParsData(
								 * "partProchFact='" + numberTxt.getText() +
								 * "'", null); //!!!!(Не работает) if
								 * (oRMPart.length > 1) { JOptionPane
								 * .showMessageDialog( null, "Номер партии " +
								 * numberOfpart + " уже существует.", "Ошибка",
								 * JOptionPane.ERROR_MESSAGE); return; } }
								 * finally {
								 * orm._1CERTPersistentManager.instance()
								 * .disposePersistentManager(); } } catch
								 * (PersistentException e3) {
								 * e3.printStackTrace(); }
								 */
								try {
									date = df.parse(dateTxt.getText());
									Timestamp timestamp = new Timestamp(date
											.getTime());
									try {
										RetrieveAndUpdate1CERTData retrieveAndUpdate1CERTData = new RetrieveAndUpdate1CERTData();
										try {
											retrieveAndUpdate1CERTData.retrieveAndUpdatePartData(
													intIDValue,
													timestamp,
													Integer.parseInt(prochFctTxt
															.getText().trim()),
													Integer.parseInt(prochProTxt
															.getText().trim()),
													numberOfpart, nomenklTxt
															.getText().trim(),
													markaTxt.getText().trim());

										} finally {
											orm._1CERTPersistentManager
													.instance()
													.disposePersistentManager();
										}
									} catch (Exception ee) {
										ee.printStackTrace();
									}

								} catch (ParseException e2) {
									e2.printStackTrace();
								}
								try {
									textFrame.setClosed(true);
								} catch (PropertyVetoException e1) {
									e1.printStackTrace();
								}

							}
						});
					}
				});

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
								rowIndex, 0).toString();
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

								delete1CERTData.deletePartData(strIDValue);
								JOptionPane.showMessageDialog(null, "Объект "
										+ jTableNom.getValueAt(rowIndex, 1)
												.toString() + " удален.",
										"Сообение",
										JOptionPane.INFORMATION_MESSAGE);
								partsModel = new PartsTableModel(null, null);
								jTableNom.setModel(partsModel);
								/*
								 * jTableNom .setRowSorter(new
								 * TableRowSorter<TableModel>( partsModel));
								 */
								jTableNom.repaint();

							} finally {
								orm._1CERTPersistentManager.instance()
										.disposePersistentManager();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Объект нельзя удалить.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				});

				refNomBt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						partsModel = new PartsTableModel(null, null);
						jTableNomPart.setModel(partsModel);
						/*
						 * jTableNomPart .setRowSorter(new
						 * TableRowSorter<TableModel>( partsModel));
						 */
						jTableNomPart.repaint();
					}
				});
				jtb.add(newNomBt);
				jtb.add(editNomBt);
				jtb.add(delNomBt);
				jtb.add(refNomBt);
				JPanel panelJtb = new JPanel();
				panelJtb.add(jtb);
				panel2.add(panelJtb, BorderLayout.WEST);
				panel2.add(panel, BorderLayout.SOUTH);
				textFrame.add(panel2, BorderLayout.NORTH);

				PassportMenu.this.desktop.add(textFrame);
				/*
				 * } catch (PersistentException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */

			}
		});

		addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
				removeAll();
			}

			public void menuSelected(MenuEvent e) {
				add(passDoc);
				add(partDoc);

			}

		});

	}
}
