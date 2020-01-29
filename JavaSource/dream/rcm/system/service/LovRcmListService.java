package dream.rcm.system.service;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.LovRcmListDTO;

/**
 * �ڻ��˾� Service
 * @author  kim21017
 * @version $Id: LovRcmListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovRcmListService
{

    /**
     * �ڻ�˻�
     * @author  kim21017
     * @version $Id: LovRcmListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovRcmListDTO
     * @param loginUser
     * @return
     */
    List findRcmList(LovRcmListDTO lovRcmListDTO, User loginUser);
}