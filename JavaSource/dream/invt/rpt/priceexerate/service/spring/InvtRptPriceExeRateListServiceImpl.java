package dream.invt.rpt.priceexerate.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.rpt.priceexerate.dao.InvtRptPriceExeRateListDAO;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;
import dream.invt.rpt.priceexerate.service.InvtRptPriceExeRateListService;

/**
 * 투자비 집행현황 목록 - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="invtRptPriceExeRateListServiceTarget"
 * @spring.txbn id="invtRptPriceExeRateListService"
 * @spring.property name="invtRptPriceExeRateListDAO" ref="invtRptPriceExeRateListDAO"
 */
public class InvtRptPriceExeRateListServiceImpl implements InvtRptPriceExeRateListService
{
    private InvtRptPriceExeRateListDAO invtRptPriceExeRateListDAO = null;

    public List findList(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception
    {      
        return invtRptPriceExeRateListDAO.findList(invtRptPriceExeRateCommonDTO,user);
    }

    public InvtRptPriceExeRateListDAO getInvtRptPriceExeRateListDAO() {
        return invtRptPriceExeRateListDAO;
    }

    public void setInvtRptPriceExeRateListDAO(InvtRptPriceExeRateListDAO invtRptPriceExeRateListDAO) {
        this.invtRptPriceExeRateListDAO = invtRptPriceExeRateListDAO;
    }    
    
    public String findTotalCount(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO,User user)  throws Exception
    {
        return invtRptPriceExeRateListDAO.findTotalCount(invtRptPriceExeRateCommonDTO, user);
    }
}
