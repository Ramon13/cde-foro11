package br.com.javamon.service;

public class ItemServiceTest {

	@SuppressWarnings("unused")
	private ItemService itemService;
	
	public ItemServiceTest() {
		itemService = new  ItemService();
	}
	
	public static void main(String[] args) throws Exception {
		char[] s = new char[6];
		for(int i = 0 ; i < s.length ; i++){
			int randNum = (int) (Math.random() * (122 - 48 + 1) ) + 48;
			if(randNum > 57 && randNum < 65 
					|| randNum > 90 && randNum < 97){
				randNum = (int) Math.random() * (90 - 65 + 1) + 65;
			}
			s[i] = (char) randNum;
		}
		System.out.println(s);
	}
	
	public void countNumOfItensTest() throws Exception{
	}
	
	
	
	
	
	
}
