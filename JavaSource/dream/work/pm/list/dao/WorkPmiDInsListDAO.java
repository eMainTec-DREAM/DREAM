package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;

/**
 * WorkPmiDIns Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkPmiDInsListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkPmiDInsListDAO
{
    /**
     * FIND LIST
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
    
    /**
     * FIND TOTAL LIST
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;

    public int[] updateDeleteTag(List<WorkPmiDInsDetailDTO> list, User user) throws Exception;
    
}
