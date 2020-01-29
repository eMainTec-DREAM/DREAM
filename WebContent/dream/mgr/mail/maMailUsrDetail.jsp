
<%--===========================================================================
메일수신자설정 - 수신자
author  kim21017
version $Id: maMailUsrDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.mgr.mail.action.MaMailUsrDetailAction"%>
<html>
<head>
<!--사원번호 -->
<title><bean:message key="LABEL.empNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maMailUsrDetailDTO.empNo","maMailUsrDetailDTO.empDesc");
	setForUpdate();
	
	mainMngAc = new autoC({"maMailUsrDetailDTO.empDesc":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setAcResultMap({
        "maMailUsrDetailDTO.empId":"emp_id",
        "maMailUsrDetailDTO.email":"e_mail"
    });
    mainMngAc.setKeyName("maMailUsrDetailDTO.empId");
    mainMngAc.setAcResultLabel({
    	"maMailUsrDetailDTO.empDesc":"LABEL.empNo",
    	"maMailUsrDetailDTO.email":"LABEL.email"
    });
    mainMngAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAMAIL_USR_ID');
}

function setSequenceVal(sequenceVal)
{
	maMailUsrDetailForm.elements['maMailUsrDetailDTO.mailUsrId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maMailUsrDetailForm.strutsAction.value = '<%=MaMailUsrDetailAction.MAIL_USR_DETAIL_INPUT%>';
	else maMailUsrDetailForm.strutsAction.value = '<%= MaMailUsrDetailAction.MAIL_USR_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maMailUsrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maMailUsrDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maMailUsrDetailForm.elements['maMailUsrDetailDTO.mailUsrId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("maMailUsrDetailDTO.empNo","maMailUsrDetailDTO.empDesc");
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maMailUsrDetailForm.elements['maMailUsrDetailDTO.mailUsrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maMailUsrDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maMailCommonDTO.mailListId"/>
<html:hidden property="maMailUsrDetailDTO.empNo"/>
<html:hidden property="maMailUsrDetailDTO.mailUsrId"/>
<html:hidden property="maMailUsrDetailDTO.empId"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.empNo"/></label>
				<div class="input_box">
					<html:text property="maMailUsrDetailDTO.empDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.phone"/></label>
				<div class="input_box">
					<html:text property="maMailUsrDetailDTO.phone" tabindex="30" />
				</div>
			</div>

			<div class="field_long">
				<!-- 이메일 -->
				<label><bean:message key="LABEL.email"/></label>
				<div class="input_box">
					<html:text property="maMailUsrDetailDTO.email" tabindex="20"/>
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>