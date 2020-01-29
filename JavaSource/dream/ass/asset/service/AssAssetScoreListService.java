package dream.ass.asset.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * AssAssetScore Page - List Service
 * @author youngjoo38
 * @version $Id: AssAssetScoreListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface AssAssetScoreListService
{
    /**
     * FIND ASS ASSET SCORE LIST
     * @param assAssetScoreListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception;
    
    /**
	 * Save List
	 * @author  youngjoo38
	 * @version $Id:$
	 * @since   1.0
	 * @param gridList
	 * @param user
	 * @throws Exception 
	 */
	public void saveList(List<Map> gridList, User user) throws Exception;

    /**
     * DELETE ASS ASSET SCORE LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assAssetScoreListDTO
     * @return
     */
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception;
    

    /**
     * 평가점수복사
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assAssetCommonDTO
     * @param assAssetScoreListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception;
    
    
}
