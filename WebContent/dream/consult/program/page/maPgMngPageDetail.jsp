<%--===========================================================================
화변별 탭페이지 상세 
author  kim21017
version $Id: maPgMngPageDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.MaPgMngPageDetailAction"%>
<html>
<head>
<!--화면 - 탭페이지 -->
<title><bean:message key="LABEL.page"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var pageAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPgMngPageDetailDTO.fileName","maPgMngPageDetailDTO.pgDesc");
	setForUpdate();
	
	
	// 탭페이지
    pageAc = new autoC({"maPgMngPageDetailDTO.pageDesc":"description"});
    pageAc.setTable("TAPAGE");
    pageAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    pageAc.setAcResultMap({
        "maPgMngPageDetailDTO.pageId":"page_id"
    });
    pageAc.init();
    
	// 사용여부
    acSysDesc("maPgMngPageDetailDTO.isUse","maPgMngPageDetailDTO.isUse","IS_USE",true);

}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGPAGE_ID');
	maPgMngPageDetailForm.elements['maPgMngPageDetailDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maPgMngPageDetailForm.elements['maPgMngPageDetailDTO.pgPageId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPgMngPageDetailForm.strutsAction.value = '<%=MaPgMngPageDetailAction.PG_PAGE_DETAIL_INPUT%>';
	else maPgMngPageDetailForm.strutsAction.value = '<%= MaPgMngPageDetailAction.PG_PAGE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPgMngPageDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPgMngPageDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPgMngPageDetailForm.elements['maPgMngPageDetailDTO.pgPageId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPgMngPageDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/> <!-- 부모화면 아이디 -->
<html:hidden property="maPgMngPageDetailDTO.pgPageId"/> <!-- 탭페이지 아이디 (시퀀스)-->
<html:hidden property="maPgMngPageDetailDTO.pageId"/> <!-- 탭페이지 화면 아이디 -->
<html:hidden property="maPgMngPageDetailDTO.keyType"/><!-- 다국어 key_type -->
<html:hidden property="maPgMngPageDetailDTO.keyNo"/><!-- 다국어 keyNo -->
<html:hidden property="maPgMngPageDetailDTO.fileName"/>
<html:hidden property="maPgMngPageDetailDTO.pgDesc"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 탭페이지id -->
			<div class="field">
				<label><bean:message key="LABEL.tabPageId"/></label>
				<div class="input_box">
					<html:text property="maPgMngPageDetailDTO.pageDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 타이틀id -->
			<div class="field">
				<label><bean:message key="LABEL.titleId"/></label>
				<div class="input_box">
					<html:text property="maPgMngPageDetailDTO.langDesc" tabindex="20" 
						onblur="valLang('maPgMngPageDetailDTO.keyType','maPgMngPageDetailDTO.keyNo','maPgMngPageDetailDTO.langDesc', true);"/>
					<p class="open_spop"><a href="javascript:lovLang('maPgMngPageDetailDTO.keyType','maPgMngPageDetailDTO.keyNo', 'maPgMngPageDetailDTO.langDesc');"><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
						<html:text property="maPgMngPageDetailDTO.isUse" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
				</div>
			</div>
			<!-- 화면순서 -->
				<div class="field">
					<label><bean:message key="LABEL.ordNo"/></label>
					<div class="input_box">
						<html:text property="maPgMngPageDetailDTO.ordNo" tabindex="40" />
					</div>
				</div>
			</div>
			<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maPgMngPageDetailDTO.remark" styleClass="ta50" tabindex="90" />
                    </div>
            </div>
			
			
			
		</div><!--article_box end-->
</html:form> 
</body>
</html>