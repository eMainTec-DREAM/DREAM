package dream.asset.rpt.lcc.usdept.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;

/**
 * 고장TOP(사용부서) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccUsDeptListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccUsDeptCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User loginUser);

    public String findTotalCount(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, User user);
    
}
