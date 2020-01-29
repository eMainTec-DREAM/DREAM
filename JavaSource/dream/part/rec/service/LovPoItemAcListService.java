package dream.part.rec.service;

import java.util.List;

import common.bean.User;
import dream.part.rec.dto.LovPoItemAcListDTO;

/**
 * 발주 선택 Lov Service
 * @author  nhkim8548
 * @version $Id: LovPoItemAcListService.java,v 1.0 2018/09/13 14:57:40 nhkim8548 Exp $
 * @since   1.0
 */
public interface LovPoItemAcListService
{
    /**
     * @author  nhkim8548
     * @version $Id: LovPoItemAcListService.java,v 1.0 2018/09/13 14:58:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param user
     * @param LovPoItemAcListDTO
     * @return
     */
    List findList(LovPoItemAcListDTO lovPoItemAcListDTO, User user);
}