package dream.asset.rpt.mtbfmttr.equip.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;

/**
 * MTBF,MTTR(설비) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrEquipDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @param assetRptMtbfmttrEquipCommonDTO 
     * @since   1.0
     * 
     * @param assetRptMtbfmttrEquipDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User loginUser) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User user) throws Exception;
    
}
