package dream.invt.rpt.priceexerate.dao;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;

/**
 * 투자비 집행현황 목록 - List DAO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public interface InvtRptPriceExeRateListDAO
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
     * FIND TOTAL LIST
     * @param invtRptPriceExeRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception;
    
}
