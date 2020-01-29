<%--===========================================================================
grid tag
author  javaworker
version $Id: grid.tag,v 1.1 2013/08/30 09:10:49 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ tag pageEncoding="EUC-KR" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ attribute name="gridId" required="true" rtexprvalue="true"%>
	<div><div>
	<div style="display:!none;margin-top: 5px;margin-left: 5px;">
	  <div id='<c:out value="${gridId}" />' style="border:0px solid #cccccc;background-color:#f3f3f3;"></div>
	</div> 
	</div></div>