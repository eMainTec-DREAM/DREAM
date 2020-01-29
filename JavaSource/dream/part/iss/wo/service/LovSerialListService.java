package dream.part.iss.wo.service;

import java.util.List;

import common.bean.User;
import dream.part.iss.wo.dto.LovSerialListDTO;
import dream.part.iss.wo.form.LovSerialListForm;

/**
 * 질의 팝업 Service
 * @author  hyosung
 * @version $Id: LovSerialListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface LovSerialListService
{

    /**
     * 질의검색
     * @author  hyosung
     * @version $Id: LovSerialListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param lovSerialListDTO
     * @param loginUser
     * @param lovSerialListForm
     * @return
     */
    List findSerialList(LovSerialListDTO lovSerialListDTO, User loginUser, LovSerialListForm lovSerialListForm);
}