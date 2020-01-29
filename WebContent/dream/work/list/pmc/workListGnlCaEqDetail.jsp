<%--===========================================================================
검교정상세 측정값상세
author  kim21017
version $Id: maWoResultBmOilCraftDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WorkListGnlCaEqDetailAction"%>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key="TAB.workListGnlCaEqList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변수  */
var baseToolAc;
var sopNoAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("workListGnlCaEqDetailDTO.description");
	setForUpdate();

	baseToolAc = new autoC({"workListGnlCaEqDetailDTO.description":"description"});
	baseToolAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"is_standard_eq":"Y"
 	   });
	baseToolAc.setTable("TAEQTOOL");
	baseToolAc.setAcResultMap({
        "workListGnlCaEqDetailDTO.equipId":"equip_id"
        ,"workListGnlCaEqDetailDTO.itemNo":"item_no"
        ,"workListGnlCaEqDetailDTO.serialNo":"serial_no"
        ,"workListGnlCaEqDetailDTO.nextPlanDate":"next_calib_date"
        ,"workListGnlCaEqDetailDTO.woNo":"calib_cert_no"
    });
	baseToolAc.setKeyName("workListGnlCaEqDetailDTO.equipId"); 
	baseToolAc.init();
	

    sopNoAc = new autoC({"workListGnlCaEqDetailDTO.calibSopdocNoDesc":"description"});
    sopNoAc.setAcConditionMap({
          "comp_no":loginUser.compNo
       	, "dir_type":"CALIB_SOPDOC_NO"
  	   });
    sopNoAc.setTable("TACDUSRD");
    sopNoAc.setKeyName("workListGnlCaEqDetailDTO.calibSopdocNo");
    sopNoAc.setAcResultMap({
        "workListGnlCaEqDetailDTO.calibSopdocNo":"cdusrd_no"
    });
    sopNoAc.init();
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "workListGnlCaEqDetailDTO.description")
	{
		var npd = $('[name="workListGnlCaEqDetailDTO.nextPlanDate"]');
		
		npd.val(dFormat(npd.val()));
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('sqawocalibstdeq_id');
}

function setSequenceVal(sequenceVal)
{
	workListGnlCaEqDetailForm.elements['workListGnlCaEqDetailDTO.woNo'].value = workListGnlCaEqDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
	workListGnlCaEqDetailForm.elements['workListGnlCaEqDetailDTO.wocalibstdeqId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	checkDuplication();
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workListGnlCaEqDetailForm.elements['workListGnlCaEqDetailDTO.wocalibstdeqId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workListGnlCaEqDetailDTO.description");
}

function checkDuplication() {
	workListGnlCaEqDetailForm.elements['workListGnlCaEqDetailDTO.wkOrId'].value = workListGnlCaEqDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
	
	var url = contextPath + "/workListGnlCaEqDetail.do";
	
	var param = "strutsAction="+'<%= WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_DCHECK %>' + 
				    "&"+FormQueryString(workListGnlCaEqDetailForm);
	
	XMLHttpPostVal(url, param, 'afterCheckDuplication');
}

var isValid;
function afterCheckDuplication(ajaxXmlDoc){
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0'){
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0009'/>");
	}
	else
	{
		if(ckCreate(currentPageId))
		{
			workListGnlCaEqDetailForm.strutsAction.value = '<%=WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_INPUT%>';
		}
		else
		{
			workListGnlCaEqDetailForm.strutsAction.value = '<%= WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_UPDATE %>';
		}
		
		var actionUrl = contextPath + "/workListGnlCaEqDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(workListGnlCaEqDetailForm), 'afterSave');
	}
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListGnlCaEqDetailForm.elements['workListGnlCaEqDetailDTO.wocalibstdeqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListGnlCaEqDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultMstrCommonDTO.woNo"/>
	<html:hidden property="workListGnlCaEqDetailDTO.wkOrId"/>
	<html:hidden property="workListGnlCaEqDetailDTO.wocalibstdeqId"/>
	<html:hidden property="workListGnlCaEqDetailDTO.calibWkorId"/>
	<html:hidden property="workListGnlCaEqDetailDTO.equipId"/>
	<html:hidden property="workListGnlCaEqDetailDTO.calibSopdocNo"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 표준기 -->
			<div class="field">
				<label><bean:message key="LABEL.calibEq"/></label>
				<div class="input_box">
					<html:text property="workListGnlCaEqDetailDTO.description" tabindex="10"/>
					<p class="open_spop">
						<a>
						 <span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 표준기# -->
			<div class="field">
				<label><bean:message key="LABEL.calibEqNo"/></label>
				<div class="input_read">
					<html:text property="workListGnlCaEqDetailDTO.itemNo" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 일련번호 -->
			<div class="field">
				<label><bean:message key="LABEL.serialNo"/></label>
				<div class="input_read">
					<html:text property="workListGnlCaEqDetailDTO.serialNo" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 교정성적서# -->
			<div class="field">
				<label><bean:message key="LABEL.calibRstNo"/></label>
				<div class="input_read">
					<html:text property="workListGnlCaEqDetailDTO.woNo" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 차기교정일 -->
			<div class="field">
				<label><bean:message key="LABEL.nextPlanDate"/></label>
				<div class="input_read">
					<html:text property="workListGnlCaEqDetailDTO.nextPlanDate" tabindex="50" readonly="true"/>
				</div>
			</div>
			<!-- 관련SOP# -->
			<div class="field">
				<label><bean:message key="LABEL.calibSopdocNo"/></label>
				<div class="input_box">
					<html:text property="workListGnlCaEqDetailDTO.calibSopdocNoDesc" tabindex="60"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>