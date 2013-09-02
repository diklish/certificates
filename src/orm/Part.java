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

public class Part {
	public Part() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_PART_CERTIF) {
			return ORM_certif;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == orm.ORMConstants.KEY_PART_USERSUSERR) {
			this.usersuserr = (orm.Users) owner;
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
	
	private java.util.Date partDate;
	
	private int partProchPro;
	
	private int partProchFact;
	
	private int partID;
	
	private String nomenklature;
	
	private String marka;
	
	private Integer numberOfPart;
	
	private orm.Users usersuserr;
	
	private java.util.Set ORM_certif = new java.util.HashSet();
	
	private void setPartID(int value) {
		this.partID = value;
	}
	
	public int getPartID() {
		return partID;
	}
	
	public int getORMID() {
		return getPartID();
	}
	
	public void setPartDate(java.util.Date value) {
		this.partDate = value;
	}
	
	public java.util.Date getPartDate() {
		return partDate;
	}
	
	public void setPartProchPro(int value) {
		this.partProchPro = value;
	}
	
	public int getPartProchPro() {
		return partProchPro;
	}
	
	public void setPartProchFact(int value) {
		this.partProchFact = value;
	}
	
	public int getPartProchFact() {
		return partProchFact;
	}
	
	public void setNomenklature(String value) {
		this.nomenklature = value;
	}
	
	public String getNomenklature() {
		return nomenklature;
	}
	
	public void setMarka(String value) {
		this.marka = value;
	}
	
	public String getMarka() {
		return marka;
	}
	
	public void setNumberOfPart(int value) {
		setNumberOfPart(new Integer(value));
	}
	
	public void setNumberOfPart(Integer value) {
		this.numberOfPart = value;
	}
	
	public Integer getNumberOfPart() {
		return numberOfPart;
	}
	
	public void setUsersuserr(orm.Users value) {
		if (usersuserr != null) {
			usersuserr.part.remove(this);
		}
		if (value != null) {
			value.part.add(this);
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
	
	private void setORM_Certif(java.util.Set value) {
		this.ORM_certif = value;
	}
	
	private java.util.Set getORM_Certif() {
		return ORM_certif;
	}
	
	public final orm.CertifSetCollection certif = new orm.CertifSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_PART_CERTIF, orm.ORMConstants.KEY_CERTIF_PARTPART, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getPartID());
	}
	
}
