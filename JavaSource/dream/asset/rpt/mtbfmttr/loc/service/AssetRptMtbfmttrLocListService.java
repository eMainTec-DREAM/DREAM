package dream.asset.rpt.mtbfmttr.loc.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;

/**
 * MTBF,MTTR(위치) 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrLocListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrLocCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User user) throws Exception;
    
}
