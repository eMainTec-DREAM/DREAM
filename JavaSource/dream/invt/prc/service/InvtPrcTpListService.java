package dream.invt.prc.service;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * 구매절차 - 목록 service
 * @author  kim21017
 * @version $Id: InvtPrcTpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPrcTpListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: InvtPrcTpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param invtPrcTpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findInvtPrcTpList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);    
    
    public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, User user);


    /**
     * delete
     * @author kim21017
     * @version $Id: InvtPrcTpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;

}
