<%--===========================================================================
작업 Popup
author  ssong
version $Id: lovWoPopup.jsp,v 1.1 2016/02/18 09:12:01 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.mafinder.mamstr.action.LovWoListAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 작업 -->
<title><bean:message key="LABEL.lovWoPopup"/></title>
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
	lovWoListForm.elements['lovWoListDTO.filterStartDate'].value   = getMinusDay(7);
	lovWoListForm.elements['lovWoListDTO.filterEndDate'].value   = getToday();

	//부서
	lovWoListForm.elements['lovWoListDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	lovWoListForm.elements['lovWoListDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	initGrid();
	
    equipDescAc = new autoC({"lovWoListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipDescAc.setAcResultMap({
        "lovWoListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "lovWoListDTO.filterEqLocId",
    	"eqctg_id" : "lovWoListDTO.filterEqCtgId",
    	"dept_id" : "lovWoListDTO.filterDeptId"
    });
    equipDescAc.init();
    
    workDescAc = new autoC({"lovWoListDTO.filterWkOrDesc":"description"});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    workDescAc.setTable("TAWORKORDER");
    workDescAc.init();
    
    deptAc = new autoC({"lovWoListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "lovWoListDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"lovWoListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "lovWoListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "lovWoListDTO.filterDeptId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"lovWoListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "lovWoListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "lovWoListDTO.filterDeptId"
    });
    subMngAc.init();
    
    empAc = new autoC({"lovWoListDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "lovWoListDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "lovWoListDTO.filterDeptId"
    });
    empAc.init();
    
    eqLocDescAc = new autoC({"lovWoListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqLocDescAc.setAcResultMap({
        "lovWoListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"lovWoListDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "lovWoListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    
    vendorDescAc = new autoC({"lovWoListDTO.vendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	});
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "lovWoListDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
	//법정설비여부 AC
    acSysDesc("lovWoListDTO.filterIsLawEq","lovWoListDTO.filterIsLawEq","IS_USE");
	//작업종류 AC
    acSysDesc("lovWoListDTO.filterWoTypeDesc","lovWoListDTO.filterWoTypeId","WO_TYPE");
	// 작업상태 AC
    acSysDesc("lovWoListDTO.filterWoStatusDesc","lovWoListDTO.filterWoStatus","WO_STATUS");
	// 자가/외주 AC
    acSysDesc("lovWoListDTO.selfVendorTypeDesc","lovWoListDTO.selfVendorType","SELF_VENDOR_TYPE");
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
    	return sortColumn("lovWoAcList", this, lovWoListForm, "wkorId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWoAcList"); // grid, grid id
	
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWoListForm;	
	form.strutsAction.value = '<%=LovWoListAction.LOV_WO_AC_FIND%>';
	var url = contextPath + "/lovWoAcList.do";
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
    if(code=="lovWoListDTO.filterWoTypeDesc")
    {
        var listType = lovWoListForm.elements['lovWoListDTO.filterWoTypeId'].value+"_TYPE";
        
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
        lovWoListForm.elements['lovWoListDTO.filterPmTypeDesc'].value = "";
        lovWoListForm.elements['lovWoListDTO.filterPmTypeId'].value = "";
        
        isPmTypeLoad = false;
    }
	
	// 작업형태
    pmTypeAc = new autoC({"lovWoListDTO.filterPmTypeDesc":"description"});
    pmTypeAc.setAcConditionMap({
        "list_type":listType,
        "is_use":"Y"
    });
    pmTypeAc.setTable("TACDSYSD");
    pmTypeAc.setKeyName("lovWoListDTO.filterPmTypeId");
    pmTypeAc.setAcResultMap({
        "lovWoListDTO.filterPmTypeId":"cdsysd_no"
    });
    pmTypeAc.init();
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovWoAcList" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovWoListDTO.extCode1" />
		<html:hidden property="lovWoListDTO.extCode2" />
		<html:hidden property="lovWoListDTO.pmType" />
		<html:hidden property="lovWoListDTO.woType" />
		<html:hidden property="lovWoListDTO.woStatus" />
		<html:hidden property="lovWoListDTO.multiSelect" />
		<html:hidden property="lovWoListDTO.filterDeptId"/>
		<html:hidden property="lovWoListDTO.filterEquipId"/>
		<html:hidden property="lovWoListDTO.filterEmpId"/>
		<html:hidden property="lovWoListDTO.filterEqLocId"/>
		<html:hidden property="lovWoListDTO.filterEqCtgId"/>
		<html:hidden property="lovWoListDTO.filterMainMngId"/>
		<html:hidden property="lovWoListDTO.filterSubMngId"/>
		<html:hidden property="lovWoListDTO.filterPlfTypeId"/>
		<html:hidden property="lovWoListDTO.filterPmTypeId"/>
		<html:hidden property="lovWoListDTO.filterWoTypeId"/>
		<html:hidden property="lovWoListDTO.filterWoStatus"/>
		<html:hidden property="lovWoListDTO.selfVendorType"/>
		<html:hidden property="lovWoListDTO.vendorId"/>
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
							<html:text property="lovWoListDTO.filterWoNo" tabindex="1"/>
						</div>
					</div>
					<!-- 작업명 -->
					<div class="field">
						<label><bean:message key="LABEL.woName"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterWkOrDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 작업일자 -->
					<div class="field">
						<label><bean:message key="LABEL.woDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovWoListDTO.filterStartDate" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovWoListDTO.filterEndDate" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEquipDesc" tabindex="35"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterDeptDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEmpDesc" tabindex="50"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEqLocDesc" tabindex="60" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 종류 -->
					<div class="field">
						<label><bean:message key="LABEL.type"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEqCtgDesc" tabindex="70" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 내/외자  -->
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterIsLawEq" tabindex="90" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterMainMngName" tabindex="100"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterSubMngName" tabindex="110"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업종류  -->
					<div class="field">
						<label><bean:message key="LABEL.woType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterWoTypeDesc" tabindex="120" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업형태  -->
					<div class="field">
						<label><bean:message key="LABEL.pmType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterPmTypeDesc" tabindex="130" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업상태  -->
					<div class="field">
						<label><bean:message key="LABEL.woStatus"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterWoStatusDesc" tabindex="140" />
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 자가/외주  -->
					<div class="field">
						<label><bean:message key="LABEL.selfVendorType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.selfVendorTypeDesc" tabindex="150"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<!-- 거래처 -->
					<div class="field">
						<label><bean:message key="LABEL.vendor"/></label>
						<div class="input_box">
		                    <html:text property="lovWoListDTO.vendorDesc" tabindex="160"/>
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