/*===========================================================================
AJAX validation 체크
author  javaworker
version $Id: ajaxValidation.js,v 1.5 2014/08/05 01:14:18 pochul2423 Exp $
since   1.0
===========================================================================*/

// 개별 validation 시 이곳에 추가한다. 
// 될수있으면 모든 validation은 ajaxCommon.js 를 사용한다.
// global script 변수도  ajaxCommon.js 가장 상단에 설명과 함께 정의 한다.


//====================================================================
//===== !!! 필히 이곳에 구현한 validation function 의 용도를 기술 !!! ======//
/*
===============
User validation
===============
TABLE : T4USERS / CODE  : user_id / DESCRIPTION : user_name
HTML  : valUser(valUserId, valUserName, isValidate)
SHEET : valUserGrid(valSheetObj, valSheetRow, valUserId, valUserName, isValidate)

===============
T4LISTS validation
===============
TABLE : T4LISTS / CODE  : code / DESCRIPTION : description
HTML  : valLists(valCode, valDesc, varListType, isValidate)
SHEET : valListsSheet(valSheetObj, valSheetRow, valCode, valDesc, varListType, isValidate)

*/
//====================================================================

//======================================================================================
/**
 * Key 가 눌려 질때 호출된다.
 * Description 등을 clear 시킨다.
 */
function validationKeyDown(valKeyId, desc1, desc2, desc3, desc4, desc5, desc6)
{
	//=====================================================
	var tempKeyObj = M$(valKeyId);
	// readOnly 상태라면  리턴한다.
	if (tempKeyObj.readOnly)
	{
		return;
	}
	//=====================================================

	if (M$(desc1))
	{
		M$(desc1).value = "";
	}
	
	if (M$(desc2))
	{
		M$(desc2).value = "";
	}
	
	if (M$(desc3))
	{
		M$(desc3).value = "";
	}
	
	if (M$(desc4))
	{
		M$(desc4).value = "";
	}
	
	if (M$(desc5))
	{
		M$(desc5).value = "";
	}
	
	if (M$(desc6))
	{
		M$(desc6).value = "";
	}				
}

/*********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 **********************  T4DIR_DTL Validation  ***********************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************/

/**
 * HTML 에서의 코드 validation
 * valDirDtlSet() 에서 셋팅
 * description 으로 code 검색
 */
function valUsrDir(pValCode, pValDesc, pVarDirType, pValDirKind, pIsValidate)
{
/*	//아래 로직이 code->desc여서
	//아래 3줄을 통해 서로 바꿔서 처리함.
	var pValDescTemp = pValDesc;
	pValDesc = pValCode;
	pValCode = pValDescTemp;
	//============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code Type
	if (valCheckCommon(pValCode, pValDesc, pVarDirType)) return;
	//============================================================

	//=====================================
	// Global 변수는 ajaxCommon.js 상단에 정의
	// ID, DESC 를 셋팅한다.
	ajaxGridObj = false;
	ajaxGridRow = false;
	
	ajaxKeyId    = pValCode;
	ajaxKeyDesc  = pValDesc;
	ajaxCodeType = pVarDirType;
	ajaxIsVal    = pIsValidate;
	//=====================================

	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
	            "&codeType=" + pVarDirType +
	            "&codeKind=" + pValDirKind +
			    "&strutsAction=" + strutsActionFindUsrDirDesc;

	XMLHttpPostVal(actionUrl, param, 'valDirDtlSet');*/
	
	//============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code Type
	if (valCheckCommon(pValDesc, pValCode, pVarDirType)) return;
	//============================================================
	
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValDesc).value +
	            "&codeType=" + pVarDirType +
	            "&codeKind=" + pValDirKind +
			    "&strutsAction=" + strutsActionFindUsrDirDesc;
	
	$.post(actionUrl , param,  function(_data){
		var jsonObj = JSON.parse(_data);  

		valDirDtlSet(jsonObj, pValCode, pValDesc, pVarDirType, pIsValidate);
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});

}

/**
 * HTML 에서의 코드 validation
 * valDirDtlSet() 에서 셋팅
 * description 으로 code 검색
 */
function valSysDir(pValCode, pValDesc, pVarDirType, pIsValidate)
{
	//============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code Type
	if (valCheckCommon(pValDesc, pValCode, pVarDirType)) return;
	//============================================================
	
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValDesc).value +
	            "&codeType=" + pVarDirType +
			    "&strutsAction=" + strutsActionFindSysDirDesc;

	$.post(actionUrl , param,  function(_data){
		var jsonObj = JSON.parse(_data);  

		valDirDtlSet(jsonObj, pValCode, pValDesc, pVarDirType, pIsValidate);
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
		  if(typeof afterValSysDir == "function") afterValSysDir(pVarDirType);
	});

}
/**
 * HTML 에서의 코드 validation
 * valDirDtlSet() 에서 셋팅
 * description 으로 code 검색
 */
function valSysDirCode(pValCode, pValDesc, pVarDirType, pValDirKind, pIsValidate)
{ 
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
				"&codeType=" + pVarDirType +
				"&codeKind=" + pValDirKind +
				"&strutsAction=" + strutsActionFindSysDirCode;

	$.post(actionUrl , param,  function(_data){
		var jsonObj = JSON.parse(_data);  

		valDirDtlSet(jsonObj,pValCode, pValDesc, pVarDirType, pIsValidate);
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});
}

/**
 * HTML 에서의 코드 validation
 * valDirDtlSet() 에서 셋팅
 * id로 desc 검색
 */
function valSysDirId(pValCode, pValDesc, pVarDirType, pValDirKind, pIsValidate)
{ 
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
				"&codeType=" + pVarDirType +
				"&codeKind=" + pValDirKind +
				"&strutsAction=" + strutsActionFindSysDirId;

	$.post(actionUrl , param,  function(_data){
		var jsonObj = JSON.parse(_data);  

		valDirDtlSet(jsonObj,pValCode, pValDesc, pVarDirType, pIsValidate);
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});
}

/**
 * HTML 에서의 코드 validation
 * valDirDtlSet() 에서 셋팅
 * id로 desc 검색
 */
function valUsrDirId(pValCode, pValDesc, pVarDirType, pValDirKind, pIsValidate)
{ 
	//============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code Type
	if (valCheckCommon(pValCode, pValDesc, pVarDirType)) return;
	//============================================================

	//=====================================
	// Global 변수는 ajaxCommon.js 상단에 정의
	// ID, DESC 를 셋팅한다.
	ajaxGridObj = false;
	ajaxGridRow = false;
	
	ajaxKeyId    = pValCode;
	ajaxKeyDesc  = pValDesc;
	ajaxCodeType = pVarDirType;
	ajaxIsVal    = pIsValidate;
	//=====================================
	
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
	"&codeType=" + pVarDirType +
	"&codeKind=" + pValDirKind +
	"&strutsAction=" + strutsActionFindUsrDirId;
	
	XMLHttpPostVal(actionUrl, param, 'valDirDtlSet');
}
/**
 * T4DIR_DTL : description 셋팅
 * valDirDtl(), valDirDtlSheet() 에서 호출 됨
 */
function valDirDtlSet(ajaxXmlDoc)
{
	//===============================================================
	//올바른 Code 가 아닙니다.
	var valMessage = COMMON_CMSG039;
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================

	var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');

	if (ajaxGridObj)
	{
		//=============================================================
		// GRID 에 셋팅
	    ajaxGridObj.setColumnValue(ajaxKeyDesc, ajaxGridRow, aDesc[0]); 
		//=============================================================
	}
	else
	{
		//=======================================================
		// HTML 에 셋팅
		if(M$(ajaxKeyDesc))
			M$(ajaxKeyDesc).value = aDesc[0];
		//=======================================================
	}
}

function valDirDtlSet(_jsonObj,pValCode, pValDesc, pVarDirType, pIsValidate)
{
	var ajaxKeyDescType = typeof pValDesc;
	var topPage = getTopPage();
	topPage.filterParam = ""; //To add Filter Parameter 

	var status = _jsonObj.status; //if desc or code
	_jsonObj = JSON.parse(_jsonObj.list); //json Result Data as List with json format
	
	if(_jsonObj.length > 1) //The length of result is more than one
	{
		//Popup창 호출
		//console.log(pValDesc.length);
		if(ajaxKeyDescType == "string")
		{
			topPage.filterParam = "&listOfValDTO.description="+M$(pValDesc).value;
			lovSysDir(pValCode, pValDesc, pVarDirType);
		}
		else if(pValDesc.length == 1)
		{
			var idName 	= pValDesc[0];
			var descName= pValCode;

			topPage.filterParam = "&listOfValDTO.description="+M$(pValDesc).value;
			lovSysDir(pValCode, pValDesc, pVarDirType);
		}
		else
		{
			for(var i in pValDesc)
			{
				
			}
		}
	}
	else if(_jsonObj.length == 1) //Only One result.
	{	
		var cnt = 0;
		$.each(_jsonObj[0], function(key,value){
//			console.log(key+"   "+value+"   "+ajaxKeyDescType+"   status:"+status);

			if(cnt == 0) M$(pValCode).value = value;
			else M$(pValDesc).value = value;
			
			cnt++;
			
		});
	}


	if(pIsValidate && _jsonObj.length == 0)
	{
		//다 지워라!
		if(ajaxKeyDescType == "string")
		{
			M$(pValCode).value = "";
			M$(pValDesc).value = "";
		}
		else
		{
			M$(pValCode).value = "";
			for(var i in pValDesc)
			{
				M$(pValDesc[i]).value = "";
			}
		}
		M$(pValCode).focus();
		
		alertMessage1(COMMON_CMSG039);
	}
}

//===================================================================================================
//===================================================================================================
//===================================================================================================

/*********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 **********************  TABLE Validation  ***************************
 *********************************************************************
 *********************************************************************
 *********************************************************************
 *********************************************************************/

/**
 * HTML 에서의 TABLE validation
 * 중간에 다른 function에서 호출하게 한다.
 * ex)valEquipNo();
 * valTableSet() 에서 셋팅
 * 결과 완료후 : afterValTableSet(codeType) 호출된다. 필요하면 각 페이지에서 구현하여 사용
 */
function valTable(pValCode, pValDesc, pVarCodeType, pIsValidate)
{
	//=============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code type
	if (valCheckCommon(pValCode, pValDesc, pVarCodeType)) return;
	//=============================================================

	//=====================================
	// Global 변수는 ajaxCommon.js 상단에 정의
	// ID, DESC 를 셋팅한다.
	ajaxGridObj = false;
	ajaxGridRow = false;
	
	ajaxKeyId    = pValCode;
	ajaxKeyDesc  = pValDesc;
	ajaxCodeType = pVarCodeType;
	ajaxIsVal    = pIsValidate;
	//=====================================
	
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
	            "&codeType=" + pVarCodeType +
			    "&strutsAction=" + strutsActionFindTableVal;

	XMLHttpPostVal(actionUrl, param, 'valTableSet');
}
/**
 * HTML에서의 Table Validation
 * description으로 code 찾기.
 */
function valTableDesc(pValCode, pValDesc, pVarCodeType, pIsValidate, pExpValCode, pExpValCode2,pExpValCode3)
{
	//=============================================================
	// validation 체크 전에 공통으로 호출 : codeId, descId, code type
	//구매요청 자재팝업에서 빈값으로 validation 시 나머지값들을 초기화하지않는다.
	//값이 빈칸이면 ID값만 초기화
	if(pExpValCode3=="TAREQPARTS"&& typeof pExpValCode3 != "undefined"){
		if(trim(M$(pValCode).value) == ""){
			M$(pValCode).value = "";
			M$(pValDesc[0]).value = "";
			return;
		}
	}else {
		if (valCheckCommon(pValCode, pValDesc, pVarCodeType)) return;
	}
	
	//=============================================================

	//ID 값이 세팅되어 있으면 Skip Validation
	/*if(typeof pValDesc == "string" && M$(pValDesc).value != "") return;
	else
	{
		for(var k in pValDesc)
		{
		//	console.log("!!!!!!"+M$(pValDesc[k]).value);
			if(k ==0 && M$(pValDesc[k]).value != "") return;
		}
	}*/
	
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + M$(pValCode).value +
			    "&codeType=" + pVarCodeType +
			    "&strutsAction=" + strutsActionFindTableDesc;

	if(pExpValCode != '' && pExpValCode != null)
	{
		ajaxExpKeyId = pExpValCode;
		if(M$(pExpValCode))
			pExpValCode =  M$(pExpValCode).value;
		
		param = param + "&expCode=" + pExpValCode;
	}
	if(pExpValCode2 != '' && pExpValCode2 != null)
	{
		if(M$(pExpValCode2))
			pExpValCode2 = M$(pExpValCode2).value;
			
		param = param + "&expCode2=" + pExpValCode2;
	}
	if(pExpValCode3 != '' && pExpValCode3 != null)
	{
		if(M$(pExpValCode3))
			pExpValCode3 = M$(pExpValCode3).value;
			
		param = param + "&expCode3=" + pExpValCode3;
	}
	
	$.post(actionUrl , param,  function(_data){
		var jsonObj = JSON.parse(_data);  
		valTableSet(jsonObj, pValCode, pValDesc, pVarCodeType, pIsValidate, pExpValCode, pExpValCode2,pExpValCode3);
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});

}

function makeGridJson(listJson)
{
	var gridData = { 
		    data : [] 
		};

}

var myPop;
/**
 * Table : description 셋팅
 * valTable(), valTableSheet() 에서 호출 됨
 */
function valTableSet(_jsonObj ,pValCode, pValDesc, pVarCodeType, pIsValidate, pExpValCode, pExpValCode2, pExpValCode3)
{	
	var ajaxKeyDescType = typeof pValDesc;
	var topPage = getTopPage();
	topPage.filterParam = "";

	var status = _jsonObj.status;
	var jsonList = _jsonObj.list;
	var popListName = new Array();
	var jqueryObj = $.parseJSON( _jsonObj.list );
	_jsonObj = JSON.parse(_jsonObj.list);

	//조회 List가 하나 이상일 경우 팝업으로 띄움
	if(_jsonObj.length > 1)
	{
		var cntArr = 0;
		$.each(_jsonObj[0], function(arrKey, arrValue){
            //console.log(arrKey+"    "+arrValue); // <-- element1, element2 etc etc
			//if(cntArr == 1) popListName[cntArr] = arrKey;
			popListName[cntArr] = arrKey;
            cntArr++;
        });
		
		var inp = $("input[name='"+pValCode+"']").get(0);
		if (!myPop)
		{
		//if(myPop) myPop.hide();
			myPop = new dhtmlXPopup();
		}
		//console.log(popListName.join(",")+"     "+jsonList);
		myPop.attachList(popListName.join(","),jqueryObj);
		
		var x = window.dhx4.absLeft(inp);	
		var y = window.dhx4.absTop(inp);
		var w = inp.offsetWidth;
		var h = inp.offsetHeight;
		//myPop.setDimension(200,150); //version up 필요
		myPop.show(x,y,w,h);

		//console.log($(window).outerHeight() +"   "+ $('.dhx_popup_dhx_skyblue > .dhx_popup_area').css("height"));
		if($(window).outerHeight() < $('.dhx_popup_dhx_skyblue > .dhx_popup_area').css("height").replace("px",""))
		{
			$('.dhx_popup_dhx_skyblue > .dhx_popup_area').css({
				"height":($(window).outerHeight()-40-y)+"px",
				"overflow-y":"scroll"	
			});
		}
		
		myPop.show(x,y,w,h);
		
		myPop.attachEvent("onClick", function(id){
			//console.log("onClick", id, myPop.getItemData(id));
			var cnt = 0;
			$.each(myPop.getItemData(id), function(key,value){
				//console.log(key+"   "+value+"   "+pValDesc[cnt]);
				
				if(M$(pValDesc[cnt])) M$(pValDesc[cnt]).value = value; //Code 세팅!
				
				cnt++;
				
			});
		});
		
		myPop.attachEvent("onHide", function(){
			if(M$(pValDesc[0]).value == "")
			{
				if(ajaxKeyDescType == "string")
				{
					M$(pValCode).value = "";
					M$(pValDesc).value = "";
				}
				else
				{
					M$(pValCode).value = "";
					for(var i in pValDesc)
					{
						if(M$(pValDesc[i])) M$(pValDesc[i]).value = "";
					}
				}
				M$(pValCode).focus();
			}
		});

	}
	else if(_jsonObj.length == 1) //결과 값이 하나 !
	{	
		if(ajaxKeyDescType == "string")  //파라미터 종류가 스트링
		{
			var cnt = 0;
			$.each(_jsonObj[0], function(key,value){

				if(M$(pValDesc[cnt]) && status == "desc") M$(pValDesc).value = value; //코드를 검색해 오면..코드 세팅
				else //코드로 Desc를 검색해 왔을경우 자리를 바꿔줘야 한다.
				{
					if(cnt == 1) M$(pValCode).value = value; //Description 세팅
					else if(M$(pValDesc)) M$(pValDesc).value = value; //Code 세팅!
				}
				
				cnt++;
				
			});
		}
		else if(pValDesc.length == 1)  //파라미터가 Array지만 1개인 경우.
		{
			var cnt = 0;
			$.each(_jsonObj[0], function(key,value){
				//console.log(key+"   "+value+"   "+ajaxKeyDescType);
				
				if(M$(pValDesc[cnt]) && status == "desc") M$(pValDesc[cnt]).value = value;
				else //코드로 검색해서 결과가 왔을경우 자리르 바꿔줘야 한다.
				{
					if(cnt == 1) M$(pValCode).value = value; //Description 세팅
					else if(M$(pValDesc[cnt])) M$(pValDesc[cnt]).value = value; //Code 세팅!
				}
				
				cnt++;
				
			});
		}
		else //세팅 파라미터가 여러개인 경우 
		{
			if(pVarCodeType == "TAVENDOR") //거래처
			{
				var temp = pValDesc.splice(0,2); //첫번째 값 추출 (id Name)
				pValDesc.unshift(pValCode); //Desc Name 밀어넣음 
				pValDesc.unshift(temp.splice(1,1)); //빼놓은 Id Name 밀어넣음
				pValDesc.unshift(temp.splice(1,1))
			}
			else
			{	
/*				var temp = pValDesc.splice(0,1); //첫번째 값 추출 (id Name)
				pValDesc.unshift(pValCode); //Desc Name 밀어넣음 
				pValDesc.unshift(temp); //빼놓은 Id Name 밀어넣음
*/			}

			var cnt = 0;
			$.each(_jsonObj[0], function(key,value){
				
				if(M$(pValDesc[cnt])) M$(pValDesc[cnt]).value = value; //Code 세팅!

				cnt++;
				
			});
		}
		
		//결과값이 1개인경우 입력되면서 팝업 하이드
		if(myPop) myPop.hide();
	}

	//아래코드의 경우 매칭되는 데이터가 없는 경우 검색 데이터만 삭제하고 나머지 데이터는 그대로 둡니다.
	//구매신청의 자재검색의 경우-품번이 없어도 품명과 규격은 입력한 값 그대로 있어야함.
	if(pExpValCode3=="TAREQPARTS"&& typeof pExpValCode3 != "undefined"){
		if(pIsValidate && _jsonObj.length == 0)
		{
			M$(pValCode).value = "";
//			
			M$(pValCode).focus();
//			
			alertMessage1(COMMON_CMSG039);
		}
	}
	else if(pIsValidate && _jsonObj.length == 0)
	{
		//다 지워라!
		if(ajaxKeyDescType == "string")
		{
			M$(pValCode).value = "";
			M$(pValDesc).value = "";
		}
		else
		{
			M$(pValCode).value = "";
			for(var i in pValDesc)
			{
				if(M$(pValDesc[i])) M$(pValDesc[i]).value = "";
			}
		}
		M$(pValCode).focus();

		alertMessage1(COMMON_CMSG039);
		if(myPop) myPop.hide();
	}
	
	try{afterSetValAjax(pVarCodeType);}
    catch(ex){}
	/*//===============================================================
	//올바른 Code 가 아닙니다.
	var valMessage = COMMON_CMSG039;
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================

	var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
    var ajaxKeyDescType = typeof ajaxKeyDesc;
	if (ajaxGridObj)
	{
		//=============================================================
		// GRID
		if (ajaxKeyDescType == "string")
		{
			ajaxGridObj.setColumnValue(ajaxKeyDesc, ajaxGridRow, aDesc[0]); 
		}
		// 배열이라면
		else
		{
			for (var i=0; i<ajaxKeyDesc.length; i++)
			{
				ajaxGridObj.setColumnValue(ajaxKeyDesc[i], ajaxGridRow, aDesc[i]);
			}
		}
		//=============================================================
	}
	else
	{
		//=======================================================
		// HTML 에 셋팅
		if (ajaxKeyDescType == "string")
		{
		    M$(ajaxKeyDesc).value = aDesc[0];
		}
		// 배열이라면
		else
		{
			for (var i=0; i<ajaxKeyDesc.length; i++)
			{
				if(ajaxKeyDesc[i]!=""&&typeof(ajaxKeyDesc[i]) == "string"){
					M$(ajaxKeyDesc[i]).value = aDesc[i];
				}
			}
		}
		//=======================================================
	}*/
}

/**
 * Equipment Description Validation
 */
function valEquipDesc(pEquipId, pEquipDesc, pEquipLoc, pEquipCtg,pEqLocId, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEquipId;
	pValDesc[1] = pEquipDesc;
	pValDesc[2] = pEquipLoc;
	pValDesc[3] = pEquipCtg;
	pValDesc[4] = pEqLocId;

	valTableDesc(pEquipDesc, pValDesc, "TAEQUIPMENT", pIsValidate);
}

/**
 * Emp Name 으로 Validation
 * @param pEmpName
 * @param pEmpId
 * @param pIsValidate
 */
function valEmpName(pEmpId, pEmpNo, pEmpName, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEmpId;
	pValDesc[1] = pEmpNo;
	pValDesc[2] = pEmpName;

	valTableDesc(pEmpName, pValDesc, "TAEMPNAME", pIsValidate);
}

/**
 * User Name 으로 Validation
 * @param pUserName
 * @param pUserId
 * @param pIsValidate
 */
function valUserName(pUserId, pUserNo, pUserName,pEmail, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	pValDesc[0] = pUserId;
	pValDesc[1] = pUserNo;
	pValDesc[2] = pUserName;
	pValDesc[3] = pEmail;
	valTableDesc(pUserName, pValDesc, "TAUSER", pIsValidate);
}
/**
 * 다국어명으로 KEY_TYPE, KEY_NO찾기
 * @param keyName
 * @param keyType
 * @param keyNo
 * @param pIsValidate
 */
function valLang(keyType, keyNo, keyName, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = keyType;
	pValDesc[1] = keyNo;
	pValDesc[2] = keyName;
	
	if(typeof pExpValCode == "undefined") pExpValCode = "";

	valTableDesc(keyName, pValDesc, "TALANG", pIsValidate,pExpValCode);
}

/**
 * Emp No로 Validation
 */
function valEmpNo(pEmpId, pEmpNo, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEmpId;
	pValDesc[1] = pEmpNo;
	
	valTableDesc(pEmpNo, pValDesc, "TAEMPNO", pIsValidate);
}


function valEqCtgDesc(peqCtgId, _peqCtgDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = peqCtgId;
	pValDesc[1] = _peqCtgDesc;

	valTableDesc(_peqCtgDesc, pValDesc, "TAEQCTG", pIsValidate);
}


function valEqCtgFullDesc(peqCtgId, _peqCtgDesc, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = peqCtgId;
	pValDesc[1] = _peqCtgDesc;
	if(typeof pExpValCode == "undefined") pExpValCode = "";

	valTableDesc(_peqCtgDesc, pValDesc, "TAEQCTGFULL", pIsValidate,pExpValCode);
}

function varMesLineDesc(_pmesLineId, _pmesLineDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = _pmesLineId;
	pValDesc[1] = _pmesLineDesc;

	valTableDesc(_pmesLineDesc, pValDesc, "MESLINE", pIsValidate);
}

function valYn(_pYn, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = _pYn;

	valTableDesc(_pYn, pValDesc, "YN", pIsValidate);
}

function valLvl(_pLvl, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = _pLvl;

	valTableDesc(_pLvl, pValDesc, "LEVEL", pIsValidate);
}
/**
 * Wo No Validation
 */
function valWoDesc(pWoId, pWoNo, pWoDesc,pWoStatusDesc,pEquipDesc,pEqLocDesc,pExpValCode,pExpValCode2,pExpValCode3, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pWoId;
	pValDesc[1] = pWoNo;
	pValDesc[2] = pWoDesc;
	pValDesc[3] = pWoStatusDesc;
	pValDesc[4] = pEquipDesc;
	pValDesc[5] = pEqLocDesc;
	
	valTableDesc(pWoDesc, pValDesc, "TAWORKORDER", pIsValidate,pExpValCode,pExpValCode2,pExpValCode3);
}
/**
 * Part No Validation
 * @param pPartNo
 * @param pPartId
 * @param pPartDesc
 * @param pIsValidate
 */
function valPartNo(pPartId, pPartNo, pPartDesc, pMaker, pModel, pPlfType, pPlfTypeDesc,pUseQty,pLastPrice, pIsValidate)
{
	if(pMaker) pIsValidate = pMaker;

	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pPartId;
	pValDesc[1] = pPartNo;
	pValDesc[2] = pPartDesc;
	pValDesc[3] = pMaker;
	pValDesc[4] = pModel;
	pValDesc[5] = pPlfType;
	pValDesc[6] = pPlfTypeDesc;
	pValDesc[7] = pUseQty;
	pValDesc[8] = pLastPrice;
	
	valTableDesc(pPartNo, pValDesc, "TAPARTS", pIsValidate);
}

/**
 * Part No Validation
 * @param pPartNo
 * @param pPartId
 * @param pPartDesc
 * @param pIsValidate
 */
function valToolNo(pPartId, pPartNo, pPartDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pPartId;
	pValDesc[1] = pPartNo;
	pValDesc[2] = pPartDesc;
	
	valTableDesc(pPartNo, pValDesc, "TATPARTS", pIsValidate);
}

/**
 * 플랜트 Validation
 * @param pPlant
 * @param pPlantDesc
 * @param pIsValidate
 */
function valPlant(pPlant, pPlantDesc, pIsValidate)
{
	if(typeof pIsValidate == "undefined") pIsValidate = true;
	
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pPlant;
	pValDesc[1] = pPlantDesc;
	
	valTableDesc(pPlantDesc, pValDesc, "TAPLANT", pIsValidate);
}

/**
 * Part No Validation
 * @param pPartNo
 * @param pPartId
 * @param pPartDesc
 * @param pIsValidate
 */
function valReqPartNo(pPartId, pPartNo, pPartDesc,pPtSize, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pPartId;
	pValDesc[1] = pPartNo;
	pValDesc[2] = pPartDesc;
	pValDesc[3] = pPtSize;
	
	valTableDesc(pPartNo, pValDesc, "TAREQPARTS", pIsValidate,"","","TAREQPARTS");
}

/**
 * 작업부위 Desc Validation 
 * @param pEqCtgAsmbDesc
 * @param pEqCtgAsmbId
 * @param eqCtgId
 * @param pIsValidate
 * @param pEqCtgId
 */
function valEqCtgAsmbDesc(pEqCtgAsmbId, pEqCtgAsmbDesc, pIsValidate, pEqCtgId)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEqCtgAsmbId;
	pValDesc[1] = pEqCtgAsmbDesc;

	valTableDesc(pEqCtgAsmbDesc, pValDesc, "TAEQCTGASMB", pIsValidate, pEqCtgId);
}


/**
 * Equipment Location Desc Validation
 * @param pEqLocDesc
 * @param pEqLocId
 * @param pIsValidate
 */
function valEqLocDesc(pEqLocId, pEqLocDesc, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEqLocId;
	pValDesc[1] = pEqLocDesc;
	

    if(typeof pExpValCode == "undefined") pExpValCode = "";
	
	valTableDesc(pEqLocDesc, pValDesc, "TAEQLOC", pIsValidate,pExpValCode);
}

function valEqLocFullDesc(pEqLocId, pEqLocFullDesc, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEqLocId;
	pValDesc[1] = pEqLocFullDesc;

	if(typeof pExpValCode == "undefined") pExpValCode = "";
	
	valTableDesc(pEqLocFullDesc, pValDesc, "TAEQLOCFULL", pIsValidate, pExpValCode);
}

function valAssetNo(pAssetId, pAssetNo, pAssetDesc, pAssetDate, pBuyerAmt, pDepAmt, pResAmt, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pAssetId;
	pValDesc[1] = pAssetNo;
	pValDesc[2] = pAssetDesc;
	pValDesc[3] = pAssetDate;
	pValDesc[4] = pBuyerAmt;
	pValDesc[5] = pDepAmt;
	pValDesc[6] = pResAmt;

	valTableDesc(pAssetNo, pValDesc, "TAEQASSET", pIsValidate);
}
function valLineNo(pLineId, pLineDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pLineId;
	pValDesc[1] = pLineDesc;

	valTableDesc(pLineNo, pLineDesc, "TAPOPLINE", pIsValidate);
}

function valEqApp(eqAppNo, eqAppId, title, contents, receiver, reqDate, appDate, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = eqAppId;
	pValDesc[1] = title;
	pValDesc[2] = contents;
	pValDesc[3] = receiver;
	pValDesc[4] = reqDate;
	pValDesc[5] = appDate;

	valTableDesc(eqAppNo, pValDesc, "TAEQAPP", pIsValidate);
}

function valPtApp(ptAppId, title, recDate, eqDesc, totAmt, contents, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	
	pValDesc[0] = title;
	pValDesc[1] = recDate;
	pValDesc[2] = eqDesc;
	pValDesc[3] = totAmt;
	pValDesc[4] = contents;
	
	valTableDesc(eqAppId, pValDesc, "TAPTAPP", pIsValidate);
}


function valDeptDesc(pDeptId, pDeptNo, pDeptDesc, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pDeptId;
	pValDesc[1] = pDeptNo;
	pValDesc[2] = pDeptDesc;
	if(typeof pExpValCode == "undefined") pExpValCode = "";
	
	valTableDesc(pDeptDesc, pValDesc, "TADEPT", pIsValidate, pExpValCode)
}

/**
 * 부위명 Validation
 */
function valEqAsmb(pEqAsmbId, pEqAsmbDesc, pIsValidate, pEquipId)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pEqAsmbId;
	pValDesc[1] = pEqAsmbDesc;
	
	valTableDesc(pEqAsmbDesc, pValDesc, "TAEQASMB", pIsValidate,'',pEquipId);
}

/**
 * Vendor Description Validation
 * @param pVendorDesc
 * @param pVendorId
 * @param pIsValidate
 */
function valVendorDesc(pVendorId, pVendorNo, pVendorDesc, addrNrepName, person, office, mobile, email, pIsValidate,pExpValCode)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.
	pValDesc[0] = pVendorId;
	pValDesc[1] = pVendorNo;
	pValDesc[2] = pVendorDesc;
	pValDesc[3] = addrNrepName;
	pValDesc[4] = person;
	pValDesc[5] = office;
	pValDesc[6] = mobile;
	pValDesc[7] = email;
	
	if(typeof pExpValCode == "undefined") pExpValCode = "";
	
	valTableDesc(pVendorDesc, pValDesc, "TAVENDOR", pIsValidate, pExpValCode);
}

/**
 * WareHouse Desc Validation
 */
function valWhouseDesc(pWhouseId, pWhouseName,whType,outWcode,outPlant, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pWhouseId;
	pValDesc[1] = pWhouseName;
	if(typeof whType != "undefined") pValDesc[2] = whType;
	if(typeof outWcode != "undefined") pValDesc[3] = outWcode;
	if(typeof outPlant != "undefined") pValDesc[4] = outPlant;

	valTableDesc(pWhouseName, pValDesc, "TAWAREHOUSE", pIsValidate);
}

/**
 * Tool WareHouse Desc Validation
 */
function valTWhouseDesc(pWhouseId, pWhouseName, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pWhouseId;
	pValDesc[1] = pWhouseName;

	valTableDesc(pWhouseName, pValDesc, "TATWAREHOUSE", pIsValidate);
}

/**
 * 유형별 세부코드명 Validation
 */
function valUsrCdDesc(pUserCdId, pUsrCdName, pIsValidate, pUsrCdmId)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pUserCdId;
	pValDesc[1] = pUsrCdName;

	valTableDesc(pUsrCdName, pValDesc, "TACDUSRD", pIsValidate, pUsrCdmId);
}

function valMenuDesc(pMenuId, pMenuDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pMenuId;
	pValDesc[1] = pMenuDesc;

	valTableDesc(pMenuDesc, pValDesc, "TAMENU", pIsValidate);
}

function valPageDesc(pPageId, pPageDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pPageId;
	pValDesc[1] = pPageDesc;

	valTableDesc(pPageDesc, pValDesc, "TAPAGE", pIsValidate);
}

function valButtonDesc(pBtnId, pBtnDesc, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pBtnId;
	pValDesc[1] = pBtnDesc;

	valTableDesc(pBtnDesc, pValDesc, "TABUTTON", pIsValidate);
}

function valUsrGrpDesc(pUsrGrpId, pUsrGrpName, pIsValidate)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pUsrGrpId;
	pValDesc[1] = pUsrGrpName;

	valTableDesc(pUsrGrpName, pValDesc, "TAUSRGRP", pIsValidate);
}

function valFailDesc(pCacdId, pCacdDesc, pIsValidate, pStatus)
{
	var pValDesc = new Array();
	
	// 셋팅 되는 변수를 배열 형태로 넣는다.

	pValDesc[0] = pCacdId;
	pValDesc[1] = pCacdDesc;

	valTableDesc(pCacdDesc, pValDesc, "TAFAILURE", pIsValidate, pStatus);
}

//=====================================================================
//Sequence NextVal
//=====================================================================
/**
* 시퀀스 nextVal 를 셋팅한다.
* callSetSequenceVal()
* - 비동기 방식
*/
function sequenceNextVal(sequenceName, isSync)
{
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + sequenceName + 
			    "&strutsAction=" + strutsActionSeqNextVal;

	// isSync : false 이면 동기방싱으로 되게 하기 위해 false 로 리턴한다.
	XMLHttpPostRequest(actionUrl, param, 'callSetSequenceVal', isSync==false?false:true);
}

/**
* 검색된 sequence value 를 setSequenceVal로 호출한다.
*/
function callSetSequenceVal(ajaxXmlDoc)
{
	//===============================================================
	//관리자에게 문의 바랍니다.
	//var valMessage = cmsg001;
	var valMessage = COMMON_CMSG040;
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================
	
	var aId = parseXmlDoc(ajaxXmlDoc, 'ID');  // sequence Name
	var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC'); //sequence
	
	//==========================
	// 검색된 내용 page 셋팅
	setSequenceVal(aDesc[0], aId[0]);
	//==========================	
}
//=====================================================================
//Sequence NextVal
//=====================================================================
/**
* 시퀀스 nextVal 를 셋팅한다.
* callSetSequenceVal()
* - 비동기 방식
*/
function sequenceNextVal2(sequenceName, isSync)
{
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + sequenceName + 
			    "&strutsAction=" + strutsActionSeqNextVal;

	// isSync : false 이면 동기방싱으로 되게 하기 위해 false 로 리턴한다.
	XMLHttpPostRequest(actionUrl, param, 'callSetSequenceVal2', isSync==false?false:true);
}

/**
* 검색된 sequence value 를 setSequenceVal로 호출한다.
*/
function callSetSequenceVal2(ajaxXmlDoc)
{
	//===============================================================
	//관리자에게 문의 바랍니다.
	//var valMessage = cmsg001;
	var valMessage = COMMON_CMSG040;
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================
	
	var aId = parseXmlDoc(ajaxXmlDoc, 'ID');
	var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	//==========================
	// 검색된 내용 page 셋팅
	setSequenceVal2(aDesc[0], aId[0]);
	//==========================	
}
//=====================================================================
//No NextVal
//=====================================================================
/**
* No nextVal 를 셋팅한다. (ex:xxxx_no 컬럼)
* callSetNoVal()
* - 비동기 방식
*/
function noNextVal(tableName,columnName, isSync)
{
	var actionUrl = contextPath + "/validation.do";
	var param = "code=" + tableName + 
    			"&codeType=" + columnName +
			    "&strutsAction=" + strutsActionNoNextVal;

	// isSync : false 이면 동기방싱으로 되게 하기 위해 false 로 리턴한다.
	XMLHttpPostRequest(actionUrl, param, 'callSetNoVal', isSync==false?false:true);
}

/**
* 검색된 sequence value 를 setSequenceVal로 호출한다.
*/
function callSetNoVal(ajaxXmlDoc)
{
	//===============================================================
	//관리자에게 문의 바랍니다.
	//var valMessage = cmsg001;
	var valMessage = COMMON_CMSG040;
	if (!valCheckHttpXml(ajaxXmlDoc, valMessage)) return;
	//===============================================================
	
	var aId = parseXmlDoc(ajaxXmlDoc, 'ID');
	var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	//==========================
	// 검색된 내용 page 셋팅
	setNoVal(aDesc[0], aId[0]);
	//==========================	
}
//=====================================================================
// 
//=====================================================================
/**
 * 첫번째 셀렉트박스를 재구성한다. 
 * 기존에 검색된 값은 클리어.
 * first select box 재구성
 */
var ajaxSelectId;
function findSelectOptions(_selectObjId, _selectType, _code1, _code2)
{   
    // 셀렉트 박스
    var selectObj = M$(_selectObjId);
    // select options초기화
    for (var i=selectObj.options.length; i > 0 ; i--)
    {
        //selectObj.options.remove(i);
        removeOption(selectObj, i-1);
    }

    ajaxSelectId= _selectObjId;
    
    var url   = contextPath + "/selectOption.do";
    var param = "strutsAction=" + strutsActionFindOptions +
                "&" + "selectOptionsDTO.selectType=" + _selectType +
                "&" + "selectOptionsDTO.code1=" + _code1 +
                "&" + "selectOptionsDTO.code2=" + _code2;
    
    // findSelectSecond의 결과와 동일.
    XMLHttpPost(url, param, 'setSelectOptions');
}

/**
 * 셀렉트 박스  Option 구성
 */
function setSelectOptions(ajaxXmlDoc)
{
    var aId   = parseXmlDoc(ajaxXmlDoc, 'ID');
    var aDesc = parseXmlDoc(ajaxXmlDoc, 'DESC');

    // 호기
    var selectObj = M$(ajaxSelectId);

    // 조회된내용을 다시 화면에 보여준다.
    for (var i=0; i<aId.length; i++)
    {
        addOption(selectObj, aId[i], aDesc[i]);
    }
    
    // options 가장 앞에 ---- 를 붙여 준다.
    initSelect(selectObj);
    
    // 셀렉트 박스 변경 완료후 로직이 필요한 경우  각 페이지에서 아래 메소드 정의.
    try{
        afterSetOptions(ajaxSelectId);
    }catch(e){}
}

//=========================================================
// 첨부파일 관련 - start
//=========================================================

/**
 * 첨부파일을 표시한다.
 */
var fileAttachTagId = "";
function showFileAttach(_objectNo, _objectType, _fileAttachTagId)
{
	if (_objectNo == "") return;
	
    var actionUrl = contextPath + "/validation.do";
    var param = "code=" + _objectNo +
                "&expCode=" + _objectType +
                "&strutsAction=" + strutsActionFileAttach;
    
    if (_fileAttachTagId && _fileAttachTagId != "") fileAttachTagId = _fileAttachTagId;
    else fileAttachTagId = "FILE_ATTACH";

    //XMLHttpPostVal(actionUrl, param, 'setFileAttach');
    $.post(actionUrl,param, function(_data){
    	setFileAttach(_data);
    });
}

function showFileAttach2(_objectNo, _objectType, _fileAttachTagId)
{
	if (_objectNo == "") return;
	
    var actionUrl = contextPath + "/validation.do";
    var param = "code=" + _objectNo +
                "&expCode=" + _objectType +
                "&strutsAction=" + strutsActionFileAttach;
    
    if (_fileAttachTagId && _fileAttachTagId != "") fileAttachTagId = _fileAttachTagId;
    else fileAttachTagId = "FILE_ATTACH";

    //XMLHttpPostVal(actionUrl, param, 'setFileAttach');
    return $.post(actionUrl,param, function(_data){
    	//setFileAttach(_data);
    });
}

/**
 * 첨부파일을 표시한다.
 * 기존 첨부내역을 지우지 않고 그 뒤에 추가적으로 붙인다.
 * 2013.12.10 bluemoon
 */
var fileAttachTagId = "";
function showFileAttachAppend(_objectNo, _objectType, _fileAttachTagId)
{
	if (_objectNo == "") return;
	
    var actionUrl = contextPath + "/validation.do";
    var param = "code=" + _objectNo +
                "&expCode=" + _objectType +
                "&strutsAction=" + strutsActionFileAttach;
    
    if (_fileAttachTagId && _fileAttachTagId != "") fileAttachTagId = _fileAttachTagId;
    else fileAttachTagId = "FILE_ATTACH";
    
    XMLHttpPostVal(actionUrl, param, 'setFileAttachAppend');
}

/**
 * 첨부파일표시 call back function
 */
function setFileAttach(ajaxXmlDoc)
{
	$('.file_list').remove();

    var aImageNo  = parseXmlDoc(ajaxXmlDoc, 'ARR0');
    var aFileName = parseXmlDoc(ajaxXmlDoc, 'ARR1');
    var aFileType = parseXmlDoc(ajaxXmlDoc, 'ARR2');
    var imageType = parseXmlDoc(ajaxXmlDoc, 'ARR4');

    var dataLength = aImageNo.length;
	
    for (var i=0; i<dataLength; i++)
    {
    	var fileName = $("<div class='file_list' ><div class='file_name'></div></div>").append($("<a>"+aFileName[i]+"</a>").on("click", function(){
									fileDownLoad($(this).prop("title"), $(this).prop("alt"));
								}).prop({"title":aImageNo[i],"alt":imageType[i]}));
    	var fileBtnSection = $("<div class='file_btn'></div>");
    	fileBtnSection.append($("<a class='b_filedown' ></a>").on("click", function(){
    								fileDownLoad($(this).prop("title"), $(this).prop("alt"));
    							}).prop({"title":aImageNo[i],"alt":imageType[i]}));

    	if(aImageNo[i] != "")
	    	fileBtnSection.append($("<a class='b_filedel'></a>").on("click",function(){
							    		$(this).parents('.file_list').remove();
										//deleteRows.push($(this).prop("title"));
										$('.form_box').append($("<input type='hidden' name='deleteRows'>").val($(this).prop("title")));
										getTopPage().updateArray[currentPageId] = "FILE";
							    	}).prop({"title":aImageNo[i]}));
    	
    	fileName.append(fileBtnSection);
    	$('.file_box').append(fileName);
	
    }
}

/**
 * 첨부파일표시 call back function
 * 기존 첨부내역을 지우지 않고 그 뒤에 추가적으로 붙인다.
 * 2013.12.10 bluemoon
 */
function setFileAttachAppend(ajaxXmlDoc)
{
    //=====================================
    // 현재 표시된 모든 첨부파일을 삭제한다.
    var fileAttachTag = M$(fileAttachTagId);
    //fileAttachTag.innerHTML= ""; // 기존 내역이 지워지지 않도록 한다.

    var aImageNo  = parseXmlDoc(ajaxXmlDoc, 'ARR0');
    var aFileName = parseXmlDoc(ajaxXmlDoc, 'ARR1');
    var aFileType = parseXmlDoc(ajaxXmlDoc, 'ARR2');

    var dataLength = aImageNo.length;
    for (var i=0; i<dataLength; i++)
    {
    	var fileInfoObject = getFileInfoObj(aImageNo[i], aFileName[i], aFileType[i]);
        fileAttachTag.appendChild(fileInfoObject);
    }
}

/**
 * 파일을 화면에 보여주게 셋팅한다.
 */
function getFileInfoObj(_imageNo, _fileName, _fileType)
{   
    /*
    // grid 에서 innerHTML로 추출한다. 아래와 같이 하면 innerHTML 추출시 event의 함수가 추출 안된다.
    var fileInfoObject = document.createElement("input");
    
    var fileId = "fileImg";
    fileInfoObject.onfocus = function(){this.blur();};
    fileInfoObject.onmouseover = function(){this.style.cursor='pointer';};
    
    fileInfoObject.title = _fileName;
    fileInfoObject.onclick = function(){fileDownLoad(_imageNo);};
     */
    
    var className="";
    // 첨부파일 아이콘 이미지가 있는경우만 설정한다.(필요시 추가)
    _fileType = _fileType.toLowerCase();
    
	if (_fileType == 'xlsx') _fileType = "xls";
	else if (_fileType == 'docx') _fileType = "doc";
	else if (_fileType == 'pptx') _fileType = "ppt";
    
    if (_fileType == 'avi' || _fileType == 'bmp' || _fileType == 'doc' || _fileType == 'exe' || 
        _fileType == 'html'|| _fileType == 'hwp' || _fileType == 'jpg' || _fileType == 'tif' ||
        _fileType == 'xls' || _fileType == 'zip' || _fileType == 'gif' || _fileType == 'txt' ||
        _fileType == 'pdf' || _fileType == 'ppt' || _fileType == 'wmv' || _fileType == 'gul' ||
        _fileType == 'png' )
    {
    	className = "file_" + _fileType;
    }
    else
    {
    	className = "file_non";
    }

    var fileDivObject = document.createElement("div");
    fileDivObject.innerHTML = '<input id="fileImg" title=\"'+_fileName+'\" onclick="fileDownLoad('+_imageNo+');" class=\"'+className+'\" onfocus="this.blur();" onmouseover="this.style.cursor=\'pointer\'" />';
    
    return fileDivObject.childNodes[0];
}

/**
 * 첨부파일을 표시한다.
 */
function findGridAttach(_grid, _columnId, _objectNo, _objectType)
{
	ajaxGridObj = _grid;
	ajaxKeyId   = _columnId;
	
    var actionUrl = contextPath + "/validation.do";
    var param = "code=" + _objectNo +
                "&expCode=" + _objectType +
                "&strutsAction=" + strutsActionFileAttach;
    
    XMLHttpPostVal(actionUrl, param, 'setGridAttach');
}

/**
 * 첨부파일표시 call back function
 */
function setGridAttach(ajaxXmlDoc)
{
    var aImageNo  = parseXmlDoc(ajaxXmlDoc, 'ARR0');
    var aFileName = parseXmlDoc(ajaxXmlDoc, 'ARR1');
    //var aFileType = parseXmlDoc(ajaxXmlDoc, 'ARR2');

    var fileInfo = "";
    for (var i=0; i<aImageNo.length; i++)
    {
    	if (fileInfo != "") fileInfo = fileInfo + " || ";
    	fileInfo = fileInfo + aImageNo[i]+" | "+aFileName[i];
    }
    
    ajaxGridObj.setColumnValue(ajaxKeyId, ajaxGridObj.activeRowNo, fileInfo);
}

/**
 * 파일 다운 로드
 */
function fileDownLoad(imageNo, fileType)
{
	if(imageNo == "")
	{
		alertMessage1("권한이 없습니다.");
		return;
	}
	
    var param = "?" + "isDownloadPage="   + "true" +
                "&" + "physicalFileName=" + imageNo +
                "&" + "fileType="+fileType;
                
    bottomForm.action = contextPath + "/download.do" + param;
    
    bottomForm.target = "bottomIframe";
    bottomForm.submit();
    bottomForm.target = "";
}
//=========================================================
// 첨부파일 관련 - end
//=========================================================

function valDate(_dateObj)
{
	if (!validDate(_dateObj)) return;
}