package dream.consult.comp.time.service;

import common.bean.User;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowDetailDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별설정 상세
 * @author  kim210117
 * @version $Id: MaLineTimeDowDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaLineTimeDowDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param user 
     * @param maLineTimeDowListDTO 
     * @since   1.0
     * @return
     * @throws Exception
     */
    public MaLineTimeDowDetailDTO findDetail(MaLineTimeDowListDTO maLineTimeDowListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaLineTimeDowDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maLineTimeDowDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception;
    /**
     * 요일 중복검사
     */
    public String validDay(MaLineTimeCommonDTO maLineTimeCommonDTO, MaLineTimeDowDetailDTO maLineTimeDowDetailDTO, User user) throws Exception;
}
