/*====================================================
 * 공통 LOV 처리를 한다.
 * strutsAction 은 commonInclude.jsp 에 정의 한다.
 *===================================================*/

//======================
// lov 사용 global변수
var lovValueId;
var lovGridObj;
var lovGridRow;
var dhxWins;

var lovCalEditorObj;    // grid 달력 editor input object
//======================


/**
 * 공통 코드 LOV 기본 popup
 * html 에서 사용한다.
 * pValueId : html code id
 * pDescId  : html description id
 * pLovType : list of value type 
 */ 
function lovUsrDir(_codeId, _descId, _codeType, _codeKind)
{
	lovUsrDirGrid(null, null, _codeId, _descId, _codeType, _codeKind);
}

/**
 * 공통 코드 LOV 기본 popup
 * html 에서 사용한다.
 * pValueId : html code id
 * pDescId  : html description id
 * pLovType : list of value type 
 */ 
function lovUsrDirGrid(_grid, _rowIndex, _codeId, _descId, _codeType, _codeKind)
{
    // 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = _codeId;
    pValId[1] = _descId;
    
    lovValueId      = pValId;
    lovGridObj      = _grid;
    lovGridRowIndex = _rowIndex;

    var url =  contextPath + "/listOfVal.do";
        
    var param = "strutsAction=" + lovUsrDirDefaultAction +
                "&" + "listOfValDTO.codeType=" + _codeType +
                "&" + "listOfValDTO.codeKind=" + _codeKind 
                ;
                
    var popWidth = 350;
    var popHeight = 520;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=no, status=no";
    
//    window.open(url + param, "LOV_DIR", pos);
    openLayerPopup("listOfVal", param);
}

/**
 * 공통 코드 LOV 기본 popup
 * html 에서 사용한다.
 * pValueId : html code id
 * pDescId  : html description id
 * pLovType : list of value type 
 */ 
function lovSysDir(_codeId, _descId, _codeType, extCode1)
{
	if(typeof extCode1 == "object")extCode1 = JSON.stringify(extCode1);
	
	lovSysDirGrid(null, null, _codeId, _descId, _codeType, extCode1);
}

/**
 * 공통 코드 LOV 기본 popup
 * html 에서 사용한다.
 * pValueId : html code id
 * pDescId  : html description id
 * pLovType : list of value type 
 */ 
function lovSysDirGrid(_grid, _rowIndex, _codeId, _descId, _codeType, extCode1)
{
    // 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = _codeId;
    pValId[1] = _descId;

    lovValueId      = pValId;
    if(typeof extCode1 == "undefined") extCode1 = "";
    var param = "strutsAction=" + lovSysDirDefaultAction +
                "&" + "listOfValDTO.codeType=" + _codeType;

    if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "listOfValDTO.extCode1=" + extCode1; 
    }
//    window.open(url + param, "LOV_DIR", pos);

    openLayerPopup("listOfVal", param);
}
 
/**
 * 공통 Table LOV 기본 popup
 * html 에서 사용한다.
 * pValueId : html code id
 * pDescId  : html description id
 * pLovType : list of value type 
 * gridObj  : grid object ( Sigma.activeGrid )
 * extCode1 : 필요시 사용(다른 검색조건 필요시)
 */ 
function lovTable(pCodeId, pDescId, pCodeType, extCode1, codeKind)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = pCodeId;
    pValId[1] = pDescId;
    
    lovValueId      = pValId;
	
	var param = "strutsAction=" + lovTableDefaultAction +
	            "&" + "listOfValDTO.codeType=" + pCodeType;

    if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "listOfValDTO.extCode1=" + extCode1; 
    }
    if (codeKind && "" != codeKind)
    {
	   param = param + "&" + "listOfValDTO.codeKind=" + M$(codeKind).value;
    }
	var url =  contextPath + "/listOfVal.do";

    openLayerPopup("listOfVal", param);
//	window.open(url + param, 'LOV', features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
	
}

/**
 * 자재팝업
 */ 
function lovParts(partId, partNo, partDesc, maker, model, plfType, plfTypeDesc,useQty,lastPrice, codeType, gridObj, extCode1, extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = partId;
    pValId[1] = partNo;
    pValId[2] = partDesc;
    pValId[3] = maker;
    pValId[4] = model;
    pValId[5] = plfType;
    pValId[6] = plfTypeDesc;
    pValId[7] = useQty;
    pValId[8] = lastPrice;
    
    lovValueId      = pValId;
    lovGridObj      = gridObj;
    
    if(lovGridObj)
    lovGridRowIndex = gridObj.activeRowNo;
    
	width  = 450;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovPartsListDTO.codeType=" + codeType;
    
	var url =  contextPath + "/lovPartsList.do";
	
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovPartsListDTO.extCode1=" + extCode1; 
    }
	if (extCode2 && "" != extCode2)
    {
 	   param = param + "&" + "lovPartsListDTO.extCode2=" + extCode2; 
     }

    openLayerPopup("lovPartsList", param);
//	window.open(url + param, 'LOV', features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
	
}

/**
 * 자재팝업
 */ 
function lovTools(partId, partNo, partDesc, maker, model, plfType, plfTypeDesc,useQty, codeType, gridObj, extCode1, extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = partId;
    pValId[1] = partNo;
    pValId[2] = partDesc;
    pValId[3] = maker;
    pValId[4] = model;
    pValId[5] = plfType;
    pValId[6] = plfTypeDesc;
    pValId[7] = useQty;
    
    lovValueId      = pValId;
    lovGridObj      = gridObj;

    if(lovGridObj)
    lovGridRowIndex = gridObj.activeRowNo;
    
	width  = 450;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovPartsListDTO.codeType=" + codeType;
    
	var url =  contextPath + "/lovToolsList.do";
	
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovToolsListDTO.extCode1=" + extCode1; 
    }
	if (extCode2 && "" != extCode2)
    {
 	   param = param + "&" + "lovToolsListDTO.extCode2=" + extCode2; 
     }

    openLayerPopup("lovToolsList", param);
//	window.open(url + param, 'LOV', features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
	
}

/**
 * 자재팝업2
 */ 
function lovReqParts(partId, partNo, partDesc, maker, model, plfType, plfTypeDesc,useQty,ptSize, codeType, gridObj, extCode1, extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = partId;
    pValId[1] = partNo;
    pValId[2] = partDesc;
    pValId[3] = maker;
    pValId[4] = model;
    pValId[5] = plfType;
    pValId[6] = plfTypeDesc;
    pValId[7] = useQty;
    pValId[8] = ptSize;
    
    lovValueId      = pValId;
    lovGridObj      = gridObj;
    
    if(lovGridObj)
    lovGridRowIndex = gridObj.activeRowNo;
    
	width  = 450;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovReqPartsListDTO.codeType=" + codeType;
    
	var url =  contextPath + "/lovReqPartsList.do";
	
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovReqPartsListDTO.extCode1=" + extCode1; 
    }
	if (extCode2 && "" != extCode2)
    {
 	   param = param + "&" + "lovReqPartsListDTO.extCode2=" + extCode2; 
     }

    openLayerPopup("lovReqPartsList", param);
//	window.open(url + param, 'LOV', features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
	
}

/**
 * 예방점검 팝업
 */ 
function lovPmNo(pmId,pmNo,pmDesc,equipDesc,scheduleType,deptDesc,cycle,usage,periodType,pmType,remark, codeType, gridObj, extCode1, extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
    var pValId = new Array();
    pValId[0] = pmId;
    pValId[1] = pmNo;
    pValId[2] = pmDesc;
    pValId[3] = equipDesc;
    pValId[4] = scheduleType;
    pValId[5] = deptDesc;
    pValId[6] = cycle;
    pValId[7] = usage;
    pValId[8] = periodType;
    pValId[9] = pmType;
    pValId[10] = remark;
  
    lovValueId      = pValId;
    lovGridObj      = gridObj;
    
    if(lovGridObj)
    lovGridRowIndex = gridObj.activeRowNo;
    
	width  = 450;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovPmNoListDTO.codeType=" + codeType;
    
	var url =  contextPath + "/lovPmNoList.do";

	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovPmNoListDTO.extCode1=" + extCode1; 
    }
	if (extCode2 && "" != extCode2)
    {
 	   param = param + "&" + "lovPmNoListDTO.extCode2=" + extCode2; 
     }
	
    openLayerPopup("lovPmNoList", param);
//	window.open(url + param, 'LOV', features+"scrollbars=no,status=no,toolbar=no, resizable=no,location=no,menu=no,width="+width+",height="+height+"");
	
}

/**
 * 설비종류 작업부위 팝업 
 * @param eqCtgAsmbId
 * @param description
 * @param remark
 */
function lovEqCtgAsmb(eqCtgAsmbId, description,extCode1
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqCtgAsmbId;
	pValId[1] = description;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovEqCtgAsmbListDTO.codeType=" + pCodeType+
	"&" + "lovEqCtgAsmbListDTO.extCode1=" + extCode1;
	var url =  contextPath + "/lovEqCtgAsmbList.do";
	
	openLayerPopup("lovEqCtgAsmbList", param);
}

/**
 * 설비종류 작업부위 팝업 
 * @param eqCtgAsmbId
 * @param description
 * @param remark
 */
function lovRcmEqCtgAsmb(eqCtgAsmbId, description,extCode1
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqCtgAsmbId;
	pValId[1] = description;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovRcmEqCtgAsmbListDTO.codeType=" + pCodeType+
	"&" + "lovRcmEqCtgAsmbListDTO.extCode1=" + extCode1;
	var url =  contextPath + "/lovRcmEqCtgAsmbList.do";
	
	openLayerPopup("lovRcmEqCtgAsmbList", param);
}

/**
 * 설비종류 작업부위 팝업 
 * @param eqCtgAsmbId
 * @param description
 * @param remark
 */
function lovEquipCtg(eqCtgAsmbId, description,extCode1
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqCtgAsmbId;
	pValId[1] = description;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovRcmEqCtgAsmbListDTO.codeType=" + pCodeType+
	"&" + "lovRcmEqCtgAsmbListDTO.extCode1=" + extCode1;
	var url =  contextPath + "/lovEquipCtgList.do";
	
	openLayerPopup("lovEquipCtgList", param);
}
/**
 * 버튼 팝업 
 * @param buttonId
 * @param buttonDesc
 */
function lovButton(buttonId, buttonDesc
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = buttonId;
	pValId[1] = buttonDesc;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovButtonListDTO.codeType=" + pCodeType;
	var url =  contextPath + "/lovButtonList.do";
	
	openLayerPopup("lovButtonList", param);
}
/**
 * 메뉴 팝업 
 * @param menuId
 * @param menuDesc
 */
function lovMenu(menuId, menuDesc
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = menuId;
	pValId[1] = menuDesc;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovMenuListDTO.codeType=" + pCodeType;
	var url =  contextPath + "/lovMenuList.do";
	
	openLayerPopup("lovMenuList", param);
}

/**
 * 권한 팝업 
 * @param usrGrpId
 * @param groupName
 */
function lovUsrGrp(usrGrpId, groupName
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = usrGrpId;
	pValId[1] = groupName;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovUsrGrpListDTO.codeType=" + pCodeType;
	var url =  contextPath + "/lovUsrGrpList.do";
	
	openLayerPopup("lovUsrGrpList", param);
}


/**
 * 페이지 팝업 
 * @param pageId
 * @param pageDesc
 */
function lovPage(pageId, pageDesc
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = pageId;
	pValId[1] = pageDesc;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovPageListDTO.codeType=" + pCodeType;
	var url =  contextPath + "/lovPageList.do";
	
	openLayerPopup("lovPageList", param);
}
/**
 * 거래처 팝업 
 * @param vendorId
 * @param vendorNo
 * @param vendorDesc
 * @param addrNrepName
 * @param person
 * @param office
 * @param mobile
 * @param email
 * @param pCodeType
 * @param gridObj
 */
function lovVendor(vendorId, vendorNo, vendorDesc
		         , addrNrepName, person, office, mobile, email
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = vendorId;
	pValId[1] = vendorNo;
	pValId[2] = vendorDesc;
	pValId[3] = addrNrepName;
	pValId[4] = person;
	pValId[5] = office;
	pValId[6] = mobile;
	pValId[7] = email;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;

	if( typeof pCodeType == "undefined")
	{
		pCodeType = "";
	}
	
	var param = "strutsAction=1001"+
    "&" + "lovVendorListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovVendorList.do";
	
	openLayerPopup("lovVendorList", param);
}

/**
 * 부서 팝업 
 * @param deptId
 * @param deptNo
 * @param deptDesc
 * @param pCodeType dept_categ의 검색조건으로 사용.
 */
function lovDept(deptId, deptNo, deptDesc, pCodeType, pExtCode1)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = deptId;
	pValId[1] = deptNo;
	pValId[2] = deptDesc;
	if(typeof pCodeType == "undefined") pCodeType = "";
	if(typeof pExtCode1 == "undefined") pExtCode1 = "";
	
	lovValueId = pValId;
	
	width  = 750;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovDeptListDTO.codeType=" + pCodeType+
    "&" + "lovDeptListDTO.extCode1=" + pExtCode1;
	var url =  contextPath + "/lovDeptList.do";
	
	openLayerPopup("lovDeptList", param);
}

/**
 * 사원 팝업 
 * @param empId
 * @param empNo
 * @param empName
 */
function lovEmp(empId, empNo, empName, deptId, deptDesc, extCode1)
{
	if(typeof extCode1 == "object")extCode1 = JSON.stringify(extCode1);
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = empId;
	pValId[1] = empNo;
	pValId[2] = empName;
	if(typeof deptId != "undefined") pValId[3] = deptId;
	if(typeof deptDesc != "undefined") pValId[4] = deptDesc;
	
	lovValueId = pValId;
	
	width  = 750;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001";
	
	if (extCode1 && "" != extCode1)
    {
	   param += "&" + "lovEmpListDTO.extCode1=" + extCode1; 
    }
	
	var url =  contextPath + "/lovEmpList.do";
	
	openLayerPopup("lovEmpList", param);
}

/**
 * 사용자 팝업 
 * @param userId
 * @param userNo
 * @param userName
 */
function lovUser(userId, userNo, userName, email)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = userId;
	pValId[1] = userNo;
	pValId[2] = userName;
	pValId[3] = email;
	
	lovValueId = pValId;
	
	width  = 750;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001";
	
	var url =  contextPath + "/lovUserList.do";
	
	openLayerPopup("lovUserList", param);
}

/**
 * 설비 팝업 
 */
function lovEquip(equipId, equipDesc, eqLocDesc, eqCtgDesc, eqLocId, eqCtgId, eqStatusDesc, eqStatusId, extCode1)
{
	if(typeof extCode1 == "object")extCode1 = JSON.stringify(extCode1);
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = equipId;
	pValId[1] = equipDesc;
	if(typeof eqLocDesc != "undefined") pValId[2] = eqLocDesc;
	if(typeof eqCtgDesc != "undefined") pValId[3] = eqCtgDesc;
	if(typeof eqLocId != "undefinded") pValId[4] = eqLocId;
	if(typeof eqCtgId != "undefinded") pValId[5] = eqCtgId;
	if(typeof eqStatusDesc != "undefinded") pValId[6] = eqStatusDesc;
	if(typeof eqStatusId != "undefinded") pValId[7] = eqStatusId;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001&popupWidth=980";
	
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovEquipListDTO.extCode1=" + extCode1; 
    }
	
    //"&" + "lovVendorListDTO.codeType=" + pCodeType;

	openLayerPopup("lovEquipList", param);
}

/**
 * 위치분류
 */
function lovEqLoc(eqLocId, eqLocDesc,extCode1,extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqLocId;
	pValId[1] = eqLocDesc;
	
	lovValueId      = pValId;
	//eqloc_lvl_type 검색
	if( typeof extCode1 == "undefined") extCode1 = "";
	//e서qloc_lvl_type 검색
	if( typeof extCode2 == "undefined") extCode2 = "";

	var param = "strutsAction=1001"+
    "&" + "lovEqLocListDTO.eqLocLvlType=" + extCode1+
    "&" + "lovEqLocListDTO.extCode2=" + extCode2;

	openLayerPopup("lovEqLocList", param);
}

/**
 * 위치분류
 */
function lovEqCtg(eqCtgId, eqCtgDesc,extCode1,extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqCtgId;
	pValId[1] = eqCtgDesc;
	
	lovValueId      = pValId;
	if( typeof extCode1 == "undefined") extCode1 = "";
	if( typeof extCode2 == "undefined") extCode2 = "";
	var param = "strutsAction=1001"+
    "&" + "lovEqCtgListDTO.extCode1=" + extCode1+
    "&" + "lovEqCtgListDTO.extCode2=" + extCode2;

	openLayerPopup("lovEqCtgList", param);
}

/**
 * 교육
 */
function lovTrain(courseListId, certName,courseListNo,extCode1,extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = courseListId;
	pValId[1] = certName;
	pValId[2] = courseListNo;
;
	lovValueId      = pValId;
	if( typeof extCode1 == "undefined") extCode1 = "";
	if( typeof extCode2 == "undefined") extCode2 = "";
	var param = "strutsAction=1001"+
    "&" + "lovEduListDTO.extCode1=" + extCode1+
    "&" + "lovEduListDTO.extCode2=" + extCode2;

	openLayerPopup("lovEduList", param);
}

/**
 * 자격증
 */
function lovCert(certListId, certName,certNo,certTypeDesc,extCode1,extCode2)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = certListId;
	pValId[1] = certName;
	pValId[2] = certNo;
	pValId[3] = certTypeDesc;
;
	lovValueId      = pValId;
	if( typeof extCode1 == "undefined") extCode1 = "";
	if( typeof extCode2 == "undefined") extCode2 = "";
	var param = "strutsAction=1001"+
    "&" + "lovEduListDTO.extCode1=" + extCode1+
    "&" + "lovEduListDTO.extCode2=" + extCode2;

	openLayerPopup("lovCertList", param);
}

/**
 * CP
 */
function lovRcmList(rcmListId, rcmDesc)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = rcmListId;
	pValId[1] = rcmDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";
	var url =  contextPath + "/lovRcmList.do";
	openLayerPopup("lovRcmList", param);
}
/**
 * CP
 */
function lovCp(ctCtrId, ctCtrDesc)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = ctCtrId;
	pValId[1] = ctCtrDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";

	openLayerPopup("lovCtCtrList", param);
}

/**
 * Work Group 작업그룹
 */
function lovWkCtr(wkCtrId, wkCtrDesc)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = wkCtrId;
	pValId[1] = wkCtrDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";

	openLayerPopup("lovWkCtrList", param);
}

/**
 * Ref컬럼 
 */
function lovRefColumn(tableName, columnName,extCode1,refColId)
{
	var extObj = $('[name="'+extCode1+'"]');
	if(extObj)
	{
		extCode1 = extObj.val();
	}
	
	var pValId = new Array();
	pValId[0] = tableName;
	pValId[1] = columnName;
	pValId[2] = refColId;
	
	lovValueId      = pValId;
	if( typeof extCode1 == "undefined") extCode1 = "";
	var param = "strutsAction=1001"+
		"&" + "lovRefColumnListDTO.extCode1=" + extCode1;

	openLayerPopup("lovRefColumnList", param);
}

/**
 * Plant 
 */
function lovPlant(plant, plantDesc)
{
	var pValId = new Array();
	pValId[0] = plant;
	pValId[1] = plantDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";
	
	openLayerPopup("lovPlantList", param);
}

/**
 * Comp
 */
function lovComp(compNo, compDesc)
{
	var pValId = new Array();
	pValId[0] = compNo;
	pValId[1] = compDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";

	openLayerPopup("consultCompListLov", param);
}

/**
 * 근무달력 
 */
function lovWrkcal(wrkcalId, wrkcalDesc, extCode1, extCode2)
{
	var pValId = new Array();
	pValId[0] = wrkcalId;
	pValId[1] = wrkcalDesc;
	
	lovValueId      = pValId;
	
	if( typeof extCode1 == "undefined") extCode1 = "";
	if( typeof extCode2 == "undefined") extCode2 = "";

	var param = "strutsAction=1001";
	param=param+"&"+"consultCompWrkcalLovDTO.plantNo="+extCode1;
	param=param+"&"+"consultCompWrkcalLovDTO.plantDesc="+extCode2;

	openLayerPopup("consultCompWrkcalLov", param);
}

/**
 * MESLINE
 */
function lovMesLine(mesLineId, mesLineDesc)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = mesLineId;
	pValId[1] = mesLineDesc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";

	openLayerPopup("lovMesLineList", param);
}

/**
 * 자산 팝업 
 * @param assetId
 * @param assetNo
 * @param assetDesc
 * @param acqDate
 * @param buyerAmt
 * @param depAmt
 * @param resAmt
 * @param pCodeType
 * @param gridObj
 */
function lovAsset(assetId, assetNo, assetDesc
		         , acqDate, buyerAmt, depAmt, resAmt
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = assetId;
	pValId[1] = assetNo;
	pValId[2] = assetDesc;
	pValId[3] = acqDate;
	pValId[4] = buyerAmt;
	pValId[5] = depAmt;
	pValId[6] = resAmt;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovAssetListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovAssetList.do";
	
	openLayerPopup("lovAssetList", param);
}

/**
 * 무정지 팝업 
 * @param popLineId
 * @param popLineName
 * @param pCodeType
 * @param gridObj
 */
function lovLine(popLineId, popLineName, pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = popLineId;
	pValId[1] = popLineName;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovLineListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovLineList.do";
	
	openLayerPopup("lovLineList", param);
}

/**
 * 설비부위 팝업 
 * @param eqAsmbId
 * @param eqAsmbDesc
 * @param pCodeType
 * @param gridObj
 */
function lovEqAsmb(eqAsmbId, eqAsmbDesc, extCode1, pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqAsmbId;
	pValId[1] = eqAsmbDesc;
	
	if(typeof extCode1 == "object")extCode1 = JSON.stringify(extCode1);
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovEqAsmbListDTO.codeType=" + pCodeType;
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovEqAsmbListDTO.extCode1=" + extCode1; 
    }
	var url =  contextPath + "/eqAsmbValLov.do";
	
	openLayerPopup("eqAsmbValLov", param);
}

/**
 * 설비부위 팝업 
 * @param eqAsmbId
 * @param eqAsmbDesc
 * @param pCodeType
 * @param gridObj
 */
function lovTaskMap(yid, yidDesc, extCode1, extCode2
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = yid;
	pValId[1] = yidDesc;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovTaskMapListDTO.codeType=" + pCodeType;
	if (extCode1 && "" != extCode1)
    {
	   param = param + "&" + "lovTaskMapListDTO.extCode1=" + extCode1; 
    }
	if (extCode2 && "" != extCode2)
    {
 	   param = param + "&" + "lovTaskMapListDTO.extCode2=" + extCode2; 
     }
	var url =  contextPath + "/lovTaskMapList.do";
	
	openLayerPopup("lovTaskMapList", param);
}

/**
 * 설비기안 품의서 팝업
 * @param eqAppNo
 * @param eqAppId
 * @param title
 * @param contents
 * @param receiver
 * @param reqDate
 * @param appDate
 * @param pCodeType
 * @param gridObj
 */
function lovEqApp(eqAppNo, eqAppId, title, contents
		         , receiver, reqDate, appDate
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = eqAppNo;
	pValId[1] = eqAppId;
	pValId[2] = title;
	pValId[3] = contents;
	pValId[4] = receiver;
	pValId[5] = reqDate;
	pValId[6] = appDate;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovEqAppListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovAssetList.do";
	
	openLayerPopup("lovEqAppList", param);
}

/**
 * 수리기안 품의서 팝업
 * @param ptAppId
 * @param title
 * @param contents
 * @param receiver
 * @param reqDate
 * @param appDate
 * @param pCodeType
 * @param gridObj
 */
function lovPtApp(ptAppId, title, recDate, eqDesc, totAmt, contents
		, pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = ptAppId;
	pValId[1] = title;
	pValId[2] = recDate;
	pValId[3] = eqDesc;
	pValId[4] = totAmt;
	pValId[5] = contents;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
		lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
	"&" + "lovPtAppListDTO.codeType=" + pCodeType;
	
	openLayerPopup("lovPtAppList", param);
}

/**
 * 사용창고 팝업 
 * @param wcodeId
 * @param wcode
 * @param wname
 * @param pCodeType
 * @param gridObj
 */
function lovWh(wcodeId, wcode, wname,whType,outWcode,outPlant
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = wcodeId;
	pValId[1] = wcode;
	pValId[2] = wname;
	if(typeof whType != "undefined") pValId[3] = whType;
	if(typeof outWcode != "undefined") pValId[4] = outWcode;
	if(typeof outPlant != "undefined") pValId[5] = outPlant;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovWhListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovWhList.do";
	
	openLayerPopup("lovWhList", param);
}

/**
 * 사용창고 팝업 
 * @param wcodeId
 * @param wcode
 * @param wname
 * @param pCodeType
 * @param gridObj
 */
function lovWhTool(wcodeId, wcode, wname
		         , pCodeType, gridObj)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = wcodeId;
	pValId[1] = wcode;
	pValId[2] = wname;
	
	lovValueId      = pValId;
	lovGridObj      = gridObj;
	
	if(lovGridObj)
	lovGridRowIndex = gridObj.activeRowNo;
	
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001"+
    "&" + "lovAsseetListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovWhToolList.do";
	
	openLayerPopup("lovWhToolList", param);
}

/**
 * 작업검색 팝업
 * @param wkorId
 * @param woNo
 * @param woDesc
 * @param woStatus
 */
function lovWo(wkorId, woNo, woDesc, woStatus, woEquip, woEquipLoc, woType, pmType, pWoStatus)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = wkorId;
	pValId[1] = woNo;
	pValId[2] = woDesc;
	pValId[3] = woStatus;
	if(typeof woEquip != "undefined") pValId[4] = woEquip;
	if(typeof woEquipLoc != "undefined")pValId[5] = woEquipLoc;
	
	lovValueId      = pValId;

	var param = "strutsAction=1001";

	if(typeof pmType != "undefined") param = param + "&" + "lovWoListDTO.pmType=" + pmType;
	if(typeof woType != "undefined") param = param + "&" + "lovWoListDTO.woType=" + woType;
	if(typeof pWoStatus != "undefined") param = param + "&" + "lovWoListDTO.woStatus=" + pWoStatus;
	
	var url =  contextPath + "/lovWoList.do";

	openLayerPopup("lovWoList", param);
}



/**
 * 다국어 팝업 
 * @param keyType
 * @param keyNo
 * @param keyName
 * @param pCodeType
 * @param gridObj
 */
function lovLang(keyType,keyNo,keyName,pCodeType)
{
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	var pValId = new Array();
	pValId[0] = keyType;
	pValId[1] = keyNo;
	pValId[2] = keyName;
	
	lovValueId      = pValId;
	if( typeof pCodeType == "undefined")
	{
		pCodeType = "";
	}
	var param = "strutsAction=1001"+
    "&" + "lovLangListDTO.codeType=" + pCodeType;

	var url =  contextPath + "/lovLangList.do";
	
	openLayerPopup("lovLangList", param);
}
//======================================================================================
function loadCalendar(fullUrl, topPosition, leftPosition)
{
	
    /* 2012.01 레이어로 변경 */
    var calIFrameObj = document.getElementById('calIframe');
    
    if(calIFrameObj.src != fullUrl)
    {
    	calIFrameObj.src = fullUrl;
    }


    var calDivObj = document.getElementById('calDiv');

    //현재 호출한 페이지의 Width와 Height 를 구한다.
    var currentWidth =document.documentElement.clientWidth - 450;
    var currentHeight =document.documentElement.clientHeight - 210;
    
    //팝업을 클릭한 Width가 화면의 끝을 벗어나는경우 위치를 조절한다.
    if(currentWidth < leftPosition)
	{
    	var widthPosition = leftPosition - currentWidth;
    	
    	leftPosition = leftPosition - widthPosition;
	}
    //팝업을 클릭한 Height가 화면의 끝을 벗어나는경우 위치를 조절한다.
    if(currentHeight < topPosition)
    {
    	var heightPosition = topPosition - currentHeight;
    	
    	topPosition = topPosition - heightPosition;
    }
    
    calDivObj.style.top = topPosition+"px"; 
    calDivObj.style.left = leftPosition+"px"; 
    calDivObj.style.display = "";	
}
/**
 * 달력을 띄운다.
 * 
 * _calendarId : 날짜 Input box id (필수)
 * _comDaysId : 요일 Select box id (선택)
 */
function openCalendar(_calendarId, event, _comDaysId)
{
	if( typeof _comDaysId == "undefined")
	{
		_comDaysId = "";
	}
	
	var url =  contextPath + "/common/calendar/calendar.jsp";
    var param = "?" + "calendarId=" + _calendarId
              + "&" + "comDaysId=" + _comDaysId;
    
    /*
    // pop up이 중앙에 위치하게 한다.
    var popHeight = 220; 
    var popWidth  = 240;
    var TopPosition  =  event.screenY;  //(screen.height/2 - popHeight/2);
    var LeftPosition = event.screenX;   //(screen.width/2 - popWidth/2);

    window.open(url+param, "CALENDAR", "toolbar=no,scrollbars=no,width="+popWidth+"px,height="+popHeight+"px,top="+TopPosition+",left="+LeftPosition);
	*/

    var leftPos = event.clientX + document.body.scrollLeft;
    var topPos = event.clientY + document.body.scrollTop;
 
    loadCalendar(url+param, topPos, leftPos);
} 
/**
 * 달력을 닫는다.
 */
function closeCalendar()
{
	var calDivObj = document.getElementById('calDiv');
	calDivObj.style.display = 'none';
	
	try{
		// 달력이 Close 된 후 실행할 로직이 있으면 해당 function을 구현한다.  
		afterCloseCalendar();
	}catch(ex){}
} 
function openCalendarGrid(inputEditorObj)
{
	if (inputEditorObj == null)
	{
		return;
	}
	
    lovCalEditorObj = inputEditorObj;

	var url =  contextPath + "/common/calendar/calendar.jsp";

    //var popHeight = 220;
    //var popWidth  = 240;

    var calPos = getAbsolutePos(inputEditorObj);
    var TopPosition  = calPos.y;  //(screen.height/2 - popHeight/2);
    var LeftPosition = calPos.x;   //(screen.width/2 - popWidth/2);
    // window.open(url, "CALENDAR", "toolbar=no,scrollbars=no,width="+popWidth+",height="+popHeight+",top="+TopPosition+",left="+LeftPosition);
	
    /* 2012.01 레이어로 변경 */
	loadCalendar(url, TopPosition, LeftPosition);
}

/**
 * 팝업에서 return 받은 정보를 setting
 * n개의 row로 리턴한다. 다중행 입력
 */
function setLovValueArray(_setValue, lovType)
{
    // 복수개 입력은 grid 에서만 이루어진다.
    if (lovGridObj)
    {
        var _insertRow = lovGridRowIndex;
        for (var i=0; i<_setValue.length; i++)
        {
            // 첫번째 값을 제외하고는 행추가후 입력한다.
            if (i != 0) _insertRow = lovGridObj.addRow();
            for (var j=0; j<lovValueId.length; j++)
            {
                if (lovValueId[j] && "" != lovValueId[j])
                {
                    lovGridObj.setColumnValue(lovValueId[j], _insertRow, _setValue[i][j]);
                }
            }
        }
    }
    
    //===========================
    // lov 값 셋팅후 호출
    try{afterSetLovValueArray(lovType);}
    catch(ex){}
    //===========================
}

/*
 * 외부에서 추가하는 경우
 */
function setLovValueArray1(_setValue, lovType)
{
    // 복수개 입력은 grid 에서만 이루어진다.
    if (lovGridObj)
    {
        var _insertRow = lovGridRowIndex;
        for (var i=0; i<_setValue.length; i++)
        {
            _insertRow = lovGridObj.addRow();
            for (var j=0; j<lovValueId.length; j++)
            {
                if (lovValueId[j] && "" != lovValueId[j])
                {
                    lovGridObj.setColumnValue(lovValueId[j], _insertRow, _setValue[i][j]);
                }
            }
        }
    }
    
    //===========================
    // lov 값 셋팅후 호출
    try{afterSetLovValueArray(lovType);}
    catch(ex){}
    //===========================
}

/**
 * 팝업에서 return 받은 정보를 setting
 */
function setLovValue(_setValue, lovType)
{	
	var lovValType = typeof lovValueId;
    var topPage = getTopPage();
    var rtnValue;

    //=======================================================
    // HTML 에 셋팅
    // 배열이라면
    if (lovValType != "string")
    {
    	if(!lovValueId) return;
    	
    	rtnValue = lovValueId[0];
        for (var i=0; i<lovValueId.length; i++)
        {
        	if(lovValueId[i]!="" && typeof(lovValueId[i]) == "string" ){   	
        		/*if(M$(lovValueId[i]).value!=''){
        		}else{*/
        			M$(lovValueId[i]).value = _setValue[i];
        			//M$(lovValueId[i]).focus();
        		/*}*/

                if(curPageUpdate)
                {
                	topPage.updateArray[currentPageId] = lovValueId[i];
                	$("[name='"+lovValueId[i]+"']").trigger("change");
                }

        	}
        }
    }
    else
    {
    	rtnValue = lovValueId;
        M$(lovValueId).value = _setValue[0];
        //M$(lovValueId).focus();
    }
    //=======================================================

    //===========================
    // lov 값 셋팅후 호출
	if((typeof afterSetValue=="function")){
		try{afterSetValue(lovType,rtnValue);}
	    catch(ex){}
	}
    //===========================
}


function openLayerPopup(_pageId, _param)
{
	//var divLength 	= parent.$("iframe[name^='popupIframe']").length+1;
	var topDoc = getTopPage();
	var divLength = getTopPage().$(".pwin_wrap").length+1;

	if(typeof popupDivId == "undefined") popupDivId = "";
	
	if(topDoc.$("#popupDiv"+divLength).length > 0) divLength = divLength + 1;
	
    var zIndex = 1300 + (divLength*2);
	var popupDivObj = $('<div></div>').prop({
		"id":"popupDiv"+divLength
		}).css({
		"position":"absolute",
		"margin":"0px",
		"display" : "",
		"z-index" : zIndex ,
		"top"	  : "-500px",
		"overflow":"hidden"
	}).addClass("pwin_wrap");

	var titleBar = $('<div></div>').prop({
		"id":"popupDivTitle"+divLength
		}).css({
		"position":"absolute",
		"vertical-align":"top",
		"margin":"0px",
		"display" : "",
		"width" : "100%",
		"height": "45px",
		"z-index" : zIndex,
		"right" : "50px"
	}).addClass("pop_tit")
	popupDivObj.append(titleBar);

	var modal = $('<div></div>').prop({
		"id":"modal"+divLength
		}).css({
		"position":"fixed",
		"top":"0px",
		"right": "0px",
		"bottom":"0px",
		"left":"0px",
		"margin":"0px",
		"padding":"0px",
		"display":"block",
		"background" : "url('./common/images/bg_pop.png') 0 0 repeat",
		"width" : "100%",
		"height": "100%",
		"z-index" : zIndex-1
	}).addClass("modal-window");	

	var decoName = "popupPage";
	if(DECORATOR_NAME && DECORATOR_NAME.indexOf("mobile") >= 0) decoName = "mobilePopupPage";
	//Set the Popup Div ID for Closing
	if( typeof _param == "undefined" || _param == "")
	{
		_param = "isDecoratorName="+decoName+"&popupDivId="+divLength + "&parentPopupDivId="+popupDivId;
	}
	else
	{
		_param = _param + "&isDecoratorName="+decoName+"&popupDivId="+divLength + "&parentPopupDivId="+popupDivId;
	}

	if(topDoc.filterParam != "") 
	{
		_param = _param + topDoc.filterParam;
		topDoc.filterParam = "";
	}
	
	var iframeObj 	= $('<iframe></iframe>').prop({
		id		:"popupIframe"+divLength,
		name 	:"popupIframe"+divLength,
		scrolling :"no"
		//src		: contextPath + "/" + _pageId + ".do" + "?" + _param
	}).css({
		"height"	:"100%",
		"width"		:"100%",
		"z-index" 	:"1200",
		"border"	:"0px",
		"overflow"  :"hidden"
	});

	popupDivObj.append(iframeObj);

	topDoc.$("body").append(modal);
	topDoc.$("body").append(popupDivObj);

	topDoc.$( "#popupDiv"+divLength ).resizable({
		stop:function(e,ui){
			
		}
	});

	//Submit as POST
	submitPost(_pageId, _param, "popupIframe"+divLength);
	
	popupDivObj.draggable();
}

function makeParamToJson(_param)
{
	var arrayObj = _param.split("&");
	var jsonObj = new Object();
	
	for(var i in arrayObj)
	{
		var nameVal = arrayObj[i].split("=");
		
		jsonObj[nameVal[0]] = nameVal[1];
  	}
	
	//var finalJsonData = JSON.stringify(jsonObj);
	
	//alert(finalJsonData);
	return jsonObj;
}

function setModal(_msg)
{	
	if(typeof _msg == "undefined") _msg = typeof COMMON_CMSG001 =="undefined"?"Loading":COMMON_CMSG001;
	var modal = $('<div></div>').prop({
		"id":"modalOne"
		}).css({
		"position":"fixed",
		"top":"0px",
		"right": "0px",
		"bottom":"0px",
		"left":"0px",	
		"margin":"0px",
		"padding":"0px",
		"display":"block",
		"background" : "url('./common/images/bg_pop.png') 0 0 repeat",
		"width" : "100%",
		"height": "100%",
		"z-index" : "1400"
	}).addClass("modal-window");	
	
	alertMessage2(_msg);
	if(getTopPage().$('#modalOne').length == 0) getTopPage().$("body").append(modal);
	//getTopPage().$("body").append(modal);
}

function closeModal()
{
	CloseAlertMessage2();
	getTopPage().$("#modalOne").remove();
}

/**
 * Close Popup
 */
function closeLayerPopup(e)
{
	parent.closeLayerPopupId(popupDivId);
}

function closeLayerPopupId(popupDirId)
{
	var popupDivObj = document.getElementById('popupDiv'+popupDirId);
	if(!popupDivObj)
	{
		window.open('', '_self').close(); //.close(); //Close Windows Popup
		return;
	}
		
	popupDivObj.style.display = 'none';
	var popupIframeObj = document.getElementById('popupIframe'+popupDirId);
	popupIframeObj.src = "";

	var curDirDiv = $("#popupDiv"+popupDirId); //To close Dialog opened already, use the sign left before. 
	var modalDiv = $("#modal"+popupDirId);
	//curDirDiv.dialog('close');

	curDirDiv.remove();
	modalDiv.remove();
}

/**
 * 조건저장 button
 */
function goConsave() 
{
	var form = $('form').not("[name='bottomForm']")[0];
	
	var param = "persPrivFilterValueDTO.fileName="+currentPageId +
    			"&persPrivFilterValueDTO.userName="+loginUser.userName +
    			"&persPrivFilterValueDTO.userNo="+loginUser.userNo + 
    			"&persPrivFilterValueDTO.setValue="+JSON.stringify(getFormValue(form));
    
	openLayerPopup("persPrivFilterValueDetail", param);
}

/**
 * 조건열기 button
 */
function goConopen()
{
	var param = "persPrivFilterValueDTO.fileName="+currentPageId +
	"&persPrivFilterValueDTO.userName="+loginUser.userName +
	"&persPrivFilterValueDTO.userNo="+loginUser.userNo;  

	openLayerPopup("persPrivFilterValueList", param);
}
