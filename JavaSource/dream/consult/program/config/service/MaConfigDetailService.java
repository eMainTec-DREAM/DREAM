package dream.consult.program.config.service;

import common.bean.User;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;

/**
 * 시스템 환경변수 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaConfigDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaConfigDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaConfigDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigCommonDTO
     * @return
     * @throws Exception
     */
    public MaConfigDetailDTO findDetail(MaConfigCommonDTO maConfigCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaConfigDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaConfigDetailDTO maConfigDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaConfigDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaConfigDetailDTO maConfigDetailDTO, User user) throws Exception;
}
