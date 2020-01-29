package dream.asset.rpt.lcc.usdept.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;

/**
 * 고장TOP(사용부서) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccUsDeptDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param assetRptLccUsDeptCommonDTO 
     * @since   1.0
     * 
     * @param assetRptLccUsDeptDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO, User loginUser);
    
}
