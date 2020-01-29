package dream.asset.std.product.service;

import java.util.List;

import common.bean.User;
import dream.asset.std.product.dto.AssetStdProductCommonDTO;

/**
 * ����ǰ�� - ��� service
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdProductListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id: $
     * @param assetStdProductCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findProductList(AssetStdProductCommonDTO assetStdProductCommonDTO, User user) throws Exception;    
   
    /**
     * delete List
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(AssetStdProductCommonDTO assetStdProductCommonDTO,User user) throws Exception;
}
