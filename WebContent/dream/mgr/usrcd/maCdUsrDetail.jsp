<%--===========================================================================
사용자코드관리 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrCdDetailAction"%>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 사용자코드관리 -->
<title><bean:message key='LABEL.dirType' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

function loadPage() 
{
    if(ckCreate(currentPageId)){
    	document.getElementById("codeDiv").className = "input_box"; 
    	maCdUsrDetailForm.elements['maCdUsrDetailDTO.dirType'].readOnly = false;
    	goInput();
    }else 
    {
    	document.getElementById("codeDiv").className = "input_read"; 
    	maCdUsrDetailForm.elements['maCdUsrDetailDTO.dirType'].readOnly = true;
    }
    
    setTitle("maCdUsrDetailDTO.dirType", "maCdUsrDetailDTO.description");
    
    setForUpdate();
    
    // 사용여부
    //href="javascript:lovTable('maCdUsrDetailDTO.isUse', 'maCdUsrDetailDTO.isUseDesc', 'YN');"
    acSysDesc("maCdUsrDetailDTO.isUseDesc","maCdUsrDetailDTO.isUse","IS_USE", true);

}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQACDUSRM_ID');
    maCdUsrDetailForm.elements['maCdUsrDetailDTO.isUse'].value = 'Y';
    maCdUsrDetailForm.elements['maCdUsrDetailDTO.isUseDesc'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
    maCdUsrDetailForm.elements['maCdUsrDetailDTO.cdUsrmId'].value = sequenceVal;
    maCdUsrDetailForm.elements['maCdUsrDetailDTO.cdUsrmNo'].value = sequenceVal;
    maCdUsrDetailForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = sequenceVal;
    
    //goTabPage("maCdUsrCdList");
}

function goTabPage(pageId)
{
    var form = document.maCdUsrDetailForm;

    goCommonTabPage(form, '' , pageId);
}

/**
 * dirType 중복 체크
 */
function valDirType()
{
	isValid = 0;
	if(maCdUsrDetailForm.strutsAction.value == '0')
	{
		var actionUrl = contextPath + "/maCdUsrDetail.do";
		var param =  "&strutsAction=" + '<%= MaCdUsrDetailAction.CDUSR_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(maCdUsrDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setValidDirType');
	}
}

/**
 * valDirType()실행 후 호출
 */
var isValid = 0;
function setValidDirType(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maCdUsrDetailForm.elements['maCdUsrDetailDTO.dirType'].value = '';
        maCdUsrDetailForm.elements['maCdUsrDetailDTO.dirType'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    }
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
    if(ckCreate(currentPageId)) maCdUsrDetailForm.strutsAction.value = "<%=MaCdUsrDetailAction.CDUSR_DETAIL_INPUT%>";
    else maCdUsrDetailForm.strutsAction.value = '<%=MaCdUsrDetailAction.CDUSR_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maCdUsrDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maCdUsrDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maCdUsrDetailForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = maCdUsrDetailForm.elements['maCdUsrDetailDTO.cdUsrmId'].value;

     if (parent.findGridRow)	parent.findGridRow(maCdUsrDetailForm.elements['maCdUsrCommonDTO.cdUsrmId'].value);
     
     setTitle("maCdUsrDetailDTO.dirType", "maCdUsrDetailDTO.description");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maCdUsrDetailForm.elements['maCdUsrDetailDTO.cdUsrmId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maCdUsrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maCdUsrCommonDTO.compNo" />
	<html:hidden property="maCdUsrCommonDTO.cdUsrmId" />
	<html:hidden property="maCdUsrCommonDTO.code" />
    <html:hidden property="maCdUsrDetailDTO.cdUsrmId" />
    <html:hidden property="maCdUsrDetailDTO.cdUsrmNo" />
    <html:hidden property="maCdUsrDetailDTO.isUse" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.dirType"/></label>
        	 	<div class="input_box" id="codeDiv">
        	 		<html:text property="maCdUsrDetailDTO.dirType"  
        	 		    onblur="valDirType();" tabindex="10"/>
        	 	</div>
        	 </div>
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.dirTypeDesc"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maCdUsrDetailDTO.description" tabindex="20"/>
        	 	</div>
        	 </div>
        	 <div class="field_long">
        	 	<label><bean:message key="LABEL.cdUsrRemark"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maCdUsrDetailDTO.remark" tabindex="30"/>
        	 	</div>
        	 </div>
        	 <div class="field">
                <label><bean:message key="LABEL.isUse"/></label>
                <div class="input_box">
                        <html:text property="maCdUsrDetailDTO.isUseDesc" tabindex="40"/>
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