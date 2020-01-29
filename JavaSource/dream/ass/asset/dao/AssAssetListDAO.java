package dream.ass.asset.dao;

import java.util.List;
import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;

/**
 * AssAsset Page - List DAO
 * @author youngjoo38
 * @version $Id: AssAssetListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface AssAssetListDAO
{
    /**
     * FIND LIST
     * @param assAssetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    
    /**
     * CHECK DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public String chkDelList(String id, User user) throws Exception;

    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * FIND TOTAL LIST
     * @param assAssetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception;
    
    public String findDefaultPrctpId(User user) throws Exception;
    
    public String findEquipDesc(String id, User user) throws Exception;
}
