<%--===========================================================================
설비마스터 - 상세
author  kim21017
version $Id: maEqMstrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 설비 -->
<title><bean:message key='LABEL.equipment' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;
var slideImage = new Array();

/** 자동완성 변수  */
var mainMngAc;
var subMngAc;
var eqLocDescAc;
var eqCtgTypeAc;
var deptAc;
var currAc;
function loadPage() 
{
	setTitle("maEqMstrDetailDTO.itemNo","maEqMstrDetailDTO.equipDesc");
	
	setForUpdate();
	
	//setSlideImage();
	
	mainMngAc = new autoC({"maEqMstrDetailDTO.mainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maEqMstrDetailDTO.mainMngId");
    mainMngAc.setAcResultMap({
        "maEqMstrDetailDTO.mainMngId":"emp_id"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maEqMstrDetailDTO.subMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
    subMngAc.setTable("TAEMP");
    subMngAc.setKeyName("maEqMstrDetailDTO.subMngId");
    subMngAc.setAcResultMap({
        "maEqMstrDetailDTO.subMngId":"emp_id"
    });
    subMngAc.init();

    eqLocDescAc = new autoC({"maEqMstrDetailDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("maEqMstrDetailDTO.eqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maEqMstrDetailDTO.eqLocId":"eqloc_id",
        "maEqMstrDetailDTO.eqLocType":"isLowestLvl"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maEqMstrDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("maEqMstrDetailDTO.eqCtgId");
    eqCtgTypeAc.setAcResultMap({
        "maEqMstrDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    
    deptAc = new autoC({"maEqMstrDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maEqMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maEqMstrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
	/** 화폐 자동완성 */
    currAc = new autoC({"maEqMstrDetailDTO.currencyDesc":"description"});
    currAc.setAcConditionMap({
          "comp_no":loginUser.compNo
       	, "dir_type":"CURRENCY"
  	   });
    currAc.setTable("TACDUSRD");
    currAc.setKeyName("maEqMstrDetailDTO.currencyId");
    currAc.setAcResultMap({
        "maEqMstrDetailDTO.currencyId":"cdusrd_no"
    });
    currAc.init();
    
    
    acSysDesc("maEqMstrDetailDTO.eqStatusDesc","maEqMstrDetailDTO.eqStatusId","EQ_STATUS",true);
    acSysDesc("maEqMstrDetailDTO.isLawEq","maEqMstrDetailDTO.isLawEq","IS_USE",true);
    acSysDesc("maEqMstrDetailDTO.eqGradeDesc","maEqMstrDetailDTO.eqGradeId","EQ_GRADE",true);
    acSysDesc("maEqMstrDetailDTO.pmiActionTypeDesc","maEqMstrDetailDTO.pmiActionType","PMI_ACTION_TYPE", true);

 	// 제/개정 화면제어
 	revDisplayCtrl(M$('maEqMstrDetailDTO.revisionStatusId').value,M$('maEqMstrDetailDTO.isLastVersion').value,"ASSET");
 	
 	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
		//goTabPage("maEqMstrSpecList");
	}
}

function afterDisable()
{
	//$('.b_save').show();
	$('.b_eqpdf').show();
	$('.b_history').show();
	$('.b_colsetting').show();
	$('.b_revisionhistory').show();

	if(maEqMstrDetailForm.elements['maEqMstrDetailDTO.isLastVersion'].value == "Y")
	{
		$('.b_revision').show();	
	}
}

function afterEnable()
{
	if(M$('maEqMstrDetailDTO.revisionStatusId').value == "W")
	{
		$('.b_revisionhistory').hide();
		$('.b_revision').hide();	
	} 
	else if(M$('maEqMstrDetailDTO.revisionStatusId').value == "P")
	{
		$('.b_revision').hide();	
	}
}

function loadSlideImages()
{
	var url = contextPath + "/maEqMstrDetail.do";

    var oldSAction = maEqMstrDetailForm.elements['strutsAction'].value;
    maEqMstrDetailForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maEqMstrDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    maEqMstrDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value +
                "&" + "maDocImgCommonDTO.objectType=EQMSTR"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}

function goUpdate()
{
	loadSlideImages();
	
	setDisable(document.getElementsByName("disableDiv"));
}

/**
 * 완료
 */ 
function goRevcompleted()
{
	var revStatus = maEqMstrDetailForm.elements['maEqMstrDetailDTO.revisionStatusId'].value;
	var objId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var objNo = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;
	var revhistId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.revisionhistId'].value;
	 
	revCompleted(revStatus, objId, objNo, revhistId, "ASSET");
}
function afterRevcompleted(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	alertMessage1("<bean:message key='MESSAGE.CMSG102'/>");
	//조회후 선택!
	//if(parent.findGridRow) parent.findGridRow(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	//if(parent.goTabPage) parent.goTabPage(currentPageId);
	var newEquipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'maEqVehicleMstrDetail');
}

/**
 * 개정
 */ 
function goRevision()
{
	var revhistId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.revisionhistId'].value;
	var desc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	
	openRev("commRevRevision", revhistId, desc, currentPageId);
}

function goRevisionhistory()
{
	openRevHistory(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value, maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value);
}

function goInput()
{
	sequenceNextVal('SQAEQUIP_ID');
	//PDF 보이기.
	$(".b_pdf").css("display","none");
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.isLawEq'].value = 'Y';
	//부서
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	//설비상태 = RUN - 운영
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqStatusDesc'].value = "R"; 
	valSysDir('maEqMstrDetailDTO.eqStatusId', 'maEqMstrDetailDTO.eqStatusDesc', 'EQ_STATUS', true);

	var selectedEqCtgType = maEqMstrDetailForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value;

	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqCtgTypeId'].value = selectedEqCtgType;
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqCtgTypeDesc'].value = selectedEqCtgType;

	valSysDir('maEqMstrDetailDTO.eqCtgTypeId', 'maEqMstrDetailDTO.eqCtgTypeDesc', 'EQCTG_TYPE', true);
	
	eqLocDescAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value = sequenceVal;
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value = sequenceVal;
	maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value = sequenceVal;

	//goTabPage("maEqMstrSpecList");
}
function afterAutoCmpt(code)
{
	if(code=="maEqMstrDetailDTO.eqLocDesc")
	{
		if("Y"!=maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocType'].value){
			alertMessage1("<bean:message key='MESSAGE.MSG0060'/>");
			maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocType'].value = "";
			maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocId'].value = "";
			maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocDesc'].value = "";
		}
	}
}
/**
 * eqLocNo 세팅
 */
 function setNoVal(nextNo){
	 maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value = nextNo;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
	
function goTabPage(pageId)
{
	var form = document.maEqMstrDetailForm;

	if(pageId == "maDocLibList" || pageId == "maEqVehicleDocLibList")
	{	
		maEqMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
		maEqMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EQMSTR";  //EQMSTR docDesc
		maEqMstrDetailForm.elements['maDocLibCommonDTO.description'].value = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
	
	if(ckCreate(currentPageId))
		valItemNo();
	else
		goSaveAction();
}

/**
 * 설비코드(Item No) 중복 체크
 */
function valItemNo()
{
	var actionUrl = contextPath + "/maEqMstrDetail.do";
	var param =  "&strutsAction=" + '<%= MaEqMstrDetailAction.EQ_NO_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(maEqMstrDetailForm);
	XMLHttpPostVal(actionUrl, param, 'setValidItemNo');
}

/**
 * valEquipNo()실행 후 호출
 */
var isValid;
function setValidItemNo(ajaxXmlDoc)
{
	isValid = '0';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid != '0')
    {
    	closeModal();
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value = '';
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].focus();
        
        // 중복되었습니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0009' />");
        
        return;
    }
    else
    {
    	goSaveAction();
    }
}

function goSaveAction()
{
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) {
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.creDate'].value = getNowDateTime(true);
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.updDate'].value = getNowDateTime(true);
		
		maEqMstrDetailForm.strutsAction.value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INPUT%>';
	}
	else {
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.updDate'].value = getNowDateTime(true);
				
		maEqMstrDetailForm.strutsAction.value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_UPDATE%>';
	}
	
	var actionUrl = contextPath + "/maEqMstrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	goUpdate();
	//PDF 보이기.
	$(".b_pdf").css("display","");
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
	//parent.goSearch();
	
	// 투자목록 - 설비(목록)에서 생성한 경우 
    if (parent.currentPageId == "invtEquipList" && maEqMstrDetailForm.strutsAction.value == '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INPUT%>') 
  	{
    	parent.afterCreate(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
  	}
	
	if (parent.findGridRow)	parent.findGridRow(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	setTitle("maEqMstrDetailDTO.itemNo","maEqMstrDetailDTO.equipDesc");
 }

/**
 * 승인요청
 */
function goApproval()
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		var objectId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
		var equipDesc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
		
		//APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID,
		appAction(objectId, "EQUIPREV", equipDesc);
	}
}
function afterApproval()
{
	if (parent.findGridRow)	parent.findGridRow(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	goClose('maEqVehicleMstrDetail');
}

 //변경이력팝업
 function goHistory(){
	var url = contextPath + "/maEqMstrHistList.do";

    var popWidth = 1210;
    var popHeight = 680;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    var strutsVal= "1001";
    var param = "strutsAction="+strutsVal+"&maEqMstrHistListDTO.equipId="+maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value+
    				"&maEqMstrHistListDTO.equipDesc="+maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;
    openLayerPopup("maEqMstrHistList", param);

 }
 
 /**
 * Print 버튼 클릭
 */
function goPrint()
{
	//Report Designer에서 사진 뿌려주는게 사진path로 잡는데 그 경우 리눅스에서 부르는게 문제가 있어서
	 // 일단 imageSlide에 copy된 사진의 url로 부름.
	var imagePath;
	var baseUrl = $(location).attr('protocol')+"//"+$(location).attr('host');
	if ( $('img[name=photo0]').length > 0 ) {
		//사진이 존재하는 경우
		imagePath = baseUrl+$('img[name=photo0]').attr('src');
	}else{
		//미존재시
		imagePath = baseUrl+contextPath+"/common/images/ma/no_image.png";
	}
	reportCall('maEqMstrDetail','maEqMstrDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value,imagePath); 
	  
}
 
//등록카드 
function goEqinfopdf(){
	 goPrint();
}
//이력카드
function goEqpdf(){
	 reportCall('maEqMstrHistList','maEqMstrHistList', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value); 
}

/*
 * Audit Trail 보기
 */
function goAudtrailLink()
{
	var objectId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqMstrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrCommonDTO.equipId" />
	<html:hidden property="maEqMstrCommonDTO.equipDesc" />
	<html:hidden property="maEqMstrDetailDTO.equipId" />
	<html:hidden property="maEqMstrDetailDTO.eqLocId" />
	<html:hidden property="maEqMstrDetailDTO.eqCtgId" />
	<html:hidden property="maEqMstrDetailDTO.eqStatusId" />
	<html:hidden property="maEqMstrDetailDTO.eqGradeId" />
	<html:hidden property="maEqMstrDetailDTO.plfTypeId" />
	<html:hidden property="maEqMstrDetailDTO.plfTypeDesc" />
	<html:hidden property="maEqMstrDetailDTO.deptId" />
	<html:hidden property="maEqMstrDetailDTO.capacity" />
	<html:hidden property="maEqMstrDetailDTO.utilCapa" />
	<html:hidden property="maEqMstrDetailDTO.buyAmt" />
	<html:hidden property="maEqMstrDetailDTO.mainMngId" />
	<html:hidden property="maEqMstrDetailDTO.subMngId" />
	<html:hidden property="maEqMstrDetailDTO.eqCtgTypeId" />
	<html:hidden property="maEqMstrDetailDTO.pmiActionType" />
	<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId" />
	<html:hidden property="maEqMstrDetailDTO.deptCateg" value="IS_LOWEST_LVL" />
	<html:hidden property="maEqMstrDetailDTO.eqLocType" />
	<html:hidden property="maEqMstrDetailDTO.revisionhistId" />
	<html:hidden property="maEqMstrDetailDTO.revisionStatusId" />
	<html:hidden property="maEqMstrDetailDTO.isLastVersion" />
	<html:hidden property="maEqMstrDetailDTO.currencyId" />		<!-- 화폐단위 ID -->
 	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<!-- 사진 -->
			<div class="field_img">
				<label><bean:message key="LABEL.photo"/></label>
				<div class="img_box" name="maEqMstrDetailDTO.imgslide">
					<div class="function_box manual"><div class="fb_group1"><a class="b_photo"></a></div> </div>
					<div class="img_prev">
						<a></a>
					</div>
					<div class="img_next">
						<a></a>
					</div>
				</div>
			</div>
			<!-- 설비번호 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.itemNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 설비유형  -->
			<div class="field">
				<label><bean:message key="LABEL.eqCtgType"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.eqCtgTypeDesc" tabindex="11" readonly="true"/>
				</div>
			</div>
			<!-- 설비명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.equipDesc" tabindex="20" />
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqLocDesc" tabindex="30"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 종류 -->
			<div class="field">
				<label><bean:message key="LABEL.type"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqCtgDesc" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 제형 -->
			<div class="field">
				<label><bean:message key="LABEL.prodShape"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.prodShape" tabindex="35"/>
				</div>
			</div>
			<!-- 제작사 -->
			<div class="field">
				<label><bean:message key="LABEL.maker"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.maker" tabindex="50" />
				</div>
			</div>
			<!-- 모델 -->
			<div class="field">
				<label><bean:message key="LABEL.model"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.modelNo" tabindex="60" />
				</div>
			</div>
			<!-- 설비상태 -->
			<div class="field">
				<label><bean:message key="LABEL.equipStatus"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqStatusDesc" tabindex="70" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.setupDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.setupDate" tabindex="90" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 기계능력 -->
			<!-- 사용Utility -->
			<!-- 구입일자 -->
			<div class="field">
				<label><bean:message key="LABEL.buyDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.buyDate" tabindex="90" />
					<p class="open_calendar">
						<span>날짜</span>
					</p>
				</div>
			</div>
			<!-- 구입금액 -->
			<div class="field">
				<label><bean:message key="LABEL.buyAmt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.buyAmt" tabindex="190" styleClass="num"/>
				</div>
			</div>
			<!-- 폐기일자 -->
			<div class="field">
				<label><bean:message key="LABEL.disUseDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.disusedDate" tabindex="100" />
					<p class="open_calendar">
						<span>날짜</span>
					</p>
				</div>
			</div>
			<!-- 관리부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.deptDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.mainManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.mainMngName" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 법정설비여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isLawEq"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.isLawEq" tabindex="160"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.subManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.subMngName" tabindex="170"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검유형구분 -->
			<div class="field">
				<label><bean:message key="LABEL.pmiActionType"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.pmiActionTypeDesc" tabindex="170"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Old Eq# -->
			<div class="field">
				<label><bean:message key="LABEL.OldEqNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.oldEqNo" tabindex="180" />
				</div>
			</div>
			<!-- Serial# -->
			<div class="field">
				<label><bean:message key="LABEL.serialNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.serialNo" tabindex="190" />
				</div>
			</div>
			<!-- AS업체명 -->
			<div class="field">
				<label><bean:message key="LABEL.asVendor"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asVendor" tabindex="200" />
				</div>
			</div>
			<!-- AS담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.asName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asName" tabindex="210" />
				</div>
			</div>
			<!-- AS전화번호 -->
			<div class="field">
				<label><bean:message key="LABEL.asPhone"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asPhone" tabindex="220" />
				</div>
			</div>
			<!-- 설비중요도 -->
			<div class="field">
				<label><bean:message key="LABEL.eqGrade"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqGradeDesc" tabindex="230" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 정렬순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.ordNo" tabindex="240" />
				</div>
			</div>
			<!-- 엑셀번호 -->
			<div class="field">
				<label><bean:message key="LABEL.excelNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.excelNo" tabindex="241" readonly="true"/>
				</div>
			</div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="maEqMstrDetailDTO.creDate" tabindex="248" readonly="true"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="maEqMstrDetailDTO.updDate" tabindex="249" readonly="true"/>
				</div>
			</div>
			<!-- 화폐단위 -->
			<div class="field">
				<label><bean:message key="LABEL.currency"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.currencyDesc" tabindex="130" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrDetailDTO.remark" styleClass="ta50" tabindex="250" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>