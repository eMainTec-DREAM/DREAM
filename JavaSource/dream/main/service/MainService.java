package dream.main.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;

/**
 * Main
 * @author  javaworker
 * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
 * @since   1.0
 *
 */
public interface MainService
{
    /**
     * 행당 User의 그룹권한에 해당하는 
     * Menu를 ArrayList로 리턴한다.
     * 하나의 sub menu는 하나의 HashTable로 구성된다.
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup : 로긴 유저 권한
     * @return ArrayList 0 Main Menu
     *                   1 이하 sub Menu
     * @throws Exception
     */
    public ArrayList findMenuList(String userGroup, User loginUser);
    
    public Map findMenuJsonList(String userGroup, User loginUser);

    /**
     * 유저 그룹 
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     * @throws Exception
     */
    public String [][] findAdminMenu(String userGroup, User loginUser);
    
    /**
     * 금일 점검 내역
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     * @throws Exception
     */
    public List findCheckList(User loginUser);

    /**
     * 사용자 그룹에 권한이 없는 Menu를 조회한다. 
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     */
    public List findNoMenuList(String userGroup);

    /**
     * 공지사항
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
     public List findNoticeList(User loginUser);

     /**
     * 게시판
     * @author  pochul2423
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
     public List findBoardList(User loginUser);
      
    /**
     * Approval 정보 건수
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public AppCntDTO getAppCntInfo(User loginUser);

    /**
     * My Work 정보 건수
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser);

    /**
     * Work Notice 정보 건수
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public WorkNoticeCntDTO getWorkNoticeCntInfo(User loginUser);

    /**
     * Rig 위치정보 조회
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public String getVsPostion();
    
    /**
     * Inspection Chart
     * @author  pochul2423
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public InspChartDTO getInspChart();
    
    /**
     * Part Chart
     * @author  pochul2423
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception 
     */
    public PartChartDTO getPartChart() throws Exception;

    /**
     * User Menu
     * @author  pochul2423
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception 
     */
    public List findUserMenuList(User loginUser);

    /**
     * Linked Menu
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public String[][] findLinkedMenu(User loginUser);

    /**
     * Field 정보 조회
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public Hashtable findPageFields(User loginUser);

	/**
	 * Find Main Page Dashboard List
	 * @param loginUser
	 * @return
	 */
	public List findMainList(User loginUser);

	public Hashtable findFilterValue(User loginUser);
}