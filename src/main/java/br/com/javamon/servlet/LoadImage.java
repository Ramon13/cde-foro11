package br.com.javamon.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/resources/LoadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadImage extends HttpServlet{

	private void setImagesPathInContext(ServletContext context) throws ServletException{
		try(InputStream in = this.getClass().getResourceAsStream("/app-config.properties")){
			Properties appConfig = new Properties();
			appConfig.load(in);
			context.setAttribute("imgPath", appConfig.getProperty("imgLinuxPath"));
		}catch(Exception e){
			throw new ServletException();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strImageId = req.getParameter("imageId");
		String strItemId = req.getParameter("itemId");
		
		if(req.getServletContext().getAttribute("imgPath") == null){
			setImagesPathInContext(req.getServletContext());
		}
		
		if(strImageId != null && strItemId != null){
			Long imageId = Long.parseLong(strImageId);
			Long itemId = Long.parseLong(strItemId);
					
			String imagePath = req.getServletContext().getAttribute("imgPath") + "/" + itemId + "/" + imageId;
			
			if(imagePath != null) {
				InputStream in = new FileInputStream(new File(imagePath));
					resp.setContentType("image/*");
		            ServletOutputStream sout = resp.getOutputStream();
		            	
		                byte[] buffer = new byte[20000];
		                int bytesRead;

		                resp.setContentType("image/png");

		                while ((bytesRead = in.read(buffer)) != -1) {

		                    sout.write(buffer, 0, bytesRead);
		                }
		                in.close();
		                return;
			}
		}
	}
}
