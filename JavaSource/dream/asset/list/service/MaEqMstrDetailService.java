package dream.asset.list.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import common.bean.User;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;

/**
 * 설비마스터 - 상세 service
 * 
 * @author kim21017
 * @version $Id: MaEqMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaEqMstrDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public int insertMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception;
    public int insertToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception;
    public int insertBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public int updateMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception;
    public int updateToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception;
    public int updateBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception;

    public List fileCopyUpload(MaEqMstrDetailDTO maEqMstrDetailDTO, String objectType, User user) throws InvalidKeyException, URISyntaxException, StorageException;

    
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
     * @version $Id: MaEqMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List getReportView(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    /**
     * 금형 상세 부분
     */
    public MaEqMoldMstrDetailDTO findMoldDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    /**
     * 계측기 상세 부분
     */
    public MaEqToolMstrDetailDTO findToolDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    /**
     * 건물 상세 부분
     */
    public MaEqBuildMstrDetailDTO findBuildDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    /**
	 * 자산부품
	 * @param maEqMstrCommonDTO
	 * @param user
	 * @return
	 */
	public MaEqDeviceMstrDetailDTO findDeviceDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public AssetListITDetailDTO findITDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	/**
	 * @param maEqMstrDetailDTO
	 * @param maEqDeviceMstrDetailDTO
	 * @param user
	 */
	public void insertDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User user);
	public void updateDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User user);
	
	// IT장비 INSERT
	public void insertITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User user);
	// IT장비 UPDATE
	public void updateITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User user);
	
	public int completeDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	
	public int insertRevisionHistAndUpdateMstr(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception;
	
	// 복사
    public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User loginUser) throws Exception;
    
    public int insertCopyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,User loginUser) throws Exception;
    public int insertCopyToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public int insertCopyBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public void insertCopyDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public void insertCopyITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public String validItemNo(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception;
    
    /**
     * 설비상태에 따라 스케줄 삭제 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @param loginUser
     */
    public void delPmSchByStatus(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
}
