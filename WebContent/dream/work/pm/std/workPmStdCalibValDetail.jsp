<%--===========================================================================
표준값
author  kim21017
version $Id: workPmStdCalibValDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibValDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 회차 -->
<title><bean:message key='LABEL.calibPointType'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var acSysDescAc;
function loadPage() 
{
    setTitle("workPmStdCalibValDetailDTO.calibPointTypeDesc", "workPmStdCalibValDetailDTO.calibPoint");
    //For Save
    setForUpdate();
    acSysDescAc = acSysDesc("workPmStdCalibValDetailDTO.calibPointTypeDesc","workPmStdCalibValDetailDTO.calibPointTypeId","CALIB_POINT_TYPE", true);
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAPMCALIBSTDVALUE_ID');
    
    acSysDescAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    workPmStdCalibValDetailForm.elements['workPmStdCalibValDetailDTO.pmCalibStdValueId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) workPmStdCalibValDetailForm.strutsAction.value = "<%=WorkPmStdCalibValDetailAction.DETAIL_INPUT%>";
    else workPmStdCalibValDetailForm.strutsAction.value = "<%=WorkPmStdCalibValDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workPmStdCalibValDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmStdCalibValDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workPmStdCalibValDetailForm.elements['workPmStdCalibValDetailDTO.pmCalibStdValueId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workPmStdCalibValDetailDTO.calibPointTypeDesc", "workPmStdCalibValDetailDTO.calibPoint");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmStdCalibValDetailForm.elements['workPmStdCalibValDetailDTO.pmCalibStdValueId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workPmStdCalibValDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmStdCalibCommonDTO.pmCalibStdTpId"/><!-- Key -->
<html:hidden property="workPmStdCalibValDetailDTO.pmCalibStdValueId"/><!-- Key -->
<html:hidden property="workPmStdCalibValDetailDTO.calibPointTypeId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 회차 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.calibPointType"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.calibPointTypeDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.ordNo" tabindex="20"/>
				</div>
			</div>
			<!-- 교정Point -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.calibPoint"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.calibPoint" tabindex="30"/>
				</div>
			</div>
			<!-- 허용오차 -->
			<div class="field">
				<label><bean:message key="LABEL.allowValue"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.allowValue" tabindex="40" styleClass="num" />
				</div>
			</div>
			<!-- As Found 표준값 -->
			<div class="field">
				<label ><bean:message key="LABEL.asfStdValue1"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.asfStdValue" tabindex="40" styleClass="num" />
				</div>
			</div>
			<!-- As Left 표준값 -->
			<div class="field">
				<label><bean:message key="LABEL.aslStdValue1"/></label>
				<div class="input_box">
					<html:text property="workPmStdCalibValDetailDTO.aslStdValue" tabindex="40" styleClass="num" />
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmStdCalibValDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
