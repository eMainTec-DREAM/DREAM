package dream.asset.rpt.eqpartprecon.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;

/**
 * AssetRptEqPartPreCon Page - List DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AssetRptEqPartPreConListDAO
{
    /**
     * FIND LIST
     * @param assetRptEqPartPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param assetRptEqPartPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception;
    
}
