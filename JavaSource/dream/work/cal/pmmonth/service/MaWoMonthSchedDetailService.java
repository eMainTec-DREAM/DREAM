package dream.work.cal.pmmonth.service;

import common.bean.User;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;

/**
 * 월간예방일정 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaWoMonthSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoMonthSchedDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmSchedId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoMonthSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoMonthSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthSchedDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO) throws Exception;
}
