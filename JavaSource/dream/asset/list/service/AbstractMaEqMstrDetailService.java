package dream.asset.list.service;

import java.util.List;

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
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public abstract class AbstractMaEqMstrDetailService implements MaEqMstrDetailService 
{    
	protected MaEqMstrDetailService maEqMstrDetailService;
	
	public AbstractMaEqMstrDetailService(MaEqMstrDetailService maEqMstrDetailService)
	{
		this.maEqMstrDetailService = maEqMstrDetailService;
	}
	
    public abstract MaEqMstrDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    public abstract int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public abstract int insertMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception;
    public abstract int insertToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception;
    public abstract int insertBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception;
    public abstract int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public abstract int updateMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User loginUser) throws Exception;
    public abstract int updateToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User loginUser) throws Exception;
    public abstract int updateBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User loginUser) throws Exception;
    public abstract List fileCopy(String objectId, String objectType);
    public abstract List fileCopyUpload(MaEqMstrDetailDTO maEqMstrDetailDTO, String objectType, User user);
    public abstract void deleteTempResultFiles();
    public abstract List findSlideImage(MaEqMstrCommonDTO maEqMstrCommonDTO, String compNo);
    public abstract List getReportView(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public abstract MaEqMoldMstrDetailDTO findMoldDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    public abstract MaEqToolMstrDetailDTO findToolDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
    public abstract MaEqBuildMstrDetailDTO findBuildDetail(MaEqMstrCommonDTO maEqMstrCommonDTO,User user)throws Exception;
	public abstract MaEqDeviceMstrDetailDTO findDeviceDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public abstract AssetListITDetailDTO findITDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public abstract void insertDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,User user);
	public abstract void updateDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,User user);
	public abstract void insertITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User user);
	public abstract void updateITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User user);
	public abstract int completeDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	public abstract int insertRevisionHistAndUpdateMstr(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception;
    public abstract int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User loginUser) throws Exception;
    public abstract int insertCopyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,User loginUser) throws Exception;
    public abstract int insertCopyToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public abstract int insertCopyBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser) throws Exception;
    public abstract void insertCopyDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public abstract void insertCopyITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public abstract String validItemNo(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception;
    public abstract void delPmSchByStatus(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
}
