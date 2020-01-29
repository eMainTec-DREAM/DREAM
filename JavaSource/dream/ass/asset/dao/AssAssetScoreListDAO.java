package dream.ass.asset.dao;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetScoreListDTO;

/**
 * AssAssetScore Page - List DAO
 * @author youngjoo38
 * @version $Id: AssAssetScoreListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface AssAssetScoreListDAO
{
    /**
     * FIND LIST
     * @param assAssetScoreListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(AssAssetCommonDTO assAssetCommonDTO, AssAssetScoreListDTO assAssetScoreListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * DELETE LIST
     * @param assAssetScoreListDTO
     * @param user
     * @return
     * @throws Exception
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
