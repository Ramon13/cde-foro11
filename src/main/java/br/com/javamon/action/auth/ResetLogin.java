package br.com.javamon.action.auth;

import br.com.javamon.action.admin.AdminAction;
import br.com.javamon.admin.domain.ApplicationHistory;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.entity.Cart;
import br.com.javamon.entity.Login;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.exception.ValidatorException;
import br.com.javamon.service.CartService;
import br.com.javamon.service.LoginService;
import br.com.javamon.util.PermissionType;
import br.com.javamon.validation.LoginValidation;
import br.com.javamon.validation.RequestParameterValidation;

public class ResetLogin extends AdminAction<FilterProperties>{

	public ResetLogin() {
		super(FilterProperties.class);
	}

	@Override
	protected void processAction() throws Exception {
		reset();
	}
	
	public void reset() throws ValidatorException, ServiceException, Exception{
		String strUser = getRequest().getParameter("user");
		String strPassword = getRequest().getParameter("password");
		
		if (!RequestParameterValidation.validateStringParam(strUser, 32)
				&& !RequestParameterValidation.validateStringParam(strPassword, 16)
				&& LoginValidation.isLoginDescriptionExists(strUser)){
			
			Login login = getServiceFactory().getService(LoginService.class).getLoginByDescription(strUser);
			
			if (login.getResetPassword()){
				login.setPassword(strPassword);
				login.setResetPassword(false);
			}

			dispatcherRequest(login);
		}
		
	}
	
	private void dispatcherRequest(Login login) throws Exception, ValidatorException {
		if(!login.getActive())
			throw new ValidatorException("Usuário bloqueado.");
		
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
					
				}

				getServiceFactory().getService(LoginService.class).update(login);
				redirect("/common/list_item.action");
			}
	}

}
