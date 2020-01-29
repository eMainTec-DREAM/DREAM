package dream.work.alarm.req.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.alarm.req.dao.WorkAlarmReqDAO;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;
import dream.work.alarm.req.service.WorkAlarmReqService;

/**
 * 荐府夸没 立荐 - 格废 serviceimpl 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workAlarmReqServiceTarget"
 * @spring.txbn id="workAlarmReqService"
 * @spring.property name="workAlarmReqDAO" ref="workAlarmReqDAO"
 */
public class WorkAlarmReqServiceImpl implements WorkAlarmReqService
{
    private WorkAlarmReqDAO workAlarmReqDAO = null;

    public WorkAlarmReqDAO getWorkAlarmReqDAO() 
    {
        return workAlarmReqDAO;
    }

    public void setWorkAlarmReqDAO(WorkAlarmReqDAO workAlarmReqDAO) 
    {
        this.workAlarmReqDAO = workAlarmReqDAO;
    }

    public List findList(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception
    {      
        return workAlarmReqDAO.findList(workAlarmReqDTO, user);
    }
    
    public String findTotalCount(WorkAlarmReqDTO workAlarmReqDTO, User user)
    {      
    	return workAlarmReqDAO.findTotalCount(workAlarmReqDTO, user);
    }
    
	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        WorkAlarmReqDTO workAlarmReqDTO = new WorkAlarmReqDTO();
        List<WorkAlarmReqDTO> list = new ArrayList<WorkAlarmReqDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		workAlarmReqDTO.setAlarmReqId(deleteRows[i]);
        		
        		list.add((WorkAlarmReqDTO)BeanUtils.cloneBean(workAlarmReqDTO));
        	}
        	result = workAlarmReqDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public WorkAlarmReqDTO findDetail(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception 
	{
		return (WorkAlarmReqDTO)CommonUtil.makeDetailFromList(workAlarmReqDAO.findList(workAlarmReqDTO, user), workAlarmReqDTO);
	}
	
	public int insertDetail(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception
    {
		if(CommonUtil.isNullCheck(workAlarmReqDTO.getAlarmReqId()))
        	workAlarmReqDTO.setAlarmReqId(workAlarmReqDAO.getNextSequence("SQAALARMREQ_ID"));
        
        List<WorkAlarmReqDTO> list = new ArrayList<WorkAlarmReqDTO>();
        list.add(workAlarmReqDTO);
        
        int[] rtn =  this.insertDetail(list, user);
        
        return rtn[0];
    }

	public int[] insertDetail(List<WorkAlarmReqDTO> list, User user) throws Exception
    {
        return workAlarmReqDAO.insertDetail(list, user);
    }
	
	@Override
	public int[] linkWoReq(WorkAlarmReqDTO workAlarmReqDTO, User user) throws Exception {
		int[] result = null;
		
		String[] multiKey = workAlarmReqDTO.getMultiKey().split("\\+");
		List<WorkAlarmReqDTO> list = new ArrayList<WorkAlarmReqDTO>();
		if(!multiKey.equals(null)){
			for (int i=0; i<multiKey.length; i++)
			{
				WorkAlarmReqDTO dto = (WorkAlarmReqDTO) BeanUtils.cloneBean(workAlarmReqDTO);
				dto.setWoReqId(multiKey[i]);
				
				if(CommonUtil.isNullCheck(workAlarmReqDTO.getAlarmReqId())) {
					dto.setAlarmReqId(workAlarmReqDAO.getNextSequence("SQAALARMREQ_ID"));
				}
				
				list.add(dto);
			}
			result = this.insertDetail(list, user);
		}
		return result;
	}
}