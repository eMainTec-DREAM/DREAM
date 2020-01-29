package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fins.gt.util.StringUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthListService;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.service.MaWoSchedListService;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodListService;
import dream.work.pm.list.dao.WorkPmListEquipListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListEquipDetailDTO;
import dream.work.pm.list.service.WorkPmListEquipListService;

/**
 * 예방설비 목록
 * @author kim21017
 * @version $Id: WorkPmListEquipListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListEquipListServiceTarget"
 * @spring.txbn id="workPmListEquipListService"
 * @spring.property name="workPmListEquipListDAO" ref="workPmListEquipListDAO"
 */
public class WorkPmListEquipListServiceImpl implements WorkPmListEquipListService
{
    private WorkPmListEquipListDAO workPmListEquipListDAO = null;


	public List findEqList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return workPmListEquipListDAO.findEqList(maPmMstrCommonDTO, loginUser);
    }

	public WorkPmListEquipListDAO getWorkPmListEquipListDAO() {
		return workPmListEquipListDAO;
	}

	public void setWorkPmListEquipListDAO(WorkPmListEquipListDAO workPmListEquipListDAO) {
		this.workPmListEquipListDAO = workPmListEquipListDAO;
	}
	
	public int deleteEqList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0){
            String pmEquipId = StringUtils.join(deleteRows, "+");
            
            //DELETE TAPMINSSCHED
            WorkCalPmRInsPeriodListService workCalPmRInsPeriodListService = (WorkCalPmRInsPeriodListService) CommonUtil.getBean("workCalPmRInsPeriodListService", loginUser);
            WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
            workCalPmRInsPeriodCommonDTO.setPmEquipId(pmEquipId);
            workCalPmRInsPeriodCommonDTO.setPmSchedStatus("-C");
            String[] pmInsSchedIds = StringUtil.toSingleArray(workCalPmRInsPeriodListService.findSchedList(workCalPmRInsPeriodCommonDTO, loginUser), "PMINSSCHEDID");
            workCalPmRInsPeriodListService.deleteSched(pmInsSchedIds, loginUser);
            
            //DELETE TAPMINSDSCHED
            WorkCalPmDInsMonthListService workCalPmDInsMonthListService = (WorkCalPmDInsMonthListService) CommonUtil.getBean("workCalPmDInsMonthListService", loginUser);
            WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO = new WorkCalPmDInsMonthCommonDTO();
            workCalPmDInsMonthCommonDTO.setPmEquipId(pmEquipId);
            workCalPmDInsMonthCommonDTO.setPmSchedStatus("-C");
            String[] pmInsDSchedIds = StringUtil.toSingleArray(workCalPmDInsMonthListService.findSchedList(workCalPmDInsMonthCommonDTO, loginUser), "PMINSDSCHEDID");
            workCalPmDInsMonthListService.deleteSched(pmInsDSchedIds, loginUser);
            
            //DELETE TAPMSCHED
            MaWoSchedListService maWoSchedListService = (MaWoSchedListService) CommonUtil.getBean("maWoSchedListService", loginUser);
            MaWoSchedCommonDTO maWoSchedCommonDTO = new MaWoSchedCommonDTO();
            maWoSchedCommonDTO.setPmEquipId(pmEquipId);
            maWoSchedCommonDTO.setPmSchedStatus("-C");
            String[] pmSchedIds = StringUtil.toSingleArray(maWoSchedListService.findSchedList(maWoSchedCommonDTO, loginUser), "PMSCHEDID");
            maWoSchedListService.deleteSched(pmSchedIds, loginUser);
            
            //DELETE TAPMEQUIP
            List list = new ArrayList();
            WorkPmListEquipDetailDTO workPmListEquipDetailDTO = new WorkPmListEquipDetailDTO();
            for(String id : deleteRows)
            {
                workPmListEquipDetailDTO.setPmEquipId(id);
                list.add(BeanUtils.cloneBean(workPmListEquipDetailDTO));
            }
            result = workPmListEquipListDAO.updateDeleteTag(list, loginUser).length;
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {
		return workPmListEquipListDAO.findTotalCount(maPmMstrCommonDTO, user);
	}
}

