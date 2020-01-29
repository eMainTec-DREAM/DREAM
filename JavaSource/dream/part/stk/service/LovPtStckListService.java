package dream.part.stk.service;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.LovPtStckListDTO;
import dream.part.stk.form.LovPtStckListForm;

/**
 * 재고팝업 Service
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovPtStckListService
{
    /**
     * 재고검색 AC LOV
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPtStckListForm
     * @param loginUser
     * @return
     */
    List findAcList(LovPtStckListForm lovPtStckListForm, User loginUser);
    
    /**
     * find Total Count
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovPtStckListForm
     * @return
     */
    public String findTotalCount(LovPtStckListForm lovPtStckListForm, User user) throws Exception;

}