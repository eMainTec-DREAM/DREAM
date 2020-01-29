/*===========================================================================
Linked Menu 구현   
*/
//====================================================================

//======================================================================================

/*
 * linkedfunc_method : equipment --> function name : goEquipmentLink 
 */
function goEquipmentLink(_pageId)
{
	//Open Equip List 
	openQuickTabPage("isLinked=Y", "maEqMstrList"); 
}

/**
 * param : equipId, eqctgType
 * ex) goEquipDetail(equipId, eqctgType)
 */
function goEquipDetail() 
{
	//Set Parameters (0:equipId, 1: eqctgType) 
	var _equipId, _eqctgType, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _equipId = arguments[i];
		else if(i == 1) _eqctgType = arguments[i];
    }
	
	//Find System Code for url
	var eqCtgTypeArr = getSysCode("EQCTG_TYPE");
	for(var i in eqCtgTypeArr)
	{
		if(_eqctgType == eqCtgTypeArr[i].CDSYSDNO) _url = eqCtgTypeArr[i].PARAM1;
	}

	//valid parameter
	if(typeof _eqctgType == "undefined") _eqctgType = "";
	if(typeof _equipId == "undefined" || _equipId == "")
	{
		goEquipmentLink();
		return;
	}
	if(typeof _url == "undefined" || _url == "") _url = "maEqMstrDetail";
	
	
	//Open Equip Detail
	var param = "strutsAction="+strutsEquipDetail+
				"&maEqMstrCommonDTO.selectedEqCtgTypeId="+_eqctgType +
				"&maEqMstrCommonDTO.equipId="+_equipId
				+ "&isLinked=Y";
		
	openQuickTabPage(param, _url); 
}

/*
 * 설비보기(설비위치, 설비종류)
 */
function goEquipList(_eqLocId, _eqLocDesc,_eqCtgId,_eqCtgDesc)
{
	var url   = contextPath + "/maEqMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "isDecoratorName=popupPage"+
				"&maEqMstrCommonDTO.strutsAction="+
				"&maEqMstrCommonDTO.filterEqLocId="+_eqLocId +
				"&maEqMstrCommonDTO.filterEqLocDesc="+_eqLocDesc +
				"&maEqMstrCommonDTO.filterEqCtgId="+_eqCtgId +
				"&maEqMstrCommonDTO.filterEqCtgDesc="+_eqCtgDesc
				+"&ACTION_FUNCTION=goSearch"
				+ "&isLinked=Y";

	//post 전송
	openWindowWithPost(url, "LOCLIST_EQ_POPUP", param, pos);
}

/*
 * 설비목록 보기
 */
function goMachEquipList()
{
	var _url = "maEqMachMstrList";
	var param = "strutsAction=&maEqMstrCommonDTO.equipId=";
	
	openQuickPage('isLinked=Y', _url); 
}

/*
 * 계측기 목록 보기
 */
function goToolsEquipList()
{
	var _url = "maEqToolMstrList";
	var param = "strutsAction=&maEqMstrCommonDTO.equipId=";
	
	openQuickPage('isLinked=Y', _url); 
}


/*
 * linkedfunc_method : asmb --> function name : goAsmbLink 
 */
function goAsmbLink(_pageId)
{
	//Open Asmb List 
	openQuickTabPage("isLinked=Y", "maEqMstrAsmbList"); 
}

/**
 * param : pmId, eqAsmbId
 * ex) goAsmbDetail(pmId, eqAsmbId,equipId)
 */
function goAsmbDetail() 
{
	//Set Parameters (0:equipId, 1:eqAsmbId, 2:equipId) 
	var _pmId, _eqAsmbId, _equipId, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _pmId = arguments[i];
		else if(i == 1) _eqAsmbId = arguments[i];
		else if(i == 2) _equipId = arguments[i];
    }
	
	//valid parameter
	if(typeof _pmId == "undefined") _pmId = "";
	if(typeof _equipId == "undefined") _equipId = "";
	if(typeof _eqAsmbId == "undefined" || _eqAsmbId == "")
	{
		goAsmbList(_pmId, _equipId);
		return;
	}
	if(typeof _url == "undefined" || _url == "") _url = "maEqMstrAsmbDetail";
	
	var param = "strutsAction="+strutsEqMstrAsmbDetail +
				"&maEqMstrAsmbListDTO.pmId="+_pmId +
				"&maEqMstrCommonDTO.equipId="+_equipId +
				"&maEqMstrAsmbListDTO.eqAsmbId="+_eqAsmbId
				+ "&isLinked=Y";
							
	openQuickTabPage(param, _url); 
}

/*
 * 부위목록 보기
 */
function goAsmbList(_pmId, _equipId)
{
	if(typeof _pmId=="undefined" &&typeof _equipId=="undefined") return;
	
	var _url = "maEqMstrAsmbList";
	var param = "strutsAction=&maEqMstrAsmbListDTO.pmId=" + _pmId
	           +"&maEqMstrCommonDTO.equipId="+_equipId
	           + "&isLinked=Y";
	
	openQuickPage(param, _url); 
}

/*
 * 고장이력 보기
 */
function goEqbmList(eqlocId, eqlocDesc, equipId, equipDesc, fromDate, toDate, woStatus, linkedUsageDeptId)
{
	//eqlocId, eqlocDesc, equipId, equipDesc, fromDate, toDate, linkedUsageDeptId
	//(설비위치,설비위치명,설비,설비명,시작일자,종료일자,작업상태,사용부서)
	
	if(typeof eqlocId=="undefined" && typeof equipId=="undefined") return;

	if(typeof woStatus=="undefined") woStatus = "";
	if(typeof linkedUsageDeptId == "undefined") linkedUsageDeptId = "";
	
	var url = "maWoResultMstrList";
	var param = "strutsAction=&maEqMstrAsmbListDTO.filterEqLocId=" + eqlocId
			  + "&maWoResultMstrCommonDTO.filterEqLocDesc=" + eqlocDesc
			  + "&maWoResultMstrCommonDTO.filterEquipId=" + equipId
			  + "&maWoResultMstrCommonDTO.filterEquipDesc=" + equipDesc
			  + "&maWoResultMstrCommonDTO.filterStartDate=" + fromDate
			  + "&maWoResultMstrCommonDTO.filterEndDate=" + toDate
			  + "&maWoResultMstrCommonDTO.filterWoTypeId=" + "BM"
			  + "&maWoResultMstrCommonDTO.filterWoTypeDesc=" + getSysCodeDesc("WO_TYPE","BM")
			  + "&isLinked=Y";

	// 작업상태
	if(typeof woStatus!="undefined" && woStatus != ""){
		param = param + "&maWoResultMstrCommonDTO.filterWoStatus=" + woStatus;
	}
	
	// 사용부서(linked)
	if(typeof linkedUsageDeptId!="undefined"  && linkedUsageDeptId != ""){
		param = param + "&maWoResultMstrCommonDTO.linkedUsageDeptId=" + linkedUsageDeptId;
	}

	openQuickPage(param, url); 
}
/*
 * 예방작업기준서 보기
 */
function goPmmstrList(equipId, equipDesc)
{
	if(typeof equipId=="undefined") return;

	var url   = contextPath + "/maPmMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
	var param = "isDecoratorName=popupPage"+
				"&maPmMstrCommonDTO.strutsAction="+
				"&maPmMstrCommonDTO.equipId="+equipId+
				"&maPmMstrCommonDTO.equipDesc="+equipDesc
				+ "&isLinked=Y";
	
	//post 전송
	openWindowWithPost(url, "PM_LIST", param, pos);
}
/*
 * 예방작업이력 보기
 */
function goPmwoList(equipId, equipDesc, fromDate, toDate)
{
	if(typeof equipId=="undefined") return;
	
	var url = "maWoResultMstrList";
	var param = "strutsAction="
			  + "&maWoResultMstrCommonDTO.filterEquipId=" + equipId
			  + "&maWoResultMstrCommonDTO.filterEquipDesc=" + equipDesc
			  + "&maWoResultMstrCommonDTO.filterStartDate=" + fromDate
			  + "&maWoResultMstrCommonDTO.filterEndDate=" + toDate
			  + "&maWoResultMstrCommonDTO.filterWoTypeId=" + "PM"
			  + "&isLinked=Y";

	openQuickPage(param, url); 
}
/*
 * 사용부품 보기
 */
function goUsepartsList(equipId, equipDesc, fromDate, toDate)
{
	if(typeof equipId=="undefined") return;
	
	var url   = contextPath + "/maWoPtHistList.do";
	
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "isDecoratorName=popupPage"+
				"&maWoPtHistCommonDTO.strutsAction="+
				"&maWoPtHistCommonDTO.filterStartDate=" + fromDate +
				"&maWoPtHistCommonDTO.filterEndDate=" + toDate +
				"&maWoPtHistCommonDTO.filterEquipId="+equipId+
				"&maWoPtHistCommonDTO.filterEquipDesc="+equipDesc
				+ "&isLinked=Y";
	//post 전송
	openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
}

/**
 * param : partId
 * ex) goPtinfo(partId)
 * 부품 정보 보기
 */
function goPtinfo(_partId) 
{
	var _url = "maPtMstrDetail";
	
	//Open Part Detail
	var param = "strutsAction=" + strutsPartDetail +
				"&maPtMstrCommonDTO.partId="+_partId
				+ "&isLinked=Y";
		
	openQuickTabPage(param, _url); 
}

/**
 * param : partId, partDesc
 * ex) goPtrechist(partId, partDesc)
 * 부품입고 이력보기
 */
function goPtrechist(_partId, _partDesc) 
{
	var _url = "maPtRecStatList";
	
	//Open List
	var param = "strutsAction=" 
		+ "&maPtRecStatCommonDTO.filterPartId="+_partId
		+ "&maPtRecStatCommonDTO.filterPartDesc="+_partDesc
		+ "&isLinked=Y";
	
	openQuickPage(param, _url); 
}

/**
 * param : partId, partDesc
 * ex) goPtisshist(partId, partDesc)
 * 부품출고 이력보기
 */
function goPtisshist(_partId, _partDesc) 
{
	var _url = "maPtIssList";
	
	//Open List
	var param = "strutsAction=" 
		+ "&maPtIssCommonDTO.partId="+_partId
		+ "&maPtIssCommonDTO.partDesc="+_partDesc
		+ "&isLinked=Y";
	
	openQuickPage(param, _url); 
}

/**
 * param : partId, partDesc
 * ex) goPtusehist(partId, partDesc)
 * 부품사용 이력보기
 */
function goPtusehist(_partId, _partDesc) 
{
	var _url = "maWoPtHistList";
	
	//Open List
	var param = "strutsAction=" 
		+ "&maWoPtHistCommonDTO.filterPartId="+_partId
		+ "&maWoPtHistCommonDTO.filterPartDesc="+_partDesc
		+ "&isLinked=Y";
	
	openQuickPage(param, _url); 
}

/**
 * param : partId, partDesc
 * ex) goPtcurrstock(partId, partDesc)
 * 부품 현재고 보기
 */
function goPtcurrstock(_partId, _partDesc) 
{
	var _url = "maPtStckList";
	
	//Open List
	var param = "strutsAction=" 
			  + "&maPtStckCommonDTO.partId="+_partId
			  + "&maPtStckCommonDTO.filterPartDesc="+_partDesc
			  + "&isLinked=Y";

	openQuickPage(param, _url); 
}

/*
 * 변경이력보기
 */
function goRevisionhistoryList(equipId, itemNo, revObjType)
{
	if(typeof equipId=="undefined") return;
	
	openRevHistory(equipId, itemNo, revObjType);

	/*var url = "commRevHistList";
	var param = "strutsAction=&commRevHistCommonDTO.objectId=" + equipId
	+ "&commRevHistCommonDTO.objectNo=" + itemNo;
	
	openQuickPage(param, url); */
}
/*
 * 설비이력출력
 */
function goEqpdf(reportName, qrdName, compNo, userId, langId, equipId)
{
	if(typeof equipId=="undefined") return;

	reportCall(reportName, qrdName, compNo, userId, langId, equipId);
}

/*
 * 금형이력출력
 */
function goMoldEqPdf(reportName, qrdName, compNo, userId, langId, equipId)
{
	if(typeof equipId=="undefined") return;

	reportCall(reportName, qrdName, compNo, userId, langId, equipId);
}

/*
 * 개정이력
 */
function goHistoryList(equipId, itemNo)
{
	if(typeof equipId=="undefined") return;

	var url = "maEqMstrHistList";
	var param = "strutsAction=&maEqMstrHistListDTO.equipId=" + equipId
	  		+ "&maEqMstrHistListDTO.equipDesc=" + itemNo
	  		+ "&isLinked=Y";

	openQuickPage(param, url); 
}
/*
 * @Deprecated
 * 설비등록카드
 */
function goEqinfopdf(reportName, qrdName, compNo, userId, langId, equipId, imagePath)
{
	if(typeof equipId=="undefined") return;

	reportCall(reportName, qrdName, compNo, userId, langId, equipId, imagePath);
}

/*
 * 설비등록카드
 */
function goRpteqinfopdf(rptNo, form, addParam)
{
	printSelect(rptNo, form, addParam);
}

/*
 * 금형등록카드
 */
function goMoldEqInfoPdf(reportName, qrdName, compNo, userId, langId, equipId, imagePath)
{
	if(typeof equipId=="undefined") return;

	reportCall(reportName, qrdName, compNo, userId, langId, equipId, imagePath);
}

/**
 * param : uploadTypeId, uploadType
 * ex) goExlupload(uploadTypeId, uploadType)
 * 엑셀 업로드
 */
function goExlupload(_uploadTypeId, _uploadType, _tableName)
{
	var _url = "mgrExldataList.do";
	
 	//Open List
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param =  "isDecoratorName=popupPage"
    	+ "&strutsAction=" 
		+ "&mgrExldataCommonDTO.filterExcelTabId="+_uploadTypeId
		+ "&mgrExldataCommonDTO.filterExcelTabDesc="+_uploadType
		+ "&mgrExldataCommonDTO.filterTableName="+_tableName
		+ "&isLinked=Y";
	
	//openQuickPage(param, _url); 
	openWindowWithPost(_url, "OPEN_EXL", param, pos);	
}

/*
 * 요청접수 목록 보기
 */
function goWoreqList(_woNo)
{
	if(typeof _woNo=="undefined") return;
	
	var _url = "maWoReqList.do";
	var param = "isDecoratorName=popupPage&strutsAction=&maWoReqCommonDTO.filterWoNo=" + _woNo
	+ "&isLinked=Y";
	
	//Open List
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	openWindowWithPost(_url, "OPEN_LINK", param, pos); 
}

/*
 * 수리불가목록출력
 */
function goRepairxpdf(reportName, qrdName, compNo, langId, filterPlantDesc, filterDeptDesc, filterStartDate, filterEndDate)
{
	reportCall(reportName, qrdName, compNo, langId, filterPlantDesc, filterDeptDesc, filterStartDate, filterEndDate);
}

/*
 * 교정이력 보기
 */
function goWopmcList(eqlocId, eqlocDesc, parEquipId, parEquipDesc, equipId, equipDesc, fromDate, toDate)
{
	if(typeof eqlocId=="undefined" && typeof equipId=="undefined") return;
	
	var url = "maWoPmcResultMstrList";
	var param = "strutsAction=&maEqMstrAsmbListDTO.filterEqLocId=" + eqlocId
			  + "&maWoResultMstrCommonDTO.filterEqLocDesc=" + eqlocDesc
			  + "&maWoResultMstrCommonDTO.filterParEquipId=" + parEquipId
			  + "&maWoResultMstrCommonDTO.filterParEquipDesc=" + parEquipDesc
			  + "&maWoResultMstrCommonDTO.filterEquipId=" + equipId
			  + "&maWoResultMstrCommonDTO.filterEquipDesc=" + equipDesc
			  + "&maWoResultMstrCommonDTO.filterStartDate=" + fromDate
			  + "&maWoResultMstrCommonDTO.filterEndDate=" + toDate
			  + "&isLinked=Y";

	openQuickPage(param, url); 
}

/*
 * Audit Trail
 */
function goAudTrailList(objectId, fileName)
{
	if(typeof objectId=="undefined") return;
	
	var url   = contextPath + "/mgrAudTrailList.do";
 	var popWidth = 850;
 	var popHeight = 540;
     // pop up이 중앙에 위치하게 한다.
     var TopPosition  = (screen.height/2 - popHeight/2);
     var LeftPosition = (screen.width/2 - popWidth/2);

     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
 	
 	var param = "isDecoratorName=popupPage";
 	param += "&mgrAudTrailCommonDTO.strutsAction=";
 	param += "&mgrAudTrailCommonDTO.objectId=" + objectId;
	param += "&mgrAudTrailCommonDTO.fileName=" + fileName
	param += "&isLinked=Y";

 	//post 전송
 	openWindowWithPost(url, "AUDTRAIL_POPUP", param, pos);
	
}


/*
 * linkedfunc_method : vendor --> function name : goVendorLink 
 */
function goVendorLink(_pageId)
{
	//Open vendor List 
	openQuickTabPage("isLinked=Y", "maVendorList"); 
}
/**
 * param : vendorId, compNo
 * ex) goVendorDetail(vendorId, compNo)
 */
function goVendorDetail() 
{
	//Set Parameters (0:vendorId, 1:compNo) 
	var _vendorId, _compNo, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _vendorId = arguments[i];
		else if(i == 1) _compNo = arguments[i];
    }
	
	//valid parameter
	if(typeof _compNo == "undefined") _compNo = "";
	if(typeof _vendorId == "undefined" || _vendorId == "")
	{
		goVendorList(_compNo);
		return;
	}
	if(typeof _url == "undefined" || _url == "") _url = "maVendorDetail";
	
	var param = "strutsAction="+strutsVendorDetail +
				"&maVendorCommonDTO.vendorId="+_vendorId +
				"&maVendorCommonDTO.compNo="+_compNo
				+ "&isLinked=Y";
							
	openQuickTabPage(param, _url); 
}
/*
 * 거래처목록 보기
 */
function goVendorList(_compNo)
{
	var _url = "maVendorList";
	var param = "strutsAction=&maVendorCommonDTO.compNo=" + _compNo
	           +"&maVendorCommonDTO.vendorId="
	           + "&isLinked=Y";
	
	openQuickPage(param, _url); 
}
/*
 * 고장영향성평가 보기
 */
function goFmeaList(_equipId, _equipDesc, _wkorId)
{
	var _url = "workFmeaList.do";
	var param = "isDecoratorName=popupPage"
			  + "&workFmeaCommonDTO.strutsAction="
			  + "&workFmeaCommonDTO.filterEquipId=" + _equipId
			  + "&workFmeaCommonDTO.filterEquipDesc=" + _equipDesc
			  + "&workFmeaCommonDTO.wkorId=" + _wkorId
			  + "&workFmeaCommonDTO.filterReqStartDate="
			  + "&isLinked=Y";
 	
	var popWidth = 850;
 	var popHeight = 540;
     // pop up이 중앙에 위치하게 한다.
     var TopPosition  = (screen.height/2 - popHeight/2);
     var LeftPosition = (screen.width/2 - popWidth/2);

     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
 	
 	openWindowWithPost(_url, "WORK_POPUP", param, pos);

}

/*
 * 요청접수 목록 보기 (투자)
 */
function goInvtWoreqList(_invtListId)
{
	if(typeof _invtListId=="undefined") return;
	
	var _url = "maWoReqList.do";
	var param = "isDecoratorName=popupPage&strutsAction="
			+ "&maWoReqCommonDTO.invtlistId="+_invtListId
			+ "&isLinked=Y";
	
	//Open List
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
	openWindowWithPost(_url, "OPEN_INVT_LINK", param, pos); 
}

/*
 * 기준서 보기
 */
function goPmstdList(pmId, fileName)
{
	if(typeof pmId=="undefined" || pmId == "") return;
	
	var url   = contextPath + "/"+fileName+".do";
	var popWidth = 1010;
	var popHeight = 640;
	
	// pop up이 중앙에 위치하게 한다.
	var TopPosition  = (screen.height/2 - popHeight/2);
	var LeftPosition = (screen.width/2 - popWidth/2);
	
	var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "strutsAction=8001"+
				"&maPmMstrCommonDTO.pmId="+pmId
				+ "&isLinked=Y";
	//post 전송
	openWindowWithPost(url, "PM_DETAIL", param, pos);
}

/*
 * WO 보기
 */
function goWoList(_params)
{
	var url   = contextPath + "/maWoResultMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
	var param = "isDecoratorName=popupPage"+
				"&maWoResultMstrCommonDTO.strutsAction="+
				"&ACTION_FUNCTION=goSearch"+
				"&isLinked=Y";
    
    if(typeof _params == "object") {
		for(var _key in _params)
		{
			if(_key.toUpperCase() == "STARTDATE") param = param + "&maWoResultMstrCommonDTO.filterStartDate="+_params[_key];
			else if((_key.toUpperCase() == "ENDDATE")) param = param + "&maWoResultMstrCommonDTO.filterEndDate="+_params[_key];
			else if((_key.toUpperCase() == "PLANT")) param = param + "&maWoResultMstrCommonDTO.filterPlantId="+_params[_key];
			else if((_key.toUpperCase() == "PLANTDESC")) param = param + "&maWoResultMstrCommonDTO.filterPlantDesc="+_params[_key];
			else if((_key.toUpperCase() == "EQUIPID")) param = param + "&maWoResultMstrCommonDTO.filterEquipId="+_params[_key];
			else if((_key.toUpperCase() == "EQUIPDESC")) param = param + "&maWoResultMstrCommonDTO.filterEquipDesc="+_params[_key];
			else if((_key.toUpperCase() == "PARTID")) param = param + "&maWoResultMstrCommonDTO.filterPartId="+_params[_key];
			
			else if((_key.toUpperCase() == "EMPID")) param = param + "&maWoResultMstrCommonDTO.filterEmpId="+_params[_key];
			else if((_key.toUpperCase() == "EMPDESC")) param = param + "&maWoResultMstrCommonDTO.filterEmpDesc="+_params[_key];
			else if((_key.toUpperCase() == "DEPTID")) param = param + "&maWoResultMstrCommonDTO.filterDeptId="+_params[_key];
			else if((_key.toUpperCase() == "DEPTDESC")) param = param + "&maWoResultMstrCommonDTO.filterDeptDesc="+_params[_key];
			else if((_key.toUpperCase() == "WKCTRID")) param = param + "&maWoResultMstrCommonDTO.filterWkCtrId="+_params[_key];
			else if((_key.toUpperCase() == "WKCTRDESC")) param = param + "&maWoResultMstrCommonDTO.filterWkCtrDesc="+_params[_key];
			else if((_key.toUpperCase() == "PMTYPE")) param = param + "&maWoResultMstrCommonDTO.filterPmTypeId="+_params[_key];
			else if((_key.toUpperCase() == "PMTYPEDESC")) param = param + "&maWoResultMstrCommonDTO.filterPmTypeDesc="+_params[_key];
			else if((_key.toUpperCase() == "WOTYPE")) param = param + "&maWoResultMstrCommonDTO.filterWoTypeId="+_params[_key];
			else if((_key.toUpperCase() == "WOTYPEDESC")) param = param + "&maWoResultMstrCommonDTO.filterWoTypeDesc="+_params[_key];
		}
    	
    }

	//post 전송
	openWindowWithPost(url, "WO_LIST_POPUP", param, pos);
}

/*
 * 작업계획서 보기
 */
function goWoplanList(_params)
{
    var wkorId = "";
    var woparam = "woPlanDetail";
    
    var url   = contextPath + "/"+woparam+".do";
    
    if(typeof _params == "object") {
    	wkorId = _params["wkorId"];
    }
    
    var param = "strutsAction=1012&woPlanCommonDTO.wkOrId="+ wkorId;
    
    ajaxPost(url, param).done(function(_data){
    	jsonObj = JSON.parse(_data);
    	if(jsonObj.isExist == '0'){
    		alertMessage1(COMMON_CSMG013);
 	       	return;
    	}
    	else{
    		var popWidth = 1010;
    	    var popHeight = 640;
    	 
    	    // pop up이 중앙에 위치하게 한다.
    	    var TopPosition  = (screen.height/2 - popHeight/2);
    	    var LeftPosition = (screen.width/2 - popWidth/2);
    	 
    	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
    	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    	    
    		param = "strutsAction=8001&woPlanCommonDTO.wkOrId="+ wkorId
    		+ "&isLinked=Y";
    		openWindowWithPost(url, "WOPLAN_DETAIL", param, pos);
    	}
    });
}


/**
 * param : wkorId, url
 * ex) goWo(wkorId, url)
 */
function goWo() 
{
	//Set Parameters (0:wkorId, 1:url) 
	var _wkorId, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _wkorId = arguments[i];
		else if(i == 1) _url = arguments[i];
    }
	
	if(typeof _url == "undefined" || _url == "") _url = "maWoResultMstrDetail";
	
	var param = "strutsAction="+strutsWoRsltDetail +
				"&maWoResultMstrCommonDTO.wkOrId="+_wkorId
				+ "&isLinked=Y";
							
	openQuickTabPage(param, _url); 
}

/**
 * param : woReqId
 * ex) goWoreqdetail(woReqId)
 */
function goWoreqdetail() 
{
	//Set Parameters (0:woReqId) 
	var _woReqId, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _woReqId = arguments[i];
	}
	
	_url = "maWoReqDetail";
	
	var param = "strutsAction="+strutsWoReqDetail +
				"&maWoReqCommonDTO.woReqId="+_woReqId
				+ "&isLinked=Y";
	
	openQuickTabPage(param, _url); 
}

/**
 * param : pmInsListId, url
 * ex) goWopmi(pmInsListId, url)
 */
function goWopmi() 
{
	//Set Parameters (0:pmInsListId, 1:url) 
	var _pmInsListId, _url;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) _pmInsListId = arguments[i];
		else if(i == 1) _url = arguments[i];
    }

	if(typeof _url == "undefined" || _url == "") _url = "workPmiDetail";
	
	var param = "strutsAction="+strutsWoPmiDetail +
				"&workPmiCommonDTO.pminslistId="+_pmInsListId
				+ "&isLinked=Y";
	
	openQuickTabPage(param, _url); 
}

/**
 * param : usrgrpId, fileName
 */
function goPageAuthList(_params)
{
	var url   = contextPath + "/mgrUsrGrpPageAuthList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var usrgrpId = "";
    var fileName = "";
    
    if(typeof _params == "object") {
    	usrgrpId = _params["usrgrpId"];
    	fileName = _params["fileName"];
    }
	
	var param = "isDecoratorName=popupPage"+
				"&strutsAction="+
				"&mgrUsrGrpPageAuthDTO.filterUsrgrpId="+usrgrpId +
				"&mgrUsrGrpPageAuthDTO.filterFileName="+fileName +
				"&ACTION_FUNCTION=goSearch"
				+ "&isLinked=Y";

	//post 전송
	openWindowWithPost(url, "_blank", param, pos);
}

/*
 * 선택목록출력
 */
function goWorklistprint(reportName, qrdName, compNo, langId, startDate, endDate, chkedRowsId)
{
	if(typeof chkedRowsId=="undefined") return;

	reportCall(reportName, qrdName, compNo, langId, startDate, endDate, chkedRowsId);
	
}


