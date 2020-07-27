package impresa.edile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ImpresaEdile {

	public ImpresaEdile() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver ok");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found:" + e.getMessage());
		}
	}

	private Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/impresa?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection connection = DriverManager.getConnection(url, "impresaUser", "impresaTester");
		System.out.println("Connessione OK");
		return connection;
	}

	private void releaseConnection(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

	public boolean aggiungiRimuoviAggiorna(String sql) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			st = con.createStatement();

			int result = st.executeUpdate(sql);

			if (result > 0) {
				System.out.println("SQL eseguito");
				return true;
			} else {
				System.out.println("Errore");
				return false;
			}

		} catch (SQLException s) {
			System.err.println(s.getMessage());
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void stampaTabella(String str, int columnsNumber, String nomeTabella) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from " + nomeTabella);
			System.out.println(str);
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++)
					System.out.format("%s ", rs.getString(i));
				System.out.println();
			}
		} catch (SQLException s) {
			System.err.println(s.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void assumiPersonale() {
		System.out.println(
				"SQL: insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('TTTTTTTTTTTTTTTT', 'Tester', 'Tester', 'Dirigente', 1700.54, 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- PRIMA --", 8, "personale");
		aggiungiRimuoviAggiorna(
				"insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('TTTTTTTTTTTTTTTT', 'Tester', 'Tester', 'Dirigente', 1700.54, 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- DOPO --", 8, "personale");
	}

	public void licenziaPersonale() {
		System.out.println("SQL: delete from personale where personale.CF = 'TTTTTTTTTTTTTTTT'");
		stampaTabella("-- PRIMA --", 8, "personale");
		aggiungiRimuoviAggiorna("delete from personale where personale.CF = 'TTTTTTTTTTTTTTTT'");
		stampaTabella("-- DOPO --", 8, "personale");
	}

	public void aggiungiCliente() {
		System.out.println(
				"SQL: insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (11111111, 'QQQQQQQQQQQQQQ', 'Tester', 'Tester', 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- PRIMA --", 7, "cliente");
		aggiungiRimuoviAggiorna(
				"insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (11111111, 'QQQQQQQQQQQQQQ', 'Tester', 'Tester', 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- DOPO --", 7, "cliente");
	}

	public void creaCantiere() {
		System.out.println(
				"SQL: insert into cantiere(codice, valore, localita, `#personale`) values (111111, 11111.11, 'TTTTTTTTT', 1)");
		stampaTabella("-- PRIMA --", 4, "cantiere");
		aggiungiRimuoviAggiorna(
				"insert into cantiere(codice, valore, localita, `#personale`) values (111111, 11111.11, 'TTTTTTTTT', 1)");
		stampaTabella("-- DOPO --", 4, "cantiere");
	}

	public void rimuoviCantiere() {
		System.out.println("SQL: delete from cantiere where codice = 111111");
		stampaTabella("-- PRIMA --", 4, "cantiere");
		aggiungiRimuoviAggiorna("delete from cantiere where codice = 111111");
		stampaTabella("-- DOPO --", 4, "cantiere");
	}

	public void aggiungiPermesso() {
		System.out.println(
				"SQL: insert into permesso(stato, `codice cantiere`, `telefono ente locale`) values('lavorazione', 61981, 816767576)");
		stampaTabella("-- PRIMA --", 3, "permesso");
		aggiungiRimuoviAggiorna(
				"insert into permesso(stato, `codice cantiere`, `telefono ente locale`) values('lavorazione', 61981, 816767576)");
		stampaTabella("-- DOPO --", 3, "permesso");
	}

	public void rimuoviPermesso() {
		System.out.println("SQL: delete from permesso where `codice cantiere` = 61981");
		stampaTabella("-- PRIMA --", 3, "permesso");
		aggiungiRimuoviAggiorna("delete from permesso where `codice cantiere` = 61981");
		stampaTabella("-- DOPO --", 3, "permesso");
	}

	public void aggiungiMagazzino() {
		System.out.println(
				"SQL: insert into magazzino(telefono, via, citta, CAP) values (88888, 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- PRIMA --", 4, "magazzino");
		aggiungiRimuoviAggiorna(
				"insert into magazzino(telefono, via, citta, CAP) values (88888, 'Garibaldi', 'Milano', 47827)");
		stampaTabella("-- DOPO --", 4, "magazzino");
	}

	public void rimuoviMagazzino() {
		System.out.println("SQL: delete from magazzino where telefono = 88888");
		stampaTabella("-- PRIMA --", 4, "magazzino");
		aggiungiRimuoviAggiorna("delete from magazzino where telefono = 88888");
		stampaTabella("-- DOPO --", 4, "magazzino");
	}

	public void aggiungiEnteLocale() {
		System.out.println("SQL: insert into `ente locale`(telefono) values(9999999)");
		stampaTabella("-- PRIMA --", 1, "`ente locale`");
		aggiungiRimuoviAggiorna("insert into `ente locale`(telefono) values(9999999)");
		stampaTabella("-- DOPO --", 1, "`ente locale`");
	}

	public void rimuoviEnteLocale() {
		System.out.println("SQL: delete from `ente locale` where telefono = 9999999");
		stampaTabella("-- PRIMA --", 1, "`ente locale`");
		aggiungiRimuoviAggiorna("delete from `ente locale` where telefono = 9999999");
		stampaTabella("-- DOPO --", 1, "`ente locale`");
	}

	public void aggiungiFornitore() {
		System.out.println(
				"SQL: insert into fornitore(telefono, nome, `partita IVA`) values (66666666, 'TTTTTTTTTT', 'TT1111111')");
		stampaTabella("-- PRIMA --", 3, "fornitore");
		aggiungiRimuoviAggiorna(
				"insert into fornitore(telefono, nome, `partita IVA`) values (66666666, 'TTTTTTTTTT', 'TT1111111')");
		stampaTabella("-- DOPO --", 3, "fornitore");
	}

	public void rimuoviFornitore() {
		System.out.println("SQL: delete from fornitore where telefono = 66666666");
		stampaTabella("-- PRIMA --", 3, "fornitore");
		aggiungiRimuoviAggiorna("delete from fornitore where telefono = 66666666");
		stampaTabella("-- DOPO --", 3, "fornitore");
	}

	public void aggiungiProdotto() {
		System.out.println(
				"SQL: insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (5555555, 'TTTTTTT', 27.69, '2020-01-26', 687654537)");
		stampaTabella("-- PRIMA --", 5, "prodotto");
		aggiungiRimuoviAggiorna(
				"insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (5555555, 'TTTTTTT', 27.69, '2020-01-26', 687654537)");
		stampaTabella("-- DOPO --", 5, "prodotto");
	}

	public void rimuoviProdotto() {
		System.out.println("SQL: delete from prodotto where codice = 5555555");
		stampaTabella("-- PRIMA --", 5, "prodotto");
		aggiungiRimuoviAggiorna("delete from prodotto where codice = 5555555");
		stampaTabella("-- DOPO --", 5, "prodotto");
	}

	public void stampaProdottiNonUsatiInNessunCantiere() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			System.out.println("SQL: select * from prodotto where codice not in (select `codice prodotto` from usa)");
			stampaTabella("-- PRODOTTI USATI --", 2, "usa");
			st = con.createStatement();
			rs = st.executeQuery("select * from prodotto where codice not in (select `codice prodotto` from usa)");
			String nome;
			int codice, telefono;
			Date data;
			double prezzo;
			System.out.println("-- PRODOTTI NON USATI --");
			while (rs.next()) {
				codice = rs.getInt(1);
				nome = rs.getString(2);
				prezzo = rs.getDouble(3);
				data = rs.getDate(4);
				telefono = rs.getInt(5);
				System.out.println(codice + " " + nome + " " + prezzo + " " + data + " " + telefono);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void stampaPersonaleSenzaTelefono() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			System.out.println("SQL: select * from personale where CF not in (select `CF personale` from telefono)");
			stampaTabella("-- PERSONALE CON TELEFONO --", 2, "telefono");
			st = con.createStatement();
			rs = st.executeQuery("select * from personale where CF not in (select `CF personale` from telefono)");
			String cf, nome, cognome, professione, via, citta;
			double stipendio;
			int cap;
			System.out.println("-- PERSONALE SENZA TELEFONO --");
			while (rs.next()) {
				cf = rs.getString(1);
				nome = rs.getString(2);
				cognome = rs.getString(3);
				professione = rs.getString(4);
				stipendio = rs.getDouble(5);
				via = rs.getString(6);
				citta = rs.getString(7);
				cap = rs.getInt(8);
				System.out.println(cf + " " + nome + " " + cognome + " " + professione + " " + stipendio + " " + via
						+ " " + citta + " " + cap);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void stampaPersonaleNonImpiegato() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			System.out.println(
					"SQL: select * from personale where CF not in (select `CF personale` from `ha personale`)");
			stampaTabella("-- PERSONALE IMPIEGATO --", 2, "`ha personale`");
			st = con.createStatement();
			rs = st.executeQuery("select * from personale where CF not in (select `CF personale` from `ha personale`)");
			String cf, nome, cognome, professione, via, citta;
			double stipendio;
			int cap;
			System.out.println("-- PERSONALE NON IMPIEGATO --");
			while (rs.next()) {
				cf = rs.getString(1);
				nome = rs.getString(2);
				cognome = rs.getString(3);
				professione = rs.getString(4);
				stipendio = rs.getDouble(5);
				via = rs.getString(6);
				citta = rs.getString(7);
				cap = rs.getInt(8);
				System.out.println(cf + " " + nome + " " + cognome + " " + professione + " " + stipendio + " " + via
						+ " " + citta + " " + cap);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void stampaNumeroPersonaleCantiere() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			System.out.println("SQL: select codice, `#personale` from cantiere");
			st = con.createStatement();
			rs = st.executeQuery("select codice, `#personale` from cantiere");
			int codice, nPersonale;
			System.out.println("-- NUMERO PERSONALE DEI CANTIERI --");
			while (rs.next()) {
				codice = rs.getInt(1);
				nPersonale = rs.getInt(2);
				System.out.println(codice + " " + nPersonale);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void aggiungiPersonaleAlCantiere() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			System.out.println(
					"SQL: insert into `ha personale`(`codice cantiere`, `CF personale`) values (61981, 'DSAF87C68DCV78FV')");
			stampaTabella("-- PRIMA --", 2, "`ha personale`");
			boolean result = aggiungiRimuoviAggiorna(
					"insert into `ha personale`(`codice cantiere`, `CF personale`) values (61981, 'DSAF87C68DCV78FV')");
			stampaTabella("-- DOPO --", 2, "`ha personale`");
			if (result) {
				System.out.println();
				stampaTabella("-- PRIMA --", 4, "cantiere");
				st = con.createStatement();
				rs = st.executeQuery("select `#personale` from cantiere where codice = 61981");
				rs.next();
				int personaleN = rs.getInt(1);
				personaleN += 1;
				st.executeUpdate("update cantiere set `#personale` = " + personaleN + " where codice = 61981");
				stampaTabella("-- DOPO --", 4, "cantiere");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void aggiornaPersonale() {
		System.out.println(
				"SQL: update personale set CF = 'TTTT1111', nome = 'TTT', cognome = 'TTTTT', professione = 'TTTT', stipendio = 11111.1, via = 'tttt', citta = 'tttt', CAP = 1111 where CF = 'DSAF87C68DCV78FV'");
		stampaTabella("-- PRIMA --", 8, "personale");
		aggiungiRimuoviAggiorna(
				"update personale set CF = 'TTTT1111', nome = 'TTT', cognome = 'TTTTT', professione = 'TTTT', stipendio = 11111.1, via = 'tttt', citta = 'tttt', CAP = 1111 where CF = 'DSAF87C68DCV78FV'");
		stampaTabella("-- DOPO --", 8, "personale");
	}

	public void stampaCantieriLocalitaUgualeTuttoPersonaleCitta() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Connection con2 = null;
		Statement st2 = null;
		ResultSet rs2 = null;
		try {
			con2 = getConnection();
			System.out.println(
					"SQL: select codice, localita, CF, citta from cantiere, `ha personale` H1, personale where cantiere.codice = H1.`codice cantiere` \nand personale.CF = H1.`CF personale`");
			st2 = con2.createStatement();
			rs2 = st2.executeQuery(
					"select codice, localita, CF, citta from cantiere, `ha personale` H1, personale where cantiere.codice = H1.`codice cantiere` and personale.CF = H1.`CF personale`");
			String localita2, citta2, cf2;
			int codice2;
			System.out.println("-- CANTIERE LOCALITA, PERSONALE IMPIEGATO LOCALITA");
			while (rs2.next()) {
				codice2 = rs2.getInt(1);
				localita2 = rs2.getString(2);
				cf2 = rs2.getString(3);
				citta2 = rs2.getString(4);
				System.out.println(codice2 + " " + " " + localita2 + " " + cf2 + " " + citta2);
			}
			con = getConnection();
			System.out.println(
					"SQL: select * from cantiere C1 where C1.codice not in ( select H1.`codice cantiere` from personale P1, `ha personale` H1, cantiere C1 where P1.CF = H1.`CF personale` \nand C1.codice = H1.`codice cantiere` and exists (select * from personale P2 where P2.CF = P1.CF and C1.localita <> P2.citta))");
			st = con.createStatement();
			rs = st.executeQuery(
					"select * from cantiere C1 where C1.codice not in ( select H1.`codice cantiere` from personale P1, `ha personale` H1, cantiere C1 where P1.CF = H1.`CF personale` and C1.codice = H1.`codice cantiere` and exists (select * from personale P2 where P2.CF = P1.CF and C1.localita <> P2.citta))");
			String localita;
			double valore;
			int codice, nPersonale;
			System.out.println("-- CANTIERI CHE HANNO TUTTO IL PERSONALE DELLA STESSA CITTA DEL CANTIERE --");
			while (rs.next()) {
				codice = rs.getInt(1);
				valore = rs.getDouble(2);
				localita = rs.getString(3);
				nPersonale = rs.getInt(4);
				System.out.println(codice + " " + " " + valore + " " + localita + " " + nPersonale);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void start() throws ParseException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			st = con.createStatement();

			Scanner input = new Scanner(System.in);
			int exit = -1;
			InputStreamReader keyIS;
			BufferedReader keyBR;
			int i = 1;
			String scelta;

			keyIS = new InputStreamReader(System.in);
			keyBR = new BufferedReader(keyIS);

			while (i != -1) {
				if (i != 1) {
					System.out.println("Premere 1 per continuare, altro per uscire");
					scelta = keyBR.readLine();
					try {
						i = Integer.parseInt(scelta);
					} catch (NumberFormatException e) {
						break;
					}
				}
				if (i == 1) {
					System.out.println("Operazioni:");
					System.out.println("0  - Uscire");
					System.out.println("1  - Assumi nuovo personale");
					System.out.println("2  - Licenzia personale");
					System.out.println("3  - Aggiungi nuovo cliente");
					System.out.println("4  - Crea nuovo cantiere");
					System.out.println("5  - Elimina cantiere");
					System.out.println("6  - Aggiungi permesso");
					System.out.println("7  - Rimuovi permesso");
					System.out.println("8  - Aggiungi magazzino");
					System.out.println("9  - Rimuovi magazzino");
					System.out.println("10 - Aggiungi ente locale");
					System.out.println("11 - Rimuovi ente locale");
					System.out.println("12 - Aggiungi fornitore");
					System.out.println("13 - Rimuovi fornitore");
					System.out.println("14 - Aggiungi prodotto");
					System.out.println("15 - Rimuovi prodotto");
					System.out.println("16 - Stampa prodotti non usati in nessun cantiere");
					System.out.println("17 - Stampa personale senza telefono");
					System.out.println("18 - Stampa personale non impiegato in nessun cantiere");
					System.out.println("19 - Stampa numero personale dei cantiere");
					System.out.println("20 - Aggiungi personale al cantiere");
					System.out.println("21 - Aggiorna personale");
					System.out.println(
							"22 - Stampa i cantieri i quali hanno tutto il personale della stessa città del cantiere");

					System.out.print("Inserisci scelta: ");
					scelta = keyBR.readLine();

					try {
						i = Integer.parseInt(scelta);
					} catch (NumberFormatException e) {
						i = 23;
					}

					switch (i) {
					case 0: {
						System.out.println("Uscito");
						i = -1;
						break;
					}
					case 1: {
						System.out.format("CF: ");
						String cf = input.next();
						System.out.format("Nome: ");
						String nome = input.next();
						System.out.format("Cognome: ");
						String cognome = input.next();
						System.out.format("Professione: ");
						String professione = input.next();
						System.out.format("Stipendio: ");
						double stipendio = input.nextDouble();
						System.out.format("Via: ");
						String via = input.next();
						System.out.format("Citta: ");
						String citta = input.next();
						System.out.format("CAP: ");
						int cap = input.nextInt();
						exit = st.executeUpdate(
								"insert into personale(CF, nome, cognome, professione, stipendio, via, citta, CAP) values ('"
										+ cf + "', '" + nome + "', '" + cognome + "', '" + professione + "', "
										+ stipendio + ", '" + via + "', '" + citta + "'," + cap + ")");
						if (exit > 0)
							System.out.println("Assunto");
						else
							System.out.println("Errore");
						break;
					}
					case 2: {
						System.out.format("CF: ");
						String cf = input.next();
						exit = st.executeUpdate("delete from personale where CF = '" + cf + "'");
						if (exit > 0)
							System.out.println("Licenziato");
						else
							System.out.println("Errore");
						break;
					}
					case 3: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						System.out.format("CF: ");
						String cf = input.next();
						System.out.format("Nome: ");
						String nome = input.next();
						System.out.format("Cognome: ");
						String cognome = input.next();
						System.out.format("Via: ");
						String via = input.next();
						System.out.format("Citta: ");
						String citta = input.next();
						System.out.format("CAP: ");
						int cap = input.nextInt();
						exit = st.executeUpdate(
								"insert into cliente(telefono, CF, nome, cognome, via, citta, CAP) values (" + telefono
										+ ", '" + cf + "', '" + nome + "', '" + cognome + "', '" + via + "', '" + citta
										+ "'," + cap + ")");
						if (exit > 0)
							System.out.println("Aggiunto");
						else
							System.out.println("Errore");
						break;
					}
					case 4: {
						System.out.format("Codice: ");
						int codice = input.nextInt();
						System.out.format("Valore: ");
						double valore = input.nextDouble();
						System.out.format("Localita: ");
						String localita = input.next();
						System.out.format("#personale: ");
						int nPersonale = input.nextInt();
						exit = st.executeUpdate("insert into cantiere(codice, valore, localita, `#personale`) values ("
								+ codice + ", " + valore + ", '" + localita + "', " + nPersonale + ")");
						if (exit > 0)
							System.out.println("Creato");
						else
							System.out.println("Errore");
						break;
					}
					case 5: {
						System.out.format("Codice: ");
						int codice = input.nextInt();
						exit = st.executeUpdate("delete from cantiere where codice = " + codice);
						if (exit > 0)
							System.out.println("Eliminato");
						else
							System.out.println("Errore");
						break;
					}
					case 6: {
						System.out.format("Stato: ");
						String stato = input.next();
						System.out.format("Codice cantiere: ");
						int codice = input.nextInt();
						System.out.format("Telefono ente locale: ");
						int telefono = input.nextInt();
						exit = st.executeUpdate(
								"insert into permesso(stato, `codice cantiere`, `telefono ente locale`) values('"
										+ stato + "', " + codice + ", " + telefono + ")");
						if (exit > 0)
							System.out.println("Aggiunto");
						else
							System.out.println("Errore");
						break;
					}
					case 7: {
						System.out.format("Codice cantiere: ");
						int codice = input.nextInt();
						exit = st.executeUpdate("delete from permesso where `codice cantiere` = " + codice);
						if (exit > 0)
							System.out.println("Rimosso");
						else
							System.out.println("Errore");
						break;
					}
					case 8: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						System.out.format("Via: ");
						String via = input.next();
						System.out.format("Citta: ");
						String citta = input.next();
						System.out.format("CAP: ");
						int cap = input.nextInt();
						exit = st.executeUpdate("insert into magazzino(telefono, via, citta, CAP) values (" + telefono
								+ ", '" + via + "', '" + citta + "'," + cap + ")");
						if (exit > 0)
							System.out.println("Aggiunto");
						else
							System.out.println("Errore");
						break;
					}
					case 9: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						exit = st.executeUpdate("delete from magazzino where telefono = " + telefono);
						if (exit > 0)
							System.out.println("Rimosso");
						else
							System.out.println("Errore");
						break;
					}
					case 10: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						exit = st.executeUpdate("insert into `ente locale`(telefono) values(" + telefono + ")");
						if (exit > 0)
							System.out.println("Aggiunto");
						else
							System.out.println("Errore");
						break;
					}
					case 11: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						exit = st.executeUpdate("delete from `ente locale` where telefono = " + telefono);
						if (exit > 0)
							System.out.println("Rimosso");
						else
							System.out.println("Errore");
						break;
					}
					case 12: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						System.out.format("Nome: ");
						String nome = input.next();
						System.out.format("PartitaIVA: ");
						String partitaIVA = input.next();
						exit = st.executeUpdate("insert into fornitore(telefono, nome, `partita IVA`) values ("
								+ telefono + ", '" + nome + "', '" + partitaIVA + "')");
						if (exit > 0)
							System.out.println("Aggiunto");
						else
							System.out.println("Errore");
						break;
					}
					case 13: {
						System.out.format("Telefono: ");
						int telefono = input.nextInt();
						exit = st.executeUpdate("delete from fornitore where telefono = " + telefono);
						if (exit > 0)
							System.out.println("Rimosso");
						else
							System.out.println("Errore");
						break;
					}
					case 14: {
						System.out.format("Codice: ");
						int codice = input.nextInt();
						System.out.format("Nome: ");
						String nome = input.next();
						System.out.format("Prezzo: ");
						double prezzo = input.nextDouble();
						System.out.format("Data acquisto: ");
						String dataS = input.next();
						System.out.format("Telefono magazzino: ");
						int telefono = input.nextInt();
						System.out.format("Telefono fornitore: ");
						int telefonoP = input.nextInt();
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date dateT = formatter.parse(dataS);
						java.sql.Date data = new java.sql.Date(dateT.getTime());
						java.sql.PreparedStatement st2 = con.prepareStatement(
								"insert into prodotto(codice, nome, prezzo, `data acquisto`, `telefono magazzino`) values (?, ?, ?, ?, ?)");
						st2.setInt(1, codice);
						st2.setString(2, nome);
						st2.setDouble(3, prezzo);
						st2.setDate(4, data);
						st2.setInt(5, telefono);
						st2.execute();

						st.executeUpdate("insert into `e fornito`(`codice prodotto`, `telefono fornitore`) values ("
								+ codice + ", " + telefonoP + ")");

						System.out.println("Aggiunto");
						break;
					}
					case 15: {
						System.out.format("Codice: ");
						int codice = input.nextInt();
						exit = st.executeUpdate("delete from prodotto where codice = " + codice);
						if (exit > 0)
							System.out.println("Rimosso");
						else
							System.out.println("Errore");
						break;
					}
					case 16: {
						rs = st.executeQuery(
								"select * from prodotto where codice not in (select `codice prodotto` from usa)");
						String nome;
						int codice, telefono;
						Date data;
						double prezzo;
						while (rs.next()) {
							codice = rs.getInt(1);
							nome = rs.getString(2);
							prezzo = rs.getDouble(3);
							data = rs.getDate(4);
							telefono = rs.getInt(5);
							System.out.println(codice + " " + nome + " " + prezzo + " " + data + " " + telefono);
						}
						break;
					}
					case 17: {
						rs = st.executeQuery(
								"select * from personale where CF not in (select `CF personale` from telefono)");
						String cf, nome, cognome, professione, via, citta;
						double stipendio;
						int cap;
						while (rs.next()) {
							cf = rs.getString(1);
							nome = rs.getString(2);
							cognome = rs.getString(3);
							professione = rs.getString(4);
							stipendio = rs.getDouble(5);
							via = rs.getString(6);
							citta = rs.getString(7);
							cap = rs.getInt(8);
							System.out.println(cf + " " + nome + " " + cognome + " " + professione + " " + stipendio
									+ " " + via + " " + citta + " " + cap);
						}
						System.out.println("Stampato");
						break;
					}
					case 18: {
						rs = st.executeQuery(
								"select * from personale where CF not in (select `CF personale` from `ha personale`)");
						String cf, nome, cognome, professione, via, citta;
						double stipendio;
						int cap;
						while (rs.next()) {
							cf = rs.getString(1);
							nome = rs.getString(2);
							cognome = rs.getString(3);
							professione = rs.getString(4);
							stipendio = rs.getDouble(5);
							via = rs.getString(6);
							citta = rs.getString(7);
							cap = rs.getInt(8);
							System.out.println(cf + " " + nome + " " + cognome + " " + professione + " " + stipendio
									+ " " + via + " " + citta + " " + cap);
						}
						System.out.println("Stampato");
						break;
					}
					case 19: {
						rs = st.executeQuery("select codice, `#personale` from cantiere");
						int codice, nPersonale;
						while (rs.next()) {
							codice = rs.getInt(1);
							nPersonale = rs.getInt(2);
							System.out.println(codice + " " + nPersonale);
						}
						System.out.println("Stampato");
						break;
					}
					case 20: {
						System.out.format("Codice cantiere: ");
						int codice = input.nextInt();
						System.out.format("CF personale: ");
						String cf = input.next();

						exit = st.executeUpdate("insert into `ha personale`(`codice cantiere`, `CF personale`) values ("
								+ codice + ", '" + cf + "')");
						if (exit > 0) {
							rs = st.executeQuery("select `#personale` from cantiere where codice = " + codice);
							rs.next();
							int personaleN = rs.getInt(1);
							personaleN += 1;
							st.executeUpdate(
									"update cantiere set `#personale` = " + personaleN + " where codice = " + codice);
							System.out.println("Aggiunto");
						} else
							System.out.println("Errore");
						break;
					}
					case 21: {
						System.out.format("CF personale da modificare: ");
						String cfM = input.next();
						System.out.format("-Modifica dati-\nCF: ");
						String cf = input.next();
						System.out.format("Nome: ");
						String nome = input.next();
						System.out.format("Cognome: ");
						String cognome = input.next();
						System.out.format("Professione: ");
						String professione = input.next();
						System.out.format("Stipendio: ");
						double stipendio = input.nextDouble();
						System.out.format("Via: ");
						String via = input.next();
						System.out.format("Citta: ");
						String citta = input.next();
						System.out.format("CAP: ");
						int cap = input.nextInt();
						exit = st.executeUpdate("update personale set CF = '" + cf + "', nome = '" + nome
								+ "', cognome = '" + cognome + "', professione = '" + professione + "', stipendio = "
								+ stipendio + ", via = '" + via + "', citta = '" + citta + "', cap = " + cap
								+ " where cf = '" + cfM + "'");
						if (exit > 0)
							System.out.println("Aggiornato");
						else
							System.out.println("Errore");
						break;
					}
					case 22: {
						rs = st.executeQuery(
								"select * from cantiere C1 where C1.codice not in ( select H1.`codice cantiere` from personale P1, `ha personale` H1, cantiere C1 where P1.CF = H1.`CF personale` and C1.codice = H1.`codice cantiere` and exists (select * from personale P2 where P2.CF = P1.CF and C1.localita <> P2.citta))");
						String localita;
						double valore;
						int codice, nPersonale;
						while (rs.next()) {
							codice = rs.getInt(1);
							valore = rs.getDouble(2);
							localita = rs.getString(3);
							nPersonale = rs.getInt(4);
							System.out.println(codice + " " + " " + valore + " " + localita + " " + nPersonale);
						}
						System.out.println("Stampato");
						break;
					}
					case 23: {
						System.out.println("Formato numero non valido");
						break;
					}
					default: {
						System.out.println("Scelta non presente");
						break;
					}
					}
				} else
					break;
			}
			input.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws ParseException {

		ImpresaEdile impresaEdile = new ImpresaEdile();
		impresaEdile.start();

//		 impresaEdile.assumiPersonale();
//		 impresaEdile.licenziaPersonale();
//		 impresaEdile.aggiungiCliente();
//		 impresaEdile.creaCantiere();
//		 impresaEdile.rimuoviCantiere();
//		 impresaEdile.aggiungiPermesso();
//		 impresaEdile.rimuoviPermesso();
//		 impresaEdile.aggiungiMagazzino();
//		 impresaEdile.rimuoviMagazzino();
//		 impresaEdile.aggiungiEnteLocale();
//		 impresaEdile.rimuoviEnteLocale();
//		 impresaEdile.aggiungiFornitore();
//		 impresaEdile.rimuoviFornitore();
//		 impresaEdile.aggiungiProdotto();
//		 impresaEdile.rimuoviProdotto();
//		 impresaEdile.stampaProdottiNonUsatiInNessunCantiere();
//		 impresaEdile.stampaPersonaleSenzaTelefono();
//		 impresaEdile.stampaPersonaleNonImpiegato();
//		 impresaEdile.stampaNumeroPersonaleCantiere();
//		 impresaEdile.aggiungiPersonaleAlCantiere();
//		 impresaEdile.aggiornaPersonale();
//		 impresaEdile.stampaCantieriLocalitaUgualeTuttoPersonaleCitta();
	}

}
