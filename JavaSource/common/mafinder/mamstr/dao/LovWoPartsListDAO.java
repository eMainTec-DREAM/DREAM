package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovWoPartsListDTO;

/**
 * 작업부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovWoPartsListDAO
{
	public List findWoPartsAcList(LovWoPartsListDTO lovWoPartsListDTO, User user, Map<String, String> conditionMap);
	
	public String findWoPartsListTotalCount(LovWoPartsListDTO lovWoPartsListDTO, User user, Map<String, String> conditionMap);
}