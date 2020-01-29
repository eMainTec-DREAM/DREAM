package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoPartsListDTO;
import common.mafinder.mamstr.form.LovWoPartsListForm;

/**
 * 작업부품 팝업 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWoPartsListService
{
	List findWoPartsAcList(LovWoPartsListDTO lovWoPartsListDTO, User user, LovWoPartsListForm lovWoPartsListForm);
	
	public String findWoPartsListTotalCount(LovWoPartsListDTO lovWoPartsListDTO, User user, LovWoPartsListForm lovWoPartsListForm);

}