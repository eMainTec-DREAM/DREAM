<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: rcmFuncEqDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaItem"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var rcmNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId))
	{		
		rcmNameAc = new autoC({"rcmFuncEqDetailDTO.rcmDesc":"description"});
		rcmNameAc.setTable("TARCMLIST");
		rcmNameAc.setKeyName("rcmFuncEqDetailDTO.rcmListId");
		rcmNameAc.setAcConditionMap({
	        "comp_no":loginUser.compNo
	    });
		rcmNameAc.setAcResultMap({
	        "rcmFuncEqDetailDTO.rcmListId":"rcmlist_id"
	    });
		rcmNameAc.init();
		
		goInput();
	}		
	else 
	{
		//수정시 readonly설정 
	    rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.rcmDesc'].readOnly="true";
	    //rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.funcNo'].readOnly="true";
	    
	    document.getElementById("rcmDescDiv").className = "input_read";
	    document.getElementById("rcmPopupDiv").style.visibility = "hidden";
	    //rcmNameAc.destroy();
		goTabPage("rcmFuncEqItemList");
	}
	
	setTitle("rcmFuncEqDetailDTO.funcNo","rcmFuncEqDetailDTO.funcName");
	
	setForUpdate();

}

function goInput()
{
	sequenceNextVal('SQARCMFFAIL_ID');

}

function setSequenceVal(sequenceVal)
{
	rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.rcmFfailId'].value = sequenceVal;
	rcmFuncEqDetailForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = sequenceVal;
	
	goTabPage("rcmFuncEqItemList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId) 
{
	var form = document.rcmFuncEqDetailForm;

	if(pageId == "maDocLibList" || pageId == "rcmFuncEqDocLibList")
	{	
		rcmFuncEqDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmFuncEqDetailForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value;
		rcmFuncEqDetailForm.elements['maDocLibCommonDTO.objectType'].value = "RCMFFAIL";
		rcmFuncEqDetailForm.elements['maDocLibCommonDTO.description'].value = rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.funcName'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}

/**
 * 저장
 */ 
function goSave()
{
	rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.rcmFfailId'].value = rcmFuncEqDetailForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value;
    
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) rcmFuncEqDetailForm.strutsAction.value = "<%=RcmFuncEqDetailAction.QNA_DETAIL_INPUT%>";
	else rcmFuncEqDetailForm.strutsAction.value = '<%=RcmFuncEqDetailAction.QNA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/rcmFuncEqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFuncEqDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.rcmFfailId'].value = rcmFuncEqDetailForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value;
     
     if (parent.findGridRow)
    	 parent
    	         .findGridRow(rcmFuncEqDetailForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value);
//                                                        다시 원상복구 ↑ 

    getTopPage().afterSaveAll(currentPageId);

    setTitle("rcmFuncEqDetailDTO.rcmFfailNo","rcmFuncEqDetailDTO.description");
     
 }
 /**
 *신청완료
 */
 function goConfirm(){
	alert('goConfirm');
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmFuncEqDetailForm.elements['rcmFuncEqDetailDTO.rcmFfailId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmFuncEqDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmFuncEqCommonDTO.rcmFfailId" />
	<html:hidden property="rcmFuncEqDetailDTO.rcmFfailId" />
	<html:hidden property="rcmFuncEqDetailDTO.rcmListId" />
    <html:hidden property="rcmFuncEqDetailDTO.funcId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
            <!-- System 분석명 -->
			<div class="field">
                <label class="check"><bean:message key="LABEL.rcmDesc"/></label>
                <div class="input_box" id="rcmDescDiv">
                    <html:text property="rcmFuncEqDetailDTO.rcmDesc" tabindex="10"/>
                    <p class="open_spop" id="rcmPopupDiv">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <div class="field">
                <label ><bean:message key="LABEL.funcNo"/></label>
                <div class="input_box">
                    <html:text property="rcmFuncEqDetailDTO.funcNo" tabindex="20"/>
                </div>
             </div>  
              <div class="field">
                <label><bean:message key="LABEL.funcName"/></label>
                <div class="input_box">
                    <html:text property="rcmFuncEqDetailDTO.funcName" tabindex="30"/>
                </div>
             </div> 
             <div class="field">
			<label><bean:message key="LABEL.rcmFfailNo"/></label>
			<div class="input_read">
				<html:text property="rcmFuncEqDetailDTO.rcmFfailNo" tabindex="40"/>
			</div>
		    </div>
		    <div class="field">
			<label><bean:message key="LABEL.rcmFfailName"/></label>
			<div class="input_read">
				<html:text property="rcmFuncEqDetailDTO.description" tabindex="50" />
			</div>
		    </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmFuncEqDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>