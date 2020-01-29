package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoListDTO;
import common.mafinder.mamstr.form.LovWoListForm;

/**
 * 작업 팝업 Service
 * @author  kim21017
 * @version $Id: LovWoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovWoListService
{

    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovWoListDTO
     * @param loginUser
     * @return
     */
    List findWoList(LovWoListDTO lovWoListDTO, User loginUser);

	List findWoAcList(LovWoListDTO lovWoListDTO, User user, LovWoListForm lovWoListForm);
	
	public String findWoListTotalCount(LovWoListDTO lovWoListDTO, User user, LovWoListForm lovWoListForm);

}