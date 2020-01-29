package dream.part.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * 보전자재분류(마스터) - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtMstrDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPtMstrDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid Part No
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validPartNo(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser) throws Exception;

    /**
     * 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List getPhotoList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException;

    /**
     * Find Slide Image
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException;

}
