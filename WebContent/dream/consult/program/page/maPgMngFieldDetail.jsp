<%--===========================================================================
화변별 필드 상세 
author  kim21017
version $Id: maPgMngFieldDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.consult.program.page.action.MaPgMngFieldDetailAction"%>
<html>
<head>
<!--화면 - 필드 -->
<title><bean:message key="TAB.maPgMngFieldDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var labelAc;
var cdSysMAc;
var cdUsrMAc;
var formInputDetailTypeAc;
var codeListTypeAc;
var gLabelAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPgMngFieldDetailDTO.fieldId");
	setForUpdate();
	
	acSysDesc("maPgMngFieldDetailDTO.fieldOptionDesc","maPgMngFieldDetailDTO.fieldOption","FIELD_OPTION");
	acSysDesc("maPgMngFieldDetailDTO.readonlyYnDesc","maPgMngFieldDetailDTO.readonlyYn","READONLY_YN");
	acSysDesc("maPgMngFieldDetailDTO.checkYnDesc","maPgMngFieldDetailDTO.checkYn","CHECK_YN", true);
	acSysDesc("maPgMngFieldDetailDTO.displayYn","maPgMngFieldDetailDTO.displayYn","IS_USE");
	acSysDesc("maPgMngFieldDetailDTO.hiddenYn","maPgMngFieldDetailDTO.hiddenYn","IS_USE");
	
	//화면입력 타입 자동완성,LOV 세팅
	acSysDesc("maPgMngFieldDetailDTO.formInputTypeDesc","maPgMngFieldDetailDTO.formInputTypeId","FORM_INPUT_TYPE");
	
	labelAc = new autoC({"maPgMngFieldDetailDTO.keyName":"key_name"});
	labelAc.setTable("TALANG");
	labelAc.setAcResultMap({
    	"maPgMngFieldDetailDTO.keyType":"key_type",
    	"maPgMngFieldDetailDTO.keyNo":"key_no"
    });
	labelAc.setAcConditionMap({
    	"key_type":"LABEL"
    });
	labelAc.setKeyName("maPgMngFieldDetailDTO.keyName");
	labelAc.init();
	
	cdSysMAc = new autoC({"maPgMngFieldDetailDTO.codeListTypeDesc":"description"});
	cdSysMAc.setTable("TACDSYSM");
	cdSysMAc.setAcResultMap({
		"maPgMngFieldDetailDTO.codeListTypeId":"list_type"
	});
	cdSysMAc.setAcConditionMap({
		"is_use":"Y"
	});
	cdSysMAc.setKeyName("maPgMngFieldDetailDTO.codeListTypeId");

	
	cdUsrMAc = new autoC({"maPgMngFieldDetailDTO.codeListTypeDesc":"description"});
	cdUsrMAc.setTable("TACDUSRM");
	cdUsrMAc.setAcResultMap({
		"maPgMngFieldDetailDTO.codeListTypeId":"dir_type"
	});
	cdUsrMAc.setAcConditionMap({
		"is_use":"Y"
	});
	cdUsrMAc.setKeyName("maPgMngFieldDetailDTO.codeListTypeId");

	/* 그룹명 */
	gLabelAc = new autoC({"maPgMngFieldDetailDTO.groupKeyName":"key_name"});
	gLabelAc.setAcConditionMap({
		"key_type":"LABEL"
	  });
	gLabelAc.setTable("TALANG");
	gLabelAc.setKeyName("maPgMngFieldDetailDTO.groupKeyNo");
	gLabelAc.setAcResultMap({
	    "maPgMngFieldDetailDTO.groupKeyNo":"key_no",
	    "maPgMngFieldDetailDTO.groupKeyType":"key_type"
	});
	gLabelAc.init();
	
	//그룹옵션
	acSysDesc("maPgMngFieldDetailDTO.groupOptionDesc","maPgMngFieldDetailDTO.groupOption","GROUP_OPTION",true);
	
	if(!ckCreate(currentPageId)) goUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGFIELD_ID');
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.hiddenYn'].value = 'N';
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.displayYn'].value = 'Y';
}

function goUpdate(){
	maPgMngFieldDetailForm.elements['maPgMngFieldListDTO.pgFieldId'].value = 
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.pgFieldId'].value;
	afterSetFormInputType();
}

function setSequenceVal(sequenceVal)
{
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.pgFieldId'].value = sequenceVal;
	maPgMngFieldDetailForm.elements['maPgMngFieldListDTO.pgFieldId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPgMngFieldDetailForm.strutsAction.value = '<%=MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INPUT%>';
	else maPgMngFieldDetailForm.strutsAction.value = '<%= MaPgMngFieldDetailAction.PG_FIELD_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPgMngFieldDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPgMngFieldDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.pgFieldId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
    
    afterSetFormInputType();
    
}
function afterAutoCmpt(code)
{
	if(code=="maPgMngFieldDetailDTO.formInputTypeDesc"){
		afterSetFormInputType(true);
	}
	
	if(code=="maPgMngFieldDetailDTO.formInputDetailTypeDesc"){
		afterSetCodeListType(true);
	}
}

/**
 * 화면입력타입 선택 후 자식 코드 세팅 준비
 */
 function afterSetFormInputType(isChange){
	
		hideField('maPgMngFieldDetailDTO.formInputDetailTypeDesc'); // 세부타입 숨기기
		hideField('maPgMngFieldDetailDTO.codeListTypeDesc'); // 코드유형 숨기기
	
		if(typeof formInputDetailTypeAc != "undefined") formInputDetailTypeAc.destroy();
		
	switch(maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.formInputTypeId'].value){
		//텍스트,일,월,년 필드 타입인 경우 세부타입, 코드유형을 숨긴다.
		case 'TEXT_INPUT_TYPE': 
		case 'DATE_INPUT_TYPE': 
		case 'MONTH_INPUT_TYPE': 
		case 'YEAR_INPUT_TYPE': 
			hideField('maPgMngFieldDetailDTO.formInputDetailTypeDesc'); // 세부타입 숨기기
			hideField('maPgMngFieldDetailDTO.codeListTypeDesc'); // 코드유형 숨기기
			break;
		case 'CODE_INPUT_TYPE':
			showField('maPgMngFieldDetailDTO.formInputDetailTypeDesc'); // 세부타입 표시
			formInputDetailTypeAc = acSysDesc("maPgMngFieldDetailDTO.formInputDetailTypeDesc","maPgMngFieldDetailDTO.formInputDetailTypeId","CODE_INPUT_TYPE");
			hideField('maPgMngFieldDetailDTO.codeListTypeDesc'); // 코드유형 숨기기
			
			afterSetCodeListType();
			break;
	}
	
	// 부모코드 새로 선택 시 하위 값 리셋
	if(isChange){
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.formInputDetailTypeId'].value   = '';
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.formInputDetailTypeDesc'].value = '';
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeId'].value   = '';
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeDesc'].value = '';
	}
}


 /**
  * 화면입력 세부타입 선택 후 자식 코드 세팅 준비
  */
function afterSetCodeListType(isChange){
	 
	if(typeof codeListTypeAc != "undefined") codeListTypeAc.destroy();
	
	switch(maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.formInputDetailTypeId'].value){
		case 'TACDSYSD' : 
			showField('maPgMngFieldDetailDTO.codeListTypeDesc'); // 코드유형 보기
			codeListTypeAc = cdSysMAc;
			codeListTypeAc.init();
			break;
		case 'TACDUSRD' : 
			showField('maPgMngFieldDetailDTO.codeListTypeDesc'); // 코드유형 보기
			codeListTypeAc = cdUsrMAc;
			codeListTypeAc.init();
			break;
		default : 
			hideField('maPgMngFieldDetailDTO.codeListTypeDesc');
			break;
	}
	
	// 부모코드 새로 선택 시 하위 값 리셋
	if(isChange){
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeId'].value   = '';
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.codeListTypeDesc'].value = '';
	}
	
}
 
 function goTabPage(pageId){
	var form  = document.maPgMngFieldDetailForm;
	goCommonTabPage(form, '', pageId);
 }

 function goColumn()
 {
 	if(maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.columnDesc'].value=='')
 	{
 		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.tableDesc'].value='';
 		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.tableId'].value='';
 	}
 }

function setLovValue(returnArray, dirType)
{
	var tableName  = returnArray[0];
	var columnName = returnArray[1];
	var columnDesc = returnArray[3];
	var tableDesc  = returnArray[4];
	var tableId    = returnArray[5];
	var pageName   = returnArray[6];
	
	if(pageName == "lovRefColumnPopup"){			
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.tableName'].value  = tableName;
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.columnName'].value = columnName;
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.columnDesc'].value = columnDesc;
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.tableDesc'].value  = tableDesc;
		maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.tableId'].value    = tableId;
	}
}
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPgMngFieldDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/>
<html:hidden property="maPgMngFieldListDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.keyNo"/>
<html:hidden property="maPgMngFieldDetailDTO.keyType"/>
<html:hidden property="maPgMngFieldDetailDTO.fieldOption"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputDetailTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.codeListTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.checkYn"/>
<html:hidden property="maPgMngFieldDetailDTO.readonlyYn"/>

<html:hidden property="maPgMngFieldDetailDTO.tableName"/>
<html:hidden property="maPgMngFieldDetailDTO.tableId"/>
<html:hidden property="maPgMngFieldDetailDTO.columnName"/>

<html:hidden property="maPgMngFieldDetailDTO.groupKeyNo"/>
<html:hidden property="maPgMngFieldDetailDTO.groupKeyType"/>
<html:hidden property="maPgMngFieldDetailDTO.groupOption"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 필드id -->
               <div class="field">
	               <label class="check"><bean:message key="LABEL.fieldId"/></label>
               	   <div class="input_box">
               	   		<html:text property="maPgMngFieldDetailDTO.fieldId" tabindex="10" />
             	   </div>
           	   </div>
				<!-- 숨김여부 -->
				<div class="field">
					<label><bean:message key="LABEL.hiddenYn"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.hiddenYn" tabindex="20"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 화면순서 -->
				<div class="field">
	               <label><bean:message key="LABEL.ordNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="maPgMngFieldDetailDTO.ordNo" tabindex="30" />
               	   </div>
				</div>
				<!-- 화면Display여부 -->
				<div class="field">
					<label><bean:message key="LABEL.displayYn"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.displayYn" tabindex="40"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				
				<!-- Label 명 -->
				<div class="field">
					<label><bean:message key="LABEL.labelName"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.keyName" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- check YN -->
				<div class="field">
					<label><bean:message key="LABEL.checkYn"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.checkYnDesc" tabindex="60"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 필드 옵션 -->
				<div class="field">
					<label><bean:message key="LABEL.fieldOption"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.fieldOptionDesc" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Read Only -->
				<div class="field">
					<label><bean:message key="LABEL.readonlyYn"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.readonlyYnDesc" tabindex="80"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- Len. Restict (길이제한) -->
				<div class="field">
					<label><bean:message key="LABEL.strLength"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.strLength" tabindex="90" styleClass="num"/>
					</div>
				</div>
								
				<!-- 화면입력 타입 -->
				<div class="field">
					<label><bean:message key="LABEL.formInputType"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.formInputTypeDesc" tabindex="100"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 화면입력 세부타입 -->
				<div class="field">
					<label><bean:message key="LABEL.formInputDetailType"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.formInputDetailTypeDesc" tabindex="110"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				<!-- 코드유형명 -->
				<div class="field">
					<label><bean:message key="LABEL.codeDesc"/></label>
					<div class="input_box">
							<html:text property="maPgMngFieldDetailDTO.codeListTypeDesc" tabindex="120"/>
							<p class="open_spop">
								<a>
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				
				<!-- Group Label 명 -->
				<div class="field">
					<label><bean:message key="LABEL.groupkeyName"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.groupKeyName" tabindex="122" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Group 옵션 -->
				<div class="field">
					<label><bean:message key="LABEL.groupOption"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.groupOptionDesc" tabindex="124" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 컬럼 -->
				<div class="field">
					<label><bean:message key="LABEL.columnName"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.columnDesc" tabindex="125" onchange="goColumn();" />
						<p class="open_spop">
							<a href="javascript:lovRefColumn('maPgMngFieldDetailDTO.tableName', 'maPgMngFieldDetailDTO.columnName','maPgMngFieldDetailDTO.tableId','');">
							<!-- <a> -->
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				  <!-- 테이블 -->
				<div class="field">
					<label><bean:message key="LABEL.tableDesc"/></label>
					<div class="input_read">
						<html:text property="maPgMngFieldDetailDTO.tableDesc" readonly="true"/>
					</div>
				</div>
				
				<!-- Validation 체크 스크립트 -->
				<div class="field_long">
					<label><bean:message key="LABEL.validationJscript"/></label>
					<div class="input_box">
						<html:text property="maPgMngFieldDetailDTO.validationJscript" tabindex="130" />
					</div>
				</div>
				
				<!-- 항목설명 -->
                <div class="field_long">
                   <label><bean:message key="LABEL.fieldDesc"/></label>
                   <div class="input_box">
                        <html:textarea property="maPgMngFieldDetailDTO.fieldDesc" styleClass="ta50" tabindex="140" />
                   </div>
                </div>
                
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>