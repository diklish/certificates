/**
 * Licensee: Klishin Dmitry
 * License Type: Evaluation
 */
package ormsamples;

import javax.swing.JOptionPane;

import org.orm.*;

import orm.Certif_Nomenclature;
public class Delete1CERTData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance().getSession().beginTransaction();
		try {
			orm.Users oRMUsers = orm.UsersDAO.loadUsersByQuery(null, null);
			// Delete the persistent object
			orm.UsersDAO.delete(oRMUsers);
			orm.Prav oRMPrav = orm.PravDAO.loadPravByQuery(null, null);
			// Delete the persistent object
			orm.PravDAO.delete(oRMPrav);
			orm.Nomenclature oRMNomenclature = orm.NomenclatureDAO.loadNomenclatureByQuery(null, null);
			// Delete the persistent object
			orm.NomenclatureDAO.delete(oRMNomenclature);
			orm.Stal oRMStal = orm.StalDAO.loadStalByQuery(null, null);
			// Delete the persistent object
			orm.StalDAO.delete(oRMStal);
			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.loadAntiKorByQuery(null, null);
			// Delete the persistent object
			orm.AntiKorDAO.delete(oRMAntiKor);
			orm.KatPov oRMKatPov = orm.KatPovDAO.loadKatPovByQuery(null, null);
			// Delete the persistent object
			orm.KatPovDAO.delete(oRMKatPov);
			orm.TU oRMTU = orm.TUDAO.loadTUByQuery(null, null);
			// Delete the persistent object
			orm.TUDAO.delete(oRMTU);
			orm.Seriy oRMSeriy = orm.SeriyDAO.loadSeriyByQuery(null, null);
			// Delete the persistent object
			orm.SeriyDAO.delete(oRMSeriy);
			orm.Certif oRMCertif = orm.CertifDAO.loadCertifByQuery(null, null);
			// Delete the persistent object
			orm.CertifDAO.delete(oRMCertif);
			orm.Certif_Nomenclature oRMCertif_Nomenclature = orm.Certif_NomenclatureDAO.loadCertif_NomenclatureByQuery(null, null);
			// Delete the persistent object
			orm.Certif_NomenclatureDAO.delete(oRMCertif_Nomenclature);
			orm.Part oRMPart = orm.PartDAO.loadPartByQuery(null, null);
			// Delete the persistent object
			orm.PartDAO.delete(oRMPart);
			orm.Marka oRMMarka = orm.MarkaDAO.loadMarkaByQuery(null, null);
			// Delete the persistent object
			orm.MarkaDAO.delete(oRMMarka);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	public void deleteStallData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Stal oRMStal = orm.StalDAO.loadStalByQuery("stalID='" + ID
					+ "'", null);
			orm.StalDAO.delete(oRMStal);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteSeriyData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Seriy oRMSeriy = orm.SeriyDAO.loadSeriyByQuery("seriyID='" + ID
					+ "'", null);
			orm.SeriyDAO.delete(oRMSeriy);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteTUData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.TU oRMTU = orm.TUDAO.loadTUByQuery("tuID='" + ID
					+ "'", null);
			orm.TUDAO.delete(oRMTU);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteKatPovData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.KatPov oRMKatPov = orm.KatPovDAO.loadKatPovByQuery("katPovID='" + ID
					+ "'", null);
			orm.KatPovDAO.delete(oRMKatPov);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteAntiKorData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.loadAntiKorByQuery("antiKorID='" + ID
					+ "'", null);
			orm.AntiKorDAO.delete(oRMAntiKor);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deletePravData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Prav oRMPrav = orm.PravDAO.loadPravByQuery("pravID='" + ID
					+ "'", null);
			orm.PravDAO.delete(oRMPrav);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteUsersData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Users oRMUsers = orm.UsersDAO.loadUsersByQuery("userrID='" + ID
					+ "'", null);
			orm.UsersDAO.delete(oRMUsers);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null,
					e.getMessage(), "Œ¯Ë·Í‡",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	public void deleteMarkaData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Marka oRMMarka = orm.MarkaDAO.loadMarkaByQuery("marcaID='" + ID
					+ "'", null);
			orm.MarkaDAO.delete(oRMMarka);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deletePartData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Part oRMPart = orm.PartDAO.loadPartByQuery("NumberOfPart='" + ID
					+ "'", null);
			orm.PartDAO.delete(oRMPart);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteNomenclatureData(String ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Nomenclature oRMNomenclature = orm.NomenclatureDAO.loadNomenclatureByQuery("nomID='" + ID
					+ "'", null);
			orm.NomenclatureDAO.delete(oRMNomenclature);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	
	public void deleteCertData(int ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Certif oRMCertif = orm.CertifDAO.loadCertifByQuery("certifID='" + ID
					+ "'", null);
			
			orm.CertifDAO.delete(oRMCertif);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteCertNomData(int ID) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {

			orm.Certif_Nomenclature oRMCertif_Nomenclature = orm.Certif_NomenclatureDAO.loadCertif_NomenclatureByQuery("CertID='" + ID
					+ "'", null);
			
			orm.Certif_NomenclatureDAO.delete(oRMCertif_Nomenclature);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}
	public void deleteCertNomData(Certif_Nomenclature certif_Nomenclature) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Certif_NomenclatureDAO.delete(certif_Nomenclature);
			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}




	
	public static void main(String[] args) {
		try {
			Delete1CERTData delete1CERTData = new Delete1CERTData();
			try {
				delete1CERTData.deleteTestData();
			}
			finally {
				orm._1CERTPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
