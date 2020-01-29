package dream.work.cal.pmyear.dao;

import common.bean.User;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;

/**
 * 연간작업일정 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaWoYearSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoYearSchedDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoYearSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaWoYearSchedDetailDTO findDetail(String pmSchedId, User user);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoYearSchedDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedDetailDTO
     * @return
     */
    public int updateDetail(MaWoYearSchedDetailDTO maWoYearSchedDetailDTO);
}