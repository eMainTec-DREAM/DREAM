package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 순회점검 작업결과 목록 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkListPtrlResultListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param workListPtrlResultListDTO
     * @param loginUser
     * @return List
     */
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO,User loginUser) throws Exception;
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultListDAO.java,v 1.0 20155/12/02 08:25:47 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo);
    
    /**
     * FIND TOTAL LIST
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User user) throws Exception;
   
}