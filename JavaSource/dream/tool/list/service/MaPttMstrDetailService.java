package dream.tool.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrDetailDTO;

/**
 * 보전자재분류(마스터) - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttMstrDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPttMstrDetailDTO findDetail(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid deptNo
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validDeptNo(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser) throws Exception;

    /**
     * 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List getPhotoList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException;

    /**
     * Find Slide Image
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser) throws InvalidKeyException, URISyntaxException, StorageException;

}
