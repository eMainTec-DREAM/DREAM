package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;

/**
 * 작업요청-작업계획 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWoPlanRsltListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user) throws Exception;
}