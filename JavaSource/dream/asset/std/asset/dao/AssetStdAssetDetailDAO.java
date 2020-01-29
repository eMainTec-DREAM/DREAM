package dream.asset.std.asset.dao;

import common.bean.User;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;

/**
 * 회계자산 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:  $
 * @since 1.0
 */
public interface AssetStdAssetDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetNo
     * @return
     */
    public AssetStdAssetDetailDTO findDetail(User user, String assetId);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public int insertDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user);
    
    /**
     * detail update
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public int updateDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user);
    
    /**
     * valid assetNo
     * @author ghlee
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     */
    public String validAssetNo(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user);
}