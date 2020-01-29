<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.user.action.ConsultCompUserListAction" %>
<%@ page import="dream.consult.comp.user.action.ConsultCompUserDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사용자 -->
<title><bean:message key='MENU.USER'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var isUseAc,compAc;
var deptAc;
var usrGrpAc;
var empAc;

function loadPage() 
{
	initGrid();
	
	// 사용여부 - 기본 선택.
    consultCompUserListForm.elements['consultCompUserCommonDTO.filterIsUseId'].value = "Y";
    consultCompUserListForm.elements['consultCompUserCommonDTO.filterIsUseDesc'].value = "Y";
    
    //사용여부 자동완성 (pop up)
    acSysDesc("consultCompUserCommonDTO.filterIsUseDesc","consultCompUserCommonDTO.filterIsUseId","IS_USE");
    
    //회사명 자동완성 (pop up)
    compAc = new autoC({"consultCompUserCommonDTO.filterCompDesc":"description"});
    //이 부분은 회사명 검색부분인데 검색 버튼을 누르면 밑에 처럼 
    //불러오고자 하는 테이블 세팅하고 그후에 어떤 변수를 어디에 담을 건지
    //그리고 어떤 키값으로 보낼건지 정하면 된다.
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompUserCommonDTO.filterCompNo");
    compAc.setAcResultMap({
        "consultCompUserCommonDTO.filterCompNo":"comp_no"
    });
    compAc.init();
    
    //권한 자동완성 (pop up)
    usrGrpAc = new autoC({"consultCompUserCommonDTO.filterUsrGrpDesc":"group_name"});
    usrGrpAc.setTable("TAUSRGRP");
    usrGrpAc.setAcDConditionMap({
    	"comp_no":"consultCompUserCommonDTO.filterCompNo"
    });
    usrGrpAc.setAcResultMap({
        "consultCompUserCommonDTO.filterUsrGrpId":"usrgrp_id"
    });
    usrGrpAc.init();
    
    //부서 자동완성 (pop up)
    deptAc = new autoC({"consultCompUserCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcDConditionMap({
    	"comp_no":"consultCompUserCommonDTO.filterCompNo"
    });
    deptAc.setAcResultMap({
        "consultCompUserCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    //사원 자동완성
    empAc = new autoC({"consultCompUserCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "consultCompUserCommonDTO.filterEmpId":"emp_id"
    });
    empAc.init();
    
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = "";
        return sortColumn("consultCompUserList", this, consultCompUserListForm, "USERID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompUserList.do";
    consultCompUserListForm.elements['strutsAction'].value = '<%=ConsultCompUserListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompUserListForm), "USERID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_userId)
{
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = _userId;
    findGridList('ReloadRow');
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = "";  // 검색시 Tab 이동Key Clear
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
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value =  getValueById(myGrid, selectedId,'USERID');  
    consultCompUserListForm.elements['consultCompUserCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
    
    goCommonTabPage(consultCompUserListForm, <%= ConsultCompUserDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultCompUserDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value =  getValueById(myGrid, selectedId,'USERID');  
    consultCompUserListForm.elements['consultCompUserCommonDTO.compNo'].value =  getValueById(myGrid, selectedId,'COMPNO');  
    consultCompUserListForm.elements['strutsAction'].value = '<%=ConsultCompUserDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultCompUserListForm), 'consultCompUserDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultCompUserDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = "";
    goCommonTabPage(consultCompUserListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'USERID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    consultCompUserListForm.strutsAction.value = '<%=ConsultCompUserListAction.LIST_DELETE%>';
    var url = contextPath + "/consultCompUserList.do";
    
    $.post(url,FormQueryString(consultCompUserListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultCompUserDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	consultCompUserListForm.elements['consultCompUserCommonDTO.userId'].value = "";
    excelServerAction("consultCompUserList", consultCompUserListForm );  
}

/**
 * Reset Password
 */
function goResetpw()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.CMSG058'/>", function(result){
		 if(result){
			 var resetArray = getSelectedRows(myGrid, 'isDelCheck', 'USERID', 'USERNO', 'COMPNO'); //Grid, check box column seq, pk column seq
			    if(typeof resetArray == "undefined"){
			        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			        return;
			    }

			    consultCompUserListForm.strutsAction.value = '<%=ConsultCompUserListAction.RESET_PW%>';
			    var url = contextPath + "/consultCompUserList.do";
			    
			    $.post(url,FormQueryString(consultCompUserListForm)+resetArray , function(_data){
			    	afterResetpw();
			    });
		 }
		});
	
}

function afterResetpw()
{
	goClose('consultCompUserDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.CMSG032"/>');
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompUserList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompUserCommonDTO.userId"/><!-- Key -->
<html:hidden property="consultCompUserCommonDTO.compNo"/><!-- Key -->

<html:hidden property="consultCompUserCommonDTO.filterCompNo"/>
<html:hidden property="consultCompUserCommonDTO.filterDeptId"/>
<html:hidden property="consultCompUserCommonDTO.filterIsUseId"/>
<html:hidden property="consultCompUserCommonDTO.filterUsrGrpId"/>

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
                <!-- 회사 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.compDesc"/></label>
                    <!--label class 에 check를 넣으면 * 이 추가된다. -->
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterCompDesc" tabindex="10"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 권한 -->
                <div class="field">
                    <label><bean:message key="LABEL.userGroup"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterUsrGrpDesc" tabindex="20"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 로그인 계정 ID -->
                <div class="field">
                    <label><bean:message key="LABEL.userId"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterUserNo" tabindex="30"/>
                    </div>
                </div>
                <!-- 계정명 -->
                <div class="field">
                    <label><bean:message key="LABEL.userName1"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterUserName" tabindex="40"/>
                    </div>
                </div>
                <!-- 부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.dept"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterDeptDesc" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultCompUserCommonDTO.filterIsUseDesc" tabindex="60" />
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

