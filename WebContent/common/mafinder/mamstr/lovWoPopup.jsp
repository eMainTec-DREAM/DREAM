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
var eqLocDescAc;
function loadPage() 
{

	//작업시작일자, 종료일자 넣기.
	lovWoListForm.elements['lovWoListDTO.filterStartDate'].value   = getMinusDay(7);
	lovWoListForm.elements['lovWoListDTO.filterEndDate'].value   = getToday();

	//부서
	lovWoListForm.elements['lovWoListDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	lovWoListForm.elements['lovWoListDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	initGrid();
	
	eqLocDescAc = new autoC({"lovWoListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "lovWoListDTO.filterEqLocId":"eqloc_id"
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
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("lovWoList", this, lovWoListForm, "wkorId", ind, direction);
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovWoList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWoListForm;	
	form.strutsAction.value = '<%=LovWoListAction.LOV_WO_FIND%>';
	var url = contextPath + "/lovWoList.do";
	/* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    }); */
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
	var returnArray = new Array();
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId==null) return;

	returnArray[0] = getValueById(myGrid, selectedId,"wkorId"); // ID
    returnArray[1] = getValueById(myGrid, selectedId,"woNo"); // wname
    returnArray[2] = getValueById(myGrid, selectedId,"woDesc"); // wname
    returnArray[3] = getValueById(myGrid, selectedId,"woStatusDesc"); // wname
    returnArray[4] = getValueById(myGrid, selectedId,"equipDesc"); // wname
    returnArray[5] = getValueById(myGrid, selectedId,"eqLocDesc"); // wname

	var dirType = "";
	
// 	opener.setLovValue(returnArray, dirType);
    getIframeContent().setLovValue(returnArray, dirType);
// 	self.close();
    closeLayerPopup();
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
	<html:form action="/lovWoList" >
		<html:hidden property="lovWoListDTO.extCode1" />
		<html:hidden property="lovWoListDTO.extCode2" />
		<html:hidden property="lovWoListDTO.pmType" />
		<html:hidden property="lovWoListDTO.woType" />
		<html:hidden property="lovWoListDTO.woStatus" />
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
							<html:text property="lovWoListDTO.filterEquipDesc" tabindex="35"
									onkeydown="validationKeyDown('lovWoListDTO.filterEquipDesc', 'lovWoListDTO.filterEquipId');"/>
							<p class="open_spop">
								<a href="javascript:lovEquip('lovWoListDTO.filterEquipId','lovWoListDTO.filterEquipDesc');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리부서 -->
					<div class="field">
						<label><bean:message key="LABEL.dept"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterDeptDesc" tabindex="40"
										onkeydown="validationKeyDown('lovWoListDTO.filterDeptDesc', 'lovWoListDTO.filterDeptId');"/>
							<p class="open_spop"><a href="javascript:lovDept('lovWoListDTO.filterDeptId', '', 'lovWoListDTO.filterDeptDesc');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEmpDesc" tabindex="50"
										onkeydown="validationKeyDown('lovWoListDTO.filterEmpDesc', 'lovWoListDTO.filterEmpId');"/>
							<p class="open_spop"><a href="javascript:lovTable('lovWoListDTO.filterEmpId', 'lovWoListDTO.filterEmpDesc','TAEMPNAME');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterEqLocDesc" tabindex="60" />
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
							<html:text property="lovWoListDTO.filterEqCtgDesc" tabindex="70" 
									onblur="validationKeyDown('lovWoListDTO.filterEqCtgDesc', 'lovWoListDTO.filterEqCtgId');"/>
							<p class="open_spop">
								<a href="javascript:lovEqCtg('lovWoListDTO.filterEqCtgId', 'lovWoListDTO.filterEqCtgDesc');">
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
							<html:text property="lovWoListDTO.filterIsLawEq" tabindex="90" />
							<p class="open_spop">
								<a href="javascript:lovTable('lovWoListDTO.filterIsLawEq', 'lovWoListDTO.filterIsLawEq','YN');">
									<span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 관리자(정) -->
					<div class="field">
						<label><bean:message key="LABEL.mainManager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterMainMngName" tabindex="100"
										onkeydown="validationKeyDown('lovWoListDTO.filterMainMngName', 'lovWoListDTO.filterMainMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovWoListDTO.filterMainMngId', '', 'lovWoListDTO.filterMainMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 관리자(부) -->
					<div class="field">
						<label><bean:message key="LABEL.subManager"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterSubMngName" tabindex="110"
										onkeydown="validationKeyDown('lovWoListDTO.filterSubMngName', 'lovWoListDTO.filterSubMngId');"/>
							<p class="open_spop"><a href="javascript:lovEmp('lovWoListDTO.filterSubMngId', '', 'lovWoListDTO.filterSubMngName');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업종류  -->
					<div class="field">
						<label><bean:message key="LABEL.woType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterWoTypeDesc" tabindex="120" 
										onkeydown="validationKeyDown('lovWoListDTO.filterWoTypeDesc', 'lovWoListDTO.filterWoTypeId');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoListDTO.filterWoTypeId', 'lovWoListDTO.filterWoTypeDesc','WO_TYPE');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업형태  -->
					<div class="field">
						<label><bean:message key="LABEL.pmType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterPmTypeDesc" tabindex="130" 
										onkeydown="validationKeyDown('lovWoListDTO.filterPmTypeDesc', 'lovWoListDTO.filterPmTypeId');"/>
							<p class="open_spop"><a href="javascript:lovTable('lovWoListDTO.filterPmTypeId', 'lovWoListDTO.filterPmTypeDesc','PM_TYPE','','lovWoListDTO.filterWoTypeId');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 작업상태  -->
					<div class="field">
						<label><bean:message key="LABEL.woStatus"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.filterWoStatusDesc" tabindex="140" 
										onkeydown="validationKeyDown('lovWoListDTO.filterWoStatusDesc', 'lovWoListDTO.filterWoStatus');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoListDTO.filterWoStatus', 'lovWoListDTO.filterWoStatusDesc','WO_STATUS');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 자가/외주  -->
					<div class="field">
						<label><bean:message key="LABEL.selfVendorType"/></label>
						<div class="input_box">
							<html:text property="lovWoListDTO.selfVendorTypeDesc" tabindex="150"/>
							<p class="open_spop"><a href="javascript:lovSysDir('lovWoListDTO.selfVendorType', 'lovWoListDTO.selfVendorTypeDesc','SELF_VENDOR_TYPE');"><span>조회</span></a></p>
						</div>
					</div>
					<!-- 거래처 -->
					<div class="field">
						<label><bean:message key="LABEL.vendor"/></label>
						<div class="input_box">
		                    <html:text property="lovWoListDTO.vendorDesc" tabindex="160"/>
		                    <p class="open_spop">
		                        <a href="javascript:lovVendor('lovWoListDTO.vendorId'
		                                                    , ''
		                                                    , 'lovWoListDTO.vendorDesc'
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