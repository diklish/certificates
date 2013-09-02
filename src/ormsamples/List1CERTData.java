/**
 * Licensee: Klishin Dmitry
 * License Type: Evaluation
 */
package ormsamples;

import org.orm.*;

public class List1CERTData {
	private static final int ROW_COUNT = 100;

	public void listTestData() throws PersistentException {
		System.out.println("Listing Users...");
		orm.Users[] oRMUserses = orm.UsersDAO.listUsersByQuery(null, null);
		int length = Math.min(oRMUserses.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMUserses[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Prav...");
		orm.Prav[] oRMPravs = orm.PravDAO.listPravByQuery(null, null);
		length = Math.min(oRMPravs.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMPravs[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Nomenclature...");
		orm.Nomenclature[] oRMNomenclatures = orm.NomenclatureDAO
				.listNomenclatureByQuery(null, null);
		length = Math.min(oRMNomenclatures.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMNomenclatures[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Stal...");
		orm.Stal[] oRMStals = orm.StalDAO.listStalByQuery(null, null);
		length = Math.min(oRMStals.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMStals[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing AntiKor...");
		orm.AntiKor[] oRMAntiKors = orm.AntiKorDAO.listAntiKorByQuery(null,
				null);
		length = Math.min(oRMAntiKors.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMAntiKors[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing KatPov...");
		orm.KatPov[] oRMKatPovs = orm.KatPovDAO.listKatPovByQuery(null, null);
		length = Math.min(oRMKatPovs.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMKatPovs[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing TU...");
		orm.TU[] oRMTUs = orm.TUDAO.listTUByQuery(null, null);
		length = Math.min(oRMTUs.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMTUs[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Seriy...");
		orm.Seriy[] oRMSeriys = orm.SeriyDAO.listSeriyByQuery(null, null);
		length = Math.min(oRMSeriys.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMSeriys[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Certif...");
		orm.Certif[] oRMCertifs = orm.CertifDAO.listCertifByQuery(null, null);
		length = Math.min(oRMCertifs.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMCertifs[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Certif_Nomenclature...");
		orm.Certif_Nomenclature[] oRMCertif_Nomenclatures = orm.Certif_NomenclatureDAO
				.listCertif_NomenclatureByQuery(null, null);
		length = Math.min(oRMCertif_Nomenclatures.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMCertif_Nomenclatures[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Part...");
		orm.Part[] oRMParts = orm.PartDAO.listPartByQuery(null, null);
		length = Math.min(oRMParts.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMParts[i]);
		}
		System.out.println(length + " record(s) retrieved.");

		System.out.println("Listing Marka...");
		orm.Marka[] oRMMarkas = orm.MarkaDAO.listMarkaByQuery(null, null);
		length = Math.min(oRMMarkas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(oRMMarkas[i]);
		}
		System.out.println(length + " record(s) retrieved.");

	}

	public orm.Users[] listUsersData(String condition, String orderBy)
			throws PersistentException {

		return orm.UsersDAO.listUsersByQuery(condition, orderBy);
	}

	public orm.Prav[] listPravsData(String condition, String orderBy)
			throws PersistentException {

		return orm.PravDAO.listPravByQuery(condition, orderBy);

	}

	public orm.Marka[] listMarkaData(String condition, String orderBy)
			throws PersistentException {

		return orm.MarkaDAO.listMarkaByQuery(condition, orderBy);
	}

	public orm.TU[] listTUData(String condition, String orderBy)
			throws PersistentException {

		return orm.TUDAO.listTUByQuery(condition, orderBy);

	}

	public orm.KatPov[] listKatPovData(String condition, String orderBy)
			throws PersistentException {

		return orm.KatPovDAO.listKatPovByQuery(condition, orderBy);
	}

	public orm.AntiKor[] listAntiKorData(String condition, String orderBy)
			throws PersistentException {

		return orm.AntiKorDAO.listAntiKorByQuery(condition, orderBy);

	}

	public orm.Stal[] listStalData(String condition, String orderBy)
			throws PersistentException {

		return orm.StalDAO.listStalByQuery(condition, orderBy);
	}

	public orm.Nomenclature[] listNomenclatureData(String condition,
			String orderBy) throws PersistentException {

		return orm.NomenclatureDAO.listNomenclatureByQuery(condition, orderBy);

	}
/*	public orm.Nomenclature[] listNomenclatureMarcaData(String condition,
			String orderBy) throws PersistentException {

		return orm.NomenclatureDAO.listNomenclatureMarkaByQuery(condition, orderBy);

	}*/
	public orm.Certif[] listCertifData(String condition, String orderBy)
			throws PersistentException {

		return orm.CertifDAO.listCertifByQuery(condition, orderBy);
	}

	public orm.Certif_Nomenclature[] listCertif_NomData(String condition,
			String orderBy) throws PersistentException {

		return orm.Certif_NomenclatureDAO.listCertif_NomenclatureByQuery(
				condition, orderBy);
	}
	public orm.Seriy[] listSeriyData(String condition, String orderBy)
			throws PersistentException {

		return orm.SeriyDAO.listSeriyByQuery(condition, orderBy);

	}

	public orm.Part[] listParsData(String condition, String orderBy)
			throws PersistentException {

		return orm.PartDAO.listPartByQuery(condition, orderBy);

	}

	public static void main(String[] args) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				list1CERTData.listTestData();
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
