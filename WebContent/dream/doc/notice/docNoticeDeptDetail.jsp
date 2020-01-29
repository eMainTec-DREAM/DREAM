<%--===========================================================================
공지부서
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.notice.action.DocNoticeDeptDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 공지부서 -->
<title><bean:message key='LABEL.dept'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var deptAc;

function loadPage() 
{
	docNoticeDeptDetailForm.elements['docNoticeDeptDetailDTO.noticeId'].value = 
		docNoticeDeptDetailForm.elements['docNoticeCommonDTO.noticeId'].value;
	
    setTitle("docNoticeDeptDetailDTO.deptNo","docNoticeDeptDetailDTO.deptDesc");
    
    // 부서
    deptAc = new autoC({"docNoticeDeptDetailDTO.deptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
    deptAc.setAcResultMap({
        "docNoticeDeptDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    //For Save
    setForUpdate();
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQANOTIDEPT_ID');
    
    deptAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    docNoticeDeptDetailForm.elements['docNoticeDeptDetailDTO.notiDeptId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}

// 중복 체크
function detailCheck(){
    var actionUrl = contextPath + "/docNoticeDeptDetail.do";
    var param =  "&strutsAction=" + '<%= DocNoticeDeptDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(docNoticeDeptDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterDetailCheck');
}

var isExist;
function afterDetailCheck(ajaxXmlDoc)
{
	isExist = '0';
	isExist = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(isExist != '1')
    {
        closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0216'/>");

        return;
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
    
    /* 부서 중복체크
	detailCheck();
	if(isExist!='0') return; */
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) docNoticeDeptDetailForm.strutsAction.value = "<%=DocNoticeDeptDetailAction.DETAIL_INPUT%>";
    else docNoticeDeptDetailForm.strutsAction.value = "<%=DocNoticeDeptDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/docNoticeDeptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(docNoticeDeptDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(docNoticeDeptDetailForm.elements['docNoticeDeptDetailDTO.notiDeptId'].value);
    if (parent.parent.goTabPage) 
    {
    	parent.parent.goTabPage('docNoticeTargetList');
    }
    getTopPage().afterSaveAll(currentPageId);
    setTitle("docNoticeDeptDetailDTO.deptNo","docNoticeDeptDetailDTO.deptDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = docNoticeDeptDetailForm.elements['docNoticeDeptDetailDTO.notiDeptId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/docNoticeDeptDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeDeptDetailDTO.notiDeptId"/><!-- Key -->
<html:hidden property="docNoticeDeptDetailDTO.noticeId"/> 
<html:hidden property="docNoticeDeptDetailDTO.deptId"/> 
<html:hidden property="docNoticeDeptDetailDTO.deptNo"/> 
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 부서명 -->
			<div class="field">
				<label><bean:message key="LABEL.deptDesc"/></label>
				<div class="input_box">
					<html:text property="docNoticeDeptDetailDTO.deptDesc" tabindex="30"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
				
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="docNoticeDeptDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
