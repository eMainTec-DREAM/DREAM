package dream.asset.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;

/**
 * 설비마스터 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrMoldDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,String compNo, User user)throws Exception;
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrMoldDetailDTO findMoldDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception;
    public int completeDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    
    public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO, User loginUser) throws Exception;

    public List fileCopy(String objectId, String objectType) throws InvalidKeyException, URISyntaxException, StorageException;
    
    public void deleteTempResultFiles();
    
    /**
     * Find Images For Image Slide
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param compNo
     * @return
     * @throws StorageException 
     * @throws URISyntaxException 
     * @throws InvalidKeyException 
     */
    public List findSlideImage(MaEqMstrCommonDTO maEqMstrCommonDTO, String compNo) throws InvalidKeyException, URISyntaxException, StorageException;

    /**
     * report
     * @author kim21017
     * @version $Id: MaEqMstrMoldDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List getReportView(MaEqMstrDetailDTO maEqMstrDetailDTO);
}
