package dream.work.list.service;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 순회점검 작업결과 목록
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListService.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkListPtrlResultListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListService.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param workListPtrlResultListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User loginUser)throws Exception;
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListService.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, String compNo) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, User user) throws Exception;
    
}
