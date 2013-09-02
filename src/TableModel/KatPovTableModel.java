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
	String[] columnNames = { "���", "��������", "������ ��������" };;
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

	// ���������� �����
	public int getRowCount() {

		return oRMKatPov.length;
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
	public Class<String> getColumnClass(int column) {
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
		return dataNomTable[row][column];

	}

	// ���������� ������
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
