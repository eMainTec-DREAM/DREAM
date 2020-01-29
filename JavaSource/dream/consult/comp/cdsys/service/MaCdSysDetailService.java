package dream.consult.comp.cdsys.service;

import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;

/**
 * 시스템코드 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaCdSysDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaCdSysDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     * @throws Exception
     */
    public MaCdSysDetailDTO findDetail(String cdSysMId, String lang)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaCdSysDetailDTO maCdSysDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaCdSysDetailDTO maCdSysDetailDTO) throws Exception;
}
