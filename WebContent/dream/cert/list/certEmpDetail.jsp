<%--===========================================================================
자격증 사원
author  kim21017
version $Id: certEmpDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.cert.list.action.CertEmpDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html>
<head>
<!-- 작업자-->
<title><bean:message key="TAB.certEmpList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("certEmpDetailDTO.empNo","certEmpDetailDTO.empName");
	setForUpdate();
	
	mainMngAc = new autoC({"certEmpDetailDTO.empName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("certEmpDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "certEmpDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    acSysDesc("certEmpDetailDTO.empCertStatusName","certEmpDetailDTO.empCertStatusCode","EMPCERT_STATUS",true);
    
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEMPCERTLIST_ID');
	
	certEmpDetailForm.elements['certEmpDetailDTO.empCertStatusName'].value = "R"; 
    valSysDir('certEmpDetailDTO.empCertStatusCode', 'certEmpDetailDTO.empCertStatusName', 'EMPCERT_STATUS', true);
    
}

function setSequenceVal(sequenceVal)
{
	certEmpDetailForm.elements['certEmpDetailDTO.empCertListId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) certEmpDetailForm.strutsAction.value = '<%=CertEmpDetailAction.CERT_EMP_DETAIL_INPUT%>';
	else certEmpDetailForm.strutsAction.value = '<%= CertEmpDetailAction.CERT_EMP_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/certEmpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(certEmpDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(certEmpDetailForm.elements['certEmpDetailDTO.empCertListId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function validEmp(){
	var actionUrl = contextPath + "/certEmpDetail.do";
	var param =  "&strutsAction=" + '<%= CertEmpDetailAction.CERT_EMP_DETAIL_CHECK %>'
	          +  "&" + FormQueryString(certEmpDetailForm);
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
		certEmpDetailForm.elements['certEmpDetailDTO.empId'].value = '';
		certEmpDetailForm.elements['certEmpDetailDTO.empName'].value = '';
    }
}
function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.certEmpDetailForm;

	if(pageId == "maDocLibList" || pageId == "certEmpDocLibList")
	{	
		certEmpDetailForm.elements['maDocLibCommonDTO.objectId'].value = certEmpDetailForm.elements['certEmpDetailDTO.empCertListId'].value;
		certEmpDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EMP_CERT";
		certEmpDetailForm.elements['maDocLibCommonDTO.description'].value = certEmpDetailForm.elements['certEmpDetailDTO.empName'].value;
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
    var objectId = certEmpDetailForm.elements['certEmpDetailDTO.empCertListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/certEmpDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="certCommonDTO.certListId"/>
	<html:hidden property="certEmpDetailDTO.empCertListId"/>
	<html:hidden property="certEmpDetailDTO.empCertStatusCode"/>
	<html:hidden property="certEmpDetailDTO.empNo"/>
	<html:hidden property="certEmpDetailDTO.empId"/>
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
					<html:text property="certEmpDetailDTO.empName" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 자격증 보유상태 -->
            <div class="field">
                <label><bean:message key="LABEL.empCertStatus"/></label>
                <div class="input_box">
                    <html:text property="certEmpDetailDTO.empCertStatusName" tabindex="20" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			
			<div class="field">
                <label><bean:message key="LABEL.acqDate"/></label>
                <div class="input_box">
                    <html:text property="certEmpDetailDTO.acqDate" tabindex="30" />
                         <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            
            <div class="field">
                <label><bean:message key="LABEL.expDate"/></label>
                <div class="input_box">
                    <html:text property="certEmpDetailDTO.expDate" tabindex="40" />
                         <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="certEmpDetailDTO.remark" styleClass="ta100" tabindex="50" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>