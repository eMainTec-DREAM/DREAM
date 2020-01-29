/**
===========================================================================
Mware 에서 사용하는 Active X를 로딩하기 위한 스크립트 파일
author  javaworker
version $Id: mwareActiveX.js,v 1.1 2013/08/30 09:11:50 javaworker Exp $
since   1.0
===========================================================================
*/

/**
 * Main.jsp 의 프레쉬 로딩
 */
function loadMainFlash()
{
	document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="275" height="377">' +
					'    <param name="movie" value="' + contextPath + '/common/images/main.swf ">				' + 
					'    <param name="quality" value="high">				' +
					'	<PARAM NAME="wmode" VALUE="transparent">				' +
					'    <embed src="' + contextPath + '/common/images/main.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="275" height="377"></embed>				' +
					'</object>');
}

/**
 * index.jsp flash loading
 */
function loadLoginFlash()
{
	document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="330" height="55">' +
					'    <param name="movie" value="' + contextPath + '/common/images/login/world_best.swf">				' + 
					'    <param name="quality" value="high">			' +
					'    <embed src="' + contextPath + '/common/images/login/world_best.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="330" height="55"></embed>' +
					'</object>');
}

/**
 * 도면 active X 를 로딩한다.
 */
function loadDrawWebocxActiveX()
{
	document.write('<OBJECT NAME = "webocx"                                                      ' +
					'	    CLASSID ="clsid:1E48B70D-9DD5-4DF2-9297-E3C20AD9C421"             ' +
					'		CODEBASE = "' + contextPath + '/common/downloadocx/ocxsetup.exe#version=1,0,0,0"      ' +
					'		WIDTH  = 0                                                    ' +
					'		HEIGHT = 0                                                    ' +
					'		HSPACE = 0                                                    ' +
					'		VSPACE = 0>                                                   ' +
					'  <PARAM NAME="LocalFileNAME" VALUE="excel.exe">                             ' +
					'  <PARAM NAME="ExecuteType" VALUE="6">                                       ' +
					'  <PARAM NAME="ButtonImage" VALUE="">                                        ' +
					'  <PARAM NAME="FTPUserId" VALUE="">                                          ' +
					'  <PARAM NAME="FTPUserPass" VALUE="">                                        ' +
					'  <PARAM NAME="FTPIpAddress" VALUE="">                                       ' +
					'  <PARAM NAME="FTPPort" VALUE="21">                                          ' +
					'  <PARAM NAME="FTPLocalFile" VALUE="a">                                      ' +
					'  <PARAM NAME="FTPServerFile" VALUE="b">                                     ' +
					'  <PARAM NAME="WebAction" VALUE="0">                                         ' +
					'  <PARAM NAME="AutoSelect" VALUE="-1">                                       ' +
					'  <PARAM NAME="AutoSize" VALUE="-1">                                         ' +
					'  <PARAM NAME="BorderStyle" VALUE="1">                                       ' +
					'  <PARAM NAME="CharCase" VALUE="0">                                          ' +
					'  <PARAM NAME="Color" VALUE="2147483653">                                    ' +
					'  <PARAM NAME="Ctl3D" VALUE="-1">                                            ' +
					'  <PARAM NAME="DragCursor" VALUE="-12">                                      ' +
					'  <PARAM NAME="DragMode" VALUE="0">                                          ' +
					'  <PARAM NAME="Enabled" VALUE="-1">                                          ' +
					'  <PARAM NAME="Font" VALUE="MS Sans Serif">                                  ' +
					'  <PARAM NAME="HideSelection" VALUE="-1">                                    ' +
					'  <PARAM NAME="ImeMode" VALUE="3">                                           ' +
					'  <PARAM NAME="ImeNAME" VALUE="한국어(한글) (MS-IME98)">                     ' +
					'  <PARAM NAME="MaxLength" VALUE="0">                                         ' +
					'  <PARAM NAME="OEMConvert" VALUE="0">                                        ' +
					'  <PARAM NAME="ParentColor" VALUE="0">                                       ' +
					'  <PARAM NAME="ParentCtl3D" VALUE="-1">                                      ' +
					'  <PARAM NAME="PasswordChar" VALUE="0">                                      ' +
					'  <PARAM NAME="ReadOnly" VALUE="0">                                          ' +
					'  <PARAM NAME="Text" VALUE>                                                  ' +
					'  <PARAM NAME="Visible" VALUE="-1">                                          ' +
					'  <PARAM NAME="Modified" VALUE="0">                                          ' +
					'  <PARAM NAME="SelLength" VALUE="0">                                         ' +
					'  <PARAM NAME="SelStart" VALUE="0">                                          ' +
					'  <PARAM NAME="SelText" VALUE>                                               ' +
					'  <PARAM NAME="DoubleBuffered" VALUE="0">                                    ' +
					'  <PARAM NAME="Cursor" VALUE="0">                                            ' +
					'</OBJECT>                                                                    '
					);
}
function loadDrawRapidviewActiveX()
{
    document.write('<OBJECT ID       = "RapidImage"                                                                   ' +
                    '        CLASSID  = "clsid:57B5EA46-A137-11D4-B4E1-00C026DDDA1A"                                  ' +
                    '       CODEBASE = "' + contextPath + '/common/draw/ocxsetup.exe#version=1,0,0,0"                         ' +
                    '       WIDTH    = "234"                                                                          ' +
                    '       HEIGHT   = "165">                                                                         ' +
                    '      <PARAM NAME="Serialize" VALUE=1>                                                          ' +
                    '      <PARAM NAME="UIMode" VALUE=7>                                                             ' +
                    '      <PARAM NAME="BarcodeType" VALUE=1>                                                        ' +
                    '      <PARAM NAME="Barcode" VALUE="abcdefg12345678">                                            ' +
                    '      <PARAM NAME="HasToolBar" VALUE=1>                                                         ' +
                    '      <PARAM NAME="HasMarkupBar" VALUE=1>                                                       ' +
                    '      <PARAM NAME="HasStatusBar" VALUE=1>                                                       ' +
                    '      <PARAM NAME="HasFontDlg" VALUE=0>                                                         ' +
                    '      <PARAM NAME="HasPrintDlg" VALUE=1>                                                        ' +
                    '      <PARAM NAME="HasPrintPriv" VALUE=1>                                                       ' +
                    '      <PARAM NAME="BackColor" VALUE=0>                                                          ' +
                    '      <PARAM NAME="MonoFrColor" VALUE=0>                                                        ' +
                    '      <PARAM NAME="MonoBkColor" VALUE=16777215>                                                 ' +
                    '      <PARAM NAME="CursorStyle" VALUE=1>                                                        ' +
                    '      <PARAM NAME="LoadMarkup" VALUE=1>                                                         ' +
                    '      <PARAM NAME="ShowMarkup" VALUE=1>                                                         ' +
                    '      <PARAM NAME="LoadOverlay" VALUE=1>                                                        ' +
                    '      <PARAM NAME="OverlayStyle" VALUE=3>                                                       ' +
                    '      <PARAM NAME="AliasStyle" VALUE=2>                                                         ' +
                    '      <PARAM NAME="Threshold" VALUE=100>                                                        ' +
                    '      <PARAM NAME="Subsample" VALUE=1>                                                          ' +
                    '      <PARAM NAME="SmoothScroll" VALUE=1>                                                       ' +
                    '      <PARAM NAME="FontPath" VALUE="C:\PROGRAM FILES\EMAINTEC\XRAPIDVUE\FONT;D:\Down\Font;">    ' +
                    '</OBJECT>                                                                                        ' 
                    );
}


/**
** 바코드 인쇄 acitveX 호출
*/
function loadBarcodeActiveX()
{
	document.write('<object ID="PrinterCtrl" ' + 
			       'classid="CLSID:FCAF9E10-7AE4-417E-B58C-184848CE2371" ' + 
			       'codebase="' + contextPath + '/common/barcodeocx/ShipPrintCtl.cab#version=1,0,0,0"' +
			       ' width="0" height="0" ' + 
			       '>'+
					'</object>');
}

/**
 * Report Ddesigner ActiveX 호출
 */
function loadReportActiveX()
{
	var cabloc =  contextPath +'/common/report/rdviewer50u.cab';
	var pdfloc =  contextPath +'/common/report/rdpdf50.cab';
	//document.write('<object id=Rdviewer name=Rdviewer width=100% height=100% classid="clsid:5A7B56B3-603D-4953-9909-1247D41967F8" codebase="'+cabloc+'#version=5,0,0,287" ></object>');
	document.write('<object id=Rdviewer name=Rdviewer width=100% height=100% classid="clsid:5A7B56B3-603D-4953-9909-1247D41967F8" codebase="'+cabloc+'#version=5,0,0,300" ></object>');
	document.write('<object id=rdpdf50 classid="clsid:0D0862D3-F678-48B5-876B-456457E668BC" width=100% height=100% codebase="'+pdfloc+'"#version=2,1,0,58"></OBJECT>');
}

/**
 * Report Ddesigner ActiveX 호출
 */
function loadRdBarcodeActiveX()
{
	var cabloc =  contextPath +'/common/report/rdbarcode5.cab';
	document.write('<object id=Rdbarcode name=Rdbarcode width=100% height=100% classid="clsid:AA30E61C-DBC4-4DF6-B2CC-FAE39282CF56" codebase="'+cabloc+'#version=5,5,0,50" ></object>');
} 
