package TableModel;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;
import orm.Certif;
import ormsamples.List1CERTData;

public class PassTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4452439384977185871L;
	// orm.Certif_Nomenclature[] oRMPass;
	orm.Certif[] oRMCertif;
	String[] columnNames = { "Номер паспорта", "Номенклатура", "Выдан",
			"Партия", "Дата изготовления" };

	private Set<Certif> dataNomTable;

	// String[][] dataNomTable;

	public PassTableModel() {
		dataNomTable = new HashSet<Certif>();

	}

	public PassTableModel(String Otb, String Sort) {
		dataNomTable = new LinkedHashSet<Certif>();
		setData(Otb, Sort);
	}

	/*
	 * public void setDate(Certif_Nomenclature CertifNSelect) {
	 * setValueAt(CertifNSelect); }
	 */
	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				Certif[] cert = list1CERTData.listCertifData(Otb, Sort);

				for (Certif CertS : cert) {
					setValueAt(CertS);
				}
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// количество строк
	public int getRowCount() {

		return dataNomTable.size();
	}

	// количество столбцов
	public int getColumnCount() {
		return columnNames.length;
	}

	// Имя заголовка
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// тип данных, хранимых в столбце
	public Class<String> getColumnClass(int column) {
		// switch (column) {
		/*
		 * case 1: return Boolean.class; case 2: return Icon.class; default:
		 * return Object.class;
		 */
		return String.class;
		// }
	}

	// Назначить значение value в ячейке row, col
	public void setValueAt(Certif value) {
		Certif certS;
		Boolean flag = true;
		for (Iterator<Certif> i = dataNomTable.iterator(); i.hasNext();) {
			certS = i.next();
			if (certS.getCertifID() == value.getCertifID())
				flag = false;
		}

		if (!flag) {
			delValueAt(value);
		}
		dataNomTable.add(value);

	}

	public void delValueAt(Certif value) {
		Certif certSel = null;
		for (Iterator<Certif> i = dataNomTable.iterator(); i.hasNext();) {
			certSel = i.next();
			if (certSel.getCertifID() == value.getCertifID()) {
				i.remove();
			}
		}
		dataNomTable.remove(value);
	}

	public int getCerNIDAt(int rowIndex) {
		Certif cerS = null;
		int j = 0;
		for (Iterator<Certif> i = dataNomTable.iterator(); i.hasNext();) {
			cerS = i.next();
			if (j == rowIndex)
				break;
			j++;
		}
		return cerS.getCertifID();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Certif cerS = null;
		int j = 0;
		for (Iterator<Certif> i = dataNomTable.iterator(); i.hasNext();) {
			cerS = i.next();
			if (j == rowIndex)
				break;
			j++;
		}
		switch (columnIndex) {
		/*
		 * case 0: return Integer.toString(cerNS.getCertID());
		 */
		case 0:
			return cerS.getNumberOfCert();
		case 1:
			return cerS.getPartpart().getNomenklature();
		case 2:
			return new SimpleDateFormat("dd.MM.yyyy").format(cerS
					.getCertifTimes());
		case 3:
			return cerS.getPartpart().getNumberOfPart().toString();
		case 4:
			return new SimpleDateFormat("dd.MM.yyyy").format(cerS.getPartpart()
					.getPartDate());
		default:
			return cerS.getPartpart().getNomenklature();
		}
	}

}