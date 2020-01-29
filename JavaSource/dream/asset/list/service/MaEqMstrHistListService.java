package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * 설비변경이력 - 목록 service
 * @author  kim21017
 * @version $Id: MaEqMstrHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrHistListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaEqMstrHistListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maEqMstrHistListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findEqMstrHistList(MaEqMstrHistListDTO maEqMstrHistListDTO, User user);

}
