package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe utilit�ria para opera��es com data
 * 
 * @author Rodrigo Oliveira Tavares
 *
 */
public class DateUtil {

	/**
	 * Soma a quantidade de dias em uma determinada data
	 * 
	 * @param data
	 * @param dias
	 * @return Date
	 */
	public static Date somarDiasData(Date data, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, dias);
		return c.getTime();
	}

	/**
	 * Soma a quantidade de meses em uma determinada data
	 * 
	 * @param data
	 * @param meses
	 * @return Date
	 */
	public static Date somarMesesData(Date data, int meses) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.MONTH, meses);
		return c.getTime();
	}

	/**
	 * Soma a quantidade de anos em uma determinada data
	 * 
	 * @param data
	 * @param ano
	 * @return
	 */
	public static Date somarAnoData(Date data, int ano) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.YEAR, ano);
		return c.getTime();
	}

	/**
	 *  Diferen�a em dias entre duas datas.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */	
	public static long obterDiferencaEntreDuasDatasEmDias(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}	
	
	/**
	 * Formata a data informada para o padr�o brasileiro ou seja dd/MM/yyyy
	 * @param data
	 * @return
	 */
	public static String formataDataPadraoBrasileiro(Date data){
		String formato = "dd/MM/yyyy";
		
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        
        return formatador.format(data);
	}

	/**
	 * Formata a data informada para o padrao brasileiro ou seja dd/MM/yyyy
	 * 
	 * @param data
	 * @return
	 */
	public static String formataDataPadraoBrasileiro(LocalDateTime data) {
		String formato = "dd/MM/yyyy";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

		return data.format(formatter);
	}
	
	/**
	 * Formata a data informada para o padr�o brasileiro sem barras ou seja ddMMyyyy
	 * 
	 * @param data
	 * @return
	 */
	public static String formataDataPadraoBrasileiroSemBarras(Date data) {
		String formato = "ddMMyyyy";

		SimpleDateFormat formatador = new SimpleDateFormat(formato);

		return formatador.format(data);
	}
	
	/**
	 * Formata a data informada para o padr�o brasileiro com hora minuto e segundo ou seja dd/MM/yyyy HH:mm:ss
	 * @param data
	 * @return
	 */
	public static String formataDataPadraoBrasileiroHoraMinutoSegundo(Date data){
		String formato = "dd/MM/yyyy HH:mm:ss";
		
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        
        return formatador.format(data);
	}	
	
	public static String formataDataPadraoAmericano(Date data){
		
		String formato = "yyyyMMdd";

		SimpleDateFormat formatador = new SimpleDateFormat(formato);

		return formatador.format(data);
	}

	/**
	 * Compara a primeira data com a segunda data, caso a primeira data seja anterior a segunda retorna -1,
	 * caso a primeira data seja posterior a segunda data retorna 1, caso as datas sejam iguais retorna 0
	 * @param primeiraData a data a ser comparada
	 * @param segundaData a data que ser� comparada em rela��o a primeira
	 * @return -1 caso a primeira data seja anterior a segunda, 0 caso as datas sejam iguais, 1 caso a primeira data seja posterior a segunda data
	 * @throws ParseException 
	 */
	public static int comparaDatas(Date primeiraData, Date segundaData) throws ParseException{
		
		// CONVERS�O FEITA PARA RESOLVER O PROBLEMA DE COMPARA��O DE DATAS IGUAIS ONDE O TIME ESTAVA FICANDO DIFERENTE E N�O RETORNAVA 0
		String strData1 = new SimpleDateFormat("dd/MM/yyyy").format(primeiraData);
		String strData2 = new SimpleDateFormat("dd/MM/yyyy").format(segundaData);
		Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(strData1);
		Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(strData2);

		Calendar cPrimeiraData = Calendar.getInstance();
		cPrimeiraData.setTime(data1);

		Calendar cSegundaData = Calendar.getInstance();
		cSegundaData.setTime(data2);
		// cPrimeiraData vem antes da cSegundaData
		if (cPrimeiraData.before(cSegundaData)) {
			return -1;
		} else if (cPrimeiraData.after(cSegundaData)) {
			return 1;
		}

		return 0;

	}

	/**
     * M�todo para comparar as datas e retornar o numero de dias de diferen�a entre elas
     *
     * Compare two date and return the difference between them in days.
     *
     * @param dataLow The lowest date
     * @param dataHigh The highest date
     *
     * @return int
     */
    public static int getDiasDaDiferencaEntreDuasDatas(java.util.Date dataLow, java.util.Date dataHigh){
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();
        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();
        startTime.setTime(dataLow);
        endTime.setTime(dataHigh);
        int dif_multiplier = 1;
        // Verifica a ordem de inicio das datas
        if( dataLow.compareTo( dataHigh ) < 0 ){
            baseTime.setTime(dataHigh);
            curTime.setTime(dataLow);
            dif_multiplier = 1;
        }else{
            baseTime.setTime(dataLow);
            curTime.setTime(dataHigh);
            dif_multiplier = -1;
        }
        int result_years = 0;
        int result_months = 0;
        int result_days = 0;
        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando
        // no total de dias. Ja leva em consideracao ano bissesto
        while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||
               curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )
        {
            int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );
            result_months += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);
        }
        // Marca que � um saldo negativo ou positivo
        result_months = result_months*dif_multiplier;
        // Retirna a diferenca de dias do total dos meses
        result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));
        return result_years+result_months+result_days;
    }
	/**
	 * Retorna a data no formato por extenso "dd 'de' MMMM 'de' yyyy"
	 * 
	 * @param date
	 * @return
	 */
	public static String formataPorExtenso(Date date) {
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		String dataPorExtenso = dateFormat.format(date);
		return dataPorExtenso;
	}

	public static String formataPorExtensoComHora(Date date) {
		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy '�s' HH:mm:ss", local);
		String dataPorExtenso = dateFormat.format(date);
		return dataPorExtenso;
	}

	/**
	 * M�dia de dias
	 * 
	 * @param dias
	 * @param quantidade
	 * @return Double
	 */
	public static Double mediaDias(Double dias, Long quantidade) {
		if (dias == null || quantidade == null) {
			return new Double("0");
		} else {
			return dias / quantidade;
		}
	}

	/**
	 * @author P613197 Guilherme Costa lopes
	 * 
	 *         N�mero de dias entre data
	 * 
	 * @param primeiraData
	 * @param segundaData
	 * @return String
	 */
	public static String numeroDiasEntreData(Date primeiraData, Date segundaData) throws ParseException {
		if (comparaDatas(primeiraData, segundaData) == 1) {
			return "0";
		}
		Long dt = ((segundaData.getTime() - primeiraData.getTime()) + 3600000) / 86400000L;
		return dt.toString();
	}

	/**
	 * Converte o Sequencial do m�s na Descri��o do M�s por Extenso 
	 * @param mes - Valor do M�s com formato Integer
	 * @return M�s por Extenso
	 */
	public static String mesPorExtenso(Integer mes) {
		switch (mes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Mar�o";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return "";
		}
	}

	/**
	 * M�todo para validar se uma determinada data � do Ano Corrente.
	 * 
	 * @param data
	 * @return Verdadeiro se a data � do ano corrente.
	 * @author p059734 - Douglas Hermes
	 */
	public static boolean isAnoAtual(Date data) {
		Calendar atual = GregorianCalendar.getInstance();
		Calendar paramData = new GregorianCalendar();
		paramData.setTime(data);
		return atual.get(Calendar.YEAR) == paramData.get(Calendar.YEAR);
	}

	public static long obterDiferencaEntreDuasDatasEmDiasIgnorandoHora(Date dataInicio, Date dataFim) throws ParseException {

		String dataInicioString = new SimpleDateFormat("dd/MM/yyyy").format(dataInicio);
		String dataFimString = new SimpleDateFormat("dd/MM/yyyy").format(dataFim);

		Date dataInicioFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioString);
		Date dataFimFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimString);
		
	    long diff = dataFimFormatada.getTime() - dataInicioFormatada.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static int getAnoCorrente(){
		return Calendar.getInstance().get(Calendar.YEAR) ;
	}
	
	public static Long getAnoCorrenteLong(){
		SimpleDateFormat anoCorrente = new SimpleDateFormat("yyyy");
		return Long.parseLong(anoCorrente.format(new Date()));
	}
	
	/**
	 * Retorna os anos entre o periodo de uma data com adi��o dos dias 
	 * @param date data incial
	 * @param dias dias a serem adicionada na data inicial
	 * @return uma lista com os ano(s) do per�odo
	 */
	public static List<Integer> retornaAnosEntrePeriodo(Date date, int dias){
		List<Integer> retorno = new ArrayList<Integer>();
		Calendar dataParametro = Calendar.getInstance();
		dataParametro.setTime(date);
		Calendar dataAdicionada = Calendar.getInstance();		
		dataAdicionada.setTime(somarDiasData(date, dias));
		for (Integer ano = dataParametro.get(Calendar.YEAR); ano <= dataAdicionada.get(Calendar.YEAR); ano++){
			retorno.add(ano);
		}
		return retorno;
	}
	
	/**
	 * Retorna o m�s referente a data passada por par�metro
	 * @param data
	 * @return
	 */
	public static int getMesData(Date data){
		Calendar dataParametro = Calendar.getInstance();
		dataParametro.setTime(data);
		return dataParametro.get(Calendar.MONTH)+1;		
	}	
	
	/**
	 * Retorna o ano referente a data passada por par�metro
	 * @param data
	 * @return
	 */
	public static int getAnoData(Date data){
		Calendar dataParametro = Calendar.getInstance();
		dataParametro.setTime(data);
		return dataParametro.get(Calendar.YEAR) ;		
	}
	
	/**
	 * Recupera em Integer o m�s corrente
	 * @return
	 */
	public static int getMesCorrente(){
		return Calendar.getInstance().get(Calendar.MONTH)+1 ;
	}	
	
	/**
	 * transforma uam data do tipo XMLGregorianCalendar para Date 
	 * @param xmlDate
	 * @return
	 */
	public static Date convertXMLGregorianDateToDate(XMLGregorianCalendar xmlDate){
		if (xmlDate != null) {
			return xmlDate.toGregorianCalendar().getTime();
		} 
		return null;
	}
	
	/**
	 * Transforma o objeto Date passado por par�metro em XMLGregorianCalendar
	 * @param data
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar convertDateToXMLGregorianDate(Date data) throws DatatypeConfigurationException {
		if (data != null) {
		    XMLGregorianCalendar xmlDate = null;
		    GregorianCalendar gc = new GregorianCalendar();
		    gc.setTime(data);
		    return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		}
		return null;
	}
	
	/**
	 * Transforma a String passada em Date para isto ocorrer da maneira correta a data deve estar no formato dd/mm/aaaa
	 * @param dataString
	 * @return
	 */
	public static Date retornarDataPassandoString(String dataString) {
		Locale local = new Locale("pt","BR");
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy", local);
		formatoData.setLenient(false);
		Date date = null;
		try {
			date = (Date) formatoData.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date stringParaDate(String padrao, String s) {
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat(padrao);
			date = (java.util.Date) formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateParaString(String padrao, Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat(padrao);
		return formatador.format(data);
	}

	/**
	 * Valida se n�o existe nenhuma data nula
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean validaDataNaoNula(Date... data) {
		for (Date dt : data) {
			if (dt == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Recupera o primeiro dia do ano da data informada
	 * 
	 * @param data
	 * @return Date
	 */
	public static Date primeiroDiaDoAno(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, recuperaAno(data));
		cal.set(Calendar.DAY_OF_YEAR, 1);
		Date start = cal.getTime();

		return start;
	}

	/**
	 * Recupera o �ltimo dia do ano da data informada
	 * 
	 * @param data
	 * @return Date
	 */
	public static Date ultimoDiaDoAno(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, recuperaAno(data));
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.MONTH, 11); // 11 = december
		Date start = cal.getTime();

		return start;
	}

	/**
	 * Recupera o ano da data informada
	 * 
	 * @param data
	 * @return int
	 */
	public static int recuperaAno(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(Calendar.YEAR);
	}

	/**
	 * Aumenta um dia na data informada
	 * 
	 * @param data
	 * @return Date
	 */
	public static Date aumentaUmDiaNaData(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		Date start = calendar.getTime();

		return start;
	}
	
	public static int recuperaMes(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(Calendar.MONTH);
	}
	
	public static Long mesExtensoPorNumero(String mes) {
		switch (mes) {
		case "Janeiro":
			return 1L;
		case "Fevereiro":
			return 2L;
		case "Mar�o":
			return 3L;
		case "Abril":
			return 4L;
		case "Maio":
			return 5L;
		case "Junho":
			return 6L;
		case "Julho":
			return 7L;
		case "Agosto":
			return 8L;
		case "Setembro":
			return 9L;
		case "Outubro":
			return 10L;
		case "Novembro":
			return 11L;
		case "Dezembro":
			return 12L;
		default:
			return 0L;
		}
	}


}
