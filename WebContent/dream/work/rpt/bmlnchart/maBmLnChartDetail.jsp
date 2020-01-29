<%--===========================================================================
라인고장분석 chart
author  kim21017
version $Id: maBmLnChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabmlnchart.action.MaBmLnChartAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 라인고장분석 chart -->
<title><bean:message key='MENU.BMLNCHART'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
var bmChart;		//라인고장분석 차트

function loadPage() 
{
	setTitle("maBmLnChartDTO.eqlocDesc");
	$('.stitle_tx').append(" (<bean:message key='LABEL.cnt2'/>)");
	//차트 초기화
	initChart();
}

function initChart(){
	bmChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#CNT#",
		tooltip:{
			template:"#CNT#"
		},
		item:{
			borderColor: "#FF7171",
			color: "#ffffff"
		},
		line:{
			color:"#FF7171",
			width:2
		},
		xAxis:{
			template:"'#MONTH#"
		},
		offset:0,
		yAxis:{
			start:0,
			step:1
		}
	});
	
	findBmChart();
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 라인고장분석 차트에 셋팅한다.
 */
function findBmChart()
{
    var url = contextPath + "/maBmLnChartDetail.do";
    maBmLnChartForm.elements['strutsAction'].value = '<%=MaBmLnChartAction.BM_CHART_FIND%>';

    bmChart.clearAll();
    
    $.post(url,FormQueryString(maBmLnChartForm), function(_data){
    	bmChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	bmChart.resize();
});

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maBmLnChartDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBmLnChartDTO.filterDeptId"/>
<html:hidden property="maBmLnChartDTO.yyyy"/>
<html:hidden property="maBmLnChartDTO.eqlocId"/>
<html:hidden property="maBmLnChartDTO.eqlocDesc"/>
<html:hidden property="maBmLnChartDTO.filterMainMngId"/>
<html:hidden property="maBmLnChartDTO.filterSubMngId"/>
<html:hidden property="maBmLnChartDTO.filterEqLocId"/>
<html:hidden property="maBmLnChartDTO.filterEqCtgId"/>
<html:hidden property="maBmLnChartDTO.filterEquipId"/>

		<div class="article_box">
			<div class="grid_area">
				<div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
</html:form> 
</body>
</html>