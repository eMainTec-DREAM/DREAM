<%--===========================================================================
업로드데이타 - 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataAction"%>
<html:html>
<head>
<!-- 업로드데이타 -->
<title><bean:message key='LABEL.docCountrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">// 저장 시 수행되는 action

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	if(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value=="W")
    {
        $('.b_confirm').show(); 
        $('.b_drop').hide(); 
    }
	else
	{
        $('.b_confirm').hide(); 
        $('.b_drop').show(); 
	}
	
    // 시스템 관리자설정여부
    acSysDesc("consultProgramUploadDataDTO.isUseConfig","consultProgramUploadDataDTO.isUseConfig","IS_USE", true);
    // 사용여부
    acSysDesc("consultProgramUploadDataDTO.isUse","consultProgramUploadDataDTO.isUse","IS_USE", true);
	
	setTitle("consultProgramUploadDataDTO.excelTabNo");
	
	setForUpdate();
	
}

function goInput()
{
	sequenceNextVal('SQAEXCELTAB_ID');
	
	// Config Setting 사용 'N' 세팅
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.isUseConfig'].value = "N";
	// 사용여부 'Y' 세팅
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.isUse'].value = "Y";

	// 상태 '작성중' 세팅
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value = "W";
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatus'].value = "작성중";
    valSysDir('consultProgramUploadDataDTO.excelListStatusId', 'consultProgramUploadDataDTO.excelListStatus', 'EXCELLIST_STATUS', true);
	
}

function goUpdate()
{

}

function setSequenceVal(sequenceVal)
{
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultProgramUploadDataForm.strutsAction.value = '<%=ConsultProgramUploadDataAction.DETAIL_INPUT%>';
	else consultProgramUploadDataForm.strutsAction.value = '<%=ConsultProgramUploadDataAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/consultProgramUploadDataDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultProgramUploadDataForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value);
	
	var tableName = consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.tableName'].value.toUpperCase();
	
	if("TY"!= tableName.substring(0,2))
	{
		consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.tableName'].value = "TY" + tableName;	
	}
	
	getTopPage().afterSaveAll(currentPageId);
 }
 
function goTabPage(pageId) 
{
    var form = document.consultProgramUploadDataForm;
    goCommonTabPage(form, '' , pageId);
       
}


/*
 * 확정하기 (테이블생성)
 */
function goConfirm()
{
    if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }else{
    	 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
             if(result){
                //================================
                // 필수 항목 체크한다.
                //================================
                if(checkValidation()) return;
                //================================
                // 필수 확정항목 체크한다.
                //================================
                if(checkConfirmValidation()) return;
                
                ckeckTableExist();
                   }
             });
  	 }
}

/**
 * 완료후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
   alertMessage1("<bean:message key='MESSAGE.MSG034'/>");
      
   // 상태 = C - 확정
   consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value = "C";
   valSysDirCode('consultProgramUploadDataDTO.excelListStatusId', 'consultProgramUploadDataDTO.excelListStatus', 'EXCELLIST_STATUS', true);
    
   if (parent.findGridRow) parent.findGridRow(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value);

   if(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value=="C")
   {
       $('.b_confirm').hide(); 
       $('.b_drop').show(); 
   }
   goClose('consultProgramUploadDataDetail');
}

/*
 * 테이블 존재여부 확인
 */
var isValid = 0;
function ckeckTableExist()
{
    
    var actionUrl = contextPath + "/consultProgramUploadDataDetail.do";
    var param =  "&strutsAction=" + '<%= ConsultProgramUploadDataAction.DETAIL_CHECK_TABLE_EXIST %>'
              +  "&" + FormQueryString(consultProgramUploadDataForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckTableExist');
}

function setValidCheckTableExist(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid == '0')
    {
    	consultProgramUploadDataForm.strutsAction.value = '<%=ConsultProgramUploadDataAction.DETAIL_COMPLETE%>';
        var actionUrl = contextPath + "/consultProgramUploadDataDetail.do";
           XMLHttpPost(actionUrl, FormQueryString(consultProgramUploadDataForm), 'afterConfirm');
           
    }else{
        alertMessage1("<bean:message key='MESSAGE.MSG0199' />");
        return;
    }
    
}
  
/*
 * 취소하기 (테이블삭제)
 */
function goDrop()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }else{
         getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0200'/>", function(result){
                                               // 테이블 생성을 취소하시겠습니까?
             if(result){
                //================================
                // 필수 항목 체크한다.
                //================================
                if(checkValidation()) return;
                //================================
                // 필수 확정항목 체크한다.
                //================================
                if(checkConfirmValidation()) return;
                
                consultProgramUploadDataForm.strutsAction.value = '<%=ConsultProgramUploadDataAction.DETAIL_DROP_TABLE%>';
                var actionUrl = contextPath + "/consultProgramUploadDataDetail.do";
                   XMLHttpPost(actionUrl, FormQueryString(consultProgramUploadDataForm), 'afterDrop');
                   
                   }
             });
     }
}

/**
 * 취소 후 호출
 */
function afterDrop(ajaxXmlDoc)
{
   alertMessage1("<bean:message key='MESSAGE.MSG034'/>");
      
   // 상태 = W - 작성중
   consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value = "W";
   valSysDirCode('consultProgramUploadDataDTO.excelListStatusId', 'consultProgramUploadDataDTO.excelListStatus', 'EXCELLIST_STATUS', true);
    
   if (parent.findGridRow) parent.findGridRow(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value);

   if(consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelListStatusId'].value=="W")
   {
       $('.b_confirm').show(); 
       $('.b_drop').hide(); 
   }
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultProgramUploadDataDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultProgramUploadDataDTO.excelTabId"/><!-- Key -->
	<html:hidden property="consultProgramUploadDataDTO.excelListStatusId"/>
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <!-- Excel 유형 No -->
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.excelTypeNo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.excelTabNo" tabindex="10" />
             	 	</div>
             	 </div>
             	 <!-- 상태 -->
             	 <div class="field">
             	 	<label><bean:message key="LABEL.status"/></label>
             	 	<div class="input_read">
             	 		<html:text property="consultProgramUploadDataDTO.excelListStatus" readonly="true" />
             	 	</div>
             	 </div>
				<!-- Excel 유형 -->
				<div class="field">
					<label><bean:message key="LABEL.excelType"/></label>
					<div class="input_box">
						<html:text property="consultProgramUploadDataDTO.excelTabName" tabindex="20" />
					</div>
				</div>
				<!-- 테이블명 -->
				<div class="field">
             	 	<label><bean:message key="LABEL.tableDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.tableName" tabindex="30" />
             	 	</div>
             	 </div>
				<!-- Config Setting 사용 -->
				<div class="field">
             	 	<label><bean:message key="LABEL.isUseConfig"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.isUseConfig" tabindex="45" />
             	 		<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
				<!-- 사용여부 -->
				<div class="field">
             	 	<label><bean:message key="LABEL.isUse"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.isUse" tabindex="40" />
             	 		<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
				<!-- 프로그램명 -->
				<div class="field">
             	 	<label><bean:message key="LABEL.programName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.uploadPgmName" tabindex="50" />
             	 	</div>
             	 </div>
				<!-- Sheet명 -->
				<div class="field">
             	 	<label><bean:message key="LABEL.sheetName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramUploadDataDTO.sheetName" tabindex="60" />
             	 	</div>
             	 </div>
             	 <!-- 비고 -->
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.remark"/></label>
             	 	<div class="input_box">
                        <html:textarea property="consultProgramUploadDataDTO.remark" styleClass="ta50" tabindex="70" />
                    </div>
             	 </div>
			
			
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         
	</html:form>
</body>
</html:html>