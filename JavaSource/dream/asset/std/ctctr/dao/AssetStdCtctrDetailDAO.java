package dream.asset.std.ctctr.dao;

import common.bean.User;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;

/**
 * CostCenter - »ó¼¼ dao
 * 
 * @author ghlee
 * @version $Id:  $
 * @since 1.0
 */
public interface AssetStdCtctrDetailDAO
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
    public AssetStdCtctrDetailDTO findDetail(User user, String assetId);
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public int insertDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user);
    
    /**
     * detail update
     * @author ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public int updateDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user);
    
    /**
     * valid ctctrNo
     * @author ghlee
     * @version $Id:  $
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     */
    public String validCtctrNo(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user);
}