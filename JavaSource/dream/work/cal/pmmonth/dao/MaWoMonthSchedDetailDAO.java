package dream.work.cal.pmmonth.dao;

import common.bean.User;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;

/**
 * 월간예방일정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoMonthSchedDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaWoMonthSchedDetailDTO findDetail(String pmSchedId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedDetailDTO
     * @return
     */
    public int updateDetail(MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO);
}