package dream.asset.rpt.mtbfmttr.usdept.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;

/**
 * MTBF,MTTR(사용부서) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrUsDeptDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrUsDeptDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO, User loginUser);
    
}
