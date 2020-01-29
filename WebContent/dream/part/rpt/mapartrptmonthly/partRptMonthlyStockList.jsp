<%--===========================================================================
부품수불부 요약
author  euna0207
version $Id: partRptMonthlyStockList.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.mapartrptmonthly.action.PartRptMonthlyStockListAction" %>
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
<!-- 부품수불부 -->
<title><bean:message key='MENU.materialsLedger'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var wareHouseAc;
var partAc;

function loadPage() 
{

	//년월 세팅
	partRptMonthlyStockListForm.elements['partRptMonthlyStockListDTO.filterYearMonthDesc'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);
	
	//창고
    wareHouseAc = new autoC({"partRptMonthlyStockListDTO.filterWcodeDesc":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART",
    	"is_use":"Y"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "partRptMonthlyStockListDTO.filterWcodeId":"wcode_id"
    });
    wareHouseAc.setCustomLov("lovWh('partRptMonthlyStockListDTO.filterWcodeId', 'partRptMonthlyStockListDTO.filterWcodeDesc')");
    wareHouseAc.init();
    
    //부품
	partAc = new autoC({"partRptMonthlyStockListDTO.filterPartsDesc":"description"});
    partAc.setAcConditionMap({
 	   "part_categ":"SPPT",
 	   "comp_no":loginUser.compNo
 	   });
    partAc.setTable("TAPARTS");
    partAc.setAcResultMap({
        "partRptMonthlyStockListDTO.filterPartsId":"part_id"
    });
    partAc.init();
    
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.setHeader(",<bean:message key='LABEL.seqNo'/>"//순번
    		+",<bean:message key='LABEL.ptNo'/>"//품번
    		+",<bean:message key='LABEL.ptDesc'/>"//품명
    		+",<bean:message key='LABEL.ptSize'/>"//규격
    		+",<bean:message key='LABEL.model'/>"//모델
    		+",<bean:message key='LABEL.unitPrice'/>"//단가
    		+",<bean:message key='LABEL.storingUnitPrice'/>"//최근입고단가
			+",<bean:message key='LABEL.stocksAmount'/>"+",#cspan"//재고
			+",<bean:message key='LABEL.initialQuantity'/>"+",#cspan"//기초
			+",<bean:message key='LABEL.ptRec'/>"+",#cspan"//입고
			+",<bean:message key='LABEL.unstoring'/>"+",#cspan"//출고
			);
	myGrid.attachHeader("#rspan"
			+",#rspan"
			+",#rspan"
			+",#rspan"
			+",#rspan"
			+",#rspan"
			+",#rspan"
			+",#rspan"
			+",<bean:message key='LABEL.qty'/>"//재고수량
			+",<bean:message key='LABEL.amt'/>"//재고금액
			+",<bean:message key='LABEL.qty'/>"//기초수량
			+",<bean:message key='LABEL.amt'/>"//기초금액
			+",<bean:message key='LABEL.qty'/>"//입고수량
			+",<bean:message key='LABEL.amt'/>"//입고금액
			+",<bean:message key='LABEL.qty'/>"//출고수량
			+",<bean:message key='LABEL.amt'/>"//출고금액
			);
	

	myGrid.setColumnIds("COMPNO,SEQNO,PARTNO,PARTDESC,PTSIZE,MODEL,UNITPRICE,RECPRICE,RESULTTOT,RESULTTOTPRICE,BASEQTY,BASETOTPRICE,RECQTY,RECTOTPRICE,ISSUEQTY,ISSUETOTPRICE");
	myGrid.setInitWidths("100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100");
	myGrid.setColAlign("center,center,center,left,left,left,right,right,right,right,right,right,right,right,right,right");
	myGrid.setColTypes("ro,cntr,ro,ro,ro,ro,ron,ron,ron,ron,ron,ron,ron,ron,ron,ron");
	myGrid.setColSorting("str,na,str,str,str,str,int,int,int,int,int,int,int,int,int,int");
	myGrid.setColumnHidden(0,true);
	myGrid.setColumnHidden(7,true);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox") });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partRptMonthlyStockListForm.elements['partRptMonthlyStockListDTO.compNo'].value = "";
    	return sortColumn("partRptMonthlyStockList", this, partRptMonthlyStockListForm, "compNo", ind, direction);
	});
	
	isHeaderLoaded[currentPageId+".gridbox"] = "Y";

	myGrid.init();
	
	goSearch();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 라인별 그리드에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partRptMonthlyStockList.do";
    partRptMonthlyStockListForm.elements['strutsAction'].value = '<%=PartRptMonthlyStockListAction.LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partRptMonthlyStockListForm), "" , "Y");
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkValidation()) return;
	
	for(var i = 0; myGrid.getColumnsNum() > i; i++)if(myGrid.getColType(i) == "ron" || myGrid.getColType(i) == "edn") myGrid.setNumberFormat("0,000",i,".",",");
	
    findGridList();
    
}
function findGridRow()
{
	goSearch();
}

/**
 *  엑셀 다운
 */
function goExcel()
{
	excelServerAction("partRptMonthlyStockList", partRptMonthlyStockListForm, {
		excelYn:"Y"
	});
} 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partRptMonthlyStockList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partRptMonthlyStockListDTO.filterWcodeId"/>
<html:hidden property="partRptMonthlyStockListDTO.filterPartsId"/>
<html:hidden property="partRptMonthlyStockListDTO.compNo"/>

	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter not_fold">
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
				<!-- 일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.yyyymm"/></label>
					<div class="input_box">
						<html:text property="partRptMonthlyStockListDTO.filterYearMonthDesc" tabindex="10" />
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 부품 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.parts"/></label>
					<div class="input_box">
						<html:text property="partRptMonthlyStockListDTO.filterPartsDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 창고 -->
				<div class="field">
					<label><bean:message key="LABEL.wname"/></label>
					<div class="input_box">
						<html:text property="partRptMonthlyStockListDTO.filterWcodeDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
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
			<div class="function_box list not_fold">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:600px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>