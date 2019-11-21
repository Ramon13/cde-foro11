<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="header.jsp" %>

<div id="menu-bar">
	<ul id="menu">
  <li>
    <div><span class="ui-icon ui-icon-disk"></span>Início</div>
  </li>
  <li>
    <div><span class="ui-icon ui-icon-zoomin"></span>Zoom In</div>
  </li>
  <li>
    <div><span class="ui-icon ui-icon-zoomout"></span>Zoom Out</div>
  </li>
  <li class="ui-state-disabled">
    <div><span class="ui-icon ui-icon-print"></span>Print...</div>
  </li>
  <li>
    <div>Playback</div>
    <ul>
      <li>
        <div><span class="ui-icon ui-icon-seek-start"></span>Prev</div>
      </li>
      <li>
        <div><span class="ui-icon ui-icon-stop"></span>Stop</div>
      </li>
      <li>
        <div><span class="ui-icon ui-icon-play"></span>Play</div>
      </li>
      <li>
        <div><span class="ui-icon ui-icon-seek-end"></span>Next</div>
      </li>
    </ul>
  </li>
  <li>
    <div>Learn more about this menu</div>
  </li>
</ul>
	
</div>

<div id="content"></div>

<%@include file="footer.jsp" %>
