package dream.work.rpt.mabdpoint.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;

/**
 * 이상점검조치 - 작업계획 목록 dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoPlanListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param maBdPointWoPlanListDTO
     * @param user
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;
    
    public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;
    public int updateWoPlanPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;
    public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception;

}