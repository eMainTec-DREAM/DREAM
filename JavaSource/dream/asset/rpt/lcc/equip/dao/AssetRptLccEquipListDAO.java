package dream.asset.rpt.lcc.equip.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;

/**
 * 고장TOP(설비) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccEquipListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccEquipCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User loginUser);

    public String findTotalCount(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User user);
    
}