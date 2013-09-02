package view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.orm.PersistentException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import orm.*;
import ormsamples.Create1CERTData;

public class DowloadNomenclature {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DownloadViewFrame frame = new DownloadViewFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			}

		});

	}
}

class DownloadViewFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218965462141994574L;
	private JTextArea textArea;

	public DownloadViewFrame() {
		setTitle("Загрузка номенклатуры");
		setSize(DEFAUL_WIDTH, DEFAULT_HEIGHT);

		// menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());

		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		JScrollPane scrolPanel = new JScrollPane(textArea);
		add(scrolPanel);

		// table = new JTable();
		// add(table);

	}

	private class FileOpenListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// dialog
			chooser = new JFileChooser();
			// FileFilter filter;
			// .xml
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Xml документ.", "xml");
			chooser.setFileFilter(filter);
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory(new File("S:\\Клишин"));

			// int result = chooser.showOpenDialog(DownloadViewFrame.this);

			// if (result == JFileChooser.APPROVE_OPTION) {
			// nameXML = chooser.getSelectedFile().getPath();

			try {
			//	 dowloadMarkXML("xml/выгрузкаМарка.xml");
			//	dowloadSeriyXML("xml/выгрузкаСерии.xml");
				// dowloadXML("xml/выгрузка.xml");
				 dowloadXML("xml/выгрузка1.xml");
				 dowloadXML("xml/выгрузка2.xml");
				 //dowloadXML(nameXML);
			} catch (ParserConfigurationException e) {
				textArea.append("\n"+ e.getMessage());
				//e.printStackTrace();
			} catch (SAXException e) {
				textArea.append("\n"+ e.getMessage());
				//e.printStackTrace();
			} catch (IOException e) {
				textArea.append("\n"+ e.getMessage());
				//e.printStackTrace();
			}

		}

	}

	// }

	public void dowloadMarkXML(String nameXML)
			throws ParserConfigurationException, SAXException, IOException {
		textArea.append("Начинаю загружать мартку бетона " + new Date());
		File file = new File(nameXML);
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder p = f.newDocumentBuilder();
		Document doc = p.parse(file);
		NodeList Seriys = doc.getElementsByTagName("Record");
		NodeList Children = null;
		Node Seriy;

		String RefNom = "", markaName = "", RefZn = "";

		for (int i = 0; i < Seriys.getLength(); i++) {
			Seriy = Seriys.item(i);

			if (Seriy.getNodeType() == Node.ELEMENT_NODE) {
				Children = Seriy.getChildNodes();

				if (Children.item(3).getTextContent()
						.contentEquals("94bf6324-5c79-11de-ba32-001e0bd74228")) {

					RefNom = Children.item(1).getTextContent();
					RefZn = Children.item(5).getTextContent();

					markaName = RefNom(nameXML, RefZn);

					/*
					 * if (RefNom != "00000000-0000-0000-0000-000000000000") {
					 * try { orm.Marka[] oRMMarka =
					 * orm.MarkaDAO.listMarkaByQuery("ref='" + RefNom+"'",
					 * null);
					 * 
					 * if (oRMMarka.length > 0)
					 * textArea.append("Марка бетона "
					 * +markaName+" уже существует");
					 * 
					 * else
					 */
					try {
						Create1CERTData create1CERTData = new Create1CERTData();
						try {
							create1CERTData.createMarkData(markaName, RefNom);
						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
							textArea.append("Марка бетона " + markaName
									+ " загружена");
						}
					} catch (Exception e) {
						textArea.append(e.getMessage());

					}

					/*
					 * } catch (PersistentException e) {
					 * 
					 * e.printStackTrace(); }
					 */

					// }

				}
			}

		}
		textArea.append("Загрузил мартку бетона " + new Date());
	}

	public void dowloadSeriyXML(String nameXML)
			throws ParserConfigurationException, SAXException, IOException {
		textArea.append("Начинаю загружать серии " + new Date());
		File file = new File(nameXML);
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder p = f.newDocumentBuilder();
		Document doc = p.parse(file);

		NodeList Seriys = doc.getElementsByTagName("Record");

		NodeList Children = null;
		Node Seriy;

		String RefNom = "", serName = "", RefZn = "";

		for (int i = 0; i < Seriys.getLength(); i++) {
			Seriy = Seriys.item(i);

			if (Seriy.getNodeType() == Node.ELEMENT_NODE) {
				Children = Seriy.getChildNodes();

				if (Children.item(3).getTextContent()
						.contentEquals("4cc3509c-dbe6-11dd-9e5e-00138fd242ac")) {

					RefNom = Children.item(1).getTextContent();
					RefZn = Children.item(5).getTextContent();

					serName = RefNom(nameXML, RefZn);
					try {
						Create1CERTData create1CERTData = new Create1CERTData();
						try {
							create1CERTData.createSeriyData(serName, RefNom);
						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
							textArea.append("Серия " + serName
									+ " загружена");
						}
					} catch (Exception e) {
						textArea.append(e.getMessage());

					}

				}
			}

		}
		textArea.append("Загрузил серии " + new Date());
	}

	private String RefNom(String nameXML, String textContent)
			throws ParserConfigurationException, SAXException, IOException {

		File file = new File(nameXML);
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder p = f.newDocumentBuilder();
		Document doc = p.parse(file);
		NodeList rezult = doc
				.getElementsByTagName("CatalogObject.ЗначенияСвойствОбъектов");
		NodeList Children = null;

		for (int i = 0; i < rezult.getLength(); i++) {
			if (rezult.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Children = rezult.item(i).getChildNodes();

				if (Children.item(1).getTextContent().equals(textContent)) {
					return Children.item(13).getTextContent();
				}
			}
		}
		return null;

	}

	public void dowloadXML(String nameXML) throws ParserConfigurationException,
			SAXException, IOException {
		textArea.append("Начинаю загружать номенклатуру " + new Date());
		
		File file = new File(nameXML);
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder p = f.newDocumentBuilder();
		Document doc = p.parse(file);

		// номенклатура
		NodeList nomenclatures = doc
				.getElementsByTagName("CatalogObject.Номенклатура");
		// .getChildNodes();
		NodeList children = null;
		Node nomenclature;
		String Ref = "", Parent1C = "", nomName = "";
		Nomenclature parentID = null;
		Seriy Seriyseriy = null;
		Marka MarkaEnt = null;
		Stal StalEnt = null;
		KatPov KatEnt = null;
		TU TUEnt = null;
		Users UserEnt = null;
		AntiKor AntiKorEnt = null;
		String numberOfNom = "";
		Boolean isFolder = false;
		Boolean RefSeach = false;
		int yu = 1;
		int h = 1;
		for (int i = 0; i < nomenclatures.getLength(); i++) {
			nomenclature = nomenclatures.item(i);

			if (nomenclature.getNodeType() == Node.ELEMENT_NODE) {
				children = nomenclature.getChildNodes();
				numberOfNom = children.item(9).getTextContent();
				Ref = children.item(1).getTextContent();
				Parent1C = children.item(7).getTextContent();

				if (Parent1C != "00000000-0000-0000-0000-000000000000") {
					try {
						orm.Nomenclature[] oRMNomenclatures = orm.NomenclatureDAO
								.listNomenclatureByQuery("nomid1c='" + Parent1C
										+ "'", null);
						// textArea.append();
						// textArea.append("oRMNomenclatures.length="+oRMNomenclatures.length);
						// textArea.append("Parent1C="+Parent1C);
						if (oRMNomenclatures.length > 0)
							parentID = oRMNomenclatures[0];

					} catch (PersistentException e) {
						textArea.append("\n"+ e.getMessage());
						//e.printStackTrace();
					}

				}

				try {
					orm.Seriy[] oRMSeriy = orm.SeriyDAO.listSeriyByQuery(
							"ref='" + Ref + "'", null);

					if (oRMSeriy.length > 0)
						Seriyseriy = oRMSeriy[0];
					else
						Seriyseriy = orm.SeriyDAO.listSeriyByQuery(
								"seriyid='0'", null)[0];

					orm.Marka[] oRMMarka = orm.MarkaDAO.listMarkaByQuery(
							"ref='" + Ref + "'", null);

					if (oRMMarka.length > 0)
						MarkaEnt = oRMMarka[0];
					else
						MarkaEnt = orm.MarkaDAO.listMarkaByQuery("marcaid='0'",
								null)[0];

					orm.Nomenclature[] oRMNomSeach = orm.NomenclatureDAO
							.listNomenclatureByQuery("nomid1c='" + Ref + "'",
									null);
					if (oRMNomSeach.length > 0) {
						textArea.append("\n"+ "!!!!!  " + numberOfNom
								+ " Уже существует " + Ref);
						
						RefSeach = true;
					}

					StalEnt = orm.StalDAO.listStalByQuery("stalid='0'", null)[0];
					KatEnt = orm.KatPovDAO.listKatPovByQuery("katpovid='0'",
							null)[0];
					TUEnt = orm.TUDAO.listTUByQuery("tuid='0'", null)[0];
					UserEnt = orm.UsersDAO.loadUsersByORMID(1);
					AntiKorEnt = orm.AntiKorDAO.listAntiKorByQuery(
							"antikorid='0'", null)[0];
				} catch (PersistentException e1) {
					// TODO Auto-generated catch block
					textArea.append("\n"+ e1.getMessage());
					//e1.printStackTrace();
				}

				nomName = children.item(11).getTextContent();
				isFolder = Boolean.parseBoolean(children.item(3)
						.getTextContent());

				if (!RefSeach) {

					try {

						Create1CERTData create1CERTData = new Create1CERTData();
						try {
							// textArea.append("parentID=" + parentID
							// + "Parent1C=" + Parent1C);
							create1CERTData.createNomData(nomName, nomName,
									new Timestamp(new Date().getTime()), Ref,
									Parent1C, parentID, Seriyseriy, MarkaEnt,
									StalEnt, KatEnt, TUEnt, UserEnt,
									AntiKorEnt, numberOfNom, isFolder);
						} finally {
							orm._1CERTPersistentManager.instance()
									.disposePersistentManager();
							textArea.append("\n"+ " " + h++ + " "
									+ children.item(11).getTextContent());
							textArea.append("\n"+ " " + h++ + " "
									+ children.item(11).getTextContent());
						}
					} catch (Exception e) {
						textArea.append("\n"+ e.getMessage());
						//e.printStackTrace();
					}

				}
				textArea.append("\n"+"- " + yu++);
				
				RefSeach = false;
			}

		}
	
		textArea.append("Закончил загружать номенклатуру " + new Date());
		
	}

	public static final int DEFAULT_HEIGHT = 300;
	public static final int DEFAUL_WIDTH = 400;

	//private JTable table;
	private JFileChooser chooser;
	// private String nameXML;
	// private String[][] dateTable=new String[1000000][1000000];
	// private String[] labelTable={"111","222"};

}
