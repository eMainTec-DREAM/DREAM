<%--===========================================================================
구성자재
author  kim21017
version $Id: maEqMstrPartDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrPartDetailAction"%>
<%@page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<html>
<head>
<!-- 구성자재-->
<title><bean:message key="TAB.maEqMstrPartList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변성 */
var partNoAc;
var eqAsmbAc;
var isNew = false;
function loadPage() 
{
	setTitle("maEqMstrPartDetailDTO.partNo","maEqMstrPartDetailDTO.partDesc");
	setForUpdate();
	getParentEqId();
	
	/* $("input[name='maEqMstrPartDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maEqMstrPartDetailDTO.partId','maEqMstrPartDetailDTO.partNo', 'maEqMstrPartDetailDTO.partDesc', true);
		}
	}); */
	
    partNoAc = new autoC({"maEqMstrPartDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    partNoAc.setAcResultMap({
    	"maEqMstrPartDetailDTO.partDesc":"full_desc",
    	"maEqMstrPartDetailDTO.partName":"description",
    	"maEqMstrPartDetailDTO.ptSize":"pt_size",
    	"maEqMstrPartDetailDTO.model":"model",
        "maEqMstrPartDetailDTO.partId":"part_id",
        "maEqMstrPartDetailDTO.ordNo":"ord_no"
    });
    partNoAc.setKeyName("maEqMstrPartDetailDTO.partId");
    partNoAc.init();
    
    // 부위
    eqAsmbAc = new autoC({"maEqMstrPartDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"equip_id":maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.equipId'].value
      	, "is_use" : "Y"
  	   });
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("maEqMstrPartDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maEqMstrPartDetailDTO.eqAsmbId":"eqasmb_id"
       ,"maEqMstrPartDetailDTO.ordNo":"ord_no"
    });
    eqAsmbAc.init();
    
    // 사용여부
    acSysDesc("maEqMstrPartDetailDTO.isUse","maEqMstrPartDetailDTO.isUse","IS_USE");
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

//상세 설비번호 가져오기
function getParentEqId(){
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.equipId'].value = parent.getEquipId();
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
	
function goTabPage(pageId)
{
	var form = document.maEqMstrPartDetailForm;
	
	goCommonTabPage(form, '' , pageId);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQPART_ID');
	// 공통부위여부 N으로 셋팅
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.isEqTypePart'].value = "N";
	// 사용여부 Y로 셋팅
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.isUse'].value = "Y";
	// 구성수량 1로 셋팅
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.consistQty'].value = "1";
	
	partNoAc.openLov();
	
}

function setSequenceVal(sequenceVal)
{
	if(isNew) maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.oldEqPartId'].value = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.eqPartId'].value;

	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.eqPartId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
{
    //setDisable(document.getElementsByName("disableDiv"));
}

function goSave(){
	if(ckCreate(currentPageId)) maEqMstrPartDetailForm.strutsAction.value = '<%=MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INPUT%>';
	else maEqMstrPartDetailForm.strutsAction.value = '<%= MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrPartDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
//     parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.eqPartId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAEQPART_ID');
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partNo'].value = '';
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partDesc'].value = '';
	maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value = '';
	
	partNoAc.openLov();
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
	sequenceNextVal('SQAEQPART_ID');
	var form = maEqMstrPartDetailForm;
	var url = contextPath + "/maEqMstrPartDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				setModal('<bean:message key="MESSAGE.MSG0083"/>');
										// 기다려주세요
				form.strutsAction.value = '<%=MaEqMstrPartDetailAction.DETAIL_COPY%>';
			    XMLHttpPostVal(url, FormQueryString(form), 'afterCopycreate');
			}
		});
    }
}
function afterCopycreate()
{
	isNew = false;
	var newEqPartId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.eqPartId'].value;

	// 상세 닫기
	goClose('maEqMstrPartDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');

	if(""== newEqPartId || "undefined"== typeof newEqPartId)
	{
		// 전체 복사인 경우
		parent.parent.frames["tabFrameTAB.maEqMstrPartList"].goSearch();
	}
	else
	{	// Unit 복사인 경우
		if(parent.setKeyAftercopy) parent.setKeyAftercopy(newEqPartId);
	}
	
}

/*
 * 부품 정보 보기
 */
function goPtinfoLink()
{
	var partId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value;
	
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
	var partId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value;
	var partDesc = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partDesc'].value;
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
	var partId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value;
	var partDesc = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partDesc'].value;
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
	var partId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value;
	var partDesc = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partDesc'].value;
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
	var partId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partId'].value;
	var partDesc = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.partDesc'].value;
	
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
	var objectId = maEqMstrPartDetailForm.elements['maEqMstrPartDetailDTO.eqPartId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrPartDetailDTO.eqPartId"/>
	<html:hidden property="maEqMstrPartDetailDTO.partId"/>
	<html:hidden property="maEqMstrPartDetailDTO.eqAsmbId"/>
	<html:hidden property="maEqMstrPartDetailDTO.equipId"/>
	<html:hidden property="maEqMstrPartDetailDTO.oldEqPartId"/>
	<html:hidden property="maEqMstrPartListDTO.eqPartId"/>
	<%-- <html:hidden property="maEqMstrPartDetailDTO.eqCtgAsmbId"/> --%>
    <html:hidden property="maEqMstrDetailDTO.eqCtgId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
		    <%-- <!-- 작업부위 -->
            <div class="field">
                <label><bean:message key="LABEL.workPart"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrPartDetailDTO.eqCtgAsmbDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div> --%>
            <!-- 부위 -->
            <div class="field" name="disableDiv">
                <label><bean:message key="LABEL.asmb"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrPartDetailDTO.eqAsmbDesc" tabindex="10"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 정렬값 -->
            <div class="field">
                <label><bean:message key="LABEL.ordNo"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrPartDetailDTO.ordNo" tabindex="20"/>
                </div>
            </div>
			<!-- 부품번호(부품코드) -->
            <div class="field" name="disableDiv">
				<label><bean:message key="LABEL.partNo"/></label>
				<div class="input_box" class="check">
					<html:text property="maEqMstrPartDetailDTO.partNo" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부품명/규격 -->
			<div class="field">
				<label><bean:message key="LABEL.partNameSize"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPartDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<!-- 부품명 -->
			<div class="field">
				<label><bean:message key="LABEL.partDesc"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPartDetailDTO.partName" readonly="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPartDetailDTO.ptSize" readonly="true" />
				</div>
			</div>
			<!-- 모델 -->
			<div class="field">
				<label><bean:message key="LABEL.model"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPartDetailDTO.model" readonly="true" />
				</div>
			</div>
			<!-- 구성수량 -->
			<div class="field">
				<label><bean:message key="LABEL.consistQty"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.consistQty" tabindex="50" styleClass="num"/>
				</div>
			</div>
			<!-- 공통부위여부 -->
            <div class="field">
                <label><bean:message key="LABEL.isCommCtgAsmb"/></label>
                <div class="input_read">
                    <html:text property="maEqMstrPartDetailDTO.isEqTypePart" tabindex="55" readonly="true"/>
                </div>
            </div>
            <!-- 사용여부 -->
            <div class="field">
                <label><bean:message key="LABEL.isUse"/></label>
                <div class="input_box">
                    <html:text property="maEqMstrPartDetailDTO.isUse" tabindex="60"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 최초출고일자 -->
			<div class="field">
				<label>최초출고일자</label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.issueFirstDate" />
					<p class="open_calendar"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 총출고수량 -->
			<div class="field">
				<label>총출고수량</label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.useQty" styleClass="num"/>
				</div>
			</div>
			<!-- 최종출고일자 -->
			<div class="field">
				<label>최종출고일자</label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.issueLastDate"/>
					<p class="open_calendar"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 출고횟수 -->
			<div class="field">
				<label>출고횟수</label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.issueTime" styleClass="num"/>
				</div>
			</div>
			<!-- 도면번호 -->
			<div class="field">
				<label><bean:message key="LABEL.consistNbr"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.consistNbr" />
				</div>
			</div>
			<!-- 구성순번 -->
			<div class="field">
				<label><bean:message key="LABEL.dwgNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.dwgNo" />
				</div>
			</div>
			<!-- 도면Section번호 -->
			<div class="field">
				<label><bean:message key="LABEL.dwgSectionNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPartDetailDTO.dwgSectionNo" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>