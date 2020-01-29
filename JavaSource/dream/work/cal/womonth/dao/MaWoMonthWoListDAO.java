package dream.work.cal.womonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �����۾����� - ��� dao
 * @author  kim21017
 * @version $Id: MaWoMonthWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoMonthWoListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthWoCommonDTO
     * @return List
     */
    public List findSchedList(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO,  MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
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
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);

    /**
     * ����Ʈ �۾���
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);

    /**
     * ����Ʈ ���Ժ�ǰ
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);
    
    /**
     * ����Ʈ �˻��׸�
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);
    
    /**
     * ����Ʈ �۾������׸�
     * @author kim21017
     * @version $Id: MaWoMonthWoListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);
    
    public String findTotalCount(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO,  MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception;

}