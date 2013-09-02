package TableModel;

import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;
import ormsamples.List1CERTData;

public class PartsTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 211724823157874888L;
	orm.Part[] oRMPart;
	String[] columnNames = { "Номер партии", "Дата",
			"Отпускная прочность бетона, %",
			"Фактическая отпускная прочность бетона, кгс/см2", "Марка",
			"Номенклатура" };
	String[][] dataNomTable;
	int partLength = 0;

	public PartsTableModel(String Otb, String Sort) {
		setData(Otb, Sort);

	}

	public void setData(String Otb, String Sort) {

		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMPart = list1CERTData.listParsData(Otb, Sort);
				partLength = oRMPart.length;
				dataNomTable = new String[partLength][columnNames.length];

				for (int i = 0; i < partLength; i++) {
					setValueAt(Integer.toString(oRMPart[i].getNumberOfPart()),
							i, 0);
					setValueAt(
							new SimpleDateFormat("dd.MM.yyyy").format(oRMPart[i]
									.getPartDate()), i, 1);
					setValueAt(Integer.toString(oRMPart[i].getPartProchPro()),
							i, 2);
					setValueAt(Integer.toString(oRMPart[i].getPartProchFact()),
							i, 3);
					setValueAt(oRMPart[i].getMarka(), i, 4);
					setValueAt(oRMPart[i].getNomenklature(), i, 5);

				}
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (PersistentException e) {
			e.printStackTrace();
		}
	}

	// количество строк
	public int getRowCount() {

		return oRMPart.length;
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

	// Назначить значение value в ячейке row, col
	public void setValueAt(String value, int row, int col) {
		dataNomTable[row][col] = value;

	}

	
}
