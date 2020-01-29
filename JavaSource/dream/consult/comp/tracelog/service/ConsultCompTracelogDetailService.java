package dream.consult.comp.tracelog.service;

import common.bean.User;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;
/**
 * Screen Trace - Detail Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface ConsultCompTracelogDetailService
{    
	/**
	 * FIND SCREEN TRACE DETAIL
	 * @param consultCompTracelogCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ConsultCompTracelogDetailDTO findCompTracelogDetail(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception;
	/**
	 * INSERT SCREEN TRACE
	 * @param consultCompTracelogDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception;
    /**
     * UPDATE SCREEN TRACE
     * @param consultCompTracelogDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user) throws Exception;
}
