package br.com.javamon.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ConvertBlobToStringTag extends SimpleTagSupport {

	private Blob blobObject;
	StringWriter sw = new StringWriter();
	
	public void setBlobObject(Blob blobObject) {
		this.blobObject = blobObject;
	}

	@Override
	public void doTag() throws JspException, IOException {
		
		JspWriter out = getJspContext().getOut();
		
		if(blobObject != null) {
			try {
				out.println(new String( blobObject.getBytes( 1 , (int) blobObject.length()), StandardCharsets.ISO_8859_1 ));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
