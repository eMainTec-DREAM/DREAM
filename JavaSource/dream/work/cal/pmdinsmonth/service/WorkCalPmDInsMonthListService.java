package dream.work.cal.pmdinsmonth.service;

import java.util.List;
import java.util.Map;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;

/**
 * 월간예방일정 - 목록 service
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmDInsMonthListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmDInsMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findSchedList(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user);


    /**
     *  find schedule
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmDInsMonthCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public String findSchedule(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user) throws Exception;
    
    
    
    public int deleteSched(String[] deleteRows, User user) throws Exception;
    
    /**
     * 스케줄 조정
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     *
     * @param gridList
     * @param user
     */
    public void updateSchedule(List<Map> gridList, User user) throws Exception;

    /**
     * 일단위 확정
     * @author syyang
     * @version $Id: $
     * @since   1.0
     *
     * @param fixRows
     * @param workCalPmDInsMonthCommonDTO
     * @return
     * @throws Exception
     */
    public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user) throws Exception;

    public String findTotalCount(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user);
}
