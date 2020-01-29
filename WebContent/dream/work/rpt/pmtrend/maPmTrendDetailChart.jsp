<%--===========================================================================
예방점검수치추이 차트
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mapmtrend.action.MaPmTrendDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검수치추이 차트 -->
<title><bean:message key='MENU.PMTREND'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var selected = false;

function loadPage() 
{
	initChart();
	
	//setTitle("maPmTrendDetailDTO.checkPoint","<bean:message key='LABEL.monthlyMttr'/>" );
	//setTitle("maPmTrendDetailDTO.checkPoint","maPmTrendDetailDTO.startDate","maPmTrendDetailDTO.endDate" );
}

var myLineChart;
/**
 * 차트
 */
function initChart(){
	myLineChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#CNT#",
		tooltip:{
			template:"#CNT#"
		},
		item:{
			borderColor: "#FF6633",
			color: "#ffffff"
		},
		line:{
			color:"#FF6633",
			width:2
		},
		xAxis:{
			template:"'#DAYS#"
		},
		offset:0,
		yAxis:{
			start:0,
			step: 1
		},
		padding:{
			left:35,
			bottom: 50
		},
        origin:0,
        legend:{
            layout:"x",
            width: 55,
            align:"right",
            toggle:false,
            valign:"bottom",
            values:[
                {text:"<bean:message key='LABEL.resultVal'/>",color:"#FF7171"},
                {text:"<bean:message key='LABEL.checkMin'/>",color:"#99ccff"},
                {text:"<bean:message key='LABEL.checkMax'/>",color:"#99ccff"}
            ],
            margin: 5
	        }
		});
		myLineChart.addSeries({
	        value:"#MINVAL#",
	        tooltip:{
	            template:"#MINVAL#"
	        },item:{
	            borderColor: "#99ccff",
	            color: "#ffffff"
	        },
	        line:{
	            color:"#99ccff",
	            width:1
	        }
	    });
	    myLineChart.addSeries({
	        value:"#MAXVAL#",
	        tooltip:{
	            template:"#MAXVAL#"
	        },item:{
	            borderColor: "#99ccff",
	            color: "#ffffff"
	        },
	        line:{
	            color:"#99ccff",
	            width:1
	        }
	    });
    
	findChart();

}

function findChart(idx,plant,line){
	var url = contextPath + "/maPmTrendDetailChart.do";
	var form = document.maPmTrendDetailForm;	
	form.strutsAction.value = '<%=MaPmTrendDetailAction.EMP_MTTR_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(maPmTrendDetailForm), function(_data){
    	myLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

function goSelect(){
       var param = "strutsAction=1001";
       var url =  contextPath + "/maWoResultSelect.do";
           
       openLayerPopup("maWoResultSelect", param);
   }

   function setAfterSelect(returnArray){

       var woType = returnArray[0];
       var pmType = returnArray[1];
       var _pageId  = returnArray[2];

       var ifm = getIframeContent();
       
       maPmTrendDetailForm.elements['maPmTrendDetailDTO.selectedWoType'].value = woType;
       maPmTrendDetailForm.elements['maPmTrendDetailDTO.selectedPmType'].value = pmType;
       
       selected = true;

       goWoex();
   }


/**
 * 조치 WO 발행
 */
 function goWoex(){
     
     if (selected == false)
    {
        // 선택
        goSelect();
    }
    else
    {
    
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0028'/>", function(result){
         if(result){
             maPmTrendDetailForm.strutsAction.value = '<%=MaPmTrendDetailAction.WO_CREATE%>';
             var url = contextPath + "/maPmTrendDetailChart.do";
             $.post(url,FormQueryString(maPmTrendDetailForm), function(_data){
                 afterWoex();
             });
         }
     });
    } 
}

 function afterWoex(){
     alertMessage1('<bean:message key="MESSAGE.MSG0027"/>');
     selected=false;
 }
 
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPmTrendDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="maPmTrendDetailDTO.pmId"/>
<html:hidden property="maPmTrendDetailDTO.pmPointId"/>
<html:hidden property="maPmTrendDetailDTO.equipId"/>
<html:hidden property="maPmTrendDetailDTO.startDate"/>
<html:hidden property="maPmTrendDetailDTO.endDate"/>
<html:hidden property="maPmTrendDetailDTO.pmType"/>
<html:hidden property="maPmTrendDetailDTO.selectedWoType"/>
<html:hidden property="maPmTrendDetailDTO.selectedPmType"/>
<html:hidden property="maPmTrendDetailDTO.checkPoint"/>

    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>