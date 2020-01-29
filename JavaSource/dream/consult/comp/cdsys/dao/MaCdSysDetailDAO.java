package dream.consult.comp.cdsys.dao;

import dream.consult.comp.cdsys.dto.MaCdSysDetailDTO;

/**
 * 시스템코드 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaCdSysDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     */
    public MaCdSysDetailDTO findDetail(String cdSysMId, String lang);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     */
    public int insertDetail(MaCdSysDetailDTO maCdSysDetailDTO);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     */
    public int updateDetail(MaCdSysDetailDTO maCdSysDetailDTO);
    
    public String checkLangData(MaCdSysDetailDTO maCdSysDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysDetailDTO
     * @return
     */
    public void updateLangData(MaCdSysDetailDTO maCdSysDetailDTO);
    
    public void insertLangData(MaCdSysDetailDTO maCdSysDetailDTO);
    
}