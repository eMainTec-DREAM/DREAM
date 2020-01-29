<%--===========================================================================
에너지사용량(설비별)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.eqeng.action.WorkRptEqEngListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량(설비별) -->
<title><bean:message key='MENU.EQENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var plantAc, eqLocDescAc, usageDeptAc, eqCtgTypeAc, equipAc;
function loadPage() 
{
	workRptEqEngListForm.elements['workRptEqEngCommonDTO.filterYear'].value = getYear(); 
	//공장
	workRptEqEngListForm.elements['workRptEqEngCommonDTO.filterPlant'].value = loginUser.filterPlant;
	workRptEqEngListForm.elements['workRptEqEngCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
		
	
	// 공장코드
	plantAc = new autoC({"workRptEqEngCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptEqEngCommonDTO.filterPlant":"plant"
    });
    plantAc.init();
    
    //위치
    eqLocDescAc = new autoC({"workRptEqEngCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	});
    eqLocDescAc.setAcDConditionMap({
    	"plant":"workRptEqEngCommonDTO.filterPlant"
    });
    eqLocDescAc.setAcResultMap({
        "workRptEqEngCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    //사용부서
    usageDeptAc = new autoC({"workRptEqEngCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    usageDeptAc.setAcDConditionMap({
    	"plant":"workRptEqEngCommonDTO.filterPlant"
    });
    usageDeptAc.setAcResultMap({
        "workRptEqEngCommonDTO.filterUsageDeptId":"dept_id",
    });
    usageDeptAc.init();
    
    // 종류
    eqCtgTypeAc = new autoC({"workRptEqEngCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgTypeAc.setAcResultMap({
        "workRptEqEngCommonDTO.filterEqCtgId":"eqctg_id"
    });
	eqCtgTypeAc.init();
	
    // 설비
    equipAc = new autoC({"workRptEqEngCommonDTO.filterEquipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
        "workRptEqEngCommonDTO.filterEquipId":"EQUIP_ID"
    });
	equipAc.setAcDConditionMap({
    	"eqloc_id" : "workRptEqEngCommonDTO.filterEqLocId"
    	,"eqctg_id" : "workRptEqEngCommonDTO.filterEqCtgId"
    	,"plant":"workRptEqEngCommonDTO.filterPlant"
    });
	equipAc.init();
    
	initGrid(workRptEqEngListForm.elements['workRptEqEngCommonDTO.filterYear'].value);
}

var myGrid;
function initGrid(year)
{
	var fromDate = year + "-01";
	var toDate = year + "-12";
	var months = 11;
	// Usage, Changes, Ratio
	var statusArr = ["P","U","C","R"];
	
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setImageSize(1,1);

	myGrid.setHeader(",,,,"
					 + ",<bean:message key='LABEL.seqNo'/>"
					 + ",<bean:message key='LABEL.plant'/>" 
					 + ",<bean:message key='LABEL.eqLocDesc'/>" 
					 + ",<bean:message key='LABEL.usedDept'/>" 
					 + ",<bean:message key='LABEL.eqCtg'/>" 
					 + ",<bean:message key='LABEL.equipNo'/>" 
					 + ",<bean:message key='LABEL.equipName'/>" 
					 + get3Months2(fromDate,toDate,months,true)
					 + ",<bean:message key='LABEL.totUsageYear'/>"); 
	myGrid.attachHeader(",,,,"
					 + ",#rspan"+",#rspan"+",#rspan"
					 + ",#rspan"+",#rspan"+",#rspan"
					 + ",#rspan"
					 + get3Words(months,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>","<bean:message key='LABEL.changes'/>","<bean:message key='LABEL.ratio'/>")
					 + ",#rspan");
	myGrid.setColumnIds("PLANTID,EQLOCID,USAGEDEPTID,EQCTGID,EQUIPID"
					 + ",SEQNO,PLANTDESC,EQLOCDESC,USAGEDEPTDESC,EQCTGDESC,ITEMNO,EQUIPDESC"
					 + get3Months(fromDate,toDate,months,statusArr)
					 + ",TOTUSAGE");
	myGrid.setInitWidths("100,100,100,100,100"
					 + ",60,120,150,120,120,100,150"
					 + getWords(months,"80")+getWords(months,"80")+getWords(months,"80")+getWords(months,"80")
					 + ",200");
	myGrid.setColAlign("left,left,left,left,left"
					 + ",center,left,left,left,left,center,left"
					 + getWords(months,"right")+getWords(months,"right")+getWords(months,"right")+getWords(months,"right")
					 + ",right");
	myGrid.setColTypes("ro,ro,ro,ro,ro"
			         + ",cntr,ro,ro,ro,ro,ro,ro"
			         + getWords(months,"ron")+getWords(months,"ron")+getWords(months,"ron")+getWords(months,"ron")
			         + ",ron");
	
	myGrid.setColumnHidden(0,true);
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true);
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();

	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 *fromDate, toDate 사이에 년-월을 조회 (2018-04, 2018-05)
 * 이페이지에서 만 사용...
 */
function getMonths1(fromDate,toDate,months){
		var str = "";
		var arr1 = toDate.split('-');
	
		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
		arr1 = toDate.split('-');
	
		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			var dateArr = date.split("-");
			var dateStr = date.replace(/\-/gi, "");
			var year   	= dateArr[0];
			var month   = dateArr[1];
			
			str +=","+year+"-"+month; 
		}
		return str;
}
/**
*fromDate, toDate 사이 날짜를 3개씩 반환
* statusArr를 순서대로 날짜 앞에 붙혀준다...
* 이페이지에서 만 사용...
*/
function get3Months(fromDate,toDate,months,statusArr){
		var str = "";
		var arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-01";
		arr1 = toDate.split('-');

		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1);
			var datArr = getMinusMonth2(dat1,-(i)).split('-');
			var date = datArr[0]+datArr[1];
			
			str +=","+statusArr[0]+date;
			str +=","+statusArr[1]+date;
			str +=","+statusArr[2]+date;
			str +=","+statusArr[3]+date;
		}
		return str;
	}
/**
 * fromDate와 toDate사이의 월을 반환(format:,2018-01,2018-02)
 *  3개씩 반환
 * 이페이지에서만 사용..
 */
function get3Months2(fromDate,toDate,months,isColspan){
	var str = "";
	var arr1 = toDate.split('-');
	
	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-01";
	arr1 = toDate.split('-');
	
	for(var i=months; i>=0; i--){
		var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		var date = getMinusMonth2(dat1,-(i));
		
		var dateArr = date.split("-");
		var dateStr = date.replace(/\-/gi, "");
		var year 	= dateArr[0];
		var month   = dateArr[1];
		
		str +=","+year+"-"+month; 
		if(isColspan){
			str +=",#cspan"; 
	 		str +=",#cspan";
	 		str +=",#cspan";
		}else{
			str +=","+year+"-"+month; 
	 		str +=","+year+"-"+month; 
		}
	}
	return str;
}
/**
 * val1,val2,val3 문자열을 days수만큼 연속해서 반환
 */
function get3Words(months,val1,val2,val3,val4){
	var str = "";
	for(var i=months; i>=0; i--){
		str +=","+val1;
		str +=","+val2;
		str +=","+val3;
		str +=","+val4;
	}
	return str;
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		if(myGrid.getColType(i) == "ron" ) 
		{
			myGrid.setNumberFormat("0,000.00",i,".",",");
		}
	}
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptEqEngListForm;	
	form.strutsAction.value = '<%=WorkRptEqEngListAction.LIST_FIND %>';
	
	var url = contextPath + "/workRptEqEngList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptEqEngListForm), "RESULTID");
}

/**
 * 상세열기
 */
 function goOpen(){
 	goTabPage('workRptEqEngDetailChart');
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
 	var form = document.workRptEqEngListForm;
 	
 	form.elements['workRptEqEngDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
 	form.elements['workRptEqEngDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
 	form.elements['workRptEqEngDetailListDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
 	form.elements['workRptEqEngDetailListDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
 	form.elements['workRptEqEngDetailListDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
 	form.elements['workRptEqEngDetailListDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
 	form.elements['workRptEqEngDetailListDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
 	form.elements['workRptEqEngDetailListDTO.eqCtgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
 	form.elements['workRptEqEngDetailListDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
 	form.elements['workRptEqEngDetailListDTO.equipDesc'].value = getValueById(myGrid, selectedId,'EQUIPDESC');
 	
 	goCommonTabPage(form, '' , pageId);
 }

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelServerAction("workRptEqEngList", workRptEqEngListForm );  
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptEqEngList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptEqEngCommonDTO.filterPlant"/>
<html:hidden property="workRptEqEngCommonDTO.filterEqLocId"/>
<html:hidden property="workRptEqEngCommonDTO.filterUsageDeptId"/>
<html:hidden property="workRptEqEngCommonDTO.filterEqCtgId"/>
<html:hidden property="workRptEqEngCommonDTO.filterEquipId"/>

<html:hidden property="workRptEqEngDetailListDTO.plantId"/>
<html:hidden property="workRptEqEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptEqEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptEqEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.eqCtgId"/>
<html:hidden property="workRptEqEngDetailListDTO.eqCtgDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.equipId"/>
<html:hidden property="workRptEqEngDetailListDTO.equipDesc"/>

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
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptEqEngCommonDTO.filterYear" tabindex="10" />
							<p class="open_year_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장  -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="workRptEqEngCommonDTO.filterPlantDesc" tabindex="20"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="workRptEqEngCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="workRptEqEngCommonDTO.filterUsageDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="workRptEqEngCommonDTO.filterEqCtgDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_box">
						<html:text property="workRptEqEngCommonDTO.filterEquipDesc" tabindex="60"/>
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
</html:form> 
</body>
</html>