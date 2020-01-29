package dream.work.cal.pmrinsperiod.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;

/**
 * 예방작업일정(기간) - 상세 dao
 *
 * @author kim21017
 * @version $Id: WorkCalPmRInsPeriodDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface WorkCalPmRInsPeriodDetailDAO
{
    /**
     * detail input
     * @author kim21017
     * @version $Id: WorkCalPmRInsPeriodDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailDTO
     * @return
     */
    public int inputDetail(WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO, User user);

    public int[] update(List<WorkCalPmRInsPeriodDetailDTO> list, User user);
    
}