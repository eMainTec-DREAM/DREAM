package dream.asset.std.asset.service;

import java.util.List;

import common.bean.User;
import dream.asset.std.asset.dto.AssetStdAssetCommonDTO;

/**
 * ȸ���ڻ� - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdAssetListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id: $
     * @param assetStdAssetCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findAssetList(AssetStdAssetCommonDTO assetStdAssetCommonDTO, User user) throws Exception;    
   
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

    public String findTotalCount(AssetStdAssetCommonDTO assetStdAssetCommonDTO,User user) throws Exception;
}
