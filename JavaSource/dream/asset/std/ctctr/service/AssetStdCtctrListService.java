package dream.asset.std.ctctr.service;

import java.util.List;

import common.bean.User;
import dream.asset.std.ctctr.dto.AssetStdCtctrCommonDTO;

/**
 * CostCenter - 목록 service
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 */
public interface AssetStdCtctrListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id: $
     * @param assetStdCtctrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findCtctrList(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO, User user) throws Exception;    
   
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

    public String findTotalCount(AssetStdCtctrCommonDTO assetStdCtctrCommonDTO,User user) throws Exception;
}
