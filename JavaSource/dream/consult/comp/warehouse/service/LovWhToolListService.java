package dream.consult.comp.warehouse.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;

/**
 * ���â�� �˾� Service
 * @author  kim21017
 * @version $Id: LovWhToolListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovWhToolListService
{

    /**
     * ���â�� �˻�
     * @author  kim21017
     * @version $Id: LovWhToolListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovWhToolListDTO
     * @param loginUser
     * @return
     */
    List findWhList(LovWhToolListDTO lovWhToolListDTO, User loginUser);
}