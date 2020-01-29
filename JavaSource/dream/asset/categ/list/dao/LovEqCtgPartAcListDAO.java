package dream.asset.categ.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;

/**
 * 설비종류별부품 팝업
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovEqCtgPartAcListDAO
{
    /**
     * 검색
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgPartAcListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgPartAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEqCtgPartAcListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 * @throws Exception 
	 */
	public List findEqCtgPartAcAcList(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, Map<String, String> conditionMap) throws Exception;
	
	public String findTotalCount(LovEqCtgPartAcListDTO lovEqCtgPartAcListDTO, User user, Map<String, String> conditionMap) throws Exception;
}