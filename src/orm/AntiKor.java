/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Dmitry Klishin
 * License Type: Evaluation
 */
package orm;

public class AntiKor {
	public AntiKor() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_ANTIKOR_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int antiKorID;
	
	private String antiKorName;
	
	private String antiKorFullName;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private void setAntiKorID(int value) {
		this.antiKorID = value;
	}
	
	public int getAntiKorID() {
		return antiKorID;
	}
	
	public int getORMID() {
		return getAntiKorID();
	}
	
	public void setAntiKorName(String value) {
		this.antiKorName = value;
	}
	
	public String getAntiKorName() {
		return antiKorName;
	}
	
	public void setAntiKorFullName(String value) {
		this.antiKorFullName = value;
	}
	
	public String getAntiKorFullName() {
		return antiKorFullName;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_ANTIKOR_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_ANTIKORANTIKOR, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getAntiKorID());
	}
	
}
