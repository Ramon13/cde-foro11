package br.com.javamon.util;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.javamon.dao.DAOFactory;
import br.com.javamon.dao.DocumentDAO;
import br.com.javamon.dao.EntryDAO;
import br.com.javamon.dao.EntryItemDAO;
import br.com.javamon.entity.Document;
import br.com.javamon.entity.Entry;
import br.com.javamon.entity.EntryItem;
import br.com.javamon.service.DocumentService;
import br.com.javamon.service.EntryService;
import br.com.javamon.service.ServiceFactory;

public class ExcelBackups {
	
	
	private static void createEntryItens() throws Exception{
		ServiceFactory sf = ServiceFactory.getInstance();
		List<Document> list = sf.getService(DocumentService.class).list();
		EntryService entrySvc = sf.getService(EntryService.class);
		Set<String> documents = new TreeSet<>();
		
		for(Document d : list){
			if(d.getNumber() != null){
				documents.add(d.getNumber());
			}else{
				System.out.println(d.getId());
				if(d.getId() == 1147){
					d.setNumber("000");
					DAOFactory.getInstance().getDAO(DocumentDAO.class).save(d);
				}
				if(d.getId() == 1146){
					d.setNumber("sem número");
					DAOFactory.getInstance().getDAO(DocumentDAO.class).save(d);
				}
			}	
		}
		
		int count = 0;
		for(String documentNumber : documents){
			
			Document doc = new Document();
			doc.setNumber(documentNumber);
			DAOFactory.getInstance().getDAO(DocumentDAO.class).save(doc);
			
			
			List<Entry> entries = entrySvc.listEntriesByDocument(documentNumber);
			Entry entry = new Entry();
			entry.setAmount(0L);
			entry.setDate(LocalDate.now());
			entry.setUnityValue(0D);
			entry.setTotal(0D);
			entry.setDocument(doc);
			DAOFactory.getInstance().getDAO(EntryDAO.class).save(entry);
			
			Set<EntryItem> entryItens = new HashSet<>();
			
			
			if(entries.size() > 0 ){
				entry.setDate(entries.get(0).getDate().plusDays(1));
				
				entry.setProvider(entries.get(0).getProvider());
				
				for(Entry e : entries){
					if(e.getAmount() > 0){
						EntryItem ei = new EntryItem();
						ei.setAmount(e.getAmount());
						ei.setItem(e.getItem());
						ei.setTotal(e.getTotal());
						ei.setUnityValue(e.getUnityValue());
						ei.setEntry(entry);
						DAOFactory.getInstance().getDAO(EntryItemDAO.class).save(ei);
						
						System.out.println(ei);
						entryItens.add(ei);
						count++;
					}
				}
				
				//System.out.println(entry.getDate().plusDays(1));
				entry.setEntryItens(entryItens);
				DAOFactory.getInstance().getDAO(EntryDAO.class).save(entry);
			}
		}
		System.out.println(count);
	}
	
	private static void deleteEntries() throws Exception{
		List<Entry> eList = DAOFactory.getInstance().getDAO(EntryDAO.class).list();

		for(Entry e : eList){
			if(e.getEntryItens().isEmpty()){
				DAOFactory.getInstance().getDAO(EntryDAO.class).delete(e);

			}
				
		}
	}
	
	public static void main(String[] args) {
		try {

			HibernateUtil.beginTransaction();
			
			//createEntryItens();
			deleteEntries();
			HibernateUtil.commitTransaction();
			
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		
		System.exit(0);
	}
}
