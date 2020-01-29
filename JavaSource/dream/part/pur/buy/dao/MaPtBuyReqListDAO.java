package dream.part.pur.buy.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 구매신청 item 목록 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @param maPtBuyReqListDTO
     * @return List
     */
    public List findItemList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtBuyReqListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt, User user);

	public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, MaPtBuyReqListDTO maPtBuyReqListDTO, User user);

}