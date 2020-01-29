package dream.asset.rpt.mtbfmttr.equip.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;

/**
 * MTBF,MTTR(설비) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrEquipListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrEquipCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception;
    
    public List findMtbfList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception;
    
    public List findMttrList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User user) throws Exception;
    
}
