<%--===========================================================================
System분석 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.system.action.RcmSysDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 사원 -->
<title><bean:message key='MENU.RCMSYSTEM' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

var taskMapAc;
var crityListAc;
var eqLocDescAc;
var rcmCategAc;
function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	//goUpdate();
//     	goTabPage("orgEmpCertList");
//     	goTabPage("orgEmpTrainList");
    }
    setTitle("rcmSysDetailDTO.rcmListNo", "rcmSysDetailDTO.description");
    
    setForUpdate();
    
    taskMapAc = new autoC({"rcmSysDetailDTO.pmTaskMapListDesc":"description"});
    taskMapAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    taskMapAc.setTable("TAPMTASKMAPLIST");
    taskMapAc.setAcResultMap({
        "rcmSysDetailDTO.pmTaskMapListId":"pmTaskMapList_Id",
    });
    taskMapAc.init();
    
    crityListAc = new autoC({"rcmSysDetailDTO.crityListDesc":"description"});
    crityListAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	});
    crityListAc.setTable("TACRITYLIST");
    crityListAc.setKeyName("rcmSysDetailDTO.crityListId");
    crityListAc.setAcResultMap({
        "rcmSysDetailDTO.crityListId":"critylist_id"
    });
    crityListAc.init();
	
	eqLocDescAc = new autoC({"rcmSysDetailDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("rcmSysDetailDTO.eqLocId");
    eqLocDescAc.setAcResultMap({
        "rcmSysDetailDTO.eqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    rcmCategAc = new autoC({"rcmSysDetailDTO.rcmCategDesc":"description"});
    rcmCategAc.setAcConditionMap({
        	"comp_no":loginUser.compNo
          , "dir_type":"RCM_CATEG"
  	   });
    rcmCategAc.setTable("TACDUSRD");
    rcmCategAc.setKeyName("rcmSysDetailDTO.rcmCateg");
    rcmCategAc.setAcResultMap({
        "rcmSysDetailDTO.rcmCateg":"cdusrd_no"
//      , "rcmSysDetailDTO.rcmCategDesc" : "description"
    });
    rcmCategAc.init();
	
    acSysDesc("rcmSysDetailDTO.rcmListStatusDesc","rcmSysDetailDTO.rcmListStatus","RCMLIST_STATUS");
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQARCMLIST_ID');
    
    rcmSysDetailForm.elements['rcmSysDetailDTO.rcmListStatusDesc'].value = 'W';
    valSysDir('rcmSysDetailDTO.rcmListStatus', 'rcmSysDetailDTO.rcmListStatusDesc', 'RCMLIST_STATUS', true);
    
    rcmSysDetailForm.elements['rcmSysDetailDTO.regDate'].value = getToday();
}

/**
 * 수정
 */
function goUpdate()
{
	
}

function setSequenceVal(sequenceVal)
{
    rcmSysDetailForm.elements['rcmSysDetailDTO.rcmListId'].value = sequenceVal;
    rcmSysDetailForm.elements['rcmSysCommonDTO.rcmListId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) rcmSysDetailForm.strutsAction.value = "<%=RcmSysDetailAction.RCM_DETAIL_INPUT%>";
    else rcmSysDetailForm.strutsAction.value = '<%=RcmSysDetailAction.RCM_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/rcmSysDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(rcmSysDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     rcmSysDetailForm.elements['rcmSysCommonDTO.rcmListId'].value = rcmSysDetailForm.elements['rcmSysDetailDTO.rcmListId'].value;

     //parent.goSearch();
     if (parent.findGridRow)parent.findGridRow(rcmSysDetailForm.elements['rcmSysCommonDTO.rcmListId'].value);
     
     setTitle("rcmSysDetailDTO.rcmListNo", "rcmSysDetailDTO.description");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 function goTabPage(pageId)
 {
 	var form = document.rcmSysDetailForm;
 	
 	if(pageId == "maDocLibList" || pageId == "rcmSysDocLibList")
	{
 		rcmSysDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmSysDetailForm.elements['rcmSysCommonDTO.rcmListId'].value;
 		rcmSysDetailForm.elements['maDocLibCommonDTO.objectType'].value = "RCMSYS";  //=docDesc
 		rcmSysDetailForm.elements['maDocLibCommonDTO.description'].value = rcmSysDetailForm.elements['rcmSysDetailDTO.description'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
 	else
 	{
 		goCommonTabPage(form, '' , pageId);	
 	}
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmSysDetailForm.elements['rcmSysDetailDTO.rcmListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/rcmSysDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmSysCommonDTO.compNo" />
	<html:hidden property="rcmSysCommonDTO.rcmListId" />
	<html:hidden property="rcmSysDetailDTO.rcmListId" />
	<html:hidden property="rcmSysDetailDTO.rcmListStatus" />
	<html:hidden property="rcmSysDetailDTO.pmTaskMapListId" />
	<html:hidden property="rcmSysDetailDTO.crityListId" />
	<html:hidden property="rcmSysDetailDTO.rcmCateg" />
	<html:hidden property="rcmSysDetailDTO.eqLocId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
    <div class="article_box">
        <div class="form_box">
        	<!-- System 분석No  -->
            <div class="field">
				<label class="check"><bean:message key="LABEL.rcmAnalNo"/></label>
				<div class="input_box">
					<html:text property="rcmSysDetailDTO.rcmListNo"  tabindex="10" />
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.proStatus"/></label>
				<div class="input_box">
		            <html:text property="rcmSysDetailDTO.rcmListStatusDesc" tabindex="20" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
			<!-- System 분석명  -->
            <div class="field_long">
				<label class="check"><bean:message key="LABEL.rcmDesc"/></label>
				<div class="input_box">
					<html:text property="rcmSysDetailDTO.description"  tabindex="30" />
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_box">
		            <html:text property="rcmSysDetailDTO.eqLocDesc" tabindex="40" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
			<!-- System 분류 -->
			<div class="field">
				<label><bean:message key="LABEL.rcmCateg"/></label>
				<div class="input_box">
		            <html:text property="rcmSysDetailDTO.rcmCategDesc" tabindex="50" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
			<!-- 기간	-->
			<div class="field">
				<label><bean:message key="LABEL.period"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="rcmSysDetailDTO.startDate" tabindex="60" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="rcmSysDetailDTO.endDate" tabindex="70" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 등록일 -->
			<div class="field">
				<label><bean:message key="LABEL.regDate"/></label>
				<div class="calendar">
					<div class="input_box input_carendar">
						<html:text property="rcmSysDetailDTO.regDate" tabindex="80" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- Criticality -->
			<div class="field">
				<label><bean:message key="LABEL.critical"/></label>
				<div class="input_box">
		            <html:text property="rcmSysDetailDTO.crityListDesc" tabindex="90" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
			<!-- Task Map -->
			<div class="field">
				<label><bean:message key="LABEL.taskMap"/></label>
				<div class="input_box">
		            <html:text property="rcmSysDetailDTO.pmTaskMapListDesc" tabindex="100" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
		            <html:textarea property="rcmSysDetailDTO.remark" styleClass="ta50" tabindex="110" />
		            <p class="open_spop">
		            	<a><span>조회</span></a>
		            </p>
				</div>
			</div>
    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>
