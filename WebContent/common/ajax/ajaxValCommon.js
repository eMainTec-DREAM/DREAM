/*===========================================================================
AJAX Validation 체크
author  jung7126
version $Id:$
since   1.0
===========================================================================*/
var gValMap={},gValCond={},gValDcond={};//Keep Map Object Info as Global
var myPop, popGrid, eId, myPopTop;

function valCheck(name)
{ 
	var vaObjName;   //The Name of Input Object set by AutoComplete function 
	var vaKeyMap;   //This Map is configured with input object name for display and column name to search.
	var vaConditionMap; //This Map is configured with column name and cnodition value for search condition.
	var vaDconditionMap; //Dynamic Condition 
	var vaTable; //The Table Name in Database.
 	var vaCustomVal; //Custom LOV String trigger when clicked.
 	
 	//It could be Map<input name,column> or just input name. In the case of only input name inserted, the 'setAcName' is essential.  
	if(typeof name == "object")
	{
		var cntN = 0;
		for (var key in name)  // key : maPtMstrList.partNo, value : part_no
		{
			if(cntN ==0) vaObjName = key; //maPtMstrList.partNo
			
			cntN++;
		}
		vaKeyMap = name; //{"maPtMstrList.partNo":"part_no"}
	}
	else vaObjName = name; //Sting

	this.setVaConditionMap = function(condMap){
		vaConditionMap = condMap;
	}
	
	this.setVaDConditionMap = function(dCondMap)
	{
		vaDconditionMap = dCondMap;
	}
	
	this.setTable = function(tableName)
	{
		vaTable = tableName;
	}
	
	this.init = function()
	{
		//Check Essential Value 
		if(typeof vaKeyMap == "undefined"){
			alertMessage1("Validation 항목을 지정하세요.");
			return;
		}
		
		if(typeof vaTable == "undefined")
		{
			alertMessage1("Validation Table을 지정하세요.");
			return;
		}

		valCheckAction(vaKeyMap, vaTable, vaConditionMap, vaDconditionMap, vaCustomVal);
	}
	
	this.destroy = function()
	{
		$("input[name='"+vaObjName+"']").unbind( "blur");
	}
	
	//Set Custom LOV
	this.setCustomVal = function(valFnc)
	{
		vaCustomVal = valFnc;
		//$.globalEval(lovFnc);
	}
	
	this.getCustomVal = function()
	{
		return vaCustomVal;
	}
	
	this.exec = function(callbackFnc)
	{
		valCheckFunc(vaObjName, vaTable, callbackFnc);
	}
}



/**
 * AutoComplete Function (자동완성) 
 * 
 * @param pkeyMap MAP<Search Key Input Name,Search Key Column> ex) {"maPtDTO.partNo":"part_no"}
 * @param pColMap MAP<Result Input Name,Result Column> ex) {"maPtDTO.partNo":"part_no","maPtDTO.ptDesc":"description"}
 * @param pTable ex) "TAPARTS"
 * @param pCondition MAP<Condition Column,Condition Value> ex) {"part_categ":"SPPT"}
 * @param pkeyIdMap Key Input Name ex){"maPtDTO.partId":"part_id"}
 * @returns
 */
function valCheckAction(pkeyMap, pTable, pCondition, pDcondition, pCustomLov)
{
	if(typeof pCondition == "undefined" || pCondition == "") pCondition = {};
	if(typeof pDcondition == "undefined" || pDcondition == "") pDcondition = {};
	if(typeof pCustomLov == "undefined") pCustomLov = "";
	
	var pCode; 
	var keyCnt = 0;
	for (var key in pkeyMap)
	{
		if(keyCnt == 0) pCode = key;
		
		keyCnt++;
	}

	gValMap[pCode] = JSON.stringify(pkeyMap);
	gValCond[pCode] = JSON.stringify(pCondition);
	gValDcond[pCode] = JSON.stringify(pDcondition);  //Dynamic Condition

   	$("input[name='"+pCode+"']").on({
   		"blur":function(e){

   			//Complete Val Setting 
   			if(pCustomLov != "")
   			{
   				$.globalEval(pCustomLov);
   			}
   			else
   			{	
   				//작동! (rtn value would be number cnt of specific column;)
   				valCheckFunc(pCode, pTable);
   				//blur에서 rtn값이 1개 이상이면 inputObj clear 후에 focus 
   			}

   		}
   	}); 
   	
}

/**
 * Request Auto Complete as Post Ajax
 * 
 * code : maPtMstrList.partNo
 * codeType :TAPARTS
 * pIsValidate  : true
 */
function valCheckFunc(code, pTable, callBackFunc)
{   
	if(typeof callBackFunc == "undefined" ) callBackFunc = "";
	if(M$(code).value == ""){
		//alertMessage1(COMMON_CMSG013);
		return;
	}
	
	var pkeyCode;
	var keyJsonObj = JSON.parse(gValMap[code]);
	var keyCodeCnt = 0;
	for (var key in keyJsonObj){
		if(keyCodeCnt == 0){
			pkeyCode = keyJsonObj[key];   // part_no
		}
		keyCodeCnt++;
	}
	
	//Post Ajax 
	var rtnVal = "";
	var actionUrl = contextPath + "/maFinderAc.do";
	var param = "code=" + M$(code).value + //Key Search Value
				"&keyCode=" + pkeyCode +  //Key Search Column
	            "&codeType=" + pTable +   // Table for Search
	            "&param=" + gValCond[code] + //JSON.stringify(gValCond) +  //Search Condition
	            "&isGridSearch=true"+  //For Session Check
			    "&strutsAction=" + strutsActionVal;

	$.post(actionUrl , param,  function(_data){
		if(checkSession(_data)) {
			var jsonData = JSON.parse(_data);
			rtnVal = jsonData.count;	

			if(callBackFunc != "") $.globalEval(callBackFunc+"("+rtnVal+");");
			else afterValCheck(code, rtnVal);
		}
	}).done(function(_data) {
	  })
	  .fail(function() {
	  })
	  .always(function() {
	});
}


function afterValCheck(code, cnt)
{
	if(cnt > 0)
	{
		alertMessage1(COMMON_MSG0009);
		//0보다 크면 값 없애고 포커싱!
		$("[name='"+code+"']").val("").focus();
	}
}
