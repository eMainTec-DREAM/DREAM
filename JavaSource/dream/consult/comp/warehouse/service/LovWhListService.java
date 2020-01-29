package dream.consult.comp.warehouse.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dto.LovWhListDTO;
import dream.consult.comp.warehouse.form.LovWhListForm;

/**
 * 사용창고 팝업 Service
 * @author  kim21017
 * @version $Id: LovWhListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovWhListService
{

    /**
     * 사용창고 검색
     * @author  kim21017
     * @version $Id: LovWhListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovWhListDTO
     * @param loginUser
     * @return
     */
    List findWhList(LovWhListDTO lovWhListDTO, User loginUser);

    List findWhACList(LovWhListDTO lovWhListDTO, User user,
            LovWhListForm lovWhListForm);
    
    public String findTotalCount(LovWhListDTO lovWhListDTO, User user, LovWhListForm lovWhListForm);
}