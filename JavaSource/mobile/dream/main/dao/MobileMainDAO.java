package mobile.dream.main.dao;

import java.util.List;

import common.bean.User;
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
 */
public interface MobileMainDAO
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
    public String [][] findMenus(String pMenuId, String userGroup, User loginUser);
    
    public List findMenusList(String pMenuId, String userGroup, User loginUser);

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
    public String[][] findAdminMenu(String userGroup, User loginUser);

    /**
     * 금일점검
     * @author  wondo
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findCheckList(User loginUser);
    /**
     * 사용자 그룹에 권한이 없는 Menu를 조회한다.
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     */
    public List findNoMenuList(String userGroup);
    
    public List findNoticeList(User loginUser);

    public List findBoardList(User loginUser);
    
    public AppCntDTO getAppCntInfo(User loginUser);
    /**
     * 로그인유저의 작업설계, 작업수행
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser);

    /**
     * 전체 작업 Notice
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public WorkNoticeCntDTO getWorkNoticeCntInfo();

    /**
     * Rig 위치정보 조회
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public String getVsPostion();
    
    /**
     * Inspection Chart
     * @author  pochul2423
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public InspChartDTO getInspChart();
    
    /**
     * 자재 입고/출고 chart
     * @author  pochul2423
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     * @throws Exception 
     */
    public PartChartDTO getPartChart() throws Exception;

    /**
     * Find USer Menu
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
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
	 * Find Menu List by Usr Grp
	 * @param userGroup
	 * @return
	 */
	public List findMenuList(String userGroup, User loginUser) ;

    public List findPageFields(User loginUser);

	public List findMenusJsonList(String pMenuId, String userGroup, User loginUser);

	/**
	 * Find Main Dashboard List
	 * @param loginUser
	 * @return
	 */
	public List findMainList(User loginUser);
}