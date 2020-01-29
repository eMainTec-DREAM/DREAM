<%--===========================================================================
재고지표
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.stockkpi.action.StockKpiListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 재고지표 -->
<title><bean:message key='MENU.STCKKPI'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc;

function loadPage() 
{
	// 년월
	stockKpiListForm.elements['stockKpiCommonDTO.filterStartYyyymm'].value = getYear()+"-01";
	stockKpiListForm.elements['stockKpiCommonDTO.filterEndYyyymm'].value   = getYear()+"-"+(getMonth());
	
	//창고명 
    wareHouseAc = new autoC({"stockKpiCommonDTO.filterWName":"wname"});
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "stockKpiCommonDTO.filterWCodeId":"wcode_id"
    });
    wareHouseAc.init();
    
	initGrid();
	initChart();
}

var myGrid;
var myLineChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	stockKpiListForm.elements['stockKpiCommonDTO.filterWCodeId'].value = "";
        return sortColumn("stockKpiList", this, stockKpiListForm, "EMPID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

// 차트
function initChart()
{
	myLineChart =  new dhtmlXChart({
		
		view:"spline",
        container:"chartbox",
        value:"#GOALAMT#",
        label:function(val){
        	return setNumberFormat(val.GOALAMT);
		},
        color:"#dddddd",
        item:{
            borderColor: "#ffffff",
            color: "#000000"
        },
        line:{
            color:"#f45042",
            width:3
        },
        tooltip:{
			template:function(val){
				return setNumberFormat(val.GOALAMT);	
			}
		},
        offset:0,
        xAxis:{
            template:"#YYYYMM#"
        },
        yAxis:{
        },
        legend:{
            values:[{text:"<bean:message key='LABEL.goalAmt'/>",color:"#f45042"}
                   ,{text:"<bean:message key='LABEL.stockAmt'/>",color:"#FFDB58"}
                   ,{text:"<bean:message key='LABEL.useAmt'/>",color:"#3fa4db"}
                   ],
            valign:"middle",
            align:"right",
            width:90,
            layout:"y"
        },
    });
	// 재고금액
	myLineChart.addSeries({
        value:"#STOCKAMT#",
        label:function(val){
        	return setNumberFormat(val.STOCKAMT);
		},
        item:{
            borderColor: "#ffffff",
            color: "#000000"
        },
        line:{
            color:"#FFDB58",
            width:3
        },
        tooltip:{
			template:function(val){
				return setNumberFormat(val.STOCKAMT);	
			}
		},
    });
	// 사용금액
	myLineChart.addSeries({
		value:"#USEAMT#",
		label:function(val){
        	return setNumberFormat(val.USEAMT);
		},
        item:{
            borderColor: "#ffffff",
            color: "#000000"
        },
        line:{
            color:"#3fa4db",
            width:3
        },
        tooltip:{
			template:function(val){
				return setNumberFormat(val.USEAMT);	
			}
		},
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.stockKpiListForm;	
	form.strutsAction.value = '<%=StockKpiListAction.PMINS_ACH_LIST_FIND %>';
	
	var url = contextPath + "/stockKpiList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(stockKpiListForm), "EMPID", "Y");
	
	$.post(url,FormQueryString(stockKpiListForm), function(_data){
		myLineChart.clearAll();
    	myLineChart.parse(_data,"json");
    });
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.stockKpiListForm;
	
	
	goCommonTabPage(form, '' , pageId);
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("stockKpiList", stockKpiListForm);
  }
 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/stockKpiList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="stockKpiCommonDTO.filterWCodeId"/>

	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="stockKpiCommonDTO.filterStartYyyymm" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="stockKpiCommonDTO.filterEndYyyymm" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 창고명  -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.wname"/></label>
					<div class="input_box">
						<html:text property="stockKpiCommonDTO.filterWName" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
	 
	   <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
            <div class="stitle_wrap">
                <div class="stitle_icon"><a></a></div>
                <div class="stitle_tx"><bean:message key="LABEL.chart"/></div>
            </div>
            <div class="function_box">
                <div class="fb_group3">
                    <div class="sfb_wrap" style="display:none;">
                    </div>
                </div>

                <div class="fb_group2">
                </div>
            </div>
        </div>
        <div class="article_box">
            <div class="grid_area">
                <div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
            </div>
        </div>
    </div> <!--  end section_wrap -->
	 
</html:form> 
</body>
</html>