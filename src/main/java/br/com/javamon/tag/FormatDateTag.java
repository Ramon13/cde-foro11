package br.com.javamon.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.javamon.convert.DateConvert;
import br.com.javamon.exception.ConvertException;

public class FormatDateTag extends SimpleTagSupport {

	private String language;
	private String country;
	StringWriter sw = new StringWriter();
	
	public void setLanguage( String language ) {
		this.language = language;
	}
	
	public void setCountry( String country ) {
		this.country = country;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		getJspBody().invoke(sw);
		String date = sw.toString();
		String newDate;
		try {
			newDate = DateConvert.parseLocalDateToString(LocalDate.parse(date), "dd/MM/yyyy", new Locale(language, country));
			JspWriter out = getJspContext().getOut();
			out.println(newDate);
		} catch (ConvertException e) {
			e.printStackTrace();
		}
	}
}
