<%--===========================================================================
설비종류별 부품
author  kim21017
version $Id: maEqCtgPartDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.categ.list.action.MaEqCtgPartDetailAction"%>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogDetailAction"%>
<html>
<head>
<!-- 부품코드-->
<title><bean:message key="LABEL.partCode"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var partNoAc;
var eqCtgAsmbAc;
var isUse;
var isNew = false;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	if(maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.useQty'].value=='')
	maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.useQty'].value = '1';
	
	setTitle("maEqCtgPartDetailDTO.partDesc");
	setForUpdate();
	
	/* $("input[name='maEqCtgPartDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maEqCtgPartDetailDTO.partId','maEqCtgPartDetailDTO.partNo','maEqCtgPartDetailDTO.partDesc', true);
		}
	}); */

    
    partNoAc = new autoC({"maEqCtgPartDetailDTO.partNo":"part_no"});
	partNoAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"
	});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
        "maEqCtgPartDetailDTO.partId":"part_id"
        ,"maEqCtgPartDetailDTO.partDesc":"description"
        ,"maEqCtgPartDetailDTO.ptSize":"pt_size"
        ,"maEqCtgPartDetailDTO.model":"model"
    });
    partNoAc.setKeyName("maEqCtgPartDetailDTO.partId");
    partNoAc.init();
    
    
    eqCtgAsmbAc = new autoC({"maEqCtgPartDetailDTO.eqCtgAsmbDesc":"description"});
    eqCtgAsmbAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"
	});
    eqCtgAsmbAc.setAcDConditionMap({
    	"eqctg_id":"maEqCatalogCommonDTO.eqCtgId"
    });
    eqCtgAsmbAc.setTable("TAEQCTGASMB");
    eqCtgAsmbAc.setKeyName("maEqCtgPartDetailDTO.eqCtgAsmbId");
    eqCtgAsmbAc.setAcResultMap({
        "maEqCtgPartDetailDTO.eqCtgAsmbId":"eq_ctg_asmb_id"
    });
    eqCtgAsmbAc.init();

    // 사용여부
    acSysDesc("maEqCtgPartDetailDTO.isUse","maEqCtgPartDetailDTO.isUse","IS_USE",true);
    
    // 예상수명 주기
    acSysDesc("maEqCtgPartDetailDTO.periodTypeDesc","maEqCtgPartDetailDTO.periodType","PERIOD_TYPE",true);
    
    // 공통부위여부
    acSysDesc("maEqCtgPartDetailDTO.isEqTypePart","maEqCtgPartDetailDTO.isEqTypePart","IS_USE",true);

    // 수명확인방법
    acSysDesc("maEqCtgPartDetailDTO.scheduleTypeDesc","maEqCtgPartDetailDTO.scheduleTypeId","SCHEDULE_TYPE",true);
    
    // 수명확인방법 hidden control
    checkScheduleType($("input[name='maEqCtgPartDetailDTO.scheduleTypeId']").val());
}

function checkScheduleType(scheduleType)
{
	var cycleObj 		= $("input[name='maEqCtgPartDetailDTO.cycle']");		// 예상수명(시간)
	var periodTypeObj	= $("input[name='maEqCtgPartDetailDTO.periodTypeDesc']");  // 예상수명(시간단위)
	var usageObj		= $("input[name='maEqCtgPartDetailDTO.usage']");			// 예상수명(사용량,생산량,가동시간)

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
	if(code == "maEqCtgPartDetailDTO.scheduleTypeDesc") {
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQ_CTG_PART_ID');
	maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.isUse'].value = 'Y';
	maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.isEqTypePart'].value = 'N';
}

function setSequenceVal(sequenceVal)
{
	if(isNew) maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.oldEqCtgPartId'].value = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.eqCtgPartId'].value;
		
	maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.eqCtgPartId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maEqCtgPartDetailForm.strutsAction.value = '<%=MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_INPUT%>';
	else maEqCtgPartDetailForm.strutsAction.value = '<%= MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqCtgPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqCtgPartDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch();
    if (parent.findGridRow)
        parent.findGridRow(maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.eqCtgPartId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}


/*
 * 생성
 */
function goCreate()
{
	parent.goCreate();
}
 
/*
 * 복사
 */
function goCopycreate()
{
	isNew = true;
	sequenceNextVal('SQAEQ_CTG_PART_ID');
	
	var form = maEqCtgPartDetailForm;
	var url = contextPath + "/maEqCtgPartDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				var param = "&strutsAction=" + '<%=MaEqCtgPartDetailAction.DETAIL_COPY%>'
						  + "&" + FormQueryString(form);
			    XMLHttpPostVal(url, param, 'afterCopycreate');
			}
		});
    }
}

function afterCopycreate()
{
	isNew = false;
	var newKeyId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.eqCtgPartId'].value;
	
	// 상세 닫기
	goClose('maEqCtgPartDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');

	if(""== newKeyId || "undefined"== typeof newKeyId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.maEqCtgPartList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newKeyId);
	}
}

/*
 * 부품 정보 보기
 */
function goPtinfoLink()
{
	var partId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partId'].value;
	
	if("" == partId || "undefined" == typeof partId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;	
	}
	
	goPtinfo(partId);
}

/*
 * 입고 이력 보기
 */
function goPtrechistLink()
{
	var partId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partId'].value;
	var partDesc = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partDesc'].value;
	partDesc = partDesc.substring(0,partDesc.indexOf(','));
	
	if("" == partId || "undefined" == typeof partId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;	
	}
	
	goPtrechist(partId, partDesc);
}

/*
 * 출고 이력 보기
 */
function goPtisshistLink()
{
	var partId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partId'].value;
	var partDesc = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partDesc'].value;
	partDesc = partDesc.replace(", ",",");
	
	if("" == partId || "undefined" == typeof partId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;	
	}
	
	goPtisshist(partId, partDesc);
}

/*
 * 사용 이력 보기
 */
function goPtusehistLink()
{
	var partId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partId'].value;
	var partDesc = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partDesc'].value;
	partDesc = partDesc.replace(", ",",");
	
	if("" == partId || "undefined" == typeof partId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;	
	}
	
	goPtusehist(partId, partDesc);
}

/*
 * 현재고 보기
 */
function goPtcurrstockLink()
{
	var partId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partId'].value;
	var partDesc = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.partDesc'].value;
	
	partDesc = partDesc.replace(", ",",");
	
	if("" == partId || "undefined" == typeof partId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;	
	}
	
	goPtcurrstock(partId, partDesc);
}

                          
function goAudtrailLink()
{
	var objectId = maEqCtgPartDetailForm.elements['maEqCtgPartDetailDTO.eqCtgPartId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqCtgPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/>
	<html:hidden property="maEqCtgPartDetailDTO.eqCtgId"/>
	<html:hidden property="maEqCtgPartDetailDTO.eqCtgPartId"/>
	<html:hidden property="maEqCtgPartDetailDTO.eqCtgAsmbId"/>
	<html:hidden property="maEqCtgPartDetailDTO.partId"/>
	<html:hidden property="maEqCtgPartDetailDTO.oldEqCtgPartId"/>
	<html:hidden property="maEqCtgPartDetailDTO.periodType" />
	<html:hidden property="maEqCtgPartDetailDTO.scheduleTypeId" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 작업부위 -->
            <div class="field">
                <label><bean:message key="LABEL.workPart"/></label>
                <div class="input_box">
                    <html:text property="maEqCtgPartDetailDTO.eqCtgAsmbDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 부품코드 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.partCode"/></label>
				<div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.partNo" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부품명/규격 -->
			<div class="field">
				<label><bean:message key="LABEL.partNameSize"/></label>
				<div class="input_read">
					<html:text property="maEqCtgPartDetailDTO.partNameSize" readonly="true" />
				</div>
			</div>
			<!-- 부품명 -->
			<div class="field">
				<label><bean:message key="LABEL.partDesc"/></label>
				<div class="input_read">
					<html:text property="maEqCtgPartDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maEqCtgPartDetailDTO.ptSize" readonly="true" />
				</div>
			</div>
			<!-- 모델 -->
			<div class="field">
				<label><bean:message key="LABEL.model"/></label>
				<div class="input_read">
					<html:text property="maEqCtgPartDetailDTO.model" readonly="true" />
				</div>
			</div>
			<!-- 사용수량 -->
			<div class="field">
				<label><bean:message key="LABEL.useQty"/></label>
				<div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.useQty" tabindex="40" styleClass="num"/>
				</div>
			</div>
			
			<!-- 수명확인방법 -->
			<div class="field">
				<label>수명확인방법</label>
				<div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.scheduleTypeDesc" tabindex="42"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>

			<!-- 예상수명 -->
			<div class="field">
                <label><bean:message key="LABEL.expLife"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maEqCtgPartDetailDTO.cycle" tabindex="45" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maEqCtgPartDetailDTO.periodTypeDesc" tabindex="46" />
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
			<!-- 예상수명(사용량,생산량,가동시간) -->
			<div class="field">
                <label>예상수명</label>
                <div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.usage" tabindex="47" styleClass="num"/>
				</div>
            </div>
            
			<!-- 정렬값 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<!-- 공통부품여부 -->
            <div class="field">
                <label><bean:message key="LABEL.isCommCtgPart"/></label>
                <div class="input_box">
                    <html:text property="maEqCtgPartDetailDTO.isEqTypePart" tabindex="55"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maEqCtgPartDetailDTO.isUse" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>