package br.com.javamon.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.javamon.format.NumberFormatter;


public class FormatNumberTag extends SimpleTagSupport {

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
		String number = sw.toString();
		
		String newNumber = NumberFormatter.toBrazilCurrencyPattern(number, new Locale(language, country));
		
		JspWriter out = getJspContext().getOut();
		out.println(newNumber);
	}
}
