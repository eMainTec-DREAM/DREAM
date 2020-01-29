package dream.work.list.dao;

import common.bean.User;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * 순회점검 작업결과 상세 dao
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2015/12/04 08:10:27 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkListPtrlResultMstrDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultCommonDTO
     * @param user
     * @return
     */
    public WorkListPtrlResultMstrDetailDTO findDetail(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO, WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id: WorkListPtrlResultMstrDetailDAO.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param workListPtrlResultCommonDTO
     * @return
     */
    public int updateDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user);
    
    /**
     * PTRLCOMPLETED DETAIL
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int ptrlCompletedDetail(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user) throws Exception;

    /**
     * completeSched
     * @author syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     */
    public int completeSched(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user);
   
    /**
     *  SP_PM_UPDATE_LASTCPLT_DATE 프로시져 호출 
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param workListPtrlResultMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int executePmUpdate(WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO, User user);
    
    /**
     * PTRL COMPLETED
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