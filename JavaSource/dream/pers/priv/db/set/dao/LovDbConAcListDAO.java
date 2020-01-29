package dream.pers.priv.db.set.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;

/**
 * Contents Lov DAO
 * @author  nhkim8548
 * @version $Id: LovDbConAcListDAO.java,v 1.0 2018/08/06 09:29:40 nhkim8548 Exp $
 * @since   1.0
 */
public interface LovDbConAcListDAO
{
    /**
     * Find List
     * @author  nhkim8548
     * @version $Id: LovDbConAcListDAO.java,v 1.0 2018/08/06 09:29:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param user
     * @param LovDbConAcListDTO
     * @return
     */
    public List findList(LovDbConAcListDTO lovDbConAcListDTO, User loginUser);
}