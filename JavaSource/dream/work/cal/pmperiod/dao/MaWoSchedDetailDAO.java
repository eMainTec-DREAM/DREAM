package dream.work.cal.pmperiod.dao;

import common.bean.User;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간) - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoSchedDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaWoSchedDetailDTO findDetail(String pmSchedId, User user);
    
    /**
     * detail input
     * @author kim21017
     * @version $Id: MaWoSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailDTO
     * @return
     */
    public int inputDetail(MaWoSchedDetailDTO maWoSchedDetailDTO);
}