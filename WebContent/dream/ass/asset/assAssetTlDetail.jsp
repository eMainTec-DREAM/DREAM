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
<%@ page import="dream.ass.asset.action.AssAssetDetailAction" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 계측기위험도평가 디테일 -->
<title><bean:message key='LABEL.docCountrNo' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

var empAc, equipAc, assBaseListAc, plantAc;

function loadPage() 
{	
	assAssetDetailForm.elements['assAssetCommonDTO.eqCtgType'].value = "TL";
	
    setDisable(document.getElementsByName("disableDiv"));
    
    setTitle("assAssetDetailDTO.eqasslistNo", "assAssetDetailDTO.equipDesc");
    //For Save
    setForUpdate();
    
    //사원 자동완성
    empAc = new autoC({"assAssetDetailDTO.empName":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
     });
    empAc.setAcResultMap({
        "assAssetDetailDTO.empId":"emp_id"
    });
    empAc.setKeyName("assAssetDetailDTO.empId");
    empAc.init();
    
    
    // 계측기 자동완성 
    equipAc = new autoC({"assAssetDetailDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("assAssetDetailDTO.equipId");
	equipAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
		"eqctg_type" : "TL"
    });
	equipAc.setAcResultMap({
        "assAssetDetailDTO.equipId":"equip_id"
        , "assAssetDetailDTO.parEquipDesc":"parEquipDesc"
        , "assAssetDetailDTO.parEqLocDesc":"parEqLocDesc"
    });
    equipAc.init();
    
    // 평가등급 기준표 자동완성
    assBaseListAc = new autoC({"assAssetDetailDTO.assBaseListDesc":"assBaseListDesc"});
    assBaseListAc.setTable("TAASSBASE");
    assBaseListAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
    })
    assBaseListAc.setAcResultMap({
        "assAssetDetailDTO.assBaseListId":"assBaseListId"
    });
    assBaseListAc.setKeyName("assAssetDetailDTO.assBaseListId");
    assBaseListAc.init();
    
    //공장 
    plantAc = new autoC({"assAssetDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("assAssetDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assAssetDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    
    acSysDesc("assAssetDetailDTO.pmiActionTypeDesc","assAssetDetailDTO.pmiActionType","PMI_ACTION_TYPE", true);
    acSysDesc("assAssetDetailDTO.assTypeDesc","assAssetDetailDTO.assTypeId","EQASSLIST_TYPE", true);
    
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
	setEnableAll();
    sequenceNextVal('SQAEQASSLIST_ID');

    assAssetDetailForm.elements['assAssetDetailDTO.eqasslistStatusId'].value = "W";
    assAssetDetailForm.elements['assAssetDetailDTO.eqasslistStatusDesc'].value = "W";
    valSysDir('assAssetDetailDTO.eqasslistStatusId', 'assAssetDetailDTO.eqasslistStatusDesc', 'EQASSLIST_STATUS', true);
    
    assAssetDetailForm.elements['assAssetDetailDTO.assTypeId'].value = "LT";
    assAssetDetailForm.elements['assAssetDetailDTO.assTypeDesc'].value = "LT";
    valSysDir('assAssetDetailDTO.assTypeId', 'assAssetDetailDTO.assTypeDesc', 'EQASSLIST_TYPE', true);
    
 	//담당자 자동완성 (로그인 유저)
	assAssetDetailForm.elements['assAssetDetailDTO.empId'].value = loginUser.empId;
	assAssetDetailForm.elements['assAssetDetailDTO.empName'].value   = loginUser.empName;
		
	assBaseListAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	
	//생성일 때만 활성화
//    setEnable(document.getElementById("assBaseListDiv"));  
	
    assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value = sequenceVal;
    assAssetDetailForm.elements['assAssetCommonDTO.eqasslistId'].value = sequenceVal;
    assAssetDetailForm.elements['assAssetDetailDTO.eqasslistNo'].value = sequenceVal;
    
    assAssetDetailForm.elements['assAssetDetailDTO.assDate'].value = getToday()
}
/**
 * 수정
 */
function goUpdate()
{
	if("C"==assAssetDetailForm.elements['assAssetDetailDTO.eqasslistStatusId'].value){
		setDisableAll();
	}else{
		setEnableAll();
	}
}

/**
 * 요청
 */
 
//신청완료
function goAsscompleted(){

	// 하위항목 작성 여부 체크
	completeTab();
    if(isComplete!='1') return;    
    
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
            if(result){
                   //================================
                   // 필수 항목 체크한다.
                   //================================
                   if(checkValidation()) return;

                   assAssetDetailForm.strutsAction.value = '<%=AssAssetDetailAction.DETAIL_ASSCOMPLETED%>';
                   
                   var actionUrl = contextPath + "/assAssetTlDetail.do";
                   XMLHttpPost(actionUrl, FormQueryString(assAssetDetailForm), 'afterAsscompleted');
                   
                   }
           });
    }
}
	
/**
  * 평가 완료 후 호출
  */
 function afterAsscompleted(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     assAssetDetailForm.elements['assAssetCommonDTO.eqasslistId'].value = assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value;
     if (parent.findGridRow)    parent.findGridRow(assAssetDetailForm.elements['assAssetCommonDTO.eqasslistId'].value);
     
     // 등급 update
     gradeUpdate();
         
     // 점수 update
     scoreUpdate();
     
     //상태 = C - 평가완료
     assAssetDetailForm.elements['assAssetDetailDTO.eqasslistStatusId'].value = "C";
     assAssetDetailForm.elements['assAssetDetailDTO.eqasslistStatusDesc'].value = "C";
     valSysDirCode('assAssetDetailDTO.eqasslistStatusId', 'assAssetDetailDTO.eqasslistStatusDesc', 'EQASSLIST_STATUS', true);
     setDisableAll();
     getTopPage().afterSaveAll(currentPageId);
     
     //계측기에 붙은 탭인경우 교정주기 탭 refresh
     if("maEqToolMstrDetail"==parent.parent.currentPageId)
	 {
    	 console.log('계측기 상세 업데이트됨.');
    	 parent.parent.frames["tabFrameTAB.assetListTcycleList"].goSearch();
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
    
  //strutsAction 셋팅.

    if(ckCreate(currentPageId)) assAssetDetailForm.strutsAction.value = "<%=AssAssetDetailAction.DETAIL_INPUT%>";
    else assAssetDetailForm.strutsAction.value = "<%=AssAssetDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/assAssetTlDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(assAssetDetailForm),'afterSave');

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
        parent.findGridRow(assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value);

    assAssetDetailForm.elements['assAssetCommonDTO.eqasslistId'].value = assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("assAssetDetailDTO.eqasslistId", "assAssetDetailDTO.equipDesc");
    
    var ifmCnt = getParentIframe("assAssetTlScoreList", document);
    if(ifmCnt && !ifmCnt.curPageUpdate) getParentIframe("assAssetTlScoreList", document).goSearch();
    
    var ifmCnt2 = getParentIframe("assAssetScoreList", document);
    if(ifmCnt2 && !ifmCnt2.curPageUpdate) getParentIframe("assAssetScoreList", document).goSearch();

}

function goTabPage(pageId) 
{
    var form = document.assAssetDetailForm;
    goCommonTabPage(form, '' , pageId);
       
}

// 하위항목 작성 체크
function completeTab(){
    var actionUrl = contextPath + "/assAssetTlDetail.do";
    var param =  "&strutsAction=" + '<%= AssAssetDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(assAssetDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterCompleteTab');
}

var isComplete;
function afterCompleteTab(ajaxXmlDoc)
{
	isComplete = '0';
	isComplete = parseXmlDoc(ajaxXmlDoc, 'DESC');
    if(isComplete != '1')
    {
        closeModal();
        alertMessage1("<bean:message key='MESSAGE.writeScoreFirst'/>");
    }
}

// 등급 가져오기
function gradeUpdate(){
    var actionUrl = contextPath + "/assAssetTlDetail.do";
    var param =  "&strutsAction=" + '<%= AssAssetDetailAction.GRADE_UPDATE %>'
              +  "&" + FormQueryString(assAssetDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterGradeUpdate');
}

var grade;
function afterGradeUpdate(ajaxXmlDoc)
{
    grade = '0';
    grade = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(grade != '0')
    {
        closeModal();
        assAssetDetailForm.elements['assAssetDetailDTO.eqGradeId'].value = grade;
        assAssetDetailForm.elements['assAssetDetailDTO.eqGradeDesc'].value = grade;

        valSysDir('assAssetDetailDTO.eqGradeId', 'assAssetDetailDTO.eqGradeDesc', 'EQ_GRADE', true);
        
        var ifmCnt = searchPage("maEqToolMstrDetail");
        if(ifmCnt) {
        	ifmCnt.M$('maEqMstrDetailDTO.eqGradeId').value = grade;
        	ifmCnt.M$('maEqMstrDetailDTO.eqGradeDesc').value = grade;
        	ifmCnt.valSysDir('maEqMstrDetailDTO.eqGradeId', 'maEqMstrDetailDTO.eqGradeDesc', 'EQ_GRADE', true);
        }
    }
}


//점수 가져오기
function scoreUpdate(){
    var actionUrl = contextPath + "/assAssetTlDetail.do";
    var param =  "&strutsAction=" + '<%= AssAssetDetailAction.SCORE_UPDATE %>'
              +  "&" + FormQueryString(assAssetDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterScoreUpdate');
}

var score;
function afterScoreUpdate(ajaxXmlDoc)
{
	score = '0';
	score = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(score != '0')
    {
        closeModal();
        assAssetDetailForm.elements['assAssetDetailDTO.evalValue'].value = score;
    }
}

function goAudtrailLink()
{
	var objectId = assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 위험분석출력
 */
function goRaprintLink()
{
	var eqasslistId = assAssetDetailForm.elements['assAssetDetailDTO.eqasslistId'].value;
	
	reportCall('assAssetTlDetail','assAssetTlDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",eqasslistId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/assAssetDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="currentPageId"/>
        <html:hidden property="assAssetCommonDTO.eqasslistId" />          <!-- Key -->
        <html:hidden property="assAssetDetailDTO.eqasslistId" />          <!-- Key -->
        <html:hidden property="assAssetDetailDTO.pmiActionType" />
        <html:hidden property="assAssetDetailDTO.eqasslistStatusId" />    <!-- 상태ID -->
        <html:hidden property="assAssetDetailDTO.assBaseListId" />        <!-- 평가등급 기준표 ID -->
        <html:hidden property="assAssetDetailDTO.eqGradeId" />            <!-- 최종 평가등급 ID -->
        <html:hidden property="assAssetDetailDTO.parEqLocId" />           <!-- 상위설비위치ID -->
        <html:hidden property="assAssetDetailDTO.equipId" />              <!-- 설비 ID -->
        <html:hidden property="assAssetDetailDTO.parEquipId" />              <!-- 설비 ID -->
        <html:hidden property="assAssetDetailDTO.empId" />                <!-- 담당자ID -->
        <html:hidden property="assAssetCommonDTO.eqCtgType" />
        <html:hidden property="assAssetDetailDTO.plantId" />              <!-- 공장ID -->
        <html:hidden property="assAssetDetailDTO.assTypeId" />            <!-- 평가구분ID -->
        
        
        <div class="article_box">
            <div class="form_box">
                <!-- 계측기위험도평가# -->
                <div class="field"  name="disableDiv">
                    <label><bean:message key="LABEL.eqAssTlNo"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.eqasslistNo" tabindex="10"/>
                    </div>
                </div>
                <!-- 상태 -->
                <div class="field" name="disableDiv">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.eqasslistStatusDesc" tabindex="20"/>
                    </div>
                </div>
                <!-- 상위설비위치 -->
                <div class="field_long" name="disableDiv">
                    <label><bean:message key="LABEL.location"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.parEqLocDesc" tabindex="30"/>
                    </div>
                </div>
                <!-- 상위설비 -->
                <div class="field" name="disableDiv">
                    <label><bean:message key="LABEL.equipment"/></label>
                    <div class="input_read">
                        <html:text property="assAssetDetailDTO.parEquipDesc" tabindex="40" readonly="true"/>
                    </div>
                </div>
                <!-- 계측기 -->
                <div class="field" id="equipDescDiv">
                    <label class="check"><bean:message key="LABEL.eqTool"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.equipDesc" tabindex="45" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="assAssetDetailDTO.plantDesc" tabindex="43"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 평가등급기준표 -->
                <div class="field" id ="assBaseListDiv">
                    <label class="check"><bean:message key="LABEL.assGradeBaseTable"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.assBaseListDesc" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.empName" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 평가구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.assType"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.assTypeDesc" tabindex="70"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 최종평가등급 Desc -->
                <div class="field" name="disableDiv">
                    <label><bean:message key="LABEL.finalEqGrade"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.eqGradeDesc" tabindex="70"/>
                    </div>
                </div>
                <!-- 평가점수 -->
                <div class="field" name="disableDiv">
                    <label><bean:message key="LABEL.assScore"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.evalValue" tabindex="80" styleClass="num"/>
                    </div>
                </div>
                <!-- 평가일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.assDate"/></label>
                    <div class="input_box">
                        <html:text property="assAssetDetailDTO.assDate" tabindex="90"/>
                        <p class="open_calendar">
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
                        <html:textarea property="assAssetDetailDTO.remark" styleClass="ta50" tabindex="100" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>
