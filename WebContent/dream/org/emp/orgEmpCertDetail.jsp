
<%--===========================================================================
구매신청 item 상세
author  kim21017
version $Id: orgEmpCertDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.org.emp.action.OrgEmpCertDetailAction"%>
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

var certNoAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("orgEmpCertDetailDTO.certName");
	setForUpdate();

	certNoAc = new autoC({"orgEmpCertDetailDTO.certName":"cert_name"});
    certNoAc.setTable("TACERTLIST");
    certNoAc.setAcResultMap({
        "orgEmpCertDetailDTO.certListId":"certlist_id",
        "orgEmpCertDetailDTO.certNo":"cert_no",
        "orgEmpCertDetailDTO.certType":"cert_type",
        "orgEmpCertDetailDTO.certTypeDesc":"certTypeDesc",
    });
    certNoAc.setKeyName("orgEmpCertDetailDTO.certListId");
    certNoAc.init();
    
   //acSysDesc("orgEmpCertDetailDTO.certTypeDesc","orgEmpCertDetailDTO.certType","CERT_TYPE", true);
   acSysDesc("orgEmpCertDetailDTO.empCertStatusDesc","orgEmpCertDetailDTO.empCertStatus","EMPCERT_STATUS", true);


}

function goInput(){
	orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.empCertStatusDesc'].value = "R";
	valSysDir('orgEmpCertDetailDTO.empCertStatus', 'orgEmpCertDetailDTO.empCertStatusDesc', 'EMPCERT_STATUS', true);
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEMPCERTLIST_ID');
	
}

function setSequenceVal(sequenceVal)
{
	orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.empCertListId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) orgEmpCertDetailForm.strutsAction.value = '<%=OrgEmpCertDetailAction.BUY_ITEM_DETAIL_INPUT%>';
	else orgEmpCertDetailForm.strutsAction.value = '<%= OrgEmpCertDetailAction.BUY_ITEM_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/orgEmpCertDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(orgEmpCertDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.empCertListId'].value);

    setTitle("orgEmpCertDetailDTO.certName");
    getTopPage().afterSaveAll(currentPageId);
}
function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.orgEmpCertDetailForm;

	if(pageId == "maDocLibList" || pageId == "orgEmpCertDocLibList")
	{	
		orgEmpCertDetailForm.elements['maDocLibCommonDTO.objectId'].value = orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.empCertListId'].value;
		orgEmpCertDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EMP_CERT";
		orgEmpCertDetailForm.elements['maDocLibCommonDTO.description'].value = orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.certName'].value;
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
    var objectId = orgEmpCertDetailForm.elements['orgEmpCertDetailDTO.empCertListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/orgEmpCertDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEmpCommonDTO.empId"/>
<html:hidden property="orgEmpCertDetailDTO.empCertListId"/>
<html:hidden property="orgEmpCertDetailDTO.certListId"/>
<html:hidden property="orgEmpCertDetailDTO.empCertStatus"/>
<html:hidden property="orgEmpCertDetailDTO.certType" />
<html:hidden property="orgEmpCertDetailDTO.certNo" />
<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<!-- 자격증명 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.certName"/></label>
					<div class="input_box">
						<html:text property="orgEmpCertDetailDTO.certName" tabindex="10"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 자격증 구분 -->
			    <div class="field">
			   	    <label><bean:message key="LABEL.certType"/></label>
			   	    <div class="input_read">
			   	    	<html:text property="orgEmpCertDetailDTO.certTypeDesc"  readonly="true"/>
			   	    </div>
			    </div>
			    <!-- 취득일자 -->
			    <div class="field">
			    	<label><bean:message key="LABEL.acqDate"/></label>
			    	<div class="date_wrap">
			    		<div class="input_box input_carendar">
			    			<html:text property="orgEmpCertDetailDTO.acqDate"  tabindex="20" />
			    			<p class="open_calendar"><span>날짜</span></p>
			    		</div>
			    	</div>
			    </div>
			    <!-- 만료일자 -->
			    <div class="field">
			    	<label><bean:message key="LABEL.expDate"/></label>
			    	<div class="date_wrap">
			    		<div class="input_box input_carendar">
			    			<html:text property="orgEmpCertDetailDTO.expDate"  tabindex="30" />
			    			<p class="open_calendar"><span>날짜</span></p>
			    		</div>
			    	</div>
			    </div>
			    <!-- 자격증 상태 -->
			    <div class="field">
			   	    <label class="check"><bean:message key="LABEL.empCertStatus"/></label>
			   	    <div class="input_box">
			   	    	<html:text property="orgEmpCertDetailDTO.empCertStatusDesc" tabindex="40"/>
			   	    	<p class="open_spop"><a><span>조회</span></a></p>
			   	    </div>
			    </div>
			    <div class="field"></div>
				<div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="orgEmpCertDetailDTO.remark" styleClass="ta50" tabindex="50"/>
					</div>
				</div>
				<div class="field"></div>
				<div class="field"></div>
				<div class="field"></div>
				<div class="field"></div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>