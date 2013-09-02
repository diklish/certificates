package TableModel;

import javax.swing.table.AbstractTableModel;

import org.orm.PersistentException;

import ormsamples.List1CERTData;

@SuppressWarnings("serial")
public class AntiKorTableModel extends AbstractTableModel {

	orm.AntiKor[] oRMAntiKor;
	String[] columnNames = { "Код", "Название", "Полное название" };;
	String[][] dataAntiKorTable;

	public AntiKorTableModel(String Otb, String Sort) {setData(Otb, Sort);
	}
	public void setData(String Otb, String Sort) {

		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {

				oRMAntiKor = list1CERTData.listAntiKorData(Otb, Sort);
				/*
				 * oRMAntiKor = orm.AntiKorDAO .listAntiKorByQuery(Otb, Sort);
				 */// parent
				dataAntiKorTable = new String[oRMAntiKor.length][3];
				for (int i = 0; i < oRMAntiKor.length; i++) {
					dataAntiKorTable[i][0] = Integer.toString(oRMAntiKor[i]
							.getAntiKorID());
					dataAntiKorTable[i][1] = oRMAntiKor[i].getAntiKorName();
					dataAntiKorTable[i][2] = oRMAntiKor[i].getAntiKorFullName();
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

		return oRMAntiKor.length;
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
		return dataAntiKorTable[row][column];

	}

	// Обновление модели
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
