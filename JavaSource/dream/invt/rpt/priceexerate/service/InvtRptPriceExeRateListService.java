package dream.invt.rpt.priceexerate.service;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;

/**
 * 투자비 집행현황 목록 - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface InvtRptPriceExeRateListService
{
    /**
     * FIND LIST
     * @param invtRptPriceExeRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptPriceExeRateCommonDTO
     * @return
     */
    public String findTotalCount(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception;
}
