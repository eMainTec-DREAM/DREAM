package dream.asset.rpt.mtbfmttr.usdept.dao;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;

/**
 * MTBF,MTTR(사용부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrUsDeptDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrUsDeptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO, User loginUser);
    
}