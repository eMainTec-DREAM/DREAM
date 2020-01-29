<%--===========================================================================
설비마스터 - 개정
author  kim21017
version $Id: commRevRevision.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.comm.revision.action.CommRevAction"%>
<html:html>
<head>
<!-- 개정 -->
<title><bean:message key='LABEL.revision' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">

<script language="javascript">

var revObjType;
function loadPage() 
{
	revObjType = commRevForm.elements['commRevCommonDTO.revisionObjType'].value;
	
	goInput();

	setForUpdate("commRev");
	
	setLabel();
}

function setLabel()
{
	if("PMSTD" == revObjType)
	{
		$('#objIdLabel').html('<bean:message key="LABEL.pmNo"/>');
		$('#descLabel').html('<bean:message key="LABEL.pmDesc"/>');
	}
	else if("ASSET" == revObjType)
	{
		$('#objIdLabel').html('<bean:message key="LABEL.equipNo"/>');
		$('#descLabel').html('<bean:message key="LABEL.equipName"/>');
		
		commRevForm.elements['commRevCommonDTO.objectNo'].readOnly = "false";
	}
	else if("STWRK" == revObjType)
	{
		$('#objIdLabel').html('<bean:message key="LABEL.stWrkNo"/>');
		$('#descLabel').html('<bean:message key="LABEL.stWrkDesc"/>');
	}
}

function goInput(){
	if("PMSTD" == revObjType)
	{
		sequenceNextVal('SQAPM_ID');
	}
	else if("ASSET" == revObjType)
	{
		sequenceNextVal('SQAEQUIP_ID');
	}
	else if("STWRK" == revObjType)
	{
		sequenceNextVal('SQASTWRK_ID');
	}
	
	//구분 - 개정
	commRevForm.elements['commRevCommonDTO.revisionType'].value = "R";
	commRevForm.elements['commRevCommonDTO.revisionTypeDesc'].value = "R";
	valSysDir('commRevCommonDTO.revisionType', 'commRevCommonDTO.revisionTypeDesc', 'REVISION_TYPE', true);
	//업무유형
	commRevForm.elements['commRevCommonDTO.revisionObjTypeDesc'].value = revObjType;
	valSysDir('commRevCommonDTO.revisionObjType', 'commRevCommonDTO.revisionObjTypeDesc', 'REVISION_OBJECT_TYPE', true);
	//revision 진행상태
	commRevForm.elements['commRevCommonDTO.revisionStatusId'].value = "P";
	commRevForm.elements['commRevCommonDTO.revisionStatusDesc'].value = "P";
	valSysDir('commRevCommonDTO.revisionStatusId', 'commRevCommonDTO.revisionStatusDesc', 'REVISION_STATUS', true);

	commRevForm.elements['commRevCommonDTO.revisionStepStatusId'].value = "WRT";
	//제정일자
	commRevForm.elements['commRevCommonDTO.wrkDate'].value = getToday();
	//제정부서
	commRevForm.elements['commRevCommonDTO.wrkDeptId'].value = loginUser.deptId;
	commRevForm.elements['commRevCommonDTO.wrkDeptDesc'].value = loginUser.deptDesc;
	//제정담당자
	commRevForm.elements['commRevCommonDTO.wrkEmpId'].value = loginUser.empId;
	commRevForm.elements['commRevCommonDTO.wrkEmpDesc'].value = loginUser.empName;
	
	setRevNo();
}

function setSequenceVal(sequenceVal)
{
	commRevForm.elements['commRevCommonDTO.objectId'].value = sequenceVal;
}

/**
 * Revision 번호 자동셋팅
 *  - 소수점 자릿수 확인 후 마지막 자리에서 증가
 */
function setRevNo(){
	commRevForm.strutsAction.value = '<%=CommRevAction.MAX_REV_NO%>';
    var url = contextPath + "/commRevRevision.do";
    $.post(url,FormQueryString(commRevForm) ,function(_data){
    	var maxRevNo=parseXmlDoc(_data, 'DESC');
		var nextRevNo = '1.0';

    	if(maxRevNo == null || maxRevNo == '')	nextRevNo = '1.0';
    	else
    	{	
    		var pointSize = maxRevNo.toString().indexOf('.');
	    	
	    	if (pointSize < 0) {
	    		nextRevNo = parseInt(maxRevNo) + 1;
	    	}
	    	else {
	    		// 소수점 자릿수
	    		pointSize = maxRevNo.toString().length - (pointSize + 1);
	    		// 소수점 마지막 자릿수 값 증가
	    		nextRevNo = parseFloat(maxRevNo)+Math.pow(0.1, pointSize);
				// 소수점 자릿수 정리	    		
	    		nextRevNo = nextRevNo.toString().substring(0, (nextRevNo.toString().indexOf('.')+(parseInt(pointSize)+1)) );
    		}
    	}
    	// 3자리 단위로 나우어서 ,를 붙여준다.
    	commRevForm.elements['commRevCommonDTO.revNo'].value = nextRevNo.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	    topDoc.updateArray['commRev'] ='commRevCommonDTO.revNo';
    });
    
}
	
function validRevNo(){
	var url = contextPath + "/commRevRevision.do";
	var param = "&strutsAction=" + '<%=CommRevAction.CHECK_REV_NO%>' 
	+  "&" + FormQueryString(commRevForm);
	
	XMLHttpPostVal(url, param, 'afterValidRevNo');
}

var isValid;
function afterValidRevNo(ajaxXmlDoc){
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0'){
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0155'/>");
		commRevForm.elements['commRevCommonDTO.revNo'].value = '';
	}
	else
	{
		goSaveAfterValid();
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
/* 	if (checkIsUpdate()) return; */
	if(checkValidation()) return;
	validRevNo();

}

function goSaveAfterValid()
{
	//strutsAction 셋팅.
	commRevForm.strutsAction.value = '<%=CommRevAction.COMM_REV_REVISION_INPUT%>'; 
	var actionUrl = contextPath + "/commRevRevision.do";
	XMLHttpPost(actionUrl, FormQueryString(commRevForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	//저장 후 설비 리스트 조회
	if (parent.parent.afterCreate)	parent.parent.afterCreate(commRevForm.elements['commRevCommonDTO.objectId'].value, commRevForm.elements['commRevCommonDTO.param'].value);
	
	getTopPage().afterSaveAll("commRev");
	goCloseLayer();
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/commRevRevision" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="commRevCommonDTO.param" />
	<html:hidden property="commRevCommonDTO.objectId" />
	<html:hidden property="commRevCommonDTO.revisionType" />
	<html:hidden property="commRevCommonDTO.revisionObjType" />
	<html:hidden property="commRevCommonDTO.revisionStatusId" />
	<html:hidden property="commRevCommonDTO.wrkDeptId" />
	<html:hidden property="commRevCommonDTO.wrkEmpId" />
	<html:hidden property="commRevCommonDTO.oldObjectId" />
	<html:hidden property="commRevCommonDTO.oldRevisionhistId" />
	<html:hidden property="commRevCommonDTO.revisionStepStatusId" />
	
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.revision"/></div>
			</div>
			<div class="function_box detail">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		
	<div class="article_box">
		<div class="form_box">
			<!-- 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionTypeDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- Revision 업무유형 -->
			<div class="field">
				<label><bean:message key="LABEL.revObjType"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionObjTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- Object No  -->
			<div class="field">
				<label id="objIdLabel"><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.objectNo" tabindex="30"  readonly="true"/>
				</div>
			</div>
			<!-- 이전 Revision No  -->
			<div class="field">
				<label><bean:message key="LABEL.befRevNo"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.oldRevNo" tabindex="35"  readonly="true"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label id="descLabel"><bean:message key="LABEL.equipName"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.description" tabindex="40"  readonly="true"/>
				</div>
			</div>
			<!-- 문서번호 -->
			<div class="field">
				<label><bean:message key="LABEL.docNo"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.docNo" tabindex="50"  readonly="true"/>
				</div>
			</div>
			<!-- Revision# -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.revNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.revNo" tabindex="60" styleClass="num" />
				</div>
			</div>
			<!-- Revision 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionStatus"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionStatusDesc" tabindex="70" readonly="true"/>
				</div>
			</div>
			<!-- 개정일자 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionDate"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDate" tabindex="80" readonly="true"/>
				</div>
			</div>
			<!-- 개정 담당부서 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionDept"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDeptDesc" tabindex="90" readonly="true"/>
				</div>
			</div>
			<!-- 개정 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionManager"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkEmpDesc" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 개정사항 -->
			<div class="field_long">
				<label><bean:message key="LABEL.revisionDesc"/></label>
				<div class="input_box">
					<html:textarea property="commRevCommonDTO.revDesc" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/comm/revision/commRevRevision_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/comm/revision/commRevRevision_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
	</div> <!--  end section_wrap -->
</html:form>
</body>
</html:html>