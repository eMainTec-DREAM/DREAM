package dream.pers.priv.db.set.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;

/**
 * 대시보드(상세) Page - Detail DAO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailDAO.java,v 1.0 2018/07/31 13:27:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public interface PersPrivDbSetDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param persPrivDbSetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public PersPrivDbSetDetailDTO findDetail(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
    /**
     * FIND DASHBOARDMENU
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String validDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
    /**
     * INSERT DASHBOARDMENU
     * @param persPrivDbSetDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public int insertDbMenu(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO, User user) throws Exception;
}