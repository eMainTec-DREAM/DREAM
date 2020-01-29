<%--===========================================================================
작업부품 Popup
author  ghlee
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
<%@ page import="common.mafinder.mamstr.action.LovWoPartsListAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 작업부품 -->
<title><bean:message key="LABEL.lovWoPartsAcList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var equipDescAc;
var workDescAc;
var deptAc;
var mainMngAc;
var subMngAc;
var empAc;
var eqLocDescAc;
var eqCtgTypeAc;
var vendorDescAc;
var pmTypeAc;

var isPmTypeLoad;
function loadPage() 
{

	//작업시작일자, 종료일자 넣기.
	lovWoPartsListForm.elements['lovWoPartsListDTO.filterStartDate'].value   = getMinusDay(7);
	lovWoPartsListForm.elements['lovWoPartsListDTO.filterEndDate'].value   = getToday();

	//부서
	lovWoPartsListForm.elements['lovWoPartsListDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	lovWoPartsListForm.elements['lovWoPartsListDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	initGrid();
	
    equipDescAc = new autoC({"lovWoPartsListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipDescAc.setAcResultMap({
        "lovWoPartsListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "lovWoPartsListDTO.filterEqLocId",
    	"eqctg_id" : "lovWoPartsListDTO.filterEqCtgId",
    	"dept_id" : "lovWoPartsListDTO.filterDeptId"
    });
    equipDescAc.init();
    
    workDescAc = new autoC({"lovWoPartsListDTO.filterWkOrDesc":"description"});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    workDescAc.setTable("TAWORKORDER");
    workDescAc.init();
    
    deptAc = new autoC({"lovWoPartsListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovWoPartsListDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"lovWoPartsListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "lovWoPartsListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "lovWoPartsListDTO.filterDeptId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"lovWoPartsListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "lovWoPartsListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "lovWoPartsListDTO.filterDeptId"
    });
    subMngAc.init();
    
    empAc = new autoC({"lovWoPartsListDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovWoPartsListDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "lovWoPartsListDTO.filterDeptId"
    });
    empAc.init();
    
    eqLocDescAc = new autoC({"lovWoPartsListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqLocDescAc.setAcResultMap({
        "lovWoPartsListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"lovWoPartsListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "lovWoPartsListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    
    vendorDescAc = new autoC({"lovWoPartsListDTO.vendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	});
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "lovWoPartsListDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
	//법정설비여부 AC
    acSysDesc("lovWoPartsListDTO.filterIsLawEq","lovWoPartsListDTO.filterIsLawEq","IS_USE");
	//작업종류 AC
    acSysDesc("lovWoPartsListDTO.filterWoTypeDesc","lovWoPartsListDTO.filterWoTypeId","WO_TYPE");
	// 작업상태 AC
    acSysDesc("lovWoPartsListDTO.filterWoStatusDesc","lovWoPartsListDTO.filterWoStatus","WO_STATUS");
	// 자가/외주 AC
    acSysDesc("lovWoPartsListDTO.selfVendorTypeDesc","lovWoPartsListDTO.selfVendorType","SELF_VENDOR_TYPE");
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
    	return sortColumn("lovWoPartsAcList", this, lovWoPartsListForm, "wkorId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWoPartsAcList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWoPartsListForm;	
	form.strutsAction.value = '<%=LovWoPartsListAction.LOV_WO_PARTS_AC_FIND%>';
	var url = contextPath + "/lovWoPartsAcList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "wkorId", "Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "wkorId");
}

/**
 * 검색
 */
function goSearch()
{
	findGridList("Search");
}

function goClose()
{
	closeLayerPopup(this);
}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
    if(code=="lovWoPartsListDTO.filterWoTypeDesc")
    {
        var listType = lovWoPartsListForm.elements['lovWoPartsListDTO.filterWoTypeId'].value+"_TYPE";
        
        setPmTypeAc(listType);
        isPmTypeLoad = true;
    }
}

// 작업형태 AC
function setPmTypeAc(listType)
{
	if(isPmTypeLoad)
    {
        // 작업종류를 재선택한 경우 distroy 후 로드한다.
        pmTypeAc.destroy();
        
        // 선택했던 작업형태 초기화
        lovWoPartsListForm.elements['lovWoPartsListDTO.filterPmTypeDesc'].value = "";
        lovWoPartsListForm.elements['lovWoPartsListDTO.filterPmTypeId'].value = "";
        
        isPmTypeLoad = false;
    }
	
	// 작업형태
    pmTypeAc = new autoC({"lovWoPartsListDTO.filterPmTypeDesc":"description"});
    pmTypeAc.setAcConditionMap({
        "list_type":listType,
        "is_use":"Y"
    });
    pmTypeAc.setTable("TACDSYSD");
    pmTypeAc.setKeyName("lovWoPartsListDTO.filterPmTypeId");
    pmTypeAc.setAcResultMap({
        "lovWoPartsListDTO.filterPmTypeId":"cdsysd_no"
    });
    pmTypeAc.init();
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovWoPartsAcList" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovWoPartsListDTO.extCode1" />
		<html:hidden property="lovWoPartsListDTO.extCode2" />
		<html:hidden property="lovWoPartsListDTO.pmType" />
		<html:hidden property="lovWoPartsListDTO.woType" />
		<html:hidden property="lovWoPartsListDTO.woStatus" />
		<html:hidden property="lovWoPartsListDTO.multiSelect" />
		<html:hidden property="lovWoPartsListDTO.filterDeptId"/>
		<html:hidden property="lovWoPartsListDTO.filterEquipId"/>
		<html:hidden property="lovWoPartsListDTO.filterEmpId"/>
		<html:hidden property="lovWoPartsListDTO.filterEqLocId"/>
		<html:hidden property="lovWoPartsListDTO.filterEqCtgId"/>
		<html:hidden property="lovWoPartsListDTO.filterMainMngId"/>
		<html:hidden property="lovWoPartsListDTO.filterSubMngId"/>
		<html:hidden property="lovWoPartsListDTO.filterPlfTypeId"/>
		<html:hidden property="lovWoPartsListDTO.filterPmTypeId"/>
		<html:hidden property="lovWoPartsListDTO.filterWoTypeId"/>
		<html:hidden property="lovWoPartsListDTO.filterWoStatus"/>
		<html:hidden property="lovWoPartsListDTO.selfVendorType"/>
		<html:hidden property="lovWoPartsListDTO.vendorId"/>
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
					</div>
				</div>
			</div><!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<!-- 작업번호 -->
					<div class="field">
						<label><bean:message key="LABEL.woNo"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterWoNo" tabindex="1"/>
						</div>
					</div>
					<!-- 작업명 -->
					<div class="field">
						<label><bean:message key="LABEL.woName"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterWkOrDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 작업일자 -->
					<div class="field">
						<label><bean:message key="LABEL.woDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovWoPartsListDTO.filterStartDate" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovWoPartsListDTO.filterEndDate" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterEquipDesc" tabindex="35"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterDeptDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterEmpDesc" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterEqLocDesc" tabindex="60" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 종류 -->
					<div class="field">
						<label><bean:message key="LABEL.type"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterEqCtgDesc" tabindex="70" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 내/외자  -->
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterIsLawEq" tabindex="90" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterMainMngName" tabindex="100"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterSubMngName" tabindex="110"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업종류  -->
					<div class="field">
						<label><bean:message key="LABEL.woType"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterWoTypeDesc" tabindex="120" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업형태  -->
					<div class="field">
						<label><bean:message key="LABEL.pmType"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterPmTypeDesc" tabindex="130" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업상태  -->
					<div class="field">
						<label><bean:message key="LABEL.woStatus"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.filterWoStatusDesc" tabindex="140" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 자가/외주  -->
					<div class="field">
						<label><bean:message key="LABEL.selfVendorType"/></label>
						<div class="input_box">
							<html:text property="lovWoPartsListDTO.selfVendorTypeDesc" tabindex="150"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 거래처 -->
					<div class="field">
						<label><bean:message key="LABEL.vendor"/></label>
						<div class="input_box">
		                    <html:text property="lovWoPartsListDTO.vendorDesc" tabindex="160"/>
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
					<div class="b_line"></div> 
					<div class="fb_group1">
						 
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