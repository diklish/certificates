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

public class KatPov {
	public KatPov() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_KATPOV_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int katPovID;
	
	private String katPovName;
	
	private String katPovFullName;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private void setKatPovID(int value) {
		this.katPovID = value;
	}
	
	public int getKatPovID() {
		return katPovID;
	}
	
	public int getORMID() {
		return getKatPovID();
	}
	
	public void setKatPovName(String value) {
		this.katPovName = value;
	}
	
	public String getKatPovName() {
		return katPovName;
	}
	
	public void setKatPovFullName(String value) {
		this.katPovFullName = value;
	}
	
	public String getKatPovFullName() {
		return katPovFullName;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_KATPOV_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_KATPOVKATPOV, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getKatPovID());
	}
	
}
