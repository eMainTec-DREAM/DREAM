package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;

/**
 * WorkPmiCIns Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmiCInsListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiCInsListService
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
     * @param workPmiCInsCommonDTO
     * @return
     */
    public String findTotalCount(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception;
}
