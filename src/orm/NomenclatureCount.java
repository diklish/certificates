package orm;

public class NomenclatureCount {
	/**
	 * @return the nomenclatute
	 */
	public Nomenclature getNomenclatute() {
		return nomenclatute;
	}

	/**
	 * @param nomenclatute
	 *            the nomenclatute to set
	 */
	public void setNomenclatute(Nomenclature nomenclatute) {
		this.nomenclatute = nomenclatute;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	public NomenclatureCount() {
	}

	public NomenclatureCount(Nomenclature nomenclatute, int count) {
		this.nomenclatute=nomenclatute;
		this.count=count;
	}

	public NomenclatureCount(Nomenclature nomenclatute) {
		this.nomenclatute=nomenclatute;
		this.count=1;
	}

	private Nomenclature nomenclatute;
	private int count;

}
