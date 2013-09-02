package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import ormsamples.List1CERTData;

@SuppressWarnings("serial")
public class SeriyTableModel extends AbstractTableModel {

	orm.Seriy[] oRMSeriy;
	String[] columnNames = { "���", "��������", "������ ��������" };;
	String[][] dataSeriyTable;

	public SeriyTableModel(String Otb, String Sort) {setData(Otb, Sort);
	}
	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMSeriy = list1CERTData.listSeriyData(Otb, Sort);
				// orm.SeriyDAO.listSeriyByQuery(Otb, Sort);
				// parent
				dataSeriyTable = new String[oRMSeriy.length][3];
				for (int i = 0; i < oRMSeriy.length; i++) {
					dataSeriyTable[i][0] = Integer.toString(oRMSeriy[i]
							.getSeriyID());
					dataSeriyTable[i][1] = oRMSeriy[i].getSeriyName();
					dataSeriyTable[i][2] = oRMSeriy[i].getSeriyFullName();
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
	// ���������� �����
	public int getRowCount() {

		return oRMSeriy.length;
	}

	// ���������� ��������
	public int getColumnCount() {
		return columnNames.length;
	}

	// ��� ���������
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// ��� ������, �������� � �������
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

	// ������ � ������
	public Object getValueAt(int row, int column) {

		// ������ ������ ��� ������ �������
		// if (column==0)
		// return "" + row;
		// else
		return dataSeriyTable[row][column];

	}

	// ���������� ������
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
