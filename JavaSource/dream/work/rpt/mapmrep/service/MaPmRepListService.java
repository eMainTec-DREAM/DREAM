package dream.work.rpt.mapmrep.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;

/**
 * 예방수리내역 service
 * @author  kim21017
 * @version $Id: MaPmRepListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmRepListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPmRepListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPmRepListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findPmRepList(MaPmRepListDTO maPmRepListDTO,User user);

    public String findTotalCount(MaPmRepListDTO maPmRepListDTO,User user);
}
