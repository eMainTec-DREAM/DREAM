package dream.work.cal.pmyear.service;

import common.bean.User;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;

/**
 * 연간작업일정 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaWoYearSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaWoYearSchedDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoYearSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmSchedId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoYearSchedDetailDTO findDetail(String pmSchedId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoYearSchedDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoYearSchedDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoYearSchedDetailDTO maWoYearSchedDetailDTO) throws Exception;
}
