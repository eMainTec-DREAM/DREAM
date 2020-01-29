<%--===========================================================================
점검항목 디테일
author  euna0207
version $Id: maEqCatalogPointDetail.jsp,v 1.0 2015/12/04 07:26:18 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.categ.list.action.MaEqCatalogPointDetailAction"%>
<html>
<head> 
<!--점검항목-->
<title><bean:message key="TAB.maEqMstrPmInsPointList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<script language="javascript">


/** 자동완성 변수 */
var pmAsmbAc;
var uomAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqCatalogPointDetailDTO.eqasmbDesc","maEqCatalogPointDetailDTO.checkPoint");
	setForUpdate();
	
	//점검부위
	pmAsmbAc = new autoC({"maEqCatalogPointDetailDTO.eqasmbDesc":"description"});
	pmAsmbAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	    , "is_use" : "Y"
	  });
	pmAsmbAc.setAcDConditionMap({
    	"eqctg_id":"maEqCatalogCommonDTO.eqCtgId"
    });
	pmAsmbAc.setTable("TAEQCTGASMB");
	pmAsmbAc.setKeyName("maEqCatalogPointDetailDTO.eqasmbId");
	pmAsmbAc.setAcResultMap({
	    "maEqCatalogPointDetailDTO.eqasmbId":"eq_ctg_asmb_id"
	});
	pmAsmbAc.init(); 
	
    //단위
    uomAc = new autoC({"maEqCatalogPointDetailDTO.uom":"description"});
    uomAc.setTable("TACDUSRD");
    uomAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    	,"dir_type":"UOM"
  	   });
    uomAc.init();
    
    //주기
	acSysDesc("maEqCatalogPointDetailDTO.periodTypeDesc","maEqCatalogPointDetailDTO.periodTypeId","PERIOD_TYPE",true);
	//사용여부
	acSysDesc("maEqCatalogPointDetailDTO.isUse","maEqCatalogPointDetailDTO.isUse","IS_USE",true);
	//수치판정
    acSysDesc("maEqCatalogPointDetailDTO.checkTypeDesc","maEqCatalogPointDetailDTO.checkTypeId","CHECK_TYPE",true);
	
    // 공통부위여부
    acSysDesc("maEqCatalogPointDetailDTO.isCommCtgPoint","maEqCatalogPointDetailDTO.isCommCtgPoint","IS_USE",true);

    // 수명확인방법
    acSysDesc("maEqCatalogPointDetailDTO.scheduleTypeDesc","maEqCatalogPointDetailDTO.scheduleTypeId","SCHEDULE_TYPE",true);
    
    // 수명확인방법 hidden control
    checkScheduleType($("input[name='maEqCatalogPointDetailDTO.scheduleTypeId']").val());

}

function checkScheduleType(scheduleType)
{
	var cycleObj 		= $("input[name='maEqCatalogPointDetailDTO.cycle']");		// 예상수명(시간)
	var periodTypeObj	= $("input[name='maEqCatalogPointDetailDTO.periodTypeDesc']");  // 예상수명(시간단위)
	var usageObj		= $("input[name='maEqCatalogPointDetailDTO.usage']");			// 예상수명(사용량,생산량,가동시간)

	if("" == scheduleType)
	{
		cycleObj.parent().parent().parent().hide();
		usageObj.parent().parent().hide();
	}
	else if('T' == scheduleType) 
	{
		cycleObj.parent().parent().parent().show();
		cycleObj.parent().parent().prev().addClass("check");
		usageObj.parent().prev().removeClass("check");
		usageObj.parent().parent().hide();
	}
	else if('R' == scheduleType || 'U' == scheduleType) 
	{
		usageObj.parent().parent().show();
		usageObj.parent().prev().addClass("check");
		cycleObj.parent().parent().prev().removeClass("check");
		cycleObj.parent().parent().parent().hide();
	}
}

function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "maEqCatalogPointDetailDTO.scheduleTypeDesc") {
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	
	sequenceNextVal('SQAEQCTGPMPOINT_ID');
	
	maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.isUse'].value = 'Y';
	maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.isCommCtgPoint'].value = 'N';
    valSysDir('maEqCatalogPointDetailDTO.isUse', 'maEqCatalogPointDetailDTO.isUse', 'IS_USE', true);

}

function setSequenceVal(sequenceVal)
{
	maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.eqCtgPmPointId'].value = sequenceVal;
	maEqCatalogPointDetailForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = sequenceVal;
}


function goSave()
{
    //================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maEqCatalogPointDetailForm.strutsAction.value = "<%=MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_INPUT%>";
    else maEqCatalogPointDetailForm.strutsAction.value = "<%=MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/maEqCatalogPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqCatalogPointDetailForm),'afterSave');

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
	parent.findGridRow(maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.eqCtgPmPointId'].value);

	maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.eqCtgPmPointId'].value = maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.eqCtgPmPointId'].value;
	getTopPage().afterSaveAll(currentPageId);
	//setTitle("maEqCatalogPointDetailDTO.eqCtgPmPointId", "maEqCatalogPointDetailDTO.eqCtgPmPointId");
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = "";
	var fileName = currentPageId;

	if(typeof objectId=="undefined") {
		return;
	} else if (objectId == maEqCatalogPointDetailForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value ||  maEqCatalogPointDetailForm.elements['maEqCatalogPointDetailDTO.eqCtgPmPointId'].value) {
		goAudTrailList(objectId, fileName);
	}
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqCatalogPointDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/>
<html:hidden property="maEqCatalogCommonDTO.eqTypeId"/>
<html:hidden property="maEqCatalogPointListDTO.eqCtgPmPointId"/>
<html:hidden property="maEqCatalogPointListDTO.eqCtgId"/>
<html:hidden property="maEqCatalogPointListDTO.eqasmbId"/>
<html:hidden property="maEqCatalogPointDetailDTO.eqCtgPmPointId"/>
<html:hidden property="maEqCatalogPointDetailDTO.eqCtgId"/>
<html:hidden property="maEqCatalogPointDetailDTO.periodTypeId"/>
<html:hidden property="maEqCatalogPointDetailDTO.eqasmbId"/>
<html:hidden property="maEqCatalogPointDetailDTO.checkTypeId"/>
<html:hidden property="maEqCatalogPointDetailDTO.scheduleTypeId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label>점검순서</label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.stepNum" tabindex="10" styleClass="num"/>
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.eqasmbDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공통점검항목여부 -->
            <div class="field">
                <label>공통점검항목여부</label>
                <div class="input_box">
                    <html:text property="maEqCatalogPointDetailDTO.isCommCtgPoint" tabindex="12"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.isUse" tabindex="15"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.checkPoint" tabindex="20"/>
				</div>
			</div>
			<!-- 수명확인방법 -->
			<div class="field">
				<label>수명확인방법</label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.scheduleTypeDesc" tabindex="42"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 주기 -->
			<div class="field">
				<label><bean:message key="LABEL.cycleDesc"/></label>
				<div class="datetime_wrap">
					<div class="input_box">
						<html:text property="maEqCatalogPointDetailDTO.cycle" tabindex="25" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maEqCatalogPointDetailDTO.periodTypeDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
			<!-- 예상수명(사용량,생산량,가동시간) -->
			<div class="field">
                <label>예상수명</label>
                <div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.usage" tabindex="47" styleClass="num"/>
				</div>
            </div>
			<!--점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.checkMethod" tabindex="35" />
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.fitBasis" tabindex="40" />
				</div>
			</div>			
			<!-- 수치/판정구분 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.checkType"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.checkTypeDesc" tabindex="45"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 하한/기준/상한 -->
			<div class="field ty3-2">
				<label><bean:message key="LABEL.minBasisMax"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<html:text property="maEqCatalogPointDetailDTO.checkMin" tabindex="140" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maEqCatalogPointDetailDTO.checkBasisVal" tabindex="150" styleClass="num"/>
					</div>
					<div class="input_box">
						<html:text property="maEqCatalogPointDetailDTO.checkMax" tabindex="160" styleClass="num"/>
					</div>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label><bean:message key="LABEL.uom"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.uom" tabindex="65"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 예상소요시간(분) -->
			<div class="field">
				<label><bean:message key="LABEL.predTime"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.predTime" tabindex="70" styleClass="num"/>
				</div>
			</div>
			<!-- 정렬값 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqCatalogPointDetailDTO.ordNo" tabindex="75" styleClass="num"/>
				</div>
			</div>			
			<div></div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqCatalogPointDetailDTO.remark" styleClass="ta50" tabindex="80" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>