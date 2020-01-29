<%--===========================================================================
예방점검 이행율(담당자) 차트
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmins.ach.action.WorkRptPminsAchDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 이행율(담당자) 차트 -->
<title><bean:message key='MENU.PMINSACH'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("workRptPminsAchDetailDTO.empDesc");
}

var myLineChart;
/**
 * 차트
 */
function initChart(){
	myLineChart =  new dhtmlXChart({
		view:"spline",
		container:"chartbox",
		value:"#ACHIEVEMENT#",
		label:"#ACHIEVEMENT#",
		item:{
			borderColor: "#447900",
			color: "#69ba00"
		},
		line:{
			color:"#69ba00",
			width:2
		},
		tooltip:{
			template:"#ACHIEVEMENT#"
		},
		offset:0,
		xAxis:{
			template:"'#MONTH#"
		},
		yAxis:{
			start:0,
			step: 10,
			end: 100
		},
		padding:{
			left:35,
			bottom: 50
		},
		origin:0,
		legend:{
			values:[{text:"<bean:message key='LABEL.completeRate'/>"}],
			align:"right",
			valign:"middle",
			layout:"y",
			width: 100,
			margin: 8,
			marker:{
				type: "item"
			}
		}
	});
	
	findChart();

}

function findChart(idx,plant,line){
	var url = contextPath + "/workRptPminsAchDetailList.do";
	var form = document.workRptPminsAchDetailForm;	
	form.strutsAction.value = '<%=WorkRptPminsAchDetailAction.PMINS_ACH_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(workRptPminsAchDetailForm), function(_data){
    	myLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPminsAchDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPminsAchDetailDTO.empId"/>
<html:hidden property="workRptPminsAchDetailDTO.empDesc"/>
<html:hidden property="workRptPminsAchDetailDTO.deptId"/>
<html:hidden property="workRptPminsAchDetailDTO.startDate"/>
<html:hidden property="workRptPminsAchDetailDTO.endDate"/>

    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>