package dream.work.rpt.mapmpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;

/**
 * 예방점검내역 service
 * @author  kim21017
 * @version $Id: MaPmPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPmPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPmPointListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPmPointList(MaPmPointListDTO maPmPointListDTO, User user);
    
    public String findTotalCount(MaPmPointListDTO maPmPointListDTO, User user);
    
}
