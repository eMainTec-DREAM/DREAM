package dream.consult.program.config.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.config.dto.MaConfigCommonDTO;

/**
 * 시스템 환경변수 - 목록 service
 * @author  kim21017
 * @version $Id: MaConfigListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaConfigListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaConfigListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maConfigCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConfigList(MaConfigCommonDTO maConfigCommonDTO, User user);    
    public String findTotalCount(MaConfigCommonDTO maConfigCommonDTO, User user);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaConfigListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteConfig(String[] configIdRows, User user) throws Exception;

}
