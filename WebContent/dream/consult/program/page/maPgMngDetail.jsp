<%--===========================================================================
화면 - 상세
author  kim21017
version $Id: maPgMngDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction"%>
<html:html>
<head>
<!-- 화면 -->
<title><bean:message key='TAB.maPgMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maPgMngBtnList");
	}
	
	setTitle("maPgMngDetailDTO.fileName","maPgMngDetailDTO.pageDesc");
	
	setForUpdate();
	
	 acSysDesc("maPgMngDetailDTO.isChkauth","maPgMngDetailDTO.isChkauth","IS_USE",true);
	 
 	 acSysDesc("maPgMngDetailDTO.isUse","maPgMngDetailDTO.isUse","IS_USE",true);
	 acSysDesc("maPgMngDetailDTO.pageTypeDesc","maPgMngDetailDTO.pageTypeId","PAGE_TYPE",true);
}

function goInput()
{
	sequenceNextVal('SQAPAGE_ID');
	maPgMngDetailForm.elements['maPgMngDetailDTO.isUse'].value = 'Y';
	maPgMngDetailForm.elements['maPgMngDetailDTO.isChkauth'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maPgMngDetailForm.elements['maPgMngDetailDTO.pageId'].value = sequenceVal;
	maPgMngDetailForm.elements['maPgMngCommonDTO.pageId'].value = sequenceVal;

	goTabPage("maPgMngBtnList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maPgMngDetailForm;

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
	if(ckCreate(currentPageId)) maPgMngDetailForm.strutsAction.value = '<%=MaPgMngDetailAction.PG_DETAIL_INPUT%>';
	else maPgMngDetailForm.strutsAction.value = '<%=MaPgMngDetailAction.PG_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPgMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPgMngDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPgMngDetailForm.elements['maPgMngDetailDTO.pageId'].value = maPgMngDetailForm.elements['maPgMngCommonDTO.pageId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPgMngDetailForm.elements['maPgMngDetailDTO.pageId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPgMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPgMngCommonDTO.pageId" />
	<html:hidden property="maPgMngDetailDTO.pageId" />
	<html:hidden property="maPgMngDetailDTO.pageTypeId" />
 
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.fileName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maPgMngDetailDTO.fileName" tabindex="1" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.pageName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maPgMngDetailDTO.pageDesc" tabindex="10" />
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="maPgMngDetailDTO.isUse" tabindex="30"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field">
					<!-- 업무페이지 -->
             	 	<label><bean:message key="LABEL.isChkauth"/></label>
					<div class="input_box">
						<html:text property="maPgMngDetailDTO.isChkauth" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				
				<!-- 설비상태 -->
				<div class="field">
					<label><bean:message key="LABEL.pageType"/></label>
					<div class="input_box">
						<html:text property="maPgMngDetailDTO.pageTypeDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<div class="field">
             	 	<label><bean:message key="LABEL.pageCateg"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maPgMngDetailDTO.pageCateg" tabindex="60" />
             	 	</div>
             	 </div>
             	 
             	 <div class="field_long">
                    <label><bean:message key="LABEL.pageDesc"/></label>
                    <div class="input_box">
                        <html:textarea property="maPgMngDetailDTO.remark" styleClass="ta50" tabindex="70" />
                    </div>
                 </div>
			
			
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         
	</html:form>
</body>
</html:html>