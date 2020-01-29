package dream.asset.rpt.lcc.equip.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.equip.dto.AssetRptLccEquipDetailDTO;

/**
 * ����TOP(����) �� ���
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccEquipDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccEquipDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User loginUser);

    public List findMo(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user);
    
    public List findCa(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user);
    
    public List findRe(AssetRptLccEquipDetailDTO assetRptLccEquipDetailDTO, User user);
    
}
