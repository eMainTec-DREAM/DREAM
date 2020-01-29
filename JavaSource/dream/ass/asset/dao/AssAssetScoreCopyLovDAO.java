package dream.ass.asset.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.asset.dto.AssAssetScoreCopyLovDTO;

/**
 * 평가결과복사 LOV
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 */
public interface AssAssetScoreCopyLovDAO
{
	/**
	 * 평가결과복사 LOV 검색
	 * @param assAssetScoreCopyLovDTO
	 * @param user
	 * @param columnMap
	 * @param conditionMap
	 * @return
	 * @throws Exception
	 */
    public List findAssList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
    /**
     * 평가결과복사 LOV COUNT
     * @param assAssetScoreCopyLovDTO
     * @param user
     * @param columnMap
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user, Map<String, String> columnMap, Map<String, String> conditionMap) throws Exception;
    
    
    /**
     * 평가결과점수 검색
     * @param assAssetScoreCopyLovDTO
     * @param user
     * @return
     */
    public List findAssScoreList(AssAssetScoreCopyLovDTO assAssetScoreCopyLovDTO, User user);


}