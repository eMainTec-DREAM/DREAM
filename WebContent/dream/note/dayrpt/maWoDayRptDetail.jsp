<%--===========================================================================
 - 상세
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.note.dayrpt.action.MaWoDayRptDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='LABEL.date' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">9
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maWoDayRptDetailDTO.workDate", "maWoDayRptDetailDTO.deptDesc");
	setForUpdate();
}
	
function goTabPage(pageId)
{
	var form = document.maWoDayRptDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDayRptDocLibList")
	{	
		maWoDayRptDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoDayRptDetailForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value;
		maWoDayRptDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WRKDAYRPT"; 
		//maWoDayRptDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//maWoDayRptDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		maWoDayRptDetailForm.elements['maDocLibCommonDTO.description'].value = 
			maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.title'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);  
}

function goInput()
{
	sequenceNextVal('SQAWRKDAYRPT_ID');
	
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.workDate'].value   = getToday();
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.empId'].value      = loginUser.empId;
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.deptId'].value     = loginUser.deptId;
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.empDesc'].value    = loginUser.empName;
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.deptDesc'].value   = loginUser.deptDesc;
}

function setSequenceVal(sequenceVal)
{
	maWoDayRptDetailForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = sequenceVal;
	maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.wrkDayRptId'].value = sequenceVal;
}

function goUpdate()
{
}
/**
 * 저장
 */ 
function goSave()
{
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maWoDayRptDetailForm.strutsAction.value = '<%=MaWoDayRptDetailAction.DETAIL_INPUT%>';
	else maWoDayRptDetailForm.strutsAction.value = '<%=MaWoDayRptDetailAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maWoDayRptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoDayRptDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.wrkDayRptId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maWoDayRptDetailForm.elements['maWoDayRptDetailDTO.wrkDayRptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWoDayRptDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoDayRptCommonDTO.wrkDayRptId" />
	<html:hidden property="maWoDayRptDetailDTO.wrkDayRptId" />
	<html:hidden property="maWoDayRptDetailDTO.empId" />
	<html:hidden property="maWoDayRptDetailDTO.deptId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			
			<!-- 작성자 -->
			<div class="field">
				<label><bean:message key="LABEL.writeBy"/></label>
				<div class="input_read">
					<html:text property="maWoDayRptDetailDTO.empDesc" readonly="true"/>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field">
				<label><bean:message key="LABEL.dept"/></label>
				<div class="input_read">
					<html:text property="maWoDayRptDetailDTO.deptDesc" readonly="true"/>
				</div>
			</div>
			<!-- 일자 -->
			<div class="field">
				<label><bean:message key="LABEL.date"/></label>
				<div class="input_box">
					<html:text property="maWoDayRptDetailDTO.workDate"  tabindex="10" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 시간 -->
			<div class="field">
				<label><bean:message key="LABEL.time"/></label>
				<div class="input_box">
					<html:text property="maWoDayRptDetailDTO.workTime"  tabindex="20" />
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maWoDayRptDetailDTO.title" tabindex="30"/>
				</div>
			</div>
			<!-- 내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.contents"/></label>
				<div class="input_box">
					<html:textarea property="maWoDayRptDetailDTO.contents" styleClass="ta150" tabindex="40" />
				</div>
			</div>
			
			<!-- 계획일자 -->
            <div class="field">
                <label><bean:message key="LABEL.planDate"/></label>
                <div class="input_box">
                    <html:text property="maWoDayRptDetailDTO.planDate"  tabindex="50" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            <!-- 시간 -->
            <div class="field">
                <label><bean:message key="LABEL.planWrkTime"/></label>
                <div class="input_box">
                    <html:text property="maWoDayRptDetailDTO.planTime"  tabindex="60" />
                </div>
            </div>
            <!-- 내용 -->
            <div class="field_long">
                <label><bean:message key="LABEL.planContents"/></label>
                <div class="input_box">
                    <html:textarea property="maWoDayRptDetailDTO.planContents" styleClass="ta150" tabindex="70" />
                </div>
            </div>
            <!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="maWoDayRptDetailDTO.remark" styleClass="ta150" tabindex="80" />
                </div>
            </div>
           
			<c:set var="filePath" value="enhance/${compName}/note/dayrpt/maWoDayRptDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/note/dayrpt/maWoDayRptDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>