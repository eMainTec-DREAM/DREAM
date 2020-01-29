<%--===========================================================================
이상점검조치 - 상세
author  kim21017
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
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='LABEL.woNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
/** 자동완성 변수  */
var repairNameAc;

var isSelected = false;   // 작업종류&작업형태 선택시 true

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maBdPointDetailDTO.inspectWoNo", "maBdPointDetailDTO.checkPoint");
	
	setForUpdate();

    repairNameAc = new autoC({"maBdPointDetailDTO.repairName":"emp_name"});
    repairNameAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    repairNameAc.setTable("TAEMP");
    repairNameAc.setKeyName("maBdPointDetailDTO.repairBy");
    repairNameAc.setAcResultMap({
        "maBdPointDetailDTO.repairBy":"emp_id"
       ,"maBdPointDetailDTO.repairDept":"deptDesc"
    });
    repairNameAc.init();

    // 조치결과  AC
    acSysDesc("maBdPointDetailDTO.pmPointRepStatusDesc","maBdPointDetailDTO.pmPointRepStatus","PM_POINT_REP_STATUS",true);
}
	
function goInput()
{}
function goUpdate()
{
	setState();
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
	if(!ckCreate(currentPageId)) 
		maBdPointDetailForm.strutsAction.value = '<%=MaBdPointDetailAction.BD_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maBdPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maBdPointDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maBdPointDetailForm.elements['maBdPointDetailDTO.woNgPointId'].value = maBdPointDetailForm.elements['maBdPointCommonDTO.woNgPointId'].value;
	if (parent.findGridRow)	parent.findGridRow(maBdPointDetailForm.elements['maBdPointDetailDTO.woNgPointId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setState();
}
 
/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
	var form = document.maBdPointDetailForm;
	var objType = "";
	
	switch(form.elements['maBdPointDetailDTO.pmiType'].value){
		case "INS":
			objType = "PM_POINT";
			break;
		case "RINS":
			objType = "PM_ROUTINE_POINT";
			break;
		case "DINS":
			objType = "PM_DAY_POINT";
			break;
		case "PINS":
			objType = "PM_PATROL_POINT";
			break;
		case "CINS":
			objType = "PM_PART_POINT";
			break;
	}
	
	if(pageId == "maDocLibList" || pageId == "maBdPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = maBdPointDetailForm.elements['maBdPointDetailDTO.attachId'].value;
// 		form.elements['maDocLibCommonDTO.objectType'].value = "PM_DAY_POINT";
		form.elements['maDocLibCommonDTO.objectType'].value = objType;
		form.elements['maDocLibCommonDTO.description'].value = maBdPointDetailForm.elements['maBdPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}
<%-- 
function goFix()
{
	goWoex();
}
 
/**
 * 조치 WO 발행
 */
 function goWoex(){
    
    if (isSelected == false)
    {
        // 선택
        goSelect();
    }
    else
    {
    
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0028'/>", function(result){
         if(result){
        	 maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value = "WO";
             maBdPointDetailForm.strutsAction.value = '<%=MaBdPointDetailAction.WO_CREATE%>';
             var url = contextPath + "/maBdPointDetail.do";
             $.post(url,FormQueryString(maBdPointDetailForm), function(_data){
                 parent.openWo=true;
                 afterWoex();
             });
         }
     });
    }
}

function afterWoex(){
	setState();
//     goClose('maBdPointDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG0027"/>');
    isSelected=false;
    
    if (parent.findGridRow) parent.findGridRow(maBdPointDetailForm.elements['maBdPointDetailDTO.woNgPointId'].value);
    parent.goOpen();
}

// 작업종류 & 작업형태 선택창
function goSelect(){
    var param = "strutsAction=1001";
    var url =  contextPath + "/maWoResultSelect.do";
        
    openLayerPopup("maWoResultSelect", param);
}

function setAfterSelect(returnArray){

    var woType = returnArray[0];
    var pmType = returnArray[1];
    var _pageId  = returnArray[2];

    var ifm = getIframeContent();
    
    maBdPointDetailForm.elements['maBdPointDetailDTO.woType'].value = woType;
    maBdPointDetailForm.elements['maBdPointDetailDTO.pmType'].value = pmType;
    
    isSelected = true;

    goWoex();
}
 --%>
/* 
// 조치 W/O 열기
function goAcwo(){
    
    var pmRepMethodType = maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value;
    
    if(pmRepMethodType == "WO") {
    	var wkorId = maBdPointDetailForm.elements['maBdPointDetailDTO.pmWkorId'].value;
        var pmType = maBdPointDetailForm.elements['maBdPointDetailDTO.pmType'].value;
        var equipId = maBdPointDetailForm.elements['maBdPointDetailDTO.equipId'].value;

        var param = maBdPointDetailForm.elements['maBdPointDetailDTO.param1'].value;
        pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
        
        var url   = contextPath + "/"+param+".do";

        var popWidth = 1010;
        var popHeight = 640;

        // pop up이 중앙에 위치하게 한다.
        var TopPosition  = (screen.height/2 - popHeight/2);
        var LeftPosition = (screen.width/2 - popWidth/2);

        var pos = "width=" + popWidth + ",height=" + popHeight + "" +
                  ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
        pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
        
        var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ wkorId+"&maWoResultMstrDetailDTO.equipId="+equipId;
      
        openWindowWithPost(url, "WO_DETAIL", param, pos);
       
        parent.openWo = false;
    }
    else {
    	goWoex();
    }
}
 */
<%-- 
//작업요청
function goWorkreq()
{
	var pmRepMethodType = maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value;
    
    if(pmRepMethodType == "REQ") {
    	goWorkreqOpen(maBdPointDetailForm.elements['maBdPointDetailDTO.woreqId'].value);
    }
    else {
    	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0225'/>", function(result){
	        if(result){
	        	maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value = "REQ";
	            maBdPointDetailForm.strutsAction.value = '<%=MaBdPointDetailAction.WO_REQ_CREATE%>';
	            var url = contextPath + "/maBdPointDetail.do";
	            $.post(url,FormQueryString(maBdPointDetailForm), function(_data){
	            	afterWoCreate();
	            });
	        }
	    });
    }
}
//작업요청서 열기
function goWorkreqOpen(woreqId)
{
	var url   = contextPath + "/reqWorkDetail.do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&reqWorkCommonDTO.woReqId="+ woreqId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
}
 --%>
<%-- 
//작업계획
function goWorkplan()
{
	var pmRepMethodType = maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value;
    
    if(pmRepMethodType == "PLAN") {
    	goWorkplanOpen(maBdPointDetailForm.elements['maBdPointDetailDTO.woplanId'].value);
    }
    else {
    	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0226'/>", function(result){
	        if(result){
	        	maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value = "PLAN";
	            maBdPointDetailForm.strutsAction.value = '<%=MaBdPointDetailAction.WO_PLAN_CREATE%>';
	            var url = contextPath + "/maBdPointDetail.do";
	            $.post(url,FormQueryString(maBdPointDetailForm), function(_data){
	            	afterWoCreate();
	            });
	        }
	    });
    }
}
//작업계획서 열기
function goWorkplanOpen(woplanId)
{
	var url   = contextPath + "/woPlanDetail.do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&woPlanCommonDTO.wkOrId="+ woplanId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);		
}
function afterWoCreate(){
	setState();
	
    alertMessage1('<bean:message key="MESSAGE.MSG0227"/>');
     
//     if (parent.findGridRow) parent.findGridRow(maBdPointDetailForm.elements['maBdPointDetailDTO.woNgPointId'].value);
//     parent.goOpen();   
    getId();
}
 --%>

function getId(){
	
    var actionUrl = contextPath + "/maBdPointDetail.do";
    var param =  "&strutsAction=" + '<%= MaBdPointDetailAction.FIND_ID %>'
              +  "&" + FormQueryString(maBdPointDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterGetId');
}

var v_id;
function afterGetId(ajaxXmlDoc)
{
	var pmRepMethodType = maBdPointDetailForm.elements['maBdPointDetailDTO.pmRepMethodType'].value;
    v_id = '';
    v_id = parseXmlDoc(ajaxXmlDoc, 'DESC').toString();
    
    if(v_id != '')
    {
        closeModal();

        maBdPointDetailForm.elements['maBdPointDetailDTO.woreqId'].value = v_id.substring(0,v_id.indexOf(",")); 
        maBdPointDetailForm.elements['maBdPointDetailDTO.woplanId'].value = v_id.substring(v_id.indexOf(",")+1); 
    }
}

function checkStatus() {
	var actionUrl = contextPath + "/maBdPointDetail.do";
    var param =  "&strutsAction=" + '<%= MaBdPointDetailAction.FIND_STATUS %>'
              +  "&" + FormQueryString(maBdPointDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterCheckStatus');
}

function afterCheckStatus(ajaxXmlDoc) {
	maBdPointDetailForm.elements['maBdPointDetailDTO.pmPointRepStatus'].value = parseXmlDoc(ajaxXmlDoc, 'DESC').toString();
	maBdPointDetailForm.elements['maBdPointDetailDTO.pmPointRepStatusDesc'].value = parseXmlDoc(ajaxXmlDoc, 'DESC').toString();
	
	valSysDir("maBdPointDetailDTO.pmPointRepStatus", "maBdPointDetailDTO.pmPointRepStatusDesc", "PM_POINT_REP_STATUS", true);
	
	if(typeof searchPage("maBdPointList").findGridRow == "function"){
		searchPage("maBdPointList").findGridRow(maBdPointDetailForm.elements['maBdPointCommonDTO.woNgPointId'].value, '');	
	}
	
	setState();
}

function setState()
{
	var pmPointRepStatus = maBdPointDetailForm.elements['maBdPointDetailDTO.pmPointRepStatus'].value;
	
	if(pmPointRepStatus == "GD")
	{
		setDisableAll();
		hideBtn('save');
	}
    else
    {
    	setEnableAll();
		showBtn('save');
    }
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maBdPointDetailForm.elements['maBdPointDetailDTO.woNgPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maBdPointDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maBdPointCommonDTO.woNgPointId" />	
	
	<html:hidden property="maBdPointDetailDTO.woNgPointId" />
	<html:hidden property="maBdPointDetailDTO.pmRepMethodType" />
	<html:hidden property="maBdPointDetailDTO.repairBy" />
	<html:hidden property="maBdPointDetailDTO.pmPointRepStatus" />
	
	<html:hidden property="maBdPointDetailDTO.woType" />
	<html:hidden property="maBdPointDetailDTO.pmType" />
	<html:hidden property="maBdPointDetailDTO.pmiType" />
	<html:hidden property="maBdPointDetailDTO.pmWkorId" />
	<html:hidden property="maBdPointDetailDTO.equipId" />
	<html:hidden property="maBdPointDetailDTO.param1" />
	
	<html:hidden property="maBdPointDetailDTO.woreqId" />
	<html:hidden property="maBdPointDetailDTO.woplanId" />
	<html:hidden property="maBdPointDetailDTO.attachId" />
	
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	<html:hidden property="maDocLibCommonDTO.objectType" />
	<html:hidden property="maDocLibCommonDTO.description" />
	
	<div class="article_box">
		<div class="form_box">
			<!-- 점검일자 -->
			<div class="field">
				<label><bean:message key="LABEL.inspectDate"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.inspectDate" readonly="true"/>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label><bean:message key="LABEL.manageDept"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.deptDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설비위치 -->
			<div class="field">
				<label><bean:message key="LABEL.eqLocDesc"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.eqLocDesc" readonly="true"/>
				</div>
			</div>
			<!-- 작업자 -->
			<div class="field">
				<label><bean:message key="LABEL.woCraft"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.woCraft" readonly="true"/>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label><bean:message key="LABEL.equipment"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.equipDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설비코드 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.equipNo" readonly="true"/>
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.asmbDesc" readonly="true"/>
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.checkPoint" readonly="true"/>
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.checkMethod" readonly="true"/>
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.fitBasis" readonly="true"/>
				</div>
			</div>
			<!-- 수치/판정 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.checkType"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.checkType" readonly="true"/>
				</div>
			</div>
			<!-- 설정값()/단위 -->
			<div class="field">
				<label><bean:message key="LABEL.checkValUom"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.checkUom" readonly="true"/>
				</div>
			</div>
			<!-- 점검값 -->
			<div class="field">
				<label><bean:message key="LABEL.resultVal"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.resultValue" readonly="true"/>
				</div>
			</div>
			<!-- 점검결과 -->
			<div class="field">
				<label><bean:message key="LABEL.rsltStatusDesc"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.pmPointRsltStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 조치W/O# -->
			<div class="field">
				<label><bean:message key="LABEL.inspectWoNo"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.inspectWoNo" readonly="true"/>
				</div>
			</div>
			<!-- 점검세부내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.inspectRemark"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.inspectRemark" readonly="true"/>
				</div>
			</div>
			<!-- 조치결과  -->
			<div class="field">
				<label><bean:message key="LABEL.reCdResult"/></label>
				<div class="input_box">
					<html:text property="maBdPointDetailDTO.pmPointRepStatusDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 조치내용 -->
			<div class="field">
				<label><bean:message key="LABEL.repairDesc"/></label>
				<div class="input_box">
					<html:text property="maBdPointDetailDTO.repairDesc"  tabindex="20" />
				</div>
			</div>
			<!-- 조치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.repDate"/></label>
				<div class="input_box">
					<html:text property="maBdPointDetailDTO.repairDate" tabindex="30" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 조치부서 -->
			<div class="field">
				<label><bean:message key="LABEL.repairDept"/></label>
				<div class="input_read">
					<html:text property="maBdPointDetailDTO.repairDept" tabindex="35" readonly="true"/>
				</div>
			</div>
			<!-- 조치자 -->
			<div class="field">
				<label><bean:message key="LABEL.repairBy"/></label>
				<div class="input_box">
					<html:text property="maBdPointDetailDTO.repairName" tabindex="40" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>