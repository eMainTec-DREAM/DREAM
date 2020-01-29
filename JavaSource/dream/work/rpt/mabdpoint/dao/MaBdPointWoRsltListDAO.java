package dream.work.rpt.mabdpoint.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * 이상점검조치 - 작업결과 목록 dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoRsltListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param maBdPointWoRsltListDTO
     * @param user
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user);

    public int deleteList(String id, String compNo);
    
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;

    public int updateWoRsltPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;
    public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;
    public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;

}