package dream.work.cal.pmperiod.service;

import common.bean.User;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간) - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaWoSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoSchedDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmSchedId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoSchedDetailDTO maWoSchedDetailDTO, User user) throws Exception;
    /**
     * input update
     * @author kim21017
     * @version $Id: MaWoSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoSchedDetailDTO
     * @return
     * @throws Exception
     */
    public int inputDetail(MaWoSchedDetailDTO maWoSchedDetailDTO) throws Exception;
}
