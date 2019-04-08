package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	
	public static Date obterDataComDiferencaDias(int dias) {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, dias);
		return calendario.getTime();
	}
	
	public static String obterDataFormatada(Date data) {
		DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		return format.format(data);
	}
}
