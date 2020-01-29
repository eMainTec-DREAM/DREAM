package common.mafinder.mamstr.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dto.LovPtIssListDTO;

/**
 * 출고부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface LovPtIssListDAO
{
	public List findPtIssAcList(LovPtIssListDTO lovPtIssListDTO, User user, Map<String, String> conditionMap);
	
	public String findPtIssListTotalCount(LovPtIssListDTO lovPtIssListDTO, User user, Map<String, String> conditionMap);
}