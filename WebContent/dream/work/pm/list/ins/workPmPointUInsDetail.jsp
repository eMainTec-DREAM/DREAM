<%--===========================================================================
사용량 항목  Detail
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmPointUInsDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 측정 Point -->
<title><bean:message key='PAGE.workPmPointUInsDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var uomAc;
var stdCheckPointAc;

function loadPage() 
{

    setTitle("workPmPointUInsDetailDTO.checkPoint");
   
    //For Save
    setForUpdate();
    
    
	if(ckCreate(currentPageId)) goInput();
	
    // 단위
    uomAc = new autoC({"workPmPointUInsDetailDTO.uom":"description"});
    uomAc.setTable("TACDUSRD");
    uomAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"UOM"
  	   });
    uomAc.init();
    
    //표준점검항목 AC 
    stdCheckPointAc = new autoC({"workPmPointUInsDetailDTO.stdCheckPointDesc":"check_point"});
    stdCheckPointAc.setTable("TASTDCHECKPOINT");
    stdCheckPointAc.setKeyName("workPmPointUInsDetailDTO.stdCheckPointId");
    stdCheckPointAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    stdCheckPointAc.setAcResultMap({
        "workPmPointUInsDetailDTO.stdCheckPointId":"std_check_point_id"
    });
    stdCheckPointAc.init();
    
  	// 시행여부  AC
    acSysDesc("workPmPointUInsDetailDTO.isActive","workPmPointUInsDetailDTO.isActive","IS_USE",true);
	// 누적값 여부  AC
    acSysDesc("workPmPointUInsDetailDTO.isRunValue","workPmPointUInsDetailDTO.isRunValue","IS_USE",true);
	
}

/**
 * 입력
 */
function goInput()
{
	// 시퀀스를 조회한다.
    sequenceNextVal('SQAPM_POINT_ID');    
    workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.isActive'].value='Y';
    workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.isRunValue'].value='N';
    workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.fitRate'].value = 1;
    workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.pmId'].value = workPmPointUInsDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
    
    // 측정순서는 10씩 증가하여 셋팅, 최초는 10
    setStepNum();
    
}

function setStepNum(){
	var actionUrl = contextPath + "/workPmPointUInsDetail.do";
	var param =  "&strutsAction=" + '<%= WorkPmPointUInsDetailAction.SET_STEP_NUM %>'
	          +  "&" + FormQueryString(workPmPointUInsDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterSetStepNum');
}

function afterSetStepNum(ajaxXmlDoc){
	var stepNum = '0';
	stepNum = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(stepNum != '0')
    {
		workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.stepNum'].value = stepNum;
    }
}

function setSequenceVal(sequenceVal)
{
    workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.pmPointId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) workPmPointUInsDetailForm.strutsAction.value = "<%=WorkPmPointUInsDetailAction.DETAIL_INPUT%>";
    else workPmPointUInsDetailForm.strutsAction.value = "<%=WorkPmPointUInsDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/workPmPointUInsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmPointUInsDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) 
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.pmPointId'].value);

	getTopPage().afterSaveAll(currentPageId);
	setTitle("workPmPointUInsDetailDTO.checkPoint"); 

}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workPmPointUInsDetailForm.elements['workPmPointUInsDetailDTO.pmPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workPmPointUInsDetail.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maPmMstrCommonDTO.pmId" />
		<html:hidden property="workPmPointUInsDetailDTO.pmPointId" />
		<html:hidden property="workPmPointUInsDetailDTO.pmId" />
		<html:hidden property="workPmPointUInsDetailDTO.stdCheckPointId" />

		<div class="article_box">
			<div class="form_box">
				<!-- 측정순서 -->
				<div class="field">
					<label class="check">측정순서</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.stepNum" tabindex="10" styleClass="num"/>
					</div>
				</div>
                <!-- 측정분류항목 -->
                <div class="field">
                    <label class="check">측정분류항목</label>
                    <div class="input_box">
                        <html:text property="workPmPointUInsDetailDTO.stdCheckPointDesc" tabindex="30"/>
                        <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
                    </div>
                </div>
				<!-- 측정 point -->
				<div class="field">
					<label class="check">측정 Point</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.checkPoint" tabindex="15"/>
					</div>
				</div>
				<!-- 단위 -->
				<div class="field">
					<label>단위</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.uom" tabindex="70"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
					</div>
				</div>
				<!-- 배율 -->
				<div class="field">
					<label>배율</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.fitRate" tabindex="80" styleClass="num"/>
					</div>
				</div>	
				<!-- 누적값 여부 -->
				<div class="field">
					<label>누적값 여부</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.isRunValue" tabindex="75"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
					</div>
				</div>
				<!-- 시행여부 -->
				<div class="field">
					<label>시행여부</label>
					<div class="input_box">
						<html:text property="workPmPointUInsDetailDTO.isActive" tabindex="45"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label>비고</label>
					<div class="input_box">
						<html:textarea property="workPmPointUInsDetailDTO.remark" styleClass="ta50" tabindex="85"/>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
