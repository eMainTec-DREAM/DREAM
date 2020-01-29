package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.LovReqPartsListDTO;

/**
 * �����˾� Service
 * @author  kim21017
 * @version $Id: LovReqPartsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovReqPartsListService
{

    /**
     * ����˻�
     * @author  kim21017
     * @version $Id: LovReqPartsListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovReqPartsListDTO
     * @param loginUser
     * @return
     */
    List findPartsList(LovReqPartsListDTO lovReqPartsListDTO, User loginUser);
}