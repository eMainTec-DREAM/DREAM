package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.pm.list.dao.WorkPmiDInsDetailDAO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;
import dream.work.pm.list.service.WorkPmiDInsDetailService;
import dream.work.pm.list.service.WorkPmiDInsListService;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * WorkPmiDIns Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmiDInsDetailServiceTarget"
 * @spring.txbn id="workPmiDInsDetailService"
 * @spring.property name="workPmiDInsDetailDAO" ref="workPmiDInsDetailDAO"
 */
public class WorkPmiDInsDetailServiceImpl implements WorkPmiDInsDetailService
{
    private WorkPmiDInsDetailDAO workPmiDInsDetailDAO = null;
    
    public WorkPmiDInsDetailDTO findDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, User user) throws Exception
    {   
        WorkPmiDInsListService workPmiDInsListService = (WorkPmiDInsListService) CommonUtil.getBean("workPmiDInsListService", user);
        return (WorkPmiDInsDetailDTO) CommonUtil.makeDetailFromList(workPmiDInsListService.findList(workPmiDInsCommonDTO, user), new WorkPmiDInsDetailDTO());
    }
    
    public int insertDetail(WorkPmiDInsCommonDTO workPmiDInsCommonDTO, WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception
    {
         return workPmiDInsDetailDAO.insertDetail(workPmiDInsCommonDTO, workPmiDInsDetailDTO, user);
    }
    
    public int updateDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception
    {
        List list = new ArrayList();
        list.add(workPmiDInsDetailDTO);
        return this.updateList(list, user)[0];
    }
    
    @Override
    public int[] updateList(List list, User user) throws Exception
    {
        return workPmiDInsDetailDAO.update(list, user);
    }

    public WorkPmiDInsDetailDAO getWorkPmiDInsDetailDAO() {
        return workPmiDInsDetailDAO;
    }

    public void setWorkPmiDInsDetailDAO(WorkPmiDInsDetailDAO workPmiDInsDetailDAO) {
        this.workPmiDInsDetailDAO = workPmiDInsDetailDAO;
    }
    
    public String checkPoint(WorkPmiDInsDetailDTO workPmiDInsDetailDTO,User user) throws Exception
    {
        return workPmiDInsDetailDAO.checkPoint(workPmiDInsDetailDTO,user );
    }

    public int completeDetail(WorkPmiDInsDetailDTO workPmiDInsDetailDTO, User user) throws Exception
    {        
        workPmiDInsDetailDAO.completeSched(workPmiDInsDetailDTO);
        workPmiDInsDetailDAO.executePmUpdate(workPmiDInsDetailDTO);
        workPmiDInsDetailDAO.completeDetail(workPmiDInsDetailDTO);
        
        //이상점검항목 처리
  		MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", user);
  		maBdPointDetailService.checkNgPoint("DINS", workPmiDInsDetailDTO.getPmInsDListId(), "C", DateUtil.getDate(), user);
  		
        return 0; 
    }
}
