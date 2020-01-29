<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: rcmFfailDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.rcm.ffail.action.RcmFfailDetailAction"%>
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
		rcmNameAc = new autoC({"rcmFfailDetailDTO.rcmDesc":"description"});
		rcmNameAc.setTable("TARCMLIST");
		rcmNameAc.setKeyName("rcmFfailDetailDTO.rcmListId");
		rcmNameAc.setAcConditionMap({
	        "comp_no":loginUser.compNo
	    });
		rcmNameAc.setAcResultMap({
	        "rcmFfailDetailDTO.rcmListId":"rcmlist_id"
	    });
		rcmNameAc.init();
		
		goInput();
	}		
	else 
	{
		//수정시 readonly설정 
	    rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmDesc'].readOnly="true";
	    rcmFfailDetailForm.elements['rcmFfailDetailDTO.funcNo'].readOnly="true";
	    
	    document.getElementById("rcmDescDiv").className = "input_read";
	    document.getElementById("funcNoDiv").className = "input_read";
	    document.getElementById("rcmPopupDiv").style.visibility = "hidden";
	    
		goTabPage("rcmFfailItemList");
	}
	
	setTitle("rcmFfailDetailDTO.funcNo","rcmFfailDetailDTO.funcName");
	
	setForUpdate();

}

function goInput()
{
	sequenceNextVal('SQARCMFUNC_ID');

}

function setSequenceVal(sequenceVal)
{
	rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value = sequenceVal;
	rcmFfailDetailForm.elements['rcmFfailCommonDTO.rcmFuncId'].value = sequenceVal;
	
	goTabPage("rcmFfailItemList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId) 
{
	var form = document.rcmFfailDetailForm;

	if(pageId == "maDocLibList" || pageId == "rcmFfailDocLibList")
	{	
		rcmFfailDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmFfailDetailForm.elements['rcmFfailCommonDTO.rcmFuncId'].value;
		rcmFfailDetailForm.elements['maDocLibCommonDTO.objectType'].value = "RCMFUNC";
		rcmFfailDetailForm.elements['maDocLibCommonDTO.description'].value = rcmFfailDetailForm.elements['rcmFfailDetailDTO.funcName'].value;
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
	rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value = rcmFfailDetailForm.elements['rcmFfailCommonDTO.rcmFuncId'].value;
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) rcmFfailDetailForm.strutsAction.value = "<%=RcmFfailDetailAction.QNA_DETAIL_INPUT%>";
	else rcmFfailDetailForm.strutsAction.value = '<%=RcmFfailDetailAction.QNA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/rcmFfailDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFfailDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {     
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     rcmFfailDetailForm.elements['rcmFfailCommonDTO.rcmFuncId'].value = rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value;
     if (parent.findGridRow) parent.findGridRow(rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value);

//     rcmFfailDetailForm.elements['rcmFfailCommonDTO.rcmFuncId'].value = rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value;
     
     getTopPage().afterSaveAll(currentPageId);
     
     setTitle("rcmFfailDetailDTO.questionNo","rcmFfailDetailDTO.questionTitle");
     
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
     var objectId = rcmFfailDetailForm.elements['rcmFfailDetailDTO.rcmFuncId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmFfailDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmFfailCommonDTO.rcmFuncId" />
	<html:hidden property="rcmFfailDetailDTO.rcmFuncId" />
	<html:hidden property="rcmFfailDetailDTO.rcmListId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<div class="field">
                <label class="check"><bean:message key="LABEL.rcmDesc"/></label>
                <div class="input_box" id="rcmDescDiv">
                    <html:text property="rcmFfailDetailDTO.rcmDesc" tabindex="30"/>
                    <p class="open_spop" id="rcmPopupDiv">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <div class="field">
                <label ><bean:message key="LABEL.funcNo"/></label>
                <div class="input_box" id="funcNoDiv">
                    <html:text property="rcmFfailDetailDTO.funcNo" tabindex="60"/>
                </div>
             </div>  
              <div class="field_long">
                <label><bean:message key="LABEL.funcName"/></label>
                <div class="input_box">
                    <html:text property="rcmFfailDetailDTO.funcName" tabindex="60"/>
                </div>
             </div> 
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmFfailDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>