package dream.pers.priv.db.set.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;

/**
 * Guide Page - List DAO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListDAO.java,v 1.0 2018/08/01 08:29:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public interface PersPrivDbSetListDAO
{
	/**
	 * FIND LIST
	 * @param persPrivDbSetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param persPrivDbSetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
    
}