package dream.asset.rpt.lcc.usdept.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptCommonDTO;
import dream.asset.rpt.lcc.usdept.dto.AssetRptLccUsDeptDetailDTO;

/**
 * ����TOP(���μ�) �� ���
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
