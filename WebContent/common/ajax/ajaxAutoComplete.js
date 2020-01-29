/*===========================================================================
AJAX AutoComplete 체크
author  jung7126
version $Id:$
since   1.0
===========================================================================*/
var autSt,gkeyMap={},gColMap={},gCondition={},gKeyIdMap={},gVal={},gDcondition={},gLabelMap={};gIsCustom={};gChild={};gGridObj={};gCellObj={}; hFmArr = {};//Keep Map Object Info as Global
var myPop, popGrid, eId, myPopTop;
var topDoc = getTopPage();
var lovCommonMap = {
		"TADOCCTG":"docCtgLov"
		,"TAFAILURE":"failCodeLov"
		,"TACRITYVALUE":"rcmCrityValLov"
		,"TADEPT":"deptValLov"
		,"TAEQLOC":"eqLocValLov"
		,"TAPLANT":"plantValLov"
		,"TAASSBASE":"assBaseValLov"
		,"TAASSBASEPVAL":"assBasePointValValLov"
		,"TATABLE":"tableValLov"
		,"TATABCOL":"tableColValLov"
		,"TAPARTS":"partsValLov"
		,"TAPARTSDESC":"partsValLov"
		,"TAPARTSMODEL":"partsValLov"
		,"TAPARTSPTSIZE":"partsValLov"
		,"TAEHMENU":"ehMenuValLov"
		,"TALANG":"langValLov"
		,"TAEQASMB":"eqAsmbValLov"	
		,"TACOMP":"compValLov"	
		,"TACDSYSD":"listOfSysVal"
		,"TAUSER":"lovUserAcList"
		,"TAEMP":"lovEmpAcList"
		,"TAEQUIPMENT":"lovEquipAcList"
		,"TAEQCTG":"lovEqCtgAcList"
		,"TAPMTASKMAPLIST":"rcmTaskMapListLov"
		,"TAINVTPRCTP":"invtPrcTpLov"
		,"TAINVTPRCPH":"invtPrcPhLov"
		,"TAINVTLIST":"invtLov" //lovInvtList
		,"TAPMTASKMAP":"lovTaskMapList"
		,"TACRITYLIST":"crityValLov"
		,"TACOURSELIST":"lovEduList"
		,"TAPAGE":"pageValLov"
		,"TAVENDOR":"lovVendorAcList"
		,"TAWORKORDER":"lovWoAcList"
		,"TACERTLIST" :"lovCertList"
		,"TAWKCTR" :"wkCtrValLov"
		,"TAPMENU":"menuValLov"
		,"TAASSET":"lovAssetAcList"
		,"TAPMLST" :"pmValLov"
		,"TAWAREHOUSE":"consultCompWarehouseLov"
		,"TAWRKCALLIST":"wrkCalListLov"
		,"TACDUSRD":"lovCdUsrdAcList"
		,"TARCMLIST":"rcmPmtaskAcList"
		,"TAEQTOOL":"lovEqToolAcList"
		,"TAEQCTGASMB":"lovEqCtgAsmbList"
		,"TACTCTR":"lovCtCtrList"
		,"TAMESLINE":"lovMesLineList"
		,"TAUSRRPTTAB":"lovUsrRptTabAcList"
		,"TAUSRGRP":"lovUsrGrpAcList"
		,"TAPARTEQUIP":"lovSerialList"
		,"TAPMCALIBSTDTP":"workPmStdCalibValLov"
		,"TASTDCHECKPOINT":"lovStdCheckPointList"
	    ,"TASTWRKWORK":"lovStdWrkWorkList"
    	,"TAPRODUCT":"lovProductAcList"
		,"TAPMEQUIP":"lovPmEquipAcList"
		,"TALNWRKLIST":"lovLnWrkAcList"
		,"TAEQASMBBYPM":"eqAsmbByPmValLov"	
		,"TACMENU":"lovConsultMenuAcList"	
		,"TAEXCELTAB":"lovExcelTabAcList"
		,"TAPTEMGISSLIST":"lovEmgPartList"
		,"TAEQCTGPART":"lovEqCtgPartAcList"
		,"TAEQCTGSPEC":"lovEqCtgSpecAcList"
		,"TALINKEDFUNC":"lovLinkedFuncAcList"
		,"TACDSYSM":"cdSysCodeParentLov"
		,"TACDUSRM":"cdUsrCodeParentLov"
		,"TAMEASURETIME":"workPmListMsTimeLov"
		,"TAEQASSLIST":"assAssetScoreCopyLov"
		,"TAWOPLAN":"lovWoPlanAcList"
		,"TADBCONTENTS":"lovDbConAcList"	
		,"TAPTSTOCK":"lovPtStckAcList"
		,"TAPTPRLIST":"lovPtprAcList"
		,"TAPTPNLIST":"lovPartPurBuyPnList"
		,"TAEQCTGPMPOINT":"lovWorkPmDInsList"
		,"TAWOREQ":"lovWoReqAcList"
		,"TAPTBINLIST":"lovPartListBinList"
		,"TAACCOUNT":"lovAccountList"
		,"TAWOPARTS":"lovWoPartsAcList"
		,"TAPTISSLIST":"lovPtIssAcList"	
		,"TAPOITEMLIST":"lovPoItemAcList"
		,"TAPARTSTOOLS":"lovToolPartsAcList"
		,"TAWOLETCTG":"lovWorkLetCategList"
		,"TAWOLETCTGPOINT":"lovWorkLetCategPointList"
		,"TAMESSAGECATEG":"lovMsgCategList"
		,"TAMSGCOMPSET":"lovMsgCompList"
		,"TAUSRPLANTAUTH":"lovUsrPlantAuthList"
		,"TAPMUPMLST":"lovWorkPmListUInsList"
		,"TAUSAGEWRKLIST":"lovUsageWrkAcList"
		,"TACONTRACT":"lovMgrContractList"
		,"TASERVICE":"lovWorkServiceList"
};

function autoC(name)
{ 
	var acObjName;   //The Name of Input Object set by AutoComplete function 
	var acKeyMap={};   //This Map is configured with input object name for display and column name to search.
	var acResultMap={}; //This Map is for hidden input object.
	var acConditionMap={}; //This Map is configured with column name and cnodition value for search condition.
	var acDconditionMap={}; //Dynamic Condition 
	var acTable; //The Table Name in Database.
 	var acKeyObjName; // The input object name which is one of 'acResultMap' is for validation.
 	var acResultLabel; // Label for List of Validation ( must pair with resultMap) 
 	var acCustomLov; //Custom LOV String trigger when clicked.
 	var acIsCustom = false; //CustomFunc...Only TableType, promised input names....
 	var acIsMultiSelect = false; //Multi Select Option
 	var acChild; // Input Name of Child Object
 	var acGridObj; //Grid Object 
 	var acCellObj; //Editing Col Obj
	var topDoc = getTopPage();
	
 	//It could be Map<input name,column> or just input name. In the case of only input name inserted, the 'setAcName' is essential.  
	if(typeof name == "object")
	{
		var cntN = 0;
		for (var key in name)  // key : maPtMstrList.partNo, value : part_no
		{

			if(cntN ==0) acObjName = key;
			
			cntN++;
		}
		acKeyMap = $.extend({}, name);
		acResultMap = $.extend({}, name);

	}
	else if(typeof name == "string")
	{
		//acObjName = name;
		for(var i = 0; i < arguments.length; i++) {
			acKeyMap[arguments[i]] = arguments[i];
			acResultMap[arguments[i]] = arguments[i];
	    }
	}
	
	this.customAc = function(isCustom)
	{
		acIsCustom = isCustom;
	}
	
	this.setGrid = function(_gridObj)
	{
		acGridObj = _gridObj;
		topDoc.gGridObj[acObjName] = acGridObj;
	}
	this.setCol = function(_colObj)
	{	    
		acCellObj = _colObj;
		topDoc.gCellObj[acObjName] = acCellObj;
	}
	
	this.getAcName = function()
	{ 
		return acObjName; 
	}; 
	
	this.setAcName = function (value) 
	{ 
		acObjName = value; 
	}; 
	
	this.setChild = function (value) 
	{
		acChild = value;
	};
	
	this.setAcCol = function(acColName)
	{
		acKeyMap = {};
		if(typeof acObjName == "undefined" || acObjName == "") 
		{
			alertMessage1("자동완성을 사용할 Input Object의 Name을 넣으세요. ex) setName(name)");
			return;
		}
		else if(typeof acColName == "undefined" || acColName == "")
		{
			alertMessage1("자동완성에 사용할 Table Column명을 넣으세요.");
		}
		
		acKeyMap[acObjName] = acColName;
	}
	
	this.getAcCol = function()
	{
		if(typeof acKeyJson == "undefined" || acKeyJson == "")
		{
			alertMessage1("자동완성에 사용한 Table Column이 없습니다.");
			return;
		}
		else return acKeyJson;
	}
	
	this.setAcResultMap = function(resultMap){

		//acResultMap["DISPLAY"] = "";
		if(typeof resultMap == "object")
		{
			if(typeof acResultMap == "undefined" || acResultMap == "")
			{
				acResultMap = resultMap;			
			}
			else 
			{
				for (var key in resultMap)  // key : maPtMstrList.partNo, value : part_no
				{
					acResultMap[key] = resultMap[key];
				}
			}
		}
		else if(typeof resultMap == "string")
		{
			//Set the unlimited arguments for Value Setting.
			for(var i = 0; i < arguments.length; i++) {
		        acResultMap[arguments[i]] = arguments[i];
		    }
		}
	}
	
	this.getAcResultMap = function()
	{
		if(typeof acResultMap == "undefined" || acResultMap == "")
		{
			alertMessage1("결과 Map이 입력되지 않았습니다.");
			return;
		}
		else return acResultMap;
	}
	
	this.setAcConditionMap = function(condMap)
	{
		if(typeof condMap == "object")
		{
			acConditionMap = condMap;
		}
		else if(typeof condMap == "string")
		{
			//Set the unlimited arguments for Value Setting.
			for(var i = 0; i < arguments.length; i++) {
				acConditionMap[i] = arguments[i];
		    }
		}
	}
	
	this.setAcDConditionMap = function(dCondMap)
	{
		acDconditionMap = dCondMap;
	}
	
	this.setTable = function(tableName)
	{
		acTable = tableName;
	}
	
	this.init = function()
	{
		//Check Essential Value 
		/*if(typeof acKeyMap == "undefined"){
			alertMessage1("자동완성 기능을 사용할 input name과 column명이 없습니다.");
			return;
		}*/
		
		if(typeof acTable == "undefined")
		{
			alertMessage1("자동완성을 사용할 Table명이 필요합니다.");
		}
		
		//Set Only Display 
		if(typeof acResultMap == "undefined")
		{
			acResultMap = {};
			acResultMap["DISPLAY"] = "1";
		}
		else
		{
			var keyAddedColMap = JSON.parse(JSON.stringify(acResultMap));
			var colCnt = 0;
			var newAcResultMap = {};
			
			for (var key in keyAddedColMap)  // key : maPtMstrList.partNo, value : part_no
			{
				acResultMap[key] = keyAddedColMap[key];
			}
			
			for (var key in acResultMap)  // key : maPtMstrList.partNo, value : part_no
			{
				if(colCnt == 0) newAcResultMap = {"DISPLAY" :acResultMap[key]};
				
				newAcResultMap[key] = acResultMap[key];
				colCnt++;
			}
		}

		
		if(!acCellObj && !acGridObj)
		{
			autoCmpt(acKeyMap, newAcResultMap, acTable, acConditionMap, acDconditionMap, acKeyObjName, acResultLabel, acCustomLov, acIsCustom, acIsMultiSelect, acChild);
		}
		else
		{

			//해당 컬럼에만 걸려라!
			var colId = acGridObj.getColumnId(acCellObj.cellIndex);
			if(colId != acObjName) return;

			gridAutoCmpt(acGridObj, acCellObj, acKeyMap, newAcResultMap, acTable, acConditionMap, acDconditionMap, acKeyObjName, acResultLabel, acCustomLov, acIsCustom, acIsMultiSelect, acChild);
		}
	}
	
	this.destroy = function()
	{
		$("input[name='"+acObjName+"']").unbind("keyup");
		
		var _btnLen = $("input[name='"+acObjName+"']").next().is('p');
   		var _lovObj;
   		
   		if(_btnLen) _lovObj = $("input[name='"+acObjName+"']").parent().find('.open_spop');
   		else _lovObj = $("input[name='"+acObjName+"']").prev();
   		
   		_lovObj.unbind("click");
	}
	
	this.setAcDisplay = function(colValue)
	{

	}
	//Validation
	this.setKeyName = function(keyName)
	{
		acKeyObjName = {};
		acKeyObjName[keyName] = "";
	}
	//LOV 용 라벨 
	this.setAcResultLabel = function(label)
	{
		if(typeof label == "object")
		{
			acResultLabel = label;
		}
		else alert("라벨 타입설정 필요합니다");
	}
	//Set Custom LOV
	this.setCustomLov = function(lovFnc)
	{
		acCustomLov = lovFnc;
		acIsCustom = true;
	}
	
	this.getCustomLov = function()
	{
		return acCustomLov;
	}

	this.setMultiSelect = function(multi)
	{
		return acIsMultiSelect = multi;
	}
	
	this.exec = function()
	{
		//실행시 한개면 바로 넣고 아니면 냅두고...
		autoComplete(acObjName, acTable, false, true); //code, codeType, val, _exec, _isCustom)
	}
	
	this.openLov = function()
	{
		var _btnLen = $("input[name='"+acObjName+"']").next().is('p');
   		var _lovObj;
   		
   		if(_btnLen) _lovObj = $("input[name='"+acObjName+"']").parent().find('.open_spop');
   		else _lovObj = $("input[name='"+acObjName+"']").prev();
   		
   		_lovObj.trigger("click");
	}
}

function gridAutoCmpt(pGridObj, pCellObj, pkeyMap, pColMap, pTable, pCondition, pDcondition, pkeyIdMap, pLabel, pCustomLov, pIsCustom, pIsMultiSelect, pChild)
{
	if(typeof pColMap == "undefined" || pColMap == "") pColMap = {};
	if(typeof pCondition == "undefined" || pCondition == "") pCondition = {};
	if(typeof pDcondition == "undefined" || pDcondition == "") pDcondition = {};
	if(typeof pkeyIdMap == "undefined" || pkeyIdMap == "") pkeyIdMap = {};
	if(typeof pLabel == "undefined" || pLabel == "") pLabel = {};
	if(typeof pCustomLov == "undefined") pCustomLov = "";
	if(typeof pIsMultiSelect == "undefined") pIsMultiSelect = false;
	if(typeof pChild == "undefined") pChild = "";
	
	var gObj = pGridObj;
	var cObj = pCellObj;
	var pCode; 
	var keyCnt = 0;
	var topDoc = getTopPage();
	
	for (var key in pkeyMap)
	{
		if(keyCnt == 0) pCode = key;
		
		keyCnt++;
	}
	
	//=================== Remove Inline Event Handler ==============
/*	
	var tObj = $("[name='"+pCode+"']")
	tObj.removeAttr("onkeydown");
	tObj.next().find('a').removeAttr("href"); //onClick Function
	
*/
	//=================== Key Search & Result Map ==================
	var isInCol = false;
	for(var colKey in pColMap)
	{
		if(colKey == pCode) isInCol = true;
	}	
	//Key Search Condition and input info
	var keyAddedColMap = JSON.parse(JSON.stringify(pkeyMap));
	var colCnt = 0;
	if(!isInCol)
		for (var key in keyAddedColMap)  // key : maPtMstrList.partNo, value : part_no
		{
			pColMap[key] = keyAddedColMap[key];
		}
	//==============================================================
	
	gkeyMap[pCode] = JSON.stringify(pkeyMap);
	gColMap[pCode] = JSON.stringify(pColMap);
	gCondition[pCode] = JSON.stringify(pCondition);
	gDcondition[pCode] = JSON.stringify(pDcondition);  //Dynamic Condition
	gKeyIdMap[pCode] = JSON.stringify(pkeyIdMap);
	gLabelMap[pCode] = JSON.stringify(pLabel);
	gChild[pCode] = pChild;
		
	var inputObj = $(pCellObj).find('input');

	if(pChild != "") setReadOnly(pChild);

	inputObj.on({
		"focusin":function(e){
			if(inputObj.prop("readonly")) return; 

			//keyup event 이후로 0.5초 이내에 다시 keyup event 발생시 이전의 keyup event는 Clear.
			if(autSt)clearTimeout(autSt);

			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;

			//up arrow, down arrow, enter, tab Key event는 autoComplete function trigger 안함.
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27) || typeof e.keyCode == "undefined")
			{			
				autSt = setTimeout(function() {
						gridAutoComplete(pGridObj, pCellObj, pCode, pTable, false, false, pIsCustom);
						}, 500);
			}

			if (!topDoc.popGrid) return //when Grid in popup is not available, stop here. 
			if (topDoc.myPop.isVisible()) {
				if (e.keyCode == 38) { // up
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind-1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 40) { // down
					//console.log("2");
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind+1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 13) { // enter
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null && topDoc.popGrid.getRowIndex(id) >= 0) {
						topDoc.popGrid.callEvent("onRowSelect",[id]);
					}
					if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide(); 
				}
			}
		},
		"keyup":function(e){

			if(inputObj.prop("readonly")) return; 
			//키업 event가 발생하면 key Input 제외하고 다른 Input 값을 Clear (TAB 키는 제외)
			var colJsonObj = JSON.parse(gColMap[pCode]);
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
			{
				for (var key in colJsonObj)
				{
					//if(M$(key) && key != pCode) M$(key).value = "";
					
					if(key != pCode)setValueById(pGridObj, pCellObj.parentNode.idd, key, "");
				}
				
				if(pChild != "")
				{
					var colChJsonObj = JSON.parse(gColMap[pChild]);
					for (var key in colChJsonObj)
					{
						if(key != pCode)setValueById(pGridObj, pCellObj.parentNode.idd, key, "");
					}
					
					if(pChild != "") setReadOnly(pChild);
				}
				
			}
			
			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
			
			//keyup event 이후로 0.5초 이내에 다시 keyup event 발생시 이전의 keyup event는 Clear.
			if(autSt)clearTimeout(autSt);

			//up arrow, down arrow, enter, tab Key event는 autoComplete function trigger 안함.
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
				autSt = setTimeout(function() {
					gridAutoComplete(pGridObj, pCellObj, pCode, pTable, false, false, pIsCustom);
					}, 500);
					
			if (!topDoc.popGrid) return //when Grid in popup is not available, stop here. 
			if (topDoc.myPop.isVisible()) {
				if (e.keyCode == 38) { // up
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind-1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 40) { // down
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind+1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 13) { // enter
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null && topDoc.popGrid.getRowIndex(id) >= 0) {
						topDoc.popGrid.callEvent("onRowSelect",[id]);
					}
					if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide(); 
				}
			}
		},
   		"blur":function(e){

   			if(inputObj.prop("readonly")) return; 

   			var valKeyCnt = 0;
   			for(var key in pkeyIdMap)
   			{
   				valKeyCnt++;
   			}
   			
			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
				
   			if(valKeyCnt>0 && inputObj.val()!= ""){
   				autSt = setTimeout(function() {
					gridAutoComplete(pGridObj, pCellObj, pCode, pTable, true, false);
					}, 500);
   			}
   		}
   	}); 
   	
   	if(inputObj.prop("readonly") || !inputObj.is(":visible")) inputObj.off("keyup");

   	function openLov(_pageId)
   	{
		var _btnLen = inputObj.next().is('p');
   		var _lovObj;

   		if(_btnLen) _lovObj = inputObj.parent().find('.open_spop>a');
   		else _lovObj = inputObj.prev();

   		_lovObj.on({
   			"click":function(e){

   				//Before All the process is started, use this function.
   				if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
   				  				
   				var pkeyCode;
   				var keyJsonObj = JSON.parse(gkeyMap[pCode]);
   				var keyCodeCnt = 0;
   				for (var key in keyJsonObj){
   					if(keyCodeCnt == 0){
   						pkeyCode = keyJsonObj[key];   // part_no
   					}
   					keyCodeCnt++;
   				}
   				
   				//dDcondition(Dynamic Condition Setting) 
   				var dCondStr = JSON.parse(gDcondition[pCode]);
   				for(var key in dCondStr)  //key : Column 
   				{
   					if(M$(dCondStr[key]))
   					{
   						var conditionJson = JSON.parse(gCondition[pCode]);
   						conditionJson[key] = M$(dCondStr[key]).value;
   						
   						
   						gCondition[pCode] = JSON.stringify(conditionJson);	
   					}
   				}

   				var param = "code=" + inputObj.val() + //Key Search Value
				"&keyCode=" + pkeyCode +  //Key Search Column
	            "&codeType=" + pTable +   // Table for Search
	            "&resultCol=" + gColMap[pCode] +// JSON.stringify(gColMap) +
	            "&label=" + gLabelMap[pCode] +// JSON.stringify(gColMap) +
	            "&chName=" + gChild[pCode] +// 
	            "&title=" + $(this).parent().prev().text() +
 	            "&param=" + gCondition[pCode] + //JSON.stringify(gCondition) +  //Search Condition
	            "&isGridSearch=true"+  //For Session Check
   				"&isDecoratorName=popupPage" + 
		        "&popupWidth=810";
   				
   				var multiS = JSON.stringify({multiSelect:"Y"});
   				
   				if(pIsMultiSelect) param = param+"&extCode1="+multiS+"&multiSelect=Y";
   				
   				openLayerPopup(_pageId, param);
   			}
   		});
   	}

   	//if(typeof lovCommonMap[pTable] != "undefined" && pCustomLov == "")
   	if(typeof lovCommonMap[pTable] != "undefined")
   	{
   		openLov(lovCommonMap[pTable]);
   	}
   	else if(inputObj.next().is('.open_spop') && !pIsCustom && inputObj.next().find('a').prop("href") == "")
   	{
   		openLov("maFinderAc");
   	}
   	else if(inputObj.next().is('.open_spop') && pCustomLov != "")
   	{
   		inputObj.next().on({
   			"click":function(e){
   				$.globalEval(pCustomLov);
   			}
   		});
   	}
}


function gridAutoComplete(_gridObj, _cellObj,  code, codeType,  val, _exec, _isCustom)
{
	//Condition could be blank, then {} should be put for JSON Object;
	if(typeof pParam == "undefined" || pParam == "") param = {};
	if(typeof val == "undefined") val = false;
	if(typeof _exec == "undefined") _exec = false;
	if(typeof _isCustom == "undefined") _isCustom = false;

	gVal[code] = val;
	
	//Extracting Key Input Name 
	var pCode, pkeyCode;
	var keyJsonObj = JSON.parse(gkeyMap[code]);
	var keyCodeCnt = 0;
	for (var key in keyJsonObj){
		if(keyCodeCnt == 0){
			pCode = key;  //maPtMstrList.partNo
			pkeyCode = keyJsonObj[key];   // part_no
		}
		keyCodeCnt++;
	}
	
	//console.log(_cellObj.cellIndex);
	
	//console.log(_cellObj.parentNode.idd);
	
	//키값 채크 필요!
	var keyIdJObj 	= JSON.parse(gKeyIdMap[code]);
	var colJsonObj 	= JSON.parse(gColMap[code]);
	var isKeyNull = false;
	
	if(val)
		for(var key in keyIdJObj)
		{
			for(var i in colJsonObj) // maPtMstrListDTO.partNo
			{
				var _keyVal = getValueById(_gridObj, _cellObj.parentNode.idd, i);
				//console.log(i+"  "+key+"!!!!"+getValueById(_gridObj, _cellObj.parentNode.idd, i));
				if(key == i && _keyVal != "" && _keyVal != "0") isKeyNull = true;
			}
		}
	
	//dDcondition(Dynamic Condition Setting) 
	var dCondStr = JSON.parse(gDcondition[code]);
	for(var key in dCondStr)  //key : Column 
	{
		if(M$(dCondStr[key]))
		{
			var conditionJson = JSON.parse(gCondition[code]);
			conditionJson[key] = M$(dCondStr[key]).value;

			gCondition[code] = JSON.stringify(conditionJson);	
		}
	}
	
	var strutsActionForAc = strutsActionAutoComplete;
	//키항목에 값이 없으면 정지
	if(isKeyNull) return;
	//if(_isCustom) strutsActionForAc = strutsActionValCustom;
	
	//Post Ajax 
	var actionUrl = contextPath + "/maFinderAc.do";
	var param = "code=" + $(_cellObj).find('input').eq(0).val() + //Key Search Value
				"&keyCode=" + pkeyCode +  //Key Search Column
	            "&codeType=" + codeType +   // Table for Search
	            "&resultCol=" + gColMap[code] +// JSON.stringify(gColMap) +
	            "&param=" + encodeURIComponent(gCondition[code]) + //JSON.stringify(gCondition) +  //Search Condition
	            "&isGridSearch=true"+  //For Session Check
			    "&strutsAction=" + strutsActionForAc;

	$.post(actionUrl , param,  function(_data){
		if(checkSession(_data) && !_exec)
		{ 
			//var jsonObj = JSON.parse(_data);  
			
			//if(jsonObj.data.length > 1)
				gridAutoCompleteSet(_gridObj, _cellObj, _data, code, codeType, val);
			//else if(jsonObj.data.length == 1)
			//	setOneValue(_data, code, codeType, val);
			//else 
			//	if (topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
		}
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});
	
}

function getIframe(pIfmName, _contents)
{
	var targetIfmCnt;
	if(typeof _contents == "undefined") _contents = getTopPage().document;

	if(_contents.parent == top && _contents.DECORATOR_NAME == "popupPage")
	{
		var parentIfmId = _contents.popupDivId;

		var ifrm = _contents.parent.$("#popupIframe"+parentIfmId);
	    targetIfmCnt =  $(ifrm); //ifrm.contentWindow || ifrm.contentDocument;
	    
	    hFmArr[pIfmName] = targetIfmCnt;
	    
	    return targetIfmCnt;
	}


	 $(_contents).find('iframe').each(function(index){

		var _nm = $(this).attr("name");
		if(typeof _nm == "undefined"){
			return true;
		}

		if(_nm.indexOf("tabFrameTAB.") >= 0)
		{
			var iframeName = $(this).attr("name").replace("tabFrameTAB.","");

			if(iframeName != pIfmName)
			{
				getIframe(pIfmName, $(this).contents());
			}
			else
			{
				targetIfmCnt = $(this);
				
				hFmArr[pIfmName] = $(this);
				return false;
			}	
		}
		else
		{
			getIframe(pIfmName, $(this).contents());
		}
	 });
	 
	 return targetIfmCnt;
}

function gridAutoCompleteSet(_gridObj, _cellObj, _jsonObj, code,  pVarDirType, val)
{	
	var pValDesc = {};
	var pValCode,onClkE,onHideE;
	var colCnt = 0;
	//Extracting Result Input Name Array for Ajax Result.  
	var colJsonObj = JSON.parse(gColMap[code]);
	for (var key in colJsonObj)
	{
		pValDesc[colCnt] = key; 
		colCnt++;
	}

	//Extracting Key Input Name 
	var keyJsonObj = JSON.parse(gkeyMap[code]);
	pValCode = code;
	
	var ajaxKeyDescType = typeof pValDesc;
	var topDoc = getTopPage();

	var jsonO = JSON.parse(_jsonObj);
	var listObj = jsonO.data;
	//조회 List가 하나 이상일 경우 팝업으로 띄움
	if(listObj.length >= 1)
	{
		var inp = $(_cellObj).find('input').eq(0);
		var inpWidth = inp.width();
		
		if (!topDoc.myPop)topDoc.myPop = new topDoc.dhtmlXPopup({"mode":"bottom"}); //팝업은 아래쪽에...
		else topDoc.myPop.hide();
		
		var pObj = self;
		var topH = 0;
		var leftH = 0;
		
		if(pObj != top)
			do{
				var cPage = pObj.currentPageId;		
				var ifm = getIframe(cPage);
				
				if(!ifm)ifm = hFmArr[cPage]; 
				
				if(ifm)
				{
					topH = topH + ifm.parent().offset().top;

					leftH = leftH + ifm.parent().offset().left;
				}
					
				pObj = pObj.parent;
				
				
			}while(pObj != top); 

		hFmArr = {};
		//Set the position of popup.
		//var x = window.dhx4.absLeft(inp);	
		//var y = window.dhx4.absTop(inp);
		var x = inp.offset().left + leftH;	
		var y = inp.offset().top + topH;
		var w = inp.parent().width();
		var h = inp.outerHeight(true)+10;
		var gridHeight;

		eId = topDoc.myPop.attachEvent("onShow",function(){

			gridHeight = (listObj.length)*30+5;
			//아래 남은 공간 높이가 그리드 높이보다 작으면, 남은 공간 높이를 그리드 높이로 세팅한다.
			if((window.outerHeight-y-140) < gridHeight )
			{
				gridHeight = window.outerHeight-y-140;
			}

			topDoc.popGrid = topDoc.myPop.attachGrid(inpWidth, gridHeight-5);
			topDoc.popGrid.setImagePath("../dhtmlxSuite/codebase/imgs/")
			topDoc.popGrid.setNoHeader(true);
			topDoc.popGrid.init();

			topDoc.popGrid.parse(_jsonObj,"js");

			topDoc.popGrid.attachEvent("onRowDblClicked", function(id){
				var topDoc = getTopPage();
				//myForm.setItemValue("country", topDoc.popGrid.cells(id,1).getValue());
				for(var i = 0; topDoc.popGrid.getColumnsNum() > i; i++)
		  		{
					if(M$(pValDesc[i]))
					{
						M$(pValDesc[i]).value = topDoc.popGrid.cells(id,i).getValue().replace(/&gt;/gi, ">");
						if(curPageUpdate)topDoc.updateArray[currentPageId] = pValDesc[i];
					}
		  		}
				
				topDoc.myPop.hide();
				topDoc.popGrid.clearSelection(); 
				
				var rtnJsonArry = new Array();
				rtnJsonArry.push(getRowData(topDoc.popGrid, id));
				//After All the process is ended, use this function.
				if(typeof afterAutoCmpt =="function") afterAutoCmpt(code, rtnJsonArry);

				//Resize Popup topDoc.myPop.setDimension(x, gridHeight) 
				var node = topDoc.$('#'+topDoc.myPop._nodeId);
				node.css("width",w+"px");
				node.css("height", gridHeight+"px");
				topDoc.myPop._repaint();
				if (topDoc.myPop._nodeObj != null && typeof(topDoc.myPop._nodeObj.setSizes) == "function") topDoc.myPop._nodeObj.setSizes();
				node = null;
			});
			
			topDoc.popGrid.attachEvent("onRowSelect", function(id){
	
				for(var i = 0; topDoc.popGrid.getColumnsNum() > i; i++)
		  		{
					//console.log(topDoc.popGrid.getColumnId(i));
					for (var key in colJsonObj)
					{
						//console.log(key+"  "+colJsonObj[key]+"   "+id+"   "+getValueById(topDoc.popGrid, id, colJsonObj[key].toUpperCase()));
						//console.log(colJsonObj[key].toUpperCase()+"   "+topDoc.popGrid.getColumnId(i).toUpperCase());
						if(colJsonObj[key].toUpperCase() == topDoc.popGrid.getColumnId(i).toUpperCase())	
						{
							//console.log(_cellObj.cellIndex);
							
							//console.log(_cellObj.parentNode.idd+"!!!!!");
							
							inp.val(getValueById(topDoc.popGrid, id, colJsonObj[key]).replace(/&gt;/gi, ">"));
							setValueById(_gridObj, _cellObj.parentNode.idd, key, getValueById(topDoc.popGrid, id, colJsonObj[key]).replace(/&gt;/gi, ">"));
							
							if(curPageUpdate)topDoc.updateArray[currentPageId] = key;
							/*if(M$(key))
							{
								M$(key).value = getValueById(topDoc.popGrid, id, colJsonObj[key]).replace(/&gt;/gi, ">");
								if(curPageUpdate)topDoc.updateArray[currentPageId] = key;
							}*/
						}
					}
					
					/*if(M$(pValDesc[i]))
						M$(pValDesc[i]).value = topDoc.popGrid.cells(id,i).getValue().replace(/&gt;/gi, ">");*/
		  		}
				
				topDoc.myPop.hide();
				topDoc.popGrid.clearSelection();
				
				var childName = gChild[code];
				if(typeof childName != "undefined") setReadable(childName);
				
				
				var rtnJsonArry = new Array();
				rtnJsonArry.push(getRowData(topDoc.popGrid, id));
				//After All the process is ended, use this function.
				if(typeof afterAutoCmpt =="function") afterAutoCmpt(code, rtnJsonArry);
			});
			
			topDoc.myPop.detachEvent(eId);
			
		});

		topDoc.myPop.attachEvent("onClick", function(id){
		      console.log("You have clicked on the item with id="+id);
		});
		
		
		topDoc.myPop.show(x,y,w,h);

		if($.browser.name == "Chrome") //크롬의 경우 css가 다르게 적용.
		{
			w = w - 1;
			//gridHeight = gridHeight +17;
		}
		x = x - 1; //border 때문에 1 px 이동.

		//console.log("x:"+x+ " y:"+y+"   gridHeight:"+gridHeight+"  $(window).outerHeight():"+$(window).outerHeight());
		
		//console.log("topDoc.myPop._nodeId:"+topDoc.myPop._nodeId);
		//Resize Popup topDoc.myPop.setDimension(x, gridHeight) 
		//var node = topDoc.document.getElementById(topDoc.myPop._nodeId);
		var node = topDoc.$('#'+topDoc.myPop._nodeId);
		node.css("width",w+"px");
		node.css("height", gridHeight+"px");
		topDoc.myPop._repaint();
		if (topDoc.myPop._nodeObj != null && typeof(topDoc.myPop._nodeObj.setSizes) == "function") topDoc.myPop._nodeObj.setSizes();
		node = null;
		
		$('.dhx_popup_dhx_skyblue').css({
			"left":x
		});

		//When the popup is closed, All of the attached Event should be detached.
		onHideE = topDoc.myPop.attachEvent("onHide", function(){

			//Is Validation Input?
			if(gVal[code])validKey(code);
			
			topDoc.myPop.detachEvent(onHideE);
		});

	}
	//When there is no result, if the popup is showed, it should be closed.
	else if(listObj.length == 0)
	{
		if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
	}

	var keyIdJson = JSON.parse(gKeyIdMap[code]);
	var keyIdSize = 0;
	for(var key in keyIdJson){
		keyIdSize++;
	}

	//If the Validate is true, all the data inserted will be removed.
	//if(keyIdSize > 0 && listObj.length == 0 && val)
	if(keyIdSize > 0 && val)
	{
		validKey(code);
	}
}

/**
 * AutoComplete Function (자동완성) 
 * 1
 * @param pkeyMap MAP<Search Key Input Name,Search Key Column> ex) {"maPtDTO.partNo":"part_no"}
 * @param pColMap MAP<Result Input Name,Result Column> ex) {"maPtDTO.partNo":"part_no","maPtDTO.ptDesc":"description"}
 * @param pTable ex) "TAPARTS"
 * @param pCondition MAP<Condition Column,Condition Value> ex) {"part_categ":"SPPT"}
 * @param pkeyIdMap Key Input Name ex){"maPtDTO.partId":"part_id"}
 * @returns
 */
function autoCmpt(pkeyMap, pColMap, pTable, pCondition, pDcondition, pkeyIdMap, pLabel, pCustomLov, pIsCustom, pIsMultiSelect, pChild)
{
	if(typeof pColMap == "undefined" || pColMap == "") pColMap = {};
	if(typeof pCondition == "undefined" || pCondition == "") pCondition = {};
	if(typeof pDcondition == "undefined" || pDcondition == "") pDcondition = {};
	if(typeof pkeyIdMap == "undefined" || pkeyIdMap == "") pkeyIdMap = {};
	if(typeof pLabel == "undefined" || pLabel == "") pLabel = {};
	if(typeof pCustomLov == "undefined") pCustomLov = "";
	if(typeof pIsMultiSelect == "undefined") pIsMultiSelect = false;
	if(typeof pChild == "undefined") pChild = "";
	
	var topDoc = getTopPage();
	var pCode; 
	var keyCnt = 0;
	for (var key in pkeyMap)
	{
		if(keyCnt == 0) pCode = key;
		
		keyCnt++;
	}
	
	//=================== Remove Inline Event Handler ==============
	
	var tObj = $("[name='"+pCode+"']")
	tObj.removeAttr("onkeydown");
	tObj.next().find('a').removeAttr("href"); //onClick Function
	

	//=================== Key Search & Result Map ==================
	var isInCol = false;
	for(var colKey in pColMap)
	{
		if(colKey == pCode) isInCol = true;
	}	
	//Key Search Condition and input info
	var keyAddedColMap = JSON.parse(JSON.stringify(pkeyMap));
	var colCnt = 0;
	if(!isInCol)
		for (var key in keyAddedColMap)  // key : maPtMstrList.partNo, value : part_no
		{
			pColMap[key] = keyAddedColMap[key];
		}
	//==============================================================
	
	gkeyMap[pCode] = JSON.stringify(pkeyMap);
	gColMap[pCode] = JSON.stringify(pColMap);
	gCondition[pCode] = JSON.stringify(pCondition);
	gDcondition[pCode] = JSON.stringify(pDcondition);  //Dynamic Condition
	gKeyIdMap[pCode] = JSON.stringify(pkeyIdMap);
	gLabelMap[pCode] = JSON.stringify(pLabel);
	gChild[pCode] = pChild;
		
	var inputObj = $("input[name='"+pCode+"']");
	
	//console.log("ajaxAutoComplet:"+inputObj.size()+"    Input Name:"+pCode);

	if(inputObj.prop("readonly") && inputObj.parent().find(".open_spop").css("display") != "block") return;

	if(pChild != "") setReadOnly(pChild);
	
	inputObj.on({
		//Add Mouse Event For Cut Which delete the key value after cut description.
		"cut":function(e){ //paste, copy
			if(inputObj.prop("readonly")) return; 
			
			var colJsonObj = JSON.parse(gColMap[pCode]);
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
			{
				for (var key in colJsonObj)
				{
					if(M$(key) && key != pCode) M$(key).value = "";
					chkInputVal($("[name='"+key+"']"));
				}
				
				if(pChild != "")
				{
					var colChJsonObj = JSON.parse(gColMap[pChild]);
					for (var key in colChJsonObj)
					{
						if(M$(key)) M$(key).value = "";
					}
					
					if(pChild != "") setReadOnly(pChild);
				}
				
			}
		},
		"customE":function(e){
			if(inputObj.prop("readonly")) return; 
			//키업 event가 발생하면 key Input 제외하고 다른 Input 값을 Clear (TAB 키는 제외)
			var colJsonObj = JSON.parse(gColMap[pCode]);
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
			{
				for (var key in colJsonObj)
				{
					if(M$(key) && key != pCode) M$(key).value = "";;
					chkInputVal($("[name='"+key+"']"));
				}
				
				if(pChild != "")
				{
					var colChJsonObj = JSON.parse(gColMap[pChild]);
					for (var key in colChJsonObj)
					{
						if(M$(key)) M$(key).value = "";
					}
					
					if(pChild != "") setReadOnly(pChild);
				}
				
			}
		},
		"focusin":function(e){
			if(inputObj.prop("readonly")) return; 

			//포커스 되었을때 key 값 채크 
   			var keyIdJObj 	= JSON.parse(gKeyIdMap[pCode]);
   			var isKeyNull = true;
			for(var key in keyIdJObj)
			{
				//console.log(M$(key).value);
				if(M$(key).value != "" && M$(key).value != "0") isKeyNull = false; //MSSQL의 number type column의 기본값이 0, 코드가 0일때도 없는걸로 가정하고 AC 진행
			}
			//키 값이 이미 있으면 멈춰라!
			if(!isKeyNull) return;

			//keyup event 이후로 0.5초 이내에 다시 keyup event 발생시 이전의 keyup event는 Clear.
			if(autSt)clearTimeout(autSt);

			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;

			//up arrow, down arrow, enter, tab Key event는 autoComplete function trigger 안함.
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27) || typeof e.keyCode == "undefined")
			{
				autSt = setTimeout("$.globalEval( \"autoComplete('"+pCode+"','"+ pTable+"', "+false+","+false+", "+pIsCustom+");\" )", 500); 
			}
		
			if (!topDoc.popGrid) return //when Grid in popup is not available, stop here. 
			if (topDoc.myPop.isVisible()) {
				if (e.keyCode == 38) { // up
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind-1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 40) { // down
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind+1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 13) { // enter
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null && topDoc.popGrid.getRowIndex(id) >= 0) {
						topDoc.popGrid.callEvent("onRowSelect",[id]);
					}
					if(topDoc.myPopisVisible()) topDoc.myPop.hide(); 
				}
			}
		},
		"keyup":function(e){

			if(inputObj.prop("readonly")) return; 
			//키업 event가 발생하면 key Input 제외하고 다른 Input 값을 Clear (TAB 키는 제외)
			var colJsonObj = JSON.parse(gColMap[pCode]);
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
			{
				for (var key in colJsonObj)
				{
					if(M$(key) && key != pCode) M$(key).value = "";;
					chkInputVal($("[name='"+key+"']"));
				}
				
				if(pChild != "")
				{
					var colChJsonObj = JSON.parse(gColMap[pChild]);
					for (var key in colChJsonObj)
					{
						if(M$(key)) M$(key).value = "";
					}
					
					if(pChild != "") setReadOnly(pChild);
				}
				
			}
			
			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
			
			//keyup event 이후로 0.5초 이내에 다시 keyup event 발생시 이전의 keyup event는 Clear.
			if(autSt)clearTimeout(autSt);

			//up arrow, down arrow, enter, tab Key event는 autoComplete function trigger 안함.
			if(!(e.keyCode == 38 || e.keyCode == 40 || e.keyCode == 13 || e.keyCode == 9|| e.keyCode == 27))
			autSt = setTimeout("$.globalEval( \"autoComplete('"+pCode+"','"+ pTable+"', "+false+","+false+", "+pIsCustom+");\" )", 500); 
		
			if (!topDoc.popGrid) return //when Grid in popup is not available, stop here. 
			if (topDoc.myPop.isVisible()) {
				if (e.keyCode == 38) { // up
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind-1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 40) { // down
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null) {
						var ind = topDoc.popGrid.getRowIndex(id);
						topDoc.popGrid.selectRow(ind+1);
					} else {
						topDoc.popGrid.selectRow(0);
					}
				}
				if (e.keyCode == 13) { // enter
					var id = topDoc.popGrid.getSelectedRowId();
					if (id != null && topDoc.popGrid.getRowIndex(id) >= 0) {
						topDoc.popGrid.callEvent("onRowSelect",[id]);
					}
					else if(id == null && topDoc.popGrid.getRowsNum() == 1)
					{
						inputObj.trigger("blur");
						inputObj.parents(".field").next().find("input")[0].focus();
					}

					if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide(); 
				}
			}
		},
   		"blur":function(e){
			
   			if (topDoc.myPop && topDoc.myPop.isVisible()) {

   				if(topDoc.popGrid.getRowsNum() == 1 && inputObj.val()!= "")
   				{
   					topDoc.popGrid.callEvent("onRowSelect",[topDoc.popGrid.getRowId(0)]);
   				}
   			}
   			
   			if(inputObj.prop("readonly")) return; 
   			//if (topDoc.myPop && topDoc.myPop.isVisible()) return 

   			var pCode = $(e.target).prop("name");  //maPtMstrDetailDTO.partNo
   			var valKeyCnt = 0;
   			for(var key in pkeyIdMap)
   			{
   				valKeyCnt++;
   			}
   			
			//Before All the process is started, use this function.
			if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
				
   			if(valKeyCnt>0 && inputObj.val()!= ""){
   				setTimeout("$.globalEval( \"autoComplete('"+pCode+"','"+ pTable+"', "+true+","+false+");\" )", 500); 
   			}
   			
   			//autoComplete(pCode,pTable, true); //Validation
   		}
   	}); 
   	
   	if(inputObj.prop("readonly")) inputObj.off("keyup");
   	
   	//화면 Display여부가 "N"인 경우, Popup은 attach되지 안는다.
  // 	if(!inputObj.is(":visible")) return;
   	
   	function openLov(_pageId)
   	{
		var _btnLen = $("input[name='"+pCode+"']").next().is('p');
   		var _lovObj;
   		
   		if(_btnLen) _lovObj = $("input[name='"+pCode+"']").parent().find('.open_spop');
   		else _lovObj = $("input[name='"+pCode+"']").prev();
   		
   		_lovObj.on({
   			"click":function(e){
   				
   				//Before All the process is started, use this function.
   				if(typeof beforeAutoCmpt =="function") if(!beforeAutoCmpt(pCode)) return;
   				  				
   				var pkeyCode;
   				var keyJsonObj = JSON.parse(gkeyMap[pCode]);
   				var keyCodeCnt = 0;
   				for (var key in keyJsonObj){
   					if(keyCodeCnt == 0){
   						pkeyCode = keyJsonObj[key];   // part_no
   					}
   					keyCodeCnt++;
   				}
   				
   				//dDcondition(Dynamic Condition Setting) 
   				var dCondStr = JSON.parse(gDcondition[pCode]);
   				for(var key in dCondStr)  //key : Column 
   				{
   					if(M$(dCondStr[key]))
   					{
   						var conditionJson = JSON.parse(gCondition[pCode]);
   						conditionJson[key] = M$(dCondStr[key]).value;
   						
   						
   						gCondition[pCode] = JSON.stringify(conditionJson);	
   					}
   				}

   				var param = "code=" + encodeURIComponent(M$(pCode).value) + //Key Search Value
				"&keyCode=" + encodeURIComponent(pkeyCode) +  //Key Search Column
	            "&codeType=" + encodeURIComponent(pTable) +   // Table for Search
	            "&resultCol=" + encodeURIComponent(gColMap[pCode]) +// JSON.stringify(gColMap) +
	            "&label=" + encodeURIComponent(gLabelMap[pCode]) +// JSON.stringify(gColMap) +
	            "&chName=" + encodeURIComponent(gChild[pCode]) +// 
	            "&title=" + encodeURIComponent($(this).parent().prev().text()) +
 	            "&param=" + encodeURIComponent(gCondition[pCode]) + //JSON.stringify(gCondition) +  //Search Condition
	            "&isGridSearch=true"+  //For Session Check
   				"&isDecoratorName=popupPage" + 
		        "&popupWidth=810";
   				
   				var multiS = JSON.stringify({multiSelect:"Y"});
   				
   				if(pIsMultiSelect) param = param+"&extCode1="+multiS+"&multiSelect=Y";
   				
   				openLayerPopup(_pageId, param);
   			}
   		});
   	}

   	//if(typeof lovCommonMap[pTable] != "undefined" && pCustomLov == "")
   	if(typeof lovCommonMap[pTable] != "undefined")
   	{
   		openLov(lovCommonMap[pTable]);
   	}
   	else if($("input[name='"+pCode+"']").next().is('.open_spop') && !pIsCustom && $("input[name='"+pCode+"']").next().find('a').prop("href") == "")
   	{
   		openLov("maFinderAc");
   	}
   	else if($("input[name='"+pCode+"']").next().is('.open_spop') && pCustomLov != "")
   	{
   		$("input[name='"+pCode+"']").next().on({
   			"click":function(e){
   				$.globalEval(pCustomLov);
   			}
   		});
   	}
}


/**
 * Request Auto Complete as Post Ajax
 * 
 * code : maPtMstrList.partNo
 * codeType :TAPARTS
 * pIsValidate  : true
 */
function autoComplete(code, codeType, val, _exec, _isCustom)
{
	//Condition could be blank, then {} should be put for JSON Object;
	if(typeof pParam == "undefined" || pParam == "") param = {};
	if(typeof val == "undefined") val = false;
	if(typeof _exec == "undefined") _exec = false;
	if(typeof _isCustom == "undefined") _isCustom = false;
    
	gVal[code] = val;
	
	//Extracting Key Input Name 
	var pCode, pkeyCode;
	var keyJsonObj = JSON.parse(gkeyMap[code]);
	var keyCodeCnt = 0;
	for (var key in keyJsonObj){
		if(keyCodeCnt == 0){
			pCode = key;  //maPtMstrList.partNo
			pkeyCode = keyJsonObj[key];   // part_no
		}
		keyCodeCnt++;
	}
    //If there is no value in Key Input object, the process should stopped!
	/*if(M$(pCode).value == ""){
		if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide(); 
		return;
	}*/
	
	//키값 채크 필요!
	var keyIdJObj 	= JSON.parse(gKeyIdMap[code]);
	var colJsonObj 	= JSON.parse(gColMap[code]);
	var isKeyNull = false;
	if(val)
		for(var key in keyIdJObj)
		{
			for(var i in colJsonObj) // maPtMstrListDTO.partNo
			{
				if(key == i && M$(i).value != "") isKeyNull = true;
			}
		}
	
	//dDcondition(Dynamic Condition Setting) 
	var dCondStr = JSON.parse(gDcondition[code]);
	for(var key in dCondStr)  //key : Column 
	{
		if(M$(dCondStr[key]))
		{
			var conditionJson = JSON.parse(gCondition[code]);
			conditionJson[key] = M$(dCondStr[key]).value;
			
			
			gCondition[code] = JSON.stringify(conditionJson);	
		}
	}
		
	var strutsActionForAc = strutsActionAutoComplete;
	//키항목에 값이 없으면 정지
	if(isKeyNull) return;
	//if(_isCustom) strutsActionForAc = strutsActionValCustom;

	//Post Ajax 
	var actionUrl = contextPath + "/maFinderAc.do";
	var param = "code=" + M$(pCode).value + //Key Search Value
				"&keyCode=" + pkeyCode +  //Key Search Column
	            "&codeType=" + codeType +   // Table for Search
	            "&resultCol=" + gColMap[code] +// JSON.stringify(gColMap) +
	            "&param=" + encodeURIComponent(gCondition[code]) + //JSON.stringify(gCondition) +  //Search Condition
	            "&isGridSearch=true"+  //For Session Check
			    "&strutsAction=" + strutsActionForAc;

	$.post(actionUrl , param,  function(_data){
		if(checkSession(_data) && !_exec)
		{ 
			//var jsonObj = JSON.parse(_data);  
			
			//if(jsonObj.data.length > 1)
				autoCompleteSet(_data, code, codeType, val);
			//else if(jsonObj.data.length == 1)
			//	setOneValue(_data, code, codeType, val);
			//else 
			//	if (topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
		}
		else if(checkSession(_data) && _exec)
		{
			var jsonObj = JSON.parse(_data);  
			if(jsonObj.data.length == 1)
					setOneValue(_data, code, codeType, val);
		}
	}).done(function() {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});
	
}

function setOneValue(_jsonObj, code,  pVarDirType, val)
{
	var jsonO = JSON.parse(_jsonObj);
	var listObj = jsonO.data;
	var pValDesc = {};
	
	//Extracting Result Input Name Array for Ajax Result.  
	var colJsonObj = JSON.parse(gColMap[code]);

	if (topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
		
	//조회 List가 하나일 경우만 세팅
	if(listObj.length == 1)
	{
		var inp = $("input[name='"+code+"']").get(0);

		for (var key in listObj[0])
		{
			for (var colId in colJsonObj)
			{
				//console.log(key+"    "+colJsonObj[colId]);
				if(key.toUpperCase() == colJsonObj[colId].toUpperCase())
				{
					if(M$(colId))
					{
						//M$(colId).value = listObj[0][key];
						$('input[name="'+colId+'"]').val(listObj[0][key]);
						//$('input[name="'+colId+'"]').focus();
					}
				}
			}

		}
	}
	
}

/**
 * After Post Ajax Request for Auto Compelete.
 * 
 * @param _jsonObj : resultData (JSON)
 * @param code : maPtMstrList.partNo
 * @param pVarDirType : TAPARTS
 * @param pIsValidate : true
 * @returns
 */
function autoCompleteSet(_jsonObj, code,  pVarDirType, val)
{	
	var pValDesc = {};
	var pValCode,onClkE,onHideE;
	var colCnt = 0;
	//Extracting Result Input Name Array for Ajax Result.  
	var colJsonObj = JSON.parse(gColMap[code]);
	for (var key in colJsonObj)
	{
		pValDesc[colCnt] = key; 
		colCnt++;
	}

	//Extracting Key Input Name 
	var keyJsonObj = JSON.parse(gkeyMap[code]);
	pValCode = code;
	
	var ajaxKeyDescType = typeof pValDesc;

	//Convert result json string to json object.
	var jsonO = JSON.parse(_jsonObj);
	var listObj = jsonO.data; //Select List data
	//조회 List가 하나 이상일 경우 팝업으로 띄움
	if(listObj.length >= 1)
	{
		var inp = $("input[name='"+pValCode+"']").get(0);
		var inpWidth = ($("input[name='"+pValCode+"']").width());
		
		if (!topDoc.myPop)topDoc.myPop = new topDoc.dhtmlXPopup({mode:"bottom"}); //팝업은 아래쪽에...
		else topDoc.myPop.hide();

		//Iframe 전체 화면 높이 계산 
		var pObj = self;
		var topH = 0;
		var leftH = 0;
		var inCnt = 0;
		var isPop = false;
		
		if(pObj != top)
			do{
				var cPage = pObj.currentPageId;		

				var ifm;
				if(pObj.DECORATOR_NAME == "popupPage")ifm =  getIframe(cPage, pObj);
				else ifm = getIframe(cPage);
				
				if(!ifm)ifm = hFmArr[cPage];

				if(pObj.DECORATOR_NAME == "innerTabPage")
				{
					topH = topH + 9;
					inCnt++;
				}
				
				if(pObj.DECORATOR_NAME == "popupPage")
				{
					topH = topH - 9*inCnt;
					isPop = true;
				}
					
				if(ifm)
				{
					topH = topH + ifm.parent().offset().top ;
					leftH = leftH + ifm.parent().offset().left;
				}
					
				pObj = pObj.parent;
				
				
			}while(pObj != top); 

		hFmArr = {};
		//console.log(window.dhx4.absLeft(inp)+"   "+leftH+"    y:"+window.dhx4.absTop(inp)+"   "+topH);
		
		//Set the position of popup.
		var x = window.dhx4.absLeft(inp) + leftH;	
		var y = window.dhx4.absTop(inp)+ topH;
		var inY = window.dhx4.absTop(inp);
		var w = $("input[name='"+pValCode+"']").parent().width();
		var h = inp.offsetHeight;
		var gridHeight;

		eId = topDoc.myPop.attachEvent("onShow",function(){
			
			gridHeight = (listObj.length)*30+5;

			if(y > inY) inY = y;
			
			//console.log("gridHeight:"+gridHeight+"     "+window.outerHeight+"   "+inY+"    "+y);
			//아래 남은 공간 높이가 그리드 높이보다 작으면, 남은 공간 높이를 그리드 높이로 세팅한다.
			if((window.outerHeight-y-140) < gridHeight )
			{
				gridHeight = window.outerHeight-y-150;
			}

			topDoc.popGrid = topDoc.myPop.attachGrid(inpWidth, gridHeight-5);
			topDoc.popGrid.setImagePath("../dhtmlxSuite/codebase/imgs/")
			topDoc.popGrid.setNoHeader(true);
			topDoc.popGrid.init();

			topDoc.popGrid.parse(_jsonObj,"js");

			topDoc.popGrid.attachEvent("onRowDblClicked", function(id){
				var topDoc = getTopPage();
				//myForm.setItemValue("country", topDoc.popGrid.cells(id,1).getValue());
				for(var i = 0; topDoc.popGrid.getColumnsNum() > i; i++)
		  		{
					if(M$(pValDesc[i]))
					{
						M$(pValDesc[i]).value = topDoc.popGrid.cells(id,i).getValue().replace(/&gt;/gi, ">");
						if(curPageUpdate)topDoc.updateArray[currentPageId] = pValDesc[i];
					}
		  		}
				
				topDoc.myPop.hide();
				topDoc.popGrid.clearSelection(); 
				
				var rtnJsonArry = new Array();
				rtnJsonArry.push(getRowData(topDoc.popGrid, id));
				//After All the process is ended, use this function.
				if(typeof afterAutoCmpt =="function") afterAutoCmpt(code, rtnJsonArry);

				//Resize Popup topDoc.myPop.setDimension(x, gridHeight) 
				var node = topDoc.$('#'+topDoc.myPop._nodeId);
				node.css("width",w+"px");
				node.css("height", gridHeight+"px");
				topDoc.myPop._repaint();
				if (topDoc.myPop._nodeObj != null && typeof(topDoc.myPop._nodeObj.setSizes) == "function") topDoc.myPop._nodeObj.setSizes();
				node = null;
			});
			
			topDoc.popGrid.attachEvent("onRowSelect", function(id){
				var topDoc = getTopPage();
				var isFilter = false;
				for (var key in colJsonObj)
				{
					isFilter = isFilter||isFilterField(key);
				}
				for(var i = 0; topDoc.popGrid.getColumnsNum() > i; i++)
		  		{
					//console.log(topDoc.popGrid.getColumnId(i));
					for (var key in colJsonObj)
					{
						//console.log(key+"  "+colJsonObj[key]+"   "+id+"   "+getValueById(topDoc.popGrid, id, colJsonObj[key].toUpperCase()));
						if(colJsonObj[key].toUpperCase() == topDoc.popGrid.getColumnId(i).toUpperCase())	
						{
							if(M$(key))
							{
								M$(key).value = getValueById(topDoc.popGrid, id, colJsonObj[key]).replace(/&gt;/gi, ">");
								
								chkInputVal($("[name='"+key+"']"));
								if(curPageUpdate&&!isFilter)topDoc.updateArray[currentPageId] = key;
							}
						}
					}
					
					/*if(M$(pValDesc[i]))
						M$(pValDesc[i]).value = topDoc.popGrid.cells(id,i).getValue().replace(/&gt;/gi, ">");*/
		  		}
				
				topDoc.myPop.hide();
				topDoc.popGrid.clearSelection();
				
				var childName = gChild[code];
				if(typeof childName != "undefined") setReadable(childName);
				
				
				var rtnJsonArry = new Array();
				rtnJsonArry.push(getRowData(topDoc.popGrid, id));
				//After All the process is ended, use this function.
				if(typeof afterAutoCmpt =="function") afterAutoCmpt(code, rtnJsonArry);
			});
			
			topDoc.myPop.detachEvent(eId);
			
		});

		topDoc.myPop.attachEvent("onClick", function(id){
		      console.log("You have clicked on the item with id="+id);
		});
		
		//console.log("x:"+x+" y:"+y+" w:"+w+" h:"+h);
		topDoc.myPop.show(x,y,w,h);

		if($.browser.name == "Chrome") //크롬의 경우 css가 다르게 적용.
		{
			w = w - 1;
			//gridHeight = gridHeight +17;
		}

		if(!isPop) x = x - 1; //border 때문에 1 px 이동.

		//Resize Popup topDoc.myPop.setDimension(x, gridHeight) 
		var node = topDoc.$('#'+topDoc.myPop._nodeId);
		node.css("width",w+"px");
		node.css("height", gridHeight+"px");
		topDoc.myPop._repaint();
		if (topDoc.myPop._nodeObj != null && typeof(topDoc.myPop._nodeObj.setSizes) == "function") topDoc.myPop._nodeObj.setSizes();
		node = null;

		topDoc.$('.dhx_popup_dhx_skyblue').css({
			"left":x
			,"z-index":1500
		});

		//When the popup is closed, All of the attached Event should be detached.
		onHideE = topDoc.myPop.attachEvent("onHide", function(){

			//Is Validation Input?
			if(gVal[code])validKey(code);
			
			topDoc.myPop.detachEvent(onHideE);
		});

	}
	//When there is no result, if the popup is showed, it should be closed.
	else if(listObj.length == 0)
	{
		if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
	}

	var keyIdJson = JSON.parse(gKeyIdMap[code]);
	var keyIdSize = 0;
	for(var key in keyIdJson){
		keyIdSize++;
	}

	//If the Validate is true, all the data inserted will be removed.
	//if(keyIdSize > 0 && listObj.length == 0 && val)
	if(keyIdSize > 0 && val)
	{
		validKey(code);
	}
}

/**
 * Validation
 * Key Input value Check if not clear all
 * @param code
 * @returns
 */
function validKey(code)
{
	var keyIdJObj 	= JSON.parse(gKeyIdMap[code]);
	var colJsonObj 	= JSON.parse(gColMap[code]);
	var _gridObj    = topDoc.gGridObj[code];
	var _cellObj    = topDoc.gCellObj[code];
	
	var isKeyNull = false;
	for(var key in keyIdJObj)
	{
		for(var i in colJsonObj) // maPtMstrListDTO.partNo
		{
			if(_gridObj && _cellObj)
			{
				if(key == i && getValueById(_gridObj, _cellObj.parentNode.idd, i) != "") isKeyNull = true;
			}
			else
			{
				if(key == i && M$(i).value != "") isKeyNull = true;
			}
		}
	}

	if(!isKeyNull)
	{
		if(_gridObj && _cellObj)
		{
			//console.log(code+"   "+ _cellObj.parentNode.idd+"   "+$.trim(getValueById(_gridObj, _cellObj.parentNode.idd, code))+"$$");
			//console.log($.trim(getValueById(_gridObj, _cellObj.parentNode.idd, code)) == "");
			if($.trim(getValueById(_gridObj, _cellObj.parentNode.idd, code)) == "")
			{
				if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
			}
			else
			{
				setValueById(_gridObj, _cellObj.parentNode.idd, code, "");
				for(var i in colJsonObj)
				{
					setValueById(_gridObj, _cellObj.parentNode.idd, i, "");
				}
				
				alertMessage1(COMMON_CMSG039);
				if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();			
			}
		}
		else
		{			
			if(M$(code).value == "")
			{
				if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();
			}
			else
			{
				M$(code).value = "";
				chkInputVal($("[name='"+code+"']"));
				for(var i in colJsonObj)
				{
					if(M$(i))
					{ 
						M$(i).value = "";
						chkInputVal($("[name='"+i+"']"));
					}
				}

				alertMessage1(COMMON_CMSG039);
				if(topDoc.myPop && topDoc.myPop.isVisible()) topDoc.myPop.hide();			
			}
		}
	}
}



/** ========================CUSTOM FUNCTION ================================== **/

/*
 * System Code Description Auto Complete
 * acSysDesc(targetObjectName, codeObjectName, CodeType);
 * 시스템 코드명으로 시스템 코드 및 코드명 자동완성
 */
function acSysDesc()
{
	var asSysObj, objName, objType, resultObjName, isVal, _gridObj, _cellObj;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) 		objName = arguments[i];
		else if(i == 1) resultObjName = arguments[i];
		else if(i == 2) objType = arguments[i]; 
		else if(i == 3) isVal = arguments[i]; 
		else if(i == 4) _gridObj = arguments[i]; 
		else if(i == 5) _cellObj = arguments[i]; 
		
		if(typeof isVal == "undefined") isVal = false;
    }
	
	if(resultObjName == objName && isVal && !_gridObj)
	{
		$('form').eq(0).append($("<input type='hidden' name='"+resultObjName+"Id"+"'></input>"));
		$("[name='"+resultObjName+"Id']").val($("[name='"+resultObjName+"']").val());
		
		resultObjName = resultObjName+ "Id";
		
	}

	var keyMap = {},labelMap={},resultMap={};
	keyMap[objName] = "description";
	labelMap[objName] = "LABEL.desc";
	resultMap[resultObjName] = "cdsysd_no";

	asSysObj = new autoC(keyMap);
	asSysObj.setCol(_cellObj); //mandatory
	asSysObj.setGrid(_gridObj); //mandatory
	asSysObj.setTable("TACDSYSD");
	asSysObj.setAcConditionMap({
        	"list_type":objType,
        	"is_use":"Y"
  	   });
	asSysObj.setAcResultMap(resultMap);
	if(isVal) asSysObj.setKeyName(resultObjName);
	
	if(objType == "IS_USE")asSysObj.setAcResultLabel(labelMap);  
	else asSysObj.setCustomLov("lovSysDir('"+resultObjName+"', '"+objName+"', '"+objType+"')");
	
	asSysObj.init();

    return asSysObj;
}

/**
 * 시스템코드 Code로 Description을 세팅 (Status나 type등에 사용, 자동완성아님)
 * exec(); 로 실행
 * ex) maPtMstrListForm.elements['maPtMstrCommonDTO.filterMroType'].value = "C";
	   var sysCode = acSysCode("maPtMstrCommonDTO.filterMroType","maPtMstrCommonDTO.filterMroTypeDesc","MRO_TYPE");
	   sysCode.exec();
 * @returns
 */
function acSysCode()
{
	var asSysObj, objName, objType, resultObjName;
	for(var i = 0; i < arguments.length; i++) {
		if(i == 0) objName = arguments[i];
		else if(i == 1) resultObjName = arguments[i];
		else if(i == 2) objType = arguments[i]; 
    }

	var keyMap = {},labelMap={},resultMap={};
	keyMap[objName] = "cdsysd_no";
	labelMap[objName] = "LABEL.code";
	resultMap[resultObjName] = "description";
	
	asSysObj = new autoC(keyMap);
	asSysObj.setTable("TACDSYSD_CODE");
	asSysObj.setAcConditionMap({
			"lang":loginUser.langId,
        	"list_type":objType,
        	"is_use":"Y"
  	   });
	asSysObj.setAcResultMap(resultMap);
	asSysObj.init();

    return asSysObj;
}
