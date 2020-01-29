package dream.part.pur.buy.dao;

import java.util.List;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * 구매신청 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqHdrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrCommonDTO
     * @return List
     */
    public List findBuyList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBuy(String id, User user);
    
    public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);
}