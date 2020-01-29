package dream.comm.revision.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.comm.revision.dto.CommRevCommonDTO;

/**
 * 상세 dao
 * @author jung7126
 * @version $Id: CommRevDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface CommRevDAO extends BaseJdbcDaoSupportIntf
{
	/**
     * Detail 조희
     * @author jung7126
     * @version $Id: CommRevDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevDTO
     * @param user
     * @return
     * @throws Exception
     */
    public CommRevCommonDTO findDetail(CommRevCommonDTO commRevCommonDTO, User user) throws Exception;
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: CommRevDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param commRevDTO
     * @return
     */
    public int insertRevHist(CommRevCommonDTO commRevCommonDTO, User user);
    public int insertPm(CommRevCommonDTO commRevCommonDTO, User loginUser);
    public int insertAsset(CommRevCommonDTO commRevCommonDTO, User user);
    public int insertMold(CommRevCommonDTO commRevCommonDTO);
	public int insertTool(CommRevCommonDTO commRevCommonDTO);
	public int insertBuilding(CommRevCommonDTO commRevCommonDTO);
	public int insertDevice(CommRevCommonDTO commRevCommonDTO);
	public int insertStwrk(CommRevCommonDTO commRevCommonDTO);
	public int insertEqItDetail(CommRevCommonDTO commRevCommonDTO);
    
	
    /**
     * update Revisionhist
     * @author jung7126
     * @version $Id: MaPmMstrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param revisionhistId
     * @param compNo
     * @return
     */
    public int updateRevHist(CommRevCommonDTO commRevCommonDTO, User user);
    public int updatePm(String objectId, String compNo, String isLastVersion, String revStat);
    public int updateAsset(String objectId, String compNo, String isLastVersion, String revStat);
    public int updateStwrk(String objectId, String compNo, String isLastVersion, String revStat);
    public int updateOldAsset(String objectId, String newObjectId, String compNo, String isLastVersion, String revStat);
    public int updateNewAsset(String objectId, String oldObjectId, String compNo, String isLastVersion, String revStat);
    public int updateEqIdRevHist(String objectId, String oldObjectId, String oldObjectNo, String compNo);
    public int updateApprObjectId(String oldObjectId, String newObjectId, String apprType, String compNo);
    public int updateAuditTrailObjectId(String oldObjectId, String newObjectId, String compNo);
    public String updateEqIdAsmb(String oldEquipId, String newEquipId, User user);
    public String updateEqIdSpec(String oldEquipId, String newEquipId, User user);
    public String updateEqIdPart(String oldEquipId, String newEquipId, User user);
    public String updateEqIdAsset(String oldEquipId, String newEquipId, User user);
    public String updateEqIdPmEquip(String oldEquipId, String newEquipId, User user);
    public String fileImgMove(String oldObjectId, String newObjectId, String objectType, User user);
    public String fileDocMove(String oldObjectId, String newObjectId, String objectType, User user);
    public String updateEqIdEqHist(String oldEquipId, String newEquipId, User user);
    
    /**
     * 현재 가장 최신버전의 ID값 조회
     * @author hankyul
     * @version $Id: CommRevCommonDTO.java,v 1.0 2017/11/03 16:39:47 hankyul Exp $
     * @since   1.0
     * 
     * @param objectNo
     * @param compNo
     * @return
     */
    public List findOldPm(String objectNo, String compNo);
    public String findOldAsset(String objectNo, String compNo);
    public String findOldStwrk(String objectNo, String compNo);
    
    /**
     * Validation
     * 저장시 각종 Validation
     * @author hankyul
     * @version $Id: CommRevCommonDTO.java,v 1.0 2017/11/03 16:39:47 hankyul Exp $
     * @since   1.0
     * 
     * @param commRevCommonDTO
     * @param user
     * @return
     */
    public String validRevNo(CommRevCommonDTO commRevCommonDTO, User user);
    public String validPmNo(CommRevCommonDTO commRevCommonDTO, User user);
    public String validStwrkNo(CommRevCommonDTO commRevCommonDTO, User user);
    
    public String maxRevNo(CommRevCommonDTO commRevCommonDTO, User user);
    
    public String findRevStatus(CommRevCommonDTO commRevCommonDTO, User user);

    public String[] getPMTracelogId(String id, String compNo);

    public void copyAuditTrailObjectId(String newSeq, String id, String compNo,
            String newTracelogId, String tracelogId);

    public void copyAuditTrailDtlObjectId(String newSeq, String id,
            String compNo, String newTraceLogId, String tracelogId);

    public void updateEqIdTool(String oldObjectId, String oldNewObjectId,
            User user);
    

}