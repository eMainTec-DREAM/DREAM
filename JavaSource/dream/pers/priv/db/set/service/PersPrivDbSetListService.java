/**
 * 
 */
package dream.pers.priv.db.set.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
/**
 * Guide Page - List Service
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListService.java,v 1.0 2018/8/01 15:55:40 nhkim8548 Exp $
 * @since 1.0
 */
public interface PersPrivDbSetListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param persPrivDbSetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
	/**
	 * DELETE PROGRAM GUIDE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND PROGRAM GUIDE LIST COUNT
	 * @param persPrivDbSetCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO, User user) throws Exception;
}
