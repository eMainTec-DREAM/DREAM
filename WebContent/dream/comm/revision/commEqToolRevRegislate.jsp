<%--===========================================================================
설비마스터 - 계측기 제정
author  kim21017
version $Id: commEqToolRevRegislate.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<!-- 제정 -->
<title><bean:message key='LABEL.regislate' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">

<script language="javascript">

var revObjType;
var parentEquipAc;
var toolNameAc;

function loadPage() 
{
	revObjType = commRevForm.elements['commRevCommonDTO.revisionObjType'].value;
	
	goInput();
	
	setForUpdate("commRev");
	
	parentEquipAc = new autoC({"commRevCommonDTO.parentEquipDesc":"description"});
    parentEquipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S",
 	   "eqctg_type":"MC"
 	   });
    parentEquipAc.setTable("TAEQUIPMENT");
    parentEquipAc.setAcResultMap({
        "commRevCommonDTO.parentEquipId":"equip_id"
        ,"commRevCommonDTO.objectNo":"item_no"
        ,"commRevCommonDTO.parentEqLocId":"eqloc_id"
    });
    parentEquipAc.setKeyName("commRevCommonDTO.parentEquipId"); 
    parentEquipAc.init();
    

    toolNameAc = new autoC({"commRevCommonDTO.description":"description"});
    toolNameAc.setAcConditionMap({
        	"comp_no":loginUser.compNo,
        	"dir_type":"GUAGE_TYPE"
  	   });
    toolNameAc.setAcResultMap({
        "commRevCommonDTO.guageType":"cdusrd_no"
    });
    toolNameAc.setKeyName("commRevCommonDTO.guageType"); 
    toolNameAc.setTable("TACDUSRD");
    toolNameAc.init();
    
}

function goUpdate(){

}

function goInput()
{
	sequenceNextVal('SQAEQUIP_ID');
	
	//구분 - 제정
	commRevForm.elements['commRevCommonDTO.revisionType'].value = "C";
	commRevForm.elements['commRevCommonDTO.revisionTypeDesc'].value = "C";
	valSysDir('commRevCommonDTO.revisionType', 'commRevCommonDTO.revisionTypeDesc', 'REVISION_TYPE', true);
	//업무유형
	commRevForm.elements['commRevCommonDTO.revisionObjType'].value = "ASSET";
	commRevForm.elements['commRevCommonDTO.revisionObjTypeDesc'].value = "ASSET";
	valSysDir('commRevCommonDTO.revisionObjType', 'commRevCommonDTO.revisionObjTypeDesc', 'REVISION_OBJECT_TYPE', true);
	//revision 진행상태
	commRevForm.elements['commRevCommonDTO.revisionStatusId'].value = "W";
	commRevForm.elements['commRevCommonDTO.revisionStatusDesc'].value = "W";
	valSysDir('commRevCommonDTO.revisionStatusId', 'commRevCommonDTO.revisionStatusDesc', 'REVISION_STATUS', true);
	//제정일자
	commRevForm.elements['commRevCommonDTO.wrkDate'].value = getToday();
	//제정부서
	commRevForm.elements['commRevCommonDTO.wrkDeptId'].value = loginUser.deptId;
	commRevForm.elements['commRevCommonDTO.wrkDeptDesc'].value = loginUser.deptDesc;
	//제정담당자
	commRevForm.elements['commRevCommonDTO.wrkEmpId'].value = loginUser.empId;
	commRevForm.elements['commRevCommonDTO.wrkEmpDesc'].value = loginUser.empName;
	
	// Revision번호 초기값
	commRevForm.elements['commRevCommonDTO.revNo'].value = '1.0';
}
function afterAutoCmpt(code)
{
		if(code=="commRevCommonDTO.eqCtgDesc")
		{
			if(loginUser.compNo=='170'){
				commRevForm.elements['commRevCommonDTO.description'].value = 
					commRevForm.elements['commRevCommonDTO.eqCtgDesc'].value;
			}
			
			if("Y"!=commRevForm.elements['commRevCommonDTO.eqCtgIsLowestLvl'].value){
				alertMessage1("<bean:message key='MESSAGE.MSG0060'/>");
				commRevForm.elements['commRevCommonDTO.eqCtgId'].value          = "";
				commRevForm.elements['commRevCommonDTO.eqCtgDesc'].value        = "";
				commRevForm.elements['commRevCommonDTO.eqCtgIsLowestLvl'].value = "";
			}
		}
}
function setSequenceVal(sequenceVal)
{
	//equip_id
	commRevForm.elements['commRevCommonDTO.objectId'].value = sequenceVal;
	//item_no
	commRevForm.elements['commRevCommonDTO.objectNo'].value = sequenceVal;
}
	
function validObjectNo(){
	var url = contextPath + "/commRevRegislate.do";
	var param = "&strutsAction=" + '<%=CommRevAction.CHECK_OBJECT_NO%>'
	+  "&" + FormQueryString(commRevForm);
	
	XMLHttpPostVal(url, param, 'afterValidItemNo');
}

var isValid;
function afterValidItemNo(ajaxXmlDoc){
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0'){
		var objDesc='<bean:message key="LABEL.equipNo"/>';
		
		closeModal();
		alertMessage1(objDesc+" "+"<bean:message key='MESSAGE.MSG0009'/>");
		commRevForm.elements['commRevCommonDTO.objectNo'].value = '';
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
	
	validObjectNo();
	if(isValid!='0') return;

	//strutsAction 셋팅.
	commRevForm.strutsAction.value = '<%=CommRevAction.COMM_REV_REGISLATE_INPUT%>'; 
	
	var actionUrl = contextPath + "/commRevRegislate.do";
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
	if (parent.afterCreate)	parent.afterCreate(commRevForm.elements['commRevCommonDTO.objectId'].value, commRevForm.elements['commRevCommonDTO.param'].value);
	getTopPage().afterSaveAll("commRev");
	
	goCloseLayer();
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/commEqToolRevRegislate.do" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="commRevCommonDTO.objectId" />
	<html:hidden property="commRevCommonDTO.revisionType" />
	<html:hidden property="commRevCommonDTO.revisionObjType" />
	<html:hidden property="commRevCommonDTO.revisionStatusId" />
	<html:hidden property="commRevCommonDTO.wrkDeptId" />
	<html:hidden property="commRevCommonDTO.wrkEmpId" />
	<html:hidden property="commRevCommonDTO.eqCtgId" />
	<html:hidden property="commRevCommonDTO.eqCtgDesc" />
	<html:hidden property="commRevCommonDTO.selectedWoType" /> <!-- PM SelectType -->
	<html:hidden property="commRevCommonDTO.selectedPmType" /> <!-- PM SelectType -->
	<html:hidden property="commRevCommonDTO.eqCtgTypeId" /> <!-- ASSET SelectType -->
	<html:hidden property="commRevCommonDTO.eqCtgIsLowestLvl" />
	<html:hidden property="commRevCommonDTO.param" />
	<html:hidden property="commRevCommonDTO.parentEquipId" />
	<html:hidden property="commRevCommonDTO.parentEqLocId" />
	<html:hidden property="commRevCommonDTO.guageType" />
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.regislate"/></div>
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
			<!-- 부모설비 -->
			<div class="field_long">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.parentEquipDesc" tabindex="30" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Object No  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.toolNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.objectNo" tabindex="40"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.toolName"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.description" tabindex="50" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 문서번호 -->
			<div class="field">
				<label><bean:message key="LABEL.docNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.docNo" tabindex="60" />
				</div>
			</div>
			<!-- Revision# -->
			<div class="field">
				<label><bean:message key="LABEL.revNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.revNo" tabindex="70" styleClass="num" />
				</div>
			</div>
			<!-- Revision 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionStatus"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionStatusDesc" tabindex="80" readonly="true"/>
				</div>
			</div>
			<!-- 제정일자 -->
			<div class="field">
				<label><bean:message key="LABEL.wrkDate"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDate" tabindex="90" readonly="true"/>
				</div>
			</div>
			<!-- 제정 담당부서 -->
			<div class="field">
				<label><bean:message key="LABEL.wrkDept"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDeptDesc" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 제정 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.wrkEmp"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkEmpDesc" tabindex="110" readonly="true"/>
				</div>
			</div>
			<!-- 제정사항 -->
			<div class="field_long">
				<label><bean:message key="LABEL.revDesc"/></label>
				<div class="input_box">
					<html:textarea property="commRevCommonDTO.revDesc" styleClass="ta50" tabindex="120" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/comm/revision/commEqToolRevRegislate_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/comm/revision/commEqToolRevRegislate_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
	</div> <!--  end section_wrap -->
</html:form>
</body>
</html:html>