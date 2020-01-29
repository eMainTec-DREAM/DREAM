<%--===========================================================================
안전작업 - 안전작업허가서유형 상세
author  syyang
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@	page import="dream.work.let.permit.action.WorkLetPermitDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html>
<head>
<!-- 안전작업허가서유형-->
<title><bean:message key="TAB.workLetPermitList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var woLetCtgAc;

function loadPage() 
{
	setTitle("workLetPermitDetailDTO.woLetListNo", "workLetPermitDetailDTO.woLetCtgTypeDesc");
	
	setForUpdate();
	
	woLetCtgAc = new autoC({"workLetPermitDetailDTO.woLetCtgTypeDesc":"woLetCtgTypeDesc"});
	woLetCtgAc.setTable("TAWOLETCTG");
	woLetCtgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	woLetCtgAc.setKeyName("workLetPermitDetailDTO.woLetCtgType");
	woLetCtgAc.setAcResultMap({
        "workLetPermitDetailDTO.woLetCtgType":"woLetCtgType"
        ,"workLetPermitDetailDTO.woLetCtgTypeDesc":"woLetCtgTypeDesc"
    });
	woLetCtgAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else {
		goUpdate();
	}
}

/**
 * Show/Hide Button
 */
function setBtnAsStatus()
{
	if(workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListStatus'].value=="COM"){
		setDisableAll();
		hideBtn("CONFIRM");
		
		showBtn("REVERSE");
	}
	else{
		setEnableAll();
		$(document).find('.fb_group2').children().each(function(){
	        if($(this).is('a'))
	            $(this).show();
	        else
	            return false;           
	    });
		showBtn("CONFIRM");
		
		hideBtn("REVERSE");
	}
}

function goInput()
{
	// 시퀀스를 조회한다. 시퀀스 생성 안되어 있음
 	sequenceNextVal('SQAWOLETLIST_ID');
	
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("REVERSE");
	hideBtn("PRINT");
	
	//작업상태 = DNG - 진행중
	workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListStatusDesc'].value = "DNG";
	valSysDir('workLetPermitDetailDTO.woLetListStatus', 'workLetPermitDetailDTO.woLetListStatusDesc', 'WOLETLIST_STATUS', true);
	
	//날짜,시간값이 비어있으면 작업상세의 값을 넣는다.
	var dateTimes = parent.getDateTime();
	dateTimes = dateTimes.split(",");
	if(workLetPermitDetailForm.elements['workLetPermitDetailDTO.startDate'].value=='') 
		workLetPermitDetailForm.elements['workLetPermitDetailDTO.startDate'].value = dateTimes[0];
	if(workLetPermitDetailForm.elements['workLetPermitDetailDTO.startTime'].value=='') 
		workLetPermitDetailForm.elements['workLetPermitDetailDTO.startTime'].value = dateTimes[1];
	if(workLetPermitDetailForm.elements['workLetPermitDetailDTO.endDate'].value=='') 
		workLetPermitDetailForm.elements['workLetPermitDetailDTO.endDate'].value = dateTimes[2];
	if(workLetPermitDetailForm.elements['workLetPermitDetailDTO.endTime'].value=='') 
		workLetPermitDetailForm.elements['workLetPermitDetailDTO.endTime'].value = dateTimes[3];

	woLetCtgAc.openLov();
	
}

function setSequenceVal(sequenceVal)
{
	workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value = sequenceVal;
	workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListNo'].value = sequenceVal;
}

function goUpdate()
{
	setBtnAsStatus();
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)){
    	workLetPermitDetailForm.elements['workLetPermitDetailDTO.creTime'].value = getNowDateTime(true); 
		workLetPermitDetailForm.elements['workLetPermitDetailDTO.updTime'].value = getNowDateTime(true); 

    	workLetPermitDetailForm.strutsAction.value = "<%=WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INPUT%>";
    }
    else {
    	workLetPermitDetailForm.elements['workLetPermitDetailDTO.updTime'].value = getNowDateTime(true); 
		
    	workLetPermitDetailForm.strutsAction.value = "<%=WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_UPDATE%>";
    }

	var actionUrl = contextPath + "/workLetPermitDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workLetPermitDetailForm), 'afterSave');
		
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value);
    getTopPage().afterSaveAll(currentPageId);

	setTitle("workLetPermitDetailDTO.woLetListNo", "workLetPermitDetailDTO.woLetCtgTypeDesc");

	setBtnAsStatus();
}


function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.workLetPermitDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "workLetPermitDocLibList")
	{	
		workLetPermitDetailForm.elements['maDocLibCommonDTO.objectId'].value = workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value;
		workLetPermitDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOLETLIST";  //WOLETLIST: 안전작업허가서유형
		workLetPermitDetailForm.elements['maDocLibCommonDTO.description'].value = 
			workLetPermitDetailForm.elements['workLetDetailDTO.woLetDesc'].value +"-" +workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetCtgTypeDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

/**
 * 작업완료
 */
function goConfirm()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0017'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				//작업시간이 미래라면 완료 처리 안됨
				var todayStr = getToday().split("-").join("");
				var startDate = workLetPermitDetailForm.elements['workLetPermitDetailDTO.startDate'].value;
				startDate = startDate.split("-").join("");
				var endDate = workLetPermitDetailForm.elements['workLetPermitDetailDTO.endDate'].value;
				endDate = endDate.split("-").join("");
				
				if(todayStr < startDate||todayStr < endDate)
				{
					alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
					return;
				}
				
				workLetPermitDetailForm.elements['workLetPermitDetailDTO.updTime'].value = getNowDateTime(true); 
				workLetPermitDetailForm.strutsAction.value = '<%=WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE%>';
				var actionUrl = contextPath + "/workLetPermitDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(workLetPermitDetailForm), 'afterConfirm');
			}
		});
	}
}
/**
 * 완료후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
	
	//안전작업상태 = COM - 작업완료
	workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListStatus'].value = "COM";
	valSysDirCode('workLetPermitDetailDTO.woLetListStatus', 'workLetPermitDetailDTO.woLetListStatusDesc', 'WOLETLIST_STATUS', true);
	
	if (parent.findGridRow)	parent.findGridRow(workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value);
	
	setBtnAsStatus();

	// 완료 시 안전작업허가서 상태 변경
	if (parent.parent.parent.findGridRow)
		parent.parent.parent.findGridRow(workLetPermitDetailForm.elements['workLetCommonDTO.woLetId'].value);
	
	if (parent.parent.setUpdateStatus)
		parent.parent.setUpdateStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));
}
 
/**
 * 작업완료 취소
 */
function goReverse()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		 if(result){
		    var url = contextPath + "/workLetPermitDetail.do";
		    workLetPermitDetailForm.elements['workLetPermitDetailDTO.updTime'].value = getNowDateTime(true); 
		    workLetPermitDetailForm.strutsAction.value = '<%=WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE_CANCEL%>';
		    XMLHttpPost(url, FormQueryString(workLetPermitDetailForm), 'afterReverse');
		 }
	});
}
/**
 * 완료 취소후 호출
 */
function afterReverse(ajaxXmlDoc)
{
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
 	
	//작업상태 = DNG - 진행중
	workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListStatus'].value = "DNG";
	valSysDirCode('workLetPermitDetailDTO.woLetListStatus', 'workLetPermitDetailDTO.woLetListStatusDesc', 'WOLETLIST_STATUS', true);
	
	if (parent.findGridRow)	parent.findGridRow(workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value);
	
	setBtnAsStatus();
	
	// 완료취소 시 안전작업허가서 상태 변경
	if (parent.parent.parent.findGridRow)
		parent.parent.parent.findGridRow(workLetPermitDetailForm.elements['workLetCommonDTO.woLetId'].value);

	if (parent.parent.setUpdateStatus)
		parent.parent.setUpdateStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));
}
 
/**
 * Print 버튼 클릭
 */
function goPrint()
{

}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workLetPermitDetailForm.elements['workLetPermitDetailDTO.woLetListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workLetPermitDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workLetCommonDTO.woLetId"/><!-- Key -->
	<html:hidden property="workLetDetailDTO.woLetId"/><!-- Key -->
	<html:hidden property="workLetDetailDTO.woLetDesc"/>
	<html:hidden property="workLetPermitListDTO.woLetListId"/><!-- Key -->
	<html:hidden property="workLetPermitDetailDTO.woLetListId"/>
	<html:hidden property="workLetPermitDetailDTO.woLetListStatus"/>
	<html:hidden property="workLetPermitDetailDTO.woLetCtgType"/>
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">			
			<!-- 안전작업 No -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woLetNo"/></label>
				<div class="input_read" id="woNoDiv">
					<html:text property="workLetPermitDetailDTO.woLetListNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.proStatus"/></label>
				<div class="input_read" id="woStatusDescDiv">
					<html:text property="workLetPermitDetailDTO.woLetListStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업유형 -->
			<div class="field">
				<label><bean:message key="LABEL.woType2"/></label>
				<div class="input_box">
					<html:text property="workLetPermitDetailDTO.woLetCtgTypeDesc" tabindex="30"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업장소 -->
			<div class="field">
				<label><bean:message key="LABEL.woLocation"/></label>
				<div class="input_box">
                    <html:text property="workLetPermitDetailDTO.place" tabindex="40" />
                </div>
			</div>
			<!-- 작업기간 From -->
			<div class="field">
				<label><bean:message key="LABEL.woPeriodFrom"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetPermitDetailDTO.startDate"  tabindex="50" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetPermitDetailDTO.startTime"   tabindex="60"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업기간 To -->
			<div class="field">
				<label><bean:message key="LABEL.woPeriodTo"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workLetPermitDetailDTO.endDate" tabindex="70" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workLetPermitDetailDTO.endTime" tabindex="80" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 발행일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woLetDate"/></label>
				<div class="input_box">
					<html:text property="workLetPermitDetailDTO.woLetDate" tabindex="100" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workLetPermitDetailDTO.remark" styleClass="ta50" tabindex="150" />
				</div>
			</div>
			
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="workLetPermitDetailDTO.creTime" tabindex="160" readonly="true"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="workLetPermitDetailDTO.updTime" tabindex="170" readonly="true"/>
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>