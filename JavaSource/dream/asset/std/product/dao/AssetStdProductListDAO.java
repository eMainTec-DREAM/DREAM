package dream.asset.std.product.dao;

import java.util.List;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;

/**
 * ����ǰ�� - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdProductListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdProductCommonDTO
     * @return List
     */
    public List findProductList(AssetStdProductCommonDTO assetStdProductCommonDTO, User user) throws Exception;
    
    /**
     * ����
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param productId
     * @return
     */
    public int deleteProduct(String productId, User user) throws Exception;

    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,User user) throws Exception;
}