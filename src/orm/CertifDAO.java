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

public class CertifDAO {
	public static Certif loadCertifByORMID(int certifID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertifByORMID(session, certifID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif getCertifByORMID(int certifID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getCertifByORMID(session, certifID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByORMID(int certifID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertifByORMID(session, certifID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif getCertifByORMID(int certifID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getCertifByORMID(session, certifID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByORMID(PersistentSession session, int certifID) throws PersistentException {
		try {
			return (Certif) session.load(orm.Certif.class, new Integer(certifID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif getCertifByORMID(PersistentSession session, int certifID) throws PersistentException {
		try {
			return (Certif) session.get(orm.Certif.class, new Integer(certifID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByORMID(PersistentSession session, int certifID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Certif) session.load(orm.Certif.class, new Integer(certifID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif getCertifByORMID(PersistentSession session, int certifID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Certif) session.get(orm.Certif.class, new Integer(certifID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif[] listCertifByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listCertifByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif[] listCertifByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listCertifByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif[] listCertifByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif as Certif");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (Certif[]) list.toArray(new Certif[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif[] listCertifByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif as Certif");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (Certif[]) list.toArray(new Certif[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertifByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadCertifByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Certif loadCertifByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Certif[] certifs = listCertifByQuery(session, condition, orderBy);
		if (certifs != null && certifs.length > 0)
			return certifs[0];
		else
			return null;
	}
	
	public static Certif loadCertifByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Certif[] certifs = listCertifByQuery(session, condition, orderBy, lockMode);
		if (certifs != null && certifs.length > 0)
			return certifs[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateCertifByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateCertifByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCertifByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateCertifByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateCertifByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif as Certif");
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
	
	public static java.util.Iterator iterateCertifByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Certif as Certif");
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
	
	public static Certif createCertif() {
		return new orm.Certif();
	}
	
	public static boolean save(orm.Certif certif) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(certif);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.Certif certif) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(certif);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Certif certif)throws PersistentException {
		try {
			if(certif.getUsersuserr() != null) {
				certif.getUsersuserr().certif.remove(certif);
			}
			
			if(certif.getPartpart() != null) {
				certif.getPartpart().certif.remove(certif);
			}
			
			orm.Certif_Nomenclature[] lCertif_Nomenclatures = certif.certif_Nomenclature.toArray();
			for(int i = 0; i < lCertif_Nomenclatures.length; i++) {
				lCertif_Nomenclatures[i].setCertifcertif(null);
			}
			return delete(certif);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Certif certif, org.orm.PersistentSession session)throws PersistentException {
		try {
			if(certif.getUsersuserr() != null) {
				certif.getUsersuserr().certif.remove(certif);
			}
			
			if(certif.getPartpart() != null) {
				certif.getPartpart().certif.remove(certif);
			}
			
			orm.Certif_Nomenclature[] lCertif_Nomenclatures = certif.certif_Nomenclature.toArray();
			for(int i = 0; i < lCertif_Nomenclatures.length; i++) {
				lCertif_Nomenclatures[i].setCertifcertif(null);
			}
			try {
				session.delete(certif);
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
	
	public static boolean refresh(orm.Certif certif) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(certif);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.Certif certif) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(certif);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
