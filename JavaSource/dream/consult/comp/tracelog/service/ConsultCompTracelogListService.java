/**
 * 
 */
package dream.consult.comp.tracelog.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
/**
 * Screen Trace - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompTracelogListService {
	/**
	 * FIND SCREEN TRACE LIST
	 * @param consultCompTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findCompTracelogList(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
	/**
	 * DELETE SCREEN TRACE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteCompTracelogList(String[] deleteRows, User user) throws Exception;
	/**
	 * FIND SCREEN TRACE LIST COUNT
	 * @param consultCompTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
}
