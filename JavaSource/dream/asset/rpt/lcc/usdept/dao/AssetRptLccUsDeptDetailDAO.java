package dream.asset.rpt.lcc.usdept.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;

/**
 * 고장TOP(사용부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptLccUsDeptDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param assetRptLccUsDeptCommonDTO 
     * @since   1.0
     * 
     * @param assetRptLccUsDeptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptLccUsDeptCommonDTO assetRptLccUsDeptCommonDTO, AssetRptLccUsDeptDetailDTO assetRptLccUsDeptDetailDTO, User loginUser);
    
}