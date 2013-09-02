package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import ormsamples.List1CERTData;

public class KatPovTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173151780877941120L;
	orm.KatPov[] oRMKatPov;
	String[] columnNames = { "Код", "Название", "Полное название" };;
	String[][] dataNomTable;

	public KatPovTableModel(String Otb, String Sort) {
		setData(Otb, Sort);
	}

	public void setData(String Otb, String Sort) {

		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMKatPov = list1CERTData.listKatPovData(Otb, Sort);
				dataNomTable = new String[oRMKatPov.length][3];
				for (int i = 0; i < oRMKatPov.length; i++) {
					dataNomTable[i][0] = Integer.toString(oRMKatPov[i]
							.getKatPovID());
					dataNomTable[i][1] = oRMKatPov[i].getKatPovName();
					dataNomTable[i][2] = oRMKatPov[i].getKatPovFullName();
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

		return oRMKatPov.length;
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

	// данные в ячейке
	public Object getValueAt(int row, int column) {

		// разные данные для разных стобцов
		// if (column==0)
		// return "" + row;
		// else
		return dataNomTable[row][column];

	}

	// Обновление модели
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
