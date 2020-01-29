package mobile.dream.main.dao;

import java.util.List;

import common.bean.User;
import dream.main.dto.AppCntDTO;
import dream.main.dto.InspChartDTO;
import dream.main.dto.MyWorkCntDTO;
import dream.main.dto.PartChartDTO;
import dream.main.dto.WorkNoticeCntDTO;

/**
 * Main �� ������ contents �� ��ȸ DAO 
 * @author  javaworker
 * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
 * @since   1.0
 */
public interface MobileMainDAO
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
    public String [][] findMenus(String pMenuId, String userGroup, User loginUser);
    
    public List findMenusList(String pMenuId, String userGroup, User loginUser);

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
    public String[][] findAdminMenu(String userGroup, User loginUser);

    /**
     * ��������
     * @author  wondo
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findCheckList(User loginUser);
    /**
     * ����� �׷쿡 ������ ���� Menu�� ��ȸ�Ѵ�.
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
     * �α��������� �۾�����, �۾�����
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser);

    /**
     * ��ü �۾� Notice
     * @author  javaworker
     * @version $Id: MainDAO.java,v 1.25 2015/01/09 00:16:43 pochul2423 Exp $
     * @since   1.0
     * 
     * @return
     */
    public WorkNoticeCntDTO getWorkNoticeCntInfo();

    /**
     * Rig ��ġ���� ��ȸ
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
     * ���� �԰�/��� chart
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