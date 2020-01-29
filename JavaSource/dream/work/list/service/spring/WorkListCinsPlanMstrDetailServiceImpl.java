package dream.work.list.service.spring;

import common.bean.User;
import common.util.DateUtil;
import dream.work.list.dao.WorkListCinsPlanMstrDetailDAO;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;
import dream.work.list.service.WorkListCinsPlanMstrDetailService;

/**
 * WorkListCinsPlanMstr Page - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workListCinsPlanMstrDetailServiceTarget"
 * @spring.txbn id="workListCinsPlanMstrDetailService"
 * @spring.property name="workListCinsPlanMstrDetailDAO" ref="workListCinsPlanMstrDetailDAO"
 */
public class WorkListCinsPlanMstrDetailServiceImpl implements WorkListCinsPlanMstrDetailService
{
    private WorkListCinsPlanMstrDetailDAO workListCinsPlanMstrDetailDAO = null;
    
    public WorkListCinsPlanMstrDetailDTO findDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, User user) throws Exception
    {   
        return workListCinsPlanMstrDetailDAO.findDetail(workListCinsPlanMstrCommonDTO, user);
    }
    
    public int insertDetail(WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO, WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception
    {
         return workListCinsPlanMstrDetailDAO.insertDetail(workListCinsPlanMstrCommonDTO, workListCinsPlanMstrDetailDTO, user);
    }
    
    public int updateDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception
    {
         return workListCinsPlanMstrDetailDAO.updateDetail(workListCinsPlanMstrDetailDTO, user);
    }

    public WorkListCinsPlanMstrDetailDAO getWorkListCinsPlanMstrDetailDAO() {
        return workListCinsPlanMstrDetailDAO;
    }

    public void setWorkListCinsPlanMstrDetailDAO(WorkListCinsPlanMstrDetailDAO workListCinsPlanMstrDetailDAO) {
        this.workListCinsPlanMstrDetailDAO = workListCinsPlanMstrDetailDAO;
    }
    
    public String checkPoint(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO,User user) throws Exception
    {
        return workListCinsPlanMstrDetailDAO.checkPoint(workListCinsPlanMstrDetailDTO,user );
    }

    public int completeDetail(WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO, User user) throws Exception
    {   
        int pmpointCnt = 0;     
        
        workListCinsPlanMstrDetailDAO.completeDetail(workListCinsPlanMstrDetailDTO, user); // INSERT TAPMINSDLIST ������������
        pmpointCnt = workListCinsPlanMstrDetailDAO.getPmInsdPointCnt(workListCinsPlanMstrDetailDTO); // PMPOINT ���� �˾ƿ���
        
        if (pmpointCnt > 0)
        {
            for(int i = 0; i< pmpointCnt; i++)
            {
                workListCinsPlanMstrDetailDTO.setPmpointCnt(""+(i+1)+"");
                workListCinsPlanMstrDetailDAO.inserPmInsdPoint(workListCinsPlanMstrDetailDTO); // INSERT PMINSDPOINT ���� ������ŭ �ֱ�.
            }
        }
        return workListCinsPlanMstrDetailDAO.completeSched(workListCinsPlanMstrDetailDTO); // UPDATE TAPMINSDSCHED ���º���
    }
}
