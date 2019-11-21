package br.com.javamon.action.admin.item;

import br.com.javamon.action.Action;

public class ListItens extends Action {

	@Override
	public void process() throws Exception {
		System.out.println(this.getClass().getCanonicalName());
	}

}
