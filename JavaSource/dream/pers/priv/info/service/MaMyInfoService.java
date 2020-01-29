package dream.pers.priv.info.service;

import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * 내정보 service
 * @author  kim21017
 * @version $Id: MaMyInfoService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaMyInfoService
{     
    /**
     *  find
     * @author  kim21017
     * @version $Id: MaMyInfoService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param loginId
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public MaMyInfoCommonDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO);    

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMyInfoService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception;
}
