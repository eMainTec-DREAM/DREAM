package dream.consult.comp.tracelog.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;

/**
 * Screen Trace - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompTracelogListDAO
{
	/**
	 * FIND LIST
	 * @param consultCompTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findCompTracelogList(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param id
     * @param user
     * @return
     * @throws Exception
     */
    public int deleteCompTracelogList(String id, User user) throws Exception;
    /**
	 * FIND LIST COUNT
	 * @param consultCompTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
    
}