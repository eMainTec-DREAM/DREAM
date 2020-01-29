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
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="dream.work.list.action.WorkListCinsPlanMstrDetailAction" %>
<html:html>
<head>
<!-- 파트체인지 점검계획 상세 -->
<title><bean:message key='LABEL.woNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var empAc, equipAc, closeByAc, productAc;
var productId = "";
	
function loadPage() 
{
	//생산제품 자동완성
    productAc = new autoC({"workListCinsPlanMstrDetailDTO.productDesc":"productDesc"});
    productAc.setAcConditionMap({
        "comp_no":loginUser.compNo
      , "is_use": "Y"
   	  , "pm_type" : "CINS"
    });
    productAc.setTable("TAPMEQUIP");
    productAc.setKeyName("workListCinsPlanMstrDetailDTO.productId");
    productAc.setAcResultMap({
         "workListCinsPlanMstrDetailDTO.pmId":"pmId"
      ,  "workListCinsPlanMstrDetailDTO.productId":"productId"
      , "workListCinsPlanMstrDetailDTO.pmEquipId":"pmEquipId"
    });
    productAc.init();
    
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("workListCinsPlanMstrDetailDTO.planDate");
    //For Save
    setForUpdate();
    
    if(workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.isActive'].value=="Y")
    {
        $('.b_exeins').hide(); 
        setDisableAll();
    }
    else
        setEnableAll();
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQAPMINSDSCHED_ID');
    
    productAc.openLov();
    
}

function setSequenceVal(sequenceVal)
{
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmInsDSchedId'].value = sequenceVal;
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = sequenceVal;
    
    //workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.wkorDate'].value = getToday();
}


/**
 * 수정
 */
function goUpdate()
{
	if(workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.isActive'].value=="Y")
    {
        $('.b_exeins').hide(); 
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
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) workListCinsPlanMstrDetailForm.strutsAction.value = "<%=WorkListCinsPlanMstrDetailAction.DETAIL_INPUT%>";
    else workListCinsPlanMstrDetailForm.strutsAction.value = "<%=WorkListCinsPlanMstrDetailAction.DETAIL_UPDATE%>";
    
    var actionUrl = contextPath + "/workListCinsPlanMstrDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workListCinsPlanMstrDetailForm),'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow)
        parent.findGridRow(workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmInsDSchedId'].value);

    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmInsDSchedId'].value;
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrCommonDTO.pmId'].value = "";
    
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workListCinsPlanMstrDetailDTO.pmInsDListNo");
    
    //저장후 완료버튼 보이기
    $(".b_confirm").css("display","");

}

// 점검일정 발행 전, 값 세팅.
function putList()
{
    //교대조 세팅
    if(loginUser.shiftType!='null'){
        workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.shiftTypeId'].value = loginUser.shiftType;
    }
    //담당자 자동완성 (로그인 유저)
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.empId'].value = loginUser.empId;
    
    //담당부서 자동완성 (로그인 유저)
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.deptId'].value = loginUser.deptId;
    
    //작업시작일자, 종료일자 넣기.
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.startDate'].value   = getToday();
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.endDate'].value   = getToday();
    
    //작업시작시간(1시간전), 종료시간(현재시간) 넣기.
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.startTime'].value   = getMinusTime(false,1);
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.endTime'].value   = getTime(false);
    
    //작업그룹
/*     if(loginUser.wkctrId!='null')
    {
        workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.wkCtrId'].value     = loginUser.wkctrId;
    }	 */
    if(loginUser.filterWkCtrId!='null')
    {
        workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.wkCtrId'].value = loginUser.filterWkCtrId;
    }	
    //작업시간
    workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.wkorDate'].value = getToday();

}

/**
 * 점검일정발행
 */
 function goExeins(){
    if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0190'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0188'/>", function(result){
        	
        	// 각종 값들 넣기
        	putList();
        	
            if(result){
               //================================
               // 필수 항목 체크한다.
               //================================
               if(checkValidation()) return;
               //================================
               // 필수 확정항목 체크한다.
               //================================
               //if(checkConfirmValidation()) return;
               //if(checkRequireValue('workListCinsPlanMstrDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
               
               //checkPoint();
               
               workListCinsPlanMstrDetailForm.strutsAction.value = '<%=WorkListCinsPlanMstrDetailAction.WORK_PMI_DETAIL_COMPLETE%>';
               var actionUrl = contextPath + "/workListCinsPlanMstrDetail.do";
               XMLHttpPost(actionUrl, FormQueryString(workListCinsPlanMstrDetailForm), 'afterExeins');
            }
       });
    }
}
 /**
  * 점검일정발행 후 호출
  */
function afterExeins(ajaxXmlDoc)
{
   alertMessage1("<bean:message key='MESSAGE.MSG0189'/>");

   // 점검일정발행 후 findGridRow
   if (parent.findGridRow) parent.findGridRow(workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmInsDSchedId'].value);
   
   if(workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.isActive'].value=="Y")
   {
       $('.b_exeins').hide(); 
   }
}

function goOpen(pageId)
{
    goTabPage(pageId);
}

function goTabPage(pageId) 
{
    var form = document.workListCinsPlanMstrDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmInsDSchedId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기
 */
function goPmstdLink()
{
	var fileName = workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.param'].value;
	var pmId     = workListCinsPlanMstrDetailForm.elements['workListCinsPlanMstrDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workListCinsPlanMstrDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="workListCinsPlanMstrCommonDTO.pmInsDSchedId"/>  <!-- Key -->
        <html:hidden property="workListCinsPlanMstrDetailDTO.pmInsDSchedId"/>  <!-- Key -->
		<html:hidden property="workListCinsPlanMstrCommonDTO.pmId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.pmId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.wkOrId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.deptId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.equipId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.empId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.eqLocId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.eqCtgId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.woTypeId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.pmTypeId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.shiftTypeId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.wkCtrId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.isActive"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.param"/>
		
		<html:hidden property="workListCinsPlanMstrDetailDTO.startDate"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.startTime"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.endDate"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.endTime"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.listStatusId"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.wkorDate"/>
		<html:hidden property="workListCinsPlanMstrDetailDTO.pmEquipId"/>
        
		<html:hidden property="workListCinsPlanMstrDetailDTO.productId"/>
        <div class="article_box">
            <div class="form_box">
            
            <!-- 계획일자 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.planDate"/></label>
                <div class="input_box">
                    <html:text property="workListCinsPlanMstrDetailDTO.planDate" tabindex="10" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            <!-- 생산제품 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.prodGoods"/></label>
                <div class="input_box">
                    <html:text property="workListCinsPlanMstrDetailDTO.productDesc" tabindex="20" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            <div class="field_long_blank"></div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
