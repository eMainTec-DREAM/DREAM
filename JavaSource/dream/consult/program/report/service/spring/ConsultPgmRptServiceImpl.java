package dream.consult.program.report.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.report.dao.ConsultPgmRptDAO;
import dream.consult.program.report.dto.ConsultPgmRptDTO;
import dream.consult.program.report.service.ConsultPgmRptService;

/**
 * Report List - ¸ñ·Ï serviceimpl 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmRptServiceTarget"
 * @spring.txbn id="consultPgmRptService"
 * @spring.property name="consultPgmRptDAO" ref="consultPgmRptDAO"
 */
public class ConsultPgmRptServiceImpl implements ConsultPgmRptService
{
    private ConsultPgmRptDAO consultPgmRptDAO = null;

    public ConsultPgmRptDAO getConsultPgmRptDAO() 
    {
        return consultPgmRptDAO;
    }

    public void setConsultPgmRptDAO(ConsultPgmRptDAO consultPgmRptDAO) 
    {
        this.consultPgmRptDAO = consultPgmRptDAO;
    }

    public List findList(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception
    {      
        return consultPgmRptDAO.findList(consultPgmRptDTO, user);
    }
    
    public String findTotalCount(ConsultPgmRptDTO consultPgmRptDTO, User user)
    {      
    	return consultPgmRptDAO.findTotalCount(consultPgmRptDTO, user);
    }

	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        ConsultPgmRptDTO consultPgmRptDTO = new ConsultPgmRptDTO();
        List<ConsultPgmRptDTO> list = new ArrayList<ConsultPgmRptDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		consultPgmRptDTO.setRptListId(deleteRows[i]);
        		
        		list.add((ConsultPgmRptDTO) BeanUtils.cloneBean(consultPgmRptDTO));
        	}
        	result = consultPgmRptDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public ConsultPgmRptDTO findDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception 
	{
		return (ConsultPgmRptDTO)CommonUtil.makeDetailFromList(consultPgmRptDAO.findList(consultPgmRptDTO, user), consultPgmRptDTO);
	}
	
	@Override
    public int updateDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception
    {
        int[] result = null;
        
        List<ConsultPgmRptDTO> list = new ArrayList<ConsultPgmRptDTO>();
        list.add(consultPgmRptDTO);
        
        result = consultPgmRptDAO.updateDetail(list, user);
        
        return result[0];
    }
	
	public String checkDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception
	{
		return consultPgmRptDAO.checkDetail(consultPgmRptDTO, user);
	}

	public int[] insertDetail(List<ConsultPgmRptDTO> list, User user) throws Exception
    {
        return consultPgmRptDAO.insertDetail(list, user);
    }

	public int insertDetail(ConsultPgmRptDTO consultPgmRptDTO, User user) throws Exception
    {
       	if(CommonUtil.isNullCheck(consultPgmRptDTO.getRptListId()))
       		consultPgmRptDTO.setRptListId(consultPgmRptDAO.getNextSequence("SQARPTLIST_ID"));
        
        List<ConsultPgmRptDTO> list = new ArrayList<ConsultPgmRptDTO>();
        list.add((ConsultPgmRptDTO) BeanUtils.cloneBean(consultPgmRptDTO));
        
        int[] rtn =  this.insertDetail(list, user);
        
        return rtn[0];
    }
}