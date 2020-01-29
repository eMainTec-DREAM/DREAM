package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;

/**
 * WorkListCinsPlanMstr Page - List Service
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkListCinsPlanMstrListService
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
     * @param workListCinsPlanMstrCommonDTO
     * @return
     */
    public String findTotalCount(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception;
}
