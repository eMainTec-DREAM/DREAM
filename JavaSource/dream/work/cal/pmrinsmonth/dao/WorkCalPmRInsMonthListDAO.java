package dream.work.cal.pmrinsmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmrinsmonth.dto.WorkCalPmRInsMonthCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmRInsMonthListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsMonthListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id, User user);

    public void SP_PM_MAKE_TO_PMI(String pminsschedId, User user) throws Exception;
    
    
    public int updateScheduleDate(String id, String scheDate, String remark, User user);
    
    public int updateResultSchedDetail(String id, String scheDate, User user);
    
    
    public String checkSched(String pmInsdSchedId, User user);
    
    public int updateDeleteTagSched(String id, User user);
    
    public String findTotalCount(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user) throws Exception;
}