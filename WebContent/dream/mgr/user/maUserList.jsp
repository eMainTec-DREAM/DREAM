<%--===========================================================================
사용자 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.user.action.MaUserListAction" %>
<%@ page import="dream.mgr.user.action.MaUserDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 사용자 -->
<title><bean:message key='MENU.USER'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var usrGrpAc;
var deptAc;
var mainMngAc;
var plantAc;
var vendorAc;
function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	maUserListForm.elements['maUserCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maUserListForm.elements['maUserCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	maUserListForm.elements['maUserCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
	maUserListForm.elements['maUserCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
	
	// 사용여부 - 기본 선택.
    maUserListForm.elements['maUserCommonDTO.filterIsUse'].value = "Y";
    maUserListForm.elements['maUserCommonDTO.filterIsUseDesc'].value = "Y";
    
	// 잠김여부 - 기본 선택.
    maUserListForm.elements['maUserCommonDTO.filterIsLocked'].value = "N";
    maUserListForm.elements['maUserCommonDTO.filterIsLockedDesc'].value = "N";
	
	initGrid();
	usrGrpAc = new autoC({"maUserCommonDTO.filterUsrGrpName":"group_name"});
	usrGrpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
	usrGrpAc.setTable("TAUSRGRP");
	usrGrpAc.setAcResultMap({
        "maUserCommonDTO.filterUsrGrpId":"usrgrp_id"
    });
	usrGrpAc.init();
	
	deptAc = new autoC({"maUserCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant" : "maUserCommonDTO.filterPlantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maUserCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maUserCommonDTO.filterUserName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maUserCommonDTO.filterDeptId",
    	"plant" : "maUserCommonDTO.filterPlantId"
    });
    mainMngAc.init();

    // 공장명
    plantAc = new autoC({"maUserCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maUserCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 거래처명
	vendorAc = new autoC({"maUserCommonDTO.filterVendorDesc":"description"});
	vendorAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
	vendorAc.setTable("TAVENDOR");
	vendorAc.setAcResultMap({
        "maUserCommonDTO.filterVendorId":"vendor_id"
    });
	vendorAc.init();
	
    acSysDesc("maUserCommonDTO.filterEqCtgTypeDesc","maUserCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    acSysDesc("maUserCommonDTO.filterIsUseDesc","maUserCommonDTO.filterIsUse","IS_USE");
    acSysDesc("maUserCommonDTO.filterIsLockedDesc","maUserCommonDTO.filterIsLocked","IS_USE");
    acSysDesc("maUserCommonDTO.filterIsMonitor","maUserCommonDTO.filterIsMonitor","IS_USE");
    acSysDesc("maUserCommonDTO.filterIsDirectDesc","maUserCommonDTO.filterIsDirectDesc","IS_USE");

}
//onRowSelect  onRowSelect

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
   myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   	maUserListForm.elements['maUserCommonDTO.userId'].value = "";
   	return sortColumn("maUserList", this, maUserListForm, "USERID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maUserList.do";
    maUserListForm.elements['strutsAction'].value = '<%=MaUserListAction.USER_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserListForm), "userId", "Y");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maUserListForm.elements['maUserCommonDTO.userId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
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
   	maUserListForm.elements['maUserCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maUserListForm.elements['maUserCommonDTO.userId'].value = getValueById(myGrid, selectedId, 'userId');

    goCommonTabPage(maUserListForm, <%=MaUserDetailAction.USER_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_userId)
{
	maUserListForm.elements['maUserCommonDTO.userId'].value = _userId;
	findGridList('ReloadRow');
	maUserListForm.elements['maUserCommonDTO.userId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maUserDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maUserListForm.elements['maUserCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maUserListForm.elements['maUserCommonDTO.userId'].value = getValueById(myGrid, selectedId, 'userId');
    maUserListForm.elements['strutsAction'].value = '<%=MaUserDetailAction.USER_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maUserListForm), 'maUserDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maUserDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maUserListForm.elements['maUserCommonDTO.userId'].value = "";
    goCommonTabPage(maUserListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	maUserListForm.elements['maUserCommonDTO.userId'].value = "";
	excelServerAction('maUserList', maUserListForm);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'userId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maUserListForm.strutsAction.value = '<%=MaUserListAction.USER_LIST_DELETE%>';
    var url = contextPath + "/maUserList.do";
    
    $.post(url,FormQueryString(maUserListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maUserDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserListForm.elements['maUserCommonDTO.userId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maUserCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maUserCommonDTO.userId"/><!-- Key -->
<html:hidden property="maUserCommonDTO.filterUsrGrpId"/>
<html:hidden property="maUserCommonDTO.filterDeptId"/>
<html:hidden property="maUserCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maUserCommonDTO.filterIsUse"/>
<html:hidden property="maUserCommonDTO.filterIsLocked"/>
<html:hidden property="maUserCommonDTO.filterPlantId"/>
<html:hidden property="maUserCommonDTO.filterVendorId"/>
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
                <div class="field">
	                <label><bean:message key="LABEL.userGroup"/></label>
		               <div class="input_box"">
		                    <html:text property="maUserCommonDTO.filterUsrGrpName" tabindex="10"/>
		                    <p class="open_spop"><a><span>조회</span></a></p>
		               </div>
                   </div> 
	               <div class="field">
		               <label><bean:message key="LABEL.userName1"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maUserCommonDTO.filterUserName" tabindex="20"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="LABEL.dept"/></label>
                       <div class="input_box"">
                            <html:text property="maUserCommonDTO.filterDeptDesc" tabindex="30"/>
                            <p class="open_spop"><a><span>조회</span></a></p>
                       </div>
               	   </div> 
                   <div class="field">
                       <label><bean:message key="LABEL.userId"/></label>
                       <div class="input_box">
                            <html:text property="maUserCommonDTO.filterUserNo" tabindex="40"/>
                       </div>
                   </div>
					<!-- 관리 설비유형  -->
					<div class="field">
						<label><bean:message key="LABEL.mngEqCtgType"/></label>
						<div class="input_box">
							<html:text property="maUserCommonDTO.filterEqCtgTypeDesc" tabindex="40"/>
							<p class="open_spop"><a><span>조회</span></a></p>
						</div>
					</div>
					<div class="field">
						<label><bean:message key="LABEL.isUse"/></label>
			            <div class="input_box">
			                <html:text property="maUserCommonDTO.filterIsUseDesc" tabindex="50"/>
			                <p class="open_spop"><a><span>조회</span></a></p>
			            </div>
			        </div>
				<!-- 잠김여부 -->
					<div class="field">
						<label><bean:message key="LABEL.isLocked"/></label>
			            <div class="input_box">
			                <html:text property="maUserCommonDTO.filterIsLockedDesc" tabindex="60"/>
			                <p class="open_spop"><a><span>조회</span></a></p>
			            </div>
			        </div>
					<!-- 공장명  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.plantDesc"/></label>
	                    <div class="input_box">
							<html:text property="maUserCommonDTO.filterPlantDesc" tabindex="90" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
					<!-- 모니터링대상 여부  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.isMonitor"/></label>
	                    <div class="input_box">
							<html:text property="maUserCommonDTO.filterIsMonitor" tabindex="100" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
	                <!-- 직영여부  -->
	                <div class="field">
	                    <label><bean:message key="LABEL.isDirect"/></label>
	                    <div class="input_box">
							<html:text property="maUserCommonDTO.filterIsDirectDesc" tabindex="110" />
							<p class="open_spop"><a><span>조회</span></a></p>
	                    </div>
	                </div>
	                <!-- 거래처 -->
	                <div class="field">
		               <label><bean:message key="LABEL.vendor"/></label>
                       <div class="input_box"">
                            <html:text property="maUserCommonDTO.filterVendorDesc" tabindex="30"/>
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