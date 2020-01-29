package dream.pers.priv.info.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivFilterValueDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(PersPrivFilterValueDTO persPrivFilterValueDTO, User loginUser);
    
    public String findTotalCount(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception;

    /**
     * delete
     * @author euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param list
     * @return
     */
    public int[] deleteList(List<PersPrivFilterValueDTO> list, User user);
    
	public int[] insertDetail(List<PersPrivFilterValueDTO> list, User user);

	public String findPageId(PersPrivFilterValueDTO persPrivFilterValueDTO);

	public int updatedefault(PersPrivFilterValueDTO persPrivFilterValueDTO, User user);
	
    public String getColums(PersPrivFilterValueDTO persPrivFilterValueDTO, User user);
    
    public String getTables(PersPrivFilterValueDTO persPrivFilterValueDTO, User user);
    
    public String getOrderBy(PersPrivFilterValueDTO persPrivFilterValueDTO, User user);
    
    public String getWhere(PersPrivFilterValueDTO persPrivFilterValueDTO, User user);
}