package dream.app.tracelog.service;

import common.bean.User;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.dto.AppTracelogDetailDTO;
/**
 * TraceLog - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface AppTracelogDetailService
{    
	/**
	 * FIND TRACELOG DETAIL
	 * @param appTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public AppTracelogDetailDTO findCompTracelogDetail(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
	/**
	 * INSERT TRACELOG
	 * @param appTracelogDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception;
    /**
     * UPDATE TRACELOG
     * @param appTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception;
}
