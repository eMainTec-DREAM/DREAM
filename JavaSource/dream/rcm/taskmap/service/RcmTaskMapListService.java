package dream.rcm.taskmap.service;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: RcmTaskMapListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmTaskMapListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmTaskMapListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param rcmTaskMapCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findQnaList(RcmTaskMapCommonDTO rcmTaskMapCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: RcmTaskMapListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmTaskMapDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;

	public String findTotalCount(RcmTaskMapCommonDTO rcmTaskMapCommonDTO, User user);

}
