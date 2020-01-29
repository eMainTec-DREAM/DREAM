<%--===========================================================================
설비등급 평가기준
author  kim21017
version $Id: assBaseDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBaseDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- No -->
<title><bean:message key='LABEL.number'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var eqCtgTypeAc;


function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	} 
	
    setTitle("assBaseDetailDTO.assBaseListNo", "assBaseDetailDTO.assBaseListDesc");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("assBaseDetailDTO.isUseDesc","assBaseDetailDTO.isUseId","IS_USE", true);
    
    //설비종류 자동완성
    eqCtgTypeAc = new autoC({"assBaseDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("assBaseDetailDTO.eqCtgId");
    eqCtgTypeAc.setAcResultMap({
        "assBaseDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAASSBASELIST_ID');
    
    assBaseDetailForm.elements['assBaseDetailDTO.isUseId'].value = 'Y';
    assBaseDetailForm.elements['assBaseDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('assBaseDetailDTO.isUseId', 'assBaseDetailDTO.isUseDesc', 'IS_USE', true);
    assBaseDetailForm.elements['assBaseDetailDTO.regDate'].value   = getToday();
}
function setSequenceVal(sequenceVal)
{
    assBaseDetailForm.elements['assBaseDetailDTO.assBaseListId'].value = sequenceVal;
    assBaseDetailForm.elements['assBaseDetailDTO.assBaseListNo'].value = sequenceVal;
    assBaseDetailForm.elements['assBaseCommonDTO.assBaseListId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
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
    if(ckCreate(currentPageId)) assBaseDetailForm.strutsAction.value = "<%=AssBaseDetailAction.DETAIL_INPUT%>";
    else assBaseDetailForm.strutsAction.value = "<%=AssBaseDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assBaseDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assBaseDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assBaseDetailForm.elements['assBaseDetailDTO.assBaseListId'].value);
    
    assBaseDetailForm.elements['assBaseCommonDTO.assBaseListId'].value = assBaseDetailForm.elements['assBaseDetailDTO.assBaseListId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assBaseDetailDTO.assBaseListNo", "assBaseDetailDTO.assBaseListDesc");
    
}

 function goTabPage(pageId) 
 {
	 var form = document.assBaseDetailForm;
	 
	 if(pageId == "maDocLibList" || pageId == "assBaseDocLibList")
		{	
			assBaseDetailForm.elements['maDocLibCommonDTO.objectId'].value = assBaseDetailForm.elements['assBaseCommonDTO.assBaseListId'].value;
			assBaseDetailForm.elements['maDocLibCommonDTO.objectType'].value = "ASSBASE";  // docDesc
			assBaseDetailForm.elements['maDocLibCommonDTO.description'].value = assBaseDetailForm.elements['assBaseDetailDTO.assBaseListDesc'].value;
			goCommonTabPage(form, '<%=AssBaseDetailAction.BASE_QUICK_SEARCH%>' , pageId);
		}
	else
		goCommonTabPage(form, '' , pageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = assBaseDetailForm.elements['assBaseDetailDTO.assBaseListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/assBaseDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBaseDetailDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBaseDetailDTO.eqCtgId"/><!-- Key -->
<html:hidden property="assBaseDetailDTO.isUseId"/>
<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 평가기준No -->
			<div class="field">
				<label><bean:message key="LABEL.assBaseNo"/></label>
				<div class="input_read">
					<html:text property="assBaseDetailDTO.assBaseListNo" readonly="true"/>
				</div>
			</div>
			<!-- 설비종류 -->
			<div class="field">
				<label><bean:message key="LABEL.eqCtgDesc"/></label>
				<div class="input_box">
					<html:text property="assBaseDetailDTO.eqCtgDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 평가기준 명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.assBaseName"/></label>
				<div class="input_box">
					<html:text property="assBaseDetailDTO.assBaseListDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 제형 -->
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="assBaseDetailDTO.isUseDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 등록일 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.regDate"/></label>
				<div class="input_box">
					<html:text property="assBaseDetailDTO.regDate" tabindex="40" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assBaseDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
