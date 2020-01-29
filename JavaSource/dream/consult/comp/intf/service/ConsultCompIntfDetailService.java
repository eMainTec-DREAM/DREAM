package dream.consult.comp.intf.service;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;
/**
 * Interface Page - Detail Service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface ConsultCompIntfDetailService
{    
	/**
	 * FIND DETAIL
	 * @param consultCompIntfCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public ConsultCompIntfDetailDTO findDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception;
	/**
	 * INSERT
	 * @param consultCompIntfDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception;
    /**
     * UPDATE
     * @param consultCompIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception;
}
