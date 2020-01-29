package dream.req.work.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResListDTO;

/**
 * 작업요청-처리사항 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoReqResListDAO extends BaseJdbcDaoSupportIntf
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
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user) throws Exception;
}