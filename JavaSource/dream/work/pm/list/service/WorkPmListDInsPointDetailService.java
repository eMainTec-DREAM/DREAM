package dream.work.pm.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;

/**
 * WorkPmListDInsPoint Page - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmListDInsPointDetailService
{
    /**
     * FIND DETAIL
     * @param workPmListDInsPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public WorkPmListDInsPointDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    /**
     * INSERT
     * @param workPmListDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception;
    /**
     * UPDATE 
     * @param workPmListDInsPointDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user) throws Exception;
    
    /**
     * Find Images For Image Slide
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListDInsPointDetailDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

    public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;

}
