package dream.work.list.service;

import common.bean.User;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * 순회점검 작업결과 상세
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailService.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkListPtrlResultMstrDetailService
{    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailService.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param workListPtrlResultCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO,User user) throws Exception;
	
    /**
     * Find List
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     */
    public WorkListPtrlResultMstrDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);
    
    /**
     * PTRL COMPLETED
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int ptrlCompletedDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception;
    /**
     * GET PTRL COMDATE
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getPtrlComDate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception;
    
    /**
     * GET PTRL COMTIME
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getPtrlComTime(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception;

    public String checkDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception;
    
}
