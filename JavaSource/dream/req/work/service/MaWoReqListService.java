package dream.req.work.service;

import java.util.List;

import common.bean.ResponseDTO;
import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoReqListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoReqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;
    
    /**
     * 삭제
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     * @throws Exception 
     */
    public ResponseDTO deleteList(String[] deleteRows, User user) throws Exception;
    
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;

}
