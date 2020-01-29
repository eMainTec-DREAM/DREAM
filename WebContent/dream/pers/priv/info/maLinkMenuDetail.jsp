<%--===========================================================================
상세 
author  jung7126
version $Id: maLinkMenuDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.info.action.MaLinkMenuDetailAction"%>
<html>
<head>
<!--메뉴명 -->
<title><bean:message key="LABEL.menuName"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maLinkMenuDetailDTO.menuDesc");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQALINKEDMENU_ID');
	//maLinkMenuDetailForm.elements['maLinkMenuDetailDTO.hidden'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	maLinkMenuDetailForm.elements['maLinkMenuDetailDTO.linkMenuId'].value = sequenceVal;
	maLinkMenuDetailForm.elements['maMyInfoCommonDTO.linkMenuId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maLinkMenuDetailForm.strutsAction.value = '<%=MaLinkMenuDetailAction.LINK_MENU_DETAIL_INPUT%>';
	else maLinkMenuDetailForm.strutsAction.value = '<%= MaLinkMenuDetailAction.LINK_MENU_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maLinkMenuDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maLinkMenuDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maLinkMenuDetailForm.elements['maLinkMenuDetailDTO.linkMenuId'].value);
	
    setTitle("maLinkMenuDetailDTO.menuDesc");
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maLinkMenuDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyInfoCommonDTO.userId"/>
<html:hidden property="maMyInfoCommonDTO.compNo"/>
<html:hidden property="maMyInfoCommonDTO.linkMenuId"/>
<html:hidden property="maLinkMenuDetailDTO.linkMenuId"/>
<html:hidden property="maLinkMenuDetailDTO.menuId"/>
<html:hidden property="maLinkMenuDetailDTO.linkedMenuId"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 칼럼 ID -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.menuName"/></label>
	               	   <div class="input_box">
							<html:text property="maLinkMenuDetailDTO.menuDesc" tabindex="10" 
								onblur="valMenuDesc('maLinkMenuDetailDTO.menuId', 'maLinkMenuDetailDTO.menuDesc',true);"/>
							<p class="open_spop">
								<a href="javascript:lovMenu('maLinkMenuDetailDTO.menuId', 'maLinkMenuDetailDTO.menuDesc');">
									<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
               	   <div class="field">
		               <label class="check"><bean:message key="LABEL.linkedMenuName"/></label>
	               	   <div class="input_box">
							<html:text property="maLinkMenuDetailDTO.linkedMenuDesc" tabindex="10" 
								onblur="valMenuDesc('maLinkMenuDetailDTO.linkedMenuId','maLinkMenuDetailDTO.linkedMenuDesc',true);"/>
							<p class="open_spop">
								<a href="javascript:lovMenu('maLinkMenuDetailDTO.linkedMenuId', 'maLinkMenuDetailDTO.linkedMenuDesc');">
									<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
				<!-- 칼럼명 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.ordNo"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maLinkMenuDetailDTO.ordNo" tabindex="20" styleClass="num"/>
	               	   </div>
               	   </div>
				
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>