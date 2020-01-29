package dream.mgr.cdsys.dao;

import common.bean.User;
import dream.mgr.cdsys.dto.MgrCdSysCodeDetailDTO;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;

/**
 * 시스템코드-분류 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrCdSysCodeDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     */
    public MgrCdSysCodeDetailDTO findDetail(MgrCdSysCommonDTO mgrCdSysCommonDTO, MgrCdSysCodeListDTO mgrCdSysCodeListDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailDTO
     * @param mgrCdSysCommonDTO
     * @return
     */
    public int updateDetail(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user);
    
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrCdSysCodeDetailDTO
     * @param mgrCdSysCommonDTO
     * @return
     */
    public int updateLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user);
    
    public String checkLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user);
    
    public int insertLangData(MgrCdSysCodeDetailDTO mgrCdSysCodeDetailDTO, MgrCdSysCommonDTO mgrCdSysCommonDTO, User user);
    
}