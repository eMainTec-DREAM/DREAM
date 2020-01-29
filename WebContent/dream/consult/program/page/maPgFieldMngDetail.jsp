<%--===========================================================================
화면입력항목 상세 
author  youngjoo38
version $Id:$
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
<title><bean:message key="TAB.maPgFieldMngDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var fileNameAc;
var gLabelAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPgMngFieldDetailDTO.fieldId");
	setForUpdate();

    // 화면명
    fileNameAc = new autoC({"maPgMngFieldDetailDTO.pageDesc":"description"});
    fileNameAc.setTable("TAPAGE");
    fileNameAc.setKeyName("maPgMngCommonDTO.pageId");
    fileNameAc.setAcResultMap({
        "maPgMngCommonDTO.pageId":"page_id"
      , "maPgMngCommonDTO.filterFileName":"file_name"
    });
    fileNameAc.init();  
    
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
	
	acSysDesc("maPgMngFieldDetailDTO.fieldOptionDesc","maPgMngFieldDetailDTO.fieldOption","FIELD_OPTION");
	
	acSysDesc("maPgMngFieldDetailDTO.checkYnDesc","maPgMngFieldDetailDTO.checkYn","CHECK_YN", true);
	acSysDesc("maPgMngFieldDetailDTO.readonlyYnDesc","maPgMngFieldDetailDTO.readonlyYn","READONLY_YN", true);
	acSysDesc("maPgMngFieldDetailDTO.displayYnDesc","maPgMngFieldDetailDTO.displayYn","IS_USE", true);
	acSysDesc("maPgMngFieldDetailDTO.hiddenYnDesc","maPgMngFieldDetailDTO.hiddenYn","IS_USE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPGFIELD_ID');
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.hiddenYn'].value = 'N';
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.hiddenYnDesc'].value = 'N';
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.displayYn'].value = 'Y';
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.displayYnDesc'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.pgFieldId'].value = sequenceVal;
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPgMngFieldDetailForm.strutsAction.value = '<%=MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INPUT%>';
	else maPgMngFieldDetailForm.strutsAction.value = '<%= MaPgMngFieldDetailAction.PG_FIELD_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPgFieldMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPgMngFieldDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maPgMngFieldDetailForm.elements['maPgMngCommonDTO.pageId'].value = "";
    if (parent.findGridRow)	parent.findGridRow(maPgMngFieldDetailForm.elements['maPgMngFieldDetailDTO.pgFieldId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
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
	
	if(pageName == "lovRefColumnPopup")
	{
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
<html:form action="/maPgFieldMngDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/>
<html:hidden property="maPgMngFieldDetailDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.keyNo"/>
<html:hidden property="maPgMngFieldDetailDTO.keyType"/>

<html:hidden property="maPgMngFieldDetailDTO.tableName"/>
<html:hidden property="maPgMngFieldDetailDTO.tableId"/>
<html:hidden property="maPgMngFieldDetailDTO.columnName"/>

<html:hidden property="maPgMngFieldDetailDTO.groupKeyNo"/>
<html:hidden property="maPgMngFieldDetailDTO.groupKeyType"/>
<html:hidden property="maPgMngFieldDetailDTO.groupOption"/>
<html:hidden property="maPgMngFieldDetailDTO.checkYn"/>
<html:hidden property="maPgMngFieldDetailDTO.readonlyYn"/>
<html:hidden property="maPgMngFieldDetailDTO.displayYn"/>
<html:hidden property="maPgMngFieldDetailDTO.hiddenYn"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 화면명 -->
               <div class="field">
	               <label><bean:message key="LABEL.pageName"/></label>
               	   <div class="input_box">
               	   		<html:text property="maPgMngFieldDetailDTO.pageDesc" tabindex="5" />
               	   		<p class="open_spop"><a><span>조회</span></a></p>    
             	   </div>
           	   </div>
           	   <div class="field"></div>
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
							<html:text property="maPgMngFieldDetailDTO.hiddenYnDesc" tabindex="20"/>
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
							<html:text property="maPgMngFieldDetailDTO.displayYnDesc" tabindex="40"/>
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
						<html:text property="maPgMngFieldDetailDTO.keyName" tabindex="50" 
							onblur="valLang('maPgMngFieldDetailDTO.keyType','maPgMngFieldDetailDTO.keyNo','maPgMngFieldDetailDTO.keyName', true);"/>
						<p class="open_spop"><a href="javascript:lovLang('maPgMngFieldDetailDTO.keyType','maPgMngFieldDetailDTO.keyNo', 'maPgMngFieldDetailDTO.keyName');"><span>조회</span></a></p>
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
				
				<!-- 항목설명 -->
                <div class="field_long">
                   <label><bean:message key="LABEL.fieldDesc"/></label>
                   <div class="input_box">
                        <html:textarea property="maPgMngFieldDetailDTO.fieldDesc" styleClass="ta50" tabindex="90" />
                   </div>
                </div>
                
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>