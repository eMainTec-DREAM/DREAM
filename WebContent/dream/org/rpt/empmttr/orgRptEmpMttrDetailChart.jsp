<%--===========================================================================
MTTR(담당자) 차트
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.empmttr.action.OrgRptEmpMttrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTTR(담당자) 차트 -->
<title><bean:message key='MENU.EmpMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("orgRptEmpMttrDetailDTO.empDesc","<bean:message key='LABEL.monthlyMttr'/>" );
}

var myLineChart;
/**
 * 차트
 */
function initChart(){
	myLineChart =  new dhtmlXChart({
		view:"spline",
		container:"chartbox",
		value:"#MTTR#",
		label:"#MTTR#",
		item:{
			borderColor: "#447900",
			color: "#69ba00"
		},
		line:{
			color:"#69ba00",
			width:2
		},
		tooltip:{
			template:"#WKORDATE#"
		},
		offset:0,
		xAxis:{
			template:"'#WKORDATE#"
		},
		yAxis:{
			start:0,
			step: 10
		},
		padding:{
			left:35,
			bottom: 50
		},
		origin:0
	});
	
	findChart();

}

function findChart(idx,plant,line){
	var url = contextPath + "/orgRptEmpMttrDetailList.do";
	var form = document.orgRptEmpMttrDetailForm;	
	form.strutsAction.value = '<%=OrgRptEmpMttrDetailAction.EMP_MTTR_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(orgRptEmpMttrDetailForm), function(_data){
    	myLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptEmpMttrDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="orgRptEmpMttrDetailDTO.empId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.empDesc"/>
<html:hidden property="orgRptEmpMttrDetailDTO.deptId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.startDate"/>
<html:hidden property="orgRptEmpMttrDetailDTO.endDate"/>

<html:hidden property="orgRptEmpMttrCommonDTO.filterEqGrade"/>
<html:hidden property="orgRptEmpMttrCommonDTO.filterEqGradeDesc"/>

    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>