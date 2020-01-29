package dream.main.dao.sqlImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.main.dao.MainDAO;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;

/**
 * Main 에 보여줄 contents 를 조회 DAO 
 * @author  javaworker
 * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="mainDAOTarget"
 * @spring.txbn id="mainDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MainDAOSqlImpl extends BaseJdbcDaoSupportSql implements MainDAO
{
    /**
     * 해당 유저그륩이 사용가능한
     * parent_id 의 이하 menu를 검색하여 리턴한다.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param pMenuId
     * @param userGroup
     * @return [0][0] : Menu 이름(화면에 보여지는 이름) 
     *         [0][1] : Page_Id 
     *         [0][2] : Menu_Id 
     * @throws Exception
     */
    public String [][] findMenus(String pMenuId, String userGroup, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
        query.append("WITH CTE (p_menu_id, menu_id, lvl)   				");
        query.append("AS												");
        query.append("( 												");
        query.append("	SELECT p_menu_id, menu_id, 1 AS lvl 			");
        query.append(" 	FROM   TAMENU  									");
        query.append(" 	WHERE  p_menu_id = '"+pMenuId+"' 				");
        query.append("  UNION ALL 										");
        query.append("	SELECT a.p_menu_id, a.menu_id, lvl+1 AS lvl		");
        query.append("	FROM   TAMENU a, CTE b 							");
        query.append("	WHERE  a.p_menu_id = b.menu_id 					");
        query.append(") 												");
        
        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (	");
        query.append("SELECT          													");
        query.append("        ''    seqNo,                                     					");
        query.append("        (SELECT file_name                     					");
        query.append("           FROM TAPAGE                         					");
        query.append("          WHERE page_id = x.page_id             					");
        query.append("        ) page_id,                             					");
        query.append("        x.menu_id,                             					");
        query.append("        x.ord_no,                             					");
        query.append("        x.description description,             					");
        query.append("        x.param,                             						");
        query.append("        x.key_no keyNo ,          								");
        query.append("        x.ord_no ordNo,                                         	");
        query.append("        ct.lvl      lvl                                           ");
        query.append("FROM  TAMENU x INNER JOIN TAUSRGRPMENU y ON x.menu_id = y.menu_id  	");
        query.append("INNER JOIN CTE ct ON x.menu_id = ct.menu_id                             ");
        query.append("WHERE   x.menu_id = y.menu_id     								");
        query.append("   AND  y.usrgrp_id = '"+userGroup+"'  							");
        query.append("   AND  x.is_system = 'N'              							");
        query.append("   AND  x.is_use = 'Y'                 							");
        query.append(" ) a     															");
        query.append(" order by a.ordNo             										");
        
//        query.append("SELECT a.*, MIN(a.lvl) OVER(ORDER BY a.ordNo) AS MINLVL FROM (	");
//        query.append("SELECT          													");
//        query.append("        '',                                     					");
//        query.append("        (SELECT file_name                     					");
//        query.append("           FROM TAPAGE                         					");
//        query.append("          WHERE page_id = x.page_id             					");
//        query.append("        ) page_id,                             					");
//        query.append("        x.menu_id,                             					");
//        query.append("        x.ord_no,                             					");
//        query.append("        x.description description,             					");
//        query.append("        x.param,                             						");
//        query.append("        x.key_no keyNo ,          								");
//        query.append("        x.ord_no ordNo,                                         	");
//        query.append("        LEVEL      lvl                                            ");
//        query.append("FROM    TAMENU x ,  TAUSRGRPMENU y                                ");
//        query.append("WHERE   x.menu_id = y.menu_id     								");
//        query.append("   AND  y.usrgrp_id = '"+userGroup+"'  							");
//        query.append("   AND  x.is_system = 'N'              							");
//        query.append("   AND  x.is_use = 'Y'                 							");
//        query.append(" START WITH x.p_menu_id = '"+pMenuId+"'                           ");
//        query.append(" CONNECT BY PRIOR x.menu_id = x.p_menu_id                         ");
//        query.append(" ORDER SIBLINGS BY ord_no) a     									");
//        query.append(" order by lvl             										");
       
//        query.append("SELECT								");
//        query.append("		'',								");
//        query.append("		(SELECT file_name				");
//        query.append("		   FROM TAPAGE					");
//        query.append("		  WHERE page_id = x.page_id		");
//        query.append("		) page_id,						");
//        query.append("		x.menu_id,						");
//        query.append("		x.ord_no,						");
//        query.append("      x.description description,		");
//        query.append("      x.param,                        ");
//        query.append("      x.key_no keyNo					");
//        query.append("FROM TAMENU x ,  TAUSRGRPMENU y       ");
//        query.append("WHERE x.menu_id = y.menu_id           ");
//        query.append("   AND y.usrgrp_id = '"+userGroup+"'  ");
//        query.append("   AND x.is_system = 'N'              ");
//        query.append("   AND x.is_use = 'Y'                 ");
//        query.append("AND p_menu_id ='"+pMenuId+"'			");
//        query.append("ORDER BY x.ord_no			            ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.toStringArray(resultList);
    }
    
    public List findMenusList(String pMenuId, String userGroup, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
        query.append("WITH CTE (p_menu_id, menu_id, lvl)   				");
        query.append("AS												");
        query.append("( 												");
        query.append("	SELECT p_menu_id, menu_id, 1 AS lvl 			");
        query.append(" 	FROM   TAMENU  									");
        query.append(" 	WHERE  p_menu_id = '"+pMenuId+"' 				");
        query.append("  UNION ALL 										");
        query.append("	SELECT a.p_menu_id, a.menu_id, lvl+1 AS lvl		");
        query.append("	FROM   TAMENU a, CTE b 							");
        query.append("	WHERE  a.p_menu_id = b.menu_id 					");
        query.append(") 												");

        
        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (    ");
        query.append("SELECT          													");
        query.append("        (SELECT file_name                     					");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        ) pageId,                             					");
        query.append("        x.menu_id menuId,                             			");
        query.append("        x.p_menu_id pMenuId,                             			");
        query.append("        x.ord_no ordNo,                             				");
        query.append("        x.description description,             					");
        query.append("        ISNULL((SELECT file_name                     				");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        ),'')+'?'+ISNULL(x.param,'')  AS url,                    	");
        query.append("       (SELECT a.key_name                              			");
        query.append("        FROM   TALANG a                                			");
        query.append("        WHERE  a.key_no = x.key_no                     			");
        query.append("          AND  a.key_type ='MENU'                      			");
        query.append("          AND  a.lang = '"+lang+"') AS text,						");
        query.append("        x.param,                             						");
        query.append("        x.key_no keyNo ,          								");
        query.append("        x.key_type keyType ,                                      ");
        query.append("        x.is_external_link isExternalLink,                        ");
        query.append("        x.external_link externalLink,                             ");
        query.append("        x.is_get_link isGetLink,                             		");
        query.append("        ct.lvl lvl                                            	");
        query.append("FROM   (SELECT b.* FROM TAMENU b ,  TAUSRGRPMENU y              	");
        query.append("WHERE   b.menu_id = y.menu_id     								");
        query.append("  AND   y.usrgrp_id = '"+userGroup+"'  							");
        query.append("  AND   b.is_system = 'N'              							");
        query.append("  AND   b.service_type = 'WEB'           							");
        query.append("  AND   b.is_use = 'Y' ) x           								");
        query.append("JOIN CTE ct           											");
        query.append("	ON x.menu_id = ct.menu_id	                           			");
        query.append(" ) a      														");
        query.append(" ORDER BY a.ordNo         											");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return resultList;
    }

    /**
     * 관리자 메뉴를 조회한다.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userGroup : 로긴 유저 그룹
     * @return
     * @throws Exception
     */
    public String[][] findAdminMenu(String userGroup, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
      query.append("SELECT									");
      query.append("		'',								");
      query.append("		(SELECT file_name				");
      query.append("		   FROM TAPAGE					");
      query.append("		  WHERE page_id = x.page_id		");
      query.append("		) page_id,						");
      query.append("		x.menu_id,						");
      query.append("		x.ord_no,						");
      query.append("        x.description description,		");
      query.append("        x.key_no keyNo					");
      query.append("FROM TAMENU x, TAUSRGRPMENU y           ");
      query.append("WHERE x.menu_id = y.menu_id             ");
      query.append("   AND y.usrgrp_id = '"+userGroup+"'    ");
      //ADMIN 부모 menu_id가 56 .
//      query.append("  AND p_menu_id IN (SELECT a.menu_id		");
//      query.append("  				    FROM   TAMENU a		    ");
//      query.append("  				    WHERE  a.is_system = 'Y'");
//      query.append("  				   )                        ");
      query.append("  AND x.p_menu_id <> 0         			");
      query.append("  AND x.is_system = 'Y'         		");
      query.append("  AND x.is_use = 'Y'                    ");
      query.append("ORDER BY x.ord_no            			");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.toStringArray(resultList);
    }

    /**
     * 금일점검
     * @author  wondo
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findCheckList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        // 금일 미점검 내역
        query.append("SELECT TOP 5                                                      ");
        query.append("       z.item_no itemNo,                                          ");
        query.append("       x.description                                              ");
        query.append("FROM   T2CHECK_LIST_HDR x, T2CHECK_RESULT_HDR y, T2EQUIPMENT z    ");
        query.append("WHERE  x.check_list_no = y.check_list_no                          ");
        query.append("  AND  x.equip_no = z.equip_no                                    ");
        query.append("  AND  y.actual_date = CONVERT(VARCHAR, GETDATE(), 112)           ");
        query.append("ORDER BY z.item_no                                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 사용자 그룹에 권한이 없는 Menu를 조회한다.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     */
    public List findNoMenuList(String userGroup)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                    ");
        query.append("       y.file_name as page_id                             ");
        query.append("FROM  TAMENU x, TAPAGE y                                  ");
        query.append("WHERE  x.page_id = y.page_id                              ");
        query.append("  AND  NOT EXISTS (SELECT 1                               ");
        query.append("                   FROM   TAUSRGRPMENU a                  ");
        query.append("                   WHERE  a.menu_id = x.menu_id           ");
        query.append("                     AND  a.usrgrp_id = '" + userGroup + "')     ");
        
        return this.getJdbcTemplate().queryForList(query.toString());
    }
    
    public List findNoticeList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT TOP 5 *                                ");
        query.append("FROM (                                        ");
        
        query.append("    SELECT                                    ");
        query.append("           x.notice_no noticeNo,              ");
        query.append("           SUBSTRING(x.enter_date,1,4)+'-'+SUBSTRING(x.enter_date,5,2)+'-'+SUBSTRING(x.enter_date,7,2) enterDate,            ");
        query.append("           x.description                      ");
        query.append("    FROM   T2NOTICE x                         ");
        query.append("    ORDER BY x.notice_no DESC                 ");

        query.append(")                                             ");
        query.append("WHERE 1=1                             		");
        
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public List findBoardList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT TOP 5 *                                            ");
        query.append("FROM (                                                    ");
        query.append("      SELECT                                              ");
        query.append("             x.board_no boardNo,                          ");
        query.append("             SUBSTRING(x.enter_date,1,4)+'-'+SUBSTRING(x.enter_date,5,2)+'-'+SUBSTRING(x.enter_date,7,2) enterDate,     ");
        query.append("             x.description  description,                  ");
        query.append("             x.parent_no  parentNo                        ");
        query.append("      FROM   T2BOARD x                                    ");
        query.append("      START WITH x.parent_no='0'                          ");
        query.append("      CONNECT BY PRIOR x.board_no = x.parent_no           ");
        query.append("      ORDER SIBLINGS BY TO_NUMBER(REGEXP_SUBSTR(x.bd_view_no, '[^-]+', 1, 1 )) DESC, TO_NUMBER(REGEXP_SUBSTR (x.bd_view_no, '[^-]+', 1, 2 )) ASC");
        query.append("     )                                                    ");
        query.append("WHERE 1=1                                          		");

        return this.getJdbcTemplate().queryForList(query.toString());
    }
    
    public AppCntDTO getAppCntInfo(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        
        query.append("(                                        		");
        query.append("SELECT COUNT(1)                       	   	");
        query.append("FROM   T2APP_DOC a, T2APP_FLOW b              ");
        query.append("WHERE  a.app_doc_no = b.app_doc_no            ");     
        query.append("	AND  b.wf_status <> 'A'                     ");     
        query.append("	AND  b.app_type <> 'NT'                     ");           
        query.append("	AND  b.wf_action = 'P'                      ");
        query.append("	AND  b.app_type IN ('GR', 'AP')             ");
        query.append(" 	AND  b.user_id = x.userId                   ");
        query.append(") appCount,                                   "); // 결재처리문서
        
        query.append("(                                             ");
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2APP_DOC a, T2APP_FLOW b              ");           
        query.append("WHERE  a.app_doc_no = b.app_doc_no            ");          
        query.append("  AND  b.wf_status <> 'A'                     ");         
        query.append("  AND  b.app_type = 'NT'                      ");                
        query.append("  AND  b.wf_action = 'P'                      ");
        query.append("  AND  b.wf_status = 'Z'                      ");
        query.append("  AND  b.user_id = x.userId                   ");
        query.append(") notCount,                                   "); // 수신된 문서
                        
        query.append("(                                        		");
        query.append("SELECT COUNT(1)                              	");
        query.append("FROM   T2APP_DOC a, T2APP_FLOW b              ");  
        query.append("WHERE  a.app_doc_no = b.app_doc_no            ");   
        query.append("  AND  b.wf_status = 'A'                      ");
        query.append("  AND  b.user_id = x.userId                   ");
        query.append("  AND  a.app_status IN ('APP01', 'APP02')     "); // 결재요청한문서(미승인:요청/결재중)
        query.append(") reqCount                                    ");

        query.append("FROM                                          ");
        query.append("(                                             ");
        query.append("SELECT ? userId                               ");
        query.append("FROM   DUAL                                   ");
        query.append(") x                                           ");

        Object[] objects = new Object[]{
//                loginUser.getUserID()
        };
        
        return (AppCntDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AppCntDTO()));
    }
    
    /**
     * 로그인유저의 작업설계, 작업수행
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        ");
        
        query.append("(                                             ");
        
        // 작업설계(로그인유저 부서)
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2WORK_ORDER a                         ");
        query.append("WHERE  a.wo_status = 'BA'                     "); // 작업설계 상태
        query.append("  AND  a.no_plan = 'N'                        ");
//        String planPe = MwareConfig.getMainPeriodDay();
        String planPe = "";
        if (planPe != null && !"".equals(planPe))
        {
            query.append("  AND  a.plan_start_date <= TO_CHAR(SYSDATE+"+planPe+", 'YYYYMMDD')   ");
        }
        query.append("  AND  a.plan_dept_no = x.loginDeptNo         ");
        
        query.append(") woPlanCount,                                ");
        query.append("(                                             ");
        
        // 작업수행(로그인 유저 작업담당)
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2WORK_ORDER a                         ");
        query.append("WHERE  a.wo_status = 'CA'                     "); // 작업수행 상태
        query.append("  AND  a.work_user = x.userId                 ");
        
        query.append(") woResultCount,                              ");
        query.append("(                                             ");
        
        // HSE Revision Request(로그인 유저 Rev. Manager)
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM   T2HSE_REV a                            ");
        query.append("WHERE  1=1                                    ");
        query.append("  AND  a.rev_status = 'AZ'                    ");
        query.append("  AND  a.rev_user = x.userId                  ");
        
        query.append(") hseRevReqCount,                             ");
        query.append("(                                             ");
        
        // Stop Card(Pending)(로그인부서 action by)
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM   T2STOP_CARD a                          ");
        query.append("WHERE  a.hazard_status = 'PRO'                ");
        query.append("  AND  a.action_by = x.loginDeptNo            ");
        
        query.append(") pendCardCount                               ");
        
        query.append("FROM                                          ");
        
        query.append("(                                             ");
        query.append("SELECT ? userId,                              ");
        query.append("       ? loginDeptNo                          ");
        query.append("FROM   DUAL                                   ");
        query.append(") x                                           ");

        Object[] objects = new Object[]{
//                loginUser.getUserID(),
//                loginUser.getDeptNo()
        };
        
        return (MyWorkCntDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MyWorkCntDTO()));
    }

    /**
     * 전체 작업 Notice
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public WorkNoticeCntDTO getWorkNoticeCntInfo()
    {
//        String mainPe = MwareConfig.getMainPeriodDay();
    	String mainPe = "";
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                ");
        
        query.append("(                                                     ");
        
        // 자재구매입고
        query.append("SELECT COUNT(1)                                       ");
        query.append("FROM   T2PURCHASE_REQ x                               ");
        query.append("WHERE  x.req_group = 'PART'                           ");
        query.append("  AND  x.pr_status IN ('RZ', 'SI')                    "); // 요청되어서 입고완료 처리되지 않은 요청건수

        query.append(") purRecCount,                                        ");
        query.append("(                                                     ");
        
        // 자재출고예약
        query.append("SELECT COUNT(1)                                       ");
        query.append("FROM   T2WORK_ORDER x                                 ");
        query.append("WHERE  1=1                                            ");
        query.append("  AND  x.wo_status = 'CA'                             ");
        query.append("  AND  EXISTS (SELECT 1                               ");
        query.append("               FROM  T2WO_PARTS a                     ");
        query.append("               WHERE  a.wo_no = x.wo_no)              "); // 자재가 있는 W/O만 건수 조회
        
        query.append(") ptIssCount,                                                     ");
        
        query.append("(                                                                 ");
        
        // 장기미결W/O 설계
        query.append("SELECT COUNT(1)                                                   ");
        query.append("FROM   T2WORK_ORDER x                                             ");
        query.append("WHERE  x.wo_status = 'BA'                                         "); // 작업설계
        query.append("  AND  x.is_hold = 'Y'                                            ");
        
        query.append(") holdPlanCount,                                                  ");
        query.append("(                                                                 ");
        
        // 장기미결W/O
        query.append("SELECT COUNT(1)                                                   ");
        query.append("FROM   T2WORK_ORDER x                                             ");
        query.append("WHERE  x.wo_status = 'CA'                                         "); // 작업수행
        query.append("  AND  x.is_hold = 'Y'                                            ");
        
        query.append(") holdPerformCount,                                               ");
        query.append("(                                                                 ");
        
        // 전체 작업설계
        query.append("SELECT COUNT(1)                                                   ");
        query.append("FROM   T2WORK_ORDER x                                             ");
        query.append("WHERE  x.wo_status = 'BA'                                         ");
        query.append("  AND  x.is_hold = 'N'                                            ");
        
//        String planPe = MwareConfig.getMainWoPlanPeriod();
        String planPe = "";
        if (planPe != null && !"".equals(planPe))
        {
            query.append("  AND  x.plan_start_date <= TO_CHAR(SYSDATE+" + planPe + ", 'YYYYMMDD')       ");
        }
        
        query.append(") totalPlanCount,                         ");
        query.append("(                                         ");
        
        // 전체 작업수행
        query.append("SELECT COUNT(1)                           ");
        query.append("FROM   T2WORK_ORDER x                     ");
        query.append("WHERE  x.wo_status = 'CA'                 ");
        query.append("  AND  x.is_hold = 'N'                    ");
        
        query.append(") totalWorkCount,                         ");
        query.append("(                                         ");
        
        // 선급검사
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   T2CLS_SURVEY_LIST x, T2CLS_SURVEY_HIST y                                           ");
        query.append("WHERE  1 = 1                                                                              ");
        query.append("  AND  x.cls_list_no = y.cls_list_no(+)                                                   ");
        query.append("  AND  ISNULL(y.check_date,'99') = ISNULL((SELECT MAX(z.check_date)                             ");
        query.append("                                     FROM   T2CLS_SURVEY_HIST z                           ");
        query.append("                                     WHERE  x.cls_list_no = z.cls_list_no(+)),'99')       ");
        query.append("  AND  y.check_start_date <=     TO_CHAR(SYSDATE+180, 'YYYYMMDD')         ");
        
        query.append(") inspBodyCount,                                  ");
        query.append("(                                                 ");
        
        // MOC(open)
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   T2PLAN_MOC_DTL z                           ");
        query.append("WHERE  z.is_opened = 'Y'                          ");
        query.append("AND z.is_closed='N'                               ");
        query.append(") openMocCount                                       ");

        query.append("FROM  DUAL                                            ");

        return (WorkNoticeCntDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WorkNoticeCntDTO()));
    }

    /**
     * Rig 위치정보 조회
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public String getVsPostion()
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("        loc_name +', longitude '+ loc_long +' / latitude '+ loc_lat   ");
        query.append("FROM T2VESSEL                                                             ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
    /**
     * Inspection Chart
     * @author  pochul2423
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public InspChartDTO getInspChart()
    {
        //해당 주차의 일요일 날짜를 구한다. 첫번째 날로 사용하고 일요일에서 날짜를 더한다.
        String sunday =DateUtil.getFirstSundayOfWeek(DateUtil.getYear(), DateUtil.getWeekOfYear(DateUtil.getDate())) ;
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        //계획
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + sunday + "'        ");
        query.append("       )sundayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 1) + "'        ");
        query.append("       )mondayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 2) + "'        ");
        query.append("       )tuesdayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 3) + "'        ");
        query.append("       )wednesdayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 4) + "'        ");
        query.append("       )thursdayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 5) + "'        ");
        query.append("       )fridayPlan,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  actual_date='" + DateUtil.getAfterDays(sunday, 6) + "'        ");
        query.append("       )saturdayPlan,         ");
        //실적
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + sunday + "'        ");
        query.append("       )sundayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 1) + "'        ");
        query.append("       )mondayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 2) + "'        ");
        query.append("       )tuesdayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 3) + "'        ");
        query.append("       )wednesdayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 4) + "'        ");
        query.append("       )thursdayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 5) + "'        ");
        query.append("       )fridayResult,           ");
        query.append("       (SELECT COUNT(*)       ");
        query.append("        FROM   T2CHECK_RESULT_HDR     ");
        query.append("        WHERE  complete_date='" + DateUtil.getAfterDays(sunday, 6) + "'        ");
        query.append("       )saturdayResult,         ");
        query.append("       (SELECT COUNT(*)            ");
        query.append("        FROM   T2CHECK_RESULT_HDR             ");
        query.append("        WHERE  actual_date>='" + sunday + "'        ");
        query.append("        AND   actual_date<='" + DateUtil.getAfterDays(sunday, 6) + "'     ");
        query.append("       )totalPlan,        ");
        query.append("       (SELECT COUNT(*)               ");
        query.append("        FROM   T2CHECK_RESULT_HDR             ");
        query.append("        WHERE  complete_date>='" + sunday + "'      ");
        query.append("        AND   complete_date<='" + DateUtil.getAfterDays(sunday, 6) + "'       ");
        query.append("       )totalResult,           ");
        query.append("'" + sunday + "'sunday,     ");
        query.append("'" + DateUtil.getAfterDays(sunday, 1) + "'monday,     ");
        query.append("'" + DateUtil.getAfterDays(sunday, 2) + "'tuesday,    ");
        query.append("'" + DateUtil.getAfterDays(sunday, 3) + "'wednesday,  ");
        query.append("'" + DateUtil.getAfterDays(sunday, 4) + "'thursday,   ");
        query.append("'" + DateUtil.getAfterDays(sunday, 5) + "'friday,     ");
        query.append("'" + DateUtil.getAfterDays(sunday, 6) + "'saturday    ");
        query.append("FROM   dual       ");


        return (InspChartDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new InspChartDTO()));
    }
    
    /**
     * 자재 입고/출고 chart
     * @author  pochul2423
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception 
     */
    public PartChartDTO getPartChart() throws Exception
    {
        String[] monthArray = new String[6];
        String [] monthLastArray = new String[6];
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        //해당월부터 이전 6개월까지의 월을 넣는다.
        for(int i=0; i<monthArray.length;i++)          
        {                    
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            monthArray[i]= format.format(cal.getTime());
        }
        
        //해당하는달의 마지막 날짜를 구한다.
        for(int i=0; i<monthArray.length;i++)          
        {            
            monthLastArray[i]=monthArray[i]+DateUtil.getLastDayOfMonth(monthArray[i].substring(0, 4), monthArray[i].substring(4, 6));            
        }
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                            ");
        //입고
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[5]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[5]+"'       ");
        query.append("       )monthRec1,                 ");
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[4]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[4]+"'       ");
        query.append("       )monthRec2,                 ");
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[3]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[3]+"'       ");
        query.append("       )monthRec3,                 ");
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[2]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[2]+"'       ");
        query.append("       )monthRec4,                 ");
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[1]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[1]+"'       ");
        query.append("       )monthRec5,                 ");
        query.append("       (SELECT ISNULL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[0]+"01'         ");
        query.append("          AND  rec_Date<='"+DateUtil.getDate()+"'      ");
        query.append("       )monthRec6,                 ");
        //출고               
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[5]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[5]+"'       ");
        query.append("       )monthIss1,                 ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[4]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[4]+"'       ");
        query.append("       )monthIss2,                 ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[3]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[3]+"'       ");
        query.append("       )monthIss3,                 ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[2]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[2]+"'       ");
        query.append("       )monthIss4,                 ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[1]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[1]+"'       ");
        query.append("       )monthIss5,                 ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[0]+"01'         ");
        query.append("          AND  iss_Date<='"+DateUtil.getDate()+"'      ");
        query.append("       )monthIss6,                 ");
        query.append("       '"+monthArray[5]+","+monthArray[4]+","+monthArray[3]+","+monthArray[2]+","+monthArray[1]+"," +
        		""+monthArray[0]+"'monthArray,           ");
        query.append("(SELECT ISNULL(SUM(rec_qty),'0')             ");
        query.append("        FROM   T2PT_REC_HIST              ");
        query.append("        WHERE  rec_date>='20131001'               ");
        query.append("          AND  rec_Date<='20140331'               ");
        query.append("       )recTotal,     ");
        query.append("       (SELECT ISNULL(SUM(iss_qty),'0')              ");
        query.append("        FROM   T2PT_ISS_HIST              ");
        query.append("        WHERE  iss_date>='"+monthArray[5]+"01'    ");
        query.append("          AND  iss_Date<='"+DateUtil.getDate()+"' ");
        query.append("       )issTotal      ");
        query.append("FROM dual                          ");

        return (PartChartDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartChartDTO()));
    }

    /**
     * Find USer Menu
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findUserMenuList(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        query.append("SELECT																				");
        query.append("       y.menu_id menuId																");
        query.append("       ,y.description menuDesc														");
        query.append("       ,(SELECT b.file_name															");
        query.append("         FROM   TAPAGE b																");
        query.append("         WHERE  b.page_id = y.page_id) fileName										");
        query.append("       ,x.ord_no ordNo																");
        query.append("       ,x.usrmenu_id usrMenuId														");
        query.append("       ,y.param																		");
        query.append("       ,y.key_no keyNo																");
        query.append("FROM   TAUSRMENU x, TAMENU y															");
        query.append("WHERE  x.menu_id = y.menu_id															");
        query.append("  AND  x.user_id= '"+loginUser.getUserId()+"'											");
        query.append("  AND  x.comp_no= '"+loginUser.getCompNo()+"'											");
        query.append("  AND  y.is_use= 'Y'                      											");
        query.append("ORDER BY x.ord_no																		");

        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Linked Menu
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public String[][] findLinkedMenu(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                                                        ");
        query.append("        y.menu_id AS menuId                                                   ");
        query.append("        ,y.description AS linkedMenuDesc                                      ");
        query.append("        ,(SELECT b.file_name                                                  ");
        query.append("          FROM   TAPAGE b                                                     ");
        query.append("          WHERE  b.page_id = y.page_id) AS fileName                           ");
        query.append("        ,x.ord_no AS ordNo                                                    ");
        query.append("        ,y.key_no AS keyNo                                                    ");
        query.append("        ,y.key_type keyType                                                   ");
        query.append("        ,x.menu_id AS pMenuId                                                   ");
        query.append("FROM   TALINKEDMENU x , TAMENU y                                              ");
        query.append("WHERE x.linked_menu_id = y.menu_id                                            ");
        query.append("  AND  x.user_id = '"+loginUser.getUserId()+"'                                ");
        query.append("  AND  x.comp_no = '"+loginUser.getCompNo()+"'                                ");
        query.append("  AND  y.is_use= 'Y'                                                          ");
        query.append("ORDER BY x.ord_no                                                             ");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.toStringArray(resultList);
    }

	/**
	 * Find Menu List by Usr Grp
	 * @param userGroup
	 * @return
	 */
	public List findMenuList(String userGroup, User loginUser) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        
        query.append("SELECT								");
        query.append("		'',								");
        query.append("		(SELECT file_name				");
        query.append("		   FROM TAPAGE					");
        query.append("		  WHERE page_id = x.page_id		");
        query.append("		) page_id pageId,				");
        query.append("		x.menu_id menuId,				");
        query.append("		x.p_menu_id pMenuId,			");
        query.append("		x.ord_no ordNo,					");
        query.append("      x.description description,		");
        query.append("      x.param,                        ");
        query.append("      level lvl,                      ");
        query.append("      x.key_no keyNo                  ");
        query.append("FROM TAMENU x ,  TAUSRGRPMENU y       ");
        query.append("	  ,(SELECT * FROM dbo.SFAMENU_ALL('0')) z	");
        query.append("WHERE 1=1								");
        query.append("AND 	x.menu_id = y.menu_id			");
        query.append("AND   x.menu_id = z.menu_id           ");
        query.getAndQuery("y.usrgrp_id", userGroup);
        query.append("   AND x.is_system = 'N'              ");
        query.append("   AND x.is_use = 'Y'                 ");
        query.append("   AND p_menu_id ='0'			        ");
        query.append("ORDER BY ISNULL(x.ord_no, '99999999') ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());

	    return resultList;
	}

    public List findPageFields(User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                    ");
        query.append("       x.page_id pageId,                                                                                  ");
        query.append("       (SELECT b.file_name FROM TAPAGE b WHERE b.page_id = x.page_id) fileName,                           ");
        query.append("       x.field_id fieldId,                                                                                ");
        query.append("       CASE c.auth_limit_type                                                                             ");
        query.append("       WHEN 'NV' THEN 'Y'                                                                                 ");   //NV 조회불가
        query.append("       ELSE hidden_yn END hiddenYn,                                                                       ");
        query.append("       CASE c.auth_limit_type                                                                             ");
        query.append("       WHEN 'NE' THEN 'Y'                                                                                 ");   //수정불가
        query.append("       ELSE x.readonly_yn END readonlyYn,                                                                 ");
        query.append("       x.check_yn checkYn,                                                                                ");
        query.append("       ISNULL(y.ord_no, x.ord_no) ordNo,                                                                  ");
        query.append("       ISNULL(y.display_yn, x.display_yn) displayYn,                                                      ");
        query.append("       ISNULL(y.field_option, x.field_option) fieldOption,                                                ");
        query.append("       ISNULL(y.key_type,x.key_type)+'.'+ISNULL(y.key_no,x.key_no) langKey,                               ");
        query.append("       ISNULL(y.group_option, x.group_option) groupOption,                                                ");
        query.append("       ISNULL(y.group_key_type,x.group_key_type)+'.'+ISNULL(y.group_key_no,x.group_key_no) groupLangKey,  ");
        query.append("       x.str_length strLength,                                                                            ");
        query.append("       x.info_msg infoMsg                                                                                 ");
        query.append("FROM   TAPGFIELD x INNER JOIN TAUSER z ON 1 = 1                                                       ");
        query.getAndQuery("z.user_id", loginUser.getUserId());
        query.getAndQuery("z.comp_no", loginUser.getCompNo());
        query.append("LEFT OUTER JOIN TAUSRPGFIELD y ON x.pgfield_id = y.pgfield_id AND y.user_id = z.user_id                                                                               ");
        query.append("LEFT OUTER JOIN TAUGPGFIELDAU c ON c.usrgrp_id = z.usrgrp_id AND x.page_id = c.page_id AND c.field_id = x.field_id    ");
        query.append("ORDER BY ISNULL(y.ord_no, x.ord_no) DESC                                                                  ");


        return getJdbcTemplate().queryForList(query.toString());
    }

	public List findMenusJsonList(String pMenuId, String userGroup, User loginUser) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
        query.append("WITH CTE (p_menu_id, menu_id, lvl)   				");
        query.append("AS												");
        query.append("( 												");
        query.append("	SELECT p_menu_id, menu_id, 1 AS lvl 			");
        query.append(" 	FROM   TAMENU  									");
        query.append(" 	WHERE  p_menu_id = '"+pMenuId+"' 				");
        query.append("  UNION ALL 										");
        query.append("	SELECT a.p_menu_id, a.menu_id, lvl+1 AS lvl		");
        query.append("	FROM   TAMENU a, CTE b 							");
        query.append("	WHERE  a.p_menu_id = b.menu_id 					");
        query.append(") 												");
        
        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (    ");
        query.append("SELECT          													");
        query.append("        (SELECT file_name                     					");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        ) pageId,                             					");
        query.append("        x.menu_id id,                                 			");
        query.append("        x.menu_id menuId,                             			");
        query.append("        x.p_menu_id pMenuId,                             			");
        query.append("        x.ord_no ordNo,                             				");
        query.append("        x.description description,             					");
        query.append("        x.param,                             						");
        query.append("        x.key_no keyNo ,          								");
        query.append("        ISNULL((SELECT file_name                     				");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        ),'')+'?'+ISNULL(x.param,'')  AS url,                    	");
        query.append("      (SELECT a.key_name                              			");
        query.append("       FROM   TALANG a                                			");
        query.append("       WHERE  a.key_no = x.key_no                     			");
        query.append("         AND  a.key_type ='MENU'                      			");
        query.append("         AND  a.lang = '"+lang+"') AS text,						");
        query.append("        ct.lvl lvl                                            	");
        query.append("FROM   (SELECT b.* FROM TAMENU b ,  TAUSRGRPMENU y                ");
        query.append("WHERE   b.menu_id = y.menu_id     								");
        query.append("  AND   y.usrgrp_id = '"+userGroup+"'  							");
        query.append("  AND   b.is_system = 'N'              							");
        query.append("  AND   b.service_type = 'WEB'           							");
        query.append("  AND   b.is_use = 'Y' ) x                 						");
        query.append("JOIN CTE ct           											");
        query.append("	ON x.menu_id = ct.menu_id	                           			");
        query.append(" ) a     															");
        query.append(" ORDER BY a.lvl, a.ordNo         										");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return resultList;
	}

	@Override
	public List findMainList(User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findPageFilter(User loginUser) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
  	
        query.append("SELECT                                                                                            		    ");
        query.append("       x.usrfiltervalue_id  	usrFilterValueId,		                   		                        	    ");
        query.append("       x.page_id  			pageId,                                                                         ");
        query.append("       x.file_name 			fileName,												                        ");
        query.append("       x.comp_no				compNo,                                                                         ");
        query.append("       x.user_id				userId,                                                                         ");
        query.append("       x.user_no				userNo,			                                                                ");
        query.append("       x.is_default   		isDefault,													                    ");
        query.append("       x.setvalue				setValue,                                                                        ");
        query.append("       x.cre_date				creDate                                                                         ");
        query.append("FROM   TAUSRFILTERVALUE x 						                                                   			");
        query.append("WHERE  1=1 						                                                   			");
        query.append("  AND  x.is_default= 'Y'                      																");
        query.getAndQuery("x.user_id", loginUser.getUserId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append("ORDER BY x.cre_date DESC            				                                                     ");

        return getJdbcTemplate().queryForList(query.toString());
	}
}