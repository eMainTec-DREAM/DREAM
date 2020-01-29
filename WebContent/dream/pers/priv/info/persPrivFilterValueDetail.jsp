<%--===========================================================================
상세 
author  euna0207
version $Id: persPrivFilterValue.jsp,v 1.0 2015/12/04 07:26:18 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.info.action.PersPrivFilterValueAction"%>
<html>
<head>
<!-- 검색조건저장 -->
<title><bean:message key="LABEL.menuName"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">

function loadPage() 
{
	setForUpdate();
    acSysDesc("persPrivFilterValueDTO.isDefault","persPrivFilterValueDTO.isDefault","IS_USE", true);
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

function goInput(){
	
	sequenceNextVal('SQAUSRFILTERVALUE_ID');
	persPrivFilterValueForm.elements['persPrivFilterValueDTO.isDefault'].value = "N";
	persPrivFilterValueForm.elements['persPrivFilterValueDTO.creDate'].value = getToday();
}

function setSequenceVal(sequenceVal)
{
	persPrivFilterValueForm.elements['persPrivFilterValueDTO.usrFilterValueId'].value = sequenceVal;

}

function goUpdate()
{
	setReadOnly("persPrivFilterValueDTO.title");
	setReadOnly("persPrivFilterValueDTO.isDefault");
}


function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
		
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) persPrivFilterValueForm.strutsAction.value = '<%=PersPrivFilterValueAction.DETAIL_INPUT%>';
	
	var actionUrl = contextPath + "/persPrivFilterValueDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(persPrivFilterValueForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(persPrivFilterValueForm.elements['persPrivFilterValueDTO.usrFilterValueId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    goUpdate();
}

function goClose()
{
	closeLayerPopup(this);
}

</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/persPrivFilterValueDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="persPrivFilterValueDTO.usrFilterValueId"/>
<html:hidden property="persPrivFilterValueDTO.setValue"/>
<html:hidden property="persPrivFilterValueDTO.creTime"/>
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.saveFilterValue"/></div>   
			</div>
			<div class="function_box detail">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
             	 	<label><bean:message key="LABEL.page"/></label>
             	 	<div class="input_read">
             	 		<html:text property="persPrivFilterValueDTO.fileName" tabindex="5" readonly="true"/>
             	 	</div>
             	 </div>
				<div class="field">
             	 	<label><bean:message key="LABEL.UserLoginId"/></label>
             	 	<div class="input_read">
             	 		<html:text property="persPrivFilterValueDTO.userNo" tabindex="10" readonly="true"/>
             	 	</div>
             	 </div>  
				<div class="field">
             	 	<label><bean:message key="LABEL.creDate"/></label>
             	 	<div class="input_read">
             	 		<html:text property="persPrivFilterValueDTO.creDate" tabindex="15" readonly="true"/>
             	 	</div>
             	 </div>  
				<div class="field">
             	 	<label><bean:message key="LABEL.userName"/></label>
             	 	<div class="input_read">
             	 		<html:text property="persPrivFilterValueDTO.userName" tabindex="20" readonly="true"/>
             	 	</div>
             	 </div>    
				<div class="field_long">
             	 	<label><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="persPrivFilterValueDTO.title" tabindex="25"/>
             	 	</div>
             	 </div>    
				<div class="field_long">
             	 	<label><bean:message key="LABEL.defaultValue"/></label>
             	 	<div class="input_box">
             	 		<html:text property="persPrivFilterValueDTO.isDefault" tabindex="30"/>
             	 			<p class="open_spop"><a><span>조회</span></a>
						</p>
             	 	</div>
             	 </div>    
             	          	            	            	 
			</div>
		</div><!--article_box end-->
	</div>
</html:form> 
</body>
</html>