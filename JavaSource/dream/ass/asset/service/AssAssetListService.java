package dream.ass.asset.service;

import java.util.List;
import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;

/**
 * AssAsset Page - List Service
 * @author youngjoo38
 * @version $Id: AssAssetListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface AssAssetListService
{
    /**
     * FIND ASS ASSET LIST
     * @param assAssetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    /**
     * DELETE ASS ASSET LIST
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    public int createInvtList(String[] selectRows, AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assAssetCommonDTO
     * @return
     */
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
}
