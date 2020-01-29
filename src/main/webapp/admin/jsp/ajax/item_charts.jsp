<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Month"%>
<%@page import="br.com.javamon.entity.OrderItem"%>
<%@page import="br.com.javamon.entity.Locale"%>
<%@page import="br.com.javamon.entity.Order"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@taglib prefix="date" uri="/WEB-INF/date-format-tag.tld"%>
 <%@taglib prefix="blob" uri="/WEB-INF/blob-to-string.tld"%>

	<select id="chartYear${item.id}">
		<c:forEach  begin="${firstSystemYear}" end="${currentDate.year}" varStatus="loop">
			<option <c:if test="${loop.index == chartYear}">selected = selected </c:if> >${loop.index	}</option>
		</c:forEach>
	</select>
	
	<%
		Gson gsonObj = new Gson();
		Map<Object, Object> map = null;
		List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
		
		@SuppressWarnings("unchecked")
		List<Map<Locale, List<OrderItem>>> ordersLocales = (List<Map<Locale, List<OrderItem>>>) request.getAttribute("ordersLocales");
		
		Map<Locale, List<OrderItem>> localeOrders = ordersLocales.get(0);	
		Iterator<Locale> locInterator = localeOrders.keySet().iterator();
		
		Locale locale = null;
		String[] dataPoints = new String[localeOrders.size()];
		int count = 0;
		while(locInterator.hasNext()){
			locale = locInterator.next();
			list = new ArrayList<Map<Object,Object>>();
			
			map = new HashMap<Object,Object>(); map.put("label", "Dezembro"); 
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 12, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Novembro"); 
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 11, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Outubro");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 10, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Setembro");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 9, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Agosto");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 8, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Julho");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 7, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Junho");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 6, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Maio");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 5, session)); list.add(map);
			
			map = new HashMap<Object,Object>(); map.put("label", "Abril");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 4, session)); list.add(map);
	
			map = new HashMap<Object,Object>(); map.put("label", "Março");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 3, session)); list.add(map);
	
			map = new HashMap<Object,Object>(); map.put("label", "Fevereiro");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 2, session)); list.add(map);
	
			map = new HashMap<Object,Object>(); map.put("label", "Janeiro");
			map.put("y", sumOrdersByMonth(localeOrders.get(locale), 1, session)); list.add(map);
			
			dataPoints[count++] = gsonObj.toJson(list);
		}
		
		
		int firstYear = Integer.parseInt((String) session.getAttribute("firstSystemYear"));
		LocalDate currentDate = (LocalDate) session.getAttribute("currentDate");
		String[] dataPoints2 = new String[(currentDate.getYear() - firstYear) + 1];
		count = 0;
		list = new ArrayList<Map<Object,Object>>();
		for(int i = firstYear; i <= currentDate.getYear(); i++){
			
			map = new HashMap<Object,Object>(); map.put("label", i); 
			map.put("y", sumOrdersByYear(localeOrders, i, session)); list.add(map);
			
			dataPoints2[count++] = gsonObj.toJson(list);
		}
	%>
	
	<%!
		int sumOrdersByMonth(List<OrderItem> orders, int month, HttpSession session){
			LocalDate startDate = (LocalDate) session.getAttribute("startDate");
			LocalDate currentDate = (LocalDate) session.getAttribute("currentDate");
			
			int sum = 0;
			for(OrderItem orderItem : orders ){
				LocalDate orderDate = orderItem.getOrder().getDate();
				if(orderDate.getMonthValue() == month && orderDate.getYear() == Integer.valueOf( (String) session.getAttribute("chartYear")) )
					sum += orderItem.getAmount();
			}
			return sum;
		}
	
		int sumOrdersByYear(Map<Locale, List<OrderItem>> localesOrders, int year, HttpSession session){
			LocalDate startDate = (LocalDate) session.getAttribute("startDate");
			LocalDate currentDate = (LocalDate) session.getAttribute("currentDate");
			
			int sum = 0;
			Set<Locale> locales = localesOrders.keySet();
			Iterator<Locale> iterator = locales.iterator();
			List<OrderItem> orders = null;
			LocalDate orderDate = null; 
			
			while(iterator.hasNext()){
				orders = localesOrders.get(iterator.next());
				
				for(OrderItem orderItem : orders ){
					orderDate = orderItem.getOrder().getDate();
					if(orderDate.getYear() == year)
						sum += orderItem.getAmount();
				}
			}
			return sum;
		}
	%>
	
	<c:set var="convertedStartDate"> <date:format country='BR' language='pt'>${startDate}</date:format> </c:set>
	<c:set var="convertedCurrentDate" > <date:format country='BR' language='pt'>${currentDate}</date:format> </c:set>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#chartYear${item.id}").on("change", function(){
				var chartAppContainer = "chartAppContainer${item.id}";
				var chartYear = $("#chartYear${item.id}").val();
				
				<c:url value="/admin/LoadItemCharts.action" var="loadItemCharts">
					<c:param name="itemId" value="${item.id }"/>
				</c:url>
				
				var url = "${loadItemCharts}" + "&chartYear=" + chartYear;
				
				if($("#chartContainer") != undefined){
					$("#chartContainer").attr("id", "chartContainerUnused");
					$("#chartContainer2").attr("id", "chartContainer2Unused");
				}
				ajaxCall(url, chartAppContainer);
			});
			
			var chart = new CanvasJS.Chart("chartContainer", {
				animationEnabled: true,
				title: {
					text: ""
				},
				axisX: {
					reversed: true
				},
				axisY: {
					title: "${item.description} (consumo mensal em ${chartYear})",
					titleFontColor: "#4F81BC",
					lineColor: "#4F81BC",
					labelFontColor: "#4F81BC",
					tickColor: "#4F81BC"
				},
				toolTip: {
					shared: true
				},
				legend: {
					cursor: "pointer",
					itemclick: toggleDataSeries
				},
				data: [
					<% 
					locInterator = localeOrders.keySet().iterator();
					count = 0;
					while(locInterator.hasNext()){ 
					%>
					{
						type: "bar",
						name: "<% out.print(locInterator.next().getDescription()); %>",
						axisYType: "primary",
						showInLegend: true,
						yValueFormatString: "#.##0 ",
						dataPoints: <%out.print(dataPoints[count++]);%>
					},
					<% }%>
				]
			});
			chart.render();
			
		
			var chart2 = new CanvasJS.Chart("chartContainer2", {
				animationEnabled: true,
				theme: "light2",
				title: {
					text: ""
				},
				axisX: {
					crosshair: {
						enabled: true,
						snapToDataPoint: true
					}
				},
				axisY: {
					title: "${item.description} (consumo anual)",
					includeZero: false,
					crosshair: {
						enabled: true,
						snapToDataPoint: true,
						valueFormatString: "##0.00",
					}
				},
				data: [
					<% 
					count = 0;
					for(int i = firstYear; i <= currentDate.getYear(); i++){
					%>
					{
						type: "area",
						yValueFormatString: "#",
						dataPoints: <%out.print(dataPoints2[count++]);%>
					},
					<% }%>
				]
			});
			chart2.render();
			
			function toggleDataSeries(e) {
				if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
					e.dataSeries.visible = false;
				} else {
					e.dataSeries.visible = true;
				}
				e.chart.render();
				e.chart2.render();
				
			}
			
		}); 
	
	</script>
	
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<div id="chartContainer2" style="height: 370px; width: 100%;"></div>
	