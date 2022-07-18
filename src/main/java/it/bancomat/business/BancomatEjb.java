package it.bancomat.business;

import it.bancomat.data.ContoCorrente;
import it.bancomat.data.ContoCorrenteDAO;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;


@Stateless
@LocalBean
public class BancomatEjb {

    private ContoCorrenteDAO contoCorrenteDao;
	
    public BancomatEjb() {
       
    }
    
    public boolean controllaOperazione(String operazione, int id_conto, float quantita) { 		
    		boolean operazioneCorretta = true;
    		boolean operazioneErrata = false;
    		ContoCorrente cc = contoCorrenteDao.getSaldo(id_conto);
    	
    	 /* if(operazione.equalsIgnoreCase("saldo") ) {
    		  contoCorrenteDao.getSaldo(id_conto);
    		  return operazioneCorretta;
    	  }*/
    	  //else 
    		  if (operazione.equalsIgnoreCase("prelievo")  && quantita >= 0 && cc.getSaldo() >= quantita ) {
    		  //contoCorrenteDao.prelievo(id_conto, quantita);
    		  return operazioneCorretta;
    	  }
    	  else if (operazione.equalsIgnoreCase("deposita") && quantita >= 0 ) {
    		  //contoCorrenteDao.deposita(id_conto, quantita);
    		  return operazioneCorretta;
    	  }
    	  else
    		  return operazioneErrata;
   }
   
    
    public ContoCorrenteDAO getContoCorrenteDao() {
    	return contoCorrenteDao;
    }
    
    public void setContoCorrenteDao(ContoCorrenteDAO contoCorrenteDao) {
    	this.contoCorrenteDao = contoCorrenteDao;
    }
}
