package dream.asset.rpt.mtbfmttr.loc.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;

/**
 * MTBF,MTTR(위치) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrLocListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrLocCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findList(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User user) throws Exception;
    
}