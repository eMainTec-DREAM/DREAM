package dream.consult.comp.tracelog.dao;

import common.bean.User;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;

/**
 * Screen Trace - Detail DAO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompTracelogDetailDAO
{
    /**
     * FIND DETAIL
     * @param consultCompTracelogCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultCompTracelogDetailDTO findCompTracelogDetail(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
    
    /**
     * INSERT DETAIL
     * @param consultCompTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param consultCompTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception;
    
}