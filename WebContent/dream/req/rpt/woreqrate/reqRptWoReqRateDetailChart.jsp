<%--===========================================================================
요청접수율(처리자) 차트
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.woreqrate.action.ReqRptWoReqRateDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요청접수율(처리자) 차트 -->
<title><bean:message key='LABEL.comWoReqRateGraphMan'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("reqRptWoReqRateDetailDTO.deptDesc");
    
	initChart();
	findChart();
}

var myChart;
/**
 * 차트
 */
 function initChart(){
    myChart =  new dhtmlXChart({
        view:"stackedBar",
        container:"chartbox",
        value:"#COMPLETECNT#",
        color: "#ffbf1e",
        gradient:"falling",
        tooltip:{
            template:"#COMPLETERATE#"+"%"
        },
        xAxis:{
            template:"'#REQBY#"
        },
        yAxis:{},
        legend:{
            values:[{text:"<bean:message key='LABEL.workReqCnt'/>",color:"#dddddd"},{text:"<bean:message key='LABEL.completeCnt'/>",color:"#ffbf1e"}],
            valign:"middle",
            align:"right",
            width:90,
            layout:"y"
        }
    });
    myChart.addSeries({
        value:"#INCOMPLETECNT#",
        color:"#dddddd"
    });
}

function findChart(idx,plant,line){
	var url = contextPath + "/reqRptWoReqRateDetailList.do";
	var form = document.reqRptWoReqRateDetailForm;	
	form.strutsAction.value = '<%=ReqRptWoReqRateDetailAction.DETAIL_FIND %>';
	
    $.post(url,FormQueryString(reqRptWoReqRateDetailForm), function(_data){
        myChart.clearAll();
    	myChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptWoReqRateDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptId"/>
<html:hidden property="reqRptWoReqRateDetailDTO.deptDesc"/>
<html:hidden property="reqRptWoReqRateDetailDTO.startDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.endDate"/>
<html:hidden property="reqRptWoReqRateDetailDTO.isReqDate"/>

    <!-- searchbox 박스 Line -->
    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>