package dream.rcm.ffail.service;

import java.util.List;

import common.bean.User;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: RcmFfailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFfailListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFfailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param rcmFfailCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findQnaList(RcmFfailCommonDTO rcmFfailCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFfailListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFfailDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;

	public String findTotalCount(RcmFfailCommonDTO rcmFfailCommonDTO, User user);

}
