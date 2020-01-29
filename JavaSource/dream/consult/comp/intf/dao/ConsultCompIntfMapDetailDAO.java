package dream.consult.comp.intf.dao;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapDetailDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfMapListDTO;

/**
 * Interface Log Page - Detail DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompIntfMapDetailDAO
{
    /**
     * FIND DETAIL
     * @param consultCompIntfMapListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public ConsultCompIntfMapDetailDTO findDetail(ConsultCompIntfMapListDTO consultCompIntfMapListDTO, User user) throws Exception;
	public int insertDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO , User user) throws Exception;
	public int updateDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, ConsultCompIntfMapDetailDTO consultCompIntfMapDetailDTO , User user) throws Exception;
}