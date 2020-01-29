package dream.work.rpt.mabdpoint.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;

/**
 * �̻�������ġ - �۾���û  ��� dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoReqListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     *
     * @param maBdPointWoReqListDTO
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;

    public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;
    public int updateWoReqPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;
    public String validWoReqId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception;

}