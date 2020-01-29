<%--===========================================================================
출고요청부품 detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqPartsDetailAction"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html:html>
<head>
<!-- 출고요청부품 -->
<title><bean:message key='LABEL.partNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var partNoAc;

function loadPage() 
{
    setForUpdate();
    
    partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.ptEmgIssReqId'].value = partIssEmgReqPartsDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value;
    
	partNoAc = new autoC({"partIssEmgReqPartsDetailDTO.partNo":"part_no"});
	partNoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partNoAc.setTable("TAPARTS");
	partNoAc.setAcResultMap({
	    "partIssEmgReqPartsDetailDTO.partId":"part_id"
	    ,"partIssEmgReqPartsDetailDTO.partDesc":"full_desc"    
	});
	partNoAc.setKeyName("partIssEmgReqPartsDetailDTO.partId"); 
	partNoAc.init();
	
    acSysDesc("partIssEmgReqPartsDetailDTO.partGradeDesc","partIssEmgReqPartsDetailDTO.partGrade","PART_GRADE",true);
    
	if(ckCreate(currentPageId)) goInput();
    else 
    {
    	setTitle("partIssEmgReqPartsDetailDTO.partNo", "partIssEmgReqPartsDetailDTO.partDesc");
    	goUpdate();
    }
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAPTEMGISSLIST_ID');
	
    partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.partGrade'].value = '<%=partGrade%>';
    partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('partIssEmgReqPartsDetailDTO.partGrade', 'partIssEmgReqPartsDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
	partNoAc.openLov();
}
function setSequenceVal(sequenceVal)
{
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.ptEmgIssListId'].value = sequenceVal;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = sequenceVal;
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
    
	var issueQty = partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.issueQty'].value;
    if(issueQty == "" || parseFloat(issueQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.issQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
    
    partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.reqDate'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDate'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.wcodeId'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.wcodeId'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.ctctrId'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ctctrId'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.toWcodeId'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.toWcodeId'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.recBy'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.recBy'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.equipId'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.equipId'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.plantId'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.plantId'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.reqBy'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqBy'].value;
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.reqDept'].value = parent.parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDept'].value;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) partIssEmgReqPartsDetailForm.strutsAction.value ="<%=PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_INPUT%>";
    else partIssEmgReqPartsDetailForm.strutsAction.value = '<%=PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/partIssEmgReqPartsDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(partIssEmgReqPartsDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(partIssEmgReqPartsDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setTitle("partIssEmgReqPartsDetailDTO.partNo", "partIssEmgReqPartsDetailDTO.partDesc");
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAPTEMGISSLIST_ID');
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.partNo'].value = '';
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.partId'].value = '';
	partIssEmgReqPartsDetailForm.elements['partIssEmgReqPartsDetailDTO.partDesc'].value = '';
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = partIssEmgReqPartsDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/partIssEmgReqPartsDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="partIssEmgReqCommonDTO.compNo" />
		<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssListId" />
		<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId" />
		<html:hidden property="partIssEmgReqPartsDetailDTO.ptEmgIssListId" />
		<html:hidden property="partIssEmgReqPartsDetailDTO.ptEmgIssReqId" />
	    <html:hidden property="partIssEmgReqPartsDetailDTO.partId" />
	    <html:hidden property="partIssEmgReqPartsDetailDTO.partGrade" />
	    <html:hidden property="partIssEmgReqPartsDetailDTO.reqDate"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.wcodeId"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.ctctrId"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.toWcodeId"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.recBy"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.equipId"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.plantId"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.reqBy"/>
		<html:hidden property="partIssEmgReqPartsDetailDTO.reqDept"/>
	    <html:hidden property="partIssEmgReqPartsDetailDTO.ptemgissStatus"  value="W" />
	    <html:hidden property="partIssEmgReqPartsDetailDTO.ptissType"  value="COST" />
	    <html:hidden property="partIssEmgReqPartsDetailDTO.ptemgTaskStatus" value="W" />

		<div class="article_box">
			<div class="form_box">
	        	 <!-- 부품번호 -->
	        	 <div class="field">
	        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
	                <div id="partNoDiv" class="input_box">
	                    <html:text property="partIssEmgReqPartsDetailDTO.partNo" tabindex="10"/>
	                    <p id="partNoSchBtn" class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	        	 </div>
	        	 <!-- 부품명 -->
				 <div class="field">
					<label><bean:message key="LABEL.partNameSize"/></label>
	        	 	<div class="input_read">
	        	 		<html:text property="partIssEmgReqPartsDetailDTO.partDesc" readonly="true" tabindex="20"/>
	        	 	</div>
				 </div>
				 <!-- 출고수량 -->
	        	 <div class="field">
	        	 	<label class="check"><bean:message key="LABEL.issQty"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="partIssEmgReqPartsDetailDTO.issueQty" tabindex="30" styleClass="num"/>
	        	 	</div>
	        	 </div>
	        	 <!-- 자재등급 -->
				 <div class="field">
					<label><bean:message key="LABEL.partGrade"/></label>
					<div class="input_box">
						<html:text property="partIssEmgReqPartsDetailDTO.partGradeDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				 </div>
				 <!-- 현재고 -->
	        	 <div class="field">
	        	 	<label><bean:message key="LABEL.stockQty"/></label>
	        	 	<div class="input_read">
	        	 		<html:text property="partIssEmgReqPartsDetailDTO.stockQty" tabindex="50" readonly="true" styleClass="num"/>
	        	 	</div>
	        	 </div>
				 <!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="partIssEmgReqPartsDetailDTO.remark" styleClass="ta50" tabindex="90" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
