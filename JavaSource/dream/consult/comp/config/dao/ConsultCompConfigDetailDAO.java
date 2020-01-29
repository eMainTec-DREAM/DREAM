package dream.consult.comp.config.dao;

import common.bean.User;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;

/**
 * 시스템환경변수 - 상세 dao
 * 
 * @author syyang
 * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
 * @since 1.0
 */
public interface ConsultCompConfigDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param configName
     * @return
     */
    public ConsultCompConfigDetailDTO findDetail(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user);

    /**
     * detail update
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user);

    /**
     * detail insert
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user);
}