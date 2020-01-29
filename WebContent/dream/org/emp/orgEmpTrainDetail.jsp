
<%--===========================================================================
구매신청 item 상세
author  kim21017
version $Id: orgEmpTrainDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@page import="dream.org.emp.action.OrgEmpTrainDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.certName"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var courseNoAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("orgEmpTrainDetailDTO.trainDesc");
	setForUpdate();

	courseNoAc = new autoC("orgEmpTrainDetailDTO.trainDesc");
	courseNoAc.setTable("TACOURSELIST");
	courseNoAc.setAcResultMap({
    	"orgEmpTrainDetailDTO.trainDesc":"description"
        ,"orgEmpTrainDetailDTO.courseListId":"courselist_id"
        ,"orgEmpTrainDetailDTO.courseListNo":"courselist_no"
    });
	courseNoAc.setKeyName("orgEmpTrainDetailDTO.courseListId");
	courseNoAc.init();
    


}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEMPTRAINLIST_ID');
	
}

function setSequenceVal(sequenceVal)
{
	orgEmpTrainDetailForm.elements['orgEmpTrainDetailDTO.empTrainListId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) orgEmpTrainDetailForm.strutsAction.value = '<%=OrgEmpTrainDetailAction.BUY_ITEM_DETAIL_INPUT%>';
	else orgEmpTrainDetailForm.strutsAction.value = '<%= OrgEmpTrainDetailAction.BUY_ITEM_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/orgEmpTrainDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(orgEmpTrainDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(orgEmpTrainDetailForm.elements['orgEmpTrainDetailDTO.empTrainListId'].value);

    setTitle("orgEmpTrainDetailDTO.trainDesc");
    getTopPage().afterSaveAll(currentPageId);
}
function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.orgEmpTrainDetailForm;

	if(pageId == "maDocLibList" || pageId == "orgEmpTrainDocLibList")
	{	
		orgEmpTrainDetailForm.elements['maDocLibCommonDTO.objectId'].value = orgEmpTrainDetailForm.elements['orgEmpTrainDetailDTO.empTrainListId'].value;
		orgEmpTrainDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EMP_EDU";
		orgEmpTrainDetailForm.elements['maDocLibCommonDTO.description'].value = orgEmpTrainDetailForm.elements['orgEmpTrainDetailDTO.trainDesc'].value;
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
    var objectId = orgEmpTrainDetailForm.elements['orgEmpTrainDetailDTO.empTrainListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/orgEmpTrainDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEmpCommonDTO.empId"/>
<html:hidden property="orgEmpTrainDetailDTO.empTrainListId"/>
<html:hidden property="orgEmpTrainDetailDTO.courseListId"/>
<html:hidden property="orgEmpTrainDetailDTO.courseListNo"/>
<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<!-- 교육일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.trainDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="orgEmpTrainDetailDTO.trainFdate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="orgEmpTrainDetailDTO.trainTdate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 교육기관 -->
				<div class="field">
					<label><bean:message key="LABEL.trainAgency"/></label>
					<div class="input_box">
						<html:text property="orgEmpTrainDetailDTO.trainAgency" tabindex="10"/>
					</div>
				</div>
				<!-- 교육명-->
			    <div class="field">
			   	    <label class="check"><bean:message key="LABEL.trainDesc"/></label>
			   	    <div class="input_box">
			   	    	<html:text property="orgEmpTrainDetailDTO.trainDesc" />
			   	    	<p class="open_spop"><a><span>조회</span></a></p>
			   	    </div>
			   	</div>
				<!-- 교육장소 -->
				<div class="field">
					<label><bean:message key="LABEL.place"/></label>
					<div class="input_box">
						<html:text property="orgEmpTrainDetailDTO.place" tabindex="10"/>
					</div>
				</div>
				<!-- 교육자 -->
			    <div class="field">
			    	<label><bean:message key="LABEL.teacher"/></label>
			    	<div class="input_box">
			    		<html:text property="orgEmpTrainDetailDTO.teacher" tabindex="110"/>
			    	</div>
			    </div>
			    <div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="orgEmpTrainDetailDTO.remark" styleClass="ta50" tabindex="50"/>
					</div>
				</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>