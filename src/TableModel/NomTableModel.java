package TableModel;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;
import ormsamples.List1CERTData;

public class NomTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3875423226135727004L;
	orm.Nomenclature[] oRMNomenc;
	String[] columnNames = { "", "Код", "Наименование", "Марка бетона",
			"Серии", "Марка стали", "Покрытие", "Поверхность", "ТУ",
			"Ответственный" };
	String[][] dataNomTable;

	public NomTableModel(String Otb) {
		setData(Otb);
	}

	public NomTableModel(String Otb, String Sort) {
		setData(Otb, Sort);
	}

	public void setData(String Otb) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMNomenc = list1CERTData.listNomenclatureData(Otb, "nomname");
				// list1CERTData.listNomenclatureData(Otb, "nomname");

				dataNomTable = new String[oRMNomenc.length][9];
				for (int i = 0; i < oRMNomenc.length; i++) {
					dataNomTable[i][0] = oRMNomenc[i].getIsFolder().toString();
					dataNomTable[i][1] = Integer.toString(oRMNomenc[i]
							.getNomID());
					dataNomTable[i][2] = oRMNomenc[i].getNomFulName();
					dataNomTable[i][3] = oRMNomenc[i].getMarkamarca1()
							.getMarcaName();
					dataNomTable[i][4] = oRMNomenc[i].getSeriyseriy()
							.getSeriyName();
					dataNomTable[i][5] = oRMNomenc[i].getStalstal()
							.getStalName();
					dataNomTable[i][6] = oRMNomenc[i].getAntiKorantiKor()
							.getAntiKorName();
					dataNomTable[i][7] = oRMNomenc[i].getKatPovkatPov()
							.getKatPovName();
					dataNomTable[i][8] = oRMNomenc[i].getTUtu().getTuName();
					dataNomTable[i][9] = oRMNomenc[i].getUsersuserr()
							.getUserName();
					// dataNomTable[i][9] =
					// oRMNomenc[i].getNomTimes().toString();

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
				oRMNomenc = list1CERTData.listNomenclatureData(Otb, "nomname");
				dataNomTable = new String[oRMNomenc.length][9];
				for (int i = 0; i < oRMNomenc.length; i++) {
					dataNomTable[i][0] = Integer.toString(oRMNomenc[i]
							.getNomID());
					dataNomTable[i][1] = oRMNomenc[i].getNomFulName();
					dataNomTable[i][2] = oRMNomenc[i].getMarkamarca1()
							.getMarcaName();
					dataNomTable[i][3] = oRMNomenc[i].getSeriyseriy()
							.getSeriyName();
					dataNomTable[i][4] = oRMNomenc[i].getStalstal()
							.getStalName();
					dataNomTable[i][5] = oRMNomenc[i].getAntiKorantiKor()
							.getAntiKorName();
					dataNomTable[i][6] = oRMNomenc[i].getKatPovkatPov()
							.getKatPovName();
					dataNomTable[i][7] = oRMNomenc[i].getTUtu().getTuName();
					dataNomTable[i][8] = oRMNomenc[i].getUsersuserr()
							.getUserName();
					// dataNomTable[i][9] =
					// oRMNomenc[i].getNomTimes().toString();

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

		return oRMNomenc.length;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		// switch (column) {
		/*
		 * case 1: return Boolean.class; case 2: return Icon.class; default:
		 * return Object.class;
		 */
		return String.class;
		// }
	}

	// данные в ячейке
	public Object getValueAt(int row, int column) {

		// разные данные для разных стобцов
		// if (column==0)
		// return "" + row;
		// else
		// return dataNomTable[row][column];
		switch (column) {
		case 0:
			if (dataNomTable[row][0] == "true")
				return new ImageIcon("resurs/folderClose.png");
			else
				return new ImageIcon("resurs/list.png");
		default:
			return dataNomTable[row][column];
		}

	}

}
