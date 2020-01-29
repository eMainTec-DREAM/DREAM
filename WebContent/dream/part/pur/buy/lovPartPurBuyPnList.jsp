<%--===========================================================================
현장신청부품 선택 Lov
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.part.pur.buy.action.LovPartPurBuyPnListAction"%>
<html>
<head>
<!-- 현장신청부품 -->
<title><bean:message key="LABEL.pnItem"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var plantAc;
var userAc;
var deptAc;
var equipAc;

var conditionFilterMap = {
	"plant":"lovPartPurBuyPnListDTO.filterPlantId"
	,"plant_desc":"lovPartPurBuyPnListDTO.filterPlantDesc"
};

function loadPage() 
{
	convertCondition();
	
	initGrid();
	
	/** 공장 */
    plantAc = new autoC({"lovPartPurBuyPnListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "lovPartPurBuyPnListDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    /** 신청자  */
    userAc = new autoC({"lovPartPurBuyPnListDTO.filterUserDesc":"emp_name"});
    userAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    userAc.setTable("TAEMP");
    userAc.setAcResultMap({
        "lovPartPurBuyPnListDTO.filterUserId":"emp_id"
    });
    userAc.setAcDConditionMap({
    	"dept_id" : "lovPartPurBuyPnListDTO.filterDeptId"
    	,"plant" : "lovPartPurBuyPnListDTO.filterPlantId"
    });
    userAc.init();
    
    /** 신청부서  */
    deptAc = new autoC({"lovPartPurBuyPnListDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovPartPurBuyPnListDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "lovPartPurBuyPnListDTO.filterPlantId"
    });
    deptAc.init();
    
    /** 사용설비  */
    equipAc = new autoC({"lovPartPurBuyPnListDTO.filterEquipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "lovPartPurBuyPnListDTO.filterDeptId":"dept_id"
    });
    equipAc.setAcDConditionMap({
    	"dept_id" : "lovPartPurBuyPnListDTO.filterDeptId"
    	,"plant" : "lovPartPurBuyPnListDTO.filterPlantId"
    });
    equipAc.init();
}

/**
 * 그리드 초기화
 */
function initGrid()
{	
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("lovPartPurBuyPnList", this, lovPartPurBuyPnListForm, "PTPNLISTID", ind, direction);
    });
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovPartPurBuyPnList"); // grid, grid id
}

function convertCondition()
{
	var paramObj = JSON.parse(M$('param').value);
	for(var key in paramObj){
		if(typeof conditionFilterMap[key] != "undefined" && typeof M$(conditionFilterMap[key]) == "object")
		{
			M$(conditionFilterMap[key]).value = paramObj[key];
			paramObj[key] = '';
			M$('param').value = JSON.stringify(paramObj);
		}
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	lovPartPurBuyPnListForm.elements['strutsAction'].value = '<%=LovPartPurBuyPnListAction.LOV_AC_FIND%>';
	var url = contextPath + "/lovPartPurBuyPnList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovPartPurBuyPnListForm), "PART_ID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "PTPNLISTID");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList('Search');
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovPartPurBuyPnList" >
		<html:hidden property="currentPageId"/>
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="lovPartPurBuyPnListDTO.ptPnListId" />
		<html:hidden property="lovPartPurBuyPnListDTO.filterPlantId" />
		<html:hidden property="lovPartPurBuyPnListDTO.filterUserId" />
		<html:hidden property="lovPartPurBuyPnListDTO.filterDeptId" />
		<html:hidden property="lovPartPurBuyPnListDTO.filterEquipId" />
		<html:hidden property="strutsAction" />
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
					<!-- 요청번호 -->
					<div class="field">
						<label><bean:message key="LABEL.reqNo"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterPtPnListNo" tabindex="10"/>
						</div>
					</div>
					<!-- 부품번호 -->
					<div class="field">
						<label><bean:message key="LABEL.partNo"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterPartNo" tabindex="20"/>
						</div>
					</div>
					<!-- 신청일자 -->
					<div class="field">
						<label><bean:message key="LABEL.reDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovPartPurBuyPnListDTO.filterReqStartDate" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovPartPurBuyPnListDTO.filterReqEndDate" tabindex="40" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 공장  -->
					<div class="field">
					    <label><bean:message key="LABEL.plant"/></label>
					    <div class="input_box">
					        <html:text property="lovPartPurBuyPnListDTO.filterPlantDesc" tabindex="50"/>
					        <p class="open_spop">
					            <a>
					                <span>조회</span>
					            </a>
					        </p>
					    </div>
					</div>
		            <!-- 부품명 -->
					<div class="field">
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterPartNameSize" tabindex="60"/>
						</div>
					</div>
					<!-- 신청자 -->
					<div class="field">
						<label><bean:message key="LABEL.reqBy"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterUserDesc" tabindex="70"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
	                <!-- 신청부서 -->
	                <div class="field">
	                    <label><bean:message key="LABEL.entDept"/></label>
	                    <div class="input_box">
	                        <html:text property="lovPartPurBuyPnListDTO.filterDeptDesc" tabindex="80" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
					<!-- 사용용도 -->
					<div class="field">
						<label><bean:message key="LABEL.usePur"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterUsage" tabindex="90"/>
						</div>
					</div>
					<!-- 사용설비 -->
					<div class="field">
						<label><bean:message key="LABEL.usedEquip"/></label>
						<div class="input_box">
							<html:text property="lovPartPurBuyPnListDTO.filterEquipDesc" tabindex="100"/>
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