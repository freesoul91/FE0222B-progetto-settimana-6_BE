package it.bancomat.data;

public class ContoCorrente {
	
	private int id_conto;
	private String intestatario;
	private float saldo;
	
	public int getId_conto() {
		return id_conto;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setId_conto(int id_conto) {
		this.id_conto = id_conto;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	

}
