package co.gov.wemake.wemakeapp.persistence.sqlite.contract;

public class ComunaContract extends PersistanceContract {

	public static final String TABLE_NAME = "COMUNA";

	private ComunaContract() {
		super();
	}

	public static final class Column {

		public static final String ID_COMUNA = "id_comuna";

		public static final String[] getAllColumns() {

			return (new String[] { ID_COMUNA });
		}
	}

}
