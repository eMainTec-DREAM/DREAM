package dream.work.pm.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 상세
 * @author  kim210117
 * @version $Id: MaPmMstrPointDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPmMstrPointDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaPmMstrPointDetailDTO findDetail(String wkOrId, String pmPointId, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception;
    
    public int insertLovDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception;
    
    /**
     * Find Images For Image Slide
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

}
