package dream.asset.categ.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;

/**
 * �������������� �˾�
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface LovEqCtgSpecAcListDAO
{
    /**
     * �˻�
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgSpecAcListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgSpecAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEqCtgSpecAcListDTO
	 * @param user
	 * @param conditionMap
	 * @return
	 * @throws Exception 
	 */
	public List findEqCtgSpecAcAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, Map<String, String> conditionMap) throws Exception;
	
	public String findTotalCount(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, Map<String, String> conditionMap) throws Exception;
}