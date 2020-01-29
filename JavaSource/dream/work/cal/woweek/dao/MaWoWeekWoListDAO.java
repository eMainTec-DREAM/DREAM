package dream.work.cal.woweek.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;

/**
 * �ְ��۾����� - ��� dao
 * @author  kim21017
 * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoWeekWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoWeekWoCommonDTO
     * @return List
     */
    public List findSchedList(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user);
    
    public String findTotalCount(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user);
    
    /**
     * find Schedule
     * @author  kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoWeekWoCommonDTO
     * @return List
     */
    public String[][] findSchedule(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user);
    
    public String[][] findWoType(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int updateDeleteTagSched(String id, User user);
    public int create4wp(String id, User user);
    
    /**
     * ����Ʈ �ٵ�
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO );

    /**
     * ����Ʈ �۾���
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO );

    /**
     * ����Ʈ ���Ժ�ǰ
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO );
    
    /**
     * ����Ʈ �˻��׸�
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO );
    
    /**
     * ����Ʈ �۾������׸�
     * @author kim21017
     * @version $Id: MaWoWeekWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoWeekWoCommonDTO maWoWeekWoCommonDTO );
}