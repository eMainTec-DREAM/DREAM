package dream.part.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgPartAcListDTO;
import dream.part.list.dto.LovPartListBinListDTO;

/**
 * �����׸� �˾�
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 */
public interface LovPartListBinListDAO
{
    /**
     * �˻�
     * @author  euna0207
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartListBinListDTO
     * @param loginUser
     * @return
     */
 
	public List findEqCtgPartAcList(LovPartListBinListDTO lovPartListBinListDTO, User user, Map<String, String> conditionMap) throws Exception;
	public String findTotalCount(LovPartListBinListDTO lovPartListBinListDTO, User user, Map<String, String> conditionMap) throws Exception;
}