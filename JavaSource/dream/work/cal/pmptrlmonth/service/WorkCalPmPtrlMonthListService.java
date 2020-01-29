package dream.work.cal.pmptrlmonth.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;

/**
 * 월간예방일정 - 목록 service
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListService.java,v 1.0 2017/09/24 09:12:40 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkCalPmPtrlMonthListService
{
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListService.java,v 1.0 2017/09/24 09:12:40 youngjoo38 Exp $
     * @param workCalPmPtrlMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findSchedList(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);
    /**
     * delete
     * @author youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListService.java,v 1.0 2017/09/24 09:12:40 youngjoo38 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteSched(String[] deleteRows, User user) throws Exception;

    /**
     *  find schedule
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListService.java,v 1.0 2017/09/24 09:12:40 youngjoo38 Exp $
     * @param workCalPmPtrlMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public String findSchedule(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user) throws Exception;
    
    public String findTotalCount(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);
}
