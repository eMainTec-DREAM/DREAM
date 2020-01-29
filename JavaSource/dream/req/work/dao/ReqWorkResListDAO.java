package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청-처리사항 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWorkResListDAO extends BaseJdbcDaoSupportIntf
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
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user);

    public int deleteList(String id, String compNo);
    
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user) throws Exception;

}