package dream.asset.std.product.service;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductDetailDTO;

/**
 * 积魂前格 - 惑技 service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AssetStdProductDetailService
{    
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetNo
     * @return
     * @throws Exception
     */
    public AssetStdProductDetailDTO findDetail(User user, String assetNo)throws Exception;
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception;
    
    /**
     * valid productNo
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdProductDetailDTO
     * @return
     * @throws Exception
     */
    public String validProductNo(AssetStdProductDetailDTO assetStdProductDetailDTO, User user) throws Exception;
}
