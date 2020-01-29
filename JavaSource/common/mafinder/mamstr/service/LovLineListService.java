package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovLineListDTO;

/**
 * ���������� Service
 * @author  kim21017
 * @version $Id: LovLineListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovLineListService
{

    /**
     * ���������� �˻�
     * @author  kim21017
     * @version $Id: LovLineListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovLineListDTO
     * @param loginUser
     * @return
     */
    List findLineList(LovLineListDTO lovLineListDTO, User loginUser);
}