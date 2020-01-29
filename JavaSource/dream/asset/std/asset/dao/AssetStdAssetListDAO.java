package dream.asset.std.asset.dao;

import java.util.List;

import common.bean.User;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;

/**
 * ȸ���ڻ� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdAssetListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetCommonDTO
     * @return List
     */
    public List findAssetList(AssetStdAssetCommonDTO assetStdAssetCommonDTO, User user) throws Exception;
    
    /**
     * ����
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param assetId
     * @return
     */
    public int deleteAsset(String assetId, User user) throws Exception;

    public String findTotalCount(AssetStdAssetCommonDTO assetStdAssetCommonDTO,User user) throws Exception;
}