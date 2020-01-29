package dream.asset.std.asset.service;

import common.bean.User;
import dream.asset.std.asset.dto.AssetStdAssetDetailDTO;

/**
 * 회계자산 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface AssetStdAssetDetailService
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
    public AssetStdAssetDetailDTO findDetail(User user, String assetNo)throws Exception;
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception;
    
    /**
     * valid assetNo
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdAssetDetailDTO
     * @return
     * @throws Exception
     */
    public String validAssetNo(AssetStdAssetDetailDTO assetStdAssetDetailDTO, User user) throws Exception;
}
