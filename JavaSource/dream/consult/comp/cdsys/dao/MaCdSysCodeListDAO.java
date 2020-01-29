package dream.consult.comp.cdsys.dao;

import java.util.List;

import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드 detail-code 목록 dao
 * @author  kim21017
 * @version $Id: MaCdSysCodeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaCdSysCodeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaCdSysCodeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCommonDTO
     * @param maCdSysCodeListDTO
     * @return List
     */
    public List findCodeList(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaCdSysCodeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteCodeList(String deleteRow, String deleteRowsExt);
}