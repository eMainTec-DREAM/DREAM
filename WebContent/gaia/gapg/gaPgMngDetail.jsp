<%--===========================================================================
화면 - 상세
author  kim21017
version $Id: gaPgMngDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="gaia.gapg.action.GaPgMngDetailAction"%>
<html:html>
<head>
<!-- 화면 -->
<title><bean:message key='TAB.gaPgMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction, eqStatusAc, isUse, isUse2, pgIdVal;

function loadPage() 
{
	//Setting Function
	setFunc();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		//goTabPage("gaPgMngBtnList");
		pgIdVal.destroy();
	}
	
	setTitle("gaPgMngDetailDTO.fileName","gaPgMngDetailDTO.pageDesc");
	
	setForUpdate();
	
}

function setFunc()
{
	/*============= Valid Dup Start ==============*/
	pgIdVal = new valCheck({"gaPgMngDetailDTO.fileName":"file_name"});
	pgIdVal.setTable("TAPAGE");
	pgIdVal.init();
	/*============= Valid Dup End ==============*/
	
	/*================ AC =================== */
	eqStatusAc = new autoC({"gaPgMngDetailDTO.pageTypeDesc":"description"});
    eqStatusAc.setAcDisplay("DESCRIPTION");
    eqStatusAc.setAcConditionMap({
        	"list_type":"PAGE_TYPE",
        	"is_use":"Y"
  	   });
    eqStatusAc.setTable("TACDSYSD");
    eqStatusAc.setAcResultMap({
        "gaPgMngDetailDTO.pageTypeId":"cdsysd_no"
    });
    eqStatusAc.setAcResultLabel({
    	"gaPgMngDetailDTO.pageTypeDesc":"LABEL.desc",
    	"gaPgMngDetailDTO.pageTypeId":"LABEL.code"
    });  
    eqStatusAc.init();
    
    
    isUse = new autoC({"gaPgMngDetailDTO.isUse":"description"});
    isUse.setAcConditionMap({
        	"list_type":"IS_USE",
        	"is_use":"Y"
  	   });
    isUse.setTable("TACDSYSD");
    isUse.setAcResultLabel({
    	"gaPgMngDetailDTO.isUse":"CODESET.IS_USE"
    });  
    isUse.init();
    
    isUse2 = new autoC({"gaPgMngDetailDTO.isChkauth":"description"});
    isUse2.setAcConditionMap({
        	"list_type":"IS_USE",
        	"is_use":"Y"
  	   });
    isUse2.setTable("TACDSYSD");
    isUse2.setAcResultLabel({
    	"gaPgMngDetailDTO.isChkauth":"CODESET.IS_USE"
    });  
    isUse2.init();
}

function goInput()
{
	sequenceNextVal('SQAPAGE_ID');
	gaPgMngDetailForm.elements['gaPgMngDetailDTO.isUse'].value = 'Y';
	gaPgMngDetailForm.elements['gaPgMngDetailDTO.isChkauth'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	gaPgMngDetailForm.elements['gaPgMngDetailDTO.pageId'].value = sequenceVal;
	gaPgMngDetailForm.elements['gaPgMngCommonDTO.pageId'].value = sequenceVal;
	
	//goTabPage("gaPgMngBtnList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.gaPgMngDetailForm;

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
	if(ckCreate(currentPageId)) gaPgMngDetailForm.strutsAction.value = '<%=GaPgMngDetailAction.PG_DETAIL_INPUT%>';
	else gaPgMngDetailForm.strutsAction.value = '<%=GaPgMngDetailAction.PG_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/gaPgMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(gaPgMngDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	gaPgMngDetailForm.elements['gaPgMngDetailDTO.pageId'].value = gaPgMngDetailForm.elements['gaPgMngCommonDTO.pageId'].value;
	if (parent.findGridRow)	parent.findGridRow(gaPgMngDetailForm.elements['gaPgMngDetailDTO.pageId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/gaPgMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="gaPgMngCommonDTO.pageId" />
	<html:hidden property="gaPgMngDetailDTO.pageId" />
	<html:hidden property="gaPgMngDetailDTO.pageTypeId" />
 
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.fileName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="gaPgMngDetailDTO.fileName" tabindex="1" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.pageName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="gaPgMngDetailDTO.pageDesc" tabindex="10" />
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.pageDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="gaPgMngDetailDTO.remark" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="gaPgMngDetailDTO.isUse" tabindex="30"/>
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
						<html:text property="gaPgMngDetailDTO.isChkauth" tabindex="40"/>
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
						<html:text property="gaPgMngDetailDTO.pageTypeDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
			
			
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         
	</html:form>
</body>
</html:html>