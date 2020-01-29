package dream.work.pm.list.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.pm.list.dao.WorkPmiCInsDetailDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;
import dream.work.pm.list.service.WorkPmiCInsDetailService;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * WorkPmiCIns Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiCInsDetailServiceTarget"
 * @spring.txbn id="workPmiCInsDetailService"
 * @spring.property name="workPmiCInsDetailDAO" ref="workPmiCInsDetailDAO"
 */
public class WorkPmiCInsDetailServiceImpl implements WorkPmiCInsDetailService
{
    private WorkPmiCInsDetailDAO workPmiCInsDetailDAO = null;
    
    public WorkPmiCInsDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) throws Exception
    {   
        String pmInsDListId = "";
        WorkPmiCInsDetailDTO workPmiCInsDetailDTO = new WorkPmiCInsDetailDTO();
        
        if("".equals(workPmiCInsCommonDTO.getPmInsDListId()))
        {
            String cnt = workPmiCInsDetailDAO.checkList(workPmiCInsCommonDTO);
            
            if(cnt == "")
            {
                // Create and 조회
                pmInsDListId = workPmiCInsDetailDAO.getNextSequence("SQAPMINSDLIST_ID");
                
                workPmiCInsCommonDTO.setPmInsDListId(pmInsDListId);
                workPmiCInsDetailDTO.setPmInsDListId(pmInsDListId);

                // DetailDTO에 자동입력되어야할 값들을 셋팅
                workPmiCInsDetailDTO.setPmId(workPmiCInsCommonDTO.getPopupPmId());
                workPmiCInsDetailDTO.setPmInsDListNo(workPmiCInsCommonDTO.getPopupWorkPointNo());
                
                workPmiCInsDetailDTO.setPmTypeId("DINS");
                
                // WO상태
                workPmiCInsDetailDTO.setSchedStatusId("P");
                workPmiCInsDetailDTO.setSchedStatusDesc("P");
                
                // 교대조
                workPmiCInsDetailDTO.setShiftTypeId(user.getShiftType());
                workPmiCInsDetailDTO.setShiftTypeDesc(user.getShiftTypeDesc());
                
                // 담당부서
                workPmiCInsDetailDTO.setDeptId(user.getDeptId());
                workPmiCInsDetailDTO.setDeptDesc(user.getDeptDesc());
                
                // 담당자
                workPmiCInsDetailDTO.setEmpId(user.getEmpId());
                workPmiCInsDetailDTO.setEmpDesc(user.getEmpName());
                
                // 작업 시작일자 / 작업 종료일자
                workPmiCInsDetailDTO.setStartDate(DateUtil.getDate());
                workPmiCInsDetailDTO.setEndDate(DateUtil.getDate());
                
                workPmiCInsDetailDAO.insertDetail(workPmiCInsCommonDTO, workPmiCInsDetailDTO, user);
            }
            else
            {
                workPmiCInsCommonDTO.setPmInsDListId(cnt);
            }
            
        }
        
        workPmiCInsDetailDTO = workPmiCInsDetailDAO.findDetail(workPmiCInsCommonDTO, user);
        
        return workPmiCInsDetailDTO;
    }
    
    public int insertDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception
    {
         return workPmiCInsDetailDAO.insertDetail(workPmiCInsCommonDTO, workPmiCInsDetailDTO, user);
    }
    
    public int updateDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception
    {
    	int rtnValue  = 0;
    	
    	rtnValue = workPmiCInsDetailDAO.updateDetail(workPmiCInsDetailDTO, user);
    	
    	rtnValue = rtnValue +  workPmiCInsDetailDAO.updatePmInsDSched(workPmiCInsDetailDTO, user);
         
         return rtnValue;
    }

    public WorkPmiCInsDetailDAO getWorkPmiCInsDetailDAO() {
        return workPmiCInsDetailDAO;
    }

    public void setWorkPmiCInsDetailDAO(WorkPmiCInsDetailDAO workPmiCInsDetailDAO) {
        this.workPmiCInsDetailDAO = workPmiCInsDetailDAO;
    }
    
    public String checkPoint(WorkPmiCInsDetailDTO workPmiCInsDetailDTO,User user) throws Exception
    {
        return workPmiCInsDetailDAO.checkPoint(workPmiCInsDetailDTO,user );
    }

    public int completeDetail(WorkPmiCInsDetailDTO workPmiCInsDetailDTO, User user) throws Exception
    {        
        workPmiCInsDetailDAO.completeSched(workPmiCInsDetailDTO);
        workPmiCInsDetailDAO.executePmUpdate(workPmiCInsDetailDTO);
        workPmiCInsDetailDAO.completeDetail(workPmiCInsDetailDTO);
        
        //이상점검항목 처리
  		MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
  		maBdPointDetailService.checkNgPoint("CINS", workPmiCInsDetailDTO.getPmInsDListId(), "C", DateUtil.getDate(), user);
  		
        return 0;
    }
}
