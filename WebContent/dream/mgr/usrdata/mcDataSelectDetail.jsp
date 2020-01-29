<%--===========================================================================
메뉴 - 상세
author  kim21017
version $Id: mcDataSelectDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.usrdata.action.McDataSelectDetailAction"%>
<%@ page import="dream.mgr.usrdata.action.McDataSelectScriptAction"%>
<html:html>
<head>
<!-- 메뉴 -->
<title><bean:message key='TAB.mcDataSelectDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var creByAc, deptAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setFunction();
	
	setTitle("mcDataSelectDetailDTO.usrrptId","mcDataSelectDetailDTO.title");
	//For Save
	setForUpdate();
	
	
}

function goUpdate()
{
	setReadOnly("mcDataSelectDetailDTO.creDate");

	goTabPage("mcDataSelectScript");
	//openTab("mcDataSelectScript");
}

function goTabPage(pageId)
{
	goCommonTabPage(mcDataSelectDetailForm, '<%=McDataSelectScriptAction.DATA_SCRIPT_FIND%>' , pageId);
}

function setFunction()
{
	creByAc = new autoC({"mcDataSelectDetailDTO.creByDesc":"emp_name"});
    creByAc.setTable("TAEMP");
    creByAc.setAcConditionMap({
    	"comp_no": loginUser.compNo
	  , "is_use":"Y"
	   });
    creByAc.setAcDConditionMap({
    	"dept_id" : "mcDataSelectDetailDTO.creDept"
    });
    creByAc.setAcResultMap({
    	"mcDataSelectDetailDTO.creBy":"emp_id"
    });
    creByAc.setKeyName("mcDataSelectDetailDTO.creBy");
    creByAc.init();  
    
/* 	creByAc = new autoC({"mcDataSelectDetailDTO.creByDesc":"emp_name"});
    creByAc.setTable("TAEMP");
    creByAc.setAcConditionMap({
	    "is_use":"Y"
	   });
    creByAc.setAcResultMap({
    	"mcDataSelectDetailDTO.creBy":"emp_id"
    });
    creByAc.setKeyName("mcDataSelectDetailDTO.creBy");
    creByAc.init();   */
    
    deptAc = new autoC({"mcDataSelectDetailDTO.creDeptDesc":"description"});
    deptAc.setAcConditionMap({
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("mcDataSelectDetailDTO.creDept");
    deptAc.setAcResultMap({
        "mcDataSelectDetailDTO.creDept":"dept_id"
    });
    deptAc.setKeyName("mcDataSelectDetailDTO.creDept");
    deptAc.init();
    
	acSysDesc("mcDataSelectDetailDTO.usrdataTypeDesc","mcDataSelectDetailDTO.usrdataType","USRDATA_TYPE", true);

}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAUSRDATA_ID');
	
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.creBy'].value = loginUser.empId;	
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.creByDesc'].value = loginUser.empName;	
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.creDept'].value = loginUser.deptId;
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.creDeptDesc'].value = loginUser.deptDesc;
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.creDate'].value = getToday();
}

function setSequenceVal(sequenceVal)
{
	mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.usrrptId'].value = sequenceVal;
	mcDataSelectDetailForm.elements['mcDataSelectCommonDTO.usrrptId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) mcDataSelectDetailForm.strutsAction.value = '<%=McDataSelectDetailAction.DATA_DETAIL_INPUT%>';
	else mcDataSelectDetailForm.strutsAction.value = '<%=McDataSelectDetailAction.DATA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/mcDataSelectDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mcDataSelectDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.usrrptId'].value = mcDataSelectDetailForm.elements['mcDataSelectCommonDTO.usrrptId'].value;
    if (parent.findGridRow)	parent.findGridRow(mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.usrrptId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mcDataSelectDetailForm.elements['mcDataSelectDetailDTO.usrrptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mcDataSelectDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="mcDataSelectCommonDTO.usrrptId" />
	<html:hidden property="mcDataSelectCommonDTO.usrrptType" />
	<html:hidden property="mcDataSelectDetailDTO.usrrptId" />
	<html:hidden property="mcDataSelectDetailDTO.usrdataType" />
	<html:hidden property="mcDataSelectDetailDTO.creBy"/>
	<html:hidden property="mcDataSelectDetailDTO.creDept"/>
         <div class="article_box" id="detailBox">
             <div class="form_box">
                <div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mcDataSelectDetailDTO.title" tabindex="10" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="mcDataSelectDetailDTO.description" styleClass="ta50" tabindex="20" />
					</div>
				</div>
             	 
				<div class="field">
             	 	<label class="check"><bean:message key="LABEL.usrdataType"/></label>
					<div class="input_box">
						<html:text property="mcDataSelectDetailDTO.usrdataTypeDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.creDept"/></label>
					<div class="input_box">
						<html:text property="mcDataSelectDetailDTO.creDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.creBy"/></label>
					<div class="input_box">
						<html:text property="mcDataSelectDetailDTO.creByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.creDate"/></label>
             	 	<div class="input_box">
						<html:text property="mcDataSelectDetailDTO.creDate" tabindex="60" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
             	 </div>
				 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
