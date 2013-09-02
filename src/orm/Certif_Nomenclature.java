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

public class Certif_Nomenclature {
	public Certif_Nomenclature() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == orm.ORMConstants.KEY_CERTIF_NOMENCLATURE_CERTIFCERTIF) {
			this.certifcertif = (orm.Certif) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_CERTIF_NOMENCLATURE_NOMENCLATURENOM) {
			this.nomenclaturenom = (orm.Nomenclature) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private orm.Certif certifcertif;
	
	private int certID;
	
	private Integer numberOfProd;
	
	private orm.Nomenclature nomenclaturenom;
	
	private int countN = 1;
	
	private void setCertID(int value) {
		this.certID = value;
	}
	
	public int getCertID() {
		return certID;
	}
	
	public int getORMID() {
		return getCertID();
	}
	
	public void setNumberOfProd(int value) {
		setNumberOfProd(new Integer(value));
	}
	
	public void setNumberOfProd(Integer value) {
		this.numberOfProd = value;
	}
	
	public Integer getNumberOfProd() {
		return numberOfProd;
	}
	
	public void setCountN(int value) {
		this.countN = value;
	}
	
	public int getCountN() {
		return countN;
	}
	
	public void setCertifcertif(orm.Certif value) {
		if (certifcertif != null) {
			certifcertif.certif_Nomenclature.remove(this);
		}
		if (value != null) {
			value.certif_Nomenclature.add(this);
		}
	}
	
	public orm.Certif getCertifcertif() {
		return certifcertif;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Certifcertif(orm.Certif value) {
		this.certifcertif = value;
	}
	
	private orm.Certif getORM_Certifcertif() {
		return certifcertif;
	}
	
	public void setNomenclaturenom(orm.Nomenclature value) {
		if (nomenclaturenom != null) {
			nomenclaturenom.certif_Nomenclature.remove(this);
		}
		if (value != null) {
			value.certif_Nomenclature.add(this);
		}
	}
	
	public orm.Nomenclature getNomenclaturenom() {
		return nomenclaturenom;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Nomenclaturenom(orm.Nomenclature value) {
		this.nomenclaturenom = value;
	}
	
	private orm.Nomenclature getORM_Nomenclaturenom() {
		return nomenclaturenom;
	}
	
	public String toString() {
		return String.valueOf(getCertID());
	}
	
}
