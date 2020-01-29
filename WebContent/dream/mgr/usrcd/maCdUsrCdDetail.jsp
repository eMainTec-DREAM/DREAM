<%--===========================================================================
사용자세부코드 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.usrcd.action.MaCdUsrCdDetailAction"%>
<jsp:useBean id="maCdUsrCdDetailForm" class="dream.mgr.usrcd.form.MaCdUsrCdDetailForm" scope="request" />
<html>
<head>
<!--사용자세부코드  -->
<title><bean:message key="LABEL.cdUsrdCode"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var cdUsrdAc;

function loadPage() 
{
    cdUsrdAc = new autoC({"maCdUsrCdDetailDTO.pcdUsrdDesc":"description"});
    cdUsrdAc.setTable("TACDUSRD");
    cdUsrdAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    cdUsrdAc.setAcDConditionMap({
        "cdusrm_id":"maCdUsrCommonDTO.cdUsrmId"
    });
    cdUsrdAc.setAcResultMap({
          "maCdUsrCdDetailDTO.pcdUsrdId":"cdusrd_id" // 상위코드ID
    });
    cdUsrdAc.init();
    
    // 사용여부
    acSysDesc("maCdUsrCdDetailDTO.isUseDesc","maCdUsrCdDetailDTO.isUse","IS_USE", true);

    
    if(ckCreate(currentPageId)){
    	document.getElementById("codeDiv").className = "input_box"; 
    	maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdNo'].readOnly = false;
    	goInput();
    }else{
    	document.getElementById("codeDiv").className = "input_read"; 
    	maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdNo'].readOnly = true;
    }
    
    
    setTitle("maCdUsrCdDetailDTO.cdUsrdNo", "maCdUsrCdDetailDTO.description");
    setForUpdate();
    
}

function goInput()
{
    // 시퀀스를 조회한다.
    sequenceNextVal('SQACDUSRD_ID');
    maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrmId'].value = maCdUsrCdDetailForm.elements['maCdUsrCommonDTO.cdUsrmId'].value;
    maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.isUse'].value = 'Y';
    maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.isUseDesc'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
    maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdId'].value = sequenceVal;
}

/**
 * dirType 중복 체크
 */
function valCode()
{
    isValid = 0;
    if(maCdUsrCdDetailForm.strutsAction.value == '0')
    {
    	maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.dirType'].value = parent.parent.M$('maCdUsrDetailDTO.dirType').value;
        var actionUrl = contextPath + "/maCdUsrCdDetail.do";
        var param =  "&strutsAction=" + '<%= MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maCdUsrCdDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidCode');
    }
    else
   	{
    	goSaveAction();
   	}
}

/**
 * valDirType()실행 후 호출
 */
var isValid = 0;
function setValidCode(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdNo'].value = '';
        maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
        
        closeModal();
        return;
    }
    else 
    {
    	goSaveAction();
    }

}

function goSave()
{
	valCode();
}

function goSaveAction()
{
  	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
	
    if(ckCreate(currentPageId)) maCdUsrCdDetailForm.strutsAction.value = '<%=MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_INPUT%>';
    else maCdUsrCdDetailForm.strutsAction.value = '<%= MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_UPDATE %>';
	var actionUrl = contextPath + "/maCdUsrCdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maCdUsrCdDetailForm), 'afterSave');	
}

function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    setTitle("maCdUsrCdDetailDTO.cdUsrdNo", "maCdUsrCdDetailDTO.description");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maCdUsrCdDetailForm.elements['maCdUsrCdDetailDTO.cdUsrdId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maCdUsrCdDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maCdUsrCommonDTO.compNo" />
<html:hidden property="maCdUsrCommonDTO.cdUsrmId" />
<html:hidden property="maCdUsrCommonDTO.cdUsrdId" />
<html:hidden property="maCdUsrCdDetailDTO.cdUsrmId" />
<html:hidden property="maCdUsrCdDetailDTO.cdUsrdId" />
<html:hidden property="maCdUsrCdDetailDTO.pcdUsrdId" />
<html:hidden property="maCdUsrCdDetailDTO.isUse" />
<html:hidden property="maCdUsrCdDetailDTO.dirType" />
    <!-- searchbox 박스 Line -->

	       <div class="article_box">
	           <div class="form_box">
	               <div class="field">
	               	   <!-- 분류코드 -->
		               <label class="check"><bean:message key="LABEL.cdUsrdCode"/></label> 
	               	   <div class="input_box" id="codeDiv">
	               	   		<html:text property="maCdUsrCdDetailDTO.cdUsrdNo" tabindex="10"/>
	               	   </div>
               	   </div>
               	   <div class="field">
	               	   <!-- 상위코드 -->
		               <label><bean:message key="LABEL.parentCode"/></label>
                       <div class="input_box">
                           <html:text property="maCdUsrCdDetailDTO.pcdUsrdDesc" tabindex="20" />
                           <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                       </div>
                   </div>
                   <div class="field">
	               	   <!-- 분류코드명 -->
		               <label class="check"><bean:message key="LABEL.cdUsrdCodeDesc"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maCdUsrCdDetailDTO.description" tabindex="30" />
	               	   </div>
               	   </div>
               	   <div class="field">
	               	   <!-- 분류설명 -->
		               <label><bean:message key="LABEL.codeRemark"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maCdUsrCdDetailDTO.remark" tabindex="40"/>
	               	   </div>
               	   </div>
               	   <div class="field">
		                <label><bean:message key="LABEL.mngCd"/></label>
		                <div class="input_box">
		                    <html:text property="maCdUsrCdDetailDTO.mngCd" tabindex="45" />
		                </div>
		           </div>
				   <div class="field">
	               	   <!-- 조회순서 -->
		               <label><bean:message key="LABEL.ordNo"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maCdUsrCdDetailDTO.ordNo" tabindex="50" />
	               	   </div>
				   </div>
				   <div class="field">
					   <label><bean:message key="LABEL.isUse"/></label>
                       <div class="input_box">
                        <html:text property="maCdUsrCdDetailDTO.isUseDesc" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                </div>
                   </div>
               </div>
           </div><!--article_box end-->

</html:form> 
</body>
</html>