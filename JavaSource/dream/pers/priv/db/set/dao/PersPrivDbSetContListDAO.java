package dream.pers.priv.db.set.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * PersPrivDbSetCont Page - List DAO
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListDAO.java,v 1.0 2018/08/03 12:26:40 nhkim8548 Exp $
 * @since 1.0
 *
 */
public interface PersPrivDbSetContListDAO
{
    /**
     * FIND LIST
     * @param persPrivDbSetCommonDTO
     * @param PersPrivDbSetContListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception;
    /**
     * TOTALCOUNT
     * @param persPrivDbSetCommonDTO
     * @param PersPrivDbSetContListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
}
