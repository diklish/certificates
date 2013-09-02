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

public class Certif_NomenclatureDAO {
	public static Certif_Nomenclature loadCertif_NomenclatureByORMID(int certID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertif_NomenclatureByORMID(session, certID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature getCertif_NomenclatureByORMID(int certID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getCertif_NomenclatureByORMID(session, certID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByORMID(int certID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertif_NomenclatureByORMID(session, certID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature getCertif_NomenclatureByORMID(int certID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getCertif_NomenclatureByORMID(session, certID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByORMID(PersistentSession session, int certID) throws PersistentException {
		try {
			return (Certif_Nomenclature) session.load(orm.Certif_Nomenclature.class, new Integer(certID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature getCertif_NomenclatureByORMID(PersistentSession session, int certID) throws PersistentException {
		try {
			return (Certif_Nomenclature) session.get(orm.Certif_Nomenclature.class, new Integer(certID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByORMID(PersistentSession session, int certID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Certif_Nomenclature) session.load(orm.Certif_Nomenclature.class, new Integer(certID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature getCertif_NomenclatureByORMID(PersistentSession session, int certID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Certif_Nomenclature) session.get(orm.Certif_Nomenclature.class, new Integer(certID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature[] listCertif_NomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listCertif_NomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature[] listCertif_NomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listCertif_NomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature[] listCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif_Nomenclature as Certif_Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (Certif_Nomenclature[]) list.toArray(new Certif_Nomenclature[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature[] listCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif_Nomenclature as Certif_Nomenclature");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (Certif_Nomenclature[]) list.toArray(new Certif_Nomenclature[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertif_NomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertif_NomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Certif_Nomenclature[] certif_Nomenclatures = listCertif_NomenclatureByQuery(session, condition, orderBy);
		if (certif_Nomenclatures != null && certif_Nomenclatures.length > 0)
			return certif_Nomenclatures[0];
		else
			return null;
	}
	
	public static Certif_Nomenclature loadCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Certif_Nomenclature[] certif_Nomenclatures = listCertif_NomenclatureByQuery(session, condition, orderBy, lockMode);
		if (certif_Nomenclatures != null && certif_Nomenclatures.length > 0)
			return certif_Nomenclatures[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateCertif_NomenclatureByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateCertif_NomenclatureByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCertif_NomenclatureByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateCertif_NomenclatureByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif_Nomenclature as Certif_Nomenclature");
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
	
	public static java.util.Iterator iterateCertif_NomenclatureByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif_Nomenclature as Certif_Nomenclature");
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
	
	public static Certif_Nomenclature createCertif_Nomenclature() {
		return new orm.Certif_Nomenclature();
	}
	
	public static boolean save(orm.Certif_Nomenclature certif_Nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(certif_Nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.Certif_Nomenclature certif_Nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(certif_Nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Certif_Nomenclature certif_Nomenclature)throws PersistentException {
		try {
			if(certif_Nomenclature.getCertifcertif() != null) {
				certif_Nomenclature.getCertifcertif().certif_Nomenclature.remove(certif_Nomenclature);
			}
			
			if(certif_Nomenclature.getNomenclaturenom() != null) {
				certif_Nomenclature.getNomenclaturenom().certif_Nomenclature.remove(certif_Nomenclature);
			}
			
			return delete(certif_Nomenclature);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Certif_Nomenclature certif_Nomenclature, org.orm.PersistentSession session)throws PersistentException {
		try {
			if(certif_Nomenclature.getCertifcertif() != null) {
				certif_Nomenclature.getCertifcertif().certif_Nomenclature.remove(certif_Nomenclature);
			}
			
			if(certif_Nomenclature.getNomenclaturenom() != null) {
				certif_Nomenclature.getNomenclaturenom().certif_Nomenclature.remove(certif_Nomenclature);
			}
			
			try {
				session.delete(certif_Nomenclature);
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
	
	public static boolean refresh(orm.Certif_Nomenclature certif_Nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(certif_Nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.Certif_Nomenclature certif_Nomenclature) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(certif_Nomenclature);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
