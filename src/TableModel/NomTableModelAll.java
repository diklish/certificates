package TableModel;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import orm.Nomenclature;
import ormsamples.List1CERTData;

public class NomTableModelAll extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1065125728998885199L;
	orm.Nomenclature[] oRMNomenc;
	String[] columnNames = { " ", "Код", "Наименование", "Марка бетона",
			"Серии", "Марка стали", "Покрытие", "Поверхность", "ТУ",
			"Ответственный" };
	// String[][] dataNomTable;

	ArrayList<orm.Nomenclature> dataNomList = new ArrayList<orm.Nomenclature>();

	public NomTableModelAll(String Otb) {
		// setData(Otb);
	}

	public NomTableModelAll(String Otb, String Sort) {
		// setData(Otb, Sort);
	}

	public void setData(String Otb) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMNomenc = list1CERTData.listNomenclatureData(Otb, null);
				// list1CERTData.listNomenclatureData(Otb, "nomname");
				dataNomList.clear();
				dataNomList.add(oRMNomenc[0].getParent());
				for (int i = 0; i < oRMNomenc.length; i++) {
					dataNomList.add(oRMNomenc[i]);

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

	public void setData(String Otb, String Sort) {
		/*
		 * try { oRMNomenc = orm.NomenclatureDAO.listNomenclatureByQuery(Otb,
		 * Sort);
		 */// parent
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMNomenc = list1CERTData.listNomenclatureData(Otb, null);
				dataNomList.clear();
				dataNomList.add(oRMNomenc[0].getParent());
				for (int i = 0; i < oRMNomenc.length; i++) {
					dataNomList.add(oRMNomenc[i]);
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

		return dataNomList.size();
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
	public Class<?> getColumnClass(int column) {
		// switch (column) {
		/*
		 * case 1: return Boolean.class; case 2: return Icon.class; default:
		 * return Object.class;
		 */
		switch (column) {
		case 0:
			return Icon.class;
		default:
			return String.class;
		}
	}

	// данные в ячейке
	public Object getValueAt(int row, int column) {

		// разные данные для разных стобцов

		switch (column) {
		case 0:
			if (dataNomList.get(row).getIsFolder())
				if (row != 0)
					return new ImageIcon("resurs/folderClose.png");
				else
					return new ImageIcon("resurs/folderOpen.png");
			else
				return new ImageIcon("resurs/list.png");
		case 1:
			return dataNomList.get(row);
		case 2:
			return dataNomList.get(row).getNomName();
		case 3:
			return dataNomList.get(row).getMarkamarca1().getMarcaName();
		case 4:
			return dataNomList.get(row).getSeriyseriy().getSeriyName();
		case 5:
			return dataNomList.get(row).getStalstal().getStalName();
		case 6:
			return dataNomList.get(row).getKatPovkatPov().getKatPovName();
		case 7:
			return dataNomList.get(row).getAntiKorantiKor().getAntiKorName();
		case 8:
			return dataNomList.get(row).getTUtu().getTuName();
		case 9:
			return dataNomList.get(row).getUsersuserr().getUserName();
		default:
			return dataNomList.get(row).getNomName();
		}

	}

	public void setDataInsert(orm.Nomenclature strIDValue) {

		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {

				oRMNomenc = list1CERTData.listNomenclatureData("parent='"
						+ strIDValue + "'", null);

				// int indexSelect=dataNomList.indexOf(strIDValue);
				// System.out.print("indexSelect="+indexSelect);
				dataNomList.clear();
				dataNomList.add(0, strIDValue);
				for (int i = 0; i < oRMNomenc.length; i++) {
					// indexSelect++;
					dataNomList.add(oRMNomenc[i]);
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

	public void setDataGr(orm.Nomenclature strIDValue) {

		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {

				oRMNomenc = list1CERTData.listNomenclatureData("parent='"
						+ strIDValue + "' AND isFolder=true", null);

				// int indexSelect=dataNomList.indexOf(strIDValue);
				// System.out.print("indexSelect="+indexSelect);
				dataNomList.clear();
				dataNomList.add(0, strIDValue);
				for (int i = 0; i < oRMNomenc.length; i++) {
					// indexSelect++;
					dataNomList.add(oRMNomenc[i]);
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

	public void Refresh() {

	}

}
