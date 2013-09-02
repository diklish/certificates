package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import ormsamples.List1CERTData;

@SuppressWarnings("serial")
public class TUTableModel extends AbstractTableModel {

	orm.TU[] oRMTU;
	String[] columnNames = { "Код", "Название", "Полное название" };;
	String[][] dataNomTable;

	public TUTableModel(String Otb, String Sort) {setData(Otb, Sort);}
	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMTU = list1CERTData.listTUData(Otb, Sort);
				dataNomTable = new String[oRMTU.length][3];
				for (int i = 0; i < oRMTU.length; i++) {
					dataNomTable[i][0] = Integer.toString(oRMTU[i].getTuID());
					dataNomTable[i][1] = oRMTU[i].getTuName();
					dataNomTable[i][2] = oRMTU[i].getTuFullName();
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

		return oRMTU.length;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		return dataNomTable[row][column];

	}

	// Обновление модели
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
