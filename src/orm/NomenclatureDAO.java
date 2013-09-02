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

import org.orm.*;
import org.hibernate.Query;
import java.util.List;

public class NomenclatureDAO {
	public static Nomenclature loadNomenclatureByORMID(int nomID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadNomenclatureByORMID(session, nomID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature getNomenclatureByORMID(int nomID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getNomenclatureByORMID(session, nomID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByORMID(int nomID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadNomenclatureByORMID(session, nomID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature getNomenclatureByORMID(int nomID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getNomenclatureByORMID(session, nomID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByORMID(PersistentSession session, int nomID) throws PersistentException {
		try {
			return (Nomenclature) session.load(orm.Nomenclature.class, new Integer(nomID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature getNomenclatureByORMID(PersistentSession session, int nomID) throws PersistentException {
		try {
			return (Nomenclature) session.get(orm.Nomenclature.class, new Integer(nomID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByORMID(PersistentSession session, int nomID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Nomenclature) session.load(orm.Nomenclature.class, new Integer(nomID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature getNomenclatureByORMID(PersistentSession session, int nomID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Nomenclature) session.get(orm.Nomenclature.class, new Integer(nomID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature[] listNomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listNomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature[] listNomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listNomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature[] listNomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Nomenclature as Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (Nomenclature[]) list.toArray(new Nomenclature[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature[] listNomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Nomenclature as Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (Nomenclature[]) list.toArray(new Nomenclature[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadNomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadNomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature loadNomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Nomenclature[] nomenclatures = listNomenclatureByQuery(session, condition, orderBy);
		if (nomenclatures != null && nomenclatures.length > 0)
			return nomenclatures[0];
		else
			return null;
	}
	
	public static Nomenclature loadNomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Nomenclature[] nomenclatures = listNomenclatureByQuery(session, condition, orderBy, lockMode);
		if (nomenclatures != null && nomenclatures.length > 0)
			return nomenclatures[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateNomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateNomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateNomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Nomenclature as Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateNomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Nomenclature as Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Nomenclature createNomenclature() {
		return new orm.Nomenclature();
	}
	
	public static boolean save(orm.Nomenclature nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.Nomenclature nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Nomenclature nomenclature)throws PersistentException {
		try {
			if(nomenclature.getParent() != null) {
				nomenclature.getParent().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getSeriyseriy() != null) {
				nomenclature.getSeriyseriy().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getStalstal() != null) {
				nomenclature.getStalstal().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getAntiKorantiKor() != null) {
				nomenclature.getAntiKorantiKor().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getKatPovkatPov() != null) {
				nomenclature.getKatPovkatPov().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getTUtu() != null) {
				nomenclature.getTUtu().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getUsersuserr() != null) {
				nomenclature.getUsersuserr().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getMarkamarca1() != null) {
				nomenclature.getMarkamarca1().nomenclature2.remove(nomenclature);
			}
			
			orm.Nomenclature[] lNomenclatures = nomenclature.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setParent(null);
			}
			orm.Certif_Nomenclature[] lCertif_Nomenclatures = nomenclature.certif_Nomenclature.toArray();
			for(int i = 0; i < lCertif_Nomenclatures.length; i++) {
				lCertif_Nomenclatures[i].setNomenclaturenom(null);
			}
			return delete(nomenclature);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Nomenclature nomenclature, org.orm.PersistentSession session)throws PersistentException {
		try {
			if(nomenclature.getParent() != null) {
				nomenclature.getParent().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getSeriyseriy() != null) {
				nomenclature.getSeriyseriy().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getStalstal() != null) {
				nomenclature.getStalstal().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getAntiKorantiKor() != null) {
				nomenclature.getAntiKorantiKor().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getKatPovkatPov() != null) {
				nomenclature.getKatPovkatPov().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getTUtu() != null) {
				nomenclature.getTUtu().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getUsersuserr() != null) {
				nomenclature.getUsersuserr().nomenclature.remove(nomenclature);
			}
			
			if(nomenclature.getMarkamarca1() != null) {
				nomenclature.getMarkamarca1().nomenclature2.remove(nomenclature);
			}
			
			orm.Nomenclature[] lNomenclatures = nomenclature.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setParent(null);
			}
			orm.Certif_Nomenclature[] lCertif_Nomenclatures = nomenclature.certif_Nomenclature.toArray();
			for(int i = 0; i < lCertif_Nomenclatures.length; i++) {
				lCertif_Nomenclatures[i].setNomenclaturenom(null);
			}
			try {
				session.delete(nomenclature);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(orm.Nomenclature nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.Nomenclature nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
