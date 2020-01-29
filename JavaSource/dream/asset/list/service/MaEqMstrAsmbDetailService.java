package dream.asset.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrAsmbDetailDTO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 상세
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrAsmbDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqAsmbId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrAsmbDetailDTO findDetail(MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrAsmbDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrAsmbDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception;
    
    /**
     * Find Images For Image Slide
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * @param maEqMstrCommonDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

    public List getPhotoList(MaEqMstrAsmbDetailDTO maEqMstrAsmbDetailDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException;
    
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user) throws Exception;
}
