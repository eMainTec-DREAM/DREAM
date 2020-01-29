<%--===========================================================================
IT장비목록 Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- IT장비목록 -->
<title><bean:message key='MENU.EQIT' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;
var slideImage = new Array();
<!-- //

/** 자동완성 변수 */
var partAc;
var currAc;
var useDeptAc;
var plantAc;
var wkctrAc;

var isNew = false;
function loadPage() 
{
	
    setTitle("maEqMstrDetailDTO.itemNo", "maEqMstrDetailDTO.equipDesc");
    //For Save
    setForUpdate();

    //setSlideImage();
    
    //관리자(쩡)
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
    
    //관리자(부)
    subMngAc = new autoC({"maEqMstrDetailDTO.subMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.deptId"
    });
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
        //"maEqMstrDetailDTO.eqLocType":"isLowestLvl"
    });
    eqLocDescAc.init();
    
    //장비구분
    eqCtgTypeAc = new autoC({"maEqMstrDetailDTO.eqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	 , "eqctg_type":"IT"
	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setKeyName("maEqMstrDetailDTO.eqCtgId");
    eqCtgTypeAc.setAcResultMap({
        "maEqMstrDetailDTO.eqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    //관리부서
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
    
    //사용부서
    useDeptAc = new autoC({"maEqMstrDetailDTO.usageDeptDesc":"description"});
    useDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    useDeptAc.setTable("TADEPT");
    useDeptAc.setKeyName("maEqMstrDetailDTO.usageDeptId");
    useDeptAc.setAcResultMap({
        "maEqMstrDetailDTO.usageDeptId":"dept_id"
    });
    useDeptAc.init();
    
    //사용자
    userAc = new autoC({"maEqMstrDetailDTO.userName":"emp_name"});
    userAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    userAc.setTable("TAEMP");
    userAc.setAcResultMap({
        "maEqMstrDetailDTO.userId":"emp_id"
    });
    userAc.setAcDConditionMap({
    	"dept_id" : "maEqMstrDetailDTO.usageDeptId"
    });
    userAc.setKeyName("maEqMstrDetailDTO.userId");
    userAc.init();
    
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

	//작업그룹
	wkctrAc = new autoC({"maEqMstrDetailDTO.wkctrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_use":"Y"
    });
	wkctrAc.setAcResultMap({
        "maEqMstrDetailDTO.wkctrId":"wkctr_id"
    });
	wkctrAc.init();

    //장비상태
    acSysDesc("maEqMstrDetailDTO.eqStatusDesc","maEqMstrDetailDTO.eqStatusId","EQ_STATUS", true);
    //장비구분
    //acSysDesc("maEqMstrDetailDTO.eqCtgTypeDesc","maEqMstrDetailDTO.eqCtgTypeId","EQCTG_TYPE", true);
    
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
    

}

function afterDisable()
{
	$('.b_save').show();
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



/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEQUIP_ID');
  	
    //설비상태 = RUN - 운영
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqStatusDesc'].value = "R"; 
	valSysDir('maEqMstrDetailDTO.eqStatusId', 'maEqMstrDetailDTO.eqStatusDesc', 'EQ_STATUS', true);
	
	var selectedEqCtgType = "IT";

	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqCtgTypeId'].value = selectedEqCtgType;
	maEqMstrDetailForm.elements['maEqMstrDetailDTO.eqCtgTypeDesc'].value = selectedEqCtgType;
	//공장명
    if(loginUser.plant!='null'){
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.plant'].value = loginUser.plant;
    	maEqMstrDetailForm.elements['maEqMstrDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
	valSysDir('maEqMstrDetailDTO.eqCtgTypeId', 'maEqMstrDetailDTO.eqCtgTypeDesc', 'EQCTG_TYPE', true);
	
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
/**
 * 수정
 */
function goUpdate()
{
	loadSlideImages();

	setDisable(document.getElementsByName("disableDiv"));

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
	var equipId = maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value;
	var itemNo  = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

	if(typeof equipId=="undefined") return;

	goRevisionhistoryList(equipId, itemNo, 'ASSET');
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
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'assetListITDetail');
}

function goTabPage(pageId) 
{
    var form = document.maEqMstrDetailForm;
    
//    form.elements['assetListITSWListDTO.itEqId'].value = form.elements['maEqMstrDetailDTO.equipId'].value;
    
    if(pageId == "maDocLibList" || pageId == "assetListItDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['maEqMstrDetailDTO.equipId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "EQMSTR";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['maEqMstrDetailDTO.equipDesc'].value;
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

	//goClose('assetListITDetail');
	
	//복사되었습니다.
	alertMessage1('<bean:message key="MESSAGE.MSG033"/>');
	
	if (parent.setKeyAftercopy)	parent.setKeyAftercopy(newEquipId,'assetListITDetail');

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
	var actionUrl = contextPath + "/assetListITDetail.do";
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
		
    	maEqMstrDetailForm.strutsAction.value = "<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INPUT%>";
    }
    else {
		maEqMstrDetailForm.elements['maEqMstrDetailDTO.updDate'].value = getNowDateTime(true);
    	
    	maEqMstrDetailForm.strutsAction.value = "<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_UPDATE%>";
    }
	
	var actionUrl = contextPath + "/assetListITDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
		
	goUpdate();
	// 투자목록 - 설비(목록)에서 생성한 경우 
    if (parent.currentPageId == "invtEquipList" && maEqMstrDetailForm.strutsAction.value == '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INPUT%>') 
  	{
    	parent.afterCreate(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);
  	}
		
	if (parent.findGridRow)
		parent
				.findGridRow(maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value);

	maEqMstrDetailForm.elements['maEqMstrCommonDTO.equipId'].value = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("maEqMstrDetailDTO.itemNo", "maEqMstrDetailDTO.equipDesc");

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
	goClose('assetListITDetail');
}
	

function loadSlideImages()
{
	var url = contextPath + "/assetListITDetail.do";

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
	
//변경이력팝업
function goHistoryLink()
{
	var equipId = maEqMstrDetailForm.elements['maEqMstrDetailDTO.equipId'].value;
	var itemNo  = maEqMstrDetailForm.elements['maEqMstrDetailDTO.itemNo'].value;

	if(typeof equipId=="undefined") return;

	goHistoryList(equipId, itemNo);
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

	<html:form action="/assetListITDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="maEqMstrCommonDTO.equipId" />		<!-- Key -->
		<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId" />
		<html:hidden property="maEqMstrDetailDTO.equipId" />		<!-- Key -->
		<html:hidden property="maEqMstrDetailDTO.eqStatusId" />
		<html:hidden property="maEqMstrDetailDTO.eqLocId" />
		<html:hidden property="maEqMstrDetailDTO.eqCtgId" />
		<html:hidden property="maEqMstrDetailDTO.deptId" />
		<html:hidden property="maEqMstrDetailDTO.mainMngId" />
		<html:hidden property="maEqMstrDetailDTO.subMngId" />
		<html:hidden property="maEqMstrDetailDTO.revisionhistId" />
		<html:hidden property="maEqMstrDetailDTO.revisionStatusId" />
	 	<html:hidden property="maEqMstrDetailDTO.eqCtgTypeId" />
	 	<html:hidden property="maEqMstrDetailDTO.eqCtgTypeDesc" />
		<html:hidden property="maEqMstrDetailDTO.usageDeptId" />
		<html:hidden property="maEqMstrDetailDTO.userId" />
		<html:hidden property="maEqMstrDetailDTO.currencyId" />		<!-- 화폐단위 ID -->
		<html:hidden property="maEqMstrDetailDTO.oldEquipId" />
		<html:hidden property="maEqMstrDetailDTO.oldEqNo" />
	 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	 	<html:hidden property="maDocLibCommonDTO.objectType" />
	 	<html:hidden property="maDocLibCommonDTO.description" />
	 	<html:hidden property="maEqMstrDetailDTO.plant" />
	 	<html:hidden property="maEqMstrDetailDTO.wkctrId" />
	 	
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
				<!-- 장비번호 -->
				<div class="field" name="disableDiv">
					<label><bean:message key="LABEL.ITEqNo"/></label>
					<div class="input_read">
						<html:text property="maEqMstrDetailDTO.itemNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- Tag No -->
				<div class="field">
					<label><bean:message key="LABEL.tagNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.tagNo" tabindex="241"/>
					</div>
				</div>
				<!-- 장비명 -->
				<div class="field">
					<label><bean:message key="LABEL.ITEqName"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.equipDesc" tabindex="20" />
					</div>
				</div>
				<!-- 설치위치 -->
				<div class="field">
					<label><bean:message key="LABEL.assetLoc"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.eqLocDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 장비구분 -->
				<div class="field">
					<label><bean:message key="LABEL.ITEqType"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.eqCtgDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 모델 -->
				<div class="field">
					<label><bean:message key="LABEL.model"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.modelNo" tabindex="50" />
					</div>
				</div>
				<!-- 설비상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.eqStatusDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Serial # -->
				<div class="field">
					<label><bean:message key="TAB.SerialNo"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.serialNo" tabindex="70" />
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.manageDept"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.deptDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(정) -->
				<div class="field">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.mainMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- AS업체명 -->
				<div class="field">
					<label><bean:message key="LABEL.asVendor"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.asVendor" tabindex="100" />
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.subMngName" tabindex="110" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- Maker -->
				<div class="field">
					<label><bean:message key="LABEL.makerName"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.maker" tabindex="120" />
					</div>
				</div>
				<!-- 공급사 -->
				<div class="field">
					<label><bean:message key="LABEL.supplier"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.supplier" tabindex="130" />
					</div>
				</div>
				<!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.usageDeptDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용자 -->
				<div class="field">
					<label><bean:message key="LABEL.user"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.userName" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 구입일자 -->
				<div class="field">
					<label><bean:message key="LABEL.buyDate"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.buyDate" tabindex="160" />
						<p class="open_calendar">
							<span>날짜</span>
						</p>
					</div>
				</div>
				<!-- 구입금액 -->
				<div class="field">
					<label><bean:message key="LABEL.buyAmt"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.buyAmt" tabindex="170" styleClass="num"/>
					</div>
				</div>
				<!-- OS -->
				<div class="field">
					<label><bean:message key="LABEL.os"/></label>
					<div class="input_box">
						<html:text property="assetListITDetailDTO.osName" tabindex="180"/>
					</div>
				</div>
				<!-- Hostname -->
				<div class="field">
					<label><bean:message key="LABEL.hostName"/></label>
					<div class="input_box">
						<html:text property="assetListITDetailDTO.hostName" tabindex="190"/>
					</div>
				</div>
				<!-- IP Address -->
				<div class="field">
					<label><bean:message key="LABEL.ipAddr"/></label>
					<div class="input_box">
						<html:text property="assetListITDetailDTO.ipAddr" tabindex="200"/>
					</div>
				</div>
				<!-- MAC Address -->
				<div class="field">
					<label><bean:message key="LABEL.macAddr"/></label>
					<div class="input_box">
						<html:text property="assetListITDetailDTO.macAddr" tabindex="210"/>
					</div>
				</div>
				<!-- 세부사양 -->
				<div class="field_long">
					<label><bean:message key="LABEL.Specification"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.eqSpec" tabindex="220"/>
					</div>
				</div>
				<!-- 생성시간 -->
				<div class="field">
					<label><bean:message key="LABEL.createDate"/></label>
					<div class="input_read" >
						<html:text property="maEqMstrDetailDTO.creDate" tabindex="228" readonly="true"/>
					</div>
				</div>
				<!-- 수정시간 -->
				<div class="field">
					<label><bean:message key="LABEL.updateDate"/></label>
					<div class="input_read" >
						<html:text property="maEqMstrDetailDTO.updDate" tabindex="229" readonly="true"/>
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
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_read">
						<html:text property="maEqMstrDetailDTO.plantDesc" tabindex="230"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>				
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="maEqMstrDetailDTO.remark" tabindex="231" styleClass="ta100"/>
					</div>
				</div>
				<!-- 작업그룹  -->
				<div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="maEqMstrDetailDTO.wkctrDesc" tabindex="232"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div> 
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>
