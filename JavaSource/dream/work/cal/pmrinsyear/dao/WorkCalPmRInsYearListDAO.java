package dream.work.cal.pmrinsyear.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;

/**
 * 연간작업일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmRInsYearListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsYearCommonDTO
     * @return List
     */
    public List findSchedMonthlyList(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    public List findYearReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    public List findYearInsReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsYearListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id,User user);
    
    public int updateScheduleDate(String id, String scheDate, String remark, User user);
    
    public int updateResultSchedDetail(String id, String scheDate, User user);

    public String findTotalCount(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user);
    
    public String checkSched(String pmInsSchedId, User user);
    
    public int updateDeleteTagSched(String id, User user);
 
    public void SP_PM_MAKE_TO_PMI(String pminsschedId, User user) throws Exception;
}