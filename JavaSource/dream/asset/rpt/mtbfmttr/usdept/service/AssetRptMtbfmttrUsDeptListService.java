package dream.asset.rpt.mtbfmttr.usdept.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;

/**
 * MTBF,MTTR(���μ�) ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface AssetRptMtbfmttrUsDeptListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrUsDeptCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User loginUser) throws Exception;

    public String findTotalCount(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User user) throws Exception;
    
}
