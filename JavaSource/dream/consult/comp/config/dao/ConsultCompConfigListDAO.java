package dream.consult.comp.config.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 dao
 * @author  syyang
 * @version $Id: ConsultCompConfigListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 */
public interface ConsultCompConfigListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: ConsultCompConfigListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigCommonDTO
     * @return List
     */
    public List findConfigList(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user);
    
    /**
     * delete
     * @author syyang
     * @version $Id: ConsultCompConfigListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteConfig(String configId, String compNo, User user);
    
    public String findTotalCount(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user);
}