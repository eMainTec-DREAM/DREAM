package mobile.dream.main.dao.oraImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.main.dao.MainDAO;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;
import mobile.dream.main.dao.MobileMainDAO;

/**
 * Main �� ������ contents �� ��ȸ DAO 
 * @author  javaworker
 * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="mobileMainDAOTarget"
 * @spring.txbn id="mobileMainDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MobileMainDAOOraImpl extends BaseJdbcDaoSupportOra implements MobileMainDAO
{
    /**
     * �ش� �����׷��� ��밡����
     * parent_id �� ���� menu�� �˻��Ͽ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param pMenuId
     * @param userGroup
     * @return [0][0] : Menu �̸�(ȭ�鿡 �������� �̸�) 
     *         [0][1] : Page_Id 
     *         [0][2] : Menu_Id 
     * @throws Exception
     */
    public String [][] findMenus(String pMenuId, String userGroup, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
        query.append("SELECT a.*, MIN(a.lvl) OVER() AS MINLVL FROM (        		");
        query.append("SELECT          ");
        query.append("        '',                                     ");
        query.append("        (SELECT file_name                     ");
        query.append("           FROM TAPAGE                         ");
        query.append("          WHERE page_id = x.page_id             ");
        query.append("        ) page_id,                             ");
        query.append("        x.menu_id,                             ");
        query.append("        x.ord_no,                             ");
        query.append("      x.description description,             ");
        query.append("      x.param,                             ");
        query.append("      x.key_no keyNo ,          ");
        query.append("      x.ord_no ordNo,                                         ");
        query.append("      LEVEL      lvl                                                       ");
        query.append("FROM    TAMENU x ,  TAUSRGRPMENU y                                                    		");
        query.append("WHERE    x.menu_id = y.menu_id     ");
        query.append("   AND y.usrgrp_id = '"+userGroup+"'  ");
        query.append("   AND x.is_system = 'N'              ");
        query.append("   AND x.is_use = 'Y'                 ");
        query.append(" START WITH x.p_menu_id = '"+pMenuId+"'                                           ");
        query.append(" CONNECT BY PRIOR x.menu_id = x.p_menu_id                                ");
        query.append(" ORDER SIBLINGS BY ord_no) a     ");
        query.append(" order by lvl             ");
//
//        
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
        
        return QueryBuffer.toStringArray(resultList);
    }
    
    public List findMenusList(String pMenuId, String userGroup, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
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
        query.append("        (SELECT file_name                     					");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        )||'?'||x.param  AS url,                    		    	");
        query.append("       (SELECT a.key_name                              			");
        query.append("        FROM   TALANG a                                			");
        query.append("        WHERE  a.key_no = x.key_no                     			");
        query.append("          AND  a.key_type ='MENU'                      			");
        query.append("          AND  a.lang = '"+lang+"') AS text,						");
        query.append("        x.param,                             						");
        query.append("        x.key_no keyNo ,          								");
        query.append("        x.key_type keyType ,                                      ");
        query.append("        LEVEL      lvl                                            ");
        query.append("FROM   (SELECT b.* FROM TAMENU b ,  TAUSRGRPMENU y              	");
        query.append("WHERE   b.menu_id = y.menu_id     								");
        query.append("  AND   y.usrgrp_id = '"+userGroup+"'  							");
        query.append("  AND   b.is_system = 'N'              							");
        query.append("  AND   b.service_type = 'MOBILE'        							");
        query.append("  AND   b.is_use = 'Y' ) x               							");
        query.append(" START WITH x.p_menu_id = '"+pMenuId+"'                           ");
        query.append(" CONNECT BY PRIOR x.menu_id = x.p_menu_id                         ");
        query.append(" ORDER SIBLINGS BY ord_no) a     									");
        query.append(" ORDER BY lvl, ordNo         										");
//
//        
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
        
        return resultList;
    }

    /**
     * ������ �޴��� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userGroup : �α� ���� �׷�
     * @return
     * @throws Exception
     */
    public String[][] findAdminMenu(String userGroup, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
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
      //ADMIN �θ� menu_id�� 56 .
//      query.append("  AND p_menu_id IN (SELECT a.menu_id		");
//      query.append("  				    FROM   TAMENU a		    ");
//      query.append("  				    WHERE  a.is_system = 'Y'");
//      query.append("  				   )                        ");
      query.append("  AND x.p_menu_id <> 0         			");
      query.append("  AND x.is_system = 'Y'         		");
      query.append("  AND x.is_use = 'Y'                    ");
      query.append("ORDER BY x.ord_no            			");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.toStringArray(resultList);
    }

    /**
     * ��������
     * @author  wondo
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findCheckList(User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        // ���� ������ ����
        query.append("SELECT                                                            ");
        query.append("       z.item_no itemNo,                                          ");
        query.append("       x.description                                              ");
        query.append("FROM   T2CHECK_LIST_HDR x, T2CHECK_RESULT_HDR y, T2EQUIPMENT z    ");
        query.append("WHERE  x.check_list_no = y.check_list_no                          ");
        query.append("  AND  x.equip_no = z.equip_no                                    ");
        query.append("  AND  y.actual_date = TO_CHAR(SYSDATE, 'YYYYMMDD')               ");
        query.append("  AND  ROWNUM < 6                                                 ");
        query.append("ORDER BY z.item_no                                                ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * ����� �׷쿡 ������ ���� Menu�� ��ȸ�Ѵ�.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     */
    public List findNoMenuList(String userGroup)
    {
        QueryBuffer query = new QueryBuffer();

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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT *                                      ");
        query.append("FROM (                                        ");
        
        query.append("    SELECT                                    ");
        query.append("           x.notice_no noticeNo,              ");
        query.append("           substr (x.enter_date,0,4)||'-'||substr (x.enter_date,5,2)||'-'||substr (x.enter_date,7,2) enterDate,            ");
        query.append("           x.description                      ");
        query.append("    FROM   T2NOTICE x                         ");
        query.append("    ORDER BY x.notice_no DESC                 ");

        query.append(")                                             ");
        query.append("WHERE ROWNUM < 6                              ");
        
        return this.getJdbcTemplate().queryForList(query.toString());
    }

    public List findBoardList(User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT *                                                  ");
        query.append("FROM (                                                    ");
        query.append("      SELECT                                              ");
        query.append("             x.board_no boardNo,                          ");
        query.append("             substr (x.enter_date,0,4)||'-'||substr (x.enter_date,5,2)||'-'||substr (x.enter_date,7,2) enterDate,     ");
        query.append("             x.description  description,                  ");
        query.append("             x.parent_no  parentNo                        ");
        query.append("      FROM   T2BOARD x                                    ");
        query.append("      START WITH x.parent_no='0'                          ");
        query.append("      CONNECT BY PRIOR x.board_no = x.parent_no           ");
        query.append("      ORDER SIBLINGS BY TO_NUMBER(REGEXP_SUBSTR(x.bd_view_no, '[^-]+', 1, 1 )) DESC, TO_NUMBER(REGEXP_SUBSTR (x.bd_view_no, '[^-]+', 1, 2 )) ASC");
        query.append("     )                                                    ");
        query.append("WHERE ROWNUM < 6                                          ");

        return this.getJdbcTemplate().queryForList(query.toString());
    }
    
    public AppCntDTO getAppCntInfo(User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
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
        query.append(") appCount,                                   "); // ����ó������
        
        query.append("(                                             ");
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2APP_DOC a, T2APP_FLOW b              ");           
        query.append("WHERE  a.app_doc_no = b.app_doc_no            ");          
        query.append("  AND  b.wf_status <> 'A'                     ");         
        query.append("  AND  b.app_type = 'NT'                      ");                
        query.append("  AND  b.wf_action = 'P'                      ");
        query.append("  AND  b.wf_status = 'Z'                      ");
        query.append("  AND  b.user_id = x.userId                   ");
        query.append(") notCount,                                   "); // ���ŵ� ����
                        
        query.append("(                                        		");
        query.append("SELECT COUNT(1)                              	");
        query.append("FROM   T2APP_DOC a, T2APP_FLOW b              ");  
        query.append("WHERE  a.app_doc_no = b.app_doc_no            ");   
        query.append("  AND  b.wf_status = 'A'                      ");
        query.append("  AND  b.user_id = x.userId                   ");
        query.append("  AND  a.app_status IN ('APP01', 'APP02')     "); // �����û�ѹ���(�̽���:��û/������)
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
     * �α��������� �۾�����, �۾�����
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        
        query.append("(                                             ");
        
        // �۾�����(�α������� �μ�)
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2WORK_ORDER a                         ");
        query.append("WHERE  a.wo_status = 'BA'                     "); // �۾����� ����
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
        
        // �۾�����(�α��� ���� �۾����)
        query.append("SELECT COUNT(1)                               ");
        query.append("FROM   T2WORK_ORDER a                         ");
        query.append("WHERE  a.wo_status = 'CA'                     "); // �۾����� ����
        query.append("  AND  a.work_user = x.userId                 ");
        
        query.append(") woResultCount,                              ");
        query.append("(                                             ");
        
        // HSE Revision Request(�α��� ���� Rev. Manager)
        query.append("SELECT                                        ");
        query.append("       COUNT(1)                               ");
        query.append("FROM   T2HSE_REV a                            ");
        query.append("WHERE  1=1                                    ");
        query.append("  AND  a.rev_status = 'AZ'                    ");
        query.append("  AND  a.rev_user = x.userId                  ");
        
        query.append(") hseRevReqCount,                             ");
        query.append("(                                             ");
        
        // Stop Card(Pending)(�α��κμ� action by)
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
     * ��ü �۾� Notice
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
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        
        query.append("(                                                     ");
        
        // ���籸���԰�
        query.append("SELECT COUNT(1)                                       ");
        query.append("FROM   T2PURCHASE_REQ x                               ");
        query.append("WHERE  x.req_group = 'PART'                           ");
        query.append("  AND  x.pr_status IN ('RZ', 'SI')                    "); // ��û�Ǿ �԰��Ϸ� ó������ ���� ��û�Ǽ�

        query.append(") purRecCount,                                        ");
        query.append("(                                                     ");
        
        // �����������
        query.append("SELECT COUNT(1)                                       ");
        query.append("FROM   T2WORK_ORDER x                                 ");
        query.append("WHERE  1=1                                            ");
        query.append("  AND  x.wo_status = 'CA'                             ");
        query.append("  AND  EXISTS (SELECT 1                               ");
        query.append("               FROM  T2WO_PARTS a                     ");
        query.append("               WHERE  a.wo_no = x.wo_no)              "); // ���簡 �ִ� W/O�� �Ǽ� ��ȸ
        
        query.append(") ptIssCount,                                                     ");
        
        query.append("(                                                                 ");
        
        // ���̰�W/O ����
        query.append("SELECT COUNT(1)                                                   ");
        query.append("FROM   T2WORK_ORDER x                                             ");
        query.append("WHERE  x.wo_status = 'BA'                                         "); // �۾�����
        query.append("  AND  x.is_hold = 'Y'                                            ");
        
        query.append(") holdPlanCount,                                                  ");
        query.append("(                                                                 ");
        
        // ���̰�W/O
        query.append("SELECT COUNT(1)                                                   ");
        query.append("FROM   T2WORK_ORDER x                                             ");
        query.append("WHERE  x.wo_status = 'CA'                                         "); // �۾�����
        query.append("  AND  x.is_hold = 'Y'                                            ");
        
        query.append(") holdPerformCount,                                               ");
        query.append("(                                                                 ");
        
        // ��ü �۾�����
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
        
        // ��ü �۾�����
        query.append("SELECT COUNT(1)                           ");
        query.append("FROM   T2WORK_ORDER x                     ");
        query.append("WHERE  x.wo_status = 'CA'                 ");
        query.append("  AND  x.is_hold = 'N'                    ");
        
        query.append(") totalWorkCount,                         ");
        query.append("(                                         ");
        
        // ���ް˻�
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   T2CLS_SURVEY_LIST x, T2CLS_SURVEY_HIST y                                           ");
        query.append("WHERE  1 = 1                                                                              ");
        query.append("  AND  x.cls_list_no = y.cls_list_no(+)                                                   ");
        query.append("  AND  NVL(y.check_date,'99') = NVL((SELECT MAX(z.check_date)                             ");
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
     * Rig ��ġ���� ��ȸ
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public String getVsPostion()
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("        loc_name ||', longitude '|| loc_long ||' / latitude '|| loc_lat   ");
        query.append("FROM T2VESSEL                                                             ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
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
        //�ش� ������ �Ͽ��� ��¥�� ���Ѵ�. ù��° ���� ����ϰ� �Ͽ��Ͽ��� ��¥�� ���Ѵ�.
        String sunday =DateUtil.getFirstSundayOfWeek(DateUtil.getYear(), DateUtil.getWeekOfYear(DateUtil.getDate())) ;
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        //��ȹ
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
        //����
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
     * ���� �԰�/��� chart
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
        //�ش������ ���� 6���������� ���� �ִ´�.
        for(int i=0; i<monthArray.length;i++)          
        {                    
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -i);
            monthArray[i]= format.format(cal.getTime());
        }
        
        //�ش��ϴ´��� ������ ��¥�� ���Ѵ�.
        for(int i=0; i<monthArray.length;i++)          
        {            
            monthLastArray[i]=monthArray[i]+DateUtil.getLastDayOfMonth(monthArray[i].substring(0, 4), monthArray[i].substring(4, 6));            
        }
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        //�԰�
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[5]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[5]+"'       ");
        query.append("       )monthRec1,                 ");
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[4]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[4]+"'       ");
        query.append("       )monthRec2,                 ");
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[3]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[3]+"'       ");
        query.append("       )monthRec3,                 ");
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[2]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[2]+"'       ");
        query.append("       )monthRec4,                 ");
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[1]+"01'         ");
        query.append("          AND  rec_Date<='"+monthLastArray[1]+"'       ");
        query.append("       )monthRec5,                 ");
        query.append("       (SELECT NVL(SUM(rec_qty),'0')        ");
        query.append("        FROM   T2PT_REC_HIST       ");
        query.append("        WHERE  rec_date>='"+monthArray[0]+"01'         ");
        query.append("          AND  rec_Date<='"+DateUtil.getDate()+"'      ");
        query.append("       )monthRec6,                 ");
        //���               
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[5]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[5]+"'       ");
        query.append("       )monthIss1,                 ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[4]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[4]+"'       ");
        query.append("       )monthIss2,                 ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[3]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[3]+"'       ");
        query.append("       )monthIss3,                 ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[2]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[2]+"'       ");
        query.append("       )monthIss4,                 ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[1]+"01'         ");
        query.append("          AND  iss_Date<='"+monthLastArray[1]+"'       ");
        query.append("       )monthIss5,                 ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')        ");
        query.append("        FROM   T2PT_ISS_HIST       ");
        query.append("        WHERE  iss_date>='"+monthArray[0]+"01'         ");
        query.append("          AND  iss_Date<='"+DateUtil.getDate()+"'      ");
        query.append("       )monthIss6,                 ");
        query.append("       '"+monthArray[5]+","+monthArray[4]+","+monthArray[3]+","+monthArray[2]+","+monthArray[1]+"," +
        		""+monthArray[0]+"'monthArray,           ");
        query.append("(SELECT NVL(SUM(rec_qty),'0')             ");
        query.append("        FROM   T2PT_REC_HIST              ");
        query.append("        WHERE  rec_date>='20131001'               ");
        query.append("          AND  rec_Date<='20140331'               ");
        query.append("       )recTotal,     ");
        query.append("       (SELECT NVL(SUM(iss_qty),'0')              ");
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
        QueryBuffer query = new QueryBuffer();
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
        QueryBuffer query = new QueryBuffer();
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
        
        return QueryBuffer.toStringArray(resultList);
    }

	/**
	 * Find Menu List by Usr Grp
	 * @param userGroup
	 * @return
	 */
	public List findMenuList(String userGroup, User loginUser) 
	{
		QueryBuffer query = new QueryBuffer();
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
        query.append("WHERE x.menu_id = y.menu_id           ");
        query.getAndQuery("y.usrgrp_id", userGroup);
        query.append("   AND x.is_system = 'N'                ");
        query.append("   AND x.is_use = 'Y'                ");
        query.append("   AND p_menu_id ='0'			        ");
        query.append("START WITH p_menu_id = '0' 			");
        query.append("CONNECT BY PRIOR x.menu_id = p_menu_id");
        query.append("ORDER BY x.ord_no			            ");
        
        List resultList = getJdbcTemplate().queryForList(query.toString());

	    return resultList;
	}

    public List findPageFields(User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("       page_id pageId,                                ");
        query.append("       (SELECT b.file_name FROM TAPAGE b WHERE b.page_id = x.page_id) fileName,     ");
        query.append("       field_id fieldId,                              ");
        query.append("       hidden_yn hiddenYn,                            ");
        query.append("       x.readonly_yn readonlyYn,                      ");
        query.append("       x.check_yn checkYn,                            ");
        query.append("       NVL(y.ord_no, x.ord_no) ordNo,                 ");
        query.append("       NVL(y.display_yn, x.display_yn) displayYn,     ");
        query.append("       NVL(y.field_option, x.field_option) fieldOption,                             ");
        query.append("       NVL(y.key_type,x.key_type)||'.'||NVL(y.key_no,x.key_no) langKey              ");
        query.append("FROM   TAPGFIELD x, TAUSRPGFIELD y                    ");
        query.append("WHERE x.pgfield_id = y.pgfield_id(+)                  ");
        query.getAndQuery("y.user_id(+)", loginUser.getUserId());
        query.getAndQuery("y.comp_no(+)", loginUser.getCompNo());
        query.append("ORDER BY NVL(y.ord_no, x.ord_no) DESC                 ");


        return getJdbcTemplate().queryForList(query.toString());
    }

	public List findMenusJsonList(String pMenuId, String userGroup, User loginUser) {
		QueryBuffer query = new QueryBuffer();
        String lang = loginUser.getLangId();
        if("".equals(pMenuId))pMenuId = "0";
        
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
        query.append("        (SELECT file_name                     					");
        query.append("         FROM   TAPAGE                         					");
        query.append("         WHERE  page_id = x.page_id             					");
        query.append("        )||'?'||x.param  AS url,                    		    	");
        query.append("      (SELECT a.key_name                              ");
        query.append("       FROM   TALANG a                                ");
        query.append("       WHERE  a.key_no = x.key_no                     ");
        query.append("         AND  a.key_type ='MENU'                      ");
        query.append("         AND  a.lang = '"+lang+"') AS text,						");
        query.append("        LEVEL      lvl                                            ");
        query.append("FROM   (SELECT b.* FROM TAMENU b ,  TAUSRGRPMENU y                ");
        query.append("WHERE   b.menu_id = y.menu_id     								");
        query.append("  AND   y.usrgrp_id = '"+userGroup+"'  							");
        query.append("  AND   b.is_system = 'N'              							");
        query.append("  AND   b.service_type = 'MOBILE'              							");
        query.append("  AND   b.is_use = 'Y' ) x                 							");
        query.append(" START WITH x.p_menu_id = '"+pMenuId+"'                           ");
        query.append(" CONNECT BY PRIOR x.menu_id = x.p_menu_id                         ");
        query.append(" ORDER SIBLINGS BY ord_no) a     									");
        query.append(" ORDER BY lvl, ordNo         										");

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return resultList;
	}

	@Override
	public List findMainList(User user) {
		
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT (SELECT a.key_name                        		");
		query.append("            FROM talang a                            	");
		query.append("            WHERE  lang = '"+user.getLangId()+"'        	");
		query.append("            AND a.key_type = 'LABEL'                	");
		query.append("            AND a.key_no = 'bmWo') title,	");
		query.append("            (SELECT COUNT(1)                              	");
		query.append("			   FROM   TAWORKORDER x                  	");
		query.append("			   WHERE  x.wkor_date = TO_CHAR(SYSDATE,'yyyymmdd')  	");
		query.append("               AND  x.wo_status  <> 'C'                        	");
		query.append(" 				 AND  x.pm_type NOT IN ( 'INS' )		");
		if(!(user.getFilterDeptId() == "" || "null".equals(user.getFilterDeptId())))
        {
            query.append("  AND  x.dept_id IN (   SELECT dept_id                                ");
            query.append("                        FROM TADEPT                                   ");
            query.append("                        WHERE 1 = 1                                   ");
            query.getAndQuery("comp_no", user.getCompNo());
            query.append("                        START WITH dept_id  = "+user.getFilterDeptId()+"    ");
            query.append("                        CONNECT BY PRIOR dept_id = p_dept_id          ");
            query.append("                    )                                                 ");
        }
        if(!(user.getEqLocId() == "" || "null".equals(user.getEqLocId())))
        {
            query.append("AND eqloc_id IN (    SELECT eqloc_id                                  ");
            query.append("                        FROM TAEQLOC                                  ");
            query.append("                        WHERE 1 = 1                                   ");
            query.getAndQuery("comp_no", user.getCompNo());
            query.append("                        START WITH eqloc_id = "+user.getEqLocId()+"   ");
            query.append("                        CONNECT BY PRIOR eqloc_id = p_eqloc_id        ");
            query.append("                 )                                                    ");
        }
        query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
		query.append(" ) count,	");
		query.append(" 'maWoBmList' url													");
		query.append(" FROM DUAL														");
        //���� ��������
        query.append("UNION ALL        													");
		query.append("SELECT (SELECT a.key_name                        					");
		query.append("            FROM talang a                            				");
		query.append("            WHERE  lang = '"+user.getLangId()+"'        			");
		query.append("            AND a.key_type = 'MENU'                				");
		query.append("            AND a.key_no = 'PMWORK') title,						");
		query.append("            (SELECT COUNT(1)                              		");
		query.append("			   FROM   TAWORKORDER x                  				");
		query.append("			   WHERE  x.wkor_date = TO_CHAR(SYSDATE,'yyyymmdd')  	");
		query.append("               AND  x.wo_status  <> 'C'                        	");
		query.append(" 				 AND  x.pm_type IN ( 'PMW' )						");
		query.append(" 				 AND  x.comp_no = '"+user.getCompNo()+"'	    	");
		if(!(user.getFilterDeptId() == "" || "null".equals(user.getFilterDeptId())))
        {
            query.append("  AND  x.dept_id IN (   SELECT dept_id                                ");
            query.append("                        FROM TADEPT                                   ");
            query.append("                        WHERE 1 = 1                                   ");
            query.getAndQuery("comp_no", user.getCompNo());
            query.append("                        START WITH dept_id  = "+user.getFilterDeptId()+"    ");
            query.append("                        CONNECT BY PRIOR dept_id = p_dept_id          ");
            query.append("                    )                                                 ");
        }
        if(!(user.getEqLocId() == "" || "null".equals(user.getEqLocId())))
        {
            query.append("AND eqloc_id IN (    SELECT eqloc_id                                  ");
            query.append("                        FROM TAEQLOC                                  ");
            query.append("                        WHERE 1 = 1                                   ");
            query.getAndQuery("comp_no", user.getCompNo());
            query.append("                        START WITH eqloc_id = "+user.getEqLocId()+"   ");
            query.append("                        CONNECT BY PRIOR eqloc_id = p_eqloc_id        ");
            query.append("                 )                                                    ");
        }
        query.getWkCtrLevelQuery("x.wkctr_id", user.getWkctrId(), user.getWkctrDesc(), user.getCompNo());
		query.append(" ) count,							");
		query.append(" 'workPmWorkList' url				");
		query.append(" FROM DUAL						");

        return getJdbcTemplate().queryForList(query.toString());
	}
}