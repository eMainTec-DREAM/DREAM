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

<%@ page import="dream.work.pm.check.action.WorkPmCheckDetailAction" %>

<html:html>
<head>
<!-- 표준점검항목 디테일 -->
<title><bean:message key='LABEL.process' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var partAc;
var acSysDescAc;
var plantAc;
var uomAc;

function loadPage() 
{	
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("workPmCheckDetailDTO.checkPointNo", "workPmCheckDetailDTO.checkPoint");
    
    //For Save
    setForUpdate();
    
    //표준점검분류 자동완성
    acSysDescAc = acSysDesc("workPmCheckDetailDTO.checkPointTypeDesc","workPmCheckDetailDTO.checkPointTypeId","CHECK_POINT_TYPE", true);
    
    //사용여부 자동완성
    acSysDesc("workPmCheckDetailDTO.isActive","workPmCheckDetailDTO.isActive","IS_USE", true);
    
    // 부품코드 자동완성 
    partAc = new autoC({"workPmCheckDetailDTO.partNo":"part_no"});
    partAc.setTable("TAPARTS");
    partAc.setKeyName("workPmCheckDetailDTO.partNo");
    partAc.setAcResultMap({
        "workPmCheckDetailDTO.partId":"part_id"
      , "workPmCheckDetailDTO.partDesc":"description"
    });
    partAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partAc.init();
    
    // 공장 자동완성
    plantAc = new autoC({"workPmCheckDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("workPmCheckDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPmCheckDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    //단위
    uomAc = new autoC({"workPmCheckDetailDTO.uom":"description"});
    uomAc.setAcConditionMap({
    	"is_use":"Y"
    	,"dir_type":"UOM"
    	,"comp_no":loginUser.compNo
  	   });
    uomAc.setTable("TACDUSRD");
    uomAc.init();
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
	setDisable(document.getElementsByName("disableDiv"));
	
    sequenceNextVal('SQASTD_CHECK_POINT_ID');

    workPmCheckDetailForm.elements['workPmCheckDetailDTO.isActive'].value = "Y";
    valSysDir('workPmCheckDetailDTO.isActive', 'workPmCheckDetailDTO.isActive', 'IS_USE', true);

	setInitVal('workPmCheckDetailDTO.plantId', loginUser.plant);
	setInitVal('workPmCheckDetailDTO.plantDesc', loginUser.plantDesc);
    
    acSysDescAc.openLov();
}

function setSequenceVal(sequenceVal)
{
    workPmCheckDetailForm.elements['workPmCheckDetailDTO.checkPointId'].value = sequenceVal;
    workPmCheckDetailForm.elements['workPmCheckCommonDTO.checkPointId'].value = sequenceVal;
    workPmCheckDetailForm.elements['workPmCheckDetailDTO.checkPointNo'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
    setEnableAll();
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

    if(ckCreate(currentPageId)) workPmCheckDetailForm.strutsAction.value = "<%=WorkPmCheckDetailAction.DETAIL_INPUT%>";
    else workPmCheckDetailForm.strutsAction.value = "<%=WorkPmCheckDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/workPmCheckDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(workPmCheckDetailForm),'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow) {
        parent.findGridRow(workPmCheckDetailForm.elements['workPmCheckDetailDTO.checkPointId'].value);
    }
    workPmCheckDetailForm.elements['workPmCheckCommonDTO.checkPointId'].value = workPmCheckDetailForm.elements['workPmCheckDetailDTO.checkPointId'].value;
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workPmCheckDetailDTO.checkPointNo", "workPmCheckDetailDTO.checkPoint");

}

function goTabPage(pageId) 
{
    var form = document.workPmCheckDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmCheckDetailForm.elements['workPmCheckDetailDTO.checkPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/workPmCheckDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="workPmCheckCommonDTO.checkPointId" />          <!-- Key -->
        <html:hidden property="workPmCheckDetailDTO.checkPointId" />          <!-- Key -->
        <html:hidden property="workPmCheckDetailDTO.checkPointTypeId"/>    
        <html:hidden property="workPmCheckDetailDTO.partId" />    
        <html:hidden property="workPmCheckDetailDTO.plantId" />    
                
        <div class="article_box">
            <div class="form_box">
                <!-- 번호 -->
                <div class="field" name="disableDiv">
                    <label class="check">번호</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.checkPointNo" tabindex="10" />
                    </div>
                </div>
                <!-- 항목구분 -->
                <div class="field">
                    <label class="check">항목구분</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.checkPointTypeDesc" tabindex="20"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 측정분류항목 -->
                <div class="field">
                    <label class="check">측정분류항목</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.checkPoint" tabindex="30"/>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label>공장</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.plantDesc" tabindex="100"/>
						<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 단위 -->
                <div class="field">
                    <label>단위</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.uom" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 배율값 -->
                <div class="field">
                    <label>배율값</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.bvalue" tabindex="90" styleClass="num"/>
                    </div>
                </div>
                <!-- 부품코드 -->
                <div class="field">
                    <label>부품코드</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.partNo" tabindex="70"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 부품명/규격 -->
                <div class="field" name="disableDiv">
                    <label>부품명/규격</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.partDesc" tabindex="80"/>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label>사용여부</label>
                    <div class="input_box">
                        <html:text property="workPmCheckDetailDTO.isActive" tabindex="60"/>
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
                        <html:textarea property="workPmCheckDetailDTO.remark" styleClass="ta50" tabindex="100" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
