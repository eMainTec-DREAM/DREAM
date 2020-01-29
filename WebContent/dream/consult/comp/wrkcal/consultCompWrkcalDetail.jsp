<%--===========================================================================
회사설정 - 상세
author  kim21017
version $Id: consultCompWrkcalDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.wrkcal.action.ConsultCompWrkcalDetailAction"%>
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
	
	setTitle("consultCompWrkcalDetailDTO.wrkcalListNo","consultCompWrkcalDetailDTO.wrkcalListDesc");
	//For Save
	setForUpdate();
	
	compAc = new autoC({"consultCompWrkcalDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompWrkcalDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompWrkcalDetailDTO.compNo":"comp_no",
        "consultCompWrkcalCommonDTO.compNo":"comp_no"
    });
    compAc.init();
    
    plantAc = new autoC({"consultCompWrkcalDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("consultCompWrkcalDetailDTO.plantNo");
    plantAc.setAcResultMap({
        "consultCompWrkcalDetailDTO.plantNo":"plant"
    });
    plantAc.init(); 
    
	acSysDesc("consultCompWrkcalDetailDTO.isUse","consultCompWrkcalDetailDTO.isUse","IS_USE");
}

function goInput()
{
	sequenceNextVal('SQAWRKCALLIST_ID');
}

function setSequenceVal(sequenceVal)
{
	//TAWRKCALLIST 테이블의 다음 no 번호를 가져온다.
	consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.wrkcalListId'].value = sequenceVal;
	consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.wrkcalListNo'].value = sequenceVal;
	consultCompWrkcalDetailForm.elements['consultCompWrkcalCommonDTO.wrkcalListId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultCompWrkcalDetailForm.strutsAction.value = "<%=ConsultCompWrkcalDetailAction.WRKCAL_DETAIL_INPUT%>";
	else consultCompWrkcalDetailForm.strutsAction.value = "<%=ConsultCompWrkcalDetailAction.WRKCAL_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/consultCompWrkcalDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompWrkcalDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    consultCompWrkcalDetailForm.elements['consultCompWrkcalCommonDTO.compNo'].value = consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.compNo'].value;
    consultCompWrkcalDetailForm.elements['consultCompWrkcalCommonDTO.wrkcalListId'].value = consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.wrkcalListId'].value;	
    	
    if (parent.findGridRow)	parent.findGridRow(consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.compNo'].value, consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.wrkcalListId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.consultCompWrkcalDetailForm;
	
	goCommonTabPage(form, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompWrkcalDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultCompWrkcalCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="consultCompWrkcalCommonDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="consultCompWrkcalDetailDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="consultCompWrkcalDetailDTO.compNo" />
	<html:hidden property="consultCompWrkcalDetailDTO.plantNo" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
                 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.compNo"/></label>
             	 	<div id="compNoDiv" class="input_box">
             	 		<html:text property="consultCompWrkcalDetailDTO.compDesc" tabindex="10"/>
             	 		<p class="open_spop">
             	 			<a><span>조회</span></a>
             	 		</p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.wrkcalListNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultCompWrkcalDetailDTO.wrkcalListNo" tabindex="20"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.wrkcalListDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultCompWrkcalDetailDTO.wrkcalListDesc" tabindex="30"/>
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
	 					<html:text property="consultCompWrkcalDetailDTO.isUse" tabindex="40"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
             	 <!-- 
             	 <div class="field">
             	 	<label><bean:message key="LABEL.plantNo"/></label>
             	 	<div id="plantNoDiv" class="input_box">
             	 		<html:text property="consultCompWrkcalDetailDTO.plantDesc" tabindex="50"/>
             	 		<p class="open_spop">
             	 			<a><span>조회</span></a>
             	 		</p>
             	 	</div>
             	 </div>
             	  -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="consultCompWrkcalDetailDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
         </div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>
