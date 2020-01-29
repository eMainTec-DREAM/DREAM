package dream.rcm.funceq.service;

import java.util.List;

import common.bean.User;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: RcmFuncEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmFuncEqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param rcmFuncEqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findQnaList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO);    

    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFuncEqListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;

	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, User user);

}
