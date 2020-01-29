<%--===========================================================================
과별고장분석 차트
author  kim21017
version $Id: maBmGwChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabmgwchart.action.MaBmGwChartAction" %>
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
<!-- 과별고장분석 차트 -->
<title><bean:message key='MENU.BMGWCHART'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
var bmChart;		//과별고장분석 차트
var selectedInd;

function loadPage() 
{
	setTitle("maBmGwChartDTO.deptDesc");
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
 * 현재 셋팅된 조건으로 값을 조회하여 과별고장현황 차트에 셋팅한다.
 */
function findBmChart()
{
    var url = contextPath + "/maBmGwChartDetail.do";
    maBmGwChartForm.elements['strutsAction'].value = '<%=MaBmGwChartAction.BM_CHART_FIND%>';

    bmChart.clearAll();
    
    $.post(url,FormQueryString(maBmGwChartForm), function(_data){
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
<html:form action="/maBmGwChartDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maBmGwChartDTO.filterDeptId"/>
<html:hidden property="maBmGwChartDTO.yyyy"/>
<html:hidden property="maBmGwChartDTO.deptId"/>
<html:hidden property="maBmGwChartDTO.deptDesc"/>
<html:hidden property="maBmGwChartDTO.filterMainMngId"/>
<html:hidden property="maBmGwChartDTO.filterSubMngId"/>
<html:hidden property="maBmGwChartDTO.filterEqLocId"/>
<html:hidden property="maBmGwChartDTO.filterEqCtgId"/>
<html:hidden property="maBmGwChartDTO.filterEquipId"/>
	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="article_box">
			<div class="grid_area">
				<div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>