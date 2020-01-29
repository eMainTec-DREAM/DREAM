<%--===========================================================================
순회점검 작업결과 상세
author  youngjoo38
version $Id: workListPtrlResultMstrDetail.jsp,v 1.0 2017/09/18 07:26:18 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.work.list.action.WorkListPtrlResultListAction"%>
<%@page import="dream.work.list.action.WorkListPtrlResultMstrDetailAction"%>
<html>
<head>
<!-- 순회점검 -->
<title><bean:message key="MENU.PTRLCAL"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var inspectorAc;

/** 자동완성 변수  */
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("workListPtrlResultMstrDetailDTO.ptrlDate","workListPtrlResultMstrDetailDTO.ptrlWorkTitle");
	
    setDisable(document.getElementsByName("disableDiv"));
	
	setForUpdate();

	//점검자 자동완성
    inspectorAc = new autoC({"workListPtrlResultMstrDetailDTO.ptrlInspectorDesc":"emp_name"});
    inspectorAc.setTable("TAEMP");
    inspectorAc.setAcResultMap({
        "workListPtrlResultMstrDetailDTO.ptrlInspectorId":"emp_id"
    });
    inspectorAc.init();

}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPMPTRLRSLTLIST_ID');
}

function setSequenceVal(sequenceVal)
{
	workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{
    var ptrlStatus = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlStatusId'].value;
    
    if(ptrlStatus == "C"){
        setDisableAll();
    }else{
        setEnableAll();
    }
    setForUpdate();
}   

 function goOpen(pageId)
{
    goTabPage(pageId);
} 

 function goTabPage(pageId)
{
    var form = document.workListPtrlResultMstrDetailForm;
    
    if(pageId == "maDocLibList" || pageId == "maPtDocLibList")
    {   
        workListPtrlResultMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value;
        workListPtrlResultMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTRLRESULT";  //WORESULT
        workListPtrlResultMstrDetailForm.elements['maDocLibCommonDTO.description'].value = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlWorkTitle'].value;  //제목
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
        goCommonTabPage(form, '' , pageId);
}

/**
 * 점검항목의 모든 결과값이 들어있는지 확인.
 */
var isValid = 0;

function checkPoint()
{
	isValid=0;
	
	var actionUrl = contextPath + "/workListPtrlResultMstrDetail.do";
    var param =  "&strutsAction=" + '<%= WorkListPtrlResultMstrDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(workListPtrlResultMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckPoint');
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid == '0')
    {
		workListPtrlResultMstrDetailForm.strutsAction.value = '<%=WorkListPtrlResultMstrDetailAction.DETAIL_PTRLCOMPLETED%>';
        
        var actionUrl = contextPath + "/workListPtrlResultMstrDetail.do";
        XMLHttpPost(actionUrl, FormQueryString(workListPtrlResultMstrDetailForm), 'afterPtrlcompleted');
    }else{
    	alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
    	return;
    }
}

 //순회완료
function goPtrlcompleted(){
    
    if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
            if(result){
                   //================================
                   // 필수 항목 체크한다.
                   //================================
                   if(checkValidation()) return;

                   checkPoint();
                   
                   }
           });
    }
}

/**
  * 순회 완료 후 호출
  */
 function afterPtrlcompleted(ajaxXmlDoc)
 {
	 //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     workListPtrlResultMstrDetailForm.elements['workListPtrlResultCommonDTO.pmPtrlRsltListId'].value = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value;
     if (parent.findGridRow)    parent.findGridRow(workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value);
     
     // 점검 완료일자 가져오기
     getPtrlComDate();
     // 점검 완료시간 가져오기
     getPtrlComTime();
     
     //parent.goOpen();
     
     workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlStatusId'].value = "C";
     valSysDirCode('workListPtrlResultMstrDetailDTO.ptrlStatusId', 'workListPtrlResultMstrDetailDTO.ptrlStatusDesc', 'PMSCHED_STATUS', true);
     setDisableAll();
     getTopPage().afterSaveAll(currentPageId);
     
  }
    
/**
 * 저장
 */ 
function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) workListPtrlResultMstrDetailForm.strutsAction.value = '<%=WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT%>';
	else workListPtrlResultMstrDetailForm.strutsAction.value = '<%= WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workListPtrlResultMstrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListPtrlResultMstrDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)  	parent.findGridRow(workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

// 점검완료일자 가져오기
function getPtrlComDate(){
    var actionUrl = contextPath + "/workListPtrlResultMstrDetail.do";
    var param =  "&strutsAction=" + '<%= WorkListPtrlResultMstrDetailAction.GET_PTRL_COM_DATE %>'
              +  "&" + FormQueryString(workListPtrlResultMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterGetPtrlComDate');
}

var ptrlComDate;
function afterGetPtrlComDate(ajaxXmlDoc)
{
	ptrlComDate = '0';
	ptrlComDate = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(ptrlComDate != '0')
    {
        closeModal();
        workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlComDate'].value = ptrlComDate;
    }
}


// 점검완료시간 가져오기
function getPtrlComTime(){
    var actionUrl = contextPath + "/workListPtrlResultMstrDetail.do";
    var param =  "&strutsAction=" + '<%= WorkListPtrlResultMstrDetailAction.GET_PTRL_COM_TIME%>'
              +  "&" + FormQueryString(workListPtrlResultMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterGetPtrlComTime');
}

var ptrlComTime;
function afterGetPtrlComTime(ajaxXmlDoc)
{
    ptrlComTime = '0';
    ptrlComTime = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(ptrlComTime != '0')
    {
        closeModal();
        workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlComTime'].value = ptrlComTime;
    }
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.pmParam'].value;
	var pmId     = workListPtrlResultMstrDetailForm.elements['workListPtrlResultMstrDetailDTO.ptrlWorkNo'].value;

	goPmstdList(pmId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListPtrlResultMstrDetail">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workListPtrlResultCommonDTO.pmPtrlRsltListId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.pmPtrlRsltListId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.pmPtrlSchedId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.ptrlWorkId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.deptId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.ptrlShiftTypeId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.managerId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.ptrlInspectorId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.ptrlStatusId"/>
	<html:hidden property="workListPtrlResultMstrDetailDTO.pmParam"/>
    <html:hidden property="maDocLibCommonDTO.objectId"/> 
    <html:hidden property="maDocLibCommonDTO.objectType"/>
    <html:hidden property="maDocLibCommonDTO.securGrade"/>
    <html:hidden property="maDocLibCommonDTO.docCateg"/>
    <html:hidden property="maDocLibCommonDTO.description"/>
    
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 순회작업# -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.ptrlNo"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.ptrlWorkNo" tabindex="10"/>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.ptrlStatusDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 순회업무명 -->
			<div class="field_long" name="disableDiv">
				<label><bean:message key="LABEL.ptrlWorkTitle"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.ptrlWorkTitle" tabindex="30"/>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="MENU.DEPT"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.deptDesc" tabindex="40"/>
				</div>
			</div>
			<!-- 교대조 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.ptrlShiftTypeDesc" tabindex="50"/>
				</div>
			</div>
			<!-- 예정일 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.schedDate"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.ptrlDate" tabindex="60"/>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="workListPtrlResultMstrDetailDTO.managerDesc" tabindex="70"/>
				</div>
			</div>
			<!-- 점검완료시간 -->
            <div class="field" name="disableDiv">
                <label><bean:message key="LABEL.ptrlCompleteTime"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="workListPtrlResultMstrDetailDTO.ptrlComDate" tabindex="80" />
                    </div>
                    <div class="input_box">
                        <html:text property="workListPtrlResultMstrDetailDTO.ptrlComTime" tabindex="90" />
                    </div>
                </div>
            </div>
            <!-- 점검자 -->
            <div class="field">
                <label><bean:message key="LABEL.ptrlInspector"/></label>
                <div class="input_box">
                    <html:text property="workListPtrlResultMstrDetailDTO.ptrlInspectorDesc" tabindex="100"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="workListPtrlResultMstrDetailDTO.remark" styleClass="ta50" tabindex="110" />
                </div>
            </div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>