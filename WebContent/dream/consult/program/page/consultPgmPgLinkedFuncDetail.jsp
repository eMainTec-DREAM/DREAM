<%--===========================================================================
화변별 연결기능 상세 
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.ConsultPgmPgLinkedFuncDetailAction"%>
<html>
<head>
<!--화면 - 연결기능 -->
<title><bean:message key="TAB.consultPgmPgLinkedFuncDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var labelAc, linkedFuncAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("consultPgmPgLinkedFuncDetailDTO.linkedFuncDesc");
	setForUpdate();
	
	acSysDesc("consultPgmPgLinkedFuncDetailDTO.isUseDesc","consultPgmPgLinkedFuncDetailDTO.isUseId","IS_USE");
	
	labelAc = new autoC({"consultPgmPgLinkedFuncDetailDTO.keyName":"key_name"});
	labelAc.setTable("TALANG");
	labelAc.setAcResultMap({
    	"consultPgmPgLinkedFuncDetailDTO.keyType":"key_type",
    	"consultPgmPgLinkedFuncDetailDTO.keyNo":"key_no"
    });
	labelAc.setAcConditionMap({
    	"key_type":"LABEL"
    });
	labelAc.setKeyName("consultPgmPgLinkedFuncDetailDTO.keyName");
	labelAc.init();  
	
	// Linked Function
    linkedFuncAc = new autoC({"consultPgmPgLinkedFuncDetailDTO.linkedFuncDesc":"LINKEDFUNCDESC"});
    linkedFuncAc.setTable("TALINKEDFUNC");
    linkedFuncAc.setAcConditionMap({
    	"is_use" : "Y"
    });
    linkedFuncAc.setKeyName("consultPgmPgLinkedFuncDetailDTO.linkedFuncId");
    linkedFuncAc.setAcResultMap({
        "consultPgmPgLinkedFuncDetailDTO.linkedFuncId":"linkedFuncId"
    });
    linkedFuncAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGLINKEDFUNC_ID');
	consultPgmPgLinkedFuncDetailForm.elements['consultPgmPgLinkedFuncDetailDTO.isUseId'].value = 'Y';
	consultPgmPgLinkedFuncDetailForm.elements['consultPgmPgLinkedFuncDetailDTO.isUseDesc'].value = 'Y';
	valSysDir('consultPgmPgLinkedFuncDetailDTO.isUseId', 'consultPgmPgLinkedFuncDetailDTO.isUseDesc', 'IS_USE', true);
}

function setSequenceVal(sequenceVal)
{
	consultPgmPgLinkedFuncDetailForm.elements['consultPgmPgLinkedFuncDetailDTO.pgLinkedFuncId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) consultPgmPgLinkedFuncDetailForm.strutsAction.value = '<%=ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_INPUT%>';
	else consultPgmPgLinkedFuncDetailForm.strutsAction.value = '<%= ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/consultPgmPgLinkedFuncDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmPgLinkedFuncDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultPgmPgLinkedFuncDetailForm.elements['consultPgmPgLinkedFuncDetailDTO.pgLinkedFuncId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/consultPgmPgLinkedFuncDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/>
<html:hidden property="consultPgmPgLinkedFuncDetailDTO.pgLinkedFuncId"/>
<html:hidden property="consultPgmPgLinkedFuncDetailDTO.linkedFuncId"/>
<html:hidden property="consultPgmPgLinkedFuncDetailDTO.isUseId"/>
<html:hidden property="consultPgmPgLinkedFuncDetailDTO.keyNo"/>
<html:hidden property="consultPgmPgLinkedFuncDetailDTO.keyType"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- linked function -->
               <div class="field">
	               <label class="check"><bean:message key="LABEL.linkedFuncDesc"/></label>
               	   <div class="input_box">
               	   		<html:text property="consultPgmPgLinkedFuncDetailDTO.linkedFuncDesc" tabindex="10" />
						<p class="open_spop"><a><span>조회</span></a></p>
             	   </div>
           	   </div>
				<!-- 화면표시명 -->
				<div class="field">
					<label><bean:message key="LABEL.labelName"/></label>
					<div class="input_box">
						<html:text property="consultPgmPgLinkedFuncDetailDTO.keyName" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
							<html:text property="consultPgmPgLinkedFuncDetailDTO.isUseDesc" tabindex="20"/>
							<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 조회순서 -->
				<div class="field">
	               <label><bean:message key="LABEL.ordNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="consultPgmPgLinkedFuncDetailDTO.ordNo" tabindex="30" />
               	   </div>
				</div>
				<!-- 비고 -->
                <div class="field_long">
                   <label><bean:message key="LABEL.remark"/></label>
                   <div class="input_box">
                        <html:textarea property="consultPgmPgLinkedFuncDetailDTO.remark" styleClass="ta50" tabindex="50" />
                   </div>
                </div>
                
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>