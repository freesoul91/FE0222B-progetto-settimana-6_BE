package it.bancomat.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;


public class ContoCorrenteDAO {
	
	private Connection conn;
	
	public ContoCorrenteDAO() {
	this.conn = ConnectionFactory.getConnection();
	 }
	
	public void closeConnection() {
		try {conn.close();} catch (Exception ex) {}
		}
	
	private static final String SELECT_CONTO = "SELECT * FROM contocorrente WHERE id_conto = ? ";

	public ContoCorrente getSaldo(int id_conto) {
		//Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContoCorrente contoCorrente = null;

		try {
			ps = conn.prepareStatement(SELECT_CONTO);
			ps.setInt(1, id_conto);
			rs = ps.executeQuery();

			if (rs.next()) {
				contoCorrente = new ContoCorrente();
				contoCorrente.setId_conto(rs.getInt("id_conto"));
				contoCorrente.setIntestatario(rs.getString("intestatario"));
				contoCorrente.setSaldo(rs.getFloat("saldo"));

			}

		} catch (SQLException ex) {
			System.out.println("Errore lettura dati per id e intestatario correntista");
			ex.printStackTrace();
		}
		try {ps.close();} catch (Exception ex) {}
		try {rs.close();} catch (Exception ex) {}
		//try {conn.close();} catch (Exception ex) {}
		System.out.println("Lettura dati conto avvenuta");
		
		return contoCorrente;
	}

	public boolean prelievo(int id_conto, float prelievo) {
		//Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement ps = null;
		ContoCorrente cc = this.getSaldo(id_conto);
		Float nSaldo = cc.getSaldo() - prelievo;

		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE id_conto = ?");
			ps.setFloat(1, nSaldo);
			ps.setInt(2, id_conto);
			ps.executeUpdate();
									
		} catch (SQLException ex) {
			System.out.println("Errore operazione di prelievo");
			ex.printStackTrace();
			return false;
		}
		//try {conn.close();} catch (Exception ex) {}		
		try {ps.close();} catch (Exception e) {}
		return true;
	
	}
	
	

	public boolean deposita(int id_conto, float deposito) {
		//Connection conn = ConnectionFactory.getConnection();
		
		ContoCorrente cc = this.getSaldo(id_conto);
		Float nSaldo = cc.getSaldo() + deposito;
		
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE contocorrente SET saldo = ? WHERE id_conto = ?");
			ps.setFloat(1, nSaldo);
			ps.setInt(2, id_conto);
			ps.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println("Errore operazione di deposito");
			ex.printStackTrace();
			return false;
		}
		//try {conn.close();} catch (Exception ex) {}
		try {ps.close();} catch(SQLException e) {e.printStackTrace();}
		return true;
	}
	
}
