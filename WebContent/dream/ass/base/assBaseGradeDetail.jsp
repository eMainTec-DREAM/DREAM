<%--===========================================================================
등급기준
author  kim21017
version $Id: assBaseGradeDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBaseGradeDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 등급 -->
<title><bean:message key='LABEL.grade0'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var acSysDescAC;

function loadPage() 
{
    setTitle("assBaseGradeDetailDTO.eqGradeDesc", "assBaseGradeDetailDTO.remark");
    //등급 자동완성
    acSysDescAC = acSysDesc("assBaseGradeDetailDTO.eqGradeDesc","assBaseGradeDetailDTO.eqGradeId","EQ_GRADE", true);
    acSysDesc("assBaseGradeDetailDTO.periodTypeDesc","assBaseGradeDetailDTO.periodType","PERIOD_TYPE",true);
    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAASSBASEGRADE_ID');
    
    acSysDescAC.openLov();
}
function setSequenceVal(sequenceVal)
{
    assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.assBaseGradeId'].value = sequenceVal;
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
    
	validGrade();
	if(isGradeValid!='0') return;
	
	var fromVal = parseFloat(assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.gradeFrom'].value);
	var toVal   = parseFloat(assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.gradeTo'].value);
	
	if(fromVal>=toVal) {
		alertMessage1("<bean:message key='MESSAGE.MSG0152'/>");
		closeModal();
		return;
	}
	
	validFromTo();
	if(isFromToValid!='0') return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) assBaseGradeDetailForm.strutsAction.value = "<%=AssBaseGradeDetailAction.DETAIL_INPUT%>";
    else assBaseGradeDetailForm.strutsAction.value = "<%=AssBaseGradeDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/assBaseGradeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(assBaseGradeDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.assBaseGradeId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assBaseGradeDetailDTO.eqGradeDesc", "assBaseGradeDetailDTO.remark");
    
}

var isGradeValid;
/** 등급 중복 체크 */
function validGrade(){
	var actionUrl = contextPath + "/assBaseGradeDetail.do";
	var param =  "&strutsAction=" + '<%= AssBaseGradeDetailAction.DETAIL_GRADE_CHECK %>'
	          +  "&" + FormQueryString(assBaseGradeDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidGrade');
}

function afterValidGrade(ajaxXmlDoc)
{
	isGradeValid = '0';
	isGradeValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isGradeValid != '0')
	{
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0150'/>");
		assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.eqGradeId'].value = '';
		assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.eqGradeDesc'].value = '';
    }
}

var isFromToValid;
/** FROM TO 값 중복 체크 */
function validFromTo(){
	var actionUrl = contextPath + "/assBaseGradeDetail.do";
	var param =  "&strutsAction=" + '<%= AssBaseGradeDetailAction.DETAIL_FROMTO_CHECK %>'
	          +  "&" + FormQueryString(assBaseGradeDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidFromTo');
}

function afterValidFromTo(ajaxXmlDoc)
{
	isFromToValid = '0';
	isFromToValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isFromToValid != '0')
	{
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0151'/>");
		assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.gradeFrom'].value = '';
		assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.gradeTo'].value = '';
    }
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBaseGradeDetailForm.elements['assBaseGradeDetailDTO.assBaseGradeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/assBaseGradeDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBaseGradeDetailDTO.assBaseGradeId"/><!-- Key -->
<html:hidden property="assBaseGradeDetailDTO.eqGradeId"/>
<html:hidden property="assBaseGradeDetailDTO.periodType" />
	<div class="article_box">
		<div class="form_box">
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.grade0"/></label>
				<div class="input_box">
					<html:text property="assBaseGradeDetailDTO.eqGradeDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="assBaseGradeDetailDTO.ordNo" tabindex="20"/>
				</div>
			</div>
			<!-- 등급값 FROM -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.gradeFrom"/></label>
				<div class="input_box">
					<html:text property="assBaseGradeDetailDTO.gradeFrom" tabindex="30" styleClass="num" />
				</div>
			</div>
			<!-- 등급값 TO -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.gradeTo"/></label>
				<div class="input_box">
					<html:text property="assBaseGradeDetailDTO.gradeTo" tabindex="40" styleClass="num" />
				</div>
			</div>
			<!-- 주기 -->
			<div class="field">
                <label><bean:message key="LABEL.cycleDesc"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="assBaseGradeDetailDTO.cycle" tabindex="45" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="assBaseGradeDetailDTO.periodTypeDesc" tabindex="100" />
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
            <!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="assBaseGradeDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
