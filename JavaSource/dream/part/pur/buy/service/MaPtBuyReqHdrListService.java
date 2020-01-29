package dream.part.pur.buy.service;

import java.util.List;

import common.bean.User;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * 구매신청 - 목록 service
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPtBuyReqHdrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPtBuyReqHdrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPtBuyReqHdrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findBuyList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaPtBuyReqHdrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtBuyReqHdrDTOList
     * @return
     * @throws Exception
     */
    public int deleteBuy(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user);


}
