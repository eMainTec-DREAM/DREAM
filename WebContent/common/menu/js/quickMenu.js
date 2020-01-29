/** 설비마스터 목록 */
var quickEqListPageId = "eqMstrList";
/** 설비마스터 상세 */
var quickEqDetailPageId = "eqMstrDetail";
/** 작업요청 목록 */
var quickWoReqListPageId = "woReqList";
/** 작업요청 상세 */
var quickWoReqDetailPageId = "woReqDetail";

/** 자재마스터 목록 */
var quickPtListPageId = "ptMstrList";
/** 자재마스터 상세 */
var quickPtDetailPageId = "ptMstrDetail";

/** 작업실적 목록 */
var quickWoResultListPageId = "woResultList";
/** 작업실적 상세 */
var quickWoResultDetailPageId = "woResultDetail";

/** 작업계획 목록 */
var quickWoPlanListPageId = "woPlanList";
/** 작업계획 상세 */
var quickWoPlanDetailPageId = "woPlanDetail";

/** 예방정비 목록 */
var quickPmListPageId = "pmMstrList";
/** 예방정비 상세 */
var quickPmDetailPageId = "pmMstrDetail";

/** 예방점검 목록 */
var quickPmiListPageId = "pmiMstrList";
/** 예방점검 상세 */
var quickPmiDetailPageId = "pmiMstrDetail";
/** 위치 관리 */
var quickLocMngPageId = "eqLocMng";

/**
 * 위치관리 호출
 */
function callGoQuickLocMng()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickLoc)=="function") doc.goQuickLoc();
	else goQuickListPage(quickLocMngPageId);
}

function goQuickLocMng(equipNo)
{
	if (equipNo == null || trim(equipNo) == "")
	{
		goQuickListPage(quickLocMngPageId);
		return;
	}
	bottomForm.elements['eqLocMngDTO.code'].value = equipNo;
	goQuickDetailPage(bottomForm, quickLocMngPageId);
	bottomForm.elements['eqLocMngDTO.code'].value = "";	// clear
}

/**
 * 설비 마스터 호출
 */
function callGoQuickEqMstr()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickEqMstr)=="function") doc.goQuickEqMstr();
	else goQuickListPage(quickEqListPageId);
}

/**
 * quik menu에서 선택시 
 * EQUIP NO가 있는경우 상세로 간다.
 */
function goQuickEqMstrDetail(quickEquipNo)
{
	if (quickEquipNo == null || trim(quickEquipNo) == "")
	{
		goQuickListPage(quickEqListPageId);
		return;
	}
	bottomForm.elements['eqMstrCommonDTO.equipNo'].value = quickEquipNo;
	goQuickDetailPage(bottomForm, quickEqDetailPageId);
}

/**
 * 작업요청 마스터 호출
 */
function callGoQuickWoReq()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickWoReq)=="function") doc.goQuickWoReq();
	else goQuickListPage(quickWoReqListPageId);
}

/**
 * quik menu에서 선택시 
 * REQ NO[마스터 번호]가 있는경우 작업요청 상세로 간다.
 */
function goQuickWoReqDetail(quicReqNo)
{
	if (quicReqNo == null || trim(quicReqNo) == "")
	{
		goQuickListPage(quickWoReqListPageId);
		return;
	}
	
	bottomForm.elements['woReqCommonDTO.reqNo'].value = quicReqNo;
	goQuickDetailPage(bottomForm, quickWoReqDetailPageId);
}

/**
 * quick menu에서 자재마스터를 눌렀을 경우  
 */
function callGoQuickPtMstr()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickPtMstr)=="function") doc.goQuickPtMstr();
	else goQuickListPage(quickPtListPageId);
}

/**
 *quick menu에서 자재마스터를 선택하였을 경우
 *[partNo]가 있으면 상세 화면으로 이동하고 그렇치 않을 경우 
 *List화면으로 이동시킨다.
 */
function goQuickPtMstrDetail(partNo)
{
	//partNo가 공백이거나 null이면 List page로 이동 시킨다.
	if(partNo == null || trim(partNo) == "")
	{
		goQuickListPage(quickPtListPageId);
		return;
	}
		
	bottomForm.elements['ptMstrListDTO.partNo'].value = partNo;
	goQuickDetailPage(bottomForm, quickPtDetailPageId);
}

/**
 * 작업계획
 */
function callGoQuickWoPlan()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickWoPlan)=="function") doc.goQuickWoPlan();
	else goQuickListPage(quickWoPlanListPageId);
}

/**
 * 작업계획 상세
 */
function goQuickWoPlanDetail(woNo)
{
	if (woNo == null || trim(woNo) == "")
	{
		goQuickListPage(quickWoPlanListPageId);
		return;
	}
	
	bottomForm.elements['woPlanCommonDTO.woNo'].value = woNo;
	goQuickDetailPage(bottomForm, quickWoPlanDetailPageId);
}

/**
 * 작업결과입력 마스터 호출
 */
function callGoQuickWoResult()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickWoResult)=="function") doc.goQuickWoResult();
	else goQuickListPage(quickWoResultListPageId);
}

/**
 * 작업결과 입력 상세로 이동
 */
function goQuickWoResultDetail(woNo)
{
	if (woNo == null || trim(woNo) == "")
	{
		goQuickListPage(quickWoResultListPageId);
		return;
	}
	
	bottomForm.elements['woResultCommonDTO.woNo'].value = woNo;
	goQuickDetailPage(bottomForm, quickWoResultDetailPageId);
}

/**
 * 예방정비마스터
 */
function callGoQuickPmMstr()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickPmMstr)=="function") doc.goQuickPmMstr();
	else goQuickListPage(quickPmListPageId);
}

/**
 * 예방정비 마스터 상세로 이동
 */
function goQuickPmMstrDetail(pmNo)
{
	if (pmNo == null || trim(pmNo) == "" || !numberCheck(pmNo))
	{
		goQuickListPage(quickPmListPageId);
		return;
	}
	bottomForm.elements['pmMstrCommonDTO.pmNo'].value = pmNo;
	goQuickDetailPage(bottomForm, quickPmDetailPageId);
}

/**
 * 예방정검마스터
 */
function callGoQuickPmiMstr()
{
	var doc = getCurrentDoc();
	if(typeof(doc.goQuickPmiMstr)=="function") doc.goQuickPmiMstr();
	else goQuickListPage(quickPmiListPageId);
}

/**
 * 점검 마스터 상세로 이동
 */
function goQuickPmiMstrDetail(checkListNo)
{
	if (checkListNo == null || trim(checkListNo) == "")
	{
		goQuickListPage(quickPmiListPageId);
		return;
	}
	bottomForm.elements['pmiMstrCommonDTO.checkListNo'].value = checkListNo;
	goQuickDetailPage(bottomForm, quickPmiDetailPageId);
}

/**
 * Quick 목록으로 이동
 * @param _pageId
 */
function goQuickListPage(_pageId)
{	
	quickFormSubmit(bottomForm, _pageId, '');
}

/**
 * Quick 상세로 이동
 * @param _form
 * @param _pageId
 */
function goQuickDetailPage(_form, _pageId)
{	
	quickFormSubmit(_form, _pageId, BASE_QUICK_DETAIL);
}

/**
 * quick menu 로 열때 
 * isDecoratorName 을 popupPage로 셋팅을 하여 popup으로 열리게 한다.
 */
function quickFormSubmit(formObj, _pageId, _strutsAction)
{
	formObj.strutsAction.value = _strutsAction;
	
	var quickParam = FormQueryString(formObj);
	
	// bottom을 다시 초기회 시킨다.
	formObj.action = "";
	formObj.strutsAction.value = "";

	openQuickPage(quickParam, _pageId);
}