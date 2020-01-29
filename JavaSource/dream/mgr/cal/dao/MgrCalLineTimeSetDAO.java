package dream.mgr.cal.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.cal.dto.MgrCalLineTimeSetDTO;

/**
 * 가동시간설정 - 목록 
 * @author  euna0207
 * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 */
public interface MgrCalLineTimeSetDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalLineTimeSetDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     * 
     * @param mgrCalLineTimeSetDTO
     * @return List
     */
    public List findList(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO,User user);

    public String findTotalCount(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);

    public int[] insertDetail(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception;
    
    public int[] deleteList(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception;

    public int[] updateDetail(final List<MgrCalLineTimeSetDTO> list, final User user) throws Exception;

    public String getColums(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);
    
    public String getTables(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);
    
    public String getOrderBy(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);
    
    public String getWhere(MgrCalLineTimeSetDTO mgrCalLineTimeSetDTO, User user);
}