<%--===========================================================================
상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.asset.loc.goal.action.MaLnGoalDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 월별보전목표 -->
<title><bean:message key='LABEL.accntNo' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

/** 자동완성 변수 */
var eqLocAc;
var plantAc;
var goalItemAc;

function loadPage() 
{
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    setTitle("maLnGoalDetailDTO.yyyymm", "maLnGoalDetailDTO.plantDesc");
    
    setForUpdate();

    //설비위치 자동완성
    eqLocAc = new autoC({"maLnGoalDetailDTO.eqlocIdDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setKeyName("maLnGoalDetailDTO.eqlocId");
    eqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcDConditionMap({
    	"plant" : "maLnGoalDetailDTO.plant"
    });
    eqLocAc.setAcResultMap({
        "maLnGoalDetailDTO.eqlocId":"eqloc_id"
    });
    eqLocAc.init();

    //공장 자동완성
    plantAc = new autoC({"maLnGoalDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maLnGoalDetailDTO.plant");
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    plantAc.setAcResultMap({
        "maLnGoalDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    // 목표항목
    goalItemAc = new autoC({"maLnGoalDetailDTO.mtpointDesc":"description"});
    goalItemAc.setAcDisplay("DESCRIPTION");
    goalItemAc.setAcConditionMap({
        	"list_type":"MTLNPOINT",
        	"is_use":"Y"
  	   });
    goalItemAc.setTable("TACDSYSD");
    goalItemAc.setAcResultMap({
        "maLnGoalDetailDTO.mtpoint":"cdsysd_no"
    });
    goalItemAc.init();
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAMTLNPOINT_ID');
	if(loginUser.plant!='null'&&loginUser.plant!=''){
		maLnGoalDetailForm.elements['maLnGoalDetailDTO.plant'].value = loginUser.plant;
		maLnGoalDetailForm.elements['maLnGoalDetailDTO.plantDesc'].value = loginUser.plantDesc;
	}
}

/**
 * 수정
 */
function goUpdate()
{
    setDisable(document.getElementById("yyyyMmDiv"));
    setDisable(document.getElementById("plantDiv"));
    setDisable(document.getElementById("eqLocDiv"));
}

function setSequenceVal(sequenceVal)
{
	maLnGoalDetailForm.elements['maLnGoalDetailDTO.mtLnPointId'].value = sequenceVal;
    maLnGoalDetailForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = sequenceVal;

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
    if(ckCreate(currentPageId)) maLnGoalDetailForm.strutsAction.value = "<%=MaLnGoalDetailAction.MTGOAL_DETAIL_INPUT%>";
    else maLnGoalDetailForm.strutsAction.value = '<%=MaLnGoalDetailAction.MTGOAL_DETAIL_UPDATE%>';

		var actionUrl = contextPath + "/maLnGoalDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maLnGoalDetailForm), 'afterSave');
	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc))
			return;
		//=====================================
		maLnGoalDetailForm.elements['maLnGoalCommonDTO.mtLnPointId'].value = maLnGoalDetailForm.elements['maLnGoalDetailDTO.mtLnPointId'].value;
		if (parent.findGridRow)
			parent
					.findGridRow(maLnGoalDetailForm.elements['maLnGoalCommonDTO.mtLnPointId'].value);
		getTopPage().afterSaveAll(currentPageId);

		goUpdate();
		setTitle("maLnGoalDetailDTO.yyyymm", "maLnGoalDetailDTO.plantDesc");

	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = maLnGoalDetailForm.elements['maLnGoalDetailDTO.mtLnPointId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maLnGoalDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maLnGoalCommonDTO.mtLnPointId" />
		<html:hidden property="maLnGoalDetailDTO.mtLnPointId" />

		<html:hidden property="maLnGoalDetailDTO.plant" />
		<html:hidden property="maLnGoalDetailDTO.eqlocId" />
		<html:hidden property="maLnGoalDetailDTO.mtpoint" />
		<html:hidden property="maLnGoalDetailDTO.eqlocType" value="" />

		<div class="article_box" id="detailBox">
			<div class="form_box">


				<div class="field" id="yyyyMmDiv">
					<label class="check"><bean:message key="LABEL.yyyymm" /></label>
					<div class="input_read">
						<html:text property="maLnGoalDetailDTO.yyyymm" readonly="true"
							tabindex="10" />
						<p class="open_mon_calendar">
							<span>날짜</span>
						</p>
					</div>
				</div>
				<div class="field" id="plantDiv">
					<label class="check"><bean:message key="LABEL.plant" /></label>
					<div class="input_box">
						<html:text property="maLnGoalDetailDTO.plantDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 라인 -->
				<div class="field" id="eqLocDiv">
					<label class="check"><bean:message key="LABEL.eqlocId" /></label>
					<div class="input_box">
						<html:text property="maLnGoalDetailDTO.eqlocIdDesc" tabindex="30" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				
				<div class="field" id="mtPointDiv">
        	 	<label class="check"><bean:message key="LABEL.goalItem"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maLnGoalDetailDTO.mtpointDesc" tabindex="30"
                        onblur="valSysDir('maLnGoalDetailDTO.mtpoint', 'maLnGoalDetailDTO.mtpointDesc', 'MTLNPOINT', true);"/>
                    <p class="open_spop">
                        <a href="javascript:lovSysDir('maLnGoalDetailDTO.mtpoint', 'maLnGoalDetailDTO.mtpointDesc', 'MTLNPOINT');">
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>

				<!-- 목표값 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.goalValue" /></label>
					<div class="input_box">
						<html:text property="maLnGoalDetailDTO.pvalue" tabindex="40"
							styleClass="num" />
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="maLnGoalDetailDTO.remark"
							styleClass="ta50" tabindex="50" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>

</body>
</html:html>