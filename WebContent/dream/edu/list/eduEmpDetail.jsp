<%--===========================================================================
자격증 사원
author  kim21017
version $Id: eduEmpDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.edu.list.action.EduEmpDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html>
<head>
<!-- 작업자-->
<title><bean:message key="TAB.eduEmpList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("eduEmpDetailDTO.empNo","eduEmpDetailDTO.empName");
	setForUpdate();
	
	mainMngAc = new autoC({"eduEmpDetailDTO.empName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("eduEmpDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "eduEmpDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEMPTRAINLIST_ID');
	
}

function setSequenceVal(sequenceVal)
{
	eduEmpDetailForm.elements['eduEmpDetailDTO.empTrainListId'].value = sequenceVal;
}


function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)){
		//중복 작업자 체크
		validEmp();
		if(isValid!='0') return;
	}
	if(ckCreate(currentPageId)) eduEmpDetailForm.strutsAction.value = '<%=EduEmpDetailAction.EDU_EMP_DETAIL_INPUT%>';
	else eduEmpDetailForm.strutsAction.value = '<%= EduEmpDetailAction.EDU_EMP_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/eduEmpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(eduEmpDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(eduEmpDetailForm.elements['eduEmpDetailDTO.empTrainListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function validEmp(){
	var actionUrl = contextPath + "/eduEmpDetail.do";
	var param =  "&strutsAction=" + '<%= EduEmpDetailAction.EDU_EMP_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(eduEmpDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidEmp');
}

var isValid;
function afterValidEmp(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0035'/>");
		eduEmpDetailForm.elements['eduEmpDetailDTO.empId'].value = '';
		eduEmpDetailForm.elements['eduEmpDetailDTO.empName'].value = '';
    }
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.eduEmpDetailForm;

	if(pageId == "maDocLibList" || pageId == "eduEmpDocLibList")
	{	
		eduEmpDetailForm.elements['maDocLibCommonDTO.objectId'].value = eduEmpDetailForm.elements['eduEmpDetailDTO.empTrainListId'].value;
		eduEmpDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EMP_EDU";
		eduEmpDetailForm.elements['maDocLibCommonDTO.description'].value = eduEmpDetailForm.elements['eduEmpDetailDTO.empName'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = eduEmpDetailForm.elements['eduEmpDetailDTO.empTrainListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/eduEmpDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="eduCommonDTO.courseListId"/>
	<html:hidden property="eduEmpDetailDTO.empTrainListId"/>
	<html:hidden property="eduEmpDetailDTO.empNo"/>
	<html:hidden property="eduEmpDetailDTO.empId"/>
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 작업자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.empName"/></label>
				<div class="input_box">
					<html:text property="eduEmpDetailDTO.empName" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<div class="field">
                <label class="check"><bean:message key="LABEL.trainDate"/></label>
                <div class="calendar_wrap">
                    <div class="input_box input_carendar">
                        <html:text property="eduEmpDetailDTO.trainFdate" tabindex="20" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box input_carendar">
                        <html:text property="eduEmpDetailDTO.trainTdate" tabindex="30" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                </div>
            </div>
            <!-- 교육기관 -->
            <div class="field">
                <label><bean:message key="LABEL.trainAgency"/></label>
                <div class="input_box">
                    <html:text property="eduEmpDetailDTO.trainAgency" tabindex="40"/>
                </div>
            </div>
            <!-- 교육자 -->
            <div class="field">
                <label><bean:message key="LABEL.teacher"/></label>
                <div class="input_box">
                    <html:text property="eduEmpDetailDTO.teacher" tabindex="50"/>
                </div>
            </div>
            <!-- 교육장소 -->
            <div class="field">
                <label><bean:message key="LABEL.place"/></label>
                <div class="input_box">
                    <html:text property="eduEmpDetailDTO.place" tabindex="60"/>
                </div>
            </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="eduEmpDetailDTO.remark" styleClass="ta100" tabindex="70" />
				</div>
			</div>
			
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>