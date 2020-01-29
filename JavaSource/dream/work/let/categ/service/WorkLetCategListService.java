package dream.work.let.categ.service;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 안전작업유형 list Page - List Service
 * @author euna0207
 * @version $Id: WorkLetCategListService.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 */
public interface WorkLetCategListService {
	/**
	 * FIND PROGRAM GUIDE LIST
	 * @param workLetCategCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception;
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
	 * @param workLetCategCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception;
}
