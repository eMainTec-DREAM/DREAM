<%--===========================================================================
표준항목 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.work.pm.std.action.MaStdPointHdrDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript"> // 저장 시 수행되는 action
<!--//

var eqCtgTypeAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    	//goTabPage("maStdPointList");
    }
    
    setTitle("maStdPointHdrDetailDTO.stWrkNo","maStdPointHdrDetailDTO.stWrkDesc");
    
    setForUpdate();

    // 버튼제어
    setBtnStatus();

	// 설비종류    
    eqCtgTypeAc = new autoC({"maStdPointHdrDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("maStdPointHdrDetailDTO.eqCtgId");
    eqCtgTypeAc.setAcResultMap({
        "maStdPointHdrDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init(); 
    
    // 등록부서
	deptAc = new autoC({"maStdPointHdrDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maStdPointHdrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maStdPointHdrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();

	// 등록자
	empAc = new autoC({"maStdPointHdrDetailDTO.regName":"emp_name"});
	empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	});
    empAc.setTable("TAEMP");
    empAc.setKeyName("maStdPointHdrDetailDTO.regBy");
    empAc.setAcResultMap({
        "maStdPointHdrDetailDTO.regBy":"emp_id"
    });
    empAc.init();
    
	// 시행여부      
    acSysDesc("maStdPointHdrDetailDTO.isActiveDesc","maStdPointHdrDetailDTO.isActive","IS_USE",true);  
    
 	// 제/개정 화면제어
 	revDisplayCtrl(M$('maStdPointHdrDetailDTO.revisionStatusId').value,M$('maStdPointHdrDetailDTO.isLastVersion').value,"PM");
}

/*
 * Show/Hide Button
 */
function setBtnStatus()
{
	// 적용 상태 - C
	if(maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stwrkStatus'].value=="C")
	{
		setDisableAll();
	}
	else
	{
		setEnableAll();
	}
}

/**
 * 입력
 */
function goInput()
{
	//적용버튼 감추기
	$(".b_confirm").css("display","none");
	
 	//작성상태 - 작성중
	maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stwrkStatusDesc'].value = "W";
	valSysDir('maStdPointHdrDetailDTO.stwrkStatus', 'maStdPointHdrDetailDTO.stwrkStatusDesc', 'STWRK_STATUS', true);
	
 	//최신버전여부 - Y
	maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.isLastVersion'].value = "Y";
	valSysDir('maStdPointHdrDetailDTO.isLastVersion', 'maStdPointHdrDetailDTO.isLastVersion', 'IS_USE', true);

	maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.regDate'].value   = getToday();

	maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.isActiveDesc'].value   = "Y";
	valSysDir('maStdPointHdrDetailDTO.isActive', 'maStdPointHdrDetailDTO.isActiveDesc', 'IS_USE', true);
    sequenceNextVal('SQASTWRK_ID'); 
}

/**
 * 수정
 */
function goUpdate()
{
	//저장후 적용버튼 보이기
	$(".b_confirm").css("display","");
}

function setSequenceVal(sequenceVal)
{
    maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value = sequenceVal;
    maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkNo'].value = sequenceVal;
    maStdPointHdrDetailForm.elements['maStdPointCommonDTO.stWrkId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
    maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.lastUpdTime'].value = getNowDateTime();
	
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maStdPointHdrDetailForm.strutsAction.value = "<%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_INPUT%>";
    else maStdPointHdrDetailForm.strutsAction.value = '<%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_UPDATE%>'; 
    
    var actionUrl = contextPath + "/maStdPointHdrDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maStdPointHdrDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maStdPointHdrDetailForm.elements['maStdPointCommonDTO.stWrkId'].value = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value;

     if (parent.findGridRow)	parent.findGridRow(maStdPointHdrDetailForm.elements['maStdPointCommonDTO.stWrkId'].value);
     setTitle("maStdPointHdrDetailDTO.stWrkNo","maStdPointHdrDetailDTO.stWrkDesc");
     goUpdate();
     setBtnStatus();
     getTopPage().afterSaveAll(currentPageId);
 }
 
function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maStdPointHdrDetailForm;
	if(pageId == "maStdDocLibList")
	{	
		maStdPointHdrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value;
		maStdPointHdrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "STDPOINT";  //EQMSTR docDesc
		maStdPointHdrDetailForm.elements['maDocLibCommonDTO.description'].value = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
				
				 maStdPointHdrDetailForm.strutsAction.value = '<%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_CONFIRM%>';
				 var actionUrl = contextPath + "/maStdPointHdrDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maStdPointHdrDetailForm), 'afterConfirm');
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
	maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stwrkStatus'].value = "C";
	valSysDirCode('maStdPointHdrDetailDTO.stwrkStatus', 'maStdPointHdrDetailDTO.stwrkStatusDesc', 'STWRK_STATUS', true);
	
	setBtnStatus();
	
	// 확정시에 제개정 완료처리
	goRevcompleted();
	
	 if (parent.findGridRow)	parent.findGridRow(maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value);
}

/**
 * 완료
 */ 
function goRevcompleted()
{
	var revStatus = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.revisionStatusId'].value;
	var objId = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value;
	var objNo = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkNo'].value;
	var revhistId = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.revisionhistId'].value;
	 
	revCompleted(revStatus, objId, objNo, revhistId, "STWRK");
}

function afterRevcompleted(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	alertMessage1("<bean:message key='MESSAGE.CMSG102'/>");
	//조회후 선택!
	if(parent.findGridRow) parent.findGridRow(maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value);
	if(parent.goTabPage) parent.goTabPage(currentPageId);
}

/**
 * 개정
 */ 
function goRevision()
{
	var revhistId = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.revisionhistId'].value;
	var desc = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkDesc'].value;
	
	openRev("commRevRevision", revhistId, desc, currentPageId);
}

function goRevisionhistory()
{
	openRevHistory(maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value, maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkNo'].value);
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maStdPointHdrDetailForm.elements['maStdPointHdrDetailDTO.stWrkId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maStdPointHdrDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maStdPointCommonDTO.stWrkId"/>
	<html:hidden property="maStdPointHdrDetailDTO.stWrkId"/>
	<html:hidden property="maStdPointHdrDetailDTO.eqCtgId"/>
	<html:hidden property="maStdPointHdrDetailDTO.updBy"/>
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maStdPointHdrDetailDTO.revisionhistId" />
	<html:hidden property="maStdPointHdrDetailDTO.revisionStatusId" />
	<html:hidden property="maStdPointHdrDetailDTO.stwrkStatus" />
	<html:hidden property="maStdPointHdrDetailDTO.isActive" />
	<html:hidden property="maStdPointHdrDetailDTO.deptId" />
	<html:hidden property="maStdPointHdrDetailDTO.regBy" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 표준번호 -->
			<div class="field">
				<label><bean:message key="LABEL.stWrkNo"/></label>
				<div class="input_read">
					<html:text property="maStdPointHdrDetailDTO.stWrkNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
				<label>상태</label>
				<div class="input_read">
					<html:text property="maStdPointHdrDetailDTO.stwrkStatusDesc" tabindex="15" readonly="true"/>
				</div>
			</div>
			<!-- 표준명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.stWrkDesc"/></label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.stWrkDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 설비종류 -->
			<div class="field">
				<label><bean:message key="LABEL.eqCtg2"/></label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.eqCtgDesc" tabindex="30" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부서 -->
			<div class="field">
				<label>등록부서</label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.deptDesc" tabindex="34" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 등록일자 -->
			<div class="field">
				<label>등록일자</label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.regDate" tabindex="38" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 등록자 -->
			<div class="field">
				<label>등록자</label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.regName" tabindex="34" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 시행여부 -->
			<div class="field">
				<label>시행여부</label>
				<div class="input_box">
					<html:text property="maStdPointHdrDetailDTO.isActiveDesc" tabindex="34" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 최신Version여부 -->
			<div class="field">
				<label>최신Version여부</label>
				<div class="input_read">
					<html:text property="maStdPointHdrDetailDTO.isLastVersion" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 최종수정시간 -->
			<div class="field">
				<label>최종수정시간</label>
				<div class="input_read">
					<html:text property="maStdPointHdrDetailDTO.lastUpdTime" tabindex="40" readonly="true"/>
				</div>
			</div>
			
			<!-- 최종수정자 -->
			<div class="field">
				<label><bean:message key="LABEL.lastUpdBy"/></label>
				<div class="input_read">
					<html:text property="maStdPointHdrDetailDTO.updName" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maStdPointHdrDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
