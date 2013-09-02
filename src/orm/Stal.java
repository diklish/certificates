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

public class Stal {
	public Stal() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_STAL_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int stalID;
	
	private String stalName;
	
	private String stalFullName;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private void setStalID(int value) {
		this.stalID = value;
	}
	
	public int getStalID() {
		return stalID;
	}
	
	public int getORMID() {
		return getStalID();
	}
	
	public void setStalName(String value) {
		this.stalName = value;
	}
	
	public String getStalName() {
		return stalName;
	}
	
	public void setStalFullName(String value) {
		this.stalFullName = value;
	}
	
	public String getStalFullName() {
		return stalFullName;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_STAL_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_STALSTAL, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getStalID());
	}
	
}
