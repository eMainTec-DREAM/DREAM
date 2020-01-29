package dream.part.rec.dao;

import java.util.List;

import common.bean.User;
import dream.part.rec.dto.LovPoItemAcListDTO;

/**
 * 발주 선택 Lov DAO
 * @author  nhkim8548
 * @version $Id: LovPoItemAcListDAO.java,v 1.0 2018/09/13 14:58:40 nhkim8548 Exp $
 * @since   1.0
 */
public interface LovPoItemAcListDAO
{
    /**
     * Find List
     * @author  nhkim8548
     * @version $Id: LovPoItemAcListDAO.java,v 1.0 2018/09/13 14:59:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param user
     * @param LovPoItemAcListDTO
     * @return
     */
    public List findList(LovPoItemAcListDTO lovPoItemAcListDTO, User loginUser);
}