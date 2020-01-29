<%--===========================================================================
자재검색 AC LOV Popup
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.part.list.action.LovPartsListAction"%>
<html>
<head>
<!-- 부품검색 -->
<title><bean:message key="LABEL.partSearch"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var partNameAc;
var deptAc;
var partGroupAc;
var equipAc;
var wareHouseAc;

var conditionFilterMap = {
	"wcode_id":"lovPartsListDTO.filterWId"
	,"wcode_desc":"lovPartsListDTO.filterWDesc"
	,"equip_id":"lovPartsListDTO.filterEquipId"
	,"equip_desc":"lovPartsListDTO.filterEquipDesc"
	,"eqasmb_id":"lovPartsListDTO.filterEqAsmbId"
	,"eqasmb_desc":"lovPartsListDTO.filterEqAsmbDesc"
	,"part_categ":"lovPartsListDTO.filterPartCategCode"
};

function loadPage() 
{
	convertCondition();
	
	initGrid();

	var whCateg = "";
	if(lovPartsListForm.elements['lovPartsListDTO.filterPartCategCode'].value == "SPPT") {
		whCateg = "PART";
	} else if (lovPartsListForm.elements['lovPartsListDTO.filterPartCategCode'].value == "TOOL") {
		whCateg = "TOOL";
	}
	
	// 설비 자동완성 
    equipAc = new autoC({"lovPartsListDTO.filterEquipDesc":"description"});
    equipAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "lovPartsListDTO.filterEquipId":"equip_id"
    });
    equipAc.init();
       
    // 창고 자동완성
    wareHouseAc = new autoC({"lovPartsListDTO.filterWDesc":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
      , "wh_categ":whCateg
    });
    wareHouseAc.setAcDConditionMap({
    });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "lovPartsListDTO.filterWId":"wcode_id"
    });
    wareHouseAc.init();
    
	partNameAc = new autoC({"lovPartsListDTO.partDesc":"full_desc"});
	partNameAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   });
	partNameAc.setTable("TAPARTS");
	partNameAc.init();
	
	deptAc = new autoC({"lovPartsListDTO.deptDesc":"description"});
	deptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	   });
	deptAc.setTable("TADEPT");
	deptAc.setAcResultMap({
	    "lovPartsListDTO.deptId":"dept_id"
	});
	deptAc.init();

	partGroupAc = new autoC({"lovPartsListDTO.filterPartGroupDesc":"description"});
	partGroupAc.setAcDisplay("DESCRIPTION");
	partGroupAc.setAcConditionMap({
    	"dir_type":"PART_GROUP",
    	"is_use":"Y",
    	"comp_no":loginUser.compNo
		   });
	partGroupAc.setTable("TACDUSRD");
	partGroupAc.setAcResultMap({
	    "lovPartsListDTO.filterPartGroup":"cdusrd_no"
	});
	partGroupAc.init();
	
	//설비부위
    eqAsmbAc = new autoC({"lovPartsListDTO.filterEqAsmbDesc":"description"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"lovPartsListDTO.filterEquipId"
    });
    eqAsmbAc.setAcResultMap({
        "lovPartsListDTO.filterEqAsmbId":"eqasmb_id"
        ,"lovPartsListDTO.filterEqAsmbDesc":"description"
    });
    eqAsmbAc.init();
    
    lovPartsListForm.elements['lovPartsListDTO.filterIsUseDesc'].value = "Y";
    lovPartsListForm.elements['lovPartsListDTO.filterIsUse'].value = "Y";
	acSysDesc("lovPartsListDTO.filterIsUseDesc","lovPartsListDTO.filterIsUse","IS_USE",true);
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
	
//     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
//     	//lovPartsListForm.elements['workPmStdCalibValLovDTO.pmCalibStdTpId'].value = "";
//         return sortColumn("partsValLov", this, lovPartsListForm, "PART_ID", ind, direction);
//     });
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "partsValLov"); // grid, grid id
	

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
	lovPartsListForm.elements['strutsAction'].value = '<%=LovPartsListAction.LOV_PARTS_AC_FIND%>';
	var url = contextPath + "/partsValLov.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovPartsListForm), "PART_ID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "PART_ID");
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
	<html:form action="/partsValLov" >
		<html:hidden property="currentPageId"/>
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="lovPartsListDTO.deptId" />
		<html:hidden property="lovPartsListDTO.filterPartGroup" />
		<html:hidden property="lovPartsListDTO.filterEquipId" />
		<html:hidden property="lovPartsListDTO.filterEqAsmbId" />
		<html:hidden property="lovPartsListDTO.filterWId" />
		<html:hidden property="lovPartsListDTO.filterIsUse" />
		<html:hidden property="lovPartsListDTO.filterPartCategCode" />
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
					<!-- 부품명/규격 -->
					<div class="field">
						<label><bean:message key="LABEL.partNameSize"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.partDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 부품코드 -->
					<div class="field">
						<label><bean:message key="LABEL.partCode"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.partNo" tabindex="20"/>
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovPartsListDTO.deptDesc" tabindex="30" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterModel" tabindex="40"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterMaker" tabindex="50"/>
                       </div>
                   </div>
                    <div class="field">
                        <label><bean:message key="LABEL.partGroup"/></label>
                        <div class="input_box">
                           <html:text property="lovPartsListDTO.filterPartGroupDesc" tabindex="60" />
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="lovPartsListDTO.filterVendorPtCode" tabindex="70"/>
                       </div>
                   </div>
                   <div class="field">
						<label><bean:message key="LABEL.remark"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.filterRemark" tabindex="80"/>
						</div>
					</div>
				   <!-- 설비 -->
                   <div class="field">
						<label><bean:message key="LABEL.equipment"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.filterEquipDesc" tabindex="90"/>
							<p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
						</div>
					</div>
				   <!-- 부위 -->
                   <div class="field">
						<label>부위명</label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.filterEqAsmbDesc" tabindex="95"/>
							<p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
						</div>
					</div>
				   <!-- 창고명 -->
                   <div class="field">
						<label><bean:message key="LABEL.wname"/></label>
						<div class="input_box">
							<input type='text' name="lovPartsListDTO.filterWDesc" tabindex="100"/>
							<p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
						</div>
					</div>
					<!-- 입고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.recDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterStartRecDate" tabindex="110" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterEndRecDate" tabindex="120" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 출고일자 -->
					<div class="field">
						<label><bean:message key="LABEL.issDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterStartIssDate" tabindex="130" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterEndIssDate" tabindex="140" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 사용일자 -->
					<div class="field">
						<label><bean:message key="LABEL.useDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterStartUseDate" tabindex="150" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovPartsListDTO.filterEndUseDate" tabindex="160" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- ERP 코드 -->
					<div class="field">
						<label><bean:message key="LABEL.erpPartNo"/></label>
						<div class="input_box">
							<html:text property="lovPartsListDTO.filterErpPartNo" tabindex="170" />
						</div>
					</div>
					<!-- 사용여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isUse"/></label>
						<div class="input_box">
							<html:text property="lovPartsListDTO.filterIsUseDesc" tabindex="180" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>					
					<c:set var="filePath" value="enhance/${compName}/part/list/partsValLov_${compNo}.jsp" />
					<c:if test="${udf:isExist(filePath)}">
						<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/list/partsValLov_${compNo}.jsp"></c:import>
					</c:if>								
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