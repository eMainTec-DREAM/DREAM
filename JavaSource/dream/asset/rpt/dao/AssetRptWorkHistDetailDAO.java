package dream.asset.rpt.dao;

import common.bean.User;
import dream.asset.rpt.dto.AssetRptWorkHistCommonDTO;
import dream.asset.rpt.dto.AssetRptWorkHistDetailDTO;

/**
 * 설비이력(과거) - Detail DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetRptWorkHistDetailDAO
{
    /**
     * FIND DETAIL
     * @param assetRptWorkHistCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public AssetRptWorkHistDetailDTO findRptWorkHistDetail(AssetRptWorkHistCommonDTO assetRptWorkHistCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param assetRptWorkHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param assetRptWorkHistDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateRptWorkHistDetail(AssetRptWorkHistDetailDTO assetRptWorkHistDetailDTO, User user) throws Exception;
    
}