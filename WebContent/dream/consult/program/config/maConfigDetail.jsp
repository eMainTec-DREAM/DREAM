
<%--===========================================================================
시스템 환경변수 - 상세
author  kim21017
version $Id: maConfigDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.config.action.MaConfigDetailAction"%>
<html:html>
<head>
<!-- 시스템 환경변수 -->
<title><bean:message key='TAB.maConfigDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;


function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maConfigDetailDTO.configName","maConfigDetailDTO.configValue");
	//For Save
	setForUpdate();
	
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQACONFIG_ID');
	maConfigDetailForm.elements['maConfigDetailDTO.isSystem'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maConfigDetailForm.elements['maConfigDetailDTO.configId'].value = sequenceVal;
	maConfigDetailForm.elements['maConfigCommonDTO.configId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maConfigDetailForm.strutsAction.value = "<%=MaConfigDetailAction.CONFIG_DETAIL_INPUT%>";
	else maConfigDetailForm.strutsAction.value = "<%=MaConfigDetailAction.CONFIG_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maConfigDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maConfigDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================	
 	maConfigDetailForm.elements['maConfigCommonDTO.configId'].value = maConfigDetailForm.elements['maConfigDetailDTO.configId'].value;
 	if (parent.findGridRow)	parent.findGridRow(maConfigDetailForm.elements['maConfigDetailDTO.configId'].value);

    getTopPage().afterSaveAll(currentPageId);
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maConfigDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maConfigCommonDTO.configId" />
	<html:hidden property="maConfigDetailDTO.configId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.configName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maConfigDetailDTO.configName" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.configValue"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maConfigDetailDTO.configValue" tabindex="30"/>
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isSystem"/></label>
					<div class="input_box">
							<html:text property="maConfigDetailDTO.isSystem" tabindex="40"
								onblur="valYn('maConfigDetailDTO.isSystem', true);"/>
							<p class="open_spop">
								<a href="javascript:lovTable('maConfigDetailDTO.isSystem', 'maConfigDetailDTO.isSystem','YN');">
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				
				<div class="field_long">
                    <label><bean:message key="LABEL.configDesc"/></label>
                    <div class="input_box">
                        <html:textarea property="maConfigDetailDTO.configDesc" styleClass="ta100" tabindex="50"/>
                    </div>
                 </div>
                 
                 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
