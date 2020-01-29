package dream.app.tracelog.dao;

import common.bean.User;
import dream.app.tracelog.dto.AppTracelogCommonDTO;
import dream.app.tracelog.dto.AppTracelogDetailDTO;

/**
 * TraceLog - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface AppTracelogDetailDAO
{
    /**
     * FIND DETAIL
     * @param appTracelogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public AppTracelogDetailDTO findCompTracelogDetail(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param appTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param appTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateCompTracelogDetail(AppTracelogDetailDTO appTracelogDetailDTO, User user) throws Exception;
    
}