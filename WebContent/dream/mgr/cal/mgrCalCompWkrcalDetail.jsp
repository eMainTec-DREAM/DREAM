<%--===========================================================================
회사설정 - 상세
author  kim21017
version $Id: mgrCalCompWkrcalDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDetailAction"%>
<html:html>
<head>
<!-- 회사설정 -->
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var compAc;
var plantAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("mgrCalCompWkrcalDetailDTO.wrkcalListNo","mgrCalCompWkrcalDetailDTO.wrkcalListDesc");
	//For Save
	setForUpdate();

    plantAc = new autoC({"mgrCalCompWkrcalDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("mgrCalCompWkrcalDetailDTO.plantNo");
    plantAc.setAcResultMap({
        "mgrCalCompWkrcalDetailDTO.plantNo":"plant"
    });
    plantAc.init(); 
    
	acSysDesc("mgrCalCompWkrcalDetailDTO.isUse","mgrCalCompWkrcalDetailDTO.isUse","IS_USE");
}

function goInput()
{
	sequenceNextVal('SQAWRKCALLIST_ID');
}

function setSequenceVal(sequenceVal)
{
	//TAWRKCALLIST 테이블의 다음 no 번호를 가져온다.
	mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalDetailDTO.wrkcalListId'].value = sequenceVal;
	mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalDetailDTO.wrkcalListNo'].value = sequenceVal;
	mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) mgrCalCompWkrcalDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_INPUT%>";
	else mgrCalCompWkrcalDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDetailAction.WRKCAL_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrCalCompWkrcalDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCalCompWkrcalDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalCommonDTO.wrkcalListId'].value = mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalDetailDTO.wrkcalListId'].value;	
    	
    if (parent.findGridRow)	parent.findGridRow(mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalDetailDTO.wrkcalListId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.mgrCalCompWkrcalDetailForm;
	
	goCommonTabPage(form, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrCalCompWkrcalDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCalCompWkrcalCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalCommonDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalDetailDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalDetailDTO.compNo" />
	<html:hidden property="mgrCalCompWkrcalDetailDTO.plantNo" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.wrkcalListNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mgrCalCompWkrcalDetailDTO.wrkcalListNo" tabindex="20"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.wrkcalListDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mgrCalCompWkrcalDetailDTO.wrkcalListDesc" tabindex="30"/>
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
	 					<html:text property="mgrCalCompWkrcalDetailDTO.isUse" tabindex="40"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.plantNo"/></label>
             	 	<div id="plantNoDiv" class="input_box">
             	 		<html:text property="mgrCalCompWkrcalDetailDTO.plantDesc" tabindex="50"/>
             	 		<p class="open_spop">
             	 			<a><span>조회</span></a>
             	 		</p>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="mgrCalCompWkrcalDetailDTO.remark" styleClass="ta50" tabindex="60" />
             	 	</div>
             	 </div>
         </div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>
