<%--===========================================================================
요청접수 (상세) - 투자요청
author  js.lee
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
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.req.work.action.MaWoReqDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='LABEL.woReqNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">

var recDeptAc;
var recWkCtrDescAc;
var recEmpAc;
var equipDescAc;
var eqLocDescAc;
var plantAc;

function loadPage() 
{
	setTitle("maWoReqDetailDTO.woReqNo", "maWoReqDetailDTO.reqDesc");
	setForUpdate();
	
	/** 투자구분 */
	acSysDesc("maWoReqDetailDTO.invtCategDesc","maWoReqDetailDTO.invtCateg","INVT_CATEG");
	/** 분류 */
	acSysDesc("maWoReqDetailDTO.invtTypeDesc","maWoReqDetailDTO.invtType","INVT_TYPE");
	
    /** 처리부서 */
    recDeptAc = new autoC({"maWoReqDetailDTO.recDeptDesc":"description"});
    recDeptAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        'IS_LOWEST_LVL':"Y"
       });
    recDeptAc.setTable("TADEPT");
    recDeptAc.setKeyName("maWoReqDetailDTO.recDeptId");
    recDeptAc.setAcResultMap({
        "maWoReqDetailDTO.recDeptId":"dept_id"
    });
    recDeptAc.init();
    
    /**처리작업그룹  */
    recWkCtrDescAc = new autoC({"maWoReqDetailDTO.recWkCtrDesc":"description"});
    recWkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    recWkCtrDescAc.setTable("TAWKCTR");
    recDeptAc.setKeyName("maWoReqDetailDTO.recWkCtrId");
    recWkCtrDescAc.setAcResultMap({
        "maWoReqDetailDTO.recWkCtrId":"wkctr_id"
    });
    recWkCtrDescAc.init();
    
    /** 처리담당자 */
    recEmpAc = new autoC({"maWoReqDetailDTO.recEmpName":"emp_name"});
    recEmpAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
       });
    recEmpAc.setTable("TAEMP");
    recEmpAc.setKeyName("maWoReqDetailDTO.recEmpId");
    recEmpAc.setAcResultMap({
        "maWoReqDetailDTO.recEmpId":"emp_id"
        ,"maWoReqDetailDTO.recDeptId":"dept_id"
        ,"maWoReqDetailDTO.recDeptDesc":"deptDesc"
    });
    recEmpAc.init();
    
    /**설비  */
    equipDescAc = new autoC({"maWoReqDetailDTO.reqEquipDesc":"description"});
    equipDescAc.setKeyName("maWoReqDetailDTO.reqEquipId");
    equipDescAc.setAcConditionMap({
 	   "a.comp_no":loginUser.compNo,
 	   "CUSTOM":"EQ_STATUS IN('R','S')"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maWoReqDetailDTO.reqEquipId":"equip_id"
        ,"maWoReqDetailDTO.reqEqLocId":"eqloc_id"
        ,"maWoReqDetailDTO.reqEqLocDesc":"eqLocDesc"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoReqDetailDTO.reqEqLocId"
    });
    equipDescAc.init();
    
    /*설비위치  */
	eqLocDescAc = new autoC({"maWoReqDetailDTO.reqEqLocDesc":"full_desc"});
    eqLocDescAc.setKeyName("maWoReqDetailDTO.reqEqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoReqDetailDTO.reqEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    /** 공장 */
    plantAc = new autoC({"maWoReqDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maWoReqDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maWoReqDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
    
    
}
	
function goTabPage(pageId)
{
	var form = document.maWoReqDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "reqInvRecWorkDocLibList")
	{	
		maWoReqDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value;
		maWoReqDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOREQ"; 
		//maWoReqDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//maWoReqDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		maWoReqDetailForm.elements['maDocLibCommonDTO.description'].value = 
			maWoReqDetailForm.elements['maWoReqDetailDTO.reqDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maWoReqDetailForm.elements['appReqCommonDTO.objectId'].value = maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value;
		maWoReqDetailForm.elements['appReqCommonDTO.apprType'].value = "REQWORK";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);  
}

function goInput()
{
	sequenceNextVal('SQAWOREQ_ID');
	
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value   = 'REQ';
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusDesc'].value = 'REQ';
	valSysDir('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);

	maWoReqDetailForm.elements['maWoReqDetailDTO.reqDate'].value   = getToday();
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqEmpId'].value   = loginUser.empId;
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqDeptId'].value   = loginUser.deptId;
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqEmpDesc'].value   = loginUser.empName+"/"+loginUser.deptDesc;
}

function setSequenceVal(sequenceVal)
{
	maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value = sequenceVal;
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value = sequenceVal;
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqNo'].value = sequenceVal;
}

function goUpdate()
{
	if("REV"==maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value){
		$(".b_receive").show();
		$(".b_cantwork").show();
	}
	else if("REQ"==maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value)
	{
		showBtn("RECEIVE");
		showBtn("CANTWORK");
	}
	else {
		$(".b_receive").hide();
		$(".b_cantwork").hide();
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
	if(ckCreate(currentPageId)) maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.DETAIL_INSERT%>';
	else maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/reqInvRecWorkDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
}
 
 function changStatus(_resStatus){
		maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value   = _resStatus;
		maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusDesc'].value = _resStatus;
		valSysDir('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
		
		if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);
 }
 
 
 /**
 * 작업불가
 */
 function goCantwork(){
	 if(checkIsUpdate(document)){
	        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
	    }
	else {
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0196'/>", function(result){
			 if(result){
				 maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_INC_STATUS_UPDATE%>';
				var actionUrl = contextPath + "/reqInvRecWorkDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterCantwork');
				}
			});
	}
 }
 /**
  * 작업불가 후 호출
  */
 function afterCantwork(ajaxXmlDoc)
 {
 	//=====================================
 	if (!checkHttpXml(ajaxXmlDoc)) return;
 	//=====================================
 	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);

 	$(".b_receive").hide();
 	$(".b_cantwork").hide();
 	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value = "INC";
 	valSysDirCode('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
 	getTopPage().afterSaveAll(currentPageId);
 	//작업불가처리 되었습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0244'/>");
 }
 /**
  * 접수
  */
function goReceive()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }
	else {
		maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_STATUS_UPDATE%>';

		var actionUrl = contextPath + "/reqInvRecWorkDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterReceive');
	}
}
/**
 * 접수후 호출
 */
function afterReceive(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);

	$(".b_receive").hide();
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value = "REC";
	valSysDirCode('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
	getTopPage().afterSaveAll(currentPageId);
	//접수되었습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0243'/>");
}

/*
 * 의뢰서 출력
 */
function goReqpdfLink()
{
	var woReqId = maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value;
	
	reportCall('maWoReqDetail','maWoReqDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",woReqId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/reqInvRecWorkDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoReqCommonDTO.woReqId" />
	<html:hidden property="maWoReqDetailDTO.woReqId" />
	<html:hidden property="maWoReqDetailDTO.woReqStatusId" />
	<html:hidden property="maWoReqDetailDTO.reqDeptId" />
	<html:hidden property="maWoReqDetailDTO.reqEmpId" />
	<html:hidden property="maWoReqDetailDTO.reqEqLocId" />
	<html:hidden property="maWoReqDetailDTO.reqEquipId" />
 	<html:hidden property="maWoReqDetailDTO.recDeptId" />
 	<html:hidden property="maWoReqDetailDTO.recWkCtrId" />
 	<html:hidden property="maWoReqDetailDTO.recEmpId" />
 	<html:hidden property="maWoReqDetailDTO.reqPriorityId" />
 	<html:hidden property="maWoReqDetailDTO.eqClassId" />
 	<html:hidden property="maWoReqDetailDTO.invtCateg"/>
	<html:hidden property="maWoReqDetailDTO.invtType"/>
	<html:hidden property="maWoReqDetailDTO.plantId"/>
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 	
    <div class="article_box">
		<div class="form_box">
			<!-- 요청No -->
			<div class="field">
				<label><bean:message key="LABEL.woReqNo"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.woReqNo" readonly="true"/>
				</div>
			</div>
			<!-- 요청상태 -->
			<div class="field">
				<label><bean:message key="LABEL.apprStatus"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.woReqStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 요청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqDate"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqDate" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqBy"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqEmpDesc" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 전화번호-->
			<div class="field">
				<label><bean:message key="LABEL.woReqPhone"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqPhone" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 이메일-->
			<div class="field">
				<label><bean:message key="LABEL.woReqEmail"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqEmail" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 투자구분 -->
            <div class="field">
                <label><bean:message key="LABEL.invtCategDesc"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.invtCategDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 분류 -->
            <div class="field">
                <label><bean:message key="LABEL.category"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.invtTypeDesc" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqDesc" tabindex="50" readonly="true"/>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqEquipDesc" tabindex="30"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqEqLocDesc" tabindex="40"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 처리부서 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqDept"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recDeptDesc" tabindex="60" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            <!-- 처리작업그룹 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqWkCtr"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recWkCtrDesc" tabindex="70"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
            <!-- 처리담당자 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqEmp"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recEmpName" tabindex="80"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
			<!-- 완료희망일 -->
			<div class="field">
				<label><bean:message key="LABEL.reqComDate"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqComDate" tabindex="90"  readonly="true"/>
				</div>
			</div>
            <!-- 우선순위 -->
            <div class="field">
                <label><bean:message key="LABEL.reqPriority"/></label>
                <div class="input_read">
                    <html:text property="maWoReqDetailDTO.reqPriorityDesc" tabindex="100" readonly="true"/>
                </div>
            </div>
            
			<!-- 요청내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.request"/></label>
				<div class="input_read">
					<html:textarea property="maWoReqDetailDTO.reqRequest" styleClass="ta150" tabindex="90" readonly="true"/>
				</div>
			</div>
			<!-- 검토내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.reviewDesc"/></label>
				<div class="input_box">
					<html:textarea property="maWoReqDetailDTO.review" styleClass="ta150" tabindex="100"/>
				</div>
			</div>
			<!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>