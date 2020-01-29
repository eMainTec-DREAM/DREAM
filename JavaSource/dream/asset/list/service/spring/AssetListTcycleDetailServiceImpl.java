package dream.asset.list.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.ass.asset.service.AssAssetDetailService;
import dream.asset.list.dao.AssetListTcycleDetailDAO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.AssetListTcycleDetailService;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dao.WorkPmListEquipDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;

/**
 * �����ֱ� ��
 * @author kim2107
 * @version $Id: AssetListTcycleDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assetListTcycleDetailServiceTarget"
 * @spring.txbn id="assetListTcycleDetailService"
 * @spring.property name="assetListTcycleDetailDAO" ref="assetListTcycleDetailDAO"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 * @spring.property name="workPmListEquipDetailDAO" ref="workPmListEquipDetailDAO"
 */
public class AssetListTcycleDetailServiceImpl implements AssetListTcycleDetailService
{
    private AssetListTcycleDetailDAO assetListTcycleDetailDAO = null;
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    private WorkPmListEquipDetailDAO workPmListEquipDetailDAO = null;
    
    public WorkPmListEquipDetailDAO getWorkPmListEquipDetailDAO() {
		return workPmListEquipDetailDAO;
	}

	public void setWorkPmListEquipDetailDAO(
			WorkPmListEquipDetailDAO workPmListEquipDetailDAO) {
		this.workPmListEquipDetailDAO = workPmListEquipDetailDAO;
	}

	public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}

	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}

	public AssetListTcycleDetailDAO getAssetListTcycleDetailDAO() {
		return assetListTcycleDetailDAO;
	}

	public void setAssetListTcycleDetailDAO(AssetListTcycleDetailDAO assetListTcycleDetailDAO) {
		this.assetListTcycleDetailDAO = assetListTcycleDetailDAO;
	}

	public AssetListTcycleDetailDTO findDetail(String equipId, String eqPmCycleId, User user)throws Exception
    {
        return assetListTcycleDetailDAO.findDetail(equipId, eqPmCycleId, user);
    }
    
	public int updateDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {   
		MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
		
		AssAssetDetailService assAssetDetailService = (AssAssetDetailService)CommonUtil.getBean("assAssetDetailService", user);
        maEqMstrCommonDTO.setEquipId(assAssetDetailService.getLastVersionEquipId(maEqMstrCommonDTO.getEquipId(), user.getCompNo()));
        
        // PM Equip ����
     	MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
     	WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
     	maPmMstrCommonDTO.setCompNo(maEqMstrCommonDTO.getCompNo());
     	workPmListEquipDetailDTO.setPmEquipId(assetListTcycleDetailDTO.getPmEquipId());
     	workPmListEquipDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
     	workPmListEquipDetailDTO.setInitWrkDate(CommonUtil.convertDate(assetListTcycleDetailDTO.getInitWrkDate()));
     	workPmListEquipDetailDTO.setIsActive(assetListTcycleDetailDTO.getIsActive());
     	assetListTcycleDetailDTO.setRemark(assetListTcycleDetailDTO.getRemark());
     	workPmListEquipDetailDAO.updateDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
        
    	maPmMstrCommonDTO.setPmId(assetListTcycleDetailDTO.getPmId());
     	maPmMstrDetailDTO  = maPmMstrDetailDAO.findDetail(maPmMstrCommonDTO, user);
     	maPmMstrDetailDTO.setEnterBy(user.getUserId());
     	maPmMstrDetailDTO.setCompNo(user.getCompNo());
     	
     	String oldCycle = maPmMstrDetailDTO.getOldCycle();
		String oldPeriodType = maPmMstrDetailDTO.getOldPeriodType();
		String oldSchedleType = maPmMstrDetailDTO.getOldScheduleType();
		String oldInitWrkDate = CommonUtil.convertDate(maPmMstrDetailDTO.getInitWrkDate());
		
		maPmMstrDetailDTO = setPmDto(maPmMstrDetailDTO, assetListTcycleDetailDTO, maEqMstrCommonDTO,user);
		
		// �����۾��ֿ��� �����۾��� ���� ó��..PM������ ����
		// update�� �� ������ ���� �κ� �����ϸ� �ȉ�.
        maPmMstrDetailDAO.updateDetail(maPmMstrDetailDTO, user);
        
     	if("N".equals(maPmMstrDetailDTO.getIsActive())){
			
			maPmMstrDetailDAO.deletePmScheduleAll(maPmMstrDetailDTO.getCompNo(),maPmMstrDetailDTO.getPmId());
			return 0;
			
    	}else if("Y".equals(maPmMstrDetailDTO.getIsActive())&&"Y".equals(maPmMstrDetailDTO.getIsLastVersion())){
    		
    		if(!oldCycle.equals(assetListTcycleDetailDTO.getCycle()) ||
    				!oldPeriodType.equals(assetListTcycleDetailDTO.getPeriodType()) ||
    				!oldInitWrkDate.equals(assetListTcycleDetailDTO.getInitWrkDate()) 
    		   ){
    			//�ֱⰡ �ٲ�����Ƿ� �������� LAST_DATE�� �����ϰ� �������� �����ؾ� ��.
    			maPmMstrDetailDAO.updateLastSchedDate(maPmMstrDetailDTO, user);
    		}
    		
    		maPmMstrDetailDAO.createPmSchedule(maPmMstrDetailDTO.getCompNo(), maPmMstrDetailDTO.getPmId(), maPmMstrDetailDTO.getEnterBy());
    		maPmMstrDetailDAO.createWorkOrder(maPmMstrDetailDTO.getCompNo(), maPmMstrDetailDTO.getPmId());

    	}
     	
     	return 0;
    }
	
	public String insertDetail(AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user) throws Exception
    {  
		assetListTcycleDetailDTO.setPmId(assetListTcycleDetailDAO.getNextSequence("SQAPM_ID"));
		
        
		AssAssetDetailService assAssetDetailService = (AssAssetDetailService)CommonUtil.getBean("assAssetDetailService", user);
		maEqMstrCommonDTO.setEquipId(assAssetDetailService.getLastVersionEquipId(maEqMstrCommonDTO.getEquipId(), user.getCompNo()));
        
		// PM������ ����
		MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
		maPmMstrDetailDTO = setPmDto(maPmMstrDetailDTO, assetListTcycleDetailDTO, maEqMstrCommonDTO,user);
		
		maPmMstrDetailDAO.insertDetail(maPmMstrDetailDTO, user);
		
		// PM Equip ����
		MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
		WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
		maPmMstrCommonDTO.setCompNo(maEqMstrCommonDTO.getCompNo());
		maPmMstrCommonDTO.setPmId(assetListTcycleDetailDTO.getPmId());
		workPmListEquipDetailDTO.setPmEquipId(assetListTcycleDetailDAO.getNextSequence("SQAPMEQUIP_ID"));
		workPmListEquipDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
		workPmListEquipDetailDTO.setInitWrkDate(CommonUtil.convertDate(assetListTcycleDetailDTO.getInitWrkDate()));
		workPmListEquipDetailDTO.setIsActive(assetListTcycleDetailDTO.getIsActive());
		assetListTcycleDetailDTO.setRemark(assetListTcycleDetailDTO.getRemark());
		workPmListEquipDetailDAO.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
		
        // �����ֱ� ���̺� Inert
		assetListTcycleDetailDAO.insertDetail(assetListTcycleDetailDTO, maEqMstrCommonDTO);
		
		// ������ ��� ���� ���
		if("N".equals(MwareConfig.getIsUsePmRevision())){
		    CommRevService commRevService = (CommRevService) CommonUtil.getBean("commRevService", user);
		    
			String histId = maPmMstrDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
			
			CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
	        commRevCommonDTO.setRevisionhistId(histId);
	        commRevCommonDTO.setCompNo(user.getCompNo());
	        commRevCommonDTO.setObjectId(maPmMstrDetailDTO.getPmId());
	        commRevCommonDTO.setObjectNo(maPmMstrDetailDTO.getPmNo());
	        commRevCommonDTO.setRevisionObjType("PMSTD");
	        commRevCommonDTO.setRevisionStatusId("C");
	        commRevCommonDTO.setRevisionStepStatusId("CMP");
	        commRevCommonDTO.setRevisionType("C");
	        commRevCommonDTO.setRevNo("1.00");
	        commRevCommonDTO.setWrkDate(DateUtil.getDateTime("yyyyMMdd"));
	        commRevCommonDTO.setWrkEmpId(user.getEmpId());
	        commRevCommonDTO.setRevDesc(maPmMstrDetailDTO.getDescription());
	        commRevService.insertRevHist(commRevCommonDTO, user);
	        
			maPmMstrDetailDAO.updatePmMstrLastVersion(maPmMstrDetailDTO, user, histId);
			
			maPmMstrDetailDTO.setIsLastVersion("Y");
        }
		
		// PM ������ ����
        if("Y".equals(maPmMstrDetailDTO.getIsActive())&&"Y".equals(maPmMstrDetailDTO.getIsLastVersion())){
            maPmMstrDetailDAO.createPmSchedule(user.getCompNo(), maPmMstrDetailDTO.getPmId(), user.getEmpId());
            maPmMstrDetailDAO.createWorkOrder(user.getCompNo(), maPmMstrDetailDTO.getPmId());
        }
		
//		return 0;
        return ( assetListTcycleDetailDTO.getPmId() + "," + workPmListEquipDetailDTO.getPmEquipId() );
    }
	
	private MaPmMstrDetailDTO setPmDto(MaPmMstrDetailDTO maPmMstrDetailDTO, AssetListTcycleDetailDTO assetListTcycleDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user)
	{
		String calibrationLabel = assetListTcycleDetailDAO.getLabelDesc(user,"calibration");
		
		maPmMstrDetailDTO.setEquipId(maEqMstrCommonDTO.getEquipId());
		maPmMstrDetailDTO.setCompNo(maEqMstrCommonDTO.getCompNo());
		maPmMstrDetailDTO.setDeptId(assetListTcycleDetailDTO.getDeptId());
		maPmMstrDetailDTO.setDescription(assetListTcycleDetailDTO.getEquipDesc()+" - "+calibrationLabel);
		maPmMstrDetailDTO.setPmId(assetListTcycleDetailDTO.getPmId());
		maPmMstrDetailDTO.setPmNo(assetListTcycleDetailDTO.getPmId());
		maPmMstrDetailDTO.setCycle(assetListTcycleDetailDTO.getCycle());
		maPmMstrDetailDTO.setPeriodType(assetListTcycleDetailDTO.getPeriodType());
		maPmMstrDetailDTO.setInitWrkDate(CommonUtil.convertDate(assetListTcycleDetailDTO.getInitWrkDate()));
		maPmMstrDetailDTO.setWoResBef(assetListTcycleDetailDTO.getWoResBef());
		maPmMstrDetailDTO.setWkCtrId(assetListTcycleDetailDTO.getWkCtrId());
		maPmMstrDetailDTO.setIsActive(assetListTcycleDetailDTO.getIsActive());
		maPmMstrDetailDTO.setRemark(assetListTcycleDetailDTO.getRemark());
		maPmMstrDetailDTO.setEnterBy(assetListTcycleDetailDTO.getEnterBy());
		maPmMstrDetailDTO.setWorkNumber("1");
		maPmMstrDetailDTO.setScheduleType("T");
		maPmMstrDetailDTO.setWoType("PMC");
		maPmMstrDetailDTO.setPmType(assetListTcycleDetailDTO.getPmcTypeId());
		maPmMstrDetailDTO.setWrkcalListId(assetListTcycleDetailDTO.getWrkcalListId());
		maPmMstrDetailDTO.setRevisionStatusId(assetListTcycleDetailDTO.getRevisionStatusId());
		maPmMstrDetailDTO.setIsLastVersion(assetListTcycleDetailDTO.getIsLastVersion());
		maPmMstrDetailDTO.setPlantId(assetListTcycleDetailDTO.getPlant());
		maPmMstrDetailDTO.setPlantDesc(assetListTcycleDetailDTO.getPlantDesc());

		return maPmMstrDetailDTO;
	}
}
