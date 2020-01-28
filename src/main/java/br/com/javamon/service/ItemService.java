package br.com.javamon.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.javamon.action.admin.locale.LocaleService;
import br.com.javamon.admin.domain.FilterProperties;
import br.com.javamon.admin.domain.ItemPaginate;
import br.com.javamon.admin.domain.PaginationProperties;
import br.com.javamon.convert.DateConvert;
import br.com.javamon.dao.DAOFactory;
import br.com.javamon.dao.ItemDAO;
import br.com.javamon.entity.Item;
import br.com.javamon.entity.ItemType;
import br.com.javamon.entity.Locale;
import br.com.javamon.exception.ConvertException;
import br.com.javamon.exception.DAOException;
import br.com.javamon.exception.ServiceException;
import br.com.javamon.util.SortProperty;

/**
 * This class provides public methods to process item operations
 * @author ramon
 *@version 1.0
 */
public class ItemService extends Service{

	private ItemDAO itemDAO;
	private final double MAX_ITENS = 35.0;
	
	public ItemService(){
		try {
			itemDAO = DAOFactory.getInstance().getDAO(ItemDAO.class);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Item> listItens() throws ServiceException{
		try {
			return itemDAO.listItem();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Item> listItens(PaginationProperties paginationProp) throws ServiceException{
		try {
			return itemDAO.listItem(paginationProp.getFirstValue() - 1, paginationProp.MAX_NUM_OF_ITENS);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Item> listItens(PaginationProperties paginationProp, FilterProperties filterProperties) throws ServiceException{
		try {
			return itemDAO.listItem(paginationProp, filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long countNumOfItens(FilterProperties filterProperties)throws ServiceException{
		try {
			return itemDAO.countNumItens(filterProperties);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public Long getNumOfPages(Long numOfItens, PaginationProperties paginationProps) throws ServiceException{
		return numOfItens / paginationProps.MAX_NUM_OF_ITENS;
	}
	
	public Date convertLocalDateToDate(LocalDate localDate) throws ServiceException{ 
		try {
			return DateConvert.localDateToDate(localDate);
		} catch (ConvertException e) {
			throw new ServiceException(e);
		}
	}
	
	public Item load(Serializable id) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemDAO.class).load(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public ItemPaginate searchItens(String pattern, String strNumPage) throws Exception{
		Long numItens = countFilteredItens(pattern);
		int numPages = calculateNumPages( numItens );
		int rowStart = getRowStart(strNumPage);
		
		List<Item> itensList = itemDAO
				.filterItens( pattern, rowStart, (int) MAX_ITENS );
		
		List<Locale> locales = getServiceFactory()
				.getService(LocaleService.class)
				.listLocales();
		
		List<Integer> years = getServiceFactory()
				.getService(OrderService.class)
				.distinctOrderYears();

		ItemPaginate itemPaginate = new ItemPaginate();
		itemPaginate.setItens( itensList );
		itemPaginate.setNumPages( numPages );
		itemPaginate.setLocales(locales);
		itemPaginate.setYears(years);
		
		return itemPaginate;
	}
	
	@Deprecated
	private Long countFilteredItens( String pattern ) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemDAO.class).countFilteredItens(pattern);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public int calculateNumPages( Long numItens ) {
		return (int) Math.ceil(numItens / MAX_ITENS);
	}
	
	@Deprecated
	private int getRowStart( String strNumPage) {
		if(strNumPage == null)
			strNumPage = "1";
		
		int numPage = Integer.parseInt(strNumPage);
		int rowFinish = numPage * (int) MAX_ITENS;
		int rowStart = rowFinish - (int) MAX_ITENS;
		
		return rowStart;
	}
	
	@Deprecated
	public ItemPaginate listItensByType(Long itemTypeId, String strNumPage ) throws Exception{
		ItemType itemType = getServiceFactory()
				.getService(ItemTypeService.class)
				.load(itemTypeId);
		
		int rowStart = getRowStart(strNumPage);

		Long numItens = getNumItensByType(itemType);
		int numPages = calculateNumPages( numItens );
		
		List<Item> itensList = getDaoFactory()
				.getDAO(ItemDAO.class)
				.paginateItemByType(itemType, rowStart, (int) MAX_ITENS);
		
		ItemPaginate itemPaginate = new ItemPaginate();
		itemPaginate.setNumPages(numPages);
		itemPaginate.setItens(itensList);
		
		return itemPaginate;
	}
	
	@Deprecated
	private Long getNumItensByType(ItemType type) throws ServiceException {
		try {
			return getDaoFactory().getDAO(ItemDAO.class).countItensBySubitemType(type);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Deprecated
	public ItemPaginate listAllItens( String strNumPage ) throws Exception{
		int rowStart = getRowStart(strNumPage);

		Long numItens = getNumItens( SortProperty.ID );
		int numPages = calculateNumPages( numItens );
		
		//TODO criar enum do tipo order
		List<Item> itensList = getDaoFactory()
				.getDAO(ItemDAO.class)
				.paginationList(
						SortProperty.ID, SortProperty.ORDER.ASC.toString(), rowStart, (int) MAX_ITENS);
		
		ItemPaginate itemPaginate = new ItemPaginate();
		itemPaginate.setNumPages(numPages);
		itemPaginate.setItens(itensList);
		
		return itemPaginate;
	}
	
	@Deprecated
	public Long getNumItens(SortProperty sortProperty) throws ServiceException{
		try {
			return getDaoFactory().getDAO(ItemDAO.class).countItens(sortProperty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
