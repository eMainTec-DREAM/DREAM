package dream.consult.comp.intf.dao;

import common.bean.User;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;

/**
 * Interface Page - Detail DAO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ConsultCompIntfDetailDAO
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
     * INSERT DETAIL
     * @param consultCompIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception;
    
    /**
     * UPDATE DETAIL
     * @param consultCompIntfDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user) throws Exception;
    
}