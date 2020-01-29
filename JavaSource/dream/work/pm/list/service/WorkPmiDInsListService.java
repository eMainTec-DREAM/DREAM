package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;

/**
 * WorkPmiDIns Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmiDInsListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiDInsListService
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
     * DELETE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmiDInsCommonDTO
     * @return
     */
    public String findTotalCount(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
}
