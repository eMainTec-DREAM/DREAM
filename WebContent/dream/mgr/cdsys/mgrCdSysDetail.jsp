<%--===========================================================================
시스템코드 - 상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.cdsys.action.MgrCdSysDetailAction"%>
<html:html>
<head>
<!-- 코드종류 -->
<title><bean:message key='PAGE.mgrCdSysDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	goTabPage("mgrCdSysCodeList");
	goTabPage("mgrCdSysFieldList");
	
	setTitle("mgrCdSysDetailDTO.listType","mgrCdSysDetailDTO.listTypeDesc");
	
	setForUpdate();
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.mgrCdSysDetailForm;

	goCommonTabPage(form, '' , pageId);
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
	mgrCdSysDetailForm.strutsAction.value = '<%=MgrCdSysDetailAction.LISTTYPE_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/mgrCdSysDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCdSysDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     mgrCdSysDetailForm.elements['mgrCdSysCommonDTO.cdSysMId'].value = mgrCdSysDetailForm.elements['mgrCdSysDetailDTO.cdSysMId'].value;
     if (parent.findGridRow)	parent.findGridRow(mgrCdSysDetailForm.elements['mgrCdSysCommonDTO.cdSysMId'].value);
 	
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrCdSysDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCdSysCommonDTO.cdSysMId" />
	<html:hidden property="mgrCdSysDetailDTO.cdSysMId" />
	
	<html:hidden property="mgrCdSysDetailDTO.keyType"/><!-- 다국어 key_type -->
	<html:hidden property="mgrCdSysDetailDTO.keyNo"/><!-- 다국어 keyNo -->
	<html:hidden property="mgrCdSysDetailDTO.keyTypeStr"/>
	
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<div class="field">
				<label class="check">코드유형</label>
				<div class="input_read">
					<html:text property="mgrCdSysDetailDTO.listType" tabindex="1" readonly="true"/>
				</div>
				
				</div>
					<div class="field">
					<label class="check"><bean:message key="LABEL.codeDesc"/></label>
					<div class="input_box">
						<html:text property="mgrCdSysDetailDTO.listTypeDesc" tabindex="10"/>
					</div>
				</div>
				<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="mgrCdSysDetailDTO.remark" styleClass="ta50" tabindex="40" />
                    </div>
                </div>
				
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>