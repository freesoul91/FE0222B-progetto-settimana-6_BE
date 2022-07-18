<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bancomat Virtuale</title>
</head>
<body style="text-align: center;">
	
	 <!-- Intestazione -->
<jsp:include page="/WEB-INF/templates/intestazione.jsp"></jsp:include>
 <h1>Bancomat Virtuale</h1>
	<p><b>Immetti l'ID conto numerico per visualizzare il saldo oppure effettuare altre operazioni. Sono presenti gli ID dei conti da 1 a 9 sul db!</b></p>
	<p style= "h1 color:black"></p>
  	<br>
  <form method="POST" action="/BancomatVirtuale/dispatcher">
	Numero Conto: <input type ="text" name="id_conto"><br>
	<br>
	Importo in Euro: <input type ="text" step="0.01" name="importo"><br><br>
 		
  <label >Operazione:</label>
  
  <select name="operazione" id="operazione" required>
  <option  value="saldo">Vedi Saldo del Conto</option>
  <option  value="deposita">Deposita sul Conto</option>
  <option  value="prelievo">Preleva dal Conto</option>
   </select>
   <br>
    <br>
   <input type="submit" value="CONFERMA"/>
</form>
<br>
<h6 style="color: red;">P.S.: Ho creato una delle pagine più brutte della storia delle pagine, scusate!</h6>
<!-- Pie Pagina -->
<jsp:include page="/WEB-INF/templates/piepagina.jsp"></jsp:include>

</body>
</html>