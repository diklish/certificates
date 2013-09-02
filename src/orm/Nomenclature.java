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

public class Nomenclature {
	public Nomenclature() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_NOMENCLATURE_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_CERTIF_NOMENCLATURE) {
			return ORM_certif_Nomenclature;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == orm.ORMConstants.KEY_NOMENCLATURE_SERIYSERIY) {
			this.seriyseriy = (orm.Seriy) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_STALSTAL) {
			this.stalstal = (orm.Stal) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_ANTIKORANTIKOR) {
			this.antiKorantiKor = (orm.AntiKor) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_KATPOVKATPOV) {
			this.katPovkatPov = (orm.KatPov) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_TUTU) {
			this.TUtu = (orm.TU) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_USERSUSERR) {
			this.usersuserr = (orm.Users) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_PARENT) {
			this.parent = (orm.Nomenclature) owner;
		}
		
		else if (key == orm.ORMConstants.KEY_NOMENCLATURE_MARKAMARCA1) {
			this.markamarca1 = (orm.Marka) owner;
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
	
	private int nomID;
	
	private String nomName;
	
	private String nomFulName;
	
	private java.sql.Timestamp nomTimes;
	
	private orm.Nomenclature parent;
	
	private String nomID1C;
	
	private String parentID1C;
	
	private orm.Seriy seriyseriy;
	
	private orm.Stal stalstal;
	
	private orm.AntiKor antiKorantiKor;
	
	private orm.KatPov katPovkatPov;
	
	private orm.TU TUtu;
	
	private orm.Users usersuserr;
	
	private String numberOfNom;
	
	private Boolean isFolder = false;
	
	private orm.Marka markamarca1;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private java.util.Set ORM_certif_Nomenclature = new java.util.HashSet();
	
	private void setNomID(int value) {
		this.nomID = value;
	}
	
	public int getNomID() {
		return nomID;
	}
	
	public int getORMID() {
		return getNomID();
	}
	
	public void setNomName(String value) {
		this.nomName = value;
	}
	
	public String getNomName() {
		return nomName;
	}
	
	public void setNomFulName(String value) {
		this.nomFulName = value;
	}
	
	public String getNomFulName() {
		return nomFulName;
	}
	
	public void setNomTimes(java.sql.Timestamp value) {
		this.nomTimes = value;
	}
	
	public java.sql.Timestamp getNomTimes() {
		return nomTimes;
	}
	
	public void setNomID1C(String value) {
		this.nomID1C = value;
	}
	
	public String getNomID1C() {
		return nomID1C;
	}
	
	public void setParentID1C(String value) {
		this.parentID1C = value;
	}
	
	public String getParentID1C() {
		return parentID1C;
	}
	
	public void setNumberOfNom(String value) {
		this.numberOfNom = value;
	}
	
	public String getNumberOfNom() {
		return numberOfNom;
	}
	
	public void setIsFolder(boolean value) {
		setIsFolder(new Boolean(value));
	}
	
	public void setIsFolder(Boolean value) {
		this.isFolder = value;
	}
	
	public Boolean getIsFolder() {
		return isFolder;
	}
	
	public void setSeriyseriy(orm.Seriy value) {
		if (seriyseriy != null) {
			seriyseriy.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.Seriy getSeriyseriy() {
		return seriyseriy;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Seriyseriy(orm.Seriy value) {
		this.seriyseriy = value;
	}
	
	private orm.Seriy getORM_Seriyseriy() {
		return seriyseriy;
	}
	
	public void setStalstal(orm.Stal value) {
		if (stalstal != null) {
			stalstal.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.Stal getStalstal() {
		return stalstal;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Stalstal(orm.Stal value) {
		this.stalstal = value;
	}
	
	private orm.Stal getORM_Stalstal() {
		return stalstal;
	}
	
	public void setAntiKorantiKor(orm.AntiKor value) {
		if (antiKorantiKor != null) {
			antiKorantiKor.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.AntiKor getAntiKorantiKor() {
		return antiKorantiKor;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_AntiKorantiKor(orm.AntiKor value) {
		this.antiKorantiKor = value;
	}
	
	private orm.AntiKor getORM_AntiKorantiKor() {
		return antiKorantiKor;
	}
	
	public void setKatPovkatPov(orm.KatPov value) {
		if (katPovkatPov != null) {
			katPovkatPov.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.KatPov getKatPovkatPov() {
		return katPovkatPov;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_KatPovkatPov(orm.KatPov value) {
		this.katPovkatPov = value;
	}
	
	private orm.KatPov getORM_KatPovkatPov() {
		return katPovkatPov;
	}
	
	public void setTUtu(orm.TU value) {
		if (TUtu != null) {
			TUtu.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.TU getTUtu() {
		return TUtu;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_TUtu(orm.TU value) {
		this.TUtu = value;
	}
	
	private orm.TU getORM_TUtu() {
		return TUtu;
	}
	
	public void setUsersuserr(orm.Users value) {
		if (usersuserr != null) {
			usersuserr.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
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
	
	public void setParent(orm.Nomenclature value) {
		if (parent != null) {
			parent.nomenclature.remove(this);
		}
		if (value != null) {
			value.nomenclature.add(this);
		}
	}
	
	public orm.Nomenclature getParent() {
		return parent;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Parent(orm.Nomenclature value) {
		this.parent = value;
	}
	
	private orm.Nomenclature getORM_Parent() {
		return parent;
	}
	
	public void setMarkamarca1(orm.Marka value) {
		if (markamarca1 != null) {
			markamarca1.nomenclature2.remove(this);
		}
		if (value != null) {
			value.nomenclature2.add(this);
		}
	}
	
	public orm.Marka getMarkamarca1() {
		return markamarca1;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Markamarca1(orm.Marka value) {
		this.markamarca1 = value;
	}
	
	private orm.Marka getORM_Markamarca1() {
		return markamarca1;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_NOMENCLATURE_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_PARENT, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Certif_Nomenclature(java.util.Set value) {
		this.ORM_certif_Nomenclature = value;
	}
	
	private java.util.Set getORM_Certif_Nomenclature() {
		return ORM_certif_Nomenclature;
	}
	
	public final orm.Certif_NomenclatureSetCollection certif_Nomenclature = new orm.Certif_NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_NOMENCLATURE_CERTIF_NOMENCLATURE, orm.ORMConstants.KEY_CERTIF_NOMENCLATURE_NOMENCLATURENOM, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private int parentID;
	
	public String toString() {
		return String.valueOf(getNomID());
	}
	
}
