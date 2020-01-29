package dream.work.cal.pmrinsperiod.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간) - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface WorkCalPmRInsPeriodListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user);

    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public boolean canDeleteWorkOrder(String id, String compNo);

    public String findTotalCount(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO, User user) throws Exception;

    public int[] updateDeleteTag(List<WorkCalPmRInsPeriodDetailDTO> list, User user);
    
}