/*========================================
tabPage decorator 에서 QuickMenu와 Link를 위해
parent(quick menu)의 펑션을 호출한다.
tabPage에는 quickMenu가 include되어 있지 않음
========================================*/

/**
 * quik menu에서 선택시 
 * EQUIP NO가 있는경우 상세로 간다.
 */
function goQuickEqMstrDetail(quickEquipNo)
{
	parent.goQuickEqMstrDetail(quickEquipNo);
}

/**
 * quik menu에서 선택시 
 * WO NO[마스터 번호]가 있는경우 작업요청 상세로 간다.
 */
function goQuickWoReqDetail(quickReqNo)
{
	parent.goQuickWoReqDetail(quickReqNo);
}

/**
 *quick menu에서 자재마스터를 선택하였을 경우
 *[partNo]가 있으면 상세 화면으로 이동하고 그렇치 않을 경우 
 *List화면으로 이동시킨다.
 */
function goQuickPtMstrDetail(partNo)
{
	parent.goQuickPtMstrDetail(partNo);
}

/**
 * 작업결과 입력 상세로 이동
 */
function goQuickWoResultDetail(woNo)
{
	parent.goQuickWoResultDetail(woNo);
}

/**
 * 예방정비 마스터 상세로 이동
 */
function goQuickPmMstrDetail(pmNo)
{
	parent.goQuickPmMstrDetail(pmNo);
}

/**
 * 점검 마스터 상세로 이동
 */
function goQuickPmiMstrDetail(checkListNo)
{
	parent.goQuickPmiMstrDetail(checkListNo);
}

/**
 * 작업설계 상세로 이동
 */
function goQuickWoPlanDetail(checkListNo)
{
	parent.goQuickWoPlanDetail(checkListNo);
}

/**
 * 위치 이동
 */
function goQuickLocMng(equipNo)
{
	parent.goQuickLocMng(equipNo);
}