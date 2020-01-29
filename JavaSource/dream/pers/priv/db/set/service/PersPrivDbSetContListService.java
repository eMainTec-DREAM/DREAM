package dream.pers.priv.db.set.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * PersPrivDbSetCont Page - List Service
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListService.java,v 1.0 2018/08/03 11:17:40 nhkim8548 Exp $
 * @since 1.0
 */
public interface PersPrivDbSetContListService
{
    /**
     * FIND DB CONTENTS LIST
     * @author  nhkim8548
     * @param PersPrivDbSetCommonDTO
     * @param persPrivDbSetContListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(PersPrivDbSetCommonDTO PersPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
    
    /**
     * DELETE DB CONTENTS LIST
     * @author  nhkim8548
     * @param deleteRows
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  nhkim8548
     * @param persPrivDbSetContListDTO
     * @param persPrivDbSetCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContListDTO persPrivDbSetContListDTO, User user) throws Exception;
    /**
     * INSERT Content List
     * @author nhkim8548
     * @param  persPrivDbSetCommonDTO
     * @param  persPrivDbSetContDetailDTO
     * @param  user
     * @return
     * @throws Exception
     */
    public int insertCntsList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO, User user) throws Exception;
}
