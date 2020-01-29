package dream.work.cal.pmcinsmonth.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.work.cal.pmcinsmonth.dto.WorkCalPmCInsMonthCommonDTO;

/**
 * 월간예방일정 - 목록 service
 * @author  kim21017
 * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmCInsMonthListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmCInsMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findSchedList(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteSched(String[] deleteRows, User user) throws Exception;

    /**
     * 월단위 확정
     * @author kim21017
     * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmCInsMonthCommonDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO monthlyScheduled(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user) throws Exception;

    /**
     * 일단위 확정
     * @author kim21017
     * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param fixRows
     * @param workCalPmCInsMonthCommonDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user) throws Exception;

    /**
     *  find schedule
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmCInsMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public String findSchedule(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user) throws Exception;

    /**
     * 스케줄 조정
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     *
     * @param gridList
     * @param user
     */
    public void updateSchedule(List gridList, User user);
    
    public String findTotalCount(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user);
}
