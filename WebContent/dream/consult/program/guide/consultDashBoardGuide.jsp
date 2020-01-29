<%--===========================================================================
Program Guide List
author  kim21017
version $Id: consultPgmGuideList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.guide.action.ConsultPgmGuideListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.PGMGUIDE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="dashboardPage">
<!-- 공통메뉴 -->
 
</head>
<BODY style="top:0; background-color:#fafafa;" marginheight="0" marginwidth="0" min-width:1300px;> 
<html:form action="/consultPgmGuideList.do">
<html:hidden property="strutsAction"/>
	<div class="ms_wrap" style="width:calc(25% - 17px); min-width:150px; height:680px;">
	  <div class="ms_title">작업계획</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_filter"><span>필터</span></a></div>
	  <div class="ms_content"> 
	  	<div class="msview_wrap">
	  		<div class="msplan_title"><h3>예방작업</h3></div>
		  	<ul class="msplan_view">
		  		<li><a href="#" class="ma_incom">10</a><p>미완료</p></li>
		  		<li><a href="#" class="ms_plan">3</a><p>계획</p></li>
		  		<li><a href="#" class="ms_total">13</a><p>전체</p></li>
	  		</ul>
	  	</div>
	  	<div class="msview_wrap">
		  	<div class="msplan_title"><h3>고장작업</h3></div>
		  	<ul class="msplan_view">
		  		<li><a href="#" class="ma_incom">0</a><p>미완료</p></li>
		  		<li><a href="#" class="ms_plan">1</a><p>계획</p></li>
		  		<li><a href="#" class="ms_total">1</a><p>전체</p></li>
		  	</ul>
		</div>
		<div class="msview_wrap">
		  	<div class="msplan_title"><h3>추가작업</h3></div>
		  	<ul class="msplan_view">
		  		<li><a href="#" class="ma_incom">3</a><p>미완료</p></li>
		  		<li><a href="#" class="ms_plan">2</a><p>계획</p></li>
		  		<li><a href="#" class="ms_total">5</a><p>전체</p></li>
		  	</ul>
		</div>
	  </div>
	</div>
	<div class="ms_wrap" style="width:calc(30% - 17px); min-width:250px; height:680px;">
	  <div class="ms_title">Project 현황</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_filter"><span>필터</span></a></div>
	  <div class="ms_content">
	  	<div class="ms_pjview">
	  		<!-- 경기도 -->
	  		<div class="pjv_box" style="top:110px; left:70px;">
	  			<div class="pjv_num"><a href="#" class="pjv_green">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 강원도 -->
	  		<div class="pjv_box" style="top:80px; left:150px;">
	  			<div class="pjv_num"><a href="#" class="pjv_red">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 충청남도 -->
	  		<div class="pjv_box" style="top:180px; left:25px;">
	  			<div class="pjv_num"><a href="#" class="pjv_yellow">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 충청북도 -->
	  		<div class="pjv_box" style="top:170px; left:110px;">
	  			<div class="pjv_num"><a href="#" class="pjv_red">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 경상북도 -->
	  		<div class="pjv_box" style="top:210px; left:180px;">
	  			<div class="pjv_num"><a href="#" class="pjv_green">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 경상남도 -->
	  		<div class="pjv_box" style="top:300px; left:150px;">
	  			<div class="pjv_num"><a href="#" class="pjv_green">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 전라북도 -->
	  		<div class="pjv_box" style="top:260px; left:60px;">
	  			<div class="pjv_num"><a href="#" class="pjv_red">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 전라남도 -->
	  		<div class="pjv_box" style="top:330px; left:20px;">
	  			<div class="pjv_num"><a href="#" class="pjv_yellow">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  		<!-- 제주도 -->
	  		<div class="pjv_box" style="top:415px; left:30px;">
	  			<div class="pjv_num"><a href="#" class="pjv_green">2</a></div>
	  			<div class="pjv_list">
	  				<a href="#">2</a>/<a href="#">1</a>/<a href="#">3</a>
	  			</div>
	  		</div>
	  	</div>
	  </div>
	</div>
	<div class="ms_wrap"  style="width:calc(45% - 17px); min-width:250px; height:300px;">
	  <div class="ms_title">보증수치 Worst Project</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_filter"><span>필터</span></a></div>
	  <div class="ms_content">
	  	<table width="100%" border="1" class="ms_tb" cellpadding="0" cellspacing="0" rules="rows" frame="hsides">
			  <tr>
			    <th scope="col">보증수치</th>
			    <th scope="col">Project명</th>
			    <th scope="col">보증값</th>
			    <th scope="col">현재값</th>
			    <th scope="col">편차</th>
			    <th scope="col">보증수준</th>
			  </tr>
			  <tr>
			    <td class="ms_tb_tit1">종합효율</td>
			    <td class="ms_tb_tit2">KCC 김천공장 ESS</td>
			    <td class="ms_tb_td">90.0%</td>
			    <td class="ms_tb_td">89.1%</td>
			    <td class="ms_tb_td">-0.9%</td>
			    <td class="ms_tb_td">보통</td>
			  </tr>
			  <tr>
			    <td class="ms_tb_tit1">가동율</td>
			    <td class="ms_tb_tit2">KCC 여주공장 ESS</td>
			    <td class="ms_tb_td">95.0%</td>
			    <td class="ms_tb_td">99.0%</td>
			    <td class="ms_tb_td">+0.4%</td>
			    <td class="ms_tb_td">보통</td>
			  </tr>
			  <tr>
			    <td class="ms_tb_tit1">배터리 잔존량</td>
			    <td class="ms_tb_tit2">AIO 목곡2</td>
			    <td class="ms_tb_td">250kWh</td>
			    <td class="ms_tb_td">250kWh</td>
			    <td class="ms_tb_td">-0.8%</td>
			    <td class="ms_tb_td">높음</td>
			  </tr>
			  <tr>
			    <td class="ms_tb_tit1">발전시간</td>
			    <td class="ms_tb_tit2">콘카그린쏠라 5호</td>
			    <td class="ms_tb_td">3.5시간/일</td>
			    <td class="ms_tb_td">3.6시간/일</td>
			    <td class="ms_tb_td">+2.7%</td>
			    <td class="ms_tb_td">높음</td>
			  </tr>
			</table>
	  </div>
	</div>
	<div class="ms_wrap" style="width:calc(45% - 17px); min-width:250px; height:363px;">
	  <div class="ms_title">Alarm List</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_filter"><span>필터</span></a></div>
	  <div class="ms_content">
	  		<table width="100%" border="1" class="ms_tb" cellpadding="0" cellspacing="0" rules="rows" frame="hsides">
			  <tr>
			    <th width="100">전체</th>
			    <td class="ms_tb_td" style="padding:0;">5</td>
			    <th width="100">중고장</th>
			    <td class="ms_tb_td" style="padding:0;">2</td>
			    <th width="100">경고장</th>
			    <td class="ms_tb_td" style="padding:0;">3</td>
			  </tr>
			</table>
			<table width="100%" border="1" class="ms_tb" cellpadding="0" cellspacing="0" rules="rows" frame="hsides">
			  <tr>
			    <th scope="col">&nbsp;# </th>
			    <th scope="col">발생시작시간</th>
			    <th scope="col">Project 명</th>
			    <th scope="col">Alarm 명</th>
			    <th scope="col">Alarm 등급</th>
			    <th scope="col">보증수준</th>
			  </tr>
			  <tr>
			    <td class="ms_tb_td">1</td>
			    <td class="ms_tb_td">2019-03-02<br>13:12:40</td>
			    <td class="ms_tb_tit2">KCC 김천공장 ESS</td>
			    <td class="ms_tb_td">Cell Over voltage</td>
			    <td class="ms_tb_td">중고장</td>
			    <td class="ms_tb_td">높음</td>
			  </tr>
			  <tr>
			    <td class="ms_tb_td">2</td>
			    <td class="ms_tb_td">2019-03-02<br>13:12:40</td>
			    <td class="ms_tb_tit2">AIO 목곡2</td>
			    <td class="ms_tb_td">PCS Door Open</td>
			    <td class="ms_tb_td">경고장</td>
			    <td class="ms_tb_td">높음</td>
			  </tr>
			  <tr>
			    <td class="ms_tb_td">3</td>
			    <td class="ms_tb_td">2019-03-02<br>13:12:40</td>
			    <td class="ms_tb_tit2">KCC 김천공장 ESS</td>
			    <td class="ms_tb_td">Cell Over voltage</td>
			    <td class="ms_tb_td">중고장</td>
			    <td class="ms_tb_td">높음</td>
			  </tr>	
		    </table>
	  </div>
	</div>
	<div class="ms_wrap" style="height:350px;">
	  <div class="ms_title">나의 작업</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_close"><span>닫기</span></a></div>
	  <div class="ms_content">
	    <div class="todo_box">
	      <ul>
	        <li>배당건수 <a href="#">56건</a></li>
	        <li>처리건수 <a href="#">48건</a></li>
	        <li>긴급작업 <a href="#">2건</a></li>
	      </ul>
	    </div>
	    <div class="ms_chart" style="background:url(/dream/common/images/ma/chart_ms.png) no-repeat center 50%">  </div>
	    <div class="todo_list">
	      <ul>
	        <li><a href="#">미완료작업(금일)<span>1</span></a></li>
	        <li><a href="#">미완료 분해작업(금일)<span>0</span></a></li>
	        <li><a href="#">미완료 정기점검(금일)<span>15</span></a></li>
	        <!-- 
	        <li><a href="#">미완료 작업(금주)<span>0</span></a></li>
	        <li><a href="#">미완료 분해점검(금주)<span>0</span></a></li>
	        <li><a href="#">미완료 정기점검(금주)<span>105</span></a></li>
	        <li><a href="#">안전재고미만건수<span>1</span></a></li>
	        <li><a href="#">결재대기<span>0</span></a></li>
	         -->
	      </ul>
	    </div>
	  </div>
	</div>
	<div class="ms_wrap" style="height:350px;">
	  <div class="ms_title">작업캘린더</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_close"><span>닫기</span></a></div>
	  <div class="ms_content">
	    <div class="ms_calender">
	      <table width="100%" border="0">
	        <tr>
	          <td colspan="7" class="mtd_month"><a href="#">&lt;</a> 2018. 03 <a href="#">&gt;</a></td>
	        </tr>
	        <tr>
	          <td class="mtd_day">일</td>
	          <td class="mtd_day">월</td>
	          <td class="mtd_day">화</td>
	          <td class="mtd_day">수</td>
	          <td class="mtd_day">목</td>
	          <td class="mtd_day">금</td>
	          <td class="mtd_day">토</td>
	        </tr>
	        <tr>
	          <td class="mtd_date">&nbsp;</td>
	          <td class="mtd_date">&nbsp;</td>
	          <td class="mtd_date">&nbsp;</td>
	          <td class="mtd_date">&nbsp;</td>
	          <td class="mtd_date">&nbsp;</td>
	          <td class="mtd_date"><a href="#">1</a></td>
	          <td class="mtd_date"><a href="#">2</a></td>
	        </tr>
	        <tr>
	          <td class="mtd_date"><a href="#">3</a></td>
	          <td class="mtd_date"><a href="#">4</a></td>
	          <td class="mtd_date"><a href="#">5</a></td>
	          <td class="mtd_date"><a href="#">6</a></td>
	          <td class="mtd_date"><a href="#">7</a></td>
	          <td class="mtd_date"><a href="#">8</a></td>
	          <td class="mtd_date"><a href="#">9</a></td>
	        </tr>
	        <tr>
	          <td class="mtd_date"><a href="#">10</a></td>
	          <td class="mtd_date"><a href="#">11</a></td>
	          <td class="mtd_date"><a href="#">12</a></td>
	          <td class="mtd_date"><a href="#">13</a></td>
	          <td class="mtd_date"><a href="#">14</a></td>
	          <td class="mtd_date"><a href="#">15</a></td>
	          <td class="mtd_date"><a href="#">16</a></td>
	        </tr>
	        <tr>
	          <td class="mtd_date"><a href="#">17</a></td>
	          <td class="mtd_date"><a href="#">18</a></td>
	          <td class="mtd_date"><a href="#">19</a></td>
	          <td class="mtd_date"><a href="#">20</a></td>
	          <td class="mtd_date"><a href="#">21</a></td>
	          <td class="mtd_date"><a href="#">22</a></td>
	          <td class="mtd_date"><a href="#">23</a></td>
	        </tr>
	        <tr>
	          <td class="mtd_date"><a href="#">24</a></td>
	          <td class="mtd_date"><a href="#">25</a></td>
	          <td class="mtd_date"><a href="#">26</a></td>
	          <td class="mtd_date"><a href="#">27</a></td>
	          <td class="mtd_date"><a href="#">28</a></td>
	          <td class="mtd_date"><a href="#">29</a></td>
	          <td class="mtd_date"><a href="#">30</a></td>
	        </tr>
	      </table>
	    </div>
	  </div>
	</div>
	<div class="ms_wrap" style="height:300px;">
	  <div class="ms_title">정비파트 업무현황</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_close"><span>닫기</span></a></div>
	  <div class="ms_content"> <img src="/dream/common/images/ma/chart2_ms.png"> </div>
	</div>
	<div class="ms_wrap" style="height:300px;">
	  <div class="ms_title">개인 업무현황</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_close"><span>닫기</span></a></div>
	  <div class="ms_content"> <img src="/dream/common/images/ma/chart2_ms.png"> </div>
	</div>
	<div class="ms_wrap long" style="height:300px;">
	  <div class="ms_title">개인 업무현황</div>
	  <div class="ms_function"><a href="#" class="b_ms_refresh"><span>새로고침</span></a><a href="#" class="b_ms_close"><span>닫기</span></a></div>
	  <div class="ms_content"> <img src="/dream/common/images/ma/chart2_ms.png"> </div>
	</div>
	
</html:form> 
</body>
</html>