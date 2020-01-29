package dream.app.tracelog.dao;

import java.util.List;

import common.bean.User;
import dream.app.tracelog.dto.AppTracelogCommonDTO;

/**
 * TraceLog - List DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AppTracelogListDAO
{
	/**
	 * FIND LIST
	 * @param appTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findCompTracelogList(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
    
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
	 * @param appTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
    
}