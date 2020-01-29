package dream.consult.comp.cdsys.service;

import common.bean.User;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 - detail-code 
 * @author  kim210117
 * @version $Id: MaCdSysCodeDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaCdSysCodeDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     * @throws Exception
     */
    public MaCdSysCodeDetailDTO findDetail(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user) throws Exception;
}
