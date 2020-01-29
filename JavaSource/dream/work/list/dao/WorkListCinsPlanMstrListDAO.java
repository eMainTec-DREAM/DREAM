package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;

/**
 * WorkListCinsPlanMstr Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkListCinsPlanMstrListDAO
{
    /**
     * FIND LIST
     * @param workListCinsPlanMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteSched(String id, User user) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param workListCinsPlanMstrCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception;
    
    public String checkSched(String pmInsdSchedId, User user);
    
    public int updateDeleteTagSched(String pmInsdSchedId, User user);
    
}
