<%--===========================================================================
화변별 버튼 상세 
author  kim21017
version $Id: maPgMngBtnDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.MaPgMngBtnDetailAction"%>
<html>
<head>
<!--화면 - 버튼 -->
<title><bean:message key="TAB.maPgMngBtnDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	maPgMngBtnDetailForm.elements['maPgMngBtnDetailDTO.keyTypeStr'].value = 'BUTTON';
	setTitle("maPgMngBtnDetailDTO.buttonNo","maPgMngBtnDetailDTO.buttonRemark");
	setForUpdate();
	
	// 버튼위치
    acSysDesc("maPgMngBtnDetailDTO.buttonLocDesc","maPgMngBtnDetailDTO.buttonLoc","BUTTON_LOC",true);
    // 기본버튼여부	
    acSysDesc("maPgMngBtnDetailDTO.isBasic","maPgMngBtnDetailDTO.isBasic","IS_USE",true);
    // 그룹버튼시작여부	
    acSysDesc("maPgMngBtnDetailDTO.isSetGroup","maPgMngBtnDetailDTO.isSetGroup","IS_USE",true);
    // 권한적용여부	
    acSysDesc("maPgMngBtnDetailDTO.isChkauth","maPgMngBtnDetailDTO.isChkauth","IS_USE",true);
    // 권한적용여부	
    acSysDesc("maPgMngBtnDetailDTO.isUse","maPgMngBtnDetailDTO.isUse","IS_USE",true);


}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGBTN_ID');
	maPgMngBtnDetailForm.elements['maPgMngBtnDetailDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maPgMngBtnDetailForm.elements['maPgMngBtnDetailDTO.pgBtnId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPgMngBtnDetailForm.strutsAction.value = '<%=MaPgMngBtnDetailAction.PG_BTN_DETAIL_INPUT%>';
	else maPgMngBtnDetailForm.strutsAction.value = '<%= MaPgMngBtnDetailAction.PG_BTN_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPgMngBtnDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPgMngBtnDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPgMngBtnDetailForm.elements['maPgMngBtnDetailDTO.pgBtnId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPgMngBtnDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/>
<html:hidden property="maPgMngBtnDetailDTO.pgBtnId"/>
<html:hidden property="maPgMngBtnDetailDTO.buttonId"/>
<html:hidden property="maPgMngBtnDetailDTO.buttonLoc"/>
<html:hidden property="maPgMngBtnDetailDTO.buttonNo"/>
<html:hidden property="maPgMngBtnDetailDTO.buttonRemark"/>
<html:hidden property="maPgMngBtnDetailDTO.keyType"/><!-- 다국어 key_type -->
<html:hidden property="maPgMngBtnDetailDTO.keyNo"/><!-- 다국어 keyNo -->
<html:hidden property="maPgMngBtnDetailDTO.keyTypeStr"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 버튼명 -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.buttonName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngBtnDetailDTO.buttonDesc" tabindex="10"
								onblur="valButtonDesc('maPgMngBtnDetailDTO.buttonId','maPgMngBtnDetailDTO.buttonDesc',true);"/>
	               	   		<p class="open_spop">
								<a href="javascript:lovButton('maPgMngBtnDetailDTO.buttonId', 'maPgMngBtnDetailDTO.buttonDesc');">
								<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
				<!-- 버튼위치 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.buttonLoc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngBtnDetailDTO.buttonLocDesc" tabindex="20"/>
	               	   		<p class="open_spop">
								<a>
								<span>조회</span>
								</a>
							</p>
	               	   </div>
               	   </div>
				<!-- 버튼설명 -->
               	   <div class="field">
		               <label class="check"><bean:message key="LABEL.buttonDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngBtnDetailDTO.pgButtonDesc" tabindex="30"
							onblur="valLang('maPgMngBtnDetailDTO.keyType','maPgMngBtnDetailDTO.keyNo','maPgMngBtnDetailDTO.pgButtonDesc', true,'maPgMngBtnDetailDTO.keyTypeStr');"/>
						<p class="open_spop"><a href="javascript:lovLang('maPgMngBtnDetailDTO.keyType','maPgMngBtnDetailDTO.keyNo', 'maPgMngBtnDetailDTO.pgButtonDesc','BUTTON');"><span>조회</span></a></p>
					   </div>
               	   </div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
							<html:text property="maPgMngBtnDetailDTO.isUse" tabindex="40"
								onblur="valYn('maPgMngBtnDetailDTO.isUse', true);"/>
							<p class="open_spop">
								<a href="javascript:lovTable('maPgMngBtnDetailDTO.isUse', 'maPgMngBtnDetailDTO.isUse','YN');">
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 기본버튼 -->
				<div class="field">
					<label><bean:message key="LABEL.isBasic"/></label>
					<div class="input_box">
							<html:text property="maPgMngBtnDetailDTO.isBasic" tabindex="43"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 그룹시작 -->
				<div class="field">
					<label><bean:message key="LABEL.isSetGroup"/></label>
					<div class="input_box">
							<html:text property="maPgMngBtnDetailDTO.isSetGroup" tabindex="45"/>
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
	               	   		<html:text property="maPgMngBtnDetailDTO.ordNo" tabindex="50" />
	               	   </div>
				</div>
				<!-- 업무버튼 -->
				<div class="field">
				    <label><bean:message key="LABEL.isChkauth"/></label>
				    <div class="input_box">
						<html:text property="maPgMngBtnDetailDTO.isChkauth" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
				    </div>
				</div>
				
				<div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maPgMngBtnDetailDTO.remark" styleClass="ta50" tabindex="90" />
                    </div>
                 </div>
				
				
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>