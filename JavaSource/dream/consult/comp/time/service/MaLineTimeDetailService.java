package dream.consult.comp.time.service;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;

/**
 * 가동시간설정 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaLineTimeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaLineTimeDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeCommonDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaLineTimeDetailDTO findDetail(MaLineTimeCommonDTO maLineTimeCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaLineTimeDetailDTO maLineTimeDetailDTO) throws Exception;
    public int insertDetail(MaLineTimeDetailDTO maLineTimeDetailDTO, User user) throws Exception;
}
