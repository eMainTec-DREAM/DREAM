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
var eqStatusAc;
var eqGradeAc;
var eqStrLocAc;
var currAc;
var usageDeptAc;
var wkctrAc;
var plantAc;

var isNew = false;	

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
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
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
        "maEqMstrDetailDTO.eqLocId":"eqloc_id"
        ,"maEqMstrDetailDTO.eqLocType":"isLowestLvl"
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
    	,"is_use":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maEqMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maEqMstrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    // 사용부서
    usageDeptAc = new autoC({"maEqMstrDetailDTO.usageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setKeyName("maEqMstrDetailDTO.usageDeptId");
    usageDeptAc.setAcResultMap({
        "maEqMstrDetailDTO.usageDeptId":"dept_id"
    });
    usageDeptAc.init();
    
    
    parentEquipDescAc = new autoC({"maEqMstrDetailDTO.parentEquipDesc":"description"});
    parentEquipDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo,
       "EQ_STATUS":"R+S"
       });
    parentEquipDescAc.setTable("TAEQUIPMENT");
    parentEquipDescAc.setAcResultMap({
        "maEqMstrDetailDTO.parentEquipId":"equip_id"
    }); 
    parentEquipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maEqMstrDetailDTO.eqLocId",
    	"eqctg_id" : "maEqMstrDetailDTO.eqCtgId",
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
    parentEquipDescAc.setKeyName("maEqMstrDetailDTO.parentEquipId"); 
    parentEquipDescAc.init();
    
    eqStrLocAc = new autoC({"maEqMstrDetailDTO.eqStrLocDesc":"description"});
    eqStrLocAc.setAcConditionMap({
    	"is_use":"Y"
    	,"dir_type":"EQSTRLOC_NO"
    	,"comp_no":loginUser.compNo
  	   });
    eqStrLocAc.setTable("TACDUSRD");
    eqStrLocAc.setKeyName("maEqMstrDetailDTO.eqStrLocNo");
    eqStrLocAc.setAcResultMap({
        "maEqMstrDetailDTO.eqStrLocNo":"cdusrd_no"
    });
    eqStrLocAc.init();
    
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
    
 	// 공장코드
	plantAc = new autoC({"maEqMstrDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maEqMstrDetailDTO.plant");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqMstrDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    /** 작업그룹 */
	wkctrAc = new autoC({"maEqMstrDetailDTO.wkctrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setKeyName("maEqMstrDetailDTO.wkctrId");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
    });
	wkctrAc.setAcResultMap({
        "maEqMstrDetailDTO.wkctrId":"wkctr_id"
    });
	wkctrAc.init();
	
    acSysDesc("maEqMstrDetailDTO.eqStatusDesc","maEqMstrDetailDTO.eqStatusId","EQ_STATUS",true);
    
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
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'maEqBuildingMstrDetail');
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

/*
 * 개정이력 보기
 */
function goRevisionhistoryLink()
{
	var equipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var itemNo = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

	if(typeof equipId=="undefined") return;

	goRevisionhistoryList(equipId, itemNo, 'ASSET');
}

function goInput()
{
	sequenceNextVal('SQAEQUIP_ID');
	//설비이력출력 감추기
	$(".b_eqpdf").css("display","none");
	//PDF 보이기.
	$(".b_pdf").css("display","none");
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

 	//공장명
    if(loginUser.plant!='null'){
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.plant'].value = loginUser.plant;
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
 	
	eqLocDescAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	if(isNew) 
	{
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.oldEquipId'].value = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.oldEqNo'].value = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;	
	}
	
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value = sequenceVal;
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value = sequenceVal;
	maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value = sequenceVal;
}
function afterAutoCmpt(code)
{
		/* if(code=="maEqMstrDetailDTO.eqLocDesc")
		{
			if("Y"!=maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocType'].value){
				alertMessage1("<bean:message key='MESSAGE.MSG0060'/>");
				maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocType'].value = "";
				maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocId'].value = "";
				maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqLocDesc'].value = "";
			}
		} */
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

	if(pageId == "maDocLibList" || pageId == "maEqBuildingDocLibList")
	{	
		maEqMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
		maEqMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EQMSTR";  //EQMSTR docDesc
		maEqMstrDetailForm.elements['maDocLibCommonDTO.description'].value = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
	sequenceNextVal('SQAEQUIP_ID');
	var form = maEqMstrDetailForm;
	var url = contextPath + "/maEqMstrDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
    	getTopPage().dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				form.strutsAction.value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_COPY%>';
			    XMLHttpPostVal(url, FormQueryString(form), 'afterCopycreate');
			}
		});
    }
}
function afterCopycreate()
{
	isNew = false;
	var newEquipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;

	//goClose('maEqBuildingMstrDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'maEqBuildingMstrDetail');

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
 	goClose('maEqBuildingMstrDetail');
 }

 
 //변경이력팝업
 function goHistoryLink()
 {
 	var equipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
 	var itemNo = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

 	if(typeof equipId=="undefined") return;

 	goHistoryList(equipId, itemNo);
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
function goEqpdfLink()
{
	var reportName = 'maEqMstrHistList';
	var qrdName = 'maEqMstrHistList';
	var compNo = "<%=user.getCompNo()%>";
	var userId = "<%=user.getUserId()%>";
	var langId = "<%=user.getLangId()%>";
	var equipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;

	if(typeof equipId=="undefined") return;
	
	goEqpdf(reportName, qrdName, compNo, userId, langId, equipId);
	
}

/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	var equipId   = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goEqbmList('', '', equipId, equipDesc, fromDate, toDate);
}
/*
 * 예방작업기준서 보기
 */
function goPmmstrLink()
{
	var equipId   = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goPmmstrList(equipId, equipDesc);
}
/*
 * 예방작업이력 보기
 */
function goPmwoLink()
{
	var equipId   = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goPmwoList(equipId, equipDesc, fromDate, toDate);
}
/*
 * 사용부품이력 보기
 */
function goUsepartsLink()
{
	var equipId   = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goUsepartsList(equipId, equipDesc, fromDate, toDate);
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
	<html:hidden property="maEqMstrDetailDTO.plfTypeId" />
	<html:hidden property="maEqMstrDetailDTO.plfTypeDesc" />
	<html:hidden property="maEqMstrDetailDTO.deptId" />
	<html:hidden property="maEqMstrDetailDTO.mainMngId" />
	<html:hidden property="maEqMstrDetailDTO.subMngId" />
	<html:hidden property="maEqMstrDetailDTO.eqCtgTypeId" />
	<html:hidden property="maEqMstrDetailDTO.pmiActionType" />
	<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId" />
	<html:hidden property="maEqMstrDetailDTO.deptCateg" value="IS_LOWEST_LVL" />
	<html:hidden property="maEqMstrDetailDTO.eqLocType" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maEqMstrDetailDTO.parentEquipId" />
 	<html:hidden property="maEqMstrDetailDTO.revisionhistId" />
	<html:hidden property="maEqMstrDetailDTO.revisionStatusId" />
	<html:hidden property="maEqMstrDetailDTO.isLastVersion" />
	<html:hidden property="maEqMstrDetailDTO.eqStrLocNo" />
	<html:hidden property="maEqMstrDetailDTO.currencyId" />		<!-- 화폐단위 ID -->
 	<html:hidden property="maEqMstrDetailDTO.usageDeptId" />
	<html:hidden property="maEqMstrDetailDTO.oldEquipId" />
	<html:hidden property="maEqMstrDetailDTO.oldEqNo" />
	<html:hidden property="maEqMstrDetailDTO.wkctrId" /> <!-- 작업그룹 ID -->
 	<html:hidden property="maEqMstrDetailDTO.plant" />
 	 	
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
			<!-- 시설물번호 -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.buildNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.itemNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 유형명  -->
			<div class="field">
				<label><bean:message key="LABEL.TYPENM"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.eqCtgTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- Tag No -->
			<div class="field">
				<label><bean:message key="LABEL.tagNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.tagNo" tabindex="241"/>
				</div>
			</div>
			<!-- 시설물명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.buildDesc"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.equipDesc" tabindex="30" />
				</div>
			</div>
			<!-- 위치명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.eqLocName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqLocDesc" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 실명 -->
			<div class="field">
				<label><bean:message key="LABEL.roomName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqStrLocDesc" tabindex="45" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 종류명 -->
			<div class="field">
				<label><bean:message key="LABEL.ctgName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqCtgDesc" tabindex="50" />
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
			<!-- 제조국 -->
			<div class="field">
				<label><bean:message key="LABEL.countryMaker"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.countryMaker" tabindex="40" />
				</div>
			</div>
			<!-- 공급업체 -->
			<div class="field">
				<label><bean:message key="LABEL.supplier"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.supplier" tabindex="45" />
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
			<!-- 상태 -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqStatusDesc" tabindex="65" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.setupDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.setupDate" tabindex="70" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
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
			<!-- 기계능력 -->
			<div class="field">
				<label><bean:message key="LABEL.capacity"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.capacity" tabindex="110" />
				</div>
			</div>
			<!-- 사용Utility -->
			<div class="field">
				<label><bean:message key="LABEL.utilCapa"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.utilCapa" tabindex="120" />
				</div>
			</div>
			<!-- 관리부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.deptDesc" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.wkctrDesc" tabindex="85"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>				
			<!-- 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.mainManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.mainMngName" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.subManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.subMngName" tabindex="100"/>
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
			<!-- Serial# -->
			<div class="field">
				<label><bean:message key="LABEL.serialNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.serialNo" tabindex="180" />
				</div>
			</div>
			<!-- AS업체명 -->
			<div class="field">
				<label><bean:message key="LABEL.asVendor"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asVendor" tabindex="110" />
				</div>
			</div>
			<!-- AS담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.asName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asName" tabindex="120" />
				</div>
			</div>
			<!-- AS전화번호 -->
			<div class="field">
				<label><bean:message key="LABEL.asPhone"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.asPhone" tabindex="130" />
				</div>
			</div>
			<!-- 정렬순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.ordNo" tabindex="140" />
				</div>
			</div>
			<!-- 엑셀번호 -->
			<div class="field">
				<label><bean:message key="LABEL.excelNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.excelNo" tabindex="150" readonly="true"/>
				</div>
			</div>
			
			<!-- 상위설비 -->
            <div class="field">
                <label><bean:message key="LABEL.equipment"/></label>
                <div class="input_box" id="equipDiv">
                    <html:text property="maEqMstrDetailDTO.parentEquipDesc" tabindex="160" />
                    <p class="open_spop" id="equipPopDiv"><a><span>조회</span></a></p>
                </div>
            </div>
            
            
			<!-- 소재지 -->
			<div class="field">
				<label><bean:message key="LABEL.buildAddres"/></label>
				<div class="input_box">
					<html:text property="maEqBuildMstrDetailDTO.eqBuildAddr" tabindex="170"/>
				</div>
			</div>
			<!-- 면적 -->
			<div class="field">
				<label><bean:message key="LABEL.buildArea"/></label>
				<div class="input_box">
					<html:text property="maEqBuildMstrDetailDTO.eqBuildArea" tabindex="180"/>
				</div>
			</div>
			<!-- 구조 -->
			<div class="field">
				<label><bean:message key="LABEL.buildStruct"/></label>
				<div class="input_box">
					<html:text property="maEqBuildMstrDetailDTO.eqBuildStruct" tabindex="190"/>
				</div>
			</div>
			<!-- 층 -->
			<div class="field">
				<label><bean:message key="LABEL.buildFloor"/></label>
				<div class="input_box">
					<html:text property="maEqBuildMstrDetailDTO.eqBuildFloor" tabindex="200"/>
				</div>
			</div>
			<!-- 실 -->
			<div class="field">
				<label><bean:message key="LABEL.buildSection"/></label>
				<div class="input_box">
					<html:text property="maEqBuildMstrDetailDTO.eqBuildSection" tabindex="210"/>
				</div>
			</div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="maEqMstrDetailDTO.creDate" tabindex="214" readonly="true"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="maEqMstrDetailDTO.updDate" tabindex="215" readonly="true"/>
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
			<!-- 사용부서 -->
			<div class="field">
				<label><bean:message key="LABEL.usedDept"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.usageDeptDesc" tabindex="145"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.plantDesc" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrDetailDTO.remark" styleClass="ta50" tabindex="220" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>