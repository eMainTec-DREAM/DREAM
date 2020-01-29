<%--===========================================================================
자격증분류 - 상세
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.cert.list.action.CertDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 자격증분류 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("certDetailDTO.certNo", "certDetailDTO.certName");
	
    var certTypeAc = new autoC({"certDetailDTO.certTypeDesc":"description"});
    certTypeAc.setTable("TACDUSRD");
    certTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"CERT_TYPE"
  	   });
    certTypeAc.setAcResultMap({
        "certDetailDTO.certType":"cdusrd_no"
    });
    certTypeAc.init();
    	
	acSysDesc("certDetailDTO.isUse","certDetailDTO.isUse","IS_USE", true);
	//acSysDesc("certDetailDTO.certTypeDesc","certDetailDTO.certType","CERT_TYPE", true);
	
	setForUpdate();
}
	
function goUpdate()
{
    //수정시 readonly설정 
    //certDetailForm.elements['certDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  

}

function goInput()
{ 
	certDetailForm.elements['certDetailDTO.isUse'].value = 'Y'; 
	sequenceNextVal('SQACERTLIST_ID');
}

function setSequenceVal(sequenceVal)
{
	certDetailForm.elements['certDetailDTO.certListId'].value = sequenceVal;
	certDetailForm.elements['certDetailDTO.certNo'].value = sequenceVal;
	certDetailForm.elements['certCommonDTO.certListId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) certDetailForm.strutsAction.value = '<%=CertDetailAction.CERT_DETAIL_INPUT%>';
	else certDetailForm.strutsAction.value = '<%=CertDetailAction.CERT_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/certDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(certDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	certDetailForm.elements['certDetailDTO.certListId'].value = certDetailForm.elements['certCommonDTO.certListId'].value;
	if (parent.findGridRow)	parent.findGridRow(certDetailForm.elements['certDetailDTO.certListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("certDetailDTO.certNo", "certDetailDTO.certName");
}
 
 
function goTabPage(pageId)
{
    var form = document.certDetailForm;
        goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = certDetailForm.elements['certDetailDTO.certListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/certDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="certCommonDTO.certListId" />	
	<html:hidden property="certDetailDTO.certListId" />
	<html:hidden property="certDetailDTO.certType" />

	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.number"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="certDetailDTO.certNo" tabindex="10" readonly="true" />
				</div>
			</div>
			<!-- 자격증 구분 -->
			<div class="field">
			    <label><bean:message key="LABEL.certType"/></label>
			    <div class="input_box">
			    	<html:text property="certDetailDTO.certTypeDesc" tabindex="40" />
			    	<p class="open_spop"><a><span>조회</span></a></p>
			    </div>
			</div>
			<div class="field_long">
               <label class="check"><bean:message key="LABEL.certName"/></label>
               <div class="input_box">
                   <html:text property="certDetailDTO.certName" tabindex="60"/>
               </div>
            </div>
			<div class="field">
               <label><bean:message key="LABEL.certAgency"/></label>
               <div class="input_box">
                   <html:text property="certDetailDTO.certAgency" tabindex="60" />
               </div>
            </div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="certDetailDTO.isUse" tabindex="160"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
 			<!-- 기본개요 -->
			<div class="field_long">
				<label><bean:message key="LABEL.certDesc"/></label>
				<div class="input_box">
					<html:textarea property="certDetailDTO.certDesc" styleClass="ta50" tabindex="190" />
				</div>
			</div>
			<!-- 취득방법 -->
			<div class="field_long">
				<label><bean:message key="LABEL.howToGet"/></label>
				<div class="input_box">
					<html:textarea property="certDetailDTO.howToGet" styleClass="ta50" tabindex="190" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>