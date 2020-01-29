package dream.work.pm.check.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;

/**
 * WorkPmCheck Page - List Service
 * @author youngjoo38
 * @version $Id: WorkPmCheckListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmCheckListService
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
     * @param workPmCheckCommonDTO
     * @return
     */
    public String findTotalCount(WorkPmCheckCommonDTO workPmCheckCommonDTO, User user) throws Exception;
}
