package dream.work.pm.check.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;

/**
 * WorkPmCheck Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkPmCheckListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmCheckListDAO
{
    /**
     * FIND LIST
     * @param workPmCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param workPmCheckCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception;
    
}
