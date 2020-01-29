package dream.work.pm.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;

/**
 * 예방설비 상세
 * @author  kim21017
 * @version $Id: WorkPmListEquipDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface WorkPmListEquipDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public WorkPmListEquipDetailDTO findDetail(String pmId, String pmEquipId,User user)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: WorkPmListEquipDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmListEquipDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    public List findSlideImage(WorkPmListEquipDetailDTO workPmListEquipDetailDTO, User user) throws InvalidKeyException, URISyntaxException, StorageException;
}
