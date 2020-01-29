package dream.asset.rpt.lcc.equip.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipCommonDTO;

/**
 * ����TOP(����) ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccEquipListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccEquipCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User loginUser);

    public String findTotalCount(AssetRptLccEquipCommonDTO assetRptLccEquipCommonDTO, User user);
    
}
