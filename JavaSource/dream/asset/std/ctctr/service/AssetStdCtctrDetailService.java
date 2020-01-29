package dream.asset.std.ctctr.service;

import common.bean.User;
import dream.asset.std.ctctr.dto.AssetStdCtctrDetailDTO;

/**
 * CostCenter - »ó¼¼ service
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AssetStdCtctrDetailService
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
    public AssetStdCtctrDetailDTO findDetail(User user, String assetNo)throws Exception;
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception;
    
    /**
     * valid ctctrNo
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetStdCtctrDetailDTO
     * @return
     * @throws Exception
     */
    public String validCtctrNo(AssetStdCtctrDetailDTO assetStdCtctrDetailDTO, User user) throws Exception;
}
