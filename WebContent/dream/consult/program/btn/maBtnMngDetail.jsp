<%--===========================================================================
버튼 - 상세
author  kim21017
version $Id: maBtnMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.btn.action.MaBtnMngDetailAction"%>
<html:html>
<head>
<!-- 버튼 -->
<title><bean:message key='TAB.maBtnMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maBtnMngDetailDTO.buttonNo","maBtnMngDetailDTO.buttonDesc");
	//For Save
	setForUpdate();
	
	
	   acSysDesc("maBtnMngDetailDTO.isUse","maBtnMngDetailDTO.isUse","IS_USE");
}
function goInput()
{
	sequenceNextVal('SQABUTTON_ID');
	
	maBtnMngDetailForm.elements['maBtnMngDetailDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maBtnMngDetailForm.elements['maBtnMngDetailDTO.buttonId'].value = sequenceVal;
	maBtnMngDetailForm.elements['maBtnMngCommonDTO.buttonId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maBtnMngDetailForm.strutsAction.value = "<%=MaBtnMngDetailAction.BTN_DETAIL_INPUT%>";
	else maBtnMngDetailForm.strutsAction.value = "<%=MaBtnMngDetailAction.BTN_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maBtnMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maBtnMngDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maBtnMngDetailForm.elements['maBtnMngDetailDTO.buttonId'].value = maBtnMngDetailForm.elements['maBtnMngCommonDTO.buttonId'].value;
    if (parent.findGridRow)	parent.findGridRow(maBtnMngDetailForm.elements['maBtnMngDetailDTO.buttonId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maBtnMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maBtnMngCommonDTO.buttonId" />
	<html:hidden property="maBtnMngDetailDTO.buttonId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.buttonName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBtnMngDetailDTO.buttonNo" tabindex="1"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.buttonDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBtnMngDetailDTO.buttonDesc" tabindex="10" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.buttonIcon"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBtnMngDetailDTO.btnImg" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="maBtnMngDetailDTO.isUse" tabindex="30"
							onblur="valYn('maBtnMngDetailDTO.isLawEq', true);"/>
						<p class="open_spop">
							<a href="javascript:lovTable('maBtnMngDetailDTO.isUse', 'maBtnMngDetailDTO.isUse','YN');">
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				
				<div class="field">
             	 	<label><bean:message key="LABEL.ordNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maBtnMngDetailDTO.ordNo" tabindex="40" />
             	 	</div>
             	</div>
             	
             	<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maBtnMngDetailDTO.remark" styleClass="ta50" tabindex="50" />
                    </div>
                 </div>
                 
                 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>
