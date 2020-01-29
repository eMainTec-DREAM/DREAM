<%--===========================================================================
Criticality Matrix 
author  kim21017
version $Id: rcmCrityMatrix.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityMatrixAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Criticality Matrix -->
<title><bean:message key='MENU.CRITICALMATRIX'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var colArr;

/** 자동완성 변수 */

function loadPage() 
{
	var url = contextPath + "/rcmCrityMatrix.do";
	rcmCrityMatrixForm.elements['strutsAction'].value = '<%=RcmCrityMatrixAction.COL_FIND%>';  
	setModal();
	$.post(url,FormQueryString(rcmCrityMatrixForm), function(_data){
		displayCol(_data);
    }).done(function(e){
		closeModal();
	});
}

function displayCol(data){
	colArr = data;
	findVal();
}

function findVal(){
	var url = contextPath + "/rcmCrityMatrix.do";
	rcmCrityMatrixForm.elements['strutsAction'].value = '<%=RcmCrityMatrixAction.VAL_FIND%>';  
	setModal();
	$.post(url,FormQueryString(rcmCrityMatrixForm), function(_data){
		displayVal(_data,colArr);
    }).done(function(e){
		closeModal();
	});
}

function displayVal(data,colData){
	var columnHdrId      = parseXmlDoc(colData, 'ARR0');
	var columnHdrName    = parseXmlDoc(colData, 'ARR1');
	
	var htmlStr = "<tr>";
	htmlStr += "<th colspan='2'><bean:message key='LABEL.separation'/></th>";
	for(var i=0;i<columnHdrId.length;i++){
		htmlStr +="<th class='"+columnHdrId[i]+"'>"+columnHdrName[i]+"</th>";
	}
	htmlStr += "</tr>";
	
	var valDesc    	= parseXmlDoc(data, 'ARR0');
	var crityColor 	= parseXmlDoc(data, 'ARR1');
	var rowId      	= parseXmlDoc(data, 'ARR2');
	var rowName    	= parseXmlDoc(data, 'ARR3');
	var ordNo      	= parseXmlDoc(data, 'ARR4');
	var uniqueId   	= parseXmlDoc(data, 'ARR5');
	var crityValueId= parseXmlDoc(data, 'ARR6');
	
	//조합 키 컬럼 받아서 조회 
	var rowIdDupArr = checkDup(rowId);

	for(var i=0;i<rowIdDupArr.length;i++){
		htmlStr += "<tr>";
		var rowIndex;
		for(var j=0;j<rowId.length;j++){
			if(rowIdDupArr[i]==rowId[j]){
				rowIndex = j;
				break;
			}
		}
		htmlStr +="<td class='th2'>"+ordNo[rowIndex]+"</td>";
		htmlStr +="<td width='100' class='th2'>"+rowName[rowIndex]+"</td>";
		for(var k=0;k<columnHdrId.length;k++){
			var index;
			var colrowId = columnHdrId[k]+rowIdDupArr[i];
			for(var h=0;h<uniqueId.length;h++){
				if(colrowId==uniqueId[h]){
					index = h;
					break;
				}
			}
			htmlStr +="<td style='background-color:"+crityColor[index]+";' class='"+columnHdrId[k]+"' id='"+crityValueId[index]+"'>"+valDesc[index]+"</td>";
		}
		
		htmlStr += "</tr>";
	}
	document.getElementById('matrixPart').innerHTML = htmlStr;
	
	afterDisplay();
}

function afterDisplay()
{	
	var idAry = opener.idAry;
	if(idAry)
	{
		for(var i = 0, len = idAry.length; i <len; i++)
		{
			$('#'+idAry[i]).addClass('selected');// alert(idAry[i]);
		}
	
		$('td:not(.th2)').on({
			
		  "click":function(e){
				if($(this).is('.selected'))
				{
					if(opener.saveMartix)
					{
						$(this).removeClass('selected');
						//리스트에서 제거 
						opener.saveMartix($(this).prop("id"), "DELETE");
					}
				}
				else
				{
					var cls = $(this).prop('class');
					if($('.'+cls+'.selected').size())
					{
						//alert("이미 선택했음");
					}
					else
					{
						if(opener.saveMartix)
						{
							$(this).addClass('selected');
							opener.saveMartix($(this).prop("id"), "INSERT");	
						}
					}
				}
			},
	      "mouseover":function(e){
	    	  $(this).css("opacity","0.5");
	      },
		  "mouseout":function(e){
	    	  $(this).css("opacity","1");
	      }
		}).css("cursor","pointer");
	
	}
}

function goClose()
{
	closeLayerPopup(this);
}

function checkDup(rowIdArr){
	var uniq = rowIdArr.reduce(function(a,b){
			if(a.indexOf(b)<0) a.push(b);
			return a;
		},[]);
	return rowIdArr.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b); return a;},[]);
}

//-->
</script>

</head>

<style type="text/css">
<!--
.m_tb { width:100%; height:100%; border-collapse:collapse; border:2px solid #aaa; }
.m_tb td, .m_tb th { border:1px solid #ccc; font-size:14px; text-align:center; padding:15px; border:1px solid #ccc; }
.m_tb th { padding:10px; background-color:#eee; border:1px solid #aaa; }
.m_tb td { color:#fff; border:1px solid #fff; font-weight:bold; }
.m_tb td.th2 { padding:15px; background-color:#f8f8f8; color:#555; font-weight:bold; border:1px solid #ccc; }
.m_tb td.selected { padding:5px; border:5px solid #333; }

-->
</style>


<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmCrityMatrix.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
</html:form> 
<table id="matrixPart" border="0" class="m_tb">
</table>


</body>
</html>