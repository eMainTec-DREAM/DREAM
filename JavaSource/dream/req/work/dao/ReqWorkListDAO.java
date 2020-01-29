package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.ReqWorkCommonDTO;

/**
 * 작업요청 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWorkListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @return List
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO, User user);

    public int deleteList(String id, String compNo);

    public int deleteResList(String id, String compNo);
    
    public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception;

}