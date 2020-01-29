package dream.ass.asset.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;

/**
 * �򰡰������ LOV
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface AssAssetScoreCopyLovDAO
{
	/**
	 * �򰡰������ LOV �˻�
	 * @param assAssetScoreCopyLovDTO
	 * @param user
	 * @param columnMap
	 * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findAssList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
    /**
     * �򰡰������ LOV COUNT
     * @param assAssetScoreCopyLovDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
    
    /**
     * �򰡰������ �˻�
     * @param assAssetScoreCopyLovDTO
     * @param user
     * @return
     */
    public List findAssScoreList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user);


}