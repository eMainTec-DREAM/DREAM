package dream.consult.comp.config.service;

import java.util.List;

import common.bean.User;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;

/**
 * 시스템 환경변수 - 목록 service
 * @author  syyang
 * @version $Id: ConsultCompConfigListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since   1.0
 */
public interface ConsultCompConfigListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id: ConsultCompConfigListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @param consultCompConfigCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findConfigList(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user);    
    /**
     * delete
     * @author syyang
     * @version $Id: ConsultCompConfigListService.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteConfig(String[] configIdRows, String[] compNoRows, User user) throws Exception;

    public String findTotalCount(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user);
}
