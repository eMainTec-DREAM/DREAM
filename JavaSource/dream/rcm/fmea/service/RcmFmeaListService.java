package dream.rcm.fmea.service;

import java.util.List;

import common.bean.User;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id: RcmFmeaListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFmeaListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFmeaListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param rcmFmeaCommonDTO
     * @param user 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user);

	public String findTotalCount(RcmFmeaCommonDTO rcmFmeaCommonDTO, User user);    

}
