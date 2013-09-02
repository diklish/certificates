package TableModel;

import javax.swing.table.AbstractTableModel;

import org.orm.PersistentException;

import ormsamples.List1CERTData;

public class AdminUsersTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4716323973189084134L;
	orm.Users[] oRMUsers;
	String[] columnNames = { "Код", "Название", "Полное название", "Роль" };;
	String[][] dataTable;

	public AdminUsersTableModel(String Otb, String Sort) {
		setData(Otb, Sort);
	}

	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMUsers = list1CERTData.listUsersData(Otb, Sort);
				// orm.UsersDAO.listUsersByQuery(Otb, Sort);
				// parent
				dataTable = new String[oRMUsers.length][columnNames.length];
				for (int i = 0; i < oRMUsers.length; i++) {
					dataTable[i][0] = Integer
							.toString(oRMUsers[i].getUserrID());
					dataTable[i][1] = oRMUsers[i].getUserName();
					dataTable[i][2] = oRMUsers[i].getUserFullName();
					dataTable[i][3] = oRMUsers[i].getPravprav().getPravName();
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

		return oRMUsers.length;
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
