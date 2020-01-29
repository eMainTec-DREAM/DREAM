package dream.part.pur.buy.dao;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * 구매신청 item 상세 dao
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqDetailDAO
{
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public int updateDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     */
    public int insertDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);
}