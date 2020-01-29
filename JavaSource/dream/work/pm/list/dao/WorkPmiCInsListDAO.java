package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;

/**
 * WorkPmiCIns Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkPmiCInsListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiCInsListDAO
{
    /**
     * FIND LIST
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    
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
     * @param workPmiCInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
    
}
