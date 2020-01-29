package dream.work.cal.pmmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;

/**
 * ������������ - ��� dao
 * @author  kim21017
 * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoMonthSchedListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @return List
     */
    public List findSchedList(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user);
    
    /**
     * W/O ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWorkOrder(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * �۾��� ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoCraft(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * �۾����� ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoPart(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * ���˳��� ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int createWoPoint(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * �۾� ���� ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoMonthSchedCommonDTO
     * @param id
     * @return
     */
    public int createWoEquip(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * �۾�������ȹ���� ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmschedList
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int updatePmSchedStatus(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    /**
     * �ֱ⿡ ���� ���� ��ȹ ����
     * @author kim21017
     * @version $Id: MaWoMonthSchedListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmschedList
     * @param maWoMonthSchedCommonDTO
     * @param type
     * @param id
     * @return
     */
    public int updateLastSchDate(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, String id);
    
    public void SP_PM_MAKE_WO_BYONE(String compNo, String userNo, String pmschedId) throws Exception;
    
    public String findTotalCount(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user);

}