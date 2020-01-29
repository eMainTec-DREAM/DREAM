package dream.work.cal.pmdinsmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmDInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmDInsMonthListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmDInsMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user);
    
    public void SP_PM_MAKE_TO_PMI(String pmInsdSchedId, User user) throws Exception;

    public int[] updateDeleteTag(List<WorkCalPmDInsMonthDetailDTO> list, User user) throws Exception;  

    public String findTotalCount(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user);
}