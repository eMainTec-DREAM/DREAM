package dream.pers.priv.db.set.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;

/**
 * Contents Lov Service
 * @author  nhkim8548
 * @version $Id: LovDbConAcListService.java,v 1.0 2018/08/06 09:27:40 nhkim8548 Exp $
 * @since   1.0
 */
public interface LovDbConAcListService
{
    /**
     * @author  nhkim8548
     * @version $Id: LovDbConAcListService.java,v 1.0 2018/08/06 09:27:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param user
     * @param LovDbConAcListDTO
     * @return
     */
    List findList(LovDbConAcListDTO lovDbConAcListDTO, User user);
}