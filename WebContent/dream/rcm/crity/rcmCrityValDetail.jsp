<%--===========================================================================
Criticality Matrix Val Detail
author  kim21017
version $Id: rcmCrityValDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityValDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 항목 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var isCriticalAc;
var crityColorAc;
var myCP;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	$('[name="rcmCrityValDetailDTO.crityColorDesc"]').attr({"colorbox":"true",
		"id":"test"
	});
	
	myCP = dhtmlXColorPicker(["test"]);
	
    setTitle("rcmCrityValDetailDTO.crityColDesc", "rcmCrityValDetailDTO.crityRowDesc");
    //For Save
    setForUpdate();
    //Critical 여부 자동완성
    acSysDesc("rcmCrityValDetailDTO.isCriticalDesc","rcmCrityValDetailDTO.isCriticalId","IS_USE", true);
    //색상 자동완성
    //acSysDesc("rcmCrityValDetailDTO.crityColorDesc","rcmCrityValDetailDTO.crityColorId","CRITYCOLOR", true);
    
    var crityColAc = new autoC({"rcmCrityValDetailDTO.crityColorDesc":"description"});
    crityColAc.setTable("TACDUSRD");
    crityColAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"CRITYCOLOR"
  	   });
    crityColAc.setAcResultMap({
        "rcmCrityValDetailDTO.crityColorId":"cdusrd_no"
    });
    crityColAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQACRITYVALUE_ID');
}
function setSequenceVal(sequenceVal)
{
    rcmCrityValDetailForm.elements['rcmCrityValDetailDTO.crityValId'].value = sequenceVal;
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
    if(!ckCreate(currentPageId)) rcmCrityValDetailForm.strutsAction.value = "<%=RcmCrityValDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/rcmCrityValDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmCrityValDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmCrityValDetailForm.elements['rcmCrityValDetailDTO.crityValId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("rcmCrityValDetailDTO.crityColDesc", "rcmCrityValDetailDTO.crityRowDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmCrityValDetailForm.elements['rcmCrityValDetailDTO.crityValId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/rcmCrityValDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityValDetailDTO.crityValId"/><!-- Key -->
<html:hidden property="rcmCrityValDetailDTO.crityColId"/>
<html:hidden property="rcmCrityValDetailDTO.crityRowId"/>
<html:hidden property="rcmCrityValDetailDTO.crityColorId"/>
<html:hidden property="rcmCrityValDetailDTO.isCriticalId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- Column 항목 -->
			<div class="field">
				<label><bean:message key="LABEL.colObject"/></label>
				<div class="input_read">
					<html:text property="rcmCrityValDetailDTO.crityColDesc" tabindex="10" readonly="true" />
				</div>
			</div>
			<!-- Row 항목 -->
			<div class="field">
				<label><bean:message key="LABEL.rowObject"/></label>
				<div class="input_read">
					<html:text property="rcmCrityValDetailDTO.crityRowDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<!-- level -->
			<div class="field">
				<label><bean:message key="LABEL.level"/></label>
				<div class="input_read">
					<html:text property="rcmCrityValDetailDTO.crityLevel" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 값 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.value"/></label>
				<div class="input_box">
					<html:text property="rcmCrityValDetailDTO.crityValDesc" tabindex="40" />
				</div>
			</div>
			<!-- 색상 -->
			<div class="field">
				<label><bean:message key="LABEL.color1"/></label>
				<div class="input_box">
					<html:text property="rcmCrityValDetailDTO.crityColorDesc" tabindex="50"/>
				</div>
			</div>
			<!-- 중요여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isCritical"/></label>
				<div class="input_box">
					<html:text property="rcmCrityValDetailDTO.isCriticalDesc" tabindex="60" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmCrityValDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
