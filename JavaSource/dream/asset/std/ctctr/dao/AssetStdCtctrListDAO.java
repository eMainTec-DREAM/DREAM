package dream.asset.std.ctctr.dao;

import java.util.List;

import common.bean.User;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;

/**
 * CostCenter - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdCtctrListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetStdCtctrCommonDTO
     * @return List
     */
    public List findCtctrList(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO, User user) throws Exception;
    
    /**
     * 삭제
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param ctctrId
     * @return
     */
    public int deleteCtctr(String ctctrId, User user) throws Exception;

    public String findTotalCount(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO,User user) throws Exception;
}