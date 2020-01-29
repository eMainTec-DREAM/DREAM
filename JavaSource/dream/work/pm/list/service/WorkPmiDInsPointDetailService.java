package dream.work.pm.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsPointDetailDTO;

/**
 * WorkPmiDInsPoint Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmiDInsPointDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmiDInsPointDetailService
{
    /**
     * FIND DETAIL
     * @param workPmiDInsCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmiDInsPointDetailDTO findDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception;
    /**
     * INSERT
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE 
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    
    
    // eqAsmbId 가져오기
    /**
     * GETID 
     * @param workPmiDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String getId(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, User user) throws Exception;
    
    /**
     * Find Images For Image Slide
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmiDInsPointDetailDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(WorkPmiDInsPointDetailDTO workPmiDInsPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

    
}
