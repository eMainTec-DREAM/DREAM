package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoRsltListDTO;

/**
 * 작업요청-작업결과 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWoRsltListDAO extends BaseJdbcDaoSupportIntf
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
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoRsltListDTO reqWoRsltListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoRsltListDTO reqWoRsltListDTO, User user) throws Exception;
}