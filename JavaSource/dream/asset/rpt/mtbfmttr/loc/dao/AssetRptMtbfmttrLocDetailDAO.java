package dream.asset.rpt.mtbfmttr.loc.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;

/**
 * MTBF,MTTR(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrLocDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrLocDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO, User loginUser);
    
}