package dream.consult.comp.cdsys.service;

import java.util.List;

import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail - code 목록
 * @author  kim21017
 * @version $Id: MaCdSysCodeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaCdSysCodeListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaCdSysCodeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param maCdSysCodeListDTO
     * @throws Exception
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaCdSysCodeListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteCodeList(String[] m_id, String[] d_id) throws Exception;

}
