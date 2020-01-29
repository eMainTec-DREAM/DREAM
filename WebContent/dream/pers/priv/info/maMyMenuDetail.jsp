<%--===========================================================================
상세 
author  jung7126
version $Id: maMyMenuDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.info.action.MaMyMenuDetailAction"%>
<html>
<head>
<!--칼럼 -->
<title><bean:message key="LABEL.columnId"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maMyMenuDetailDTO.menuDesc");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAUSRMENU_ID');
	//maMyMenuDetailForm.elements['maMyMenuDetailDTO.hidden'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	maMyMenuDetailForm.elements['maMyMenuDetailDTO.usrMenuId'].value = sequenceVal;
	maMyMenuDetailForm.elements['maMyInfoCommonDTO.usrMenuId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maMyMenuDetailForm.strutsAction.value = '<%=MaMyMenuDetailAction.MY_MENU_DETAIL_INPUT%>';
	else maMyMenuDetailForm.strutsAction.value = '<%= MaMyMenuDetailAction.MY_MENU_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maMyMenuDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maMyMenuDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maMyMenuDetailForm.elements['maMyMenuDetailDTO.usrMenuId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maMyMenuDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyInfoCommonDTO.userId"/>
<html:hidden property="maMyInfoCommonDTO.compNo"/>
<html:hidden property="maMyInfoCommonDTO.usrMenuId"/>
<html:hidden property="maMyMenuDetailDTO.usrMenuId"/>
<html:hidden property="maMyMenuDetailDTO.menuId"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 칼럼 ID -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.menuName"/></label>
	               	   <div class="input_box">
							<html:text property="maMyMenuDetailDTO.menuDesc" tabindex="10" 
								onblur="valMenuDesc('maMyMenuDetailDTO.menuId','maMyMenuDetailDTO.menuDesc',true);"/>
							<p class="open_spop">
								<a href="javascript:lovMenu('maMyMenuDetailDTO.menuId', 'maMyMenuDetailDTO.menuDesc');">
									<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
				<!-- 칼럼명 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.ordNo"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maMyMenuDetailDTO.ordNo" tabindex="20" styleClass="num"/>
	               	   </div>
               	   </div>
				
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>