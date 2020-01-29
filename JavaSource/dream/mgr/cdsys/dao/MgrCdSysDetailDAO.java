package dream.mgr.cdsys.dao;

import dream.mgr.cdsys.dto.MgrCdSysDetailDTO;

/**
 * 시스템코드 - 상세 dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrCdSysDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @return
     */
    public MgrCdSysDetailDTO findDetail(String cdSysMId, String lang);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysDetailDTO
     * @return
     */
    public int updateDetail(MgrCdSysDetailDTO mgrCdSysDetailDTO);
    
    public String checkLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO);
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysDetailDTO
     * @return
     */
    public void updateLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO);
    
    public void insertLangData(MgrCdSysDetailDTO mgrCdSysDetailDTO);
    
}