<%--===========================================================================
메뉴 - 상세
author  kim21017
version $Id: maUserRptDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptDetailAction"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptDesignAction"%>
<html:html>
<head>
<!-- 메뉴 -->
<title><bean:message key='TAB.maUserRptDetail' />
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
	
	setTitle("maUserRptDetailDTO.usrrptlistId","maUserRptDetailDTO.title");
	//For Save
	setForUpdate();
	
	
}

function goUpdate()
{
	setReadOnly("maUserRptDetailDTO.creDate");

	goTabPage("maUserRptDesign");
	//openTab("maUserRptDesign");
}

function goTabPage(pageId)
{
	maUserRptDetailForm.elements['maUserRptCommonDTO.title'].value = maUserRptDetailForm.elements['maUserRptDetailDTO.title'].value;
	goCommonTabPage(maUserRptDetailForm, '<%=MaUserRptDesignAction.USER_RPT_SCRIPT_FIND%>' , pageId);
}

function setFunction()
{
	creByAc = new autoC("maUserRptDetailDTO.creByDesc");
    creByAc.setTable("TAUSER");
    creByAc.setAcResultMap({
    	"maUserRptDetailDTO.creBy":"user_id"
   		,"maUserRptDetailDTO.creByDesc":"emp_name"
    });
    creByAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    creByAc.setAcDConditionMap({
    	"dept_id" : "maUserRptDetailDTO.creDept"
    });
    creByAc.setKeyName("maUserRptDetailDTO.creBy");
    creByAc.init();  
    
    //======================================================================//
    deptAc = new autoC({"maUserRptDetailDTO.creDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maUserRptDetailDTO.creDept");
    deptAc.setAcResultMap({
        "maUserRptDetailDTO.creDept":"dept_id"
    });
    deptAc.setKeyName("maUserRptDetailDTO.creDept");
    deptAc.init();
    
  //======================================================================//
  
	acSysDesc("maUserRptDetailDTO.usrdataTypeDesc","maUserRptDetailDTO.usrdataType","USRDATA_TYPE", true);

}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAUSRRPTLIST_ID');
	
	maUserRptDetailForm.elements['maUserRptDetailDTO.creBy'].value = loginUser.userId;	// loginUser User_Id
	maUserRptDetailForm.elements['maUserRptDetailDTO.creByDesc'].value = loginUser.empName;	// loginUser User_Id deptId
	maUserRptDetailForm.elements['maUserRptDetailDTO.creDept'].value = loginUser.deptId;
	maUserRptDetailForm.elements['maUserRptDetailDTO.creDeptDesc'].value = loginUser.deptDesc;
	maUserRptDetailForm.elements['maUserRptDetailDTO.creDate'].value = getToday();
	
	//Hide Tab 
	$(".accordion_wrap").hide();
}

function setSequenceVal(sequenceVal)
{
	maUserRptDetailForm.elements['maUserRptDetailDTO.usrrptlistId'].value = sequenceVal;
	maUserRptDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maUserRptDetailForm.strutsAction.value = '<%=MaUserRptDetailAction.USER_RPT_DETAIL_INPUT%>';
	else maUserRptDetailForm.strutsAction.value = '<%=MaUserRptDetailAction.USER_RPT_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maUserRptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maUserRptDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    maUserRptDetailForm.elements['maUserRptDetailDTO.usrrptlistId'].value = maUserRptDetailForm.elements['maUserRptCommonDTO.usrrptlistId'].value;
    if (parent.findGridRow)	parent.findGridRow(maUserRptDetailForm.elements['maUserRptDetailDTO.usrrptlistId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    //Hide Tab 
	$(".accordion_wrap").show();
	resizeTabFrame();
}

function goReportprint()
{
	if(ckCreate(currentPageId)) 
	{
		alertMessage2("저장 후 출력해주세요.");
		return;
	}
	else maUserRptDetailForm.strutsAction.value = '<%=MaUserRptDetailAction.USER_RPT_DETAIL_PRINT%>';
	
	var actionUrl = currentPageId;
	var url = contextPath + "/"+actionUrl+".do";

	$.post(url, FormQueryString(maUserRptDetailForm), function(_data){
		if(_data !== "")alertMessage1(_data);
	});
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maUserRptDetailForm.elements['maUserRptDetailDTO.usrrptlistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maUserRptDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maUserRptCommonDTO.usrrptlistId" />
	<html:hidden property="maUserRptCommonDTO.usrdataType" />
	<html:hidden property="maUserRptCommonDTO.title" />
	<html:hidden property="maUserRptDetailDTO.usrrptlistId" />
	<html:hidden property="maUserRptDetailDTO.usrdataType" />
	<html:hidden property="maUserRptDetailDTO.creBy"/>
	<html:hidden property="maUserRptDetailDTO.creDept"/>
         <div class="article_box" id="detailBox">
             <div class="form_box">
                <div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maUserRptDetailDTO.title" tabindex="10" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="maUserRptDetailDTO.description" styleClass="ta50" tabindex="20" />
					</div>
				</div>
             	 
				<div class="field">
             	 	<label class="check"><bean:message key="LABEL.usrdataType"/></label>
					<div class="input_box">
						<html:text property="maUserRptDetailDTO.usrdataTypeDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.creDept"/></label>
					<div class="input_box">
						<html:text property="maUserRptDetailDTO.creDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.creBy"/></label>
					<div class="input_box">
						<html:text property="maUserRptDetailDTO.creByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.creDate"/></label>
             	 	<div class="input_box">
						<html:text property="maUserRptDetailDTO.creDate" tabindex="60" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
             	 </div>
				 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
