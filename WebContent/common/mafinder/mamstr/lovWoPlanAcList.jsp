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
<%@ page import="common.mafinder.mamstr.action.LovWoPlanAcListAction"%>
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
var eqLocDescAc;
function loadPage() 
{

	//작업시작일자, 종료일자 넣기.
	lovWoPlanAcListForm.elements['lovWoPlanAcListDTO.filterStartDate'].value   = getMinusDay(7);
	lovWoPlanAcListForm.elements['lovWoPlanAcListDTO.filterEndDate'].value   = getToday();

	//부서
	lovWoPlanAcListForm.elements['lovWoPlanAcListDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	lovWoPlanAcListForm.elements['lovWoPlanAcListDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	initGrid();
	
	eqLocDescAc = new autoC({"lovWoPlanAcListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "lovWoPlanAcListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWoPlanAcList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWoPlanAcListForm;	
	form.strutsAction.value = '<%=LovWoPlanAcListAction.LOV_WO_AC_FIND%>';
	var url = contextPath + "/lovWoPlanAcList.do";
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
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovWoPlanAcList" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovWoPlanAcListDTO.extCode1" />
		<html:hidden property="lovWoPlanAcListDTO.extCode2" />
		<html:hidden property="lovWoPlanAcListDTO.pmType" />
		<html:hidden property="lovWoPlanAcListDTO.woType" />
		<html:hidden property="lovWoPlanAcListDTO.woStatus" />
		<html:hidden property="lovWoPlanAcListDTO.multiSelect" />
		<html:hidden property="lovWoPlanAcListDTO.filterDeptId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterEquipId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterEmpId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterEqLocId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterEqCtgId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterMainMngId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterSubMngId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterPlfTypeId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterPmTypeId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterWoTypeId"/>
		<html:hidden property="lovWoPlanAcListDTO.filterWoStatus"/>
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
					<!-- 작업번호 -->
					<div class="field">
						<label><bean:message key="LABEL.woNo"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterWoNo" tabindex="1"/>
						</div>
					</div>
					<!-- 작업명 -->
					<div class="field">
						<label><bean:message key="LABEL.woName"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterWkOrDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 작업일자 -->
					<div class="field">
						<label><bean:message key="LABEL.woDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovWoPlanAcListDTO.filterStartDate" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovWoPlanAcListDTO.filterEndDate" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterEquipDesc" tabindex="35"
									onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterEquipDesc', 'lovWoPlanAcListDTO.filterEquipId');"/>
							<p class="open_spop">
								<a href="javascript:lovEquip('lovWoPlanAcListDTO.filterEquipId','lovWoPlanAcListDTO.filterEquipDesc');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterDeptDesc" tabindex="40"
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterDeptDesc', 'lovWoPlanAcListDTO.filterDeptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('lovWoPlanAcListDTO.filterDeptId', '', 'lovWoPlanAcListDTO.filterDeptDesc');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterEmpDesc" tabindex="50"
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterEmpDesc', 'lovWoPlanAcListDTO.filterEmpId');"/>
							<p class="open_spop"><a href="javascript:lovTable('lovWoPlanAcListDTO.filterEmpId', 'lovWoPlanAcListDTO.filterEmpDesc','TAEMPNAME');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterEqLocDesc" tabindex="60" />
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 종류 -->
					<div class="field">
						<label><bean:message key="LABEL.type"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterEqCtgDesc" tabindex="70" 
									onblur="validationKeyDown('lovWoPlanAcListDTO.filterEqCtgDesc', 'lovWoPlanAcListDTO.filterEqCtgId');"/>
							<p class="open_spop">
								<a href="javascript:lovEqCtg('lovWoPlanAcListDTO.filterEqCtgId', 'lovWoPlanAcListDTO.filterEqCtgDesc');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 내/외자  -->
					<!-- 법정설비여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLawEq"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterIsLawEq" tabindex="90" />
							<p class="open_spop">
								<a href="javascript:lovTable('lovWoPlanAcListDTO.filterIsLawEq', 'lovWoPlanAcListDTO.filterIsLawEq','YN');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterMainMngName" tabindex="100"
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterMainMngName', 'lovWoPlanAcListDTO.filterMainMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovWoPlanAcListDTO.filterMainMngId', '', 'lovWoPlanAcListDTO.filterMainMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterSubMngName" tabindex="110"
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterSubMngName', 'lovWoPlanAcListDTO.filterSubMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovWoPlanAcListDTO.filterSubMngId', '', 'lovWoPlanAcListDTO.filterSubMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업종류  -->
					<div class="field">
						<label><bean:message key="LABEL.woType"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterWoTypeDesc" tabindex="120" 
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterWoTypeDesc', 'lovWoPlanAcListDTO.filterWoTypeId');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoPlanAcListDTO.filterWoTypeId', 'lovWoPlanAcListDTO.filterWoTypeDesc','WO_TYPE');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업형태  -->
					<div class="field">
						<label><bean:message key="LABEL.pmType"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterPmTypeDesc" tabindex="130" 
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterPmTypeDesc', 'lovWoPlanAcListDTO.filterPmTypeId');"/>
							<p class="open_spop"><a href="javascript:lovTable('lovWoPlanAcListDTO.filterPmTypeId', 'lovWoPlanAcListDTO.filterPmTypeDesc','PM_TYPE','','lovWoPlanAcListDTO.filterWoTypeId');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업상태  -->
					<div class="field">
						<label><bean:message key="LABEL.woStatus"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.filterWoStatusDesc" tabindex="140" 
										onkeydown="validationKeyDown('lovWoPlanAcListDTO.filterWoStatusDesc', 'lovWoPlanAcListDTO.filterWoStatus');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoPlanAcListDTO.filterWoStatus', 'lovWoPlanAcListDTO.filterWoStatusDesc','WO_STATUS');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 자가/외주  -->
					<div class="field">
						<label><bean:message key="LABEL.selfVendorType"/></label>
						<div class="input_box">
							<html:text property="lovWoPlanAcListDTO.selfVendorTypeDesc" tabindex="150"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoPlanAcListDTO.selfVendorType', 'lovWoPlanAcListDTO.selfVendorTypeDesc','SELF_VENDOR_TYPE');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 거래처 -->
					<div class="field">
						<label><bean:message key="LABEL.vendor"/></label>
						<div class="input_box">
		                    <html:text property="lovWoPlanAcListDTO.vendorDesc" tabindex="160"/>
		                    <p class="open_spop">
		                        <a href="javascript:lovVendor('lovWoPlanAcListDTO.vendorId'
		                                                    , ''
		                                                    , 'lovWoPlanAcListDTO.vendorDesc'
		                                                    , '', '', '', '', ''
		                                                    , '','');">
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