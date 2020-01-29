package dream.mgr.contract.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.contract.dto.LovMgrContractListDTO;

/**
 * 단가계약 LOV - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface LovMgrContractListDAO
{
	/**
	 * FIND LIST
	 * @param lovMgrContractListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findList(LovMgrContractListDTO lovMgrContractListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    

	/**
	 * FIND LIST COUNT
	 * @param lovMgrContractListDTO
	 * @param user
     * @param columnMap
     * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(LovMgrContractListDTO lovMgrContractListDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
}