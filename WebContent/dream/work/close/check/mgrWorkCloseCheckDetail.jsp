<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="dream.work.close.check.action.MgrWorkCloseCheckDetailAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>

<html:html>
<head>
<!-- 작업완료 점검항목 설정 상세 -->
<title><bean:message key='MENU.AR121' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var plantAc
var pmTypeAc;
var isPmTypeLoad = false;   // true면 distroy 후 재로딩

function loadPage() 
{	
    setTitle("mgrWorkCloseCheckDetailDTO.stwrkNo", "mgrWorkCloseCheckDetailDTO.stwrkDesc");
    
    //For Save
    setForUpdate();

    setBtnStatus();
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    // 공장명
    plantAc = new autoC({"mgrWorkCloseCheckDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "mgrWorkCloseCheckDetailDTO.plant":"plant"
    });
    plantAc.setKeyName("mgrWorkCloseCheckDetailDTO.plant");
    plantAc.init();
	
    // 작업종류
    acSysDesc("mgrWorkCloseCheckDetailDTO.woTypeDesc","mgrWorkCloseCheckDetailDTO.woType","WO_TYPE", true);
    // 시행여부
    acSysDesc("mgrWorkCloseCheckDetailDTO.isActiveDesc","mgrWorkCloseCheckDetailDTO.isActive","IS_USE", true);
    
 	// 제/개정 화면제어
 	revDisplayCtrl(M$('mgrWorkCloseCheckDetailDTO.revisionStatusId').value,M$('mgrWorkCloseCheckDetailDTO.isLastVersion').value,"PM");

}

/*
 * Show/Hide Button
 */
function setBtnStatus()
{
	// 적용 상태 - C
	if(mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkStatus'].value=="C")
	{
		setDisableAll();
	}
	else
	{
		setEnableAll();
	}
}

function afterAutoCmpt(code)
{
	if(code=="mgrWorkCloseCheckDetailDTO.woTypeDesc")
    {
		var listType = mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.woType'].value+"_TYPE";
		
		// 선택했던 작업형태 초기화
	     mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.pmTypeDesc'].value = "";
	     mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.pmType'].value = "";
	     
        setPmTypeAc(listType);
        isPmTypeLoad = true;
    }
}


//작업형태 AC
function setPmTypeAc(listType)
{
	if(isPmTypeLoad)
	 {
	    // 작업종류를 재선택한 경우 distroy 후 로드한다.
     	pmTypeAc.destroy();
	     
	    isPmTypeLoad = false;
	 }
		
	 // 작업형태
	 pmTypeAc = new autoC({"mgrWorkCloseCheckDetailDTO.pmTypeDesc":"description"});
	 pmTypeAc.setAcConditionMap({
	         "list_type":listType,
	         "is_use":"Y"
	    });
	 pmTypeAc.setTable("TACDSYSD");
	 pmTypeAc.setKeyName("mgrWorkCloseCheckDetailDTO.pmType");
	 pmTypeAc.setAcResultMap({
	     "mgrWorkCloseCheckDetailDTO.pmType":"cdsysd_no"
	 });
	 pmTypeAc.init();
}


function goTabPage(pageId)
{
	var form = document.mgrWorkCloseCheckDetailForm;
	if(pageId == "mgrWorkCloseCheckDocLibList")
	{	
		mgrWorkCloseCheckDetailForm.elements['maDocLibCommonDTO.objectId'].value = mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkId'].value;
		mgrWorkCloseCheckDetailForm.elements['maDocLibCommonDTO.objectType'].value = "STDPOINT";  //EQMSTR docDesc
		mgrWorkCloseCheckDetailForm.elements['maDocLibCommonDTO.description'].value = mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}


/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQASTWRK_ID');

    //상태
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkStatus'].value = "W";
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkStatusDesc'].value = "W";
    valSysDir('mgrWorkCloseCheckDetailDTO.stwrkStatus', 'mgrWorkCloseCheckDetailDTO.stwrkStatusDesc', 'STWRK_STATUS', true);
    
 	//공장명
    if(loginUser.plant!='' && loginUser.plant!= null){
    	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.plant'].value  = loginUser.plant;
    	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.plantDesc'].value  = loginUser.plantDesc;
	} 
    
 	//등록부서
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.regDept'].value = loginUser.deptId;
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.regDeptDesc'].value   = loginUser.deptDesc;
 	
 	//등록자
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.regBy'].value = loginUser.empId;
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.regByDesc'].value   = loginUser.empName;

	//등록일자
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.regDate'].value = getToday();
	
 	//사용여부 - Y
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.isActiveDesc'].value = "Y";
	valSysDir('mgrWorkCloseCheckDetailDTO.isActive', 'mgrWorkCloseCheckDetailDTO.isActiveDesc', 'IS_USE', true);
	
 	//최신버전여부 - N
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.isLastVersion'].value = "N";
	valSysDir('mgrWorkCloseCheckDetailDTO.isLastVersion', 'mgrWorkCloseCheckDetailDTO.isLastVersion', 'IS_USE', true);

}

function setSequenceVal(sequenceVal)
{
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = sequenceVal;
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkId'].value = sequenceVal;
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkNo'].value = sequenceVal;
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
	//수정일자
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.updDate'].value = getNowDateTime();
	//수정자
    mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.updBy'].value = loginUser.empId;
	
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    if(ckCreate(currentPageId)) mgrWorkCloseCheckDetailForm.strutsAction.value = "<%=MgrWorkCloseCheckDetailAction.DETAIL_INPUT%>";
    else 
	mgrWorkCloseCheckDetailForm.strutsAction.value = "<%=MgrWorkCloseCheckDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/mgrWorkCloseCheckDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(mgrWorkCloseCheckDetailForm),'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)
        parent.findGridRow(mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkId'].value);

    getTopPage().afterSaveAll(currentPageId);

    setTitle("mgrWorkCloseCheckDetailDTO.stwrkNo", "mgrWorkCloseCheckDetailDTO.stwrkDesc");
    
}


/**
 * 적용
 */
function goConfirm(){
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0037'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				
				 mgrWorkCloseCheckDetailForm.strutsAction.value = '<%=MgrWorkCloseCheckDetailAction.DETAIL_CONFIRM%>';
				 var actionUrl = contextPath + "/mgrWorkCloseCheckDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(mgrWorkCloseCheckDetailForm), 'afterConfirm');
			 }
			});
	 }
}
/**
 * 적용 후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	alertMessage1("<bean:message key='MESSAGE.MSG0038'/>");
	
	//작성상태 = C - 적용
	mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkStatus'].value = "C";
	valSysDirCode('mgrWorkCloseCheckDetailDTO.stwrkStatus', 'mgrWorkCloseCheckDetailDTO.stwrkStatusDesc', 'STWRK_STATUS', true);
	
	setBtnStatus();
	
	 if (parent.findGridRow)	parent.findGridRow(mgrWorkCloseCheckDetailForm.elements['mgrWorkCloseCheckDetailDTO.stwrkId'].value);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/mgrWorkCloseCheckDetail">
    <html:hidden property="strutsAction" />
    <html:hidden property="currentPageId"/>
    <html:hidden property="mgrWorkCloseCheckCommonDTO.stwrkId" /><!-- Key -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.stwrkId" /><!-- Key -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.stwrkWorkId" />
    <html:hidden property="mgrWorkCloseCheckDetailDTO.stwrkStatus" /><!-- 상태 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.woType" /><!-- 작업종류 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.pmType" /><!-- 작업형태 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.plant" /><!-- 공장 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.isActive" /><!-- 시행여부 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.regDept" /><!-- 등록부서 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.regBy" /><!-- 등록자 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.updBy" /><!-- 수정자 ID -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.updDate" /><!-- 수정일자 -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.isLastVersion" /><!-- 최신버전여부 -->
    <html:hidden property="mgrWorkCloseCheckDetailDTO.revisionStatusId" /><!-- 제개정 상태 -->
    
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
    <div class="article_box">
        <div class="form_box">
            <!-- 점검# -->
            <div class="field">
                <label>점검#</label>
                <div class="input_read">
                    <html:text property="mgrWorkCloseCheckDetailDTO.stwrkNo" tabindex="10" readonly="true"/>
                </div>
            </div>
            <!-- 상태 -->
            <div class="field">
                <label>상태</label>
                <div class="input_read">
                    <html:text property="mgrWorkCloseCheckDetailDTO.stwrkStatusDesc" tabindex="20" readonly="true"/>
                </div>
            </div>
            <!-- 제목 -->
            <div class="field_long">
                <label>제목</label>
                <div class="input_box">
                    <html:text property="mgrWorkCloseCheckDetailDTO.stwrkDesc" tabindex="30"/>
                </div>
            </div>
            <!-- 작업종류 -->
            <div class="field">
                <label>작업종류</label>
                <div class="input_box">
                    <html:text property="mgrWorkCloseCheckDetailDTO.woTypeDesc" tabindex="40"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 작업형태 -->
            <div class="field">
                <label>작업형태</label>
                <div class="input_box">
                    <html:text property="mgrWorkCloseCheckDetailDTO.pmTypeDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 공장 -->
            <div class="field">
                <label>공장</label>
                <div class="input_box">
                    <html:text property="mgrWorkCloseCheckDetailDTO.plantDesc" tabindex="60"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 시행여부 -->
            <div class="field">
                <label>시행여부</label>
                <div class="input_box">
                    <html:text property="mgrWorkCloseCheckDetailDTO.isActiveDesc" tabindex="70"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 등록부서 -->
            <div class="field">
                <label>등록부서</label>
                <div class="input_read">
                    <html:text property="mgrWorkCloseCheckDetailDTO.regDeptDesc" tabindex="80" readonly="true"/>
                </div>
            </div>
            <!-- 등록자 -->
            <div class="field">
                <label>등록자</label>
                <div class="input_read">
                    <html:text property="mgrWorkCloseCheckDetailDTO.regByDesc" tabindex="90" readonly="true"/>
                </div>
            </div>
            <!-- 등록일자 -->
            <div class="field">
                <label>등록일자</label>
                <div class="input_read">
                    <html:text property="mgrWorkCloseCheckDetailDTO.regDate" tabindex="100" readonly="true"/>
                </div>
            </div>
            <!-- 비고 -->
            <div class="field_long">
                <label>비고</label>
                <div class="input_read">
                    <html:textarea property="mgrWorkCloseCheckDetailDTO.remark" tabindex="110" styleClass="ta50"/>
                </div>
            </div>
        </div>
        <!-- End of Form_box -->
    </div>
    <!-- End of Article_box -->
</html:form>
</body>
</html:html>
