package dream.work.rpt.maeqbm.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;

/**
 * 설비고장내역 service
 * @author  kim21017
 * @version $Id: MaEqBmListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqBmListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqBmListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqBmListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqBmList(MaEqBmListDTO maEqBmListDTO, User user);

    public String findTotalCount(MaEqBmListDTO maEqBmListDTO, User user);
}
