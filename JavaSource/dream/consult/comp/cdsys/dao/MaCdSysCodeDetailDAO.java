package dream.consult.comp.cdsys.dao;

import common.bean.User;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * 시스템코드-분류 dao
 * @author  kim21017
 * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaCdSysCodeDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param cdSysMId
     * @param cdSysDId
     * @return
     */
    public MaCdSysCodeDetailDTO findDetail(MaCdSysCommonDTO maCdSysCommonDTO, MaCdSysCodeListDTO maCdSysCodeListDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     */
    public int updateDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     */
    public int updateLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaCdSysCodeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maCdSysCodeDetailDTO
     * @param maCdSysCommonDTO
     * @return
     */
    public int insertDetail(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user);
    
    public String checkLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user);
    public int insertLangData(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO, MaCdSysCommonDTO maCdSysCommonDTO, User user);
    
}