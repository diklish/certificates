package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import ormsamples.List1CERTData;

public class AdminPravTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3933419501846753484L;
	orm.Prav[] oRMPrav;
	String[] columnNames = { "Код", "Название", "Полное название" };;
	String[][] dataTable;

	public AdminPravTableModel(String Otb, String Sort) {setData(Otb, Sort);
		}
	public void setData(String Otb, String Sort) {
		try {
		List1CERTData list1CERTData = new List1CERTData();
		try {
			oRMPrav = list1CERTData.listPravsData(Otb, Sort);

			dataTable = new String[oRMPrav.length][3];
			for (int i = 0; i < oRMPrav.length; i++) {
				dataTable[i][0] = Integer.toString(oRMPrav[i].getPravID());
				dataTable[i][1] = oRMPrav[i].getPravName();
				dataTable[i][2] = oRMPrav[i].getPravFullName();
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

		return oRMPrav.length;
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
		return dataTable[row][column];

	}

	// Обновление модели
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
