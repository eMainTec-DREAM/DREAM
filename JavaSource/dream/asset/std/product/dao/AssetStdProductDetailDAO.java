package dream.asset.std.product.dao;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;

/**
 * 积魂前格 - 惑技 dao
 * 
 * @author ghlee
 * @version $Id:  $
 * @since 1.0
 */
public interface AssetStdProductDetailDAO
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
    public AssetStdProductDetailDTO findDetail(User user, String assetId);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     */
    public int insertDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user);
    
    /**
     * detail update
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     */
    public int updateDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user);
    
    /**
     * valid productNo
     * @author ghlee
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     */
    public String validProductNo(AssetStdProductDetailDTO assetStdProductDetailDTO, User user);
}