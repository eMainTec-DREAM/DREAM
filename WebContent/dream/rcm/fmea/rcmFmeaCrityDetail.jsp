
<%--===========================================================================
상세
author  kim21017
version $Id: rcmFmeaCrityDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.rcm.fmea.action.RcmFmeaCrityDetailAction"%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc, crityRow;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("rcmFmeaCrityDetailDTO.colName");
	setForUpdate();
	
	mainMngAc = new autoC({"rcmFmeaCrityDetailDTO.colName":"col_name"});
    mainMngAc.setTable("TACRITYVALUE");
    mainMngAc.setAcResultMap({
        "rcmFmeaCrityDetailDTO.isCritical":"is_critical",
        "rcmFmeaCrityDetailDTO.crityLvl":"crity_lvl",
        "rcmFmeaCrityDetailDTO.critycolor":"critycolor",
        "rcmFmeaCrityDetailDTO.crityvalue":"crityvalue",
        "rcmFmeaCrityDetailDTO.colOrdNo":"col_ord_no",
        "rcmFmeaCrityDetailDTO.critycolId":"critycol_id"
        ,"rcmFmeaCrityDetailDTO.crityvalueId":"crityvalue_id"
    });
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"critylist_id":rcmFmeaCrityDetailForm.elements['rcmFmeaCommonDTO.critylistId'].value
  	   });
    mainMngAc.setAcDConditionMap({
    	"row_name":"rcmFmeaCrityDetailDTO.rowName"
  	   });
 //   mainMngAc.setKeyName("maEqMstrDetailDTO.mainMngId");
    mainMngAc.init();
    
    
    crityRow = new autoC({"rcmFmeaCrityDetailDTO.rowName":"row_name"});
    crityRow.setTable("TACRITYVALUE");
    crityRow.setAcResultMap({
        "rcmFmeaCrityDetailDTO.isCritical":"is_critical",
        "rcmFmeaCrityDetailDTO.crityLvl":"crity_lvl",
        "rcmFmeaCrityDetailDTO.critycolor":"critycolor",
        "rcmFmeaCrityDetailDTO.crityvalue":"crityvalue",
        "rcmFmeaCrityDetailDTO.rowOrdNo":"row_ord_no",
        "rcmFmeaCrityDetailDTO.crityrowId":"crityrow_id"
        ,"rcmFmeaCrityDetailDTO.crityvalueId":"crityvalue_id"
    });
    crityRow.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"critylist_id":rcmFmeaCrityDetailForm.elements['rcmFmeaCommonDTO.critylistId'].value
  	   });
    crityRow.setAcDConditionMap({
    	"col_name":"rcmFmeaCrityDetailDTO.colName"
  	   });
 //   mainMngAc.setKeyName("maEqMstrDetailDTO.mainMngId");
    crityRow.init();
}

function goUpdate()
{
	setReadOnly("rcmFmeaCrityDetailDTO.colName");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMCRITY_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmFmeaCrityDetailForm.elements['rcmFmeaCrityDetailDTO.rcmcrityId'].value = sequenceVal;
}

function goSaveAct(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmFmeaCrityDetailForm.strutsAction.value = '<%=RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_INPUT%>';
	else rcmFmeaCrityDetailForm.strutsAction.value = '<%= RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmFmeaCrityDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFmeaCrityDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmFmeaCrityDetailForm.elements['rcmFmeaCrityDetailDTO.rcmcrityId'].value);

    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
}

function goSave()
{
	if(checkValidation()) return;

	if(ckCreate(currentPageId))
	{    
		var url = contextPath + "/rcmFmeaCrityDetail.do";
		rcmFmeaCrityDetailForm.strutsAction.value = '<%=RcmFmeaCrityDetailAction.RCM_FMEA_CHECK_VAL%>';
		
		$.post(url,FormQueryString(rcmFmeaCrityDetailForm) , function(_data){
			_data = $.trim(_data);
			rcmFmeaCrityDetailForm.strutsAction.value = "0";
			
			if(_data == "OK") goSaveAct();
			else {
				closeModal();
				alertMessage1("해당 항목은 등록했습니다.");
			}
	    });
	}
	else goSaveAct();
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmFmeaCrityDetailForm.elements['rcmFmeaCrityDetailDTO.rcmcrityId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmFmeaCrityDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmfmeaId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmlistId"/>
<html:hidden property="rcmFmeaCommonDTO.critylistId"/>

<html:hidden property="rcmFmeaCrityDetailDTO.rcmcrityId"/>
<html:hidden property="rcmFmeaCrityDetailDTO.critycolor"/>
<html:hidden property="rcmFmeaCrityDetailDTO.isCritical"/>
<html:hidden property="rcmFmeaCrityDetailDTO.crityLvl"/>
<html:hidden property="rcmFmeaCrityDetailDTO.critylistId"/>
<html:hidden property="rcmFmeaCrityDetailDTO.rowOrdNo"/>
<html:hidden property="rcmFmeaCrityDetailDTO.colOrdNo"/>
<html:hidden property="rcmFmeaCrityDetailDTO.crityvalueId"/>
<html:hidden property="rcmFmeaCrityDetailDTO.critycolId"/>
<html:hidden property="rcmFmeaCrityDetailDTO.crityrowId"/>

    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">

			<!-- 항목 -->
			<div class="field">
				<label  class="check"><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="rcmFmeaCrityDetailDTO.colName" tabindex="20" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field_blank"></div>
			<!-- Level명 -->
			<div class="field">
				<label  class="check"><bean:message key="LABEL.levelDesc"/></label>
				<div class="input_box">
					<html:text property="rcmFmeaCrityDetailDTO.rowName" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 결정값 -->
			<div class="field">
				<label><bean:message key="LABEL.crityValue"/></label>
				<div class="input_read">
					<html:text property="rcmFmeaCrityDetailDTO.crityvalue" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmFmeaCrityDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>