<%--===========================================================================
고장분류 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.fail.code.action.MaFailureDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 고장분류 -->
<title><bean:message key='LABEL.failureNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//

var failNameAc;
var acSysDescAc;
function loadPage() 
{
    maFailureDetailForm.elements['maFailureDetailDTO.keyTypeStr'].value ="FAILCODE";
    setTitle("maFailureDetailDTO.failureNo", "maFailureDetailDTO.failureDesc");
    
    setForUpdate();
    
    /**분류명  */
    /*
    failNameAc = new autoC({"maFailureDetailDTO.failName":"key_name"});
    failNameAc.setAcConditionMap({
            "key_type":"FAILCODE"
    });
    failNameAc.setKeyName("maFailureDetailDTO.keyNo");
    failNameAc.setTable("TALANG");
    failNameAc.setAcResultMap({
        "maFailureDetailDTO.keyNo":"key_no",
        "maFailureDetailDTO.keyType":"key_type",
        "maFailureDetailDTO.failName":"key_name"
    });
    failNameAc.init();
    */
    
    /** 고장구분  */
    acSysDescAc = acSysDesc("maFailureDetailDTO.failTypeDesc","maFailureDetailDTO.failType","FAILURE_TYPE", true);
    
	/** 사용여부 */
    acSysDesc("maFailureDetailDTO.isUse","maFailureDetailDTO.isUse","IS_USE", true);
    
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAFAILURE_ID');
    
    maFailureDetailForm.elements['maFailureDetailDTO.isUse'].value = 'Y';
    acSysDescAc.openLov();
}



/**
 * 수정
 */
function goUpdate()
{
    //수정시 등록유형과 등록id readonly설정 
    maFailureDetailForm.elements['maFailureDetailDTO.failureNo'].readOnly = true;
    document.getElementById("failureNoDiv").className = "input_read";
}

function setSequenceVal(sequenceVal)
{
	maFailureDetailForm.elements['maFailureDetailDTO.failureId'].value = sequenceVal;
	maFailureDetailForm.elements['maFailureDetailDTO.failureNo'].value = sequenceVal;
	maFailureDetailForm.elements['maFailureCommonDTO.failureId'].value = sequenceVal;
}

/**
 * failure No 세팅
 */
 function setNoVal(nextNo){
	 maFailureDetailForm.elements['maFailureDetailDTO.failureNo'].value = nextNo;
}

/**
 * failure no 중복 체크
 */
function valFailureNo()
{
    isValid = 0;
    if(maFailureDetailForm.strutsAction.value == '0')
    {
        var actionUrl = contextPath + "/maFailureDetail.do";
        var param =  "&strutsAction=" + '<%= MaFailureDetailAction.FAILURE_DETAIL_CHECK %>'
                  +  "&" + FormQueryString(maFailureDetailForm);
        XMLHttpPostVal(actionUrl, param, 'setValidFailureNo');
    }
}

/**
 * valFailureNo()실행 후 호출
 */
var isValid = 0;
function setValidFailureNo(ajaxXmlDoc)
{
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
        maFailureDetailForm.elements['maFailureDetailDTO.failureNo'].value = '';
        maFailureDetailForm.elements['maFailureDetailDTO.failureNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
        maFailureDetailForm.strutsAction.value ='';
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
    
    maFailureDetailForm.elements['maFailureDetailDTO.failureId'].value = maFailureDetailForm.elements['maFailureCommonDTO.failureId'].value;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maFailureDetailForm.strutsAction.value = "<%=MaFailureDetailAction.FAILURE_DETAIL_INPUT%>";
    else maFailureDetailForm.strutsAction.value = '<%=MaFailureDetailAction.FAILURE_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maFailureDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maFailureDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     //maFailureDetailForm.elements['maFailureCommonDTO.failureId'].value = maFailureDetailForm.elements['maFailureDetailDTO.failureId'].value;

	if (parent.findGridRow) parent.findGridRow(maFailureDetailForm.elements['maFailureDetailDTO.failureId'].value);
	parent.goSearch();
    getTopPage().afterSaveAll(currentPageId);
     
    setTitle("maFailureDetailDTO.failureNo", "maFailureDetailDTO.failureDesc");
    //goUpdate();
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maFailureDetailForm.elements['maFailureDetailDTO.failureId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/maFailureDetail" >
    <html:hidden property="strutsAction"/>
    <html:hidden property="currentPageId"/>
    <html:hidden property="maFailureCommonDTO.compNo" />
    <html:hidden property="maFailureCommonDTO.failureId" />
    <html:hidden property="maFailureDetailDTO.failureId" />
    <html:hidden property="maFailureDetailDTO.failType" />
	<html:hidden property="maFailureDetailDTO.keyType"/><!-- 다국어 key_type -->
	<html:hidden property="maFailureDetailDTO.keyNo"/><!-- 다국어 keyNo -->
	<html:hidden property="maFailureDetailDTO.keyTypeStr"/>
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        
             <div class="field">
             <!--고장분류  -->
                <label class="check"><bean:message key="LABEL.failType"/></label>
                <div class="input_box">
                    <html:text property="maFailureDetailDTO.failTypeDesc" tabindex="10"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <!-- 분류코드 -->
             <div class="field">
                <label class="check"><bean:message key="LABEL.failureNo"/></label>
                <div id="failureNoDiv" class="input_box">
                    <html:text property="maFailureDetailDTO.failureNo" onblur="valFailureNo();" tabindex="20"/>
                </div>
             </div>
             <!-- 분류명 -->
			<div class="field">
            	<label class="check"><bean:message key="LABEL.failureDesc"/></label>
				<div class="input_box">
					<html:text property="maFailureDetailDTO.failName" tabindex="35" />
				</div>
			</div>
			<!--사용여부  -->
             <div class="field">
                <label><bean:message key="LABEL.isUse"/></label>
                <div class="input_box">
                    <html:text property="maFailureDetailDTO.isUse" tabindex="40"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <div class="field">
                <label><bean:message key="LABEL.ordNo"/></label>
                <div class="input_box">
                    <html:text property="maFailureDetailDTO.ordNo" tabindex="50" />
                </div>
             </div>
             <div class="field_long">
                <label><bean:message key="LABEL.failureRemark"/></label>
                <div class="input_box">
                    <html:textarea property="maFailureDetailDTO.remark" styleClass="ta50" tabindex="60"/>
                </div>
             </div>
             
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>