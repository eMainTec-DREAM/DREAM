package dream.asset.rpt.mtbfmttr.equip.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;

/**
 * MTBF,MTTR(설비) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrEquipListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrEquipCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User loginUser) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception;
    
}