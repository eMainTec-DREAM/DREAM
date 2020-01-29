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
     * ��� User�� �׷���ѿ� �ش��ϴ� 
     * Menu�� ArrayList�� �����Ѵ�.
     * �ϳ��� sub menu�� �ϳ��� HashTable�� �����ȴ�.
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup : �α� ���� ����
     * @return ArrayList 0 Main Menu
     *                   1 ���� sub Menu
     * @throws Exception
     */
    public ArrayList findMenuList(String userGroup, User loginUser);
    
    public Map findMenuJsonList(String userGroup, User loginUser);

    /**
     * ���� �׷� 
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
     * ���� ���� ����
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
     * ����� �׷쿡 ������ ���� Menu�� ��ȸ�Ѵ�. 
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param userGroup
     * @return
     */
    public List findNoMenuList(String userGroup);

    /**
     * ��������
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
     public List findNoticeList(User loginUser);

     /**
     * �Խ���
     * @author  pochul2423
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
     public List findBoardList(User loginUser);
      
    /**
     * Approval ���� �Ǽ�
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public AppCntDTO getAppCntInfo(User loginUser);

    /**
     * My Work ���� �Ǽ�
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public MyWorkCntDTO getMyWorkCntInfo(User loginUser);

    /**
     * Work Notice ���� �Ǽ�
     * @author  javaworker
     * @version $Id: MainService.java,v 1.6 2014/02/17 06:06:56 javaworker Exp $
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public WorkNoticeCntDTO getWorkNoticeCntInfo(User loginUser);

    /**
     * Rig ��ġ���� ��ȸ
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
     * Field ���� ��ȸ
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