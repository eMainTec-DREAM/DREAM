package dream.asset.rpt.lcc.loc.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;

/**
 * 고장TOP(위치) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccLocListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccLocCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User loginUser);

    public String findTotalCount(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User user);
    
}
