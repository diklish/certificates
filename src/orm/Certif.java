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

public class Certif {
	public Certif() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_CERTIF_CERTIF_NOMENCLATURE) {
			return ORM_certif_Nomenclature;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == orm.ORMConstants.KEY_CERTIF_USERSUSERR) {
			this.usersuserr = (orm.Users) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_CERTIF_PARTPART) {
			this.partpart = (orm.Part) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private String numberOfCert;
	
	private java.sql.Timestamp certifTimes;
	
	private orm.Users usersuserr;
	
	private int certifID;
	
	private orm.Part partpart;
	
	private String marcaMPZ;
	
	private java.util.Set ORM_certif_Nomenclature = new java.util.HashSet();
	
	private void setCertifID(int value) {
		this.certifID = value;
	}
	
	public int getCertifID() {
		return certifID;
	}
	
	public int getORMID() {
		return getCertifID();
	}
	
	public void setCertifTimes(java.sql.Timestamp value) {
		this.certifTimes = value;
	}
	
	public java.sql.Timestamp getCertifTimes() {
		return certifTimes;
	}
	
	public void setNumberOfCert(String value) {
		this.numberOfCert = value;
	}
	
	public String getNumberOfCert() {
		return numberOfCert;
	}
	
	public void setMarcaMPZ(String value) {
		this.marcaMPZ = value;
	}
	
	public String getMarcaMPZ() {
		return marcaMPZ;
	}
	
	public void setUsersuserr(orm.Users value) {
		if (usersuserr != null) {
			usersuserr.certif.remove(this);
		}
		if (value != null) {
			value.certif.add(this);
		}
	}
	
	public orm.Users getUsersuserr() {
		return usersuserr;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Usersuserr(orm.Users value) {
		this.usersuserr = value;
	}
	
	private orm.Users getORM_Usersuserr() {
		return usersuserr;
	}
	
	public void setPartpart(orm.Part value) {
		if (partpart != null) {
			partpart.certif.remove(this);
		}
		if (value != null) {
			value.certif.add(this);
		}
	}
	
	public orm.Part getPartpart() {
		return partpart;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Partpart(orm.Part value) {
		this.partpart = value;
	}
	
	private orm.Part getORM_Partpart() {
		return partpart;
	}
	
	private void setORM_Certif_Nomenclature(java.util.Set value) {
		this.ORM_certif_Nomenclature = value;
	}
	
	private java.util.Set getORM_Certif_Nomenclature() {
		return ORM_certif_Nomenclature;
	}
	
	public final orm.Certif_NomenclatureSetCollection certif_Nomenclature = new orm.Certif_NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_CERTIF_CERTIF_NOMENCLATURE, orm.ORMConstants.KEY_CERTIF_NOMENCLATURE_CERTIFCERTIF, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getCertifID());
	}
	
}
