 <%--===========================================================================
안전작업유형 Detail page
author  euna0207
version $Id: workLetCategDetail.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.work.let.categ.action.WorkLetCategDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 안전작업유형 -->
<title><bean:message key='MENU.woLetCtg' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var deptAc;
var empAc;

function loadPage() 
{
	//타이틀 설정
    setTitle("workLetCategDetailDTO.woLetCtgNo", "workLetCategDetailDTO.woLetCtgTypeDesc");
    //(?)
    setForUpdate();
    
    //안전작업유형 자동완성
    acSysDesc("workLetCategDetailDTO.woLetCtgTypeDesc","workLetCategDetailDTO.woLetCtgTypeId","WOLETCTG_TYPE");
   
    //사용여부 자동완성
    acSysDesc("workLetCategDetailDTO.isUseDesc","workLetCategDetailDTO.isUseId","IS_USE", true);
    
    /** 부서 */
    deptAc = new autoC({"workLetCategDetailDTO.deptIdDesc":"description"});
    //로그인 유저의 회사 정보 셋팅
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setKeyName("workLetCategDetailDTO.deptId");
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workLetCategDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    /** 작성자  */
    empAc = new autoC({"workLetCategDetailDTO.empIdDesc":"emp_name"});
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
       });
    empAc.setTable("TAEMP");
    empAc.setAcDConditionMap({
    	"dept_id" : "workLetCategDetailDTO.deptId"
    });
    empAc.setKeyName("workLetCategDetailDTO.empId");
    empAc.setAcResultMap({
        "workLetCategDetailDTO.empId":"emp_id"
    });
    empAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else {
		goUpdate();
	}
    
}

/**
 * 입력
 */
function goInput()
{
	//DB에서 설정한 시퀀스 가져옴
    sequenceNextVal('SQAWOLETCTG_ID');
    workLetCategDetailForm.elements['workLetCategDetailDTO.isUseId'].value = 'Y';
    workLetCategDetailForm.elements['workLetCategDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('workLetCategDetailDTO.isUseId', 'workLetCategDetailDTO.isUseDesc', 'IS_USE', true);
}


function setSequenceVal(sequenceVal)
{	
    workLetCategDetailForm.elements['workLetCategDetailDTO.woLetCtgId'].value = sequenceVal;
    workLetCategDetailForm.elements['workLetCategDetailDTO.woLetCtgNo'].value = sequenceVal;
    workLetCategDetailForm.elements['workLetCategCommonDTO.woLetCtgId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{
	
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	goCommonTabPage(workLetCategDetailForm, '', pageId);
	
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
    if(ckCreate(currentPageId)) workLetCategDetailForm.strutsAction.value = "<%=WorkLetCategDetailAction.DETAIL_INPUT%>";
    else workLetCategDetailForm.strutsAction.value = "<%=WorkLetCategDetailAction.DETAIL_UPDATE%>";

    	//console.log(workLetCategDetailForm);
		var actionUrl = contextPath + "/workLetCategDetail.do";
		
		XMLHttpPost(actionUrl, FormQueryString(workLetCategDetailForm), 'afterSave');

	}

/**
 * 저장후 호출
 */

function afterSave(ajaxXmlDoc)

{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)
	parent.findGridRow(workLetCategDetailForm.elements['workLetCategDetailDTO.woLetCtgId'].value);

	workLetCategDetailForm.elements['workLetCategDetailDTO.woLetCtgId'].value = workLetCategDetailForm.elements['workLetCategCommonDTO.woLetCtgId'].value;
	getTopPage().afterSaveAll(currentPageId);
		
	setTitle("workLetCategDetailDTO.woLetCtgNo", "workLetCategDetailDTO.woLetCtgTypeDesc");

}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workLetCategDetailForm.elements['workLetCategDetailDTO.woLetCtgId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/workLetCategDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="workLetCategCommonDTO.woLetCtgId" /><!-- Key -->
		<html:hidden property="workLetCategDetailDTO.woLetCtgId" /><!-- Key -->
		<html:hidden property="workLetCategDetailDTO.woLetCtgTypeId" />
		<html:hidden property="workLetCategDetailDTO.deptId" />
		<html:hidden property="workLetCategDetailDTO.isUseId" />
		<html:hidden property="workLetCategDetailDTO.empId" />

		<div class="article_box">
			<div class="form_box">
				<!-- No -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.docCountrNo" /></label>
					<div class="input_read">
						<html:text property="workLetCategDetailDTO.woLetCtgNo"
							readonly="true" tabindex="10" />
					</div>
				</div>
				<!-- 안전작업유형 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.woLetCtgType" /></label>
					<div class="input_box">
						<html:text property="workLetCategDetailDTO.woLetCtgTypeDesc"
							tabindex="20" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.docDesc" /></label>
					<div class="input_box">
						<html:text property="workLetCategDetailDTO.description"
							tabindex="30" />
					</div>
				</div>
				<!-- 작성부서 -->
				<div class="field">
					<label><bean:message key="LABEL.regDept" /></label>
					<div class="input_box">
						<html:text property="workLetCategDetailDTO.deptIdDesc"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="workLetCategDetailDTO.isUseDesc"
							tabindex="50" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy" /></label>
					<div class="input_box">
						<html:text property="workLetCategDetailDTO.empIdDesc"
							tabindex="60" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 작성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.repRegDate" /></label>
					<div class="input_box input_carendar">
						<html:text property="workLetCategDetailDTO.regDate"
							tabindex="70" />
						<p class="open_calendar">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="workLetCategDetailDTO.remark" styleClass="ta50" tabindex="80" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
