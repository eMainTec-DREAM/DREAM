package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * 작업요청 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoReqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO, User user);
    
    public int deleteList(String id, String compNo);
    
    public int deleteResList(String id, String compNo);
    
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;

}