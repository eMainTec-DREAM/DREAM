package dream.pers.priv.db.set.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * 대시보드(Contents) - Detail DAO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailDAO.java,v 1.0 2018/08/03 12:29:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public interface PersPrivDbSetContDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param persPrivDbSetCommonDTO
     * @param persPrivDbSetContListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public PersPrivDbSetContDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param persPrivDbSetCommonDTO
     * @param persPrivDbSetContDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param persPrivDbSetCommonDTO
     * @param persPrivDbSetContDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO,PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception;
    
    /**
     * GRADE VALID CHECK
     * @param persPrivDbSetCommonDTO
     * @param persPrivDbSetContDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    
}