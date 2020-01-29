package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.AssetListITDetailDTO;
import dream.asset.list.dto.MaEqBuildMstrDetailDTO;
import dream.asset.list.dto.MaEqDeviceMstrDetailDTO;
import dream.asset.list.dto.MaEqMoldMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqToolMstrDetailDTO;

/**
 * ���񸶽��� - �� dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return
     */
    public MaEqMstrDetailDTO findDetail(String equipId, User user);
    
    public MaEqMoldMstrDetailDTO findMoldDetail(String equipId, User user);
    public MaEqToolMstrDetailDTO findToolDetail(String equipId, User user);
    public MaEqBuildMstrDetailDTO findBuildDetail(String equipId, User user);
       
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    
    public int mergeMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqMoldMstrDetailDTO maEqMoldMstrDetailDTO, User user);
    public int mergeToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqToolMstrDetailDTO maEqToolMstrDetailDTO, User user);
    public int mergeBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,MaEqBuildMstrDetailDTO maEqBuildMstrDetailDTO, User user);
    
    /**
     * ���񺯰��̷� insert
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int insertEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    
    public int insertEqPart(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    
    public int insertAssDetail(User loginUser, String equipId);
    
    public int updateEqPart(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @param loginUser 
     * @return
     */
    public int updateDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    
    /**
     * �������� ���࿩�� N���� ����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int updatePmActive(MaEqMstrDetailDTO maEqMstrDetailDTO);

    /**
     * ���ó�¥ ���� wo�̹��� ������ ����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public int deleteSchedAllPmEquip(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * ����Ʈ �ٵ�
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportEquipDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    
    /**
     * ����Ʈ �����ǰ
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoPartDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * ����Ʈ ���� �� ��ü����
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoRepDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    /**
     * ����Ʈ ������Ȳ
     * @author kim21017
     * @version $Id: MaEqMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrDetailDTO
     * @return
     */
    public List findReportWoOilDetail(MaEqMstrDetailDTO maEqMstrDetailDTO);
    
    public List findReportWoImgDetail(String objectId, String objectType);
    public void SP_IF_UPD_TXEQUIPMENT(String equipId, User loginUser) throws Exception;
    
    public String findEqPlant(MaEqMstrDetailDTO maEqMstrDetailDTO);

	/**
	 * �ڻ��ǰ���
	 * @param maEqMstrCommonDTO
	 * @param user
	 * @return
	 */
	public MaEqDeviceMstrDetailDTO findDeviceDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
	public AssetListITDetailDTO findITDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, User user);

	public int mergeDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, MaEqDeviceMstrDetailDTO maEqDeviceMstrDetailDTO,
			User loginUser);
	public int updateIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	public int updateRevisionHist(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	public int updateBeforeIsLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
	

	public int updateEqMstrLastVersion(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrDetailDTO maEqMstrDetailDTO, User user, String histId);
	
	public int mergeITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, AssetListITDetailDTO assetListITDetailDTO, User loginUser);
	
	// ����
    public int copyDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, String revisionHistId, String revisionStatus, User loginUser) throws Exception;
    public int copyEqHist(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    public int copyMoldDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,User user);
    public int copyToolDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,User user);
    public int copyBuildDetail(MaEqMstrDetailDTO maEqMstrDetailDTO,User user);
    public int copyDeviceDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);
    public int copyITDetail(MaEqMstrDetailDTO maEqMstrDetailDTO, User loginUser);

    public String getNextSequence(String seqName);

    public String validItemNo(MaEqMstrDetailDTO maEqMstrDetailDTO, User user) throws Exception;


    /**
     * ������� ������Ʈ 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @param eqStatusId
     */
    public void updateStatus(String equipId, String compNo, String eqStatusId);
    
    public void updRevObjectNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public void updEqHistItemNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
    public void updEqItemNo(String itemNo, MaEqMstrDetailDTO maEqMstrDetailDTO, User user);
}