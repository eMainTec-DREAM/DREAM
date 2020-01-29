package dream.work.cal.pmdinsmonth.service;

import java.util.List;

import common.bean.User;

import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * 월간예방일정 - 상세 service
 *
 * @author kim21017
 * @version $Id: WorkCalPmDInsMonthDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface WorkCalPmDInsMonthDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkCalPmDInsMonthDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param pmSchedId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkCalPmDInsMonthDetailDTO findDetail(String pmInsDSchedId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkCalPmDInsMonthDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmDInsMonthDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO, User user) throws Exception;
    public int[] updateList(List<WorkCalPmDInsMonthDetailDTO> list, User user) throws Exception;
}
