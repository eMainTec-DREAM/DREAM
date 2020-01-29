package dream.ass.asset.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.ass.asset.dao.AssAssetDetailDAO;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.ass.asset.dto.AssAssetDetailDTO;
import dream.ass.asset.service.AssAssetDetailService;
import dream.asset.list.dao.AssetListTcycleDetailDAO;
import dream.asset.list.dto.AssetListTcycleDetailDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pm.list.service.WorkPmListEquipDetailService;

/**
 * AssAsset Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: AssAssetDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="assAssetDetailServiceTarget"
 * @spring.txbn id="assAssetDetailService"
 * @spring.property name="assAssetDetailDAO" ref="assAssetDetailDAO"
 * @spring.property name="assetListTcycleDetailDAO" ref="assetListTcycleDetailDAO"
 * @spring.property name="maPmMstrDetailService" ref="maPmMstrDetailService"
 * @spring.property name="workPmListEquipDetailService" ref="workPmListEquipDetailService"
 */
public class AssAssetDetailServiceImpl implements AssAssetDetailService
{
    private AssAssetDetailDAO assAssetDetailDAO = null;
    private MaPmMstrDetailService maPmMstrDetailService = null;
    private WorkPmListEquipDetailService workPmListEquipDetailService = null;
    private AssetListTcycleDetailDAO assetListTcycleDetailDAO = null;
    
    
    public AssetListTcycleDetailDAO getAssetListTcycleDetailDAO()
    {
        return assetListTcycleDetailDAO;
    }
    public void setAssetListTcycleDetailDAO(
            AssetListTcycleDetailDAO assetListTcycleDetailDAO)
    {
        this.assetListTcycleDetailDAO = assetListTcycleDetailDAO;
    }
    public AssAssetDetailDTO findDetail(AssAssetCommonDTO assAssetCommonDTO, User user) throws Exception
    {
        return assAssetDetailDAO.findDetail(assAssetCommonDTO, user);
    }
	public WorkPmListEquipDetailService getWorkPmListEquipDetailService() {
		return workPmListEquipDetailService;
	}
	public void setWorkPmListEquipDetailService(WorkPmListEquipDetailService workPmListEquipDetailService) {
		this.workPmListEquipDetailService = workPmListEquipDetailService;
	}
	public MaPmMstrDetailService getMaPmMstrDetailService() {
		return maPmMstrDetailService;
	}

	public void setMaPmMstrDetailService(MaPmMstrDetailService maPmMstrDetailService) {
		this.maPmMstrDetailService = maPmMstrDetailService;
	}

	public int insertDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
	    assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));

         assAssetDetailDAO.insertDetail(assAssetDetailDTO, user);
         return assAssetDetailDAO.insertPoint(assAssetDetailDTO, user);
    }
    
    public int updateDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));
 
         assAssetDetailDAO.updateDetail(assAssetDetailDTO, user);
         
         assAssetDetailDAO.deleteEvalPointList(assAssetDetailDTO, user);
         
         assAssetDetailDAO.insertEvalPointList(assAssetDetailDTO, user);
         
         return 0;
    }
    
    public String makePMMstr(AssAssetCommonDTO assAssetCommonDTO, AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));
 
        String pmId = assAssetDetailDAO.isExistPm(assAssetDetailDTO, user);
        // TAPMLST에 해당 계측기의 교정기준서가 없는경우
        if("".equals(pmId) || pmId == null)
        {
            MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
            MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
            AssetListTcycleDetailDTO assetListTcycleDetailDTO = new AssetListTcycleDetailDTO();
            MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
            
            WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
            
            // 계측기의 교정작업타입, 교정 주기, 근무달력 알아오기
            String[] pmitypePeriodArr = assAssetDetailDAO.findPmitypePeriod(assAssetDetailDTO, user).split("\\+");
            pmId = assAssetDetailDAO.getNextSequence("SQAPM_ID");

            if(pmitypePeriodArr.length > 0) maPmMstrDetailDTO.setPmType(pmitypePeriodArr[0]);
            if(pmitypePeriodArr.length > 1) maPmMstrDetailDTO.setCycle(pmitypePeriodArr[1]);
            if(pmitypePeriodArr.length > 2) maPmMstrDetailDTO.setPeriodType(pmitypePeriodArr[2]);
            if(pmitypePeriodArr.length > 3) maPmMstrDetailDTO.setWrkcalListId(pmitypePeriodArr[3]);
            
            maPmMstrDetailDTO.setCompNo(user.getCompNo());
            maPmMstrDetailDTO.setPmId(pmId);
            maPmMstrDetailDTO.setPmNo(pmId);
            maPmMstrDetailDTO.setWoType("PMC");
            maPmMstrDetailDTO.setDescription(assAssetDetailDTO.getEquipDesc());
            maPmMstrDetailDTO.setIsActive("Y");
            maPmMstrDetailDTO.setDeptId(user.getDeptId());
            maPmMstrDetailDTO.setEmpId(user.getEmpId());
            maPmMstrDetailDTO.setEnterBy(user.getUserId());
            maPmMstrDetailDTO.setScheduleType("T");
            maPmMstrDetailDTO.setWoResBef("14");
            maPmMstrDetailDTO.setInitWrkDate(DateUtil.getDate());
            maPmMstrDetailDTO.setCreDate(CommonUtil.convertDateTime(DateUtil.getDateTime()));
            maPmMstrDetailDTO.setUpdDate(CommonUtil.convertDateTime(DateUtil.getDateTime()));
            maPmMstrDetailDTO.setIsLastVersion("Y");
            
            // TAPMLST insert
            maPmMstrDetailService.insertDetail(maPmMstrDetailDTO, user);

            workPmListEquipDetailDTO.setCompNo(user.getCompNo());
            maPmMstrCommonDTO.setPmId(pmId);
            workPmListEquipDetailDTO.setPmEquipId(assAssetDetailDAO.getNextSequence("SQAPMEQUIP_ID"));
            workPmListEquipDetailDTO.setEquipId(assAssetDetailDTO.getEquipId());
            workPmListEquipDetailDTO.setInitWrkDate(DateUtil.getDate());
            workPmListEquipDetailDTO.setIsActive("Y");

            // TAPMEQUIP insert //일정생성! Work Order 생성!
            workPmListEquipDetailService.insertDetail(workPmListEquipDetailDTO, maPmMstrCommonDTO, user);
       
            maEqMstrCommonDTO.setCompNo(user.getCompNo());
            maEqMstrCommonDTO.setEquipId(assAssetDetailDTO.getEquipId());
            assetListTcycleDetailDTO.setEqPmCycleId(assAssetDetailDAO.getNextSequence("SQAEQPMCYCLE_ID"));
            assetListTcycleDetailDTO.setPmId(pmId);
            
            // TAEQPMCYCLE insert
            assetListTcycleDetailDAO.insertDetail(assetListTcycleDetailDTO, maEqMstrCommonDTO);
 
        }
        
        return pmId;
    }

    public int asscompletedDetail(AssAssetCommonDTO assAssetCommonDTO, AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));

         int rtnValue = assAssetDetailDAO.asscompletedDetail(assAssetDetailDTO, user);

         // 평가구분이 최종평가일 때만 설비등급으로 반영
         if(rtnValue != 0 && "LT".equals(assAssetDetailDTO.getAssTypeId()))
         {
//             assAssetDetailDTO.setEquipId(assAssetCommonDTO.getFilterEquipId());
             updateEqGrade(assAssetDetailDTO, user);
         }
         
         if("TL".equals(assAssetCommonDTO.getEqCtgType())) 
         {
             makePMMstr(assAssetCommonDTO, assAssetDetailDTO, user);
         }
         
 		 return rtnValue;
         
    }
    
    public String checkDetail(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));

        return assAssetDetailDAO.checkDetail(assAssetDetailDTO, user);
    }
    
    public String findGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));

        return assAssetDetailDAO.updateGrade(assAssetDetailDTO, user);
    }
    
    public String updateScore(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        assAssetDetailDTO.setEquipId(getLastVersionEquipId(assAssetDetailDTO.getEquipId(), user.getCompNo()));
 
        return assAssetDetailDAO.updateScore(assAssetDetailDTO, user);
    }
    
    public AssAssetDetailDAO getAssAssetDetailDAO() {
        return assAssetDetailDAO;
    }

    public void setAssAssetDetailDAO(AssAssetDetailDAO assAssetDetailDAO) {
        this.assAssetDetailDAO = assAssetDetailDAO;
    }

    @Override
    public int updateEqGrade(AssAssetDetailDTO assAssetDetailDTO, User user) throws Exception
    {
        return assAssetDetailDAO.updateEqGrade(assAssetDetailDTO, user);
    }
    @Override
    public String getLastVersionEquipId(String equipId, String compNo)
    {
        // TODO Auto-generated method stub
        String lastEquipId= assAssetDetailDAO.getLastVersionEquipId(equipId, compNo);
        
        if("".equals(lastEquipId) || lastEquipId == null) lastEquipId = equipId;
        
        return lastEquipId;
    }
}
