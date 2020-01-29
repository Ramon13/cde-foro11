	package br.com.javamon.action.auth;

import java.time.LocalDate;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.ApplicationHistory;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.CartService;
import br.com.javamon.service.LoginService;
import br.com.javamon.util.PermissionType;
import br.com.javamon.validation.RequestParameterValidation;

public class LoginAction extends AdminAction<FilterProperties>{

	public LoginAction() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		Login login;
		try {
			login = getServiceFactory().getService(LoginService.class).login(inputLogin());
			
			getRequest().getSession().setAttribute("login", login);
		} catch (ValidatorException e) {
			throw new ValidatorException("Usuário e/ou senha incorretos.");
		}	
		
		setCurrentDateInUserSession();
		
		dispatcherRequest(login);

	}

	private Login inputLogin() throws ValidatorException{
		String user = getRequest().getParameter("user");
		RequestParameterValidation.isEmpty(user);
		RequestParameterValidation.validateStringParam(user, 32);
		
		String pass = getRequest().getParameter("password");
		RequestParameterValidation.isEmpty(user);
		RequestParameterValidation.validateStringParam(user, 32);
		
		
		Login login = new Login();
		login.setUser(user);
		login.setPassword(pass);
		
		return login;
	}
	
	private void setCurrentDateInUserSession() {
		getRequest().getSession().setAttribute("startDate", LocalDate.of(LocalDate.now().getYear(), 1, 1));
		getRequest().getSession().setAttribute("currentDate", LocalDate.now());
		getRequest().getSession().setAttribute("lastYear", (LocalDate.now().getYear() - 1));
		getRequest().getSession().setAttribute("firstSystemYear", "2013");
		
	}
//	
//	private void create1stLogin() throws Exception{
//		if(getRequest().getServletContext().getAttribute("importExcelData").equals("true")) {
//			ExcelBackups.execute();
//			
//			Permission permission = DAOFactory.getInstance().getDAO(PermissionDAO.class).load(1L);
//			
//			Locale locale = new Locale();
//			locale.setDescription("defaultLocale");
//			serviceFactory.getService(LocaleService.class).saveLocale(locale);
//			
//			Login login = new Login();
//			login.setUser("admin");
//			login.setPassword("admin");
//			login.setConfirmationPassword("admin");
//			login.setPermission(permission);
//			login.setLocale(locale);
//			serviceFactory.getService(LoginService.class).save(login);
//			
//			
//		}
//		
//	}

//	private void setLoginCart(Login login){
//		Cart cart = new Cart();
//		cart.setId(login.getId());
//		cart = serviceFactory.getService(CartService.class).save(cart);
//		
//		login.setCart(cart);
//		serviceFactory.getService(LoginService.class).update(login);
//	}
	
	private void dispatcherRequest(Login login) throws Exception, ValidatorException {
		if(!login.getActive())
			throw new ValidatorException("Usuário bloqueado.");
		
		if(login.getResetPassword()){
			redirect("/login/reset_login.jsp");
			return;
		}
		
		if( login.getPermission().getDescription().equals( PermissionType.ADMIN.getValue() ) 
				|| login.getPermission().getDescription().equals( PermissionType.SUPER_ADMIN.getValue() )) {
			getRequest().getSession().setAttribute("history", new ApplicationHistory());
			redirect("/admin/jsp/home.jsp");
			
		}else
			if( login.getPermission().getDescription().equals( PermissionType.USER.getValue() ) ) {
				if(login.getCart() == null){
					Cart cart = new Cart();
					cart.setLogin(login);
					cart = getServiceFactory().getService(CartService.class).save(cart);
					
					login.setCart(cart);
					getServiceFactory().getService(LoginService.class).update(login);
				}
				
				redirect("/common/list_item.action");
			}
		
		return;
	}
}
