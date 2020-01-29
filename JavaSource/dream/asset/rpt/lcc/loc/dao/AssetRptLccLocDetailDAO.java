package dream.asset.rpt.lcc.loc.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocDetailDTO;

/**
 * 고장TOP(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccLocDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccLocDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User loginUser);

    public List findMo(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);

    public List findCa(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);

    public List findRe(AssetRptLccLocDetailDTO assetRptLccLocDetailDTO, User user);
    
}