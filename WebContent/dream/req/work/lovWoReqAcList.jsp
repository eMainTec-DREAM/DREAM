<%--===========================================================================
작업요청 팝업
author  syyang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.req.work.action.LovWoReqAcListAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 작업요청 -->
<title><bean:message key="LABEL.lovWoReqPopup"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

/** 자동완성 변수 */
var equipDescAc;
var eqLocDescAc;
var reqDeptAc;
var reqEmpAc;

function loadPage() 
{

	//작업시작일자, 종료일자 넣기.
	lovWoReqAcListForm.elements['lovWoReqAcListDTO.filterReqStartDate'].value   = getMinusDay(7);
	lovWoReqAcListForm.elements['lovWoReqAcListDTO.filterReqEndDate'].value   = getToday();

	//부서
	lovWoReqAcListForm.elements['lovWoReqAcListDTO.filterReqDeptId'].value    = "<%=user.getDeptId()%>";
	lovWoReqAcListForm.elements['lovWoReqAcListDTO.filterReqDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	initGrid();
	
    // 설비
    equipDescAc = new autoC({"lovWoReqAcListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipDescAc.setAcResultMap({
        "lovWoReqAcListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "lovWoReqAcListDTO.filterEqLocId",
    });
    equipDescAc.init();
    
    // 설비위치
	eqLocDescAc = new autoC({"lovWoReqAcListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "lovWoReqAcListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
	// 요청부서
    reqDeptAc = new autoC({"lovWoReqAcListDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcDisplay("DESCRIPTION");
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "lovWoReqAcListDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.init();
    
    //요청자
    reqEmpAc = new autoC({"lovWoReqAcListDTO.filterReqEmpDesc":"emp_name"});
    reqEmpAc.setAcDisplay("EMP_NAME");
    reqEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    reqEmpAc.setTable("TAEMP");
    reqEmpAc.setAcResultMap({
        "lovWoReqAcListDTO.filterReqEmpId":"emp_id"
    });
    reqEmpAc.setAcDConditionMap({
    	"dept_id" : "lovWoReqAcListDTO.filterReqDeptId"
    });
    reqEmpAc.init();
    
    //요청구분
    acSysDesc("lovWoReqAcListDTO.filterWoReqTypeDesc","lovWoReqAcListDTO.filterWoReqTypeId","WOREQ_TYPE");
	
    //진행상태
    acSysDesc("lovWoReqAcListDTO.filterWoReqStatusDesc","lovWoReqAcListDTO.filterWoReqStatus","WOREQ_STATUS");
	
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
	setHeader(myGrid, "gridbox", "goSearch", "lovWoReqAcList"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var form = document.lovWoReqAcListForm;	
	form.strutsAction.value = '<%=LovWoReqAcListAction.LOV_WOREQ_AC_FIND%>';
	var url = contextPath + "/lovWoReqAcList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "WOREQID", "Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "WOREQID");
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
	<html:form action="/lovWoReqAcList" >

<html:hidden property="currentPageId"/>
<html:hidden property="keyCode" /><html:hidden property="chName" />
<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
<html:hidden property="codeType" />
<html:hidden property="param" />
<html:hidden property="label" />
<html:hidden property="title" />

		<html:hidden property="lovWoReqAcListDTO.extCode1" />
		<html:hidden property="lovWoReqAcListDTO.multiSelect" />
		<html:hidden property="lovWoReqAcListDTO.filterReqDeptId"/>
		<html:hidden property="lovWoReqAcListDTO.filterEquipId"/>
		<html:hidden property="lovWoReqAcListDTO.filterReqEmpId"/>
		<html:hidden property="lovWoReqAcListDTO.filterEqLocId"/>
		<html:hidden property="lovWoReqAcListDTO.filterWoReqTypeId"/>
		<html:hidden property="lovWoReqAcListDTO.filterWoReqStatus"/>
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
					<!-- 요청번호 -->
					<div class="field">
						<label><bean:message key="LABEL.woReqNo"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterWoReqNo" tabindex="1"/>
						</div>
					</div>
					<!-- 요청명 -->
					<div class="field">
						<label><bean:message key="LABEL.woReqDesc"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterWoReqDesc" tabindex="10"/>
						</div>
					</div>
					<!-- 요청일자 -->
					<div class="field">
						<label><bean:message key="LABEL.appReqDate"/></label>
						<div class="calendar_wrap">
							<div class="input_box input_carendar">
								<html:text property="lovWoReqAcListDTO.filterReqStartDate" tabindex="20" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
							<div class="input_box input_carendar">
								<html:text property="lovWoReqAcListDTO.filterReqEndDate" tabindex="30" />
								<p class="open_calendar"><span>날짜</span></p>
							</div>
						</div>
					</div>
					<!-- 설비 -->
					<div class="field">
						<label><bean:message key="LABEL.equipDesc"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterEquipDesc" tabindex="35" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 위치 -->
					<div class="field">
						<label><bean:message key="LABEL.location"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterEqLocDesc" tabindex="60" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 요청부서 -->
					<div class="field">
						<label><bean:message key="LABEL.woReqDept"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterReqDeptDesc" tabindex="40" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 요청자 -->
					<div class="field">
						<label><bean:message key="LABEL.appReqBy"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterReqEmpDesc" tabindex="50" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 요청구분  -->
					<div class="field">
						<label><bean:message key="LABEL.woReqType"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterWoReqTypeDesc" tabindex="120" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 진행상태  -->
					<div class="field">
						<label><bean:message key="LABEL.proStatus"/></label>
						<div class="input_box">
							<html:text property="lovWoReqAcListDTO.filterWoReqStatusDesc" tabindex="140" />
							<p class="open_spop">
								<a><span>조회</span></a>
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