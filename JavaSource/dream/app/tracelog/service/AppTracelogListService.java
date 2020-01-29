/**
 * 
 */
package dream.app.tracelog.service;

import java.util.List;

import common.bean.User;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
/**
 * TraceLog - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AppTracelogListService {
	/**
	 * FIND TRACELOG LIST
	 * @param appTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findCompTracelogList(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
	/**
	 * DELETE TRACELOG LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteCompTracelogList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND TRACELOG LIST COUNT
	 * @param appTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
}
