package dream.part.pur.buy.service;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 구매신청item - detail
 * @author  kim21017
 * @version $Id: MaPtBuyReqDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqListDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     * @throws Exception
     */
    public MaPtBuyReqDetailDTO findDetail(MaPtBuyReqListDTO maPtBuyReqListDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtBuyReqDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqDetailDTO
     * @param maPtBuyReqHdrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception;
    public MaPtBuyReqDetailDTO getQty(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, User user) throws Exception;
}
