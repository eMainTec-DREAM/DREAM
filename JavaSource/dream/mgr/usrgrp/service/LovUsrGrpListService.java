package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;

/**
 * ±ÇÇÑ Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovUsrGrpListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovUsrGrpListDTO
     * @param loginUser
     * @return
     */
    List findUsrGrpList(LovUsrGrpListDTO lovUsrGrpListDTO, User loginUser);
}