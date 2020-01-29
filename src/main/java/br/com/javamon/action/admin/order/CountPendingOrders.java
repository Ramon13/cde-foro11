package br.com.javamon.action.admin.order;

import java.io.PrintWriter;

import br.com.javamon.action.Action;
import br.com.javamon.service.OrderService;

public class CountPendingOrders extends Action {

	@Override
	public void process() throws Exception {
		Long pendingOrders = getServiceFactory().getService(OrderService.class).getPendingOrdersAmount();
		PrintWriter pw = new PrintWriter(getResponse().getOutputStream());
		pw.write( String.valueOf(pendingOrders));
		
		pw.close();
	}

}
