<%--===========================================================================
시스템코드 - 상세
author  kim21017
version $Id: maCdSysDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysDetailAction"%>
<html:html>
<head>
<!-- 코드종류 -->
<title><bean:message key='LABEL.cdType' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maCdSysCodeList");
		goTabPage("consultCdSysFieldList");
	}
	
	setTitle("maCdSysDetailDTO.listType","maCdSysDetailDTO.listTypeDesc");
	
	setForUpdate();
	
    acSysDesc("maCdSysDetailDTO.isUse","maCdSysDetailDTO.isUse","IS_USE", true);
    acSysDesc("maCdSysDetailDTO.isSystem","maCdSysDetailDTO.isSystem","IS_USE", true);
}

function goInput()
{
	sequenceNextVal('SQACDSYSM_ID');
	maCdSysDetailForm.elements['maCdSysDetailDTO.isUse'].value = 'Y';
	maCdSysDetailForm.elements['maCdSysDetailDTO.isSystem'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maCdSysDetailForm.elements['maCdSysDetailDTO.cdSysMId'].value = sequenceVal;
	maCdSysDetailForm.elements['maCdSysCommonDTO.cdSysMId'].value = sequenceVal;
	
	goTabPage("maCdSysCodeList");
	goTabPage("consultCdSysFieldList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maCdSysDetailForm;

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
	if(ckCreate(currentPageId)) maCdSysDetailForm.strutsAction.value = "<%=MaCdSysDetailAction.LISTTYPE_DETAIL_INPUT%>";
	else maCdSysDetailForm.strutsAction.value = '<%=MaCdSysDetailAction.LISTTYPE_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maCdSysDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maCdSysDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maCdSysDetailForm.elements['maCdSysCommonDTO.cdSysMId'].value = maCdSysDetailForm.elements['maCdSysDetailDTO.cdSysMId'].value;
     if (parent.findGridRow)	parent.findGridRow(maCdSysDetailForm.elements['maCdSysCommonDTO.cdSysMId'].value);
 	
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maCdSysDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maCdSysCommonDTO.cdSysMId" />
	<html:hidden property="maCdSysDetailDTO.cdSysMId" />
	
	<html:hidden property="maCdSysDetailDTO.keyType"/><!-- 다국어 key_type -->
	<html:hidden property="maCdSysDetailDTO.keyNo"/><!-- 다국어 keyNo -->
	<html:hidden property="maCdSysDetailDTO.keyTypeStr"/>
	
	
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.codeType"/></label>
				<div class="input_box">
					<html:text property="maCdSysDetailDTO.listType" tabindex="1"/>
				</div>
				
				</div>
					<div class="field">
					<label class="check"><bean:message key="LABEL.codeDesc"/></label>
					<div class="input_box">
						<html:text property="maCdSysDetailDTO.listTypeDesc" tabindex="10"/>
					</div>
				</div>
				
				
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="maCdSysDetailDTO.isUse" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				
				<div class="field">
					<label>시스템여부</label>
					<div class="input_box">
						<html:text property="maCdSysDetailDTO.isSystem" tabindex="25"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.codeCateg"/></label>
					<div class="input_box">
						<html:text property="maCdSysDetailDTO.listCateg" tabindex="30" />
					</div>
				</div>
				
				<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maCdSysDetailDTO.remark" styleClass="ta50" tabindex="40" />
                    </div>
                </div>
				
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>