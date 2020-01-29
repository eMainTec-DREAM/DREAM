package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;
import common.mafinder.mamstr.form.LovWoPlanAcListForm;

/**
 * 작업 팝업 Service
 * @author  kim21017
 * @version $Id: LovWoPlanAcListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovWoPlanAcListService
{

    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoPlanAcListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovWoPlanAcListDTO
     * @param loginUser
     * @return
     */
    List findWoPlanAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser);

	List findWoAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user, LovWoPlanAcListForm lovWoPlanAcListForm);
	
	public String findWoListTotalCount(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user, LovWoPlanAcListForm lovWoPlanAcListForm);

}