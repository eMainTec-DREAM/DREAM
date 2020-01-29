<%--===========================================================================
메뉴 - 상세
author  kim21017
version $Id: mcMenuDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.consult.menu.action.McMenuDetailAction"%>
<html:html>
<head>
<!-- 메뉴 -->
<title><bean:message key='TAB.mcMenuDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var pmenuAc;
var menuDescAc;
var pageDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setFunction();
	
	mcMenuDetailForm.elements['mcMenuDetailDTO.keyTypeStr'].value ="MENU";
	setTitle("mcMenuDetailDTO.pmenuDesc","mcMenuDetailDTO.menuDesc");
	//For Save
	setForUpdate();
	
	
}

function setFunction()
{
/* 		partNameAc = new autoC({"mcMenuDetailDTO.pmenuDesc":"y.key_name"});
    partNameAc.setTable("TAEHMENU x, TALANG y");
    partNameAc.setAcConditionMap({
		"y.lang":loginUser.langId,
		"CUSTOM":"x.key_no||x.key_type=y.key_no||y.key_type",
	    "x.is_use":"Y"
	   });
    partNameAc.setAcResultMap({
    	"mcMenuDetailDTO.pmenuId":"x.ehmenu_id"
    });
    partNameAc.setAcResultLabel({
    	"mcMenuDetailDTO.pmenuDesc":"LABEL.pMenu"
    });
    partNameAc.init();   */
    
    
    pmenuAc = new autoC({"mcMenuDetailDTO.pmenuDesc":"key_name"});
    pmenuAc.setTable("TAEHMENU");
    pmenuAc.setAcResultMap({
    	"mcMenuDetailDTO.pmenuId":"ehmenu_id"
    });
    pmenuAc.setKeyName("mcMenuDetailDTO.pmenuId");
    pmenuAc.init();   
    
    

    menuDescAc = new autoC({"mcMenuDetailDTO.menuDesc":"key_name"});
    menuDescAc.setTable("TALANG");
    menuDescAc.setAcResultMap({
    	"mcMenuDetailDTO.keyType":"key_type",
    	"mcMenuDetailDTO.keyNo":"key_no"
    });
    menuDescAc.setAcConditionMap({
    	"key_type":"MENU"
	   });
    menuDescAc.setKeyName("mcMenuDetailDTO.keyNo");
    menuDescAc.init();  
    
    
    pageDescAc = new autoC({"mcMenuDetailDTO.pageDesc":"description"});
    pageDescAc.setTable("TAPAGE");
    pageDescAc.setAcResultMap({
    	"mcMenuDetailDTO.pageId":"page_id"
    });
    pageDescAc.setKeyName("mcMenuDetailDTO.pageId");
    pageDescAc.init();  
    
    acSysDesc("mcMenuDetailDTO.isUse","mcMenuDetailDTO.isUse","IS_USE");
}



/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAEHMENU_ID');
}

function setSequenceVal(sequenceVal)
{
	mcMenuDetailForm.elements['mcMenuDetailDTO.menuId'].value = sequenceVal;
	mcMenuDetailForm.elements['mcMenuCommonDTO.menuId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) mcMenuDetailForm.strutsAction.value = '<%=McMenuDetailAction.MENU_DETAIL_INPUT%>';
	else mcMenuDetailForm.strutsAction.value = '<%=McMenuDetailAction.MENU_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/mcMenuDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mcMenuDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    mcMenuDetailForm.elements['mcMenuDetailDTO.menuId'].value = mcMenuDetailForm.elements['mcMenuCommonDTO.menuId'].value;
    if (parent.findGridRow)	parent.findGridRow(mcMenuDetailForm.elements['mcMenuDetailDTO.menuId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<%-- <c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import> --%>
	<html:form action="/mcMenuDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mcMenuCommonDTO.menuId" />
	<html:hidden property="mcMenuDetailDTO.menuId" />
	<html:hidden property="mcMenuDetailDTO.pmenuId" />
	<html:hidden property="mcMenuDetailDTO.pageId" />
	<html:hidden property="mcMenuDetailDTO.keyType"/><!-- 다국어 key_type -->
	<html:hidden property="mcMenuDetailDTO.keyNo"/><!-- 다국어 keyNo -->
	<html:hidden property="mcMenuDetailDTO.keyTypeStr"/>
         <div class="article_box" id="detailBox">
             <div class="form_box">
                <div class="field">
             	 	<label class="check"><bean:message key="LABEL.menuNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mcMenuDetailDTO.menuNo" tabindex="10" />
             	 	</div>
             	 </div>
             	 
				<div class="field">
             	 	<label class="check"><bean:message key="LABEL.menuName"/></label>
					<div class="input_box">
						<html:text property="mcMenuDetailDTO.menuDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pMenu"/></label>
					<div class="input_box">
						<html:text property="mcMenuDetailDTO.pmenuDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pageName"/></label>
					<div class="input_box">
						<html:text property="mcMenuDetailDTO.pageDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.ordNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mcMenuDetailDTO.ordNo" tabindex="50" />
             	 	</div>
             	 </div>
             	 
                 
                 <!-- 사용여부 -->
				 <div class="field">
					 <label class="check"><bean:message key="LABEL.isUse"/></label>
					 <div class="input_box">
						<html:text property="mcMenuDetailDTO.isUse" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					 </div>
				 </div>
                 
             	 <!-- 시스템메뉴
				 <div class="field">
					 <label class="check"><bean:message key="LABEL.isSystemMenu"/></label>
					 <div class="input_box">
						<html:text property="mcMenuDetailDTO.isSystem" tabindex="70"
							onblur="valYn('mcMenuDetailDTO.isSystem', true);"/>
						<p class="open_spop">
							<a href="javascript:lovTable('mcMenuDetailDTO.isSystem', 'mcMenuDetailDTO.isSystem','YN');">
								<span>조회</span>
							</a>
						</p>
					 </div>
				 </div> -->
				 
				 <div class="field_long">
             	 	<label><bean:message key="LABEL.paramValue"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mcMenuDetailDTO.paramValue" tabindex="80" />
             	 	</div>
             	 </div>
             	 
				 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_box">
             	 		<html:textarea property="mcMenuDetailDTO.remark" styleClass="ta50" tabindex="90" />
             	 	</div>
             	 </div>
				 
				  
				 
			
				 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
