package dream.part.pur.buy.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.buy.form.LovPartPurBuyPnListForm;

/**
 * 현장신청부품 선택 Lov Service
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPartPurBuyPnListService
{
    /**
     * 현장신청부품 검색 LOV
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartPurBuyPnListForm
     * @param user
     * @return
     */
    List findAcList(LovPartPurBuyPnListForm lovPartPurBuyPnListForm, User user);
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPartPurBuyPnListForm
     * @return
     */
    public String findTotalCount(LovPartPurBuyPnListForm lovPartPurBuyPnListForm, User user) throws Exception;

}