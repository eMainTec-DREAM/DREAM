package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * WorkListPoint Page - List DAO
 * @author youngjoo38
 * @version $Id: WorkListPointListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface WorkListPointListDAO
{
    /**
     * FIND LIST
     * @param workListPointListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int[] deleteList(List<WorkListPointDetailDTO> list, User user) throws Exception;
    /**
     * DELETE LIST
     * @param workListPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkListPointListDTO workListPointListDTO, User user) throws Exception;
    
}
