<%--===========================================================================
자분석
author  kim21017
version $Id: rcmSysEmpDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.rcm.system.action.RcmSysEmpDetailAction"%>
<html>
<head>
<!-- 자분석-->
<title><bean:message key="TAB.rcmSysEmpList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var mainMngAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("rcmSysEmpDetailDTO.empNo","rcmSysEmpDetailDTO.empName");
	setForUpdate();
	
	mainMngAc = new autoC({"rcmSysEmpDetailDTO.empName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("rcmSysEmpDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "rcmSysEmpDetailDTO.empId":"emp_id"
        ,"rcmSysEmpDetailDTO.empNo":"emp_no"
        ,"rcmSysEmpDetailDTO.deptId":"dept_id"
        ,"rcmSysEmpDetailDTO.deptDesc":"deptDesc"
    });
    mainMngAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMEMP_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmSysEmpDetailForm.elements['rcmSysEmpDetailDTO.rcmEmpId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmSysEmpDetailForm.strutsAction.value = '<%=RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_INPUT%>';
	else rcmSysEmpDetailForm.strutsAction.value = '<%= RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmSysEmpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmSysEmpDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(rcmSysEmpDetailForm.elements['rcmSysEmpDetailDTO.rcmEmpId'].value);
	setTitle("rcmSysEmpDetailDTO.empNo","rcmSysEmpDetailDTO.empName");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.rcmSysEmpDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmSysEmpDetailForm.elements['rcmSysEmpDetailDTO.rcmEmpId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/rcmSysEmpDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmSysCommonDTO.rcmListId"/>
	<html:hidden property="rcmSysEmpDetailDTO.rcmListId"/>
	<html:hidden property="rcmSysEmpDetailDTO.rcmEmpId"/>
	<html:hidden property="rcmSysEmpDetailDTO.empId"/>
	<html:hidden property="rcmSysEmpDetailDTO.empNo"/>
	<html:hidden property="rcmSysEmpDetailDTO.deptId"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 사원 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.emp"/></label>
				<div class="input_box">
					<html:text property="rcmSysEmpDetailDTO.empName" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field">
				<label><bean:message key="LABEL.dept"/></label>
				<div class="input_read">
					<html:text property="rcmSysEmpDetailDTO.deptDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmSysEmpDetailDTO.remark" styleClass="ta50" tabindex="30"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
