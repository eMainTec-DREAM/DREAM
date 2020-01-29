package dream.work.pmi.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;

/**
 * 점검작업 점검 상세
 * @author  kim210117
 * @version $Id: WorkPmiPointDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface WorkPmiPointDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmiPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmiPointDetailDTO findDetail(String pminslistId, String pmInsPointId, String pmPointId, User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmiPointDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiPointDetailDTO
     * @param workPmiCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmiPointDetailDTO workPmiPointDetailDTO, WorkPmiCommonDTO workPmiCommonDTO, User user) throws Exception;
    
    /**
     * Find Images For Image Slide
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmiPointDetailDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(WorkPmiPointDetailDTO workPmiPointDetailDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

    
}
