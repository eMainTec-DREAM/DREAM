package dream.work.cal.pmrinsperiod.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;

/**
 * 예방작업일정(기간) - 목록 service
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmRInsPeriodListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsPeriodListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param workCalPmRInsPeriodCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findSchedList(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
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
    
    public String findTotalCount(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user) throws Exception;

}
