<%--===========================================================================
설비마스터 - 상세
author  kim21017
version $Id: maEqMstrMoldDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.asset.list.action.MaEqMstrMoldDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
    
    List list = (List)request.getAttribute("slideFileList");
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
/** 자동완성 변수  */
var eqLocDescAc;
var eqStrLocAc;
var lnWrkDescAc;
var currAc;
var usageDeptAc;
var wkctrAc;
var plantAc;
var eqMoldAtypeAc;
var eqMoldBtypeAc;
var eqMoldCtypeAc;
var isNew = false;

var slideImage = new Array();
<%
if(list != null)
	for(int i= 0; list.size() > i ; i++)
	{
	    Map map = (Map)list.get(i);
%>
	slideImage.push('<%=(String)map.get("FILE_NAME")%>');
<% 
	}
%>		

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
		//goTabPage("maEqMstrSpecList");
	}
	
	setTitle("maEqMstrDetailDTO.itemNo","maEqMstrDetailDTO.equipDesc");
	
	setForUpdate();
	
	setSlideImage();

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
    
	mainMngAc = new autoC({"maEqMstrDetailDTO.mainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maEqMstrDetailDTO.mainMngId");
    mainMngAc.setAcResultMap({
        "maEqMstrDetailDTO.mainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maEqMstrDetailDTO.subMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setKeyName("maEqMstrDetailDTO.subMngId");
    subMngAc.setAcResultMap({
        "maEqMstrDetailDTO.subMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
    subMngAc.init();
    
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
    
	// 사용부서
    usageDeptAc = new autoC({"maEqMstrDetailDTO.usageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setKeyName("maEqMstrDetailDTO.usageDeptId");
    usageDeptAc.setAcResultMap({
        "maEqMstrDetailDTO.usageDeptId":"dept_id"
    });
    usageDeptAc.init();
    
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
    
	lnWrkDescAc = new autoC({"maEqMstrDetailDTO.lnWrkListDesc":"description"});
	lnWrkDescAc.setAcConditionMap({
    	"comp_no": loginUser.compNo
  	   });
	lnWrkDescAc.setTable("TALNWRKLIST");
	lnWrkDescAc.setKeyName("maEqMstrDetailDTO.lnWrkListId");
	lnWrkDescAc.setAcResultMap({
        "maEqMstrDetailDTO.lnWrkListId":"lnWrkListId"
    });
	lnWrkDescAc.init();
	
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
    
 	//공장코드
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
    
    //작업그룹
	wkctrAc = new autoC({"maEqMstrDetailDTO.wkctrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setKeyName("maEqMstrDetailDTO.wkctrId");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	wkctrAc.setAcResultMap({
        "maEqMstrDetailDTO.wkctrId":"wkctr_id"
    });
	wkctrAc.init();
	
	//금형구분
	eqMoldAtypeAc = new autoC({"maEqMstrMoldDetailDTO.eqMoldAtypeDesc":"full_desc"});
	eqMoldAtypeAc.setTable("TACDUSRD");
	eqMoldAtypeAc.setKeyName("maEqMstrMoldDetailDTO.eqMoldAtype");
	eqMoldAtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_ATYPE"
    });
	eqMoldAtypeAc.setAcResultMap({
        "maEqMstrMoldDetailDTO.eqMoldAtype":"cdusrd_no"
    });
	eqMoldAtypeAc.init();
	
	//금형구분
	eqMoldBtypeAc = new autoC({"maEqMstrMoldDetailDTO.eqMoldBtypeDesc":"full_desc"});
	eqMoldBtypeAc.setTable("TACDUSRD");
	eqMoldBtypeAc.setKeyName("maEqMstrMoldDetailDTO.eqMoldBtype");
	eqMoldBtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_BTYPE"
    });
	eqMoldBtypeAc.setAcResultMap({
        "maEqMstrMoldDetailDTO.eqMoldBtype":"cdusrd_no"
    });
	eqMoldBtypeAc.init();
	
	//금형구분
	eqMoldCtypeAc = new autoC({"maEqMstrMoldDetailDTO.eqMoldCtypeDesc":"full_desc"});
	eqMoldCtypeAc.setTable("TACDUSRD");
	eqMoldCtypeAc.setKeyName("maEqMstrMoldDetailDTO.eqMoldCtype");
	eqMoldCtypeAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
        ,"dir_type":"EQMOLD_CTYPE"
    });
	eqMoldCtypeAc.setAcResultMap({
        "maEqMstrMoldDetailDTO.eqMoldCtype":"cdusrd_no"
    });
	eqMoldCtypeAc.init();
	
    acSysDesc("maEqMstrDetailDTO.pmiActionTypeDesc","maEqMstrDetailDTO.pmiActionType","PMI_ACTION_TYPE", true);
    acSysDesc("maEqMstrMoldDetailDTO.isDisUse","maEqMstrMoldDetailDTO.isDisUse","IS_USE", true);
  
 // 제/개정 화면제어
 	revDisplayCtrl(M$('maEqMstrDetailDTO.revisionStatusId').value,M$('maEqMstrDetailDTO.isLastVersion').value,"ASSET");
}

function afterDisable()
{
	$('.b_save').show();
	$('.b_eqpdf').show();
	$('.b_history').show();
	$('.b_colsetting').show();
	$('.b_revisionhistory').show();

	if(maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.isLastVersion'].value == "Y")
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
	var url = contextPath + "/maEqMstrMoldDetail.do";

    var oldSAction = maEqMstrMoldDetailForm.elements['strutsAction'].value;
    maEqMstrMoldDetailForm.elements['strutsAction'].value = '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_PHOTO%>'

    $.post(url,FormQueryString(maEqMstrMoldDetailForm), function(_data){

    	var jsonObj = JSON.parse(_data);  
		slideImage = new Array()
    	for(var i = 0; jsonObj.length >i ; i++)
    	{
    		slideImage.push(jsonObj[i].FILE_NAME);
    	}
		
		setSlideImage();
    });
    
    maEqMstrMoldDetailForm.elements['strutsAction'].value = oldSAction;
}

/**
 * Attach Images
 */
function goPhoto()
{
	var url =  contextPath + "/maDocImgPopList.do";
    
    var param = "strutsAction=" + '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' +
                "&" + "maDocImgCommonDTO.objectId=" + maEqMstrMoldDetailForm.elements['maEqMstrCommonDTO.equipId'].value +
                "&" + "maDocImgCommonDTO.objectType=EQMSTR"+
                "&" + "maDocImgCommonDTO.objectDesc="+ maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value +
                "&" + "isDecoratorName=popupPage";
    
    openLayerPopup("maDocImgList", param);	
}
	
function goUpdate(){
}

/**
 * 완료
 */ 
function goRevcompleted()
{
	var revStatus = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.revisionStatusId'].value;
	var objId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var objNo = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;
	var revhistId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.revisionhistId'].value;
	 
	revCompleted(revStatus, objId, objNo, revhistId, "ASSET");
}

function afterRevcompleted(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	alertMessage1("<bean:message key='MESSAGE.CMSG102'/>");
	
	//조회후 선택!
	//if(parent.findGridRow) parent.findGridRow(maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	//if(parent.goTabPage) parent.goTabPage(currentPageId);
	var newEquipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'maEqMstrMoldDetail');
}

/**
 * 개정
 */ 
function goRevision()
{
	var revhistId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.revisionhistId'].value;
	var desc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	
	openRev("assetListRevision", revhistId, desc, currentPageId);
}
/*
 * 개정이력 보기
 */
function goRevisionhistoryLink()
{
	var equipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var itemNo = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

	if(typeof equipId=="undefined") return;

	goRevisionhistoryList(equipId, itemNo, 'ASSET');
}

function goInput()
{
	sequenceNextVal('SQAEQUIP_ID');
	//PDF 보이기.
	$(".b_pdf").css("display","none");
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.isLawEq'].value = 'Y';
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	//설비상태 = RUN - 운영
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.eqStatusDesc'].value = "R"; 
	valSysDir('maEqMstrDetailDTO.eqStatusId', 'maEqMstrDetailDTO.eqStatusDesc', 'EQ_STATUS', true);

}

function setSequenceVal(sequenceVal)
{
	if(isNew) 
	{
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.oldEquipId'].value = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.oldEqNo'].value = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;	
	}
	
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value = sequenceVal;
	maEqMstrMoldDetailForm.elements['maEqMstrCommonDTO.equipId'].value = sequenceVal;
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.itemNo'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
	
function goTabPage(pageId)
{
	var form = document.maEqMstrMoldDetailForm;

	if(pageId == "maDocLibList" || pageId == "maEqDocLibList")
	{	
		maEqMstrMoldDetailForm.elements['maDocLibCommonDTO.objectId'].value = maEqMstrMoldDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
		maEqMstrMoldDetailForm.elements['maDocLibCommonDTO.objectType'].value = "EQMSTR";  //EQMSTR docDesc
		maEqMstrMoldDetailForm.elements['maDocLibCommonDTO.description'].value = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
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
	var form = maEqMstrMoldDetailForm;
	var url = contextPath + "/maEqMstrMoldDetail.do"; 
	
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
        								// 저장후 사용해주세요.
    }else{
    	getTopPage().dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
			if(result){					// 복사하시겠습니까?
				form.strutsAction.value = '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_COPY%>';
			    XMLHttpPostVal(url, FormQueryString(form), 'afterCopycreate');
			}
		});
    }
}
function afterCopycreate()
{
	isNew = false;
	var newEquipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;

	//goClose('maEqMstrMoldDetail');
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'maEqMstrMoldDetail');

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
	if(ckCreate(currentPageId)) maEqMstrMoldDetailForm.strutsAction.value = '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_INPUT%>';
	else maEqMstrMoldDetailForm.strutsAction.value = '<%=MaEqMstrMoldDetailAction.EQ_MSTR_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maEqMstrMoldDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrMoldDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	//PDF 보이기.
	$(".b_pdf").css("display","");
	maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value = maEqMstrMoldDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
	//parent.goSearch();
	if (parent.findGridRow)	parent.findGridRow(maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }

/**
 * 승인요청
 */
function goApproval()
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		var objectId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
		var equipDesc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
		
		//APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID,
		appAction(objectId, "EQUIPREV", equipDesc);
	}
}

function afterApproval()
{
	if (parent.findGridRow)	parent.findGridRow(maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
	goClose('maEqMstrMoldDetail');
}
 
 //변경이력
 function goHistoryLink()
 {
 	var equipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
 	var itemNo = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

 	if(typeof equipId=="undefined") return;

 	goHistoryList(equipId, itemNo);
 }
 
//등록카드 
function goEqinfopdfLink()
{
	var equipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	if(typeof equipId=="undefined") return;
	
	var reportName = 'maEqMstrDetail';
	var qrdName = 'maEqMstrDetail';
	var compNo = "<%=user.getCompNo()%>";
	var userId = "<%=user.getUserId()%>";
	var langId = "<%=user.getLangId()%>";
	
	var imagePath;
	var baseUrl = $(location).attr('protocol')+"//"+$(location).attr('host');
	if ( $('img[name=photo0]').length > 0 ) {
		//사진이 존재하는 경우
		imagePath = baseUrl+$('img[name=photo0]').attr('src');
	}else{
		//미존재시
		imagePath = baseUrl+contextPath+"/common/images/ma/no_image.png";
	}
	
	goEqinfopdf(reportName, qrdName, compNo, userId, langId, equipId, imagePath);
	
}
//이력카드
function goEqpdfLink()
{
	var reportName = 'maEqMstrHistList';
	var qrdName = 'maEqMstrHistList';
	var compNo = "<%=user.getCompNo()%>";
	var userId = "<%=user.getUserId()%>";
	var langId = "<%=user.getLangId()%>";
	var equipId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;

	if(typeof equipId=="undefined") return;
	
	goEqpdf(reportName, qrdName, compNo, userId, langId, equipId);
	
}
/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	var equipId   = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goEqbmList('', '', equipId, equipDesc, fromDate, toDate);
}
/*
 * 예방작업기준서 보기
 */
function goPmmstrLink()
{
	var equipId   = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goPmmstrList(equipId, equipDesc);
}
/*
 * 예방작업이력 보기
 */
function goPmwoLink()
{
	var equipId   = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goPmwoList(equipId, equipDesc, fromDate, toDate);
}
/*
 * 사용부품이력 보기
 */
function goUsepartsLink()
{
	var equipId   = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var equipDesc = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipDesc'].value;
	var fromDate = getMinusDay(365).replace(/\-/gi, "");
	var toDate = getToday().replace(/\-/gi, "");
	
	goUsepartsList(equipId, equipDesc, fromDate, toDate);
}


function goAudtrailLink()
{
	var objectId = maEqMstrMoldDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqMstrMoldDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maEqMstrCommonDTO.equipId" />
	<html:hidden property="maEqMstrDetailDTO.equipId" />
	<html:hidden property="maEqMstrDetailDTO.eqLocId" />
	<html:hidden property="maEqMstrDetailDTO.eqCtgId" />
	<html:hidden property="maEqMstrDetailDTO.eqStatusId" />
	<html:hidden property="maEqMstrDetailDTO.plfTypeId" />
	<html:hidden property="maEqMstrDetailDTO.deptId" />
	<html:hidden property="maEqMstrDetailDTO.mainMngId" />
	<html:hidden property="maEqMstrDetailDTO.subMngId" />
	<html:hidden property="maEqMstrDetailDTO.isLawEq" />
	<html:hidden property="maEqMstrDetailDTO.plfTypeDesc" />
	<html:hidden property="maEqMstrDetailDTO.utilCapa" />
	<html:hidden property="maEqMstrDetailDTO.deptCateg" value="OG" />
	<html:hidden property="maEqMstrDetailDTO.eqLocType" />
	<html:hidden property="maEqMstrDetailDTO.pmiActionType" />
	<html:hidden property="maEqMstrDetailDTO.revisionhistId" />
	<html:hidden property="maEqMstrDetailDTO.revisionStatusId" />
	<html:hidden property="maEqMstrDetailDTO.isLastVersion" />
	<html:hidden property="maEqMstrDetailDTO.eqStrLocNo" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maEqMstrDetailDTO.lnWrkListId" />		<!-- 가동달력 ID -->
 	<html:hidden property="maEqMstrDetailDTO.currencyId" />		<!-- 화폐단위 ID -->
 	<html:hidden property="maEqMstrDetailDTO.usageDeptId" />
	<html:hidden property="maEqMstrDetailDTO.oldEquipId" />
	<html:hidden property="maEqMstrDetailDTO.oldEqNo" />
 	<html:hidden property="maEqMstrDetailDTO.wkctrId" />
 	<html:hidden property="maEqMstrDetailDTO.plant" />
 	<html:hidden property="maEqMstrMoldDetailDTO.eqMoldAtype" />
 	<html:hidden property="maEqMstrMoldDetailDTO.eqMoldBtype" />
 	<html:hidden property="maEqMstrMoldDetailDTO.eqMoldCtype" />
 	
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
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrDetailDTO.itemNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 기존금형번호 -->
			<div class="field">
				<label><bean:message key="LABEL.oldMoldNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.oldEqNo" tabindex="175" />
				</div>
			</div>
			<!-- Tag No -->
			<div class="field">
				<label><bean:message key="LABEL.tagNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.tagNo" tabindex="241"/>
				</div>
			</div>
			<!-- 설비명 -->
			<div class="field">
				<label  class="check"><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.equipDesc" tabindex="20" />
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqLocDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 실명 -->
			<div class="field">
				<label><bean:message key="LABEL.roomName"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqStrLocDesc" tabindex="35" />
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
					<html:text property="maEqMstrDetailDTO.eqCtgDesc" tabindex="40" 
							onblur="valEqCtgFullDesc('maEqMstrDetailDTO.eqCtgId', 'maEqMstrDetailDTO.eqCtgDesc',true);"/>
					<p class="open_spop">
						<a href="javascript:lovEqCtg('maEqMstrDetailDTO.eqCtgId', 'maEqMstrDetailDTO.eqCtgDesc');">
							<span>조회</span>
						</a>
					</p>
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
			<!-- 설비상태 -->
			<div class="field">
				<label><bean:message key="LABEL.equipStatus"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.eqStatusDesc" tabindex="70" 
						onblur="valSysDir('maEqMstrDetailDTO.eqStatusId', 'maEqMstrDetailDTO.eqStatusDesc', 'EQ_STATUS', true);"/>
					<p class="open_spop"><a href="javascript:lovSysDir('maEqMstrDetailDTO.eqStatusId', 'maEqMstrDetailDTO.eqStatusDesc','EQ_STATUS');"><span>조회</span></a></p>
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
					<html:text property="maEqMstrDetailDTO.deptDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.wkctrDesc" tabindex="145"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>	
			<!-- 가동달력명 -->
			<div class="field">
				<label><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.lnWrkListDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.mainManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.mainMngName" tabindex="150"
						onblur="valEmpName('maEqMstrDetailDTO.mainMngId', '', 'maEqMstrDetailDTO.mainMngName',true);"/>
					<p class="open_spop"><a href="javascript:lovEmp('maEqMstrDetailDTO.mainMngId', '', 'maEqMstrDetailDTO.mainMngName');"><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.subManager"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.subMngName" tabindex="170"
						onblur="valEmpName('maEqMstrDetailDTO.subMngId', '' ,'maEqMstrDetailDTO.subMngName', true);"/>
					<p class="open_spop"><a href="javascript:lovEmp('maEqMstrDetailDTO.subMngId', '', 'maEqMstrDetailDTO.subMngName');"><span>조회</span></a></p>
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
					<html:text property="maEqMstrDetailDTO.serialNo" tabindex="190" />
				</div>
			</div>
			
			<!-- 금형구분 -->
			<div class="field">
				<label><bean:message key="LABEL.eqMoldAtype"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.eqMoldAtypeDesc" tabindex="172"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 금형구분 -->
			<div class="field">
				<label><bean:message key="LABEL.eqMoldBtype"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.eqMoldBtypeDesc" tabindex="173"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 금형구분 -->
			<div class="field">
				<label><bean:message key="LABEL.eqMoldCtype"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.eqMoldCtypeDesc" tabindex="174"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Cavity -->
			<div class="field">
				<label><bean:message key="LABEL.cavity"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.cavity" tabindex="175" />
				</div>
			</div>
			<!-- 사용처 -->
			<div class="field">
				<label><bean:message key="LABEL.storage"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.storage" tabindex="180" />
				</div>
			</div>
			<!-- 금형벌수 -->
			<div class="field">
				<label><bean:message key="LABEL.setCnt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.setCnt" tabindex="190" styleClass="num"/>
				</div>
			</div>
			<!-- 금형순서 -->
			<div class="field">
				<label><bean:message key="LABEL.moldStepNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.stepNo" tabindex="190" styleClass="num"/>
				</div>
			</div>
			<!-- 일생산량 -->
			<div class="field">
				<label><bean:message key="LABEL.dailyOutPut"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.outPut" tabindex="200" styleClass="num"/>
				</div>
			</div>
			<!-- 소유권 -->
			<div class="field">
				<label><bean:message key="LABEL.ownerShip"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.ownerShip" tabindex="210" />
				</div>
			</div>
			<!--매각/폐기유무 -->
			<div class="field">
				<label><bean:message key="LABEL.isDisUse"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.isDisUse" tabindex="220"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
				</div>
			</div>
			<!-- 매각/폐기일자 -->
			<div class="field">
				<label><bean:message key="LABEL.disUseDate"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.disUseDate" tabindex="230" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 매각금액 -->
			<div class="field">
				<label><bean:message key="LABEL.disUseAmt"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.disUseAmt" tabindex="240" styleClass="num"/>
				</div>
			</div>
			<!-- 매각업체 -->
			<div class="field">
				<label><bean:message key="LABEL.disUseVendor"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.disUseVendor" tabindex="250" />
				</div>
			</div>
			<!-- 사용기간 -->
			<div class="field">
				<label><bean:message key="LABEL.usePeriod"/></label>
				<div class="input_box">
					<html:text property="maEqMstrMoldDetailDTO.usePeriod" tabindex="260" styleClass="num"/>
				</div>
			</div>
			<!-- 정렬순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.ordNo" tabindex="270" />
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
				<div class="input_box">
					<html:text property="maEqMstrDetailDTO.plantDesc" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maEqMstrDetailDTO.remark" styleClass="ta50" tabindex="300" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>