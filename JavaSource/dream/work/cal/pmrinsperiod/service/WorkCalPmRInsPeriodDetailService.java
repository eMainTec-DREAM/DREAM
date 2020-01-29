package dream.work.cal.pmrinsperiod.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간) - 상세 service
 *
 * @author kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkCalPmRInsPeriodDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param pmSchedId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkCalPmRInsPeriodDetailDTO findDetail(String pmSchedId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user) throws Exception;
    /**
     * input update
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailDTO
     * @return
     * @throws Exception
     */
    public int inputDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user) throws Exception;
    
    public int[] updateList(List<WorkCalPmRInsPeriodDetailDTO> list, User user) throws Exception;
}
