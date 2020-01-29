package dream.mgr.message.service;

import java.util.List;

import common.bean.User;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;

/**
 * Message Transfer Page - List Service
 * @author syyang
 * @version $Id:$
 * @since 1.0
 */
public interface MgrMessageTransListService {
	/**
	 * FIND LIST
	 * @param mgrMessageTransCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param mgrMessageTransCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception;
}
