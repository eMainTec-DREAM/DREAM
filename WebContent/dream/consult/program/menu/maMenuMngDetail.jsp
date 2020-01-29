<%--===========================================================================
메뉴 - 상세
author  kim21017
version $Id: maMenuMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.menu.action.MaMenuMngDetailAction"%>
<html:html>
<head>
<!-- 메뉴 -->
<title><bean:message key='TAB.maMenuMngDetail' />
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
	
	maMenuMngDetailForm.elements['maMenuMngDetailDTO.keyTypeStr'].value ="MENU";
	setTitle("maMenuMngDetailDTO.pmenuDesc","maMenuMngDetailDTO.menuDesc");
	//For Save
	setForUpdate();
	
	    pmenuAc = new autoC({"maMenuMngDetailDTO.pmenuDesc":"description"});
	    pmenuAc.setTable("TACMENU");
	    pmenuAc.setAcResultMap({
	    	"maMenuMngDetailDTO.pmenuId":"menu_id"
	    });
	    pmenuAc.setAcConditionMap({
	    	"auth":"N"
	  	   });
	    pmenuAc.setKeyName("maMenuMngDetailDTO.pmenuId");
	    pmenuAc.init();   
	    
	    

	    menuDescAc = new autoC({"maMenuMngDetailDTO.menuDesc":"key_name"});
	    menuDescAc.setTable("TALANG");
	    menuDescAc.setAcResultMap({
	    	"maMenuMngDetailDTO.keyType":"key_type",
	    	"maMenuMngDetailDTO.keyNo":"key_no",
	    	"maMenuMngDetailDTO.keyTypeStr":"key_type"
	    });
	    menuDescAc.setAcConditionMap({
        	"key_type":"MENU"
  	   });
	    menuDescAc.setKeyName("maMenuMngDetailDTO.keyNo");
	    menuDescAc.init();  
	    
	    
	    pageDescAc = new autoC({"maMenuMngDetailDTO.pageDesc":"description"});
	    pageDescAc.setTable("TAPAGE");
	    pageDescAc.setAcResultMap({
	    	"maMenuMngDetailDTO.pageId":"page_id"
	    });
	    pageDescAc.setKeyName("maMenuMngDetailDTO.pageId");
	    pageDescAc.init();  
	    
	    acSysDesc("maMenuMngDetailDTO.isUse","maMenuMngDetailDTO.isUse","IS_USE",true);
	    acSysDesc("maMenuMngDetailDTO.isSystem","maMenuMngDetailDTO.isSystem","IS_USE",true);
	    acSysDesc("maMenuMngDetailDTO.serviceTypeDesc","maMenuMngDetailDTO.serviceType","SERVICE_TYPE",true);
	    acSysDesc("maMenuMngDetailDTO.isExternalLink","maMenuMngDetailDTO.isExternalLink","IS_USE",true);
	    acSysDesc("maMenuMngDetailDTO.isGetLink","maMenuMngDetailDTO.isGetLink","IS_USE",true);
}

function afterAutoCmpt(_name)
{
	
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAMENU_ID');

	maMenuMngDetailForm.elements['maMenuMngDetailDTO.isSystem'].value ="N";
	valSysDir('maMenuMngDetailDTO.isSystem', 'maMenuMngDetailDTO.isSystem', 'IS_USE', true);
	maMenuMngDetailForm.elements['maMenuMngDetailDTO.isUse'].value ="Y";
	valSysDir('maMenuMngDetailDTO.isUse', 'maMenuMngDetailDTO.isUse', 'IS_USE', true);
	maMenuMngDetailForm.elements['maMenuMngDetailDTO.serviceTypeDesc'].value ="WEB";
	maMenuMngDetailForm.elements['maMenuMngDetailDTO.serviceType'].value ="WEB";
	valSysDir('maMenuMngDetailDTO.serviceType', 'maMenuMngDetailDTO.serviceTypeDesc', 'SERVICE_TYPE', true);
}

function setSequenceVal(sequenceVal)
{
	maMenuMngDetailForm.elements['maMenuMngDetailDTO.menuId'].value = sequenceVal;
	maMenuMngDetailForm.elements['maMenuMngCommonDTO.menuId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) maMenuMngDetailForm.strutsAction.value = '<%=MaMenuMngDetailAction.MENU_DETAIL_INPUT%>';
	else maMenuMngDetailForm.strutsAction.value = '<%=MaMenuMngDetailAction.MENU_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maMenuMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maMenuMngDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    maMenuMngDetailForm.elements['maMenuMngDetailDTO.menuId'].value = maMenuMngDetailForm.elements['maMenuMngCommonDTO.menuId'].value;
    if (parent.findGridRow)	parent.findGridRow(maMenuMngDetailForm.elements['maMenuMngDetailDTO.menuId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<%-- <c:import charEncoding="UTF-8" url="/common/jsp/topButtonInclude.jsp"></c:import> --%>
	<html:form action="/maMenuMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maMenuMngCommonDTO.menuId" />
	<html:hidden property="maMenuMngDetailDTO.menuId" />
	<html:hidden property="maMenuMngDetailDTO.pmenuId" />
	<html:hidden property="maMenuMngDetailDTO.pageId" />
	<html:hidden property="maMenuMngDetailDTO.keyType"/><!-- 다국어 key_type -->
	<html:hidden property="maMenuMngDetailDTO.keyNo"/><!-- 다국어 keyNo -->
	<html:hidden property="maMenuMngDetailDTO.keyTypeStr"/>
	<html:hidden property="maMenuMngDetailDTO.serviceType"/>
         <div class="article_box" id="detailBox">
             <div class="form_box">
                <div class="field">
             	 	<label class="check"><bean:message key="LABEL.menuNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maMenuMngDetailDTO.menuNo" tabindex="10" />
             	 	</div>
             	 </div>
             	 
				<div class="field">
             	 	<label class="check"><bean:message key="LABEL.menuName"/></label>
					<div class="input_box">
						<html:text property="maMenuMngDetailDTO.menuDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pMenu"/></label>
					<div class="input_box">
						<html:text property="maMenuMngDetailDTO.pmenuDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pageName"/></label>
					<div class="input_box">
						<html:text property="maMenuMngDetailDTO.pageDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.ordNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maMenuMngDetailDTO.ordNo" tabindex="50" />
             	 	</div>
             	 </div>
             	 
                 
                 <!-- 사용여부 -->
				 <div class="field">
					 <label class="check"><bean:message key="LABEL.isUse"/></label>
					 <div class="input_box">
						<html:text property="maMenuMngDetailDTO.isUse" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					 </div>
				 </div>
                 
             	 <!-- 시스템메뉴 -->
				 <div class="field">
					 <label class="check"><bean:message key="LABEL.isSystemMenu"/></label>
					 <div class="input_box">
						<html:text property="maMenuMngDetailDTO.isSystem" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					 </div>
				 </div>
				 
				 <div class="field">
             	 	<label><bean:message key="LABEL.paramValue"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maMenuMngDetailDTO.paramValue" tabindex="80" />
             	 	</div>
             	 </div>
             	 
             	 <!-- 서비스 구분  -->
	            <div class="field">
	                <label class="check"><bean:message key="LABEL.serviceType"/></label>
	                <div class="input_box">
	                    <html:text property="maMenuMngDetailDTO.serviceTypeDesc" tabindex="90" />
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	            </div>
	            <!-- 외부 Link메뉴  -->
	            <div class="field">
	                <label><bean:message key="LABEL.isExternalLink"/></label>
	                <div class="input_box">
	                    <html:text property="maMenuMngDetailDTO.isExternalLink" tabindex="95" />
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	            </div>
	            <!-- 외부 Link URL -->
	            <div class="field">
             	 	<label><bean:message key="LABEL.externalLink"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maMenuMngDetailDTO.externalLink" tabindex="97" />
             	 	</div>
             	 </div>
             	 <!-- 외부 Link메뉴  -->
	            <div class="field">
	                <label><bean:message key="LABEL.isGetLink"/></label>
	                <div class="input_box">
	                    <html:text property="maMenuMngDetailDTO.isGetLink" tabindex="98" />
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	            </div>
	            <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maMenuMngDetailDTO.remark" styleClass="ta50" tabindex="100" />
                    </div>
                </div>
				 
				  
				 
			
				 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
