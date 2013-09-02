package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.orm.PersistentException;
import TableModel.AntiKorTableModel;
import TableModel.KatPovTableModel;
import TableModel.MarkaTableModel;
import TableModel.NomTableModelAll;
import TableModel.SeriyTableModel;
import TableModel.StallTableModel;
import TableModel.TUTableModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import orm.AntiKor;
import orm.KatPov;
import orm.Marka;
import orm.Nomenclature;
import orm.Seriy;
import orm.Stal;
import orm.TU;
import ormsamples.Create1CERTData;
import ormsamples.List1CERTData;
import ormsamples.RetrieveAndUpdate1CERTData;

public class NomNewEdit extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428438299882221112L;
	private Marka marka;
	private Stal stall;
	private AntiKor antiKor;
	private KatPov katPov;
	private TU tu;
	private Seriy seriy;
	private Nomenclature nomE, parentID = null;
	private JLabel codParentLb;
	private JLabel codLb;
	private JLabel cod1CLb;
	private JLabel nameLb;
	private JLabel fullNameLb;
	private JLabel timeLb;
	private JLabel seriyLb;
	private JLabel stallLb;
	private JLabel katPovLb;
	private JLabel antiLb;
	private JLabel markaLb;
	private JLabel tuLb;
	private JLabel userLb;

	private JTextField codTxt;
	private JTextField codParentTxt;
	private JTextField cod1CTxt;
	private JTextField nameTxt;
	private JTextField timeTxt;
	private JTextField fullNameTxt;
	private JTextField seriyTxt;
	private JTextField stallTxt;
	private JTextField antiTxt;
	private JTextField katPovTxt;
	private JTextField markaTxt;
	private JTextField tuTxt;
	private JTextField userTxt;
	private JCheckBox isFolder;

	private JButton nomBt;
	private JButton markaBt;
	private JButton stallBt;
	private JButton antiBt;
	private JButton katBt;
	private JButton tuBt;
	private JButton seriyBt;
	// private JButton userBt;
	private JButton okButton;
	private NomChoserAll dialogChoserNom = null;
	private NomChoser dialogChoserMarka = null;
	private NomChoser dialogChoserStall = null;
	private NomChoser dialogChoserAntiKor = null;
	private NomChoser dialogChoserKatPov = null;
	private NomChoser dialogChoserTU = null;
	private NomChoser dialogChoserSeriy = null;

	public NomNewEdit(JFrame owner, final Nomenclature NomEdit)
			throws PersistentException {

		super(owner, "Номенклатура: Новый.", true);
		setIconImage(new ImageIcon("resurs/nom.png").getImage());
		SwingUtilities.updateComponentTreeUI(this);

		codParentLb = new JLabel("Папка");
		codLb = new JLabel("Код");
		cod1CLb = new JLabel("Код 1С");
		nameLb = new JLabel("Наименование");
		fullNameLb = new JLabel("Полно имя");
		timeLb = new JLabel("Время создание");
		seriyLb = new JLabel("Серии");
		stallLb = new JLabel("Сталь");
		antiLb = new JLabel("Антикоррозийное покрытие");
		markaLb = new JLabel("Марка бетона");
		tuLb = new JLabel("Обозначение стандарта (ТУ)");
		userLb = new JLabel("Ответственный");
		katPovLb = new JLabel("Категория поверхности");
		isFolder = new JCheckBox("Папка");

		codTxt = new JTextField();
		codTxt.setEditable(false);
		codParentTxt = new JTextField();
		codParentTxt.setEditable(false);
		cod1CTxt = new JTextField();
		cod1CTxt.setEditable(false);
		nameTxt = new JTextField();
		timeTxt = new JTextField();
		timeTxt.setEditable(false);
		fullNameTxt = new JTextField();
		seriyTxt = new JTextField();
		seriyTxt.setEditable(false);
		stallTxt = new JTextField();
		stallTxt.setEditable(false);
		antiTxt = new JTextField();
		antiTxt.setEditable(false);
		katPovTxt = new JTextField();
		katPovTxt.setEditable(false);
		markaTxt = new JTextField();
		markaTxt.setEditable(false);
		tuTxt = new JTextField();
		tuTxt.setEditable(false);
		userTxt = new JTextField();
		userTxt.setEditable(false);
		isFolder.setSelected(false);

		nomBt = new JButton("...");
		markaBt = new JButton("...");
		stallBt = new JButton("...");
		antiBt = new JButton("...");
		katBt = new JButton("...");
		tuBt = new JButton("...");
		seriyBt = new JButton("...");
		// userBt = new JButton("...");

		// try {
		// List1CERTData list1CERTData = new List1CERTData();

		// orm.Users user;
		// try {

		// user = list1CERTData.listUsersData("userrID='" +
		// Integer.toString(GlobalVars.value) + "'", null)[0];
		userTxt.setText(GlobalVars.getUser().getUserName());
		// } finally {
		// orm._1CERTPersistentManager.instance()
		// .disposePersistentManager();
		// }

		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// user = orm.UsersDAO.listUsersByQuery(
		// "userrID='" + Integer.toString(GlobalVars.value) + "'", null)[0];

		timeTxt.setText(new Timestamp(new Date().getTime()).toString());

		if (NomEdit != null) {
			codTxt.setText(Integer.toString(NomEdit.getNomID()));
			codParentTxt.setText(NomEdit.getParent().getNomName());
			cod1CTxt.setText(NomEdit.getNomID1C());
			nameTxt.setText(NomEdit.getNomName());
			fullNameTxt.setText(NomEdit.getNomFulName());
			seriyTxt.setText(NomEdit.getSeriyseriy().getSeriyName());
			seriy = NomEdit.getSeriyseriy();
			stallTxt.setText(NomEdit.getStalstal().getStalName());
			stall = NomEdit.getStalstal();
			antiTxt.setText(NomEdit.getAntiKorantiKor().getAntiKorName());
			antiKor = NomEdit.getAntiKorantiKor();
			katPovTxt.setText(NomEdit.getKatPovkatPov().getKatPovName());
			katPov = NomEdit.getKatPovkatPov();
			markaTxt.setText(NomEdit.getMarkamarca1().getMarcaName());
			marka = NomEdit.getMarkamarca1();
			tuTxt.setText(NomEdit.getTUtu().getTuName());
			tu = NomEdit.getTUtu();
			isFolder.setSelected(NomEdit.getIsFolder());
		}

		// События JTextField поля
		nameTxt.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				fullNameTxt.setText(nameTxt.getText());
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});

		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (NomEdit == null) {

					try {
						List1CERTData list1CERTData = new List1CERTData();
						orm.Nomenclature parentID1;
						orm.AntiKor antiKor1;
						orm.Marka marka1;
						orm.Stal stal1;
						orm.KatPov katPov1;
						orm.TU tU1;
						orm.Seriy seriy1;
						orm.Users user1;

						try {
							parentID1 = list1CERTData.listNomenclatureData(
									"nomName='" + codParentTxt.getText() + "'",
									null)[0];
							antiKor1 = list1CERTData.listAntiKorData(
									"antiKorID='" + antiKor.getAntiKorID()
											+ "'", null)[0];
							marka1 = list1CERTData.listMarkaData("marcaID='"
									+ marka.getMarcaID() + "'", null)[0];
							stal1 = list1CERTData.listStalData("stalID='"
									+ stall.getStalID() + "'", null)[0];
							katPov1 = list1CERTData.listKatPovData("katPovID='"
									+ katPov.getKatPovID() + "'", null)[0];
							tU1 = list1CERTData.listTUData(
									"tuID='" + tu.getTuID() + "'", null)[0];
							seriy1 = list1CERTData.listSeriyData("seriyID='"
									+ seriy.getSeriyID() + "'", null)[0];
							user1 = list1CERTData.listUsersData("userrID='"
									+ GlobalVars.getUser().getUserrID() + "'",
									null)[0];
							// user1=GlobalVars.getUser();
							try {
								Create1CERTData create1CERTData = new Create1CERTData();
								try {
									create1CERTData.createNomData(
											nameTxt.getText(),
											fullNameTxt.getText(),
											new Timestamp(new Date().getTime()),
											"", "", parentID1, seriy1, marka1,
											stal1, katPov1, tU1, user1,
											antiKor1, "", isFolder.isSelected());
								}

								catch (Exception e) {
									e.printStackTrace();
								} finally {
									orm._1CERTPersistentManager.instance()
											.disposePersistentManager();

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {

					try {
						List1CERTData list1CERTData = new List1CERTData();
						@SuppressWarnings("unused")
						orm.Nomenclature parentID1;
						orm.AntiKor antiKor1;
						orm.Marka marka1;
						orm.Stal stal1;
						orm.KatPov katPov1;
						orm.TU tU1;
						orm.Seriy seriy1;
						orm.Users user1;

						try {
							parentID1 = list1CERTData.listNomenclatureData(
									"nomName='" + codParentTxt.getText() + "'",
									null)[0];

							antiKor1 = list1CERTData.listAntiKorData(
									"antiKorID='" + antiKor.getAntiKorID()
											+ "'", null)[0];
							marka1 = list1CERTData.listMarkaData("marcaID='"
									+ marka.getMarcaID() + "'", null)[0];
							stal1 = list1CERTData.listStalData("stalID='"
									+ stall.getStalID() + "'", null)[0];
							katPov1 = list1CERTData.listKatPovData("katPovID='"
									+ katPov.getKatPovID() + "'", null)[0];
							tU1 = list1CERTData.listTUData(
									"tuID='" + tu.getTuID() + "'", null)[0];
							seriy1 = list1CERTData.listSeriyData("seriyID='"
									+ seriy.getSeriyID() + "'", null)[0];
							user1 = list1CERTData.listUsersData("userrID='"
									+ GlobalVars.getUser().getUserrID() + "'",
									null)[0];
							try {
								RetrieveAndUpdate1CERTData updateDate = new RetrieveAndUpdate1CERTData();
								Timestamp timeNom = Timestamp.valueOf(timeTxt
										.getText());
								try {
									updateDate
											.retrieveAndUpdateNomenclatureData(
													parentID1,
													NomEdit.getNomID(),
													nameTxt.getText(),
													fullNameTxt.getText(),
													timeNom, user1,
													isFolder.isSelected(),
													marka1, stal1, antiKor1,
													katPov1, tU1, seriy1);

								} catch (Exception e) {
									e.printStackTrace();

								} finally {
									orm._1CERTPersistentManager.instance()
											.disposePersistentManager();

								}
							} catch (Exception ee) {
								ee.printStackTrace();

							}

						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				// dispose();

				setVisible(false);
			}
		});

		nomBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dialogChoserNom == null) {
					NomTableModelAll nomModelSelect = new NomTableModelAll(
							"isFolder='true'", "nomName");
					nomModelSelect.setData("isFolder='true'", "nomName");
					JTable jTableListS = new JTable(nomModelSelect);
					/*
					 * 
					 * final TableRowSorter<TableModel> sorter = new
					 * TableRowSorter<TableModel>( nomModelSelect);
					 * sorter.setModel(nomModelSelect); sorter.setSortable(0,
					 * false); sorter.setSortable(1, false);
					 * 
					 * jTableListS.setRowSorter(sorter);
					 */

					dialogChoserNom = new NomChoserAll(jTableListS,
							nomModelSelect);
				}

				if (dialogChoserNom.showDialog(null, "Выбор родителя")) {

					try {
						List1CERTData list1CERTData = new List1CERTData();

						try {
							int i = dialogChoserNom.getDate(1);
							parentID = list1CERTData.listNomenclatureData(
									"nomID='" + i + "'", null)[0];
							codParentTxt.setText(parentID.getNomName());
						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					/*
					 * try { int i = dialogChoserNom.getDate(1); parentID =
					 * orm.NomenclatureDAO.listNomenclatureByQuery( "nomID='" +
					 * i + "'", null)[0];
					 * codParentTxt.setText(parentID.getNomName());
					 * 
					 * } catch (PersistentException e1) {
					 * 
					 * e1.printStackTrace(); }
					 */
				}

			}
		});
		markaBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dialogChoserMarka == null) {
					MarkaTableModel marModelSelect = new MarkaTableModel(null,
							"marcaName");
					JTable jTableListMarka = new JTable(marModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							marModelSelect);

					dialogChoserMarka = new NomChoser(jTableListMarka, sorter);
				}

				if (dialogChoserMarka.showDialog(null, "Выбор марки бетона")) {
					try {
						int i = dialogChoserMarka.getDate(1);
						marka = orm.MarkaDAO.listMarkaByQuery("marcaID='" + i
								+ "'", null)[0];
						markaTxt.setText(marka.getMarcaName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		stallBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if first time, construct dialog

				if (dialogChoserStall == null) {
					StallTableModel stallModelSelect = new StallTableModel(
							null, "stalName");
					JTable jTableListStall = new JTable(stallModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							stallModelSelect);

					dialogChoserStall = new NomChoser(jTableListStall, sorter);
				}

				if (dialogChoserStall.showDialog(null, "Выбор стали")) {
					try {
						int i = dialogChoserStall.getDate(1);
						stall = orm.StalDAO.listStalByQuery("stalID='" + i
								+ "'", null)[0];
						stallTxt.setText(stall.getStalName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		antiBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if first time, construct dialog

				if (dialogChoserAntiKor == null) {
					AntiKorTableModel antiModelSelect = new AntiKorTableModel(
							null, "antiKorName");
					JTable jTableListAnti = new JTable(antiModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							antiModelSelect);

					dialogChoserAntiKor = new NomChoser(jTableListAnti, sorter);
				}

				if (dialogChoserAntiKor.showDialog(null,
						"Выбор антикор. покрытия")) {
					try {
						int i = dialogChoserAntiKor.getDate(1);
						antiKor = orm.AntiKorDAO.listAntiKorByQuery(
								"antiKorID='" + i + "'", null)[0];
						antiTxt.setText(antiKor.getAntiKorName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		katBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if first time, construct dialog

				if (dialogChoserKatPov == null) {
					KatPovTableModel katModelSelect = new KatPovTableModel(
							null, "katPovName");
					JTable jTableListKat = new JTable(katModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							katModelSelect);

					dialogChoserKatPov = new NomChoser(jTableListKat, sorter);
				}

				if (dialogChoserKatPov.showDialog(null,
						"Выбор категории поверхности")) {
					try {
						int i = dialogChoserKatPov.getDate(1);
						katPov = orm.KatPovDAO.listKatPovByQuery("katPovID='"
								+ i + "'", null)[0];
						katPovTxt.setText(katPov.getKatPovName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		tuBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if first time, construct dialog

				if (dialogChoserTU == null) {
					TUTableModel tuModelSelect = new TUTableModel(null,
							"tuName");
					JTable jTableListTU = new JTable(tuModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							tuModelSelect);

					dialogChoserTU = new NomChoser(jTableListTU, sorter);
				}

				if (dialogChoserTU.showDialog(null, "Выбор ТУ")) {
					try {
						int i = dialogChoserTU.getDate(1);
						tu = orm.TUDAO.listTUByQuery("tuID='" + i + "'", null)[0];
						tuTxt.setText(tu.getTuName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});
		seriyBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if first time, construct dialog

				if (dialogChoserSeriy == null) {
					SeriyTableModel seriyModelSelect = new SeriyTableModel(
							null, "seriyName");
					JTable jTableListSeriy = new JTable(seriyModelSelect);

					final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
							seriyModelSelect);

					dialogChoserSeriy = new NomChoser(jTableListSeriy, sorter);
				}

				if (dialogChoserSeriy.showDialog(null, "Выбор серии")) {
					try {
						int i = dialogChoserSeriy.getDate(1);
						seriy = orm.SeriyDAO.listSeriyByQuery("seriyID='" + i
								+ "'", null)[0];
						seriyTxt.setText(seriy.getSeriyName());

					} catch (PersistentException e1) {

						e1.printStackTrace();
					}

				}

			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				// setVisible(false);
				dispose();
			}
		});

		FormLayout layout = new FormLayout(
				"fill:default:grow, pref, 9dlu, fill:default:grow, 2dlu, p, fill:default:grow",
				"fill:p:grow, pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu,pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, p, fill:p:grow");

		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// , new FormDebugPanel()
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(codLb, cc.xy(2, 2));
		builder.add(codTxt, cc.xyw(4, 2, 1));
		builder.add(isFolder, cc.xy(6, 2));

		builder.add(codParentLb, cc.xy(2, 4));
		builder.add(codParentTxt, cc.xyw(4, 4, 1));
		builder.add(nomBt, cc.xyw(6, 4, 1));

		builder.add(cod1CLb, cc.xy(2, 6));
		builder.add(cod1CTxt, cc.xyw(4, 6, 3));

		builder.add(nameLb, cc.xy(2, 8));
		builder.add(nameTxt, cc.xyw(4, 8, 3));

		builder.add(fullNameLb, cc.xy(2, 10));
		builder.add(fullNameTxt, cc.xyw(4, 10, 3));

		builder.add(timeLb, cc.xy(2, 12));
		builder.add(timeTxt, cc.xyw(4, 12, 3));

		builder.add(seriyLb, cc.xy(2, 14));
		builder.add(seriyTxt, cc.xyw(4, 14, 1));
		builder.add(seriyBt, cc.xyw(6, 14, 1));

		builder.add(stallLb, cc.xy(2, 16));
		builder.add(stallTxt, cc.xyw(4, 16, 1));
		builder.add(stallBt, cc.xyw(6, 16, 1));

		builder.add(antiLb, cc.xy(2, 18));
		builder.add(antiTxt, cc.xyw(4, 18, 1));
		builder.add(antiBt, cc.xyw(6, 18, 1));

		builder.add(katPovLb, cc.xy(2, 20));
		builder.add(katPovTxt, cc.xyw(4, 20, 1));
		builder.add(katBt, cc.xyw(6, 20, 1));

		builder.add(markaLb, cc.xy(2, 22));
		builder.add(markaTxt, cc.xyw(4, 22, 1));
		builder.add(markaBt, cc.xyw(6, 22, 1));

		builder.add(tuLb, cc.xy(2, 24));
		builder.add(tuTxt, cc.xyw(4, 24, 1));
		builder.add(tuBt, cc.xyw(6, 24, 1));

		builder.add(userLb, cc.xy(2, 26));
		builder.add(userTxt, cc.xyw(4, 26, 3));
		// builder.add(userBt, cc.xyw(6, 26, 1));

		builder.add(
				ButtonBarFactory.buildAddRemoveRightBar(okButton, cancelButton),
				cc.xy(4, 28));
		add(builder.getPanel());
		setSize(600, 600);

	}

	public orm.Nomenclature getNewNomenclature() {

		orm.Nomenclature parent;
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				parent = list1CERTData.listNomenclatureData(
						"nomID='" + codTxt.getText() + "'", null)[0];

				return parent;
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setNomenGr(orm.Nomenclature nomencl) {

		codParentTxt.setText(nomencl.getNomName());
		nomE = nomencl;
	}
}