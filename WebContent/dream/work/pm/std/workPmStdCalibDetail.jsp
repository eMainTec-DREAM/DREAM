<%--===========================================================================
교정표준값 타입
author  kim21017
version $Id: workPmStdCalibDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 교정구분 -->
<title><bean:message key='LABEL.calType'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var acSysDescAc;
function loadPage() 
{
    setTitle("workPmStdCalibDetailDTO.pmCalibStdTpNo", "workPmStdCalibDetailDTO.pmcTypeDesc");
    //For Save
    setForUpdate();
    //교정구분 자동완성
    acSysDescAc = acSysDesc("workPmStdCalibDetailDTO.pmcTypeDesc","workPmStdCalibDetailDTO.pmcTypeId","PMC_TYPE", true);
    
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	} 
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAPMCALIBSTDTP_ID');
    
    acSysDescAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmCalibStdTpId'].value = sequenceVal;
    workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmCalibStdTpNo'].value = sequenceVal;
    workPmStdCalibDetailForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}
/*
 * PMC_TYPE 중복체크
 */
function validPmcType()
{
	var actionUrl = contextPath + "/workPmStdCalibDetail.do";
	var param =  "&strutsAction=" + '<%= WorkPmStdCalibDetailAction.DETAIL_CHECK %>'
			  +  "&" + FormQueryString(workPmStdCalibDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidPmcType');
	
}

var pmcTypeCnt = 0;
function setValidPmcType(ajaxXmlDoc) 
{
	pmcTypeCnt = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
    if(pmcTypeCnt != '0')
    {
    	closeModal();
    	workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmcTypeId'].value = '';
    	workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmcTypeDesc'].value = '';
    	workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmcTypeDesc'].focus();
        
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
    } else {
    	//strutsAction 셋팅.
        if(ckCreate(currentPageId)) workPmStdCalibDetailForm.strutsAction.value = "<%=WorkPmStdCalibDetailAction.DETAIL_INPUT%>";
        else workPmStdCalibDetailForm.strutsAction.value = "<%=WorkPmStdCalibDetailAction.DETAIL_UPDATE%>";
        
    	var actionUrl = contextPath + "/workPmStdCalibDetail.do";
    	XMLHttpPost(actionUrl, FormQueryString(workPmStdCalibDetailForm), 'afterSave');
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

    validPmcType();

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmCalibStdTpId'].value);
    
    workPmStdCalibDetailForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmCalibStdTpId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workPmStdCalibDetailDTO.pmCalibStdTpNo", "workPmStdCalibDetailDTO.pmcTypeDesc");
    
}

 function goTabPage(pageId) 
 {
	 var form = document.workPmStdCalibDetailForm;
	 goCommonTabPage(form, '' , pageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = workPmStdCalibDetailForm.elements['workPmStdCalibDetailDTO.pmCalibStdTpId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/workPmStdCalibDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmStdCalibCommonDTO.pmCalibStdTpId"/><!-- Key -->
<html:hidden property="workPmStdCalibDetailDTO.pmCalibStdTpId"/><!-- Key -->
<html:hidden property="workPmStdCalibDetailDTO.pmcTypeId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 교정구분 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.calibType"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibDetailDTO.pmcTypeDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- No-->
			<div class="field">
				<label class="check"><bean:message key="LABEL.number"/></label>
				<div class="input_read">
					<html:text property="workPmStdCalibDetailDTO.pmCalibStdTpNo" readonly="true" />
				</div>
			</div>
			<!-- 계측범위/사용범위 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.measureRange"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibDetailDTO.measureRange" tabindex="20" />
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmStdCalibDetailDTO.remark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
