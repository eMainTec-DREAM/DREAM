package dream.asset.rpt.monthnew.service;

import java.util.List;

import common.bean.User;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;

/**
 * 신규설비등록현황 service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface AssetRptMonthNewListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param assetRptMonthNewListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConnList(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser);    
}
