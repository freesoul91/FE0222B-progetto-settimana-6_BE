package it.bancomat.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it.bancomat.business.BancomatEjb;
import it.bancomat.data.ContoCorrenteDAO;


@WebServlet("/dispatcher")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	BancomatEjb bankService;
    
    public Dispatcher() {}
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ContoCorrenteDAO ccDao = new ContoCorrenteDAO();
		bankService.setContoCorrenteDao(ccDao);
		String operazione = request.getParameter("operazione");
		
		
	try { Integer.parseInt(request.getParameter("id_conto"));
		        	
		if ( ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))) != null &&  ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getId_conto() == Integer.parseInt(request.getParameter("id_conto")) ) {
		
		if ( operazione.equalsIgnoreCase("saldo") ) {
			bankService.controllaOperazione("saldo", ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getId_conto() , 0);
			System.out.println("SALDO");
			out.println("<h1 style=\"text-align: center;\"> **** SALDO **** </h1>");
			out.println("<h2 style=\"text-align: center;\"> / Intestatario: " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getIntestatario() + " / Danaro:  " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getSaldo() + " Euro!" + " </h2>");
			
			
		out.println("<p style=\"text-align: center;\"><a href=\"index.jsp\">Torna alla Home</a></p>");
			
		}
				
		else if ( operazione.equalsIgnoreCase("prelievo")  ) {
			Float importoCorrettoSenzaVirgola = Float.parseFloat(request.getParameter("importo").replace(',', '.'));
			boolean operazionePrelievo = bankService.controllaOperazione("prelievo", ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getId_conto(), importoCorrettoSenzaVirgola);
			
			System.out.println("PRELIEVO");
			out.println("<h1 style=\"text-align: center;\"> **** PRELIEVO **** </h1>");
			out.println("<p style=\"text-align: center;\"> Soldi prelevati: " + importoCorrettoSenzaVirgola + " </p>");
			out.println("<h2 style=\"text-align: center;\"> / Intestatario: " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getIntestatario() + " / Danaro:  " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getSaldo() + " Euro!" + " </h2>");
			
			if( operazionePrelievo == false ) {
				out.println("<br><b><p style=\"text-align: center;\"> <font color=\"red\">Non hai abbastanza DANARO per PRELEVARE!!!</font> </p></b><br>");
			}
				if ( importoCorrettoSenzaVirgola < 0) {
					out.println("<h3 style=\"text-align: center;\"><font color=\"red\">L'operazione non è andata a buon fine, inserire un importo valido senza il segno davanti all'importo!</font></h3>");
			}
				
			out.println("<p style=\"text-align: center;\"><a href=\"index.jsp\">Torna alla Home</a></p>");
		}
		else if ( operazione.equalsIgnoreCase("deposita") ) {
			Float importoCorrettoSenzaVirgola = Float.parseFloat(request.getParameter("importo").replace(',', '.'));
			bankService.controllaOperazione("deposita", ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getId_conto(), importoCorrettoSenzaVirgola);
			
			System.out.println("DEPOSITA");
			out.println("<h1 style=\"text-align: center;\"> **** DEPOSITO **** </h1>");
			out.println("<p style=\"text-align: center;\"> Soldi depositati: " + importoCorrettoSenzaVirgola + " </p>");
			out.println("<h2 style=\"text-align: center;\"> / Intestatario: " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getIntestatario() + " / Danaro:  " + ccDao.getSaldo(Integer.parseInt(request.getParameter("id_conto"))).getSaldo() + " Euro!" + " </h2>");
				if ( importoCorrettoSenzaVirgola < 0 ) {
				out.println("<h3 style=\"text-align: center;\"><font color=\"red\">L'operazione non è andata a buon fine, inserire un importo valido senza il segno davanti all'importo!</font></h3>");
			}
			
			out.println("<p style=\"text-align: center;\"><a href=\"index.jsp\">Torna alla Home</a></p>");
			}
				
			}
			else {
				out.println("<h3 style=\"text-align: center;\"><font color=\"red\">L'operazione non è andata a buon fine, inserire ID Conto esistente!</font></h3><br><br>");
				out.println("<p style=\"text-align: center;\"><a href=\"index.jsp\">Torna alla Home</a></p>");
			}
		    
			} catch (Exception ex) {
		    	out.println("<h1 style=\"text-align: center;\">NON HAI INSERITO UN ID CONTO NUMERICO! ** OPPURE HAI IMMESSO NEL CAMPO \"IMPORTO IN EURO\" DEI CARATTERI NON VALIDI! RIPROVA!</h1><br><br>");
		    	out.println("<h2 style=\"text-align: center;\"><font color=\"red\"> -> ERRORE INVALID CHARS IN TEXT FIELD <- </font></h2>");
		    	out.println("<p style=\"text-align: center;\"><a href=\"index.jsp\">Torna alla Home</a></p>");
		    	ex.printStackTrace();
		        
				}
		
			}
	
		}
