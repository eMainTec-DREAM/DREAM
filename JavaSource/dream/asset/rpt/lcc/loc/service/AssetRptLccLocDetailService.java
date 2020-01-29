package dream.asset.rpt.lcc.loc.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;

/**
 * 고장TOP(위치) 상세 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccLocDetailService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccLocDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User loginUser);
    
    public List findMo(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);
    
    public List findCa(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);
    
    public List findRe(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);
    
}
