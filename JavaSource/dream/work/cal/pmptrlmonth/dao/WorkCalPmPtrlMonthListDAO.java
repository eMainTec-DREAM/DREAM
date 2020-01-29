package dream.work.cal.pmptrlmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2017/09/24 09:14:12 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkCalPmPtrlMonthListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2017/09/24 09:14:12 youngjoo38 Exp $
     * @since   1.0
     *
     * @param workCalPmPtrlMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);

    /**
     * delete
     * @author youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2017/09/24 08:25:47 youngjoo38 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id, User user);
    
    public String findTotalCount(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user);
}