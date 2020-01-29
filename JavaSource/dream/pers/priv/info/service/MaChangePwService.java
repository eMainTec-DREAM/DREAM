package dream.pers.priv.info.service;

import java.util.Map;

import common.bean.User;
import dream.pers.priv.info.dto.MaChangePwDTO;

/**
 * 비밀번호변경
 * @author  kim210117
 * @version $Id: MaChangePwService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaChangePwService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaChangePwService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     * @throws Exception
     */
    public MaChangePwDTO findDetail(MaChangePwDTO maChangePwDTO)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaChangePwService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaChangePwDTO maChangePwDTO) throws Exception;
    
    public String checkPwHist(MaChangePwDTO maChangePwDTO, String pwChangeHistCnt) throws Exception;
    
    /**
     * System User Password 확인 Method
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maChangePwDTO
     * @param user 
     * @return
     * @throws Exception 
     */
    public Map<String, String> checkPassword(MaChangePwDTO maChangePwDTO, User user) throws Exception;
}
