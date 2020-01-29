package dream.consult.program.config.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.config.dto.MaConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 dao
 * @author  kim21017
 * @version $Id: MaConfigListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaConfigListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaConfigListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigCommonDTO
     * @return List
     */
    public List findConfigList(MaConfigCommonDTO maConfigCommonDTO, User user);
    public String findTotalCount(MaConfigCommonDTO maConfigCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaConfigListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteConfig(String configId, User user);
}