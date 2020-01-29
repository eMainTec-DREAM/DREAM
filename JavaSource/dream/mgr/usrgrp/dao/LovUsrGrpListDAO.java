package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.LovUsrGrpListDTO;

/**
 * ±ÇÇÑ ÆË¾÷
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovUsrGrpListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovUsrGrpListDTO
     * @param loginUser
     * @return
     */
    public List findUsrGrpList(LovUsrGrpListDTO lovUsrGrpListDTO, User loginUser);
}