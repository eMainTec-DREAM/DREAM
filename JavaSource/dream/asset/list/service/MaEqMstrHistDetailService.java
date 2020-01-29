package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * 설비변경이력- 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqMstrHistDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrHistDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrHistDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrHistListDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrHistDetailDTO findDetail(MaEqMstrHistListDTO maEqMstrHistListDTO,User user)throws Exception;
}
