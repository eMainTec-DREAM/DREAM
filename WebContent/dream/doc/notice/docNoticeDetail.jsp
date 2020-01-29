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

<%@ page import="dream.doc.notice.action.DocNoticeDetailAction" %>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>

<html:html>
<head>
<!-- 공지사항 디테일 -->
<title><bean:message key='MENU.NOTICE' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var empAc, equipAc, eqLocAc, assBaseListAc;

function loadPage() 
{	
	if("C"==docNoticeDetailForm.elements['docNoticeDetailDTO.noticeStatusId'].value)
		$('.b_notice').hide();
	
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setTitle("docNoticeDetailDTO.noticeNo", "docNoticeDetailDTO.title");
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQANOTICE_ID');

    docNoticeDetailForm.elements['docNoticeDetailDTO.noticeStatusId'].value = "W";
    docNoticeDetailForm.elements['docNoticeDetailDTO.noticeStatus'].value = "W";
    valSysDir('docNoticeDetailDTO.noticeStatusId', 'docNoticeDetailDTO.noticeStatus', 'EQASSLIST_STATUS', true);
    
 	//작성자 
	docNoticeDetailForm.elements['docNoticeDetailDTO.writeById'].value = loginUser.empId;
	docNoticeDetailForm.elements['docNoticeDetailDTO.writeBy'].value   = loginUser.empName;
	
 	//작성부서
	docNoticeDetailForm.elements['docNoticeDetailDTO.regDeptId'].value = loginUser.deptId;
	docNoticeDetailForm.elements['docNoticeDetailDTO.regDept'].value   = loginUser.deptDesc;
}

function setSequenceVal(sequenceVal)
{
    docNoticeDetailForm.elements['docNoticeDetailDTO.noticeId'].value = sequenceVal;
    docNoticeDetailForm.elements['docNoticeCommonDTO.noticeId'].value = sequenceVal;
    docNoticeDetailForm.elements['docNoticeDetailDTO.noticeNo'].value = sequenceVal;
    
    docNoticeDetailForm.elements['docNoticeDetailDTO.noticePeriod'].value = getToday();
    docNoticeDetailForm.elements['docNoticeDetailDTO.noticeDate'].value = getToday();
}
/**
 * 수정
 */
function goUpdate()
{

}

/**
 * 요청
 */
 
//공지하기
function goNotice(){

	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0217'/>", function(result){
            if(result){
                   //================================
                   // 필수 항목 체크한다.
                   //================================
                   if(checkValidation()) return;

                   docNoticeDetailForm.strutsAction.value = '<%=DocNoticeDetailAction.GO_NOTICE%>';
                   
                   var actionUrl = contextPath + "/docNoticeDetail.do";
                   XMLHttpPost(actionUrl, FormQueryString(docNoticeDetailForm), 'afterGoNotice');
                   
                   }
           });
    }
}
	
/**
  * 공지 후 호출
  */
 function afterGoNotice(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     docNoticeDetailForm.elements['docNoticeCommonDTO.noticeId'].value = docNoticeDetailForm.elements['docNoticeDetailDTO.noticeId'].value;
     if (parent.findGridRow)    parent.findGridRow(docNoticeDetailForm.elements['docNoticeCommonDTO.noticeId'].value);
     
     //상태 = C - 공지완료
     docNoticeDetailForm.elements['docNoticeDetailDTO.noticeStatusId'].value = "C";
     docNoticeDetailForm.elements['docNoticeDetailDTO.noticeStatus'].value = "C";
     valSysDirCode('docNoticeDetailDTO.noticeStatusId', 'docNoticeDetailDTO.noticeStatus', 'NOTICE_STATUS', true);
     getTopPage().afterSaveAll(currentPageId);
     $('.b_notice').hide();
     
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

    if(ckCreate(currentPageId)) docNoticeDetailForm.strutsAction.value = "<%=DocNoticeDetailAction.DETAIL_INPUT%>";
    else docNoticeDetailForm.strutsAction.value = "<%=DocNoticeDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/docNoticeDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(docNoticeDetailForm),'afterSave');

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
        parent.findGridRow(docNoticeDetailForm.elements['docNoticeDetailDTO.noticeId'].value);

    docNoticeDetailForm.elements['docNoticeCommonDTO.noticeId'].value = docNoticeDetailForm.elements['docNoticeDetailDTO.noticeId'].value;
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("docNoticeDetailDTO.noticeNo", "docNoticeDetailDTO.title");

}

function goTabPage(pageId) 
{
    var form = document.docNoticeDetailForm;
    
    if(pageId == "maDocLibList" || pageId == "docNoticeDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['docNoticeDetailDTO.noticeId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "NOTICE";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['docNoticeDetailDTO.title'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
       
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = docNoticeDetailForm.elements['docNoticeDetailDTO.noticeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/docNoticeDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="docNoticeCommonDTO.noticeId" />          <!-- Key -->
        <html:hidden property="docNoticeDetailDTO.noticeId" />          <!-- Key -->
        <html:hidden property="docNoticeDetailDTO.noticeStatusId" />    <!-- 상태ID -->
        <html:hidden property="docNoticeDetailDTO.regDeptId" />         <!-- 작성부서ID -->
        <html:hidden property="docNoticeDetailDTO.writeById" />         <!-- 작성자ID -->
        <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
		<html:hidden property="maDocLibCommonDTO.objectType" />
		<html:hidden property="maDocLibCommonDTO.description" />
        
        <div class="article_box">
            <div class="form_box">
                <!-- 공지# -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.noticeNo"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.noticeNo" tabindex="10"/>
                    </div>
                </div>
                <!-- 상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.noticeStatus" tabindex="20" readonly="true" />
                    </div>
                </div>
                <!-- 제목 -->
                <div class="field_long">
                    <label class="check"><bean:message key="LABEL.title"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.title" tabindex="30"/>
                    </div>
                </div>
                <!-- 공지기한 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.noticePeriod"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeDetailDTO.noticePeriod" tabindex="40"/>
                        <p class="open_calendar">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 공지일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.noticeDate"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.noticeDate" tabindex="50" readonly="true" />
                    </div>
                </div>
                <!-- 작성부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.regDept"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.regDept" tabindex="60" readonly="true" />
                    </div>
                </div>
                <!-- 작성자 -->
                <div class="field">
                    <label><bean:message key="LABEL.writeBy"/></label>
                    <div class="input_read">
                        <html:text property="docNoticeDetailDTO.writeBy" tabindex="70" readonly="true" />
                    </div>
                </div>
                <!-- 내용 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.contents"/></label>
                    <div class="input_box">
                        <html:textarea property="docNoticeDetailDTO.remark" styleClass="ta150" tabindex="80" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
